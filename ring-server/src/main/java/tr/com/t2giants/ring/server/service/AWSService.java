package tr.com.t2giants.ring.server.service;

import tr.com.t2giants.ring.server.domain.enums.StreamType;

import java.io.InputStream;

/**
 * User: soner
 * Date: 1/15/13
 */
public interface AWSService {

    String uploadMedia(long id, InputStream bgImage, StreamType streamType);

    void deleteContent(String deletePath, StreamType streamType);

}
