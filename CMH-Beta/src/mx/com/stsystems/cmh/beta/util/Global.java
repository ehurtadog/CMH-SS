package mx.com.stsystems.cmh.beta.util;

public class Global {
	public static synchronized String getUUID() {
		String md5 = String.valueOf(System.nanoTime());
		
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			
			return sb.toString().toUpperCase();
		} catch (java.security.NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		}
		
		return "INVALID";
	}
}
