package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema<Map<String, Object>> {
    private int sizeOf = 0;
    private boolean checkSize = false;
    private boolean shape = false;
    private Map<String, BaseSchema> schemas = null;

    Predicate<Map<String, Object>> predSizeOf = v -> checkSize ? v.size() == sizeOf : true;
    Predicate<Map<String, Object>> predShape = v -> checkSchema(v);

    public MapSchema() {
        addCheck("sizeOf", predSizeOf);
        addCheck("shape", predShape);
    }

    public boolean checkSchema(Map<String, Object> value) {
        if (shape) {
            for (String key : schemas.keySet()) {
                BaseSchema schema = schemas.get(key);
                Object fieldValue = value.get(key);
                if (!schema.isValid(fieldValue)) {
                    return false;
                }
            }
        }
        return true;
    }

    public MapSchema sizeOf(int num) {
        checkSize = true;
        sizeOf = num;
        return this;
    }

    public void shape(Map<String, BaseSchema> shapeSchemas) {
        this.shape = true;
        this.schemas = shapeSchemas;
    }

    @Override
    public MapSchema required() {
        super.required();
        return this;
    }
}
