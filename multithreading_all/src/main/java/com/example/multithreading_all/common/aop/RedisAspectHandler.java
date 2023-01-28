package com.example.multithreading_all.common.aop;

import com.example.multithreading_all.common.enums.RedisLockTypeEnum;
import com.example.multithreading_all.common.lock.RedisLockDefinitionHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * module: 应用模块名称<br/>
 * <p>
 * description: 描述<br/>
 *
 * @author XiaoDong.Yang
 * @date: 2023/1/17 15:09
 */

@Aspect
@Component
@Slf4j
public class RedisAspectHandler {

    @Autowired
    private RedisTemplate redisTemplate;

    private static ConcurrentLinkedQueue<RedisLockDefinitionHolder> holderList = new ConcurrentLinkedQueue();


    @Pointcut("@annotation(com.example.multithreading_all.common.aop.RedisLockAnnotation)")
    public void redisLockPC() {
    }

    @Around(value = "redisLockPC()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        // 解析参数
        Method method = resolveMethod(pjp);
        RedisLockAnnotation annotation = method.getAnnotation(RedisLockAnnotation.class);
        RedisLockTypeEnum typeEnum = annotation.typeEnum();
        Object[] params = pjp.getArgs();
        String ukString = params[annotation.lockFiled()].toString();
        // 省略很多参数校验和判空
        String businessKey = typeEnum.getUniqueKey(ukString);
        String uniqueValue = UUID.randomUUID().toString();
        // 加锁
        Object result = null;
        try {
            boolean isSuccess = redisTemplate.opsForValue().setIfAbsent(businessKey, uniqueValue);
            if (!isSuccess) {
                throw new Exception("You can't do it，because another has get the lock =-=");
            }
            redisTemplate.expire(businessKey, annotation.lockTime(), TimeUnit.SECONDS);
            Thread currentThread = Thread.currentThread();
            // 将本次 Task 信息加入「延时」队列中
            holderList.add(new RedisLockDefinitionHolder(businessKey, annotation.lockTime(), System.currentTimeMillis(),
                    currentThread, annotation.tryCount()));
            // 执行业务操作
            result = pjp.proceed();
            // 线程被中断，抛出异常，中断此次请求
            if (currentThread.isInterrupted()) {
                throw new InterruptedException("You had been interrupted =-=");
            }
        } catch (InterruptedException e ) {
            log.error("Interrupt exception, rollback transaction", e);
            throw new Exception("Interrupt exception, please send request again");
        } catch (Exception e) {
            log.error("has some error, please check again", e);
        } finally {
            // 请求结束后，强制删掉 key，释放锁
            redisTemplate.delete(businessKey);
            log.info("release the lock, businessKey is [" + businessKey + "]");
        }
        return result;
    }

    public static Method resolveMethod(ProceedingJoinPoint pjp) {
        Method method = null;
        try {
            MethodSignature signature = (MethodSignature) pjp.getSignature();
            method = signature.getMethod();
        } catch (Exception e) {
            log.info("aop transform exception ->",e);
        }
        return method;
    }

}
