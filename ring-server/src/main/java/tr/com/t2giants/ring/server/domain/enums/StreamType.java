package tr.com.t2giants.ring.server.domain.enums;

/**
 * User: sonic
 * Date: 1/16/13
 */
public enum StreamType {

    BACKGROUNDS("backgrounds"),
    AVATAR("avatar"),
    AVATAR_THUMB("avatar-thumb"),
    FUN_ITEM("fun-items");

    private final String prefix;

    StreamType(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
