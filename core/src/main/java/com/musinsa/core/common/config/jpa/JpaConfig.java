package com.musinsa.core.common.config.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EntityScan(basePackages = "com.musinsa.core.domain")
@EnableJpaRepositories(basePackages = "com.musinsa.core.domain.*.repository")
public class JpaConfig {
}