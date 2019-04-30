package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.dropbox.core.v2.DbxClientV2;

import utility.DropboxUtility;
import utility.PropertiesUtility;
import utility.ZipUtility;

public class RunnerZipAndUploadProject {

	private static Properties props;

	static {
		try{
			props = PropertiesUtility.getPropValues();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	};

	public static void main(String[] args) throws Exception {
		//2 argomenti:
		//0 - Cartella da zippare
		//1 - Nome zip file
		String folder = args[0];
		String fileName = args[1].concat(".zip");

		String accessToken = props.getProperty("Token");
		
		//step 1: creo lo zip
		ZipUtility.zipFolder(folder, fileName);
		
		//step 2: carico su DropBox
		DbxClientV2 client = DropboxUtility.getClient(accessToken);
		InputStream in = new FileInputStream(fileName);
		DropboxUtility.uploadFile(client, in, fileName);
		
		//step 3: cancello lo zip locale
		new File(fileName).delete();
	}

}
