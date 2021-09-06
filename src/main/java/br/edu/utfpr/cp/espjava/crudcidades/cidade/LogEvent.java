package br.edu.utfpr.cp.espjava.crudcidades.cidade;

import org.springframework.context.ApplicationEvent;

public class LogEvent extends ApplicationEvent {

    public LogEvent(Object source) {

        super(source);
    }

}
