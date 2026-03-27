package com.mav.patientservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
    import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(properties = {
        // Don't require a running Kafka broker during tests/build-image
        "spring.kafka.admin.enabled=false"
})
class PatientServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
