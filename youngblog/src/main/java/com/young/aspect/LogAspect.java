package com.young.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author young
 * @Description 日志文件处理
 * @date 2020-05-01 15:01
 */
@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    //拦截controller下的所有方法
    @Pointcut("execution(* com.young.controller.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        //获取request
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        StringBuffer url = request.getRequestURL();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
        logger.info("RequestLog" + requestLog);
    }

    @After("log()")
    public void doAfter() {
//        logger.info("------doAfter------");
    }

    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturn(Object result) {
        logger.info("result" + result);
    }

    private class RequestLog {

        private StringBuffer url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog() {
        }

        public RequestLog(StringBuffer url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return
                    "url='" + url + '\'' +
                            ", ip='" + ip + '\'' +
                            ", classMethod='" + classMethod + '\'' +
                            ", args=" + Arrays.toString(args) +
                            '}';
        }
    }
}
