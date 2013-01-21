package tr.com.t2giants.ring.server.util;

/**
 * User: sonic
 * Date: 1/13/13
 */
public enum ErrorMessages {

    INVALID_USERNAME("invalid.username"),
    EMAIL_ALREADY_USED("email.already.used"),
    USERNAME_ALREADY_TAKEN("username.already.taken"),
    INVALID_EMAIL("invalid_email"),
    INVALID_AGE("invalid.age"),
    USERNAME_MAX_CHAR_EXCEEDED("max.char.exceeded.username"),
    EMPTY_USERNAME("empty.username"),
    EMPTY_EMAIL("empty.email"),
    EMPTY_FULL_NAME("empty.full.name"),
    EMPTY_PASSWORD("empty.password"),
    EMPTY_AGE("empty.age"),
    FULL_NAME_MAX_CHAR_EXCEEDED("max.char.exceeded.full.name"),
    COMMENT_MAX_CHAR_EXCEEDED("About cannot be more than 200 chars"),
    USERNAME_CONTAINS_SPACE("username.contains.space"),
    PASSWORD_TOO_SHORT("password.too.short"),
    PASSWORD_MAX_CHAR_EXCEEDED("password.too.long"),
    CANNOT_FIND_USER("Cannot find user"),
    SAME_SPORTING_EVENT_EXISTS("same.sporting.event.exists"),
    COUNTERS_ARE_NOT_ZERO("counters.are.not.zero"),
    INVALID_SEASON("invalid.season"),
    NON_EXISTING_LEAGUE("non.existing.league"),
    INVALID_COORDINATES("invalid.coordinates"),
    NO_FOOTBALL_CLUB_EXISTS("no.football.club.exits"),
    CAPACITY_EXCEEDS("capacity.exceeds"),
    CLUB_HAS_STADIUM("club.has.another.stadium"),
    INVALID_HOME("invalid.home"),
    INVALID_AWAY("invalid.away"),
    SAME_FOOTBALL_CLUB("same.football.club"),
    CAPACITY_SHALL_BE_GREATER_THAN_10000("capacity.shall.be.greater.than.10000"),
    EMAIL_MAX_LENGTH_EXCEEDED("email.max.length"),
    CLUB_NAME_TOO_SHORT("club.name.too.short"),
    CLUB_NAME_TOO_LONG("club.name.too.long"),
    CLUB_NAME_EMPTY("empty.club.name"),
    EARLIER_THAN_1800("earlier.than.1800"),
    LATER_THAN_TODAY("later.than.today"),
    MAX_LEAGUE_NAME_EXCEEDED("max.league.name.exceeded"),
    LEAGUE_NAME_TOO_SHORT("short.league.name"),
    EMPTY_LEAGUE_NAME("empty.league.name"),
    CLUB_IS_NOT_IN_LEAGUE("club.is.not.in.league"),
    CLUB_IS_IN_ALREADY_IN_ANOTHER_LEAGUE("club.is.already.in.league"),
    CLUB_INFO_TOO_SHORT("club.info.too.short"),
    CLUB_INFO_TOO_LONG("club.info.too.long"),
    CLUB_INFO_EMPTY("club.info.empty"),
    NON_EXISTING_EVENT("non.existing.event"),
    NON_EXISTING_SEASON("non.existing.season"), INVALID_STADIUM("invalid.stadium"),
    END_DATE_CANNOT_BE_BEFORE_THAN_START_DATE("end.date.before.start.date"),
    SAME_END_YEAR_EXISTS("same.end.year.exists"),
    SAME_START_YEAR_EXISTS("same.start.year.exists"),
    FILE_TOO_LARGE("file.too.large"),
    FILE_TOO_SMALL("file.too.small"),
    COMMENT_BODY_EMPTY("comment.body.empty"),
    COMMENT_BODY_LENGTH_EXCEEDED("comment.body.length.exceeded"),
    NEWS_DIFFERENT_TEAM_AND_SUPPORTER("news.and.supporter.different"),
    NOT_IN_STREAM_RANGE("not.in.stream.range"),
    BACKGROUND_COLOR_LENGTH_IS_TOO_LONG("bg.color.length.is.too.long"),
    BACKGROUND_COLOR_LENGTH_IS_INVALID("bg.color.is.invalid"),
    BACKGROUND_COLOR_CANNOT_BE_EMPTY("bg.color.cannot.be.empty"),
    LINK_COLOR_LENGTH_IS_TOO_LONG("link.color.length.is.too.long"),
    LINK_COLOR_LENGTH_IS_INVALID("link.color.is.invalid"),
    LINK_COLOR_CANNOT_BE_EMPTY("link.color.cannot.be.empty"),
    CANNOT_COMPLETE_OPERATION("Cannot complete operation");

    private final String value;

    ErrorMessages(String value) {
        this.value = value;
    }

    public String getErrorMessage() {
        return value;
    }
}
