//package com.cloud.aspect;
//
//
//import com.cloud.base.RestResponse;
//import com.cloud.base.Session;
//import com.cloud.entity.UserBase;
//import com.cloud.util.AuthenticateUtils;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.springframework.stereotype.Component;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//
//import javax.servlet.http.HttpSession;
//
//@Aspect
//@Component
//public class AuthAspect {
//
//
//    @Around("@annotation(com.cloud.notation.LowPermission)")
//    public Object checkPermission(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        HttpSession session = Session.getSession();
//        Byte rank = ((UserBase) session.getAttribute("user")).getAuthority();
//        boolean auth = true;
//        if(rank != null && rank >= 1){
//            auth =  false;
//        }else {
//            auth =  true;
//        }
//        if(auth){
//            return RestResponse.error("no authentication");
//        }
//        return proceedingJoinPoint.proceed();
//    }
//
//    @Around("@annotation(com.cloud.notation.MidPermission)")
//    public Object checkProPermission(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        HttpSession session = Session.getSession();
//        Byte rank = ((UserBase) session.getAttribute("user")).getAuthority();
//        boolean auth = true;
//        if(rank != null && rank >= 2){
//            auth =  false;
//        }else {
//            auth =  true;
//        }
//        if(auth){
//            return RestResponse.error("no authentication");
//        }
//        return proceedingJoinPoint.proceed();
//    }
//
//
//    @Around("@annotation(com.cloud.notation.HighPermission)")
//    public Object checkManagerPermission(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        HttpSession session = Session.getSession();
//        Byte rank = ((UserBase) session.getAttribute("user")).getAuthority();
//        boolean auth = true;
//        if(rank != null && rank >= 3){
//            auth = false;
//        }else {
//            if (null == rank) {
//                auth = true;
//            }
//            auth = true;
//        }
//        if(auth){
//            return RestResponse.error("no authentication");
//        }
//        return proceedingJoinPoint.proceed();
//    }
//}