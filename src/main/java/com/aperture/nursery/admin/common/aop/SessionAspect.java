package com.aperture.nursery.admin.common.aop;

import com.aperture.nursery.admin.common.context.SessionContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class SessionAspect {
    @Pointcut("execution(* com.aperture.nursery.admin.application..*.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        try {
            long start = System.currentTimeMillis();
            log.info("session start");
            Object o = pjp.proceed();
            long cost = System.currentTimeMillis() - start;
            log.info("session end, cost:{} ms", cost);
            return o;
        } finally {
            SessionContext.exit();
        }
    }
}
