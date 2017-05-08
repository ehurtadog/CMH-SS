package mx.com.stsystems.cmh.beta.test.image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;

public class ImageManipulation {
	
	public static void main(String[] args) {
		File file = new File("d:\\1705000001.png");
		
		try {
			FileInputStream imageInFile = new FileInputStream(file);
			byte imageData[] = new byte[(int) file.length()];
			imageInFile.read(imageData);
			
			String imageDataString = encodeImage(imageData);
			
			System.out.println("Image Data String size: " + imageDataString.length());
			System.out.println("Image Data String excerpt: " + imageDataString.substring(0, 500));
			
			byte[] imageByteArray = decodeImage(imageDataString);
			
			FileOutputStream imageOutFile = new FileOutputStream("d:\\1705000001-2.jpg");
			
			imageOutFile.write(imageByteArray);
			
			imageInFile.close();
			imageOutFile.close();
			
			System.out.println("Image successfully Manipulated!");
		} catch (FileNotFoundException fnfe) {
			System.err.println("Image not found: " + fnfe);
		} catch (IOException ioe) {
			System.err.println("Exception while reading the image: " + ioe);
		}
	}
	
	public static String encodeImage(byte[] imageByteArray) {
		return Base64.encodeBase64URLSafeString(imageByteArray);
	}
	
	public static byte[] decodeImage(String imageDataString) {
		return Base64.decodeBase64(imageDataString);
	}
}
