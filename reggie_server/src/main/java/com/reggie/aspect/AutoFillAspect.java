package com.reggie.aspect;

import com.reggie.annotation.AutoFill;
import com.reggie.constant.AutoFillConstant;
import com.reggie.context.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class AutoFillAspect {

    /**
     * 定义切入点
     */
    @Pointcut("execution(* com.reggie.mapper.*.*(..)) && @annotation(com.reggie.annotation.AutoFill)")
    public void autoFillPointCut() {
    }

    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) {
        log.info("开始公共字段自动填充...");

        //方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获得方法上的AutoFill注解
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);
        //获得操作类型
        String type = autoFill.type();
        //获得Mapper方法的参数
        Object[] args = joinPoint.getArgs();

        if (args == null || args.length == 0) {
            return;
        }
        Object entity = args[0];

        LocalDateTime time = LocalDateTime.now();
        Long empId = BaseContext.getCurrentId();

        if (type.equals(AutoFillConstant.INSERT)) {
            try {
                Method setCreateTimeMethod = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setUpdateTimeMethod = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setCreateUserMethod = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                Method setUpdateUserMethod = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                //通过反射为实体的属性赋值
                setCreateTimeMethod.invoke(entity, time);
                setUpdateTimeMethod.invoke(entity, time);
                setCreateUserMethod.invoke(entity, empId);
                setUpdateUserMethod.invoke(entity, empId);
            } catch (Exception e) {
                log.error("公共字段填充失败");
                e.printStackTrace();
            }
        } else {
            try {
                Method setUpdateTimeMethod = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUserMethod = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                //通过反射为实体的属性赋值
                setUpdateTimeMethod.invoke(entity, time);
                setUpdateUserMethod.invoke(entity, empId);
            } catch (Exception e) {
                log.error("公共字段填充失败");
                e.printStackTrace();
            }
        }
    }

}
