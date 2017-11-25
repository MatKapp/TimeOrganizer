package com.timeOrganizer.model;

import java.util.ArrayList;

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

    public ArrayList<String> friendsEmailList = new ArrayList<String>();

    public ArrayList<String> getFriendsEmailList() {
        return friendsEmailList;
    }

    public void setFriendsEmailList(ArrayList<String> friendsEmailList) {
        this.friendsEmailList = friendsEmailList;
    }

    private ActualSessionInfo() {
    }
}
