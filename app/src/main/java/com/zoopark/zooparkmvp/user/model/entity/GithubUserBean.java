package com.zoopark.zooparkmvp.user.model.entity;

import com.google.gson.annotations.SerializedName;

public class GithubUserBean {

    @SerializedName("avatar_url")
    private String avatarUrl;
    private String login;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
