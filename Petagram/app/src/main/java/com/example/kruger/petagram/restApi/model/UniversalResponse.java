package com.example.kruger.petagram.restApi.model;


import com.example.kruger.petagram.model.Media;
import com.example.kruger.petagram.model.User;

import java.util.ArrayList;


public class UniversalResponse {

    String StatusCode;

    public String getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(String statusCode) {
        StatusCode = statusCode;
    }
}
