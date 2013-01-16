package tr.com.t2giants.ring.server.service.helper;

import tr.com.t2giants.ring.server.data.enums.StreamType;

import javax.servlet.http.HttpServletRequest;

/**
 * User: sonic
 * Date: 1/15/13
 */
public interface StreamServiceHelper {

    String[] uploadStreamRequest(HttpServletRequest request, long id, StreamType streamType);

}
