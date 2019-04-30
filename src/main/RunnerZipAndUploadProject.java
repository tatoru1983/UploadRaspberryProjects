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
		DbxClientV2 client = DropboxUtility.getClient(accessToken);
		
		//step 1: creo lo zip
		ZipUtility.zipFolder(folder, fileName);
		
		//step 2: cancello il file se presente
		DropboxUtility.deleteFile(client, fileName);
		
		//step 3: carico su DropBox
		InputStream in = new FileInputStream(fileName);
		DropboxUtility.uploadFile(client, in, fileName);
		in.close();
		
		//step 4: cancello lo zip locale
		new File(fileName).delete();
	}

}
