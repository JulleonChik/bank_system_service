package pro.julleon.bank_system.validation.validators;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniqueCollectionValidationContext {
    private static final ThreadLocal<Map<String, List<String>>> invalidValuesContext = ThreadLocal.withInitial(HashMap::new);

    public static List<String> getInvalidValues(String field) {
        Map<String, List<String>> context = invalidValuesContext.get();
        if (context == null) {
            return Collections.emptyList();
        }

        List<String> strings = context.get(field);
        return strings != null ? strings : Collections.emptyList();
    }

    public static void setInvalidValues(String field, List<String> invalidValues) {
        invalidValuesContext.get().put(field, invalidValues);
    }

    public static void clear() {
        invalidValuesContext.remove();
    }
}
