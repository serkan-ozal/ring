package tr.com.t2giants.ring.server.domain.validator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * User: sonic
 * Date: 6/12/12
 */
public class ValidationList implements Iterable<String>{

    private final List<String> list;

    public ValidationList() {
        list = new ArrayList<String>();
    }

    public void add(String s) {
        if (s != null) {
            list.add(s);
        }
    }

    @Override
    public Iterator<String> iterator() {
        return list.iterator();
    }

    public int size() {
        return list.size();
    }

    public String get(int index) {
        return list.get(index);
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }
}

