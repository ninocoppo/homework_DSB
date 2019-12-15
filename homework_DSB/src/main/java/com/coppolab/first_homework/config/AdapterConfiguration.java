package com.coppolab.first_homework.config;

import com.coppolab.first_homework.services.AdapterUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class AdapterConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    AdapterUserDetailsService adapterUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.userDetailsService(adapterUserDetailsService);
    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.httpBasic().and().
                sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                /*User Controller*/
                .antMatchers("/user/register").permitAll()
                /*Record Controller*/
                .antMatchers(HttpMethod.POST,"/record/put").hasAnyAuthority("USER,ADMIN")
                .antMatchers(HttpMethod.GET,"/record/showRecord/{id}").hasAnyAuthority("USER,ADMIN")
                .antMatchers(HttpMethod.POST,"record/update/{id},{objectName}").hasAnyAuthority("USER,ADMIN")
                .antMatchers(HttpMethod.GET,"/record/testGateway").permitAll()
                /*Minio Controller*/
                .antMatchers(HttpMethod.GET,"/minio/files").hasAnyAuthority("USER,ADMIN")
                .antMatchers(HttpMethod.POST,"/minio/upload").permitAll()
                .antMatchers(HttpMethod.DELETE,"/minio/deleteAsAdmin").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/minio/deleteAsUser").hasAnyAuthority("ADMIN","USER")
                .anyRequest().authenticated()
                .and().csrf().disable();
    }
}
