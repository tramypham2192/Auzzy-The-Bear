package com.example.registrationlogindemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/register", "/aboutUs-contactUs/about", "/aboutUs-contactUs/contactUs", "/css/**", "/images/**", "/js/**", "/static/images/upload-dir/**", "/register/save", "/product/test").permitAll()
                        .requestMatchers("/index", "/success", "/login", "/", "/product/productList", "/product/listOfProducts", "/product/displayProductInCard", "/uploadForm", "/cartItemDisplay", "/add-to-cart-item", "/product/productListWithKeywordSearch", "/product/productListWithKeywordSearch/{keyword}/**", "/product/sortProductsFromCheapestToMostExpensive", "/product/sortProductsAccordingToMostOrdered").permitAll()
                        .requestMatchers( "/checkout", "/", "/cartItem", "/add-to-cart-item", "/checkout", "/orderCompleted", "/indexUser").hasRole("USER")
                        .requestMatchers("/adminHomePage", "/product/addProduct", "/product/editProduct", "/product/deleteProduct", "/product", "/delete/{productId}", "/update/{productId}/**", "/update/**", "/update").hasRole("ADMIN")
                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/success", true)
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
