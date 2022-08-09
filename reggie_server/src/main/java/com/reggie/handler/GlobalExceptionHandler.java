package com.reggie.handler;

import com.reggie.constant.MessageConstant;
import com.reggie.exception.BaseException;
import com.reggie.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

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

    @ExceptionHandler
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException e){
        log.error("捕获到业务异常:"+ e.getMessage());
        if (e.getMessage().contains("Duplicate entry")){
            String name = e.getMessage().split(" ")[2];
            return R.error(name + "已存在");
        }
        return R.error(MessageConstant.UNKNOWN_ERROR);
    }
}
