package com.mimmimji.jsonformatter;

import java.util.Map;

public class JsonColorConfig {
    private static final Map<String, String> COLOR_CODES = Map.of(
            "black", "\u001B[30m",
            "red", "\u001B[31m",
            "green", "\u001B[32m",
            "yellow", "\u001B[33m",
            "blue", "\u001B[34m",
            "purple", "\u001B[35m",
            "cyan", "\u001B[36m",
            "white", "\u001B[37m",
            "reset", "\u001B[0m"
    );

    private String keyColor;
    private String stringValueColor;
    private String numberValueColor;
    private String booleanValueColor;

    public JsonColorConfig(String keyColor, String stringValueColor, String numberValueColor, String booleanValueColor) {
        this.keyColor = resolveColor(keyColor);
        this.stringValueColor = resolveColor(stringValueColor);
        this.numberValueColor = resolveColor(numberValueColor);
        this.booleanValueColor = resolveColor(booleanValueColor);
    }

    private String resolveColor(String colorName) {
        return COLOR_CODES.getOrDefault(colorName.toLowerCase(), COLOR_CODES.get("reset"));
    }

    // Getter methods
    public String getKeyColor() { return keyColor; }
    public String getStringValueColor() { return stringValueColor; }
    public String getNumberValueColor() { return numberValueColor; }
    public String getBooleanValueColor() { return booleanValueColor; }
}
