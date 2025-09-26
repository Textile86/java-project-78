package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {
    public StringSchema minLength(int min) {
        addCheck("minLength", value -> value.length() >= min);
        return this;
    }

    public StringSchema contains(String text) {
        addCheck("contains", value -> value.contains(text));
        return this;
    }

    @Override
    public StringSchema required() {
        super.required();
        addCheck("isEmpty", value -> !value.isEmpty());
        return this;
    }
}
