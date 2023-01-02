package com.example.votingsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
    @Configuration
    public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
        @Bean
        PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }

        @Autowired
        DataSource dataSource;
        @Override
        @Bean
        protected UserDetailsService userDetailsService(){
            JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
            return manager;
        }

        @Bean
        RoleHierarchy roleHierarchy(){
            RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
            hierarchy.setHierarchy("ROLE_admin > ROLE_user");
            return hierarchy;
        }

        @Override
        public void configure(WebSecurity web) throws Exception{
            web.ignoring().antMatchers("/js/**","/css/**","/img/**","/lib/**","/","/security/register","/visitor/**");//排除过滤器文件
        }
        @Override
        protected void configure(HttpSecurity http) throws Exception{
            http.authorizeRequests()
                    .antMatchers("/user/**").hasRole("user")
                    .antMatchers("/admin/**").hasRole("admin")
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/security/login")//自定义登录界面
                    .defaultSuccessUrl("/user/list")//登录成功客户端跳转
                    .failureUrl("/security/login")//登录失败跳转
                    .permitAll()
                    .and()
                    .csrf().disable();
        }
    }
}
