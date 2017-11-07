package com.example.christofferwiregren.quiz;

/**
 * Created by christofferwiregren on 2017-11-01.
 */

public class User {


    private String nickname;
    private String id;

    public User() {

        this("", "");

    }


    public User(String id, String nickname) {

        this.nickname = nickname;
        this.id = id;

    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
