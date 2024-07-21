package by.servletsCRUDApi.listener;

import by.servletsCRUDApi.config.DataBaseConnection;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InternshipContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DataBaseConnection.getConnection();

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DataBaseConnection.closeConnection();
    }
}
