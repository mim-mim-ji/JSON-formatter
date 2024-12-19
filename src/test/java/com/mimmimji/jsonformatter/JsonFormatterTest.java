package com.mimmimji.jsonformatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JsonFormatterTest {
    private static final String SAMPLE_JSON = "{\"name\":\"John\",\"age\":30,\"city\":\"New York\"}";

    @Test
    void testPrettyPrint() {
        String prettyJson = JsonFormatter.prettyPrint(SAMPLE_JSON);
        assertTrue(prettyJson.contains("\n"));
    }

    @Test
    void testMinify() {
        String minifiedJson = JsonFormatter.minify(SAMPLE_JSON);
        assertFalse(minifiedJson.contains("\n"));
    }

    @Test
    void testSortKeys() throws JsonProcessingException {
        String sortedJson = JsonFormatter.sortByKeys(SAMPLE_JSON);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode expectedJsonNode = mapper.readTree("{\"age\":30,\"city\":\"New York\",\"name\":\"John\"}");
        JsonNode actualJsonNode = mapper.readTree(sortedJson);

        assertEquals(expectedJsonNode, actualJsonNode,
                "Expected JSON structure does not match the actual result");
    }

    @Test
    void testValidate() {
        String validJson = JsonFormatter.validate(SAMPLE_JSON);
        assertEquals("Valid", validJson, "Expected JSON to be valid");

        String invalidJson = JsonFormatter.validate("{invalid-json}");
        assertFalse(invalidJson.equals("Valid"), "Expected JSON to be invalid");
        assertTrue(invalidJson.startsWith("Invalid JSON format:"), "Expected error message to start with 'Invalid JSON format:'");
    }

    @Test
    void testSyntaxHighlightValidJson() {
        JsonFormatter.setColorConfig(new JsonColorConfig("yellow", "green", "blue", "red"));
        String highlightedJson = JsonFormatter.syntaxHighlight(SAMPLE_JSON);
        assertNotNull(highlightedJson, "Syntax highlighting should not return null");
        assertTrue(highlightedJson.contains("\u001B[33m"), "Key color (yellow) should be applied");
        assertTrue(highlightedJson.contains("\u001B[32m"), "String value color (green) should be applied");
    }
}
