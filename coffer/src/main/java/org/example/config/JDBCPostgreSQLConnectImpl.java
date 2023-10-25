package org.example.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
@RequiredArgsConstructor
public class JDBCPostgreSQLConnectImpl implements JDBCPostgreSQLConnect {
    @Value("${database.url}")
    protected String url;
    @Value("${database.user}")
    private String user;
    @Value("${database.password}")
    private String password;
    @Bean
    public Connection connect () {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null)
                System.out.println("Connected to PostgreSQL");
            else System.out.println("Failed to connect PostgreSQL");
        } catch (Exception e) {
            System.out.println(e);
        }
        return connection;
    }
}
