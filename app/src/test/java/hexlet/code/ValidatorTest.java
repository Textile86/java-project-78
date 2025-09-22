package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {
    @Test
    public void testValidator() {
        Validator v = new Validator();
        StringSchema schema1 = v.string();
        assertTrue(schema1.isValid(""));
        assertTrue(schema1.isValid(null));
        schema1.required();
        assertFalse(schema1.isValid(""));
        assertFalse(schema1.isValid(null));
        assertTrue(schema1.isValid("what does the fox say"));
        assertTrue(schema1.contains("what").isValid("what does the fox say"));
        assertFalse(schema1.contains("whatthe").isValid("what does the fox say"));
        assertFalse(schema1.isValid("what does the fox say"));

        StringSchema schema2 = v.string();
        schema2.required();
        assertTrue(schema2.minLength(10).minLength(4).isValid("Hexlet"));
    }
}
