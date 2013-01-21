package tr.com.t2giants.ring.core.domain;

/**
 * User: sonic
 * Date: 1/21/13
 */
public class RingUserLastPosition {

    protected String id;
    protected Coordinates lastPosition;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Coordinates getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(Coordinates lastPosition) {
        this.lastPosition = lastPosition;
    }
}
