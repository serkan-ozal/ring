package tr.com.t2giants.ring.server.data.validator;

import org.springframework.stereotype.Service;
import tr.com.t2giants.ring.core.domain.Coordinates;

/**
 * User: soner
 * Date: 1/15/13
 */
@Service
public class CoordinateValidatorImp {

    public boolean isValid(Coordinates coordinates) {

        return !((coordinates.getLat() == null) || (coordinates.getLon() == null) ||
                (coordinates.getLat() <= -90.0 || coordinates.getLat() >= 90)
                || coordinates.getLon() <= -180.0 || coordinates.getLon() >= 180);
    }

}
