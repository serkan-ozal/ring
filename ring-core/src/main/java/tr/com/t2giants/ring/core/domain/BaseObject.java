package tr.com.t2giants.ring.core.domain;

import java.io.Serializable;

/**
 * User: sonic
 * Date: 1/6/13
 */
public abstract class BaseObject implements Serializable {

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
