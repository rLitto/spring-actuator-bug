/*
 * Copyright 2017 Robert Winkler
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.resilience4j.circuitbreaker;

import io.github.resilience4j.service.test.TestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = TestApplication.class)
public class CircuitBreakerEndpointTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCircuitBreakerEndpoint() {

        ResponseEntity<String> response = restTemplate.getForEntity("/actuator/circuitbreaker-events", String.class);
        assertThat(response.getBody()).isEqualTo("Hello world");

        response = restTemplate.getForEntity("/actuator/circuitbreaker-events?name=Raffaele", String.class);
        assertThat(response.getBody()).isEqualTo("Hello world");

        response = restTemplate.getForEntity("/actuator/circuitbreaker-events/Raffaele", String.class);
        assertThat(response.getBody()).isEqualTo("Hello Raffaele");
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//        assertThat(response.getBody()).contains("Missing parameters: name");
//
//        response = restTemplate.getForEntity("/actuator/circuitbreaker-events/XXX?name=Raffaele", String.class);
//        assertThat(response.getBody()).isEqualTo("Hello Raffaele");

    }

}
