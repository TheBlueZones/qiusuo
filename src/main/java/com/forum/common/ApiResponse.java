package com.forum.common;

import com.forum.Exception.ExceptionEnum;

/**
 * 接口返回给前端的数据
 */

public class ApiResponse<T> {/*genericity*/
    private Integer status;

    private String msg;

    private T data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }


    private static final int Ok_CODE = 10000;

    private static final String OK_MESSAGE = "SUCCESS";

    public ApiResponse() {
        this(Ok_CODE, OK_MESSAGE);
    }

    public ApiResponse(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ApiResponse(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>();
    }

    public static <T> ApiResponse<T> success(T result) {
        ApiResponse<T> response = new ApiResponse<>();
        /*会有默认构造函数给其他两个值赋值*/
        response.setData(result);
        return response;
    }
    public static <T> ApiResponse<T> success(Integer code, String msg) {
        return new ApiResponse<>(code, msg);
    }


    public static <T> ApiResponse<T> error(ExceptionEnum ex) {
        return new ApiResponse<>(ex.getCode(), ex.getMsg());
    }

    public static <T> ApiResponse<T> error(Integer code, String msg) {
        return new ApiResponse<>(code, msg);
    }


}
