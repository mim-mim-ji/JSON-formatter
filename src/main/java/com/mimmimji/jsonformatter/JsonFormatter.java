package com.mimmimji.jsonformatter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class JsonFormatter {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * JSON Pretty Print
     * @param jsonString 원본 JSON 문자열
     * @return 포맷팅된 JSON 문자열
     */
    public static String prettyPrint(String jsonString) {
        String validationMessage = validate(jsonString);
        if (!validationMessage.equals("Valid")) {
            return "Error: " + validationMessage;
        }

        try {
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
        } catch (JsonProcessingException e) {
            return "Invalid JSON format: " + e.getMessage();
        }
    }

    /**
     * JSON Minify
     * @param jsonString 원본 JSON 문자열
     * @return 압축된 JSON 문자열
     */
    public static String minify(String jsonString) {
        String validationMessage = validate(jsonString);
        if (!validationMessage.equals("Valid")) {
            return "Error: " + validationMessage;
        }

        try {
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            return objectMapper.writeValueAsString(jsonNode);
        } catch (JsonProcessingException e) {
            return "Invalid JSON format: " + e.getMessage();
        }
    }

    /**
     * JSON Key Sorting
     * @param jsonString 정렬할 JSON 문자열
     * @return 정렬된 JSON 문자열
     */
    public static String sortByKeys(String jsonString){
        String validationMessage = validate(jsonString);
        if (!validationMessage.equals("Valid")) {
            return "Error: " + validationMessage;
        }

        try {
            Object jsonObject = objectMapper.readValue(jsonString, Object.class);
            TreeMap<String, Object> sortedMap = new TreeMap<>((Map<String, Object>) jsonObject);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(sortedMap);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid JSON format", e);
        }
    }

    /**
     * JSON Validation
     *
     * @param jsonString 검사할 JSON 문자열
     * @return 유효성 검사 결과
     */
    public static String validate(String jsonString) {
        try {
            objectMapper.readTree(jsonString);
            return "Valid";
        } catch (JsonProcessingException e) {
            return "Invalid JSON format: " + e.getMessage();
        }
    }

    /**
     * JSON Syntax Highlighting
     * @param jsonString 원본 JSON 문자열
     * @return 색상 추가된 JSON 문자열
     */
    private static JsonColorConfig colorConfig = new JsonColorConfig("yellow", "green", "blue", "red");

    public static String syntaxHighlight(String jsonString) {
        String validationMessage = validate(jsonString);
        if (!validationMessage.equals("Valid")) {
            return "Error: " + validationMessage;
        }

        StringBuilder builder = new StringBuilder();
        try {
            JsonNode rootNode = new ObjectMapper().readTree(jsonString);

            highlightJsonNode(rootNode, builder, 0);
        } catch (JsonParseException e) {
            return "Invalid JSON format: " + e.getMessage();
        } catch (IOException e) {
            return "Error parsing JSON: " + e.getMessage();
        }
        return builder.toString();
    }

    private static void highlightJsonNode(JsonNode node, StringBuilder builder, int indentLevel) {
        String indent = "  ".repeat(indentLevel); // 현재 들여쓰기
        if (node.isObject()) {
            builder.append("{\n");
            node.fields().forEachRemaining(entry -> {
                builder.append(indent).append("  ")
                        .append(colorConfig.getKeyColor()).append("\"").append(entry.getKey()).append("\"\u001B[0m") // 키 색상
                        .append(" : ");
                highlightJsonNode(entry.getValue(), builder, indentLevel + 1);
                builder.append(",\n"); // 키-값 쌍 끝에 쉼표 추가
            });
            // 마지막 쉼표 제거
            if (builder.charAt(builder.length() - 2) == ',') {
                builder.setLength(builder.length() - 2);
            }
            builder.append("\n").append(indent).append("}");
        } else if (node.isArray()) {
            builder.append("[");
            for (int i = 0; i < node.size(); i++) {
                highlightJsonNode(node.get(i), builder, 0); // 배열 요소 들여쓰기 제거
                if (i < node.size() - 1) {
                    builder.append(", ");
                }
            }
            builder.append("]");
        } else if (node.isTextual()) {
            builder.append(colorConfig.getStringValueColor()).append("\"").append(node.asText()).append("\"\u001B[0m"); // 값 색상
        } else if (node.isNumber()) {
            builder.append(colorConfig.getNumberValueColor()).append(node.asText()).append("\u001B[0m");     // 값 색상
        } else if (node.isBoolean()) {
            builder.append(colorConfig.getBooleanValueColor()).append(node.asText()).append("\u001B[0m");    // 값 색상
        } else {
            builder.append(node.asText());
        }
    }

    public static void setColorConfig(JsonColorConfig config) {
        colorConfig = config;
    }

    public static void main(String[] args) {
        // 샘플 JSON 데이터
        String sampleJson = "{\"name\":\"John\",\"age\":30,\"city\":\"New York\",\"hobbies\":[\"reading\",\"traveling\"]}";

        // Pretty Print
        System.out.println("=== Pretty Print ===");
        String prettyJson = JsonFormatter.prettyPrint(sampleJson);
        System.out.println(prettyJson);

        // Minify
        System.out.println("\n=== Minify ===");
        String minifiedJson = JsonFormatter.minify(sampleJson);
        System.out.println(minifiedJson);

        // Key Sorting
        System.out.println("\n=== Key Sorting ===");
        String sortedJson = JsonFormatter.sortByKeys(sampleJson);
        System.out.println(sortedJson);

        // Validation
        System.out.println("\n=== Validation ===");
        String result = JsonFormatter.validate(sampleJson);
        System.out.println("Validation result: " + result);

        // Syntax Highlighting
        System.out.println("\n=== Syntax Highlighting ===");
        String highlightedJson = JsonFormatter.syntaxHighlight(sampleJson);
        // 기본 색상 출력
        System.out.println(highlightedJson);

        // 사용자 정의 색상 설정
        JsonColorConfig customConfig = new JsonColorConfig("cyan", "red", "yellow", "green");
        JsonFormatter.setColorConfig(customConfig);

        // 사용자 정의 색상 출력
        System.out.println(JsonFormatter.syntaxHighlight(sampleJson));
    }
}
