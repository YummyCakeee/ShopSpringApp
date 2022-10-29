package ru.nikita.spring.ShopSpringApp.util;

public class ResponseData {
    private Object data;
    private String message;
    private boolean success;

    public ResponseData() {
    }

    public ResponseData(Object data, boolean success) {
        this.data = data;
        this.success = success;
    }

    public ResponseData(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ResponseData(boolean success) {
        this.success = success;
    }

    public ResponseData(Object data, String message, boolean success) {
        this.data = data;
        this.message = message;
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean issuccess() {
        return success;
    }

    public void setsuccess(boolean success) {
        this.success = success;
    }
}
