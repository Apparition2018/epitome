package springboot.result;

import java.io.Serializable;

/**
 * IResultCode
 *
 * @author ljh
 * created on 2021/6/18 16:55
 */
public interface IResultCode extends Serializable {

    String getMsg();

    String getCode();
}
