package hexlet.code.schemas;

public class StringSchema {
    private boolean isValid = true;
    private  int minLength = 0;
    private  String contains = null;
    private  boolean required = false;

    public  boolean isValid(String value) {
        if (required && (value == null || value.isEmpty())) {
            return false;
        }
        if (contains != null && !value.contains(contains)) {
            return false;
        }
        if (minLength > 0 && value.length() < minLength) {
            return false;
        }
        return true;
    }

    public StringSchema required() {
        required = true;
        return this;
    }

    public StringSchema minLength(int min) {
        minLength = min;
        return this;
    }

    public StringSchema contains(String text) {
        contains = text;
        return this;
    }
}
