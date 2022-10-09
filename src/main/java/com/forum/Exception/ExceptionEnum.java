package com.forum.Exception;

/*返回所有的异常的具体异常枚举，配合 public static <T> ApiResponse<T> error存在*/
public enum ExceptionEnum {

    USERNAME_NOT_NULL(10001, "还未填写昵称"),
    PASSWORD_NOT_NULL(10002, "还未填写密码"),
    PASSWORD_TOO_SHORT(10003, "密码长度不能小于八位"),
    DUPLICATE_NAME(10004, "该昵称已经被注册"),

    USER_ERROR(10005, "昵称错误"),
    PASSWORD_WRONG(10006, "密码错误"),
    INSERT_FAILED(10005, "注册失败"),
    SYSTEM_ERROR(20000, "系统异常");
    Integer code;
    String msg;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /*把这个默认构造函数写错了*/
    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
