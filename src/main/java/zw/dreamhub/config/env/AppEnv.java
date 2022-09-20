package zw.dreamhub.config.env;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 20/9/2022
 */


@ConfigurationProperties(prefix = "app")
@Component
@Data
public class AppEnv {

    private UserServiceDetails userService;

    @Data
    public static class UserServiceDetails {
        private String url;
    }

}
