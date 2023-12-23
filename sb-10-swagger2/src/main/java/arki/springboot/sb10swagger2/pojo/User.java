package arki.springboot.sb10swagger2.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("用户实体")
public class User {
    @ApiModelProperty("用户名")
    private String id;
    @ApiModelProperty("密码")
    private int passcode;

    public User(String id, int passcode) {
        this.id = id;
        this.passcode = passcode;
    }
}
