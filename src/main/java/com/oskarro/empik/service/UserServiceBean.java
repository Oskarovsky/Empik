package com.oskarro.empik.service;

import com.oskarro.empik.gateway.GithubGateway;
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
                .login(obj.getString("login"))
                .id(obj.getLong("id"))
                .avatarUrl(obj.getString("avatar_url"))
                .name(obj.getString("name"))
                .type(obj.getString("type"))
                .createdAt(obj.getString("created_at"))
                .calculations(calculate(obj.getDouble("followers"), obj.getDouble("public_repos")))
                .build();
    }

    private Double calculate(final Double numberOfFollowers, final Double numberOfPublicRepos) {
        return numberOfFollowers == 0 ? 0 : (6 / numberOfFollowers * (2+numberOfPublicRepos));
    }

}
