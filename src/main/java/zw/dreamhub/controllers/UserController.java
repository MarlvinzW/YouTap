package zw.dreamhub.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zw.dreamhub.domain.responses.User;
import zw.dreamhub.services.UserService;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.util.Optional;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 20/9/2022
 */

@RestController
@Produces("application/json")
@Consumes("application/json")
@RequiredArgsConstructor
public class UserController {

  final UserService userService;

  @GetMapping(value = "getusercontacts")
  public ResponseEntity<User> getUserContacts(@RequestParam(name = "id") Optional<Long> id,
                              @RequestParam(name = "username") Optional<String> username){

    // response
    Optional<User> user = Optional.empty();

    // filter by username & id
    if (id.isPresent() && username.isPresent()) {
      user = userService.findUser(username.get(), id.get());
    }
    // filter by id
    else if(id.isPresent()){
      user = userService.findUser(id.get());
    }
    // filter by username
    else if (username.isPresent()) {
      user = userService.findUser(username.get());
    }

    // response mapping
    return user.map(ResponseEntity::ok).orElse(ResponseEntity.ok(new User(-1)));
  }


}
