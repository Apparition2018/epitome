package springboot.resolver.argument;

import l.demo.Demo;
import l.demo.Person;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * ArgumentResolver 参数解析器
 *
 * @author ljh
 * @since 2021/8/11 15:02
 */
public class PersonArgumentResolver extends Demo implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Person.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(@NonNull MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String id = webRequest.getHeader("id");
        if (StringUtils.isNotBlank(id)) {
            try {
                return personList.get(Integer.parseInt(id) - 1);
            } catch (IndexOutOfBoundsException e) {
                return null;
            }
        }
        return null;
    }
}
