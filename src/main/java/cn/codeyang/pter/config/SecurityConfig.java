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
import org.springframework.security.config.Customizer;
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
import org.springframework.web.filter.CorsFilter;

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
                        header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .cors(Customizer.withDefaults());

        // 添加Logout filter
        httpSecurity.logout((logout) -> logout.logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler));
        // 添加JWT filter
        httpSecurity.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        // 添加CORS filter
        httpSecurity.addFilterBefore(corsFilter, JwtAuthenticationTokenFilter.class);
        httpSecurity.addFilterBefore(corsFilter, LogoutFilter.class);

        return httpSecurity.build();
    }

    ///**
    // * anyRequest          |   匹配所有请求路径
    // * access              |   SpringEl表达式结果为true时可以访问
    // * anonymous           |   匿名可以访问
    // * denyAll             |   用户不能访问
    // * fullyAuthenticated  |   用户完全认证可以访问（非remember-me下自动登录）
    // * hasAnyAuthority     |   如果有参数，参数表示权限，则其中任何一个权限可以访问
    // * hasAnyRole          |   如果有参数，参数表示角色，则其中任何一个角色可以访问
    // * hasAuthority        |   如果有参数，参数表示权限，则其权限可以访问
    // * hasIpAddress        |   如果有参数，参数表示IP地址，如果用户IP和参数匹配，则可以访问
    // * hasRole             |   如果有参数，参数表示角色，则其角色可以访问
    // * permitAll           |   用户可以任意访问
    // * rememberMe          |   允许通过remember-me登录的用户访问
    // * authenticated       |   用户登录后可访问
    // */
    //@Override
    //protected void configure(HttpSecurity httpSecurity) throws Exception {
    //    // 注解标记允许匿名访问的url
    //    ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity.authorizeRequests();
    //    permitAllUrl.getUrls().forEach(url -> registry.antMatchers(url).permitAll());
    //
    //    httpSecurity
    //            // CSRF禁用，因为不使用session
    //            .csrf().disable()
    //            // 认证失败处理类
    //            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
    //            // 基于token，所以不需要session
    //            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
    //            // 过滤请求
    //            .authorizeRequests()
    //            // 对于登录login 注册register 验证码captchaImage 允许匿名访问
    //            .antMatchers("/login", "/register", "/captchaImage").permitAll()
    //            // 静态资源，可匿名访问
    //            .antMatchers(HttpMethod.GET, "/", "/*.html", "/**/*.html", "/**/*.css", "/**/*.js", "/profile/**").permitAll()
    //            .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/*/api-docs", "/druid/**").permitAll()
    //            // 除上面外的所有请求全部需要鉴权认证
    //            .anyRequest().authenticated()
    //            .and()
    //            .headers().frameOptions().disable();
    //    // 添加Logout filter
    //    httpSecurity.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);
    //    // 添加JWT filter
    //    httpSecurity.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    //    // 添加CORS filter
    //    httpSecurity.addFilterBefore(corsFilter, JwtAuthenticationTokenFilter.class);
    //    httpSecurity.addFilterBefore(corsFilter, LogoutFilter.class);
    //}

    /**
     * 强散列哈希加密实现
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
