package com.fullstack.infrastructure.configuration.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {
    "com.fullstack.infrastructure.entity"
})
@EnableJpaRepositories(basePackages = {
    "com.fullstack.infrastructure.adapter"
})
public class JpaConfig {

}
