package zw.dreamhub.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import zw.dreamhub.config.env.AppEnv;
import zw.dreamhub.domain.responses.User;
import zw.dreamhub.services.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 20/9/2022
 */

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    final AppEnv env;

    @Override
    public Optional<User> findUser(String username) {
        return this.fetchUser(this.url("username", username)).map(results -> Arrays.stream(results).reduce((first, last) -> first)).get();
    }

    @Override
    public Optional<User> findUser(Long id) {
        return this.fetchUser(this.url("id", String.valueOf(id))).map(results -> Arrays.stream(results).reduce((first, last) -> first)).get();
    }

    @Override
    public Optional<User> findUser(String username, Long id) {
        String url = String.format(
                "%s?username=%s&id=%s"
                , env.getUserService().getUrl(), username, id
        );
        return this.fetchUser(url).map(results -> Arrays.stream(results).reduce((first, last) -> first)).get();
    }

    private String url(String parameter, String value) {
        return String.format("%s?%s=%s", env.getUserService().getUrl(), parameter, value);
    }

    Optional<User[]> fetchUser(String url) {
        // web client init
        WebClient webClient = WebClient.create();

        // fetch user
        Mono<User[]> result = webClient.get()
                .uri(url)
                .header("Content-Type", "application/json")
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(User[].class);
                    } else {
                        return response.createException()
                                .flatMap(Mono::error);
                    }
                });

        // return response
        return Optional.ofNullable(result.block());
    }
}
