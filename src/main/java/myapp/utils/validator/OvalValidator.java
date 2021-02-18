package myapp.utils.validator;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.context.FieldContext;

import java.util.List;

public class OvalValidator extends Validator {

    public String getMessage(String fieldName, List<ConstraintViolation> violations) {
        boolean isBadField = false;
        StringBuilder msg = new StringBuilder();

        for (ConstraintViolation v : violations) {
            if (((FieldContext)v.getContext()).getField().getName().equals(fieldName)) {
                isBadField = true;
                msg.append(v.getMessage()).append("  ");
            }
        }
        return isBadField ? msg.toString() : null;
    }
}
