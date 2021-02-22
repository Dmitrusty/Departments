package myapp.utils.validator;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.context.FieldContext;
import net.sf.oval.exception.ValidationFailedException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OvalValidatorImpl extends Validator {
    @Override
    public List<ConstraintViolation> validate(Object validatedObject) throws IllegalArgumentException, ValidationFailedException {
        return super.validate(validatedObject);
    }
    //    @Override
//    public void validate(Object o) { // throws ValidateException {
//        List<ConstraintViolation> violations = validator.validate(o);
//        if (!violations.isEmpty()) {
//            Map<String, String> errorsMap = new HashMap<>();
//            for (ConstraintViolation violation : violations) {
//                if (violation.getContext() instanceof FieldContext) {
//                    errorsMap.put(((FieldContext) violation.getContext()).getField().getName(), violation.getMessage());
//                }
//            }
//         //   throw new ValidateException(errorsMap);
//        }
//    }
}
