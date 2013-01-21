package tr.com.t2giants.ring.core.domain;

/**
 * User: sonic
 * Date: 1/21/13
 */
public class Friendship extends BaseObject {

    protected long followerID;
    protected long followingID;
    protected long creationTime;

    public long getFollowerID() {
        return followerID;
    }

    public void setFollowerID(long followerID) {
        this.followerID = followerID;
    }

    public long getFollowingID() {
        return followingID;
    }

    public void setFollowingID(long followingID) {
        this.followingID = followingID;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }
}
