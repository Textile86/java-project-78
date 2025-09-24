package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema<Integer> {
    private boolean positive = false;
    private boolean range = false;
    private int from = 0;
    private int to = 0;

    Predicate<Integer> predPositive = v -> positive ?  v > 0 : true;
    Predicate<Integer> predRange = v -> range ? (v >= from && v <= to) : true;

    public NumberSchema() {
        addCheck("positive", predPositive);
        addCheck("range", predRange);
    }

    public NumberSchema positive() {
        positive = true;
        return this;
    }

    public NumberSchema range(int x, int y) {
        range = true;
        this.from = x;
        this.to = y;
        return this;
    }

    @Override
    public NumberSchema required() {
        super.required();
        return this;
    }
}
