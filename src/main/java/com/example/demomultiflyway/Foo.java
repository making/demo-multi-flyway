package com.example.demomultiflyway;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class Foo implements CommandLineRunner {
    private final JdbcTemplate jdbcTemplate;

    public Foo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("foo=" + this.jdbcTemplate.queryForList("SELECT * FROM admin.foo"));
        System.out.println("bar=" + this.jdbcTemplate.queryForList("SELECT * FROM bar"));
    }
}
