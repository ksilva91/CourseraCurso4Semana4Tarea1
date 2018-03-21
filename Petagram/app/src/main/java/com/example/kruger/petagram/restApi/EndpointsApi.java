package com.example.kruger.petagram.restApi;

import com.example.kruger.petagram.model.User;
import com.example.kruger.petagram.restApi.model.LikeResponse;
import com.example.kruger.petagram.restApi.model.MediaResponse;
import com.example.kruger.petagram.restApi.model.RelationshipResponse;
import com.example.kruger.petagram.restApi.model.UniversalResponse;
import com.example.kruger.petagram.restApi.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface EndpointsApi {

    @GET(ConstantesRestApi.INSTAGRAM_URL_GET_USERS)
    Call<MediaResponse> getUser(@Query("q") String username, @Query("access_token") String accessToken);

    @GET(ConstantesRestApi.INSTAGRAM_URL_GET_RECENT_MEDIA_BY_USER_ID)
    Call<MediaResponse> getRecentMediaByUserId(@Path("user-id") String userId);

    @POST(ConstantesRestApi.INSTAGRAM_URL_POST_SET_LIKE)
    Call<UniversalResponse> setLike(@Path("media-id") String mediaId);

    @GET(ConstantesRestApi.INSTAGRAM_URL_GET_RELATIONSHIP)
    Call<RelationshipResponse> getRelationship(@Path("user-id") String userId);

    @FormUrlEncoded
    @POST(ConstantesRestApi.INSTAGRAM_URL_GET_RELATIONSHIP)
    Call<RelationshipResponse> setRelationship(@Path("user-id") String userId, @Field("action") String action);


    @FormUrlEncoded
    @POST(ConstantesRestApi.SERVER_URL_REGISTER_USER)
    Call<UserResponse> registerUser(@Field("id_dispositivo") String id_dispositivo, @Field("id_usuario_instagram") String id_usuario_instagram);

    @FormUrlEncoded
    @POST(ConstantesRestApi.SERVER_URL_PROCESAR_LIKE)
    Call<LikeResponse> procesarLike(@Field("id_foto_instagram") String id_foto_instagram, @Field("id_usuario_instagram") String id_usuario_instagram,
                                    @Field("id_dispositivo") String id_dispositivo, @Field("usuario_instagram") String usuario_instagram);

}
