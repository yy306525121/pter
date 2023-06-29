package cn.codeyang.pter.config;

import cn.codeyang.pter.config.properties.PermitAllUrlProperties;
import cn.codeyang.pter.framework.security.filter.JwtAuthenticationTokenFilter;
import cn.codeyang.pter.framework.security.handler.AuthenticationEntryPointImpl;
import cn.codeyang.pter.framework.security.handler.LogoutSuccessHandlerImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * spring security配置
 *
 * @author ruoyi
 */
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    /**
     * 自定义用户认证逻辑
     */

    private final UserDetailsService userDetailsService;

    /**
     * 认证失败处理类
     */
    private final AuthenticationEntryPointImpl unauthorizedHandler;

    /**
     * 退出处理类
     */
    private final LogoutSuccessHandlerImpl logoutSuccessHandler;

    /**
     * token认证过滤器
     */
    private final JwtAuthenticationTokenFilter authenticationTokenFilter;

    /**
     * 跨域过滤器
     */
    private final CorsFilter corsFilter;

    /**
     * 允许匿名访问的地址
     */
    private final PermitAllUrlProperties permitAllUrl;

    /**
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // 注解标记允许匿名访问的url
        httpSecurity.authorizeHttpRequests((request) -> {
            permitAllUrl.getUrls().forEach(url -> request.requestMatchers(url).permitAll());
        });

        httpSecurity
                // CSRF禁用，因为不使用session
                .csrf(AbstractHttpConfigurer::disable)
                // 认证失败处理类
                .exceptionHandling((exceptionHandling) ->
                        exceptionHandling.authenticationEntryPoint(unauthorizedHandler))
                // 基于token，所以不需要session
                .sessionManagement((management) ->
                        management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 过滤请求
                .authorizeHttpRequests((authz) ->
                        authz
                                // 对于登录login 注册register 验证码captchaImage 允许匿名访问
                                .requestMatchers("/login", "/register", "/captchaImage").permitAll()
                                // 静态资源，可匿名访问
                                .requestMatchers(HttpMethod.GET, "/", "/*.html", "/*/*.html", "/*/*.css", "/*/*.js", "/profile/*").permitAll()
                                .requestMatchers("/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/*/api-docs", "/druid/**").permitAll()
                                // 除上面外的所有请求全部需要鉴权认证
                                .anyRequest().authenticated())
                .headers((header) ->
                        header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        // 添加Logout filter
        httpSecurity.logout((logout) -> logout.logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler));
        // 添加JWT filter
        httpSecurity.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        // 添加CORS filter
        httpSecurity.addFilterBefore(corsFilter, JwtAuthenticationTokenFilter.class);
        httpSecurity.addFilterBefore(corsFilter, LogoutFilter.class);

        return httpSecurity.build();
    }

    //private UrlBasedCorsConfigurationSource getCorsConfigurationSource() {
    //    CorsConfiguration config = new CorsConfiguration();
    //    config.addAllowedHeader("*");
    //    config.addAllowedOrigin("*");
    //    config.addAllowedMethod(HttpMethod.GET);
    //    config.addAllowedMethod(HttpMethod.OPTIONS);
    //    config.addAllowedMethod(HttpMethod.POST);
    //    config.addAllowedMethod(HttpMethod.PUT);
    //    config.addAllowedMethod(HttpMethod.DELETE);
    //    config.addAllowedMethod(HttpMethod.PATCH);
    //    config.setAllowCredentials(true);
    //    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //    source.registerCorsConfiguration("/**", config);
    //    return source;
    //}
    /**
     * 强散列哈希加密实现
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
