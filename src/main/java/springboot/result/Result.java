package springboot.result;

import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.Optional;

/**
 * Result
 *
 * @author ljh
 * created on 2021/6/18 16:28
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;

    private boolean success;

    private String msg;

    private T data;

    private Result(IResultCode resultCode) {
        this(resultCode, resultCode.getMsg(), null);
    }

    private Result(IResultCode resultCode, String msg) {
        this(resultCode, msg, null);
    }

    private Result(IResultCode resultCode, T data) {
        this(resultCode, resultCode.getMsg(), data);
    }

    private Result(IResultCode resultCode, String msg, T data) {
        this(resultCode.getCode(), msg, data);
    }

    private Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.success = ResultCode.SUCCESS.code.equals(code);
    }

    public static boolean isSuccess(@Nullable Result<?> result) {
        return Optional.ofNullable(result).map((x) -> ObjectUtils.nullSafeEquals(ResultCode.SUCCESS.code, x.code)).orElse(Boolean.FALSE);
    }

    public static boolean isFail(@Nullable Result<?> result) {
        return !isSuccess(result);
    }

    public static <T> Result<T> success() {
        return new Result<>(ResultCode.SUCCESS);
    }
    
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS, data);
    }

    public static <T> Result<T> fail() {
        return new Result<>(ResultCode.FAIL);
    }
    
    public static <T> Result<T> fail(IResultCode resultCode) {
        return new Result<>(resultCode);
    }
    
    public static <T> Result<T> status(boolean flag) {
        return flag ? success() : fail();
    }
    
}
