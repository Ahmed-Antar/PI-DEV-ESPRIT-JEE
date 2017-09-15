package tn.esprit.twin1.brogrammers.eventify.Eventify.util;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

public class FTPProvider {
	public static final String IMAGE = "../../img1.png";
	public static boolean UploadImageToFTP(String imgToUpload) {
		
		FTPClient client = new FTPClient();
		FileInputStream fis = null ;

		try {
		    client.connect("ftp.byethost9.com");
		    client.login("b9_19103037", "event123456789");

		    //
		    // Create an InputStream of the file to be uploaded
		    //
		    String filename = imgToUpload;
		    fis = new FileInputStream(filename);

		    //
		    // Store file to server
		    //
		    client.storeFile(filename, fis);
		    client.logout();
		    return true;
		} catch (IOException e) {
		    e.printStackTrace();
		    return false;
		} finally {
		    try {
		        if (fis != null) {
		            fis.close();
		        }
		        client.disconnect();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
		
	}
	
	
}
