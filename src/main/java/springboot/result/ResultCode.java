package springboot.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ResultCode
 * <p>阿里错误码：
 * <pre>
 * 1.错误码的制定原则：快速溯源、沟通标准化
 *   1.1 错误码必须能够快速知晓错误来源，可快速判断是谁的问题
 *   1.2 错误码必须能够进行清晰地比对（代码中容易 equals）
 *   1.3 错误码有利于团队快速对错误原因达到一致认知
 * 2.错误码不体现版本号和错误等级信息
 *   2.1 错误码以不断追加的方式进行兼容
 *   2.2 错误等级由日志和错误码本身的释义来决定
 * 3.错误码为字符串类型，共 5 位，分成两个部分：错误产生来源+四位数字编号
 *   来源分为 A/B/C，A 来源于用户，B 来源于当前系统，C 来源于第三方服务
 * 4.编号不与公司业务架构，更不与组织架构挂钩，以先到先得的原则在统一平台上进行，审批生效，编号即被永久固定
 * 5.在获取第三方服务错误码时，向上抛出允许本系统转义，由 C 转为 B，并且在错误信息上带上原有的第三方错误码
 * 6.错误码分为一级宏观错误码、二级宏观错误码、三级宏观错误码
 *   调用第三方服务出错是一级，中间件错误是二级，消息服务出错是三级
 * </pre>
 *
 * @author ljh
 * @since 2021/6/18 16:39
 */
@Getter
@AllArgsConstructor
public enum ResultCode implements IResultCode {

    SUCCESS(200, "操作成功"),
    FAILURE(400, "业务异常"),
    UN_AUTHORIZED(401, "请求未授权"),
    NOT_FOUND(404, "404 没找到请求"),
    MSG_NOT_READABLE(400, "消息不能读取"),
    METHOD_NOT_SUPPORTED(405, "不支持当前请求方法"),
    MEDIA_TYPE_NOT_SUPPORTED(415, "不支持当前媒体类型"),
    REQ_REJECT(403, "请求被拒绝"),
    INTERNAL_SERVER_ERROR(500, "服务器异常"),
    PARAM_MISS(400, "缺少必要的请求参数"),
    PARAM_TYPE_ERROR(400, "请求参数类型错误"),
    PARAM_BIND_ERROR(400, "请求参数绑定错误"),
    PARAM_VALID_ERROR(400, "参数校验失败");

    final int code;
    final String msg;
}
