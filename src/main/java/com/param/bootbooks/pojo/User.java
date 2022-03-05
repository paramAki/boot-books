package com.param.bootbooks.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author zhoujingyu（976944083@qq.com）
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int userID;
    private int status;
    private String userEmail;
    private String username;
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(userID, user.userID) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, userEmail, username, password);
    }
}
