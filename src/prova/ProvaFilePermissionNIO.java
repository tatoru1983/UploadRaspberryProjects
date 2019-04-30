package prova;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProvaFilePermissionNIO {
	public static void main(String args[]) throws IOException {

		Path file = Paths.get("prova.zip");
		String userMessage = "";

		userMessage = strategyA(file);
		System.out.println("Strategy A: " + userMessage);

		userMessage = strategyB(file);
		System.out.println("Strategy B: " + userMessage);
	}

	public static String strategyA(Path file) throws IOException {
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

	public static String strategyB(Path file) throws IOException {
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
