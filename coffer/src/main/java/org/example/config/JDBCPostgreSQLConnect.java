package org.example.config;

import java.sql.Connection;
import java.sql.SQLException;

public interface JDBCPostgreSQLConnect {
    Connection connect() throws SQLException, ClassNotFoundException;
}
