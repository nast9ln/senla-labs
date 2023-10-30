package org.example.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class JDBCPostgreSQLConnectionHolder {
    private List<Connection> openConnections = new ArrayList<>();
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
            return connection;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @EventListener
    public void onApplicationEvent(ContextClosedEvent event) {
        for (Connection connection : openConnections){
            try {
                connection.close();
                System.out.println("Closed connection");
            } catch (SQLException e) {
                System.out.println("Error with closing connection");
                throw new RuntimeException(e);
            }
        }
    }
}
