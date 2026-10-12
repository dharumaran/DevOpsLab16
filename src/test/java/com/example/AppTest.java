package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AppTest {
    @Test
    void applicationMessageIsNotEmpty() {
        String message = "Hello from the Git + Maven + Jenkins + Docker pipeline!";
        assertTrue(!message.isBlank());
    }
}
