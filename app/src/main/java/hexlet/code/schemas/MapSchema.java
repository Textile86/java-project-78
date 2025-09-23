package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<String, Object>> {
    private int sizeOf = 0;
    private boolean checkSize = false;
    private boolean shape = false;
    private Map<String, BaseSchema> schemas = null;


    @Override
    public boolean isValid(Map<String, Object> value) {
        if (!required && value == null) {
            return true;
        }
        if (required && value == null) {
            return false;
        }
        if (checkSize && value.size() != sizeOf) {
            return false;
        }
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
}
