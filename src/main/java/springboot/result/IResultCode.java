package springboot.result;

import java.io.Serializable;

/**
 * IResultCode
 *
 * @author ljh
 * @since 2021/6/18 16:55
 */
public interface IResultCode extends Serializable {

    String getMsg();

    int getCode();
}
