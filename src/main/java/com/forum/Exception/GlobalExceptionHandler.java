package com.forum.Exception;

import com.forum.common.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice/*拦截这些异常*/
public class GlobalExceptionHandler {
    /*记录日志*/
    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)/*规定处理那些异常*/
    @ResponseBody
    public Object handleException(Exception e) {
        log.error("Default Exception:", e);
        return ApiResponse.error(ExceptionEnum.SYSTEM_ERROR);
    }

    @ExceptionHandler(ForumException.class)/*规定处理那些异常*/
    @ResponseBody
    public ApiResponse handleemmoeceException(ForumException e) {
        log.error("ForumException Exception: ", e);
        return ApiResponse.error(e.getCode(), e.getMessage());
    }


}
