package tr.com.t2giants.ring.server.data.validator;

import com.mysql.jdbc.StringUtils;
import tr.com.t2giants.ring.server.data.Coordinates;
import tr.com.t2giants.ring.server.data.FunItem;
import tr.com.t2giants.ring.server.util.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: sonic
 * Date: 1/15/13
 */
@Service
public class FunItemValidator implements Validator<FunItem> {

    private ValidationList validationList;

    private static final int COMMENT_MAX_LENGTH = 200;

    @Autowired
    private CoordinateValidatorImp coordinateValidatorImp;

    @Override
    public ValidationList validateCreationData(FunItem funItem) {
        validationList = new ValidationList();

        validateComment(funItem.getComment());
        validateLocation(funItem.getLocation());

        return validationList;
    }

    private void validateComment(String comment) {
        if (!StringUtils.isNullOrEmpty(comment) && comment.length() > COMMENT_MAX_LENGTH) {
            addValidationError(ErrorMessages.COMMENT_MAX_CHAR_EXCEEDED);
        }
    }

    private void validateLocation(Coordinates location) {
        final boolean valid = coordinateValidatorImp.isValid(location);
        if (!valid) {
            addValidationError(ErrorMessages.INVALID_COORDINATES);
        }
    }

    @Override
    public ValidationList validateUpdateData(FunItem funItem) {
        return null;
    }

    public void addValidationError(ErrorMessages errorMessages) {
        validationList.add(errorMessages.getErrorMessage());
    }
}
