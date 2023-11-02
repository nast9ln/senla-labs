package org.example.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@RequiredArgsConstructor
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class JDBCPostgreSQLConnectionHolder {
    private final List<Connection> openConnections = new ArrayList<>();
    private final ConcurrentHashMap<Thread, Connection> threadConnectionConcurrentHashMap = new ConcurrentHashMap<>();
    @Value("${database.url}")
    protected String url;
    @Value("${database.user}")
    private String user;
    @Value("${database.password}")
    private String password;

    @Bean
    public Connection connect() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);
            openConnections.add(connection);
            System.out.println("connect succesful");
            return connection;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Connection getConnection (){
        Thread currentThread= Thread.currentThread();
        Connection connection = threadConnectionConcurrentHashMap.get(currentThread);
        if (connection!=null && !isConnectionClose(connection)){
            return connection;
        } else {
            connection=connect();
            threadConnectionConcurrentHashMap.put(currentThread, connection);
            return connection;
        }
    }

    private boolean isConnectionClose(Connection connection){
        try {
            return connection.isClosed();
        } catch (SQLException e) {
            return true;
        }
    }

    @EventListener(ContextClosedEvent.class)
    public void onApplicationEvent(ContextClosedEvent event) {
        for (Connection connection : openConnections){
            try {
                System.out.println("Closed connection");
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error with closing connection");
                throw new RuntimeException(e);
            }
        }
    }
}
