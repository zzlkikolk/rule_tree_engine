package com.github.zzlkikolk.exception;

/**
 * 解析json异常
 */
public class JsonFormatException extends RuntimeException {
    // 可选：存储导致异常的原始字符串
    private final String rawData;

    public JsonFormatException(String message) {
        super(message);
        this.rawData = null;
    }

    public JsonFormatException(String message, String rawData) {
        super(message);
        this.rawData = rawData;
    }

    public JsonFormatException(String message, Throwable cause) {
        super(message, cause);
        this.rawData = null;
    }

    public JsonFormatException(String message, String rawData, Throwable cause) {
        super(message, cause);
        this.rawData = rawData;
    }

    // 获取原始数据（可选）
    public String getRawData() {
        return rawData;
    }

    @Override
    public String getMessage() {
        if (rawData != null) {
            return super.getMessage() + "\nRaw Data: " + rawData;
        }
        return super.getMessage();
    }
}
