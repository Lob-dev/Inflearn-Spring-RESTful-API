package com.lob.rest.events;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void builder() {
        // Given - Then
        Event event = Event.builder().build();

        // When
        assertNotNull(event);
    }

    @Test
    void builder_argument() {
        // Given - Then
        Event event = Event.builder()
                .name("Inflearn Spring REST API")
                .description("REST API Development with Spring")
                .build();

        // When
        assertNotNull(event);
    }

}