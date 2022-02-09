package l.demo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * User
 *
 * @author ljh
 * created on 2022/2/9 10:30
 */
@Data
@NoArgsConstructor
public class User {
    private Date birthDate;
    private String birth;

    public User(Date birthDate) {
        this.birthDate = birthDate;
    }

    public User(String birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        String result = (birthDate != null ? "birthDate=" + birthDate : "") +
                (birth != null ? "birthString='" + birth : "") + '\'';
        return "User{" + (result.startsWith(",") ? result.substring(2) : result) + "}";
    }

}
