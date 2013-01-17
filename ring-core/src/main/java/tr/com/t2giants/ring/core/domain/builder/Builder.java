package tr.com.t2giants.ring.core.domain.builder;

/**
 * User: sonic
 * Date: 1/15/13
 */
@SuppressWarnings("unchecked")
public abstract class Builder<T, B> {

    long id;

    public B id(long id) {
        this.id = id;
        return (B) this;
    }

    public abstract T build();
}