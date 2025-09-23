package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {
    private  int minLength = 0;
    private  String contains = null;

    @Override
    public  boolean isValid(String value) {
        if (!required && (value == null || value.isEmpty())) {
            return true;
        }
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

    public StringSchema minLength(int min) {
        minLength = min;
        return this;
    }

    public StringSchema contains(String text) {
        contains = text;
        return this;
    }

    @Override
    public StringSchema required() {
        this.required = true;
        return this;
    }
}
