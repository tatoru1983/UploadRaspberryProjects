package utility;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtility {
	
	public static String deleteStrategyA(Path file) throws IOException {
		String userMessage = "";
		boolean isWritable = Files.isWritable(file);
		if (isWritable) {
			Files.delete(file);
			userMessage = "Deleted Successfully!";
		} else {
			userMessage = "File is not writable.";
		}
		return userMessage;
	}

	public static String deleteStrategyB(Path file) throws IOException {
		String userMessage = "";
		try {
			// do not check for permission
			// just go ahead and delete
			// if denied, act based on it
			Files.delete(file);
			userMessage = "Deleted Successfully!";
		} catch (AccessDeniedException ade) {
			ade.printStackTrace();
			userMessage = ade.getMessage() + " File is not writable.";
		}
		return userMessage;
	}
}
