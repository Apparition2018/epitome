package l.demo;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * User
 *
 * @author ljh
 * @since 2022/2/9 10:30
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
        String result = (birthDate != null ? "birthDate=" + birthDate : StringUtils.EMPTY) +
                (birth != null ? "birthString='" + birth : StringUtils.EMPTY) + '\'';
        return "User{" + (result.startsWith(StrUtil.COMMA) ? result.substring(2) : result) + "}";
    }
}
