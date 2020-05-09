package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig  {


    /**
     * 管理者用のセキュリティー
     */
    @Configuration
    @Order(1)
    public static class AdminSecurity extends WebSecurityConfigurerAdapter {


        @Autowired
        @Qualifier("ADMIN")
        AdminDetailsServiceImpl adminDetailsService;


        /**
         * 静的ファイルに認証はかけない.
         * @param web
         * @throws Exception
         */
        @Override
        public void configure(WebSecurity web) throws Exception{
            web.ignoring().antMatchers( "/css/**", "/js/**", "/images/**");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception{
            http.antMatcher("/admin/**")
                    .authorizeRequests()
                    .antMatchers("/admin/adminLogin","/admin/adminRegisterPage","/admin/registerAdmin").permitAll()
                    .antMatchers("/admin/**")
                    .hasAuthority("ADMIN")
                    .anyRequest()
                    .authenticated()
                    .and();

            http
                    .formLogin()
                    .loginProcessingUrl("/admin/login")//ログイン処理をするURL
                    .loginPage("/admin/adminLogin")//ログイン画面のURL
                    .failureUrl("/admin/loginPage?error")//ログイン失敗時
                    .defaultSuccessUrl("/admin/adminTop",true)//認証成功時のURL
                    .usernameParameter("email")//ユーザーのパラメーター名
                    .passwordParameter("password")
                    .and();

            http
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout"))
                    .logoutSuccessUrl("/admin/loginPage");
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder ath) throws Exception{
            ath.userDetailsService(this.adminDetailsService)
                    .passwordEncoder(new BCryptPasswordEncoder());
        }
    }
}