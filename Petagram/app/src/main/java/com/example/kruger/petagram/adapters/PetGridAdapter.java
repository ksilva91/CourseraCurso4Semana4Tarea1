package com.example.kruger.petagram.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kruger.petagram.MainActivity;
import com.example.kruger.petagram.R;
import com.example.kruger.petagram.model.Media;
import com.example.kruger.petagram.model.Pet;
import com.example.kruger.petagram.restApi.ConstantesRestApi;
import com.example.kruger.petagram.restApi.EndpointsApi;
import com.example.kruger.petagram.restApi.adapter.RestApiAdapter;
import com.example.kruger.petagram.restApi.model.LikeResponse;
import com.example.kruger.petagram.restApi.model.MediaResponse;
import com.example.kruger.petagram.restApi.model.UniversalResponse;
import com.example.kruger.petagram.restApi.model.UserResponse;
import com.example.kruger.petagram.utils.SharedPreferencesManager;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Kruger on 27/12/2017.
 */

public class PetGridAdapter extends RecyclerView.Adapter<PetGridAdapter.MediaViewHolder> {
    private ArrayList<Media> mediaArrayList;
    private Activity activity;

    public PetGridAdapter(ArrayList<Media> mediaArrayList, Activity activity) {
        this.mediaArrayList = mediaArrayList;
        this.activity = activity;
    }

    @Override
    public MediaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_list_pet, parent, false);
        return new MediaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MediaViewHolder holder, final int position) {
        final Media media = mediaArrayList.get(position);
        Picasso.with(activity)
                .load(media.getUrlPhoto())
                .placeholder(R.drawable.image_not_found)
                .into(holder.ivImage);
        holder.tvMediaRating.setText(String.valueOf(media.getLikes()));
        holder.ivLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendLikeToInstagram(media.getId(), holder, position);

            }
        });
    }

    private void sendLikeToInstagram(String idPhoto, final MediaViewHolder holder, final int position){
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonSendLike = restApiAdapter.buildGsonUniversalDeserializer();
        EndpointsApi endpointsApi = restApiAdapter.setConnectionRestApiInstagram(gsonSendLike);
        Call<UniversalResponse> universalResponseCall = endpointsApi.setLike(idPhoto);

        universalResponseCall.enqueue(new Callback<UniversalResponse>() {
            @Override
            public void onResponse(Call<UniversalResponse> call, Response<UniversalResponse> response) {
                Media media = mediaArrayList.get(position);
                int likes = media.getLikes() + 1;
                media.setLikes(likes);
                holder.tvMediaRating.setText(String.valueOf(likes));
                sendPush(media.getId(),media.getName());
            }

            @Override
            public void onFailure(Call<UniversalResponse> call, Throwable throwable) {
                Toast.makeText(activity, "Ocurrió un error al enviar el like a Instagram", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void sendPush(String id_foto_instagram, String usuario_instagram){
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.setConnectionRestApiServer();
        Call<LikeResponse> likeResponseCall = endpointsApi.procesarLike(id_foto_instagram, SharedPreferencesManager.getUserId(activity),
                refreshedToken, usuario_instagram);

        likeResponseCall.enqueue(new Callback<LikeResponse>() {
            @Override
            public void onResponse(Call<LikeResponse> call, Response<LikeResponse> response) {
                Toast.makeText(activity, "Enviado el push", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<LikeResponse> call, Throwable throwable) {
                Toast.makeText(activity, "¡Algo pasó en la conexión! Intenta de nuevo", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mediaArrayList.size();
    }

    static class MediaViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImage;
        TextView tvMediaRating;
        ImageView ivLike;

        MediaViewHolder(View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.iv_image);
            tvMediaRating = itemView.findViewById(R.id.tv_rating);
            ivLike = itemView.findViewById(R.id.ivLike);
        }
    }
}
