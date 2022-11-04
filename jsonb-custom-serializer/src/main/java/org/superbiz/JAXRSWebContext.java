package org.superbiz;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class JAXRSWebContext implements ServletContextListener {

    @Override
    public void contextInitialized(final ServletContextEvent arg0) {
        System.out.println("application started");
    }

    @Override
    public void contextDestroyed(final ServletContextEvent arg0) {
        System.out.println("application stopped");
    }
    
}
