package com.timeOrganizer.model;

public class ActualSessionInfo {
    private static ActualSessionInfo ourInstance = new ActualSessionInfo();

    public static ActualSessionInfo getInstance() {
        return ourInstance;
    }

    private String ActualUserEmail;

    public String getActualUserEmail() {
        return ActualUserEmail;
    }

    public void setActualUserEmail(String actualUserEmail) {
        ActualUserEmail = actualUserEmail;
    }

    private ActualSessionInfo() {
    }
}
