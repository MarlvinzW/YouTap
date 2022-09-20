package zw.dreamhub.services;

import zw.dreamhub.domain.responses.User;

import java.util.Optional;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 20/9/2022
 */

public interface UserService {

    Optional<User> findUser(String username);

    Optional<User> findUser(Long id);

    Optional<User> findUser(String username, Long id);

}
