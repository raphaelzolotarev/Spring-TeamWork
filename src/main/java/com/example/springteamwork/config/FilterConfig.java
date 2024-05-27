package com.example.springteamwork.config;

import com.example.springteamwork.filter.CookieFilter;
import com.example.springteamwork.service.UserService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    private final UserService userService;

    public FilterConfig(UserService userService) {
        this.userService = userService;
    }
    @Bean
    public FilterRegistrationBean<CookieFilter> cookieFilter() {
        FilterRegistrationBean<CookieFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CookieFilter(userService));
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}

