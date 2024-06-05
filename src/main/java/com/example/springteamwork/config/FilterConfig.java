package com.example.springteamwork.config;

import com.example.springteamwork.filter.CookieFilter;
import com.example.springteamwork.model.NumberOfVisits;
import com.example.springteamwork.repository.FavoriteRepository;
import com.example.springteamwork.repository.NumberOfVisitsRepository;
import com.example.springteamwork.service.FavoriteService;
import com.example.springteamwork.service.NumberOfVisitsService;
import com.example.springteamwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class FilterConfig {
    private final UserService userService;
    private final FavoriteService favoriteService;
    private final NumberOfVisitsService numberOfVisitsService;
    private NumberOfVisitsRepository numberOfVisitsRepository;

    @Autowired
    public FilterConfig(UserService userService, FavoriteService favoriteService, NumberOfVisitsService numberOfVisitsService, NumberOfVisitsRepository numberOfVisitsRepository) {
        this.userService = userService;
        this.favoriteService = favoriteService;
        this.numberOfVisitsService = numberOfVisitsService;
        this.numberOfVisitsRepository = numberOfVisitsRepository;
    }

    @Bean
    public FilterRegistrationBean<CookieFilter> cookieFilter() {
        List<NumberOfVisits> visits = numberOfVisitsRepository.findAll();
        if (visits.isEmpty()) {
            NumberOfVisits initialVisit = new NumberOfVisits((byte)1, 0);
            numberOfVisitsRepository.save(initialVisit);
        }

        FilterRegistrationBean<CookieFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CookieFilter(userService, numberOfVisitsService, favoriteService));
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

}
