package br.edu.utfpr.cp.espjava.crudcidades;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class CrudCidadesApplication {

    @Value("${server.port}")
    private String property;

    @Value("${passwd}")
    private String passwd;

    @Value("${company}")
    private String company;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        System.out.println("Property value: " + this.property);
        System.out.println("Passwd value: " + this.passwd);
        System.out.println("The company is: " + this.company);
    }

	public static void main(String[] args) {
		SpringApplication.run(CrudCidadesApplication.class, args);
	}

	@Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

	@Bean
    public Validator getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}
