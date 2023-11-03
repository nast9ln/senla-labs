package org.example.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class TransactionAspect {

    private final JDBCPostgreSQLConnectionHolder connectionHolder;

    @Around("@annotation(org.example.config.Transaction)")
    public Object doTransaction(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Connection connection = connectionHolder.getConnection();
        connection.setAutoCommit(false);
        log.trace("transaction start");
        try {
            Object result = proceedingJoinPoint.proceed();
            connection.commit();
            log.info("transaction commit");
            return result;
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connectionHolder.closeConnection();
        }
    }

}

