package tr.com.t2giants.ring.server.service.helper;

import tr.com.t2giants.ring.server.data.enums.StreamType;
import tr.com.t2giants.ring.server.exception.StritFunNotAcceptableException;
import tr.com.t2giants.ring.server.exception.StritFunRuntimeException;
import tr.com.t2giants.ring.server.exception.StritFunValidationException;
import tr.com.t2giants.ring.server.service.AWSService;
import tr.com.t2giants.ring.server.util.ErrorMessages;
import tr.com.t2giants.ring.server.util.WebDesignParameters;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * User: sonic
 * Date: 1/15/13
 */
@Service
public class StreamServiceHelperImpl implements StreamServiceHelper{

    @Autowired
    private AWSService awsService;

    @Autowired
    private UserServiceHelper userServiceHelper;

    private final Log logger = LogFactory.getLog(StreamServiceHelperImpl.class);

    private void logErrorMessage(String logMessage, Throwable ex) {
        if (logger.isErrorEnabled()) {
            if (ex != null) {
                logger.error(logMessage, ex);
            } else {
                logger.error(logMessage);
            }
        }
    }

    private void logDebugMessage(String logMessage) {
        if (logger.isDebugEnabled()) {
            logger.debug(logMessage);
        }
    }

    public String[] uploadStreamRequest(HttpServletRequest request, long id, StreamType streamType) {
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setFileSizeMax(WebDesignParameters.MAXIMUM_FILE_SIZE_IN_BYTES);
                List list = upload.parseRequest(request);
                Iterator<FileItem> iterator = list.iterator();

                while (iterator.hasNext()) {
                    FileItem item = iterator.next();
                    if (!item.isFormField()) {
                        long sizeInBytes = item.getSize();
                        try {

                            if (sizeInBytes > WebDesignParameters.MAXIMUM_FILE_SIZE_IN_BYTES) {
                                logDebugMessage("Stream upload is rejected for supporter with id: " + id + " due to " + ErrorMessages.FILE_TOO_LARGE.getErrorMessage());
                                throw new StritFunValidationException(ErrorMessages.FILE_TOO_LARGE.getErrorMessage());
                            }

                            InputStream inStream = item.getInputStream();
                            byte[] stream = new byte[(int) sizeInBytes];
                            inStream.read(stream);

                            if (streamType.equals(StreamType.FUN_ITEM)) {
                                return processStream(id, stream, streamType);
                            } else if (streamType.equals(StreamType.AVATAR)) {
                                String avatar = userServiceHelper.getAvatar(id);

                                if (!avatar.equals(WebDesignParameters.DEFAULT_AVATAR)) {
                                    String avatarThumb = userServiceHelper.getAvatarThumb(id);
                                    awsService.deleteContent(avatarThumb, StreamType.AVATAR_THUMB);
                                    awsService.deleteContent(avatar, StreamType.AVATAR);
                                }
                                return processStream(id, stream, streamType);
                            }
                        } catch (IOException ex) {
                            logErrorMessage("Exception occurred during uploading supporter avatar for user with id: : " + id, ex);
                            throw new StritFunRuntimeException();
                        }
                    }
                }
                throw new StritFunNotAcceptableException();
            } catch (FileUploadException ex) {
                logErrorMessage("Exception occurred during uploading supporter avatar for user with id: : " + id, ex);
                throw new StritFunRuntimeException();
            }
        } else {
            logErrorMessage("Avatar upload data is not multipart content for user with id: : " + id, null);
            throw new StritFunNotAcceptableException();
        }
    }

    public String[] processStream(long id, byte[] image, StreamType streamType) {
        try {

            ByteArrayInputStream inStream = new ByteArrayInputStream(image);
            BufferedImage bufferedImage = ImageIO.read(inStream);

            final String fileName = id + "_" + System.currentTimeMillis() + ".png";
            File file = new File(fileName);
            ImageIO.write(bufferedImage, "PNG", file);

            if (streamType.equals(StreamType.FUN_ITEM)) {

                String funItemPath = awsService.uploadMedia(id, new FileInputStream(file), streamType);

                file.delete();

                return new String[] {funItemPath};
            } else if (streamType.equals(StreamType.AVATAR)) {
                String largeAvatarPath = awsService.uploadMedia(id, new FileInputStream(file), streamType);

                inStream = new ByteArrayInputStream(image);
                bufferedImage = ImageIO.read(inStream);

                bufferedImage = cropImage(bufferedImage);
                BufferedImage smallImage = scale(bufferedImage);

                File smallFile = new File(fileName);
                ImageIO.write(smallImage, "PNG", smallFile);

                String smallPath = awsService.uploadMedia(id, new FileInputStream(smallFile), StreamType.AVATAR_THUMB);

                smallFile.delete();
                file.delete();

                return new String[] {smallPath, largeAvatarPath};
            } else {
                throw new StritFunRuntimeException();
            }
        } catch (IOException e) {
            throw new StritFunRuntimeException();
        }
    }

    private BufferedImage scale(BufferedImage image) {
        return scaleImage(image);
    }

    private BufferedImage scaleImage(BufferedImage image) {
        BufferedImage newImage = new BufferedImage(WebDesignParameters.SMALL_AVATAR_WIDTH, WebDesignParameters.SMALL_AVATAR_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(image, 0, 0, WebDesignParameters.SMALL_AVATAR_WIDTH, WebDesignParameters.SMALL_AVATAR_HEIGHT, null);
        graphics.dispose();
        return newImage;
    }

    private BufferedImage cropImage(BufferedImage bufferedImage) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        int widthStart = 0;
        int heightStart = 0;
        if (width > height) {
            widthStart = (width - height) / 2;
            width = height;
        } else {
            heightStart = (height - width) / 2;
            height = width;
        }
        bufferedImage = bufferedImage.getSubimage(widthStart, heightStart, width, height);
        return bufferedImage;
    }

}
