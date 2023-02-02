package springboot.exception;

import lombok.Getter;
import springboot.result.IResultCode;
import springboot.result.ResultCode;

import java.io.Serial;

/**
 * SpringBlade 自定义异常 (ServiceException)
 *
 * @author ljh
 * @since 2021/6/21 10:26
 */
@Getter
public class ServiceException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -1069434601087045461L;
    private final IResultCode resultCode;

    public ServiceException(String msg) {
        super(msg);
        this.resultCode = ResultCode.FAILURE;
    }

    public ServiceException(IResultCode resultCode) {
        super(resultCode.getMsg());
        this.resultCode = resultCode;
    }

    public ServiceException(IResultCode resultCode, Throwable cause) {
        super(cause);
        this.resultCode = resultCode;
    }

    public Throwable fillInStackTrace() {
        return this;
    }

    public Throwable doFillInStackTrace() {
        return super.fillInStackTrace();
    }
}
