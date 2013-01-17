package tr.com.t2giants.ring.server.domain.validator;

/**
 * User: sonic
 * Date: 1/13/13
 */
public interface Validator<T> {

    ValidationList validateCreationData(T t);

    ValidationList validateUpdateData(T t);

}
