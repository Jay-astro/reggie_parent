package com.reggie.handler;

import com.reggie.exception.BaseException;
import com.reggie.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public R<String> exceptionHandler(BaseException ex){
        log.error("捕获到业务异常:"+ ex.getMessage());
        return R.error(ex.getMessage());
    }
}
