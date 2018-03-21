package com.example.kruger.petagram.restApi.model;

/**
 * Created by Kruger on 7/2/2018.
 */

public class UserResponse {
    private String id_dispositivo;
    private String id_usuario_instagram;

    public String getId_dispositivo() {
        return id_dispositivo;
    }

    public void setId_dispositivo(String id_dispositivo) {
        this.id_dispositivo = id_dispositivo;
    }

    public String getId_usuario_instagram() {
        return id_usuario_instagram;
    }

    public void setId_usuario_instagram(String id_usuario_instagram) {
        this.id_usuario_instagram = id_usuario_instagram;
    }
}
