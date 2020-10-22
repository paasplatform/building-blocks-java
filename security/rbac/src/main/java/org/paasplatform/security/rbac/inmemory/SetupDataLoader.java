package org.paasplatform.security.rbac.inmemory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

@SuppressWarnings("unused")
@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired()
    InMemoryUserDetailsManager userDetailsService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.print(this.userDetailsService);
    }
}
