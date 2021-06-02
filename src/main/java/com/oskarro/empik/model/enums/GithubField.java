package com.oskarro.empik.model.enums;

public enum GithubField {

    USER_LOGIN("login"),
    USER_ID("id"),
    USER_AVATAR("avatar_url"),
    USER_NAME("name"),
    USER_TYPE("type"),
    CREATED_DATE("created_at"),
    FOLLOWERS("followers"),
    REPOS("public_repos");

    public final String key;

    GithubField(String key) {
        this.key = key;
    }
}
