package tr.com.t2giants.ring.server.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import tr.com.t2giants.ring.server.data.enums.StreamType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * User: sonic
 * Date: 1/15/13
 */
@Service
public class AWSServiceImpl implements AWSService{

    private final Log logger = LogFactory.getLog(AWSServiceImpl.class);

    private static final String EXTENSION = "png";

    @Value("${cloud.bucket.content.name}")
    private String bucketName;

    @Value("${cloud.bucket.content.url}")
    private String contentURLPrefix;

    @Value("${cloud.bucket.s3.content.url}")
    private String s3ContentURL;

    @Value("${targetPlatform}")
    private String targetPlatform;

    private static AmazonS3 s3 = null;

    @PostConstruct
    public void init() throws IOException {
        AWSCredentials credentials = new PropertiesCredentials(this.getClass().getResourceAsStream("/global." + targetPlatform +".properties"));
        s3 = new AmazonS3Client(credentials);
    }

    private void logDebugMessage(String logMessage) {
        if (logger.isDebugEnabled()) {
            logger.debug(logMessage);
        }
    }

    private void logErrorMessage(String logMessage, Throwable ex) {
        if (logger.isErrorEnabled()) {
            if (ex != null) {
                logger.error(logMessage, ex);
            } else {
                logger.error(logMessage);
            }
        }
    }

    @Override
    public String uploadMedia(long id, InputStream image, StreamType streamType) {
        logDebugMessage("Medial upload request is received for id: " + id);
        return uploadStream(id, image, streamType);
    }

    private String uploadStream(long id, InputStream stream, StreamType streamType) {
        try {
            String key = decideKeyName(id, streamType);

            ObjectMetadata meta = new ObjectMetadata();
            meta.setCacheControl("public, max-age=252460800");
            meta.setHeader("Expires", "Thu, 21 Mar 2042 08:16:32 GMT");
            meta.setLastModified(new Date());

            meta.setContentType("image/png");

            PutObjectRequest putObjectRequest;

            putObjectRequest = new PutObjectRequest(bucketName, key, stream, meta);

            putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
            s3.putObject(putObjectRequest);

            String streamPath = contentURLPrefix + "/" + key;
            logDebugMessage("Medial uploaded successfully to the path: " + streamPath);
            return streamPath;
        }
        catch (Exception ex) {
            logErrorMessage("Exception during uploading media for id: " + id, ex);
            return null;
        }
    }

    private String decideKeyName(Long id, StreamType streamType) {
        return streamType.getPrefix() + "/"
                    + System.currentTimeMillis() + "_"
                    + id + "." + EXTENSION;
    }

    public void deleteContent(String deletePath, StreamType streamType) {
        try {
            deletePath = deletePath.replace(contentURLPrefix + "/", "");
            logDebugMessage("File is tried to be removed from s3 with key: " + deletePath);
            s3.deleteObject(bucketName, deletePath);
        }
        catch (Exception e) {
            logErrorMessage("Cannot delete file from path: " + deletePath + " from s3", e);
        }
    }
}
