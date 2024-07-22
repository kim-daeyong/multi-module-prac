package com.musinsa.bo.config.redis;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Profile;
import redis.embedded.RedisServer;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Profile("test")
@TestConfiguration
public class TestEmbeddedRedisConfig {

    private RedisServer redisServer;

    @PostConstruct
    public void startRedis() throws IOException {
        if (isArm()) {
            redisServer = new RedisServer(Objects.requireNonNull(getRedisTestServer()), 6480);
        } else {
            redisServer = RedisServer.builder()
                    .port(6480)
                    .setting("maxmemory 128M")
                    .build();
        }
        redisServer.start();
    }

    @PreDestroy
    public void stopRedis() {
        if (redisServer != null) {
            redisServer.stop();
        }
    }

    private boolean isArm() {
        return System.getProperty("os.arch").contains("aarch64");
    }

    private File getRedisTestServer() throws IOException {
        try {
            return new File("src/test/resources/binary/redis/redis-server");
        } catch (Exception e) {
            throw new IOException("Redis server exec fail");
        }
    }
}
