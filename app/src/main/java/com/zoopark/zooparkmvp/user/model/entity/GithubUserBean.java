package com.zoopark.zooparkmvp.user.model.entity;

import com.google.gson.annotations.SerializedName;

public class GithubUserBean {

    @SerializedName("avatar_url")
    private String avatarUrl;
    private String login;
    @SerializedName("html_url")
    private String htmlUrl;

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

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }
}
