package com.example.kruger.petagram.pushnotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.SyncStateContract;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.kruger.petagram.MainActivity;
import com.example.kruger.petagram.R;
import com.example.kruger.petagram.SeeUserActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getName();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
        sendNotification(remoteMessage.getNotification().getTitle(),
                remoteMessage.getNotification().getBody(),
                remoteMessage.getData().get("id_user"));
    }

    private void sendNotification(String title, String description, String id_user) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , intent, PendingIntent.FLAG_ONE_SHOT);
        String channelId = getString(R.string.app_name);
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        ArrayList<NotificationCompat.Action> actions = new ArrayList<>();

        Intent intentViewProfile = new Intent(this, MainActivity.class);
        PendingIntent pendingIntentViewProfile = PendingIntent.getActivity(this, 0, intentViewProfile, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Action action_view_profile = new NotificationCompat.Action.Builder(R.drawable.ic_full_profile,
                getString(R.string.see_profile), pendingIntentViewProfile).build();

        Intent intentFollowUnfollow = new Intent();
        intentFollowUnfollow.setAction("F_U").putExtra("id_user", id_user);
        PendingIntent pendingIntentFollowUnfollow = PendingIntent.getBroadcast(this, 0, intentFollowUnfollow, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Action action_follow_unfollow = new NotificationCompat.Action.Builder(R.drawable.ic_full_follow_unfollow,
                getString(R.string.follow_unfolow), pendingIntentFollowUnfollow).build();

        Intent intentViewUser = new Intent(this, SeeUserActivity.class);
        intentViewUser.putExtra("id_user", id_user);
        PendingIntent pendingIntentViewUser = PendingIntent.getActivity(this, 0, intentViewUser, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Action action_view_user = new NotificationCompat.Action.Builder(R.drawable.ic_full_user,
                getString(R.string.see_user), pendingIntentViewUser).build();

        actions.add(action_view_profile);
        actions.add(action_follow_unfollow);
        actions.add(action_view_user);

        NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender();

        NotificationCompat.Builder notificationBuilder = new
            NotificationCompat.Builder(MyFirebaseMessagingService.this, channelId)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(title)
                .setContentText(description)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)
                .extend(wearableExtender.addActions(actions));

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(0, notificationBuilder.build());
    }

}