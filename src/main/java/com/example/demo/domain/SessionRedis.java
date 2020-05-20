//package com.example.demo.domain;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.annotation.SessionScope;
//
//import java.io.Serializable;
//
//
//@SessionScope
//@Component
//public class SessionRedis implements Serializable {
//
//    private static final long serialVersionUID = 8048097948251750715L;
//    private String name;
//    private String sessionId;
//
//    public SessionRedis(String name, String sessionId) {
//        this.name = name;
//        this.sessionId = sessionId;
//    }
//
//    public SessionRedis() {
//    }
//
//    @Override
//    public String toString() {
//        return "SessionRedis{" +
//                "name='" + name + '\'' +
//                ", sessionId='" + sessionId + '\'' +
//                '}';
//    }
//
//    public static long getSerialVersionUID() {
//        return serialVersionUID;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getSessionId() {
//        return sessionId;
//    }
//
//    public void setSessionId(String sessionId) {
//        this.sessionId = sessionId;
//    }
//}
