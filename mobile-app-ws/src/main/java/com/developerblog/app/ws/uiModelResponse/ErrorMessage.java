package com.developerblog.app.ws.uiModelResponse;

import java.util.Date;

public class ErrorMessage {

    private Date timestamp;
    private String message;

    public ErrorMessage() {
    }

    public ErrorMessage(Date date, String message) {
        this.timestamp = date;
        this.message = message;
    }

    public Date getDate() {
        return timestamp;
    }
    public void setDate(Date date) {
        this.timestamp = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    
    
}
