package com.example.demomultiflyway;

import org.flywaydb.core.Flyway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.MetaDataAccessException;

// should be called before FlywayMigrationInitializer
public class FlywayPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Flyway) {
            this.migrate((Flyway) bean);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    public void migrate(Flyway flyway) {
        Flyway admin = new Flyway();
        BeanUtils.copyProperties(flyway, admin);
        admin.setLocations("db/admin/migration");
        admin.setSchemas("ADMIN");
        admin.migrate();

        try {
            String url = JdbcUtils.extractDatabaseMetaData(flyway.getDataSource(), "getURL");
            DatabaseDriver driver = DatabaseDriver.fromJdbcUrl(url);
            if (driver == DatabaseDriver.H2) {
                // reset schema
                flyway.setSchemas("PUBLIC");
            }
        } catch (MetaDataAccessException ignored) {
        }

    }
}
