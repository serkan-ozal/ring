package tr.com.t2giants.ring.server.data;

import java.io.Serializable;

/**
 * User: sonic
 * Date: 1/11/13
 */
public class FunItem implements Serializable {

    private String id;
    private Coordinates location;
    private String photoURL;
    private String comment;
    private long userID;

    public Coordinates getLocation() {
        return location;
    }

    public void setLocation(Coordinates location) {
        this.location = location;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
