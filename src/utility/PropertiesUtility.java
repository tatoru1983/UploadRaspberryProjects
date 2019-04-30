package utility;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtility {
	
	private static InputStream is = null;
	
	static {
		String filePath = "foobar.config";
		is = PropertiesUtility.class.getClassLoader().getResourceAsStream(filePath);
	}

	public static Properties getPropValues() throws IOException {
		Properties prop = new Properties();
		if(is!=null) {
			prop.load(is);
		}else {
			throw new FileNotFoundException("Errore!");
		}
		return prop;
	}
}
