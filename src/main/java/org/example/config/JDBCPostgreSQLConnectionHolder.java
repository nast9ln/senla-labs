package org.example.config;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.controller.PersonControllerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@RequiredArgsConstructor
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class JDBCPostgreSQLConnectionHolder {
    private static final Logger logger = LoggerFactory.getLogger(JDBCPostgreSQLConnectionHolder.class);
    private final ConcurrentHashMap<String, Connection> threadConnectionMap = new ConcurrentHashMap<>();
    @Value("${database.url}")
    protected String url;
    @Value("${database.user}")
    private String user;
    @Value("${database.password}")
    private String password;

    @Bean
    public Connection createConnection() {
        try {
            System.out.println(url+"!!!!!");
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            logger.trace("connection successful");
            return connection;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    public Connection getConnection() {
        String currentThread = Thread.currentThread().getName();
        Connection connection = threadConnectionMap.get(currentThread);
        if (connection != null && !isConnectionClose(connection)) {
            logger.trace("connection is not null and is not close");
            return connection;
        } else {
            logger.trace("new connection has been created");
            connection = DriverManager.getConnection(url, user, password);
            threadConnectionMap.put(currentThread, connection);
            return connection;
        }
    }


    private boolean isConnectionClose(Connection connection) {
        try {
            return connection.isClosed();
        } catch (SQLException e) {
            return true;
        }
    }

    @EventListener(ContextClosedEvent.class)
    public void onApplicationEvent(ContextClosedEvent event) {
        for (Connection connection : threadConnectionMap.values()) {
            try {
                logger.trace("Closed connections");
                connection.close();
            } catch (SQLException e) {
                logger.trace("Error with closing connections");
                throw new RuntimeException(e);
            }
        }
    }
}