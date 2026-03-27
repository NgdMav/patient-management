package com.mav.analyticsservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
        // Don't require a running Kafka broker during tests/build-image
        "spring.kafka.admin.enabled=false",
        "spring.kafka.listener.auto-startup=false"
})
class AnalyticsServiceApplicationTests {

    @Test
    void contextLoads() {
    }
}
