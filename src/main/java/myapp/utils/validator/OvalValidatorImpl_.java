package myapp.utils.validator;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.context.FieldContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OvalValidatorImpl_ implements OvalValidator_ {

    private final Validator validator;

    public OvalValidatorImpl_() {
        validator = new Validator();
    }

    @Override
    public void validate(Object o) { // throws ValidateException {
        List<ConstraintViolation> violations = validator.validate(o);
        if (!violations.isEmpty()) {
            Map<String, String> errorsMap = new HashMap<>();
            for (ConstraintViolation violation : violations) {
                if (violation.getContext() instanceof FieldContext) {
                    errorsMap.put(((FieldContext) violation.getContext()).getField().getName(), violation.getMessage());
                }
            }
         //   throw new ValidateException(errorsMap);
        }
    }
}
