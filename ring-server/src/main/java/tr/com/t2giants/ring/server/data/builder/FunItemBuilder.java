package tr.com.t2giants.ring.server.data.builder;

import tr.com.t2giants.ring.server.data.Coordinates;
import tr.com.t2giants.ring.server.data.FunItem;

/**
 * User: soner
 * Date: 1/15/13
 */
public class FunItemBuilder extends Builder<FunItem, FunItemBuilder> {

    private Coordinates location;
    private String photoURL;
    private String comment;
    private long userID;

    @Override
    public FunItem build() {

        FunItem funItem = new FunItem();

        funItem.setLocation(location);
        funItem.setPhotoURL(photoURL);
        funItem.setComment(comment);
        funItem.setUserID(userID);

        return funItem;
    }

    public FunItemBuilder location(Coordinates location) {
        this.location = location;
        return this;
    }

    public FunItemBuilder photoURL(String photoURL) {
        this.photoURL = photoURL;
        return this;
    }

    public FunItemBuilder comment(String comment) {
        this.comment = comment;
        return this;
    }

    public FunItemBuilder userID(long userID) {
        this.userID = userID;
        return this;
    }
}
