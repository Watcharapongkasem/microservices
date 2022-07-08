package auth.authdemo.vm;

import auth.authdemo.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Userviewmodel {
    
    private String userId;
    private String userName;
    private String password;
    private String token;

    public Userviewmodel(){}

    public Userviewmodel(User entity){
        this.userId = entity.getUserId();
        this.userName = entity.getUserName();
    }

    public User toEntity(){
        User entity =  new User();
        entity.setUserId(this.userId);
        entity.setUserName(this.userName);
        return entity;
    }

    public Userviewmodel(User entity,String token){
        this.userId = entity.getUserId();
        this.userName = entity.getUserName();
        this.token = token;
    }
    
}
