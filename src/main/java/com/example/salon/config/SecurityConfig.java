package com.example.salon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity


public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	http.formLogin(form -> form
	.loginPage("/login") // ログイン画面の URL
	.loginProcessingUrl("/authenticate")// ユーザー名・パスワードの送信先 URL
	.defaultSuccessUrl("/salon") // ログイン成功後のリダイレクト先 URL
	.failureUrl("/login?failure") // ログイン失敗後のリダイレクト先 URL
	.permitAll() // ログイン画面は未ログインでもアクセス可能
	).logout(logout -> logout
	.logoutSuccessUrl("/login?logout") // ログアウト成功後のリダイレクト先 URL
	).authorizeHttpRequests(authz -> authz
	.requestMatchers("/login").permitAll()// 「/login」はすべて許可
	// URL ごとに Role の権限を設定
	.requestMatchers("/salon/*").hasRole("ASSISTANT")
	.requestMatchers("/stylist").hasRole("STYLIST")
	.requestMatchers("/assistant").hasRole("ASSISTANT")
	.anyRequest().authenticated() // 他の URL はログイン後のみアクセス可能
	).exceptionHandling((exceptionHandling) -> exceptionHandling
					.accessDeniedPage("/error") // エラー時に表示する画面
	);
	return http.build();
	}
	
	@Bean
	public RoleHierarchy roleHierarchy() {
	// ロール階層の設定
	 RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
	 String hierarchy = "ROLE_OWNER > ROLE_STYLIST \n ROLE_STYLIST > ROLE_ASSISTANT";
	 roleHierarchy.setHierarchy(hierarchy);
	 return roleHierarchy;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
//    System.out.println(new BCryptPasswordEncoder().encode("mei"));
//    System.out.println(new BCryptPasswordEncoder().encode("sosuke"));
//    System.out.println(new BCryptPasswordEncoder().encode("chihiro"));		
//    System.out.println(new BCryptPasswordEncoder().encode("satsuki"));
//    System.out.println(new BCryptPasswordEncoder().encode("kanta"));
//    System.out.println(new BCryptPasswordEncoder().encode("yuan"));		
//    System.out.println(new BCryptPasswordEncoder().encode("marukuru"));		
	  
    return new BCryptPasswordEncoder();
	}
}
	

