package com.cloud.base;

import com.cloud.entity.Blog;
import com.cloud.entity.UserBase;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class Session {



    public static HttpSession getSession() {
        HttpSession session;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        session = request.getSession();
        return session;
    }

    public static UserBase getCurrentUser() {
        return (UserBase) Session.getSession().getAttribute("user");
    }

    public static String getCurrentUserVerificationNumber() {
        return (String) Session.getSession().getAttribute("verificationNumber");
    }

    public static String getCurrentUserMail() {
        return (String) Session.getSession().getAttribute("mail");
    }

    public static String getCurrentUserName() {
        return (String) Session.getSession().getAttribute("name");
    }

    public static Byte getCurrentUserAuthority() {
        return (Byte) Session.getSession().getAttribute("authority");
    }

    public static void setUserInfo(UserBase user) {
        HttpSession session = getSession();
        session.setAttribute("id", user.getId());
        session.setAttribute("name", user.getName());
        session.setAttribute("mail", user.getMail());
        session.setAttribute("authority", user.getAuthority());
    }

    public static void setUser(UserBase user) {
        HttpSession session = getSession();
        session.setAttribute("user", user);
    }
    public static void setML(Boolean isML) {
        HttpSession session = getSession();
        session.setAttribute("isML", isML);
    }
    public static Boolean getML() {
        HttpSession session = getSession();
        return (Boolean) session.getAttribute("isML");
    }

    public static String getMail() {
        HttpSession session = getSession();
        return (String) session.getAttribute("mail");
    }

    public static void setMail(String mail) {
        HttpSession session = getSession();
        session.setAttribute("mail", mail);
    }

    public static void removeUser() {
        HttpSession session = getSession();
        session.removeAttribute("user");
        session.removeAttribute("isML");
    }

    public static void removeUserInfo() {
        HttpSession session = getSession();
        session.removeAttribute("id");
        session.removeAttribute("name");
        session.removeAttribute("mail");
        session.removeAttribute("authority");
    }

    public static void setRecommendation(List<Long> list){
        HttpSession session = getSession();
        session.setAttribute("recommendation",list);
        session.setAttribute("recommendationSize",list.size());

    }
    public static List<Long> getRecommendation(){
        HttpSession session = getSession();
        return (List<Long>) session.getAttribute("recommendation");
    }

    public static void removeRecommendation(){
        HttpSession session = getSession();
        session.removeAttribute("recommendation");
    }
    public static Integer getRecommendationSize(){
        HttpSession session = getSession();
        return (Integer) session.getAttribute("recommendationSize");
    }


    public static void setNextIndex(Integer nextIndex){

        getSession().setAttribute("nextIndex",nextIndex);
    }
    public static Integer getNextIndex(){
        return (Integer) getSession().getAttribute("nextIndex");
    }
}
