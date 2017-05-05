package mx.com.stsystems.cmh.beta.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncryptDecrypt {
	private final static Logger LOGGER = LoggerFactory.getLogger(EncryptDecrypt.class);
	private static final String SECRET_KEY_1 = "1234567890123456";
	private static final String SECRET_KEY_2 = "12e4567890123456";

	private IvParameterSpec ivParameterSpec;
	private SecretKeySpec secretKeySpec;
	private Cipher cipher;
	
	public static void main(String[] args) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, 
		InvalidKeyException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {
		EncryptDecrypt encriptator = new EncryptDecrypt();
		String password = "Cal34$Echnikov&";
		String encrypted = encriptator.encrypt(password);
		
		LOGGER.debug("Password: " + password);
		LOGGER.debug("Encrypted: " + encrypted);
		LOGGER.debug("Decrypted: " + encriptator.decrypt(encrypted));
	}

	public EncryptDecrypt() throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchPaddingException, NoSuchAlgorithmException {
		ivParameterSpec = new IvParameterSpec(SECRET_KEY_1.getBytes("UTF-8"));
		secretKeySpec = new SecretKeySpec(SECRET_KEY_2.getBytes("UTF-8"), "AES");
		cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	}


	public String encrypt(String toBeEncrypt) throws NoSuchPaddingException, NoSuchPaddingException,
	InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
		byte[] encrypted = cipher.doFinal(toBeEncrypt.getBytes());
		return Base64.encodeBase64String(encrypted);
	}

	public String decrypt(String encrypted) throws InvalidAlgorithmParameterException, InvalidKeyException,
	BadPaddingException, IllegalBlockSizeException {
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
		byte[] decryptedBytes = cipher.doFinal(Base64.decodeBase64(encrypted));
		return new String(decryptedBytes);
	}
}