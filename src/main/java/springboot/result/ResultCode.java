package springboot.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * ResultCode
 *
 * @author ljh
 * created on 2021/6/18 16:39
 */
@Getter
@AllArgsConstructor
@ToString
public enum ResultCode implements IResultCode {

    SUCCESS("00000", "操作成功"),
    FAIL("00001", "操作失败"),
    USER_ERROR("A0001", "用户端错误"),
    SYSTEM_ERROR("B0001", "系统错误"),
    THIRD_ERROR("C0001", "第三方服务错误");

    final String code;
    final String msg;

}
