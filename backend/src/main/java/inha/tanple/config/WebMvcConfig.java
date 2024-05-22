package inha.tanple.config;

import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggingFilter());
    }

    private static class LoggingFilter extends CommonsRequestLoggingFilter implements HandlerInterceptor {
        public LoggingFilter() {
            setIncludeClientInfo(true);
            setIncludeQueryString(true);
            setIncludePayload(true);
            setMaxPayloadLength(10000);
        }
    }
}
