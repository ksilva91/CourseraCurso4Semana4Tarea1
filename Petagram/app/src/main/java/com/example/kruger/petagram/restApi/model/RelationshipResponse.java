package com.example.kruger.petagram.restApi.model;

/**
 * Created by Kruger on 7/2/2018.
 */

public class RelationshipResponse {
    private String outgoing_status;
    private String incoming_status;
    private String status;

    public String getOutgoing_status() {
        return outgoing_status;
    }

    public void setOutgoing_status(String outgoing_status) {
        this.outgoing_status = outgoing_status;
    }

    public String getIncoming_status() {
        return incoming_status;
    }

    public void setIncoming_status(String incoming_status) {
        this.incoming_status = incoming_status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
