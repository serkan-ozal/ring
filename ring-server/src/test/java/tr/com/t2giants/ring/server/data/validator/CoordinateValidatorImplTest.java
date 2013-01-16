package tr.com.t2giants.ring.server.data.validator;

import tr.com.t2giants.ring.server.data.Coordinates;
import tr.com.t2giants.ring.server.data.builder.CoordinatesBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: soner
 * Date: 1/15/13
 */
@RunWith(MockitoJUnitRunner.class)
public class CoordinateValidatorImplTest {

    @InjectMocks
    private final CoordinateValidatorImp impl = new CoordinateValidatorImp();

    @Test
    public void validateOutOfRangeLatitudeCoordinate(){
        Coordinates coordinates = new Coordinates(179.99, 90.0);

        assertThat(impl.isValid(coordinates), is(false));

        coordinates = new CoordinatesBuilder().latitude(-90.0).longitude(-179.99).build();
        assertThat(impl.isValid(coordinates), is(false));
    }

    @Test
    public void validateOutOfRangeLongitudeCoordinate(){
        Coordinates coordinates = new CoordinatesBuilder().coordinates(180.0, 89.99).build();

        assertThat(impl.isValid(coordinates), is(false));

        coordinates = new CoordinatesBuilder().latitude(-89.99).longitude(-180.0).build();
        assertThat(impl.isValid(coordinates), is(false));
    }

    @Test
    public void validateEqual(){
        Coordinates c1 = new CoordinatesBuilder().latitude(89.99).longitude(170.0).build();
        Coordinates c2 = new CoordinatesBuilder().latitude(89.99).longitude(170.0).build();
        Coordinates c3 = new CoordinatesBuilder().latitude(89.99).longitude(170.01).build();

        assertThat(c1.equals(c2), is(true));
        assertThat(c1.equals(c3), is(false));
        assertThat(c2.equals(c3), is(false));
    }

    @Test
    public void validateNullCoordinate(){
        Coordinates coordinates = null;

        assertThat(impl.isValid(coordinates), is(false));
    }

    @Test
    public void validateSuccessfully(){
        Coordinates coordinates = new CoordinatesBuilder().latitude(89.99).longitude(179.99).build();

        assertThat(impl.isValid(coordinates), is(true));

        coordinates = new CoordinatesBuilder().latitude(-89.99).longitude(-179.99).build();
        assertThat(impl.isValid(coordinates), is(true));

        coordinates = new CoordinatesBuilder().latitude(0.0).longitude(-0.0).build();
        assertThat(impl.isValid(coordinates), is(true));
    }
}
