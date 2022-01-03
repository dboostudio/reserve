package studio.dboo.reserve.infra.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import studio.dboo.reserve.module.accounts.AccountService;

import javax.sql.DataSource;

/**
 * Created by dboo on 2021/11/04
 * Blog : http://dboostudio.github.io
 * Github : http://github.com/dboostudio
 * Gitlab : http://dboostudio.synology.me:30000
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /** Bean injection */
    private final PasswordEncoder passwordEncoder;
    private final DataSource dataSource;
    private final AccountService accountService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
        auth
                .userDetailsService(accountService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
        web.ignoring().antMatchers("/node_modules/**");
        web.ignoring().antMatchers("/js/**");
        web.ignoring().antMatchers("/css/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                /** View */
                .antMatchers("/", "/view/login", "/view/sign-up").permitAll()
                .antMatchers("/view/calendar").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                /** API */
                //account
                .antMatchers(HttpMethod.POST, "/api/account").permitAll()
                .antMatchers("/api/account/login", "/api/account/logout").permitAll()
                .anyRequest().authenticated();

        //폼 로그인 설정
        http.formLogin()
                .loginPage("/view/login").permitAll();

        //로그아웃 후 리다이렉션 설정
        http.logout()
                .logoutSuccessUrl("/");
    }
}
