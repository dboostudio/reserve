package studio.dboo.reserve.infra.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;
/**
 * Created by dboo on 2021/11/04
 * Blog : http://dboostudio.github.io
 * Github : http://github.com/dboostudio
 * Gitlab : http://dboostudio.synology.me:30000
 */
@Aspect
@Component
@Slf4j
public class RestControllerAOP {

    @Pointcut("@annotation(studio.dboo.reserve.infra.annotation.RestLogger)")
    public void restLogger(){}

    @Around("restLogger()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        log.info("========= DBOOLOG LOG [START] : " + joinPoint.getTarget().getClass().getSimpleName()
                + " >> " + method.getName() + " ==========");

        try{
            Object[] args = joinPoint.getArgs();
            if(args.length == 0) {
                log.info("호출 파라미터가 없습니다.");
            } else {
                log.info("호출 파라미터는 다음과 같습니다.");
            }
            for(Object obj : args){
                log.info("PARAMETER NAME : " + obj.getClass().getSimpleName() + ", VALUE : " + obj);
            }

        } catch (NullPointerException npe){
            System.out.println("NPE!");
        }

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = joinPoint.proceed(); //메소드 실행

        stopWatch.stop();

        log.info("해당 응답은 총 " + stopWatch.getTotalTimeSeconds() + "초 걸렸습니다.");
        log.info("결과값 : " + result);
        log.info("========= DBOOLOG LOG [END] : " + joinPoint.getTarget().getClass().getSimpleName()
                + " >> " + method.getName() + " ==========");
    }
}
