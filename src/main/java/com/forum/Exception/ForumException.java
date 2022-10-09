package com.forum.Exception;

/*自己定义的异常类*/
/*之前的ExceptionEnum只是配合ApiResponse的返回异常，并不属于程序错误时抛出的异常*/
public class ForumException extends RuntimeException{

    private final Integer code;
    private final String message;


    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ForumException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    /*这里的枚举应该不需要导入包*/
    public ForumException(ExceptionEnum exceptionEnum){
        this(exceptionEnum.getCode(),exceptionEnum.getMsg());
    }


}
