package org.example.config;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Aspect
@Component
@RequiredArgsConstructor
public class TransactionAspect {
    private final Connection connection;

    @Around("@annotation(org.example.config.MyTransaction)")
    public Object doTransaction(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        connection.setAutoCommit(false);
        System.out.println("transaction start");
        try {
            Object result = proceedingJoinPoint.proceed();
            connection.commit();
            System.out.println("transaction commit");
            return result;
        } catch (Exception e) {
            connection.rollback();
            throw e;
        }
    }
//    @Before("@annotation(org.example.config.MyTransaction)")
//    public void startTransaction(JoinPoint joinPoint) throws SQLException {
//        System.out.println("start transaction");
//        // Берем коннект у конектион холедр
//        connection.setAutoCommit(false);
//
//        // В этой строке выполняет метод сервиса
//        try {
//            joinPoint;
//        } catch (Exception e) {
//            connection.rollback();
//            System.out.println("rollback transaction");
//        }
//
//        connection.commit();
//        System.out.println();
//        System.out.println("commit transaction");
//        System.out.println();
//    }
//    @AfterReturning("@annotation(org.example.config.MyTransaction)")
//    public void commitTransaction() throws SQLException {
//        connection.commit();
//        System.out.println();
//        System.out.println("commit transaction");
//        System.out.println();
//
//    }
//
//    @AfterThrowing(value = "@annotation(org.example.config.MyTransaction)", throwing = "ex")
//    public void rollbackTransaction(RuntimeException ex) throws SQLException {
//        connection.rollback();
//        System.out.println("rollback transaction");
//    }
}

