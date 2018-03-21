package com.example.kruger.petagram.restApi;


public final class ConstantesRestApi {



    public static final String INSTAGRAM_VERSION = "/v1/";
    public static final String INSTAGRAM_ROOT_URL = "https://api.instagram.com" + INSTAGRAM_VERSION;
    public static final String INSTAGRAM_ACCESS_TOKEN = "6974332266.6e31c05.f01af65cdea641538194327adfd0b952";
    public static final String INSTAGRAM_KEY_ACCESS_TOKEN = "access_token=";

    public static final String INSTAGRAM_KEY_GET_USERS = "users/search";
    public static final String INSTAGRAM_URL_GET_USERS = INSTAGRAM_KEY_GET_USERS;
    //https://api.instagram.com/v1/users/search?q=jack&access_token=ACCESS-TOKEN

    public static final String INSTAGRAM_KEY_GET_RECENT_MEDIA_BY_USER_ID = "users/{user-id}/media/recent/?";
    public static final String INSTAGRAM_URL_GET_RECENT_MEDIA_BY_USER_ID = INSTAGRAM_KEY_GET_RECENT_MEDIA_BY_USER_ID + INSTAGRAM_KEY_ACCESS_TOKEN + INSTAGRAM_ACCESS_TOKEN;
    //https://api.instagram.com/v1/users/{user-id}/media/recent/?access_token=ACCESS-TOKEN

    public static final String INSTAGRAM_KEY_POST_SET_LIKE = "media/{media-id}/likes/?";
    public static final String INSTAGRAM_URL_POST_SET_LIKE = INSTAGRAM_KEY_POST_SET_LIKE + INSTAGRAM_KEY_ACCESS_TOKEN + INSTAGRAM_ACCESS_TOKEN;
    //https://api.instagram.com/v1/media/{media-id}/likes

    public static final String INSTAGRAM_KEY_GET_RELATIONSHIP = "users/{user-id}/relationship?";
    public static final String INSTAGRAM_URL_GET_RELATIONSHIP = INSTAGRAM_KEY_GET_RELATIONSHIP + INSTAGRAM_KEY_ACCESS_TOKEN + INSTAGRAM_ACCESS_TOKEN;


    public static final String SERVER_ROOT_URL = "https://desolate-depths-46045.herokuapp.com";
    public static final String SERVER_URL_REGISTER_USER = "registrar-usuario";
    public static final String SERVER_URL_PROCESAR_LIKE = "procesar-like";
}
