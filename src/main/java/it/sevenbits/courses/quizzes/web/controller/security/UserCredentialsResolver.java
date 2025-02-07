package it.sevenbits.courses.quizzes.web.controller.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Resolves {@link UserCredentials} method arguments
 */
public class UserCredentialsResolver implements HandlerMethodArgumentResolver {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(UserCredentials.class);
    }

    @Override
    public UserCredentials resolveArgument(
            final @NonNull MethodParameter parameter,
            final ModelAndViewContainer mavContainer,
            final @NonNull NativeWebRequest webRequest,
            final WebDataBinderFactory binderFactory
    ) {
        try {
            logger.debug("Getting UserCredentials from request attribute");
            return (UserCredentials) webRequest.getAttribute(
                    JwtAuthInterceptor.USER_CREDENTIALS, RequestAttributes.SCOPE_REQUEST
            );
        } catch (Exception e) {
            return null;
        }
    }

}
