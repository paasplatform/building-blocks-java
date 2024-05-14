package org.paasplatform.edas.configuration.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CustomServletContextListener implements ServletContextListener {

    Logger logger = LoggerFactory.getLogger(CustomServletContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("Servlet {} context is initialized", sce.getServletContext().getServletContextName());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Servlet {} context is destroyed", sce.getServletContext().getServletContextName());
    }
}
