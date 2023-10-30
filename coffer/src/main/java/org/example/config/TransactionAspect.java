package org.example.config;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Aspect
@Component
@RequiredArgsConstructor
public class TransactionAspect {
    private final Connection connection;

    @AfterReturning("@annotation(org.example.config.MyTransaction)")
    public void commitTransaction() throws SQLException {
        connection.commit();
        System.out.println();
        System.out.println("commit transaction");
        System.out.println();

    }

    @AfterThrowing(value = "@annotation(org.example.config.MyTransaction)", throwing = "ex")
    public void rollbackTransaction(RuntimeException ex) throws SQLException {
        connection.rollback();
        System.out.println("rollback transaction");
    }
}

