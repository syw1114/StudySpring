package com.example.demo.aop;

import com.example.demo.exception.CustomValidationException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// 이 클래스가 Aspect를 나타내는 클래스라는 것을 명시
@Aspect
//스프링 빈으로 등록하기 위해 컨포넌트.
@Component

public class ValidationAop {
    //Pointcut -> 이 시점에 사용하겠다.
    //추가적으로 demo 패키지 아래 Api 객체의 모든 메서드에 이 pointcut을 적용시키겠다.
    @Pointcut("@annotation(com.example.demo.aop.annotation.ValidAspect)")
    private void annotationPointCut() {}

    //타겟 메서드를 감싸서 특정 Advice를 실행한다는 의미
    //어드바이스가 타겟 메소드를 감싸서 타겟 메소드 호출전과 후에 어드바이스 기능을 수행
    //어라운드가 적용될 포인트컷 명시.
    @Around("annotationPointCut()")

    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        //메서드 호출 자체를 감쌈.
        //파라미더 목록을 구현.
        Object[] args = joinPoint.getArgs();
        BeanPropertyBindingResult bindingResult = null;
        for (Object arg : args) {
            if (arg.getClass() == BeanPropertyBindingResult.class) {
                bindingResult = (BeanPropertyBindingResult) arg;
                break;
            }
        }
       // 에러가 있으면 if 실행
        if(bindingResult.hasErrors()){

            //에러맵을 만들어냄
            Map<String, String> errorMap = new HashMap<String,String>();

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            for(FieldError fieldError : fieldErrors) {
                errorMap.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            //에러가있으니 예외 처리를 할 것이다.
            //생성자 , 예외를 생성하면서 강제로 예외를 발생시킴.
            throw  new CustomValidationException("Validation Error", errorMap);
        }


        Object result = null;
        result = joinPoint.proceed();

        return result;
    }

}
