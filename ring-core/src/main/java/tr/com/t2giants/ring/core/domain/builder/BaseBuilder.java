package tr.com.t2giants.ring.core.domain.builder;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseBuilder<T> implements Builder<T> {

	protected Class<T> dataClass;
	
	@SuppressWarnings("unchecked")
	public BaseBuilder() {
		Type type = getClass().getGenericSuperclass();
		if (type instanceof ParameterizedType) {
		    ParameterizedType paramType = (ParameterizedType) type;
		    dataClass = (Class<T>) paramType.getActualTypeArguments()[0];
		} 
		else if (type instanceof Class) {
			dataClass = (Class<T>) type;
		}
	}
	
	@Override
	public T empty() {
		try {
			return dataClass.newInstance();
		} 
		catch (InstantiationException e) {
			e.printStackTrace();
		} 
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
