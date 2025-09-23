package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {
    private boolean positive = false;
    private boolean range = false;
    private int from = 0;
    private int to = 0;

    @Override
    public boolean isValid(Integer value) {
        if (!required && value == null) {
            return true;
        }
        if (required && value == null) {
            return false;
        }
        if (positive && value <= 0) {
            return false;
        }
        if (range && (value < from || value > to)) {
            return false;
        }
        return true;
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
}
