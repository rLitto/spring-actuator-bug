package io.github.resilience4j.circuitbreaker.monitoring.endpoint;


import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "circuitbreaker-events")
public class CircuitBreakerEventsEndpoint {


    @ReadOperation
    public String getAllCircuitBreakerEvents() {
        return "Hello world";
    }

    @ReadOperation
    public String getEventsFilteredByCircuitBreakerName(@Selector String name) {
        return "Hello " + name;
    }

    @ReadOperation
    public String getEventsFilteredByCircuitBreakerNameAndEventType(@Selector String name, @Selector String eventType) {
        return eventType + " " + name;
    }
}
