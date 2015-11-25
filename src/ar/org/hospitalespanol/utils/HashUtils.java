package ar.org.hospitalespanol.utils;

import java.util.Calendar;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;

/**
 * Utils Class for Managing Password Hashes
 * 
 * @author fgonzalez
 * 
 */
public class HashUtils {

	/**
	 * Hashes the password.
	 * 
	 * @param password
	 *            to hash
	 * 
	 * @return hashed password as a hex-string
	 */
	public static String hashPassword(String password) {
		// we use the sha512 digester
		return DigestUtils.sha512Hex(password);
	}

	/**
	 * Generates a new token for the logged user.
	 * 
	 * @return the token as a hex-string hash
	 */
	public static String generateUserToken() {
		return DigestUtils.sha512Hex(((Long) Calendar.getInstance()
				.getTimeInMillis()).toString());
	}

	/**
	 * Generates a new random alphanumeric password of length 'length'.
	 * 
	 * @param length
	 *            the length of the generated password
	 * 
	 * @return alphanumeric string
	 */
	public static String generateRandomPassword(Integer length) {
		return RandomStringUtils.randomAlphanumeric(length.intValue());
	}
}
