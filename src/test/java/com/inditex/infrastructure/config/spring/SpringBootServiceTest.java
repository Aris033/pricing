package com.inditex.infrastructure.config.spring;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@SpringBootTest
public
class SpringBootServiceTest {

    private static CountDownLatch latch = new CountDownLatch(1);
    @BeforeAll
    public static void beforeAll() throws InterruptedException {
        if (latch.getCount() > 0) {
            // Inicializa el latch solo una vez
            new Thread(() -> {
                SpringApplication.run(SpringBootService.class);
                latch.countDown(); // Asegura que el latch se reduzca solo una vez
            }).start();
            latch.await(); // Espera a que el servidor esté levantado
        }
    }
    @Test
    void contextLoads() {
    }

}