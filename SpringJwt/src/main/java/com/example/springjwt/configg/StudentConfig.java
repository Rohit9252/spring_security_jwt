package com.example.springjwt.configg;

import com.example.springjwt.Service.StudentServiceImpl;
import com.example.springjwt.jwt.EntryPoint;
import com.example.springjwt.jwt.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class StudentConfig {

        @Autowired
        private StudentServiceImpl studentServiceImpl;

        @Autowired
        private AuthTokenFilter authTokenFilter;

        @Autowired
        private EntryPoint entryPoint;



        @Bean
        public SecurityFilterChain securityFilterChain( HttpSecurity http) throws Exception {


            http.exceptionHandling().authenticationEntryPoint(entryPoint).and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                    .authorizeHttpRequests(auth ->{
                        auth.requestMatchers("/api/signup", "/api/login","/api/test/all").permitAll()
                                .anyRequest().authenticated();
                    }).cors().and().csrf().disable();
            http.authenticationProvider(daoAuthenticationProvider());
            http.addFilterBefore(tokenFilter(), UsernamePasswordAuthenticationFilter.class);
            
                return http.build();


        }
        @Bean
        public DaoAuthenticationProvider daoAuthenticationProvider(){
            DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
            daoAuthenticationProvider.setUserDetailsService(studentServiceImpl);
            daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
            return daoAuthenticationProvider;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder() ;

        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {

            return auth.getAuthenticationManager()   ;

        }


        @Bean
        public AuthTokenFilter tokenFilter(){
            return new AuthTokenFilter();
        }



}
