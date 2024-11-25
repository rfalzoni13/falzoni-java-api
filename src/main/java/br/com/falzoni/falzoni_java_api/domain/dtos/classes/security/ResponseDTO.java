package br.com.falzoni.falzoni_java_api.domain.dtos.classes.security;

public class ResponseDTO {
    private int statusCode;
    private String message;

    public ResponseDTO(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
