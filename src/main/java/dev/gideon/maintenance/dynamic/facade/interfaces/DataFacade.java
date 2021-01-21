package dev.gideon.maintenance.dynamic.facade.interfaces;

import org.springframework.boot.context.event.ApplicationReadyEvent;

public interface DataFacade {
    void appReady(ApplicationReadyEvent event);
}
