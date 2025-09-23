package hexlet.code.schemas;

public abstract class BaseSchema<T> {
    boolean required = false;

    public abstract boolean isValid(T value);

    public BaseSchema<T> required() {
        required = true;
        return this;
    }

}
