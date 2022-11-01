package springboot.result;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.Optional;

/**
 * Result
 * SpringBlade 统一返回类 (R)
 *
 * @author ljh
 * created on 2021/6/18 16:28
 */
@Getter
@ToString
@NoArgsConstructor
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 2911133065149289466L;
    private int code;
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

    private Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
        this.success = ResultCode.SUCCESS.code == code;
    }

    private Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.success = ResultCode.SUCCESS.code == code;
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

    public static <T> Result<T> success(String msg) {
        return new Result<>(ResultCode.SUCCESS, msg);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS, data);
    }

    public static <T> Result<T> success(IResultCode resultCode) {
        return new Result<>(resultCode);
    }

    public static <T> Result<T> failure() {
        return new Result<>(ResultCode.FAILURE);
    }

    public static <T> Result<T> failure(int code, String msg) {
        return new Result<>(code, msg);
    }

    public static <T> Result<T> failure(IResultCode resultCode) {
        return new Result<>(resultCode);
    }

    public static <T> Result<T> failure(IResultCode resultCode, String msg) {
        return new Result<>(resultCode, msg);
    }

    public static <T> Result<T> failure(IResultCode resultCode, T data) {
        return new Result<>(resultCode, data);
    }

    public static <T> Result<T> status(boolean flag) {
        return flag ? success() : failure();
    }
}
