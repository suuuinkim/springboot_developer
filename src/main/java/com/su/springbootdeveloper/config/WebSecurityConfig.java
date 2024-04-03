//package com.su.springbootdeveloper.config;
//
//import com.su.springbootdeveloper.service.UserDetailService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
//
//@RequiredArgsConstructor
//@Configuration
//public class WebSecurityConfig {
//    private final UserDetailService userService;
//
//    /**
//     * 스프링 시큐리티 기능 비활성화
//     * -- 스프링 시큐리티의 모든 기능을 사용하지 않게 설정하는 코드
//     * -- 인증 / 인가 서비스를 모든 곳에 적용하지 않음
//     * -- 일반적으로 정적 리소스(이미지, HTML 파일)에 설정
//     */
//    @Bean
//    public WebSecurityCustomizer configure() {
//        return (web) -> web.ignoring()
//                .requestMatchers(toH2Console())
//                .requestMatchers("/static/**");
//    }
//
//    /**
//     * 특정 Http 요청에 대한 웹 기반 보안 구성
//     * -- 인증 / 인가 및 로그인, 로그아웃 관련 설정
//     * -- requestMatchers() : 특정 요청과 일치하는 url에 대한 액세스를 설정
//     * -- permitAll() : 누구나 접근이 가능하게 설정 즉, /login, /signup, /user로 요청이 오면 인증 인가 없이도 접근
//     * -- anyRequest() : 위에서 설정한 url 이외의 요청에 대한 설정
//     * -- authenticated() : 별도의 인가는 필요하지 않지만 인증이 성공된 상태여야 접근
//     */
//     @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeRequests() // 인증, 인가 설정 / 특정 경로에 대한 액세스 설정
//                .requestMatchers("/login", "/signup", "/user").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin() // 폼 기반 로그인 설정
//                .loginPage("/login") // 로그인 페이지 경로를 설정
//                .defaultSuccessUrl("/articles") // 로그인이 완료되었을 때 이동할 경로 설정
//                .and()
//                .logout() // 로그아웃 설정
//                .logoutSuccessUrl("/login")// 로그아웃이 완료되었을 때 이동할 경로 설정
//                .invalidateHttpSession(true)// 로그아웃 이후에 세션을 전체 삭제할지 여부를 설정
//                .and()
//                .csrf().disable() // csrf 비활성화
//                .build();
//     }
//
//    /**
//     * 인증 관리자 관련 설정
//     */
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder,
//                                                       UserDetailService userDetailService) throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(userService) // 사용자 정보 서비스 설정 ** 반드시 UserDetailsService를 상속받은 클래스여야함
//                .passwordEncoder(bCryptPasswordEncoder)
//                .and()
//                .build();
//    }
//
//    // 패스워드 인코더로 사용할 빈 등록
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
