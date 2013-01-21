package tr.com.t2giants.ring.core.domain;

/**
 * User: sonic
 * Date: 1/21/13
 */
public class FriendshipRequest extends BaseObject {

    protected long requesterID;
    protected long requestedID;
    protected long creationTime;

    public long getRequesterID() {
        return requesterID;
    }

    public void setRequesterID(long requesterID) {
        this.requesterID = requesterID;
    }

    public long getRequestedID() {
        return requestedID;
    }

    public void setRequestedID(long requestedID) {
        this.requestedID = requestedID;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }
}
