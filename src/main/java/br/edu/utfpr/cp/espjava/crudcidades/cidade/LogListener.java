package br.edu.utfpr.cp.espjava.crudcidades.cidade;

import org.springframework.context.event.EventListener;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;

@Log
@Component
public class LogListener {

    @EventListener
    public void onApplicationEvent(LogEvent event) {
        var source = (CidadeController) event.getSource();

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        log.info(source.getClass().getCanonicalName() + " - Event dispatched - " + username);

    }
    
}
