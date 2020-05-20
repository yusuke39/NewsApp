package com.example.demo.security;

import com.example.demo.domain.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

public class SecurityConfig  {


    /**
     * 管理者用のセキュリティー
     */
    @EnableWebSecurity
    @EnableRedisRepositories
    @EnableRedisHttpSession
    @Configuration
    @Order(1)
    public static class AdminSecurity extends WebSecurityConfigurerAdapter {


        @Autowired
        AdminDetailService adminDetailService;

        /*AuthenticationManagerのBean定義を行うためのメソッドを作成*/
        @Autowired
        void configAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(adminDetailService)
                    .passwordEncoder(passwordEncoder());
        }

        @Bean
        PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }


        @Bean
        public static ConfigureRedisAction configureRedisAction() {
            return ConfigureRedisAction.NO_OP;
        }

        @Bean
        public RedisTemplate<String, Admin> jsonRedisTemplate(RedisConnectionFactory connectionFactory) {
            RedisTemplate<String, Admin> redisTemplate = new RedisTemplate<>();
            redisTemplate.setConnectionFactory(connectionFactory);
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Admin.class));
            redisTemplate.setHashKeySerializer(redisTemplate.getKeySerializer());
            redisTemplate.setHashValueSerializer(redisTemplate.getValueSerializer());
            return redisTemplate;
        }

//        @Bean
//        public JedisConnectionFactory connectionFactory(){
//            JedisConnectionFactory factory = new JedisConnectionFactory();
//            factory.setHostName("localhost");
//            factory.setPort(6379);
//            factory.setUsePool(true);
//            return factory;
//        }



        /**
         * 静的ファイルに認証はかけない.
         * @param web
         * @throws Exception
         */
        @Override
        public void configure(WebSecurity web) throws Exception{
            web
                    .ignoring()
                    .antMatchers( "/css/**", "/js/**", "/images/**");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception{
            http
                    .antMatcher("/admin/**")
                    .authorizeRequests()
                    .antMatchers("/admin/adminLogin","/admin/adminRegisterPage","/admin/registerAdmin").permitAll()
                    .antMatchers("/admin/**").hasAuthority("ADMIN")
                    .anyRequest()
                    .authenticated()
                    .and()
                    .csrf()
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                    .and();

            http
                    .formLogin()
                    .loginProcessingUrl("/admin/login")//ログイン処理をするURL
                    .loginPage("/admin/adminLogin")//ログイン画面のURL
                    .failureUrl("/admin/loginPage?error")//ログイン失敗時
                    .defaultSuccessUrl("/admin/adminTop")//認証成功時のURL
                    .usernameParameter("email")//ユーザーのパラメーター名
                    .passwordParameter("password")
                    .and();


            http
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout"))
                    .logoutSuccessUrl("/admin/adminLogin")
                    .permitAll()
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true)
                    .and();

        }

    }
}