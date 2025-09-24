package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema<String> {
    private  int minLength = 0;
    private  String contains = "";

    private Predicate<String> predMinLength = v -> v.length() >= minLength;
    private Predicate<String> predContains = v -> v.contains(contains);
    private Predicate<String> predIsEmpty = v -> required ? !v.isEmpty() : true;

    public StringSchema() {
        addCheck("minLength", predMinLength);
        addCheck("contains", predContains);
        addCheck("isEmpty", predIsEmpty);
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
        super.required();
        return this;
    }
}
