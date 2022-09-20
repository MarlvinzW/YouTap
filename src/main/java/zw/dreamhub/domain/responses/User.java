package zw.dreamhub.domain.responses;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 20/9/2022
 */


@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class User {
    private int id;
    private String email;
    private String phone;

    public User(int id){
        this.id = id;
    }

}
