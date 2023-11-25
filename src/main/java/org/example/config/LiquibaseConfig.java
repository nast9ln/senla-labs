package org.example.config;

import liquibase.integration.spring.SpringLiquibase;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@Data
@RequiredArgsConstructor
public class LiquibaseConfig {
    private final DataSource dataSource;
//    @Value("${liquibase.changeLogFile}")
    private String changelogFile = "classpath:changelog.xml";

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog(changelogFile);
        liquibase.setDataSource(dataSource);
        return liquibase;
    }

}


