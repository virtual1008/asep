package com.asep.common.response;

public class ApiResponse<T> {
    private boolean success;
    private T data;
    private String message;

    public ApiResponse(boolean success , T data , String message){
        this.success = success;
        this.data = data;
        this.message = message;
    }
    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(true, data, message);
    }

    public static <T> ApiResponse<T> failure(String message) {
        return new ApiResponse<>(false, null, message);
    }
}
