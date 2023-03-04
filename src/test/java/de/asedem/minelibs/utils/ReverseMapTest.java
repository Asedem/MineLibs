package de.asedem.minelibs.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReverseMapTest {

    private ReverseMap<String, String> reverseMap;

    @BeforeEach
    void setup() {
        this.reverseMap = new ReverseMap<>();
        this.reverseMap.put("Test123", "test");
    }

    @Test
    void testInsertAndGetValue() {
        assertEquals("test", this.reverseMap.get("Test123"));
    }

    @Test
    void testInsertAndGetKey() {
        assertEquals("Test123", this.reverseMap.getKey("test"));
    }
}
