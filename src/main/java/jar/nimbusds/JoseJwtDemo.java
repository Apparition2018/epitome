package jar.nimbusds;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import l.demo.Demo;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
 * JWT (JSON Web Token) 是一种开放标准 (RFC 7519)，它定义了一种紧凑且自包含的方式，用于在各方之间作为 JSON 对象安全地传输信息
 * <p>
 * 实现方式:
 * 1.JWS：JSON Web Signature（常用）
 * 2.JWE：JSON Web Encryption
 * <p>
 * JWT 结构：
 * 1.Header：令牌类型（JWT），正使用的签名算法（HmacSHA256/RSA/...）
 * 2.Payload：
 * -    2.1.Registered claims：不强制而是建议使用
 * -        iss (issuer), sub(subject), aud(audience), exp (expiration time), nbf (Not Before), iat (Issued At), jti (JWT ID)
 * -        https://tools.ietf.org/html/rfc7519#section-4.1
 * -    2.2.Public claims：使用者自定义；为了 avoid collisions 应在以下中定义
 * -        IANA JSON Web Token Registry：https://www.iana.org/assignments/jwt/jwt.xhtml
 * -    2.3.Private claims：使用者自定义；不需要 avoid collisions
 * 3.Signature：HmacSHA256(base64UrlEncode(header) + "." + base64UrlEncode(payload), secret)
 * <p>
 * JSON Web Token Introduction：https://jwt.io/introduction
 * JWT 介绍：https://zhuanlan.zhihu.com/p/86937325
 * JWT 超详细分析：https://learnku.com/articles/17883
 * JWT 与 Token+Redis，哪种方案更好用？：https://www.zhihu.com/question/274566992
 * nimbus-jose-jwt 使用介绍：https://mp.weixin.qq.com/s/Jo3PZoa7nL99c8UCxPiTTA
 *
 * @author ljh
 * created on 2021/8/27 8:59
 */
public class JoseJwtDemo extends Demo {

    private String secret;

    @BeforeEach
    public void init() {
        secret = DigestUtils.md5DigestAsHex("test".getBytes(StandardCharsets.UTF_8));
    }

    @Test
    public void testJoseJwt() throws JsonProcessingException, JOSEException, ParseException {
        PayloadDto payloadDto = new PayloadDto()
                .setSub("ljh").setIat(new Date().getTime())
                .setExp(DateUtils.addDays(new Date(), 7).getTime())
                .setJti(new AlternativeJdkIdGenerator().generateId().toString())
                .setUsername("ljh")
                .setAuthorities(Collections.singletonList("ADMIN"));

        String token = generateTokenByHMAC(jsonMapper.writeValueAsString(payloadDto), secret);
        p(verifyTokenByHMAC(token, secret));
    }

    /**
     * 生成 Token
     */
    private String generateTokenByHMAC(String payloadStr, String secret) throws JOSEException {
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
    private PayloadDto verifyTokenByHMAC(String token, String secret) throws ParseException, JOSEException, JsonProcessingException {
        // 将 token 转换成 JWSObject
        JWSObject jwsObject = JWSObject.parse(token);
        // 创建 HMAC 验证器
        JWSVerifier jwsVerifier = new MACVerifier(secret);
        if (!jwsObject.verify(jwsVerifier)) {
            throw new ServiceException("token 签名不合法！");
        }
        String payloadStr = jwsObject.getPayload().toString();
        PayloadDto payloadDto = jsonMapper.readValue(payloadStr, PayloadDto.class);
        if (payloadDto.getExp() < new Date().getTime()) {
            throw new ServiceException("token 已过期！");
        }
        return payloadDto;
    }

    @Data
    @Accessors(chain = true)
    static class PayloadDto {
        private String sub;
        private Long iat;
        private Long exp;
        private String jti;
        private String username;
        private List<String> authorities;
    }
}
