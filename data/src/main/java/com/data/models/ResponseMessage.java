package com.data.models;

/**
 * Created by Antoan on 2/26/2017.
 */

public class ResponseMessage {
    private Boolean isSuccesful;

    public ResponseMessage() {

    }
    public ResponseMessage(Boolean isSuccesful) {
        this.isSuccesful = isSuccesful;
    }

    public Boolean getSuccesful() {
        return isSuccesful;
    }

    public void setSuccesful(Boolean succesful) {
        isSuccesful = succesful;
    }
}
