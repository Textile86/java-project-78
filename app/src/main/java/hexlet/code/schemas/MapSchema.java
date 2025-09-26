package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map> {
    public MapSchema sizeof(int num) {
        addCheck("sizeOf", value -> value.size() == num);
        return this;
    }

    public MapSchema shape(Map shapeSchemas) {
        addCheck("shape", value -> {
            for (Object key : shapeSchemas.keySet()) {
                BaseSchema schema = (BaseSchema) shapeSchemas.get(key);
                Object fieldValue = value.get(key);
                if (!schema.isValid(fieldValue)) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }

    @Override
    public MapSchema required() {
        super.required();
        return this;
    }
}
