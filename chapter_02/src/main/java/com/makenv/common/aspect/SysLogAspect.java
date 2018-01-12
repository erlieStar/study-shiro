package com.makenv.common.aspect;

import com.google.gson.Gson;
import com.makenv.common.annotation.SysLog;
import com.makenv.common.util.HttpContextUtil;
import com.makenv.common.util.IPUtil;
import com.makenv.entity.LogEntity;
import com.makenv.entity.UserEntity;
import com.makenv.service.SysLogService;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class SysLogAspect {

    @Autowired
    SysLogService sysLogService;

    @Pointcut("@annotation(com.makenv.common.annotation.SysLog)")
    public void logPointCut() {}

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行切点的方法
        Object object = point.proceed();
        long time = System.currentTimeMillis() - beginTime;
        LogEntity logEntity = new LogEntity();

        //设置用户名
        //这里之所以getPrincipal获取的是UserEntity对象，是因为MyRealm认证的时候设置进去的是UserEntity
        String username = ((UserEntity)SecurityUtils.getSubject().getPrincipal()).getUsername();
        logEntity.setUsername(username);

        //设置操作时间
        logEntity.setCreateDate(new Date());

        //设置程序运行的时间
        logEntity.setTime(time);

        //设置操作
        MethodSignature methodSignature = (MethodSignature)point.getSignature();
        Method method = methodSignature.getMethod();
        SysLog sysLog = method.getAnnotation(SysLog.class);
        logEntity.setOperation(sysLog.value());

        //设置方法
        String className = point.getTarget().getClass().getName();
        String methodName = methodSignature.getName();
        logEntity.setMethod(className + "." + methodName + "()");

        //设置参数
        Object[] args = point.getArgs();
        String params = new Gson().toJson(args[0]);
        logEntity.setParams(params);

        //设置IP地址
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        logEntity.setIp(IPUtil.getIpAddr(request));

        sysLogService.createLog(logEntity);

        return object;
    }

}