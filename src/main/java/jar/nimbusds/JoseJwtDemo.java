package jar.nimbusds;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import l.demo.Demo;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.util.AlternativeJdkIdGenerator;
import org.springframework.util.DigestUtils;
import springboot.exception.ServiceException;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * JoseJwt
 * <pre>
 * <a href="https://www.rfc-editor.org/rfc/rfc7519">JSON Web Token</a> (JWT) 是一种紧凑的声明表示格式，适用于空间受限的环境，如 HTTP 授权报头和 URI 查询参数。
 * JWT 编码声明以 JSON [RFC7159] 对象的形式传输，该对象被用作 JSON Web Signature (JWS) 结构的有效负载，或作为 SON Web Encryption (JWE) 结构的明文，使声明能够被数字签名或使用消息验证码 (MAC) 保护完整性，或加密。
 * </pre>
 * JWT 结构：
 * <pre>
 * 1 Header：①typ:JWT，②alg:算法名称
 * 2 Payload：
 *   2.1 <a href="https://www.rfc-editor.org/rfc/rfc7519#section-4.1">Registered Claim Names</a>：不强制而是建议使用
 *       iss (Issuer), sub(Subject), aud(Audience), exp (Expiration Time), nbf (Not Before), iat (Issued At), jti (JWT ID)
 *   2.2 <a href="https://www.rfc-editor.org/rfc/rfc7519#section-4.2">Public Claim Names</a>：使用者自定义
 *       为了防止冲突，Claim Name 应该在 <a href="https://www.iana.org/assignments/jwt/jwt.xhtml">IANA JSON Web Token Registry</a>
 *   2.3 Private Claim Names：使用者自定义
 * 3 Signature：HmacSHA256(base64UrlEncode(header) + "." + base64UrlEncode(payload), secret)
 * </pre>
 * 参考：
 * <pre>
 * <a href="https://jwt.io/introduction">JSON Web Token Introduction</a>
 * <a href="https://zhuanlan.zhihu.com/p/86937325">JWT 介绍</a>
 * <a href="https://learnku.com/articles/17883">JWT 超详细分析</a>
 * <a href="https://www.zhihu.com/question/274566992">JWT 与 Token+Redis，哪种方案更好用？</a>
 * <a href="https://mp.weixin.qq.com/s/Jo3PZoa7nL99c8UCxPiTTA">nimbus-jose-jwt 使用介绍</a>
 * </pre>
 *
 * @author ljh
 * @since 2021/8/27 8:59
 */
public class JoseJwtDemo extends Demo {

    private static final String SECRET = DigestUtils.md5DigestAsHex("test".getBytes(StandardCharsets.UTF_8));

    public static void main(String[] args) throws JsonProcessingException, JOSEException, ParseException {
        PayloadDto payloadDto = new PayloadDto()
                .setSub(MY_NAME).setIat(System.currentTimeMillis())
                .setExp(DateUtils.addHours(new Date(), 6).getTime())
                .setJti(new AlternativeJdkIdGenerator().generateId().toString())
                .setUsername(MY_NAME)
                .setAuthorities(Collections.singletonList("ADMIN"));

        String token = generateTokenByHMAC(jsonMapper.writeValueAsString(payloadDto), SECRET);
        p("token = " + token);
        p(verifyTokenByHMAC(token, SECRET));
    }

    /**
     * 生成 Token
     */
    private static String generateTokenByHMAC(String payloadStr, String secret) throws JOSEException {
        // 创建 JWSHeader，设置签名算法和类型
        JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.HS256).type(JOSEObjectType.JWT).build();
        // 创建 Payload
        Payload payload = new Payload(payloadStr);
        // 创建 JWSObject
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        // 创建 HMAC 签名器
        JWSSigner jwsSigner = new MACSigner(secret);
        // 签名
        jwsObject.sign(jwsSigner);
        return jwsObject.serialize();
    }

    /**
     * 验证 Token
     */
    private static PayloadDto verifyTokenByHMAC(String token, String secret) throws ParseException, JOSEException, JsonProcessingException {
        // 将 token 转换成 JWSObject
        JWSObject jwsObject = JWSObject.parse(token);
        // 创建 HMAC 验证器
        JWSVerifier jwsVerifier = new MACVerifier(secret);
        if (!jwsObject.verify(jwsVerifier)) {
            throw new ServiceException("token 签名不合法！");
        }
        String payloadStr = jwsObject.getPayload().toString();
        PayloadDto payloadDto = jsonMapper.readValue(payloadStr, PayloadDto.class);
        if (payloadDto.getExp() < System.currentTimeMillis()) {
            throw new ServiceException("token 已过期！");
        }
        return payloadDto;
    }

    @Data
    @Accessors(chain = true)
    private static class PayloadDto {
        private String sub;
        private Long iat;
        private Long exp;
        private String jti;
        private String username;
        private List<String> authorities;
    }
}
