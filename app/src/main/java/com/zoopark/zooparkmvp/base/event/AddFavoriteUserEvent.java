package com.zoopark.zooparkmvp.base.event;

public class AddFavoriteUserEvent {

    private String userName;
    private String avatar;

    public AddFavoriteUserEvent(String userName, String avatar) {
        this.userName = userName;
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public String getAvatar() {
        return avatar;
    }
}
