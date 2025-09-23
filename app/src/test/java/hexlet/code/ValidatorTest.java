package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {

    @Test
    public void testStringSchema() {
        Validator v1 = new Validator();
        StringSchema schema1 = v1.string();
        assertTrue(schema1.isValid(""));
        assertTrue(schema1.isValid(null));

        schema1.required();
        assertFalse(schema1.isValid(""));
        assertFalse(schema1.isValid(null));
        assertTrue(schema1.isValid("what does the fox say"));
        assertTrue(schema1.contains("what").isValid("what does the fox say"));
        assertFalse(schema1.contains("whatthe").isValid("what does the fox say"));
        assertFalse(schema1.isValid("what does the fox say"));
        StringSchema schema2 = v1.string();
        schema2.required();
        assertTrue(schema2.minLength(10).minLength(4).isValid("Hexlet"));
    }

    @Test
    public void testNumberSchema() {
        Validator v1 = new Validator();
        NumberSchema schema2 = v1.number();
        assertTrue(schema2.isValid(5));
        assertTrue(schema2.positive().isValid(null));
        assertTrue(schema2.isValid(null));
        assertTrue(schema2.positive().isValid(null));

        schema2.required();
        assertFalse(schema2.isValid(null));
        assertTrue(schema2.isValid(10));
        assertFalse(schema2.isValid(-10));
        assertFalse(schema2.isValid(0));
        schema2.range(5, 10);
        assertTrue(schema2.isValid(5));
        assertTrue(schema2.isValid(10));
        assertFalse(schema2.isValid(4));
        assertFalse(schema2.isValid(11));

    }

    @Test
    public void testMapSchema() {
        var v3 = new Validator();
        var schema3 = v3.map();
        assertTrue(schema3.isValid(null));

        schema3.required();

        assertFalse(schema3.isValid(null));
        assertTrue(schema3.isValid(new HashMap<>()));

        var data = new HashMap<String, Object>();
        data.put("key1", "value1");
        assertTrue(schema3.isValid(data));

        schema3.sizeOf(2);
        assertFalse(schema3.isValid(data));

        data.put("key2", "value2");
        assertTrue(schema3.isValid(data));
        assertTrue(schema3.sizeOf(10).sizeOf(2).isValid(data));

    }

    @Test
    public void testShapeString() {
        var v4 = new Validator();
        var schema1 = v4.map();

        Map<String, BaseSchema> schemas = new HashMap<>();
        StringSchema nameSchema = v4.string().required();
        StringSchema lastNameSchema = v4.string().required().minLength(2);
        schemas.put("firstName", nameSchema);
        schemas.put("lastName", lastNameSchema);

        schema1.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(schema1.isValid(human1));

        Map<String, Object> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(schema1.isValid(human2));

        Map<String, Object> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(schema1.isValid(human3));

        Map<String, Object> human4 = new HashMap<>();
        human4.put("key1", "value1");
        human4.put("key2", "v2");
        assertFalse(schema1.isValid(human4));

    }

    @Test
    public void testShapeNumber() {
        var v5 = new Validator();
        var schema2 = v5.map();

        Map<String, Object> data = new HashMap<>();
        data.put("age", 35);
        data.put("salary", 100000);
        assertTrue(schema2.isValid(data));


        NumberSchema ageSchema = v5.number().range(18, 30);
        NumberSchema salarySchema = v5.number().positive();
        Map<String, BaseSchema> schemas2 = new HashMap<>();
        schemas2.put("age", ageSchema);
        schemas2.put("salary", salarySchema);
        schema2.shape(schemas2);
        assertFalse(schema2.isValid(data));
    }
}
