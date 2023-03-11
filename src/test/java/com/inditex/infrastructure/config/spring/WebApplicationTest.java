package com.inditex.infrastructure.config.spring;

import com.inditex.infrastructure.rest.spring.controllers.Resources;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebApplicationTest {

    @Autowired
    private Resources resources;

    @Test
    void contextLoads() {
        Assertions.assertThat(resources).isNotNull();
    }
}
