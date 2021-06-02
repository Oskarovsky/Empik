package com.oskarro.empik.service;

import com.oskarro.empik.gateway.GithubGateway;
import com.oskarro.empik.model.GithubField;
import com.oskarro.empik.model.User;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserServiceBean implements UserService {

    GithubGateway gateway;

    public UserServiceBean(GithubGateway gateway) {
        this.gateway = gateway;
    }

    public User getUser(final String login) {
        Mono<String> userDataFromGithub = gateway.getUserDataFromGithub(login);
        return convertToApi(userDataFromGithub.block());
    }

    private User convertToApi(final String content) {
        final JSONObject obj = new JSONObject(content);
        return User.builder()
                .login(obj.getString(GithubField.USER_LOGIN.key))
                .id(obj.getLong(GithubField.USER_ID.key))
                .avatarUrl(obj.getString(GithubField.USER_AVATAR.key))
                .name(obj.getString(GithubField.USER_NAME.key))
                .type(obj.getString(GithubField.USER_TYPE.key))
                .createdAt(obj.getString(GithubField.CREATED_DATE.key))
                .calculations(calculate(obj.getDouble(GithubField.FOLLOWERS.key), obj.getDouble(GithubField.REPOS.key)))
                .build();
    }

    private Double calculate(final Double numberOfFollowers, final Double numberOfPublicRepos) {
        return numberOfFollowers == 0 ? 0 : (6 / numberOfFollowers * (2+numberOfPublicRepos));
    }

}
