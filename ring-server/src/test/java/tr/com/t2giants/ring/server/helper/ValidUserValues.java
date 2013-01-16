package tr.com.t2giants.ring.server.helper;

import org.apache.commons.lang.RandomStringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * User: sonic
 * Date: 1/15/13
 */
public class ValidUserValues {

    public String getMaxUsername() {
        return RandomStringUtils.random(15, "username");
    }

    public String getMaxFullName() {
        return RandomStringUtils.random(40, "fullName");
    }

    public String getMaxPassword() {
        return RandomStringUtils.random(15, "password");
    }

    public String getMaxEmail() {
        return RandomStringUtils.random(249, "email") + "@a.com";
    }

    public String getMaxAbout() {
        return RandomStringUtils.random(200, "about");
    }

    public String getRandomImageURL() {
        return RandomStringUtils.random(96, "imageURL") + ".png";
    }

    public long getValidBirthDate() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date(System.currentTimeMillis()));
        cal.add(Calendar.YEAR, -13);

        System.out.println(cal.getTime());

        return cal.getTimeInMillis();
    }
}
