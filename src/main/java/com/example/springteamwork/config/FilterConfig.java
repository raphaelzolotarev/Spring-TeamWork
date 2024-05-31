package com.example.springteamwork.config;

import com.example.springteamwork.filter.CookieFilter;
import com.example.springteamwork.service.NumberOfVisitsService;
import com.example.springteamwork.service.UserService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    private final UserService userService;

    private final NumberOfVisitsService numberOfVisitsService;

    public FilterConfig(UserService userService, NumberOfVisitsService numberOfVisitsService) {
        this.userService = userService;
        this.numberOfVisitsService = numberOfVisitsService;
    }
    @Bean
    public FilterRegistrationBean<CookieFilter> cookieFilter() {
        FilterRegistrationBean<CookieFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CookieFilter(userService, numberOfVisitsService));
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
