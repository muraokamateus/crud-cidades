package br.edu.utfpr.cp.espjava.crudcidades;

import javax.servlet.Filter;

import com.amazonaws.xray.javax.servlet.AWSXRayServletFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XRayConfig {

    @Bean
    public Filter TracingFilter() {
        return new AWSXRayServletFilter("CrudCidades");
    }

}
