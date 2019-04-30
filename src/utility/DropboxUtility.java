package utility;

import java.io.IOException;
import java.io.InputStream;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.UploadErrorException;

public class DropboxUtility {
	
	public static DbxClientV2 getClient(String accessToken) {
		// Create Dropbox client
       DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial");
       return new DbxClientV2(config, accessToken);
	}
	
	public static void uploadFile(DbxClientV2 client, InputStream in, String path) throws UploadErrorException, DbxException, IOException {
		client.files().uploadBuilder("/".concat(path)).uploadAndFinish(in);
	}
	
	public static void deleteFile(DbxClientV2 client, String path) throws UploadErrorException, DbxException, IOException {
		client.files().deleteV2("/".concat(path));
	}
}
