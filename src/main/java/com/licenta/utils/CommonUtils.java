package com.licenta.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class used for defining general methods
 * @author mirela
 *
 */
public class CommonUtils {
	
	/**
	 * Method that determines if a string is null or empty
	 * @param str
	 * 		the string to be checked
	 * @return	true if the string is null or empty
	 */
	public static boolean isNullOrEmpty(String str) {
		if (str == null || str.length() == 0) {
			return true;
		}
		return false	;
	}

	/**
	 * Method used for deleting a directory or file 
	 * @param file
	 * 		The file to be deleted
	 * @throws IOException
	 */
	public static void deleteDirectory(File file) throws IOException {
		if (file.isDirectory()) {
			// directory is empty, then delete it
			if (file.list().length == 0) {
				file.delete();
			} else {
				// list all the directory contents
				String files[] = file.list();
				for (String temp : files) {
					// construct the file structure
					File fileDelete = new File(file, temp);

					// recursive delete
					deleteDirectory(fileDelete);
				}

				// check the directory again, if empty then delete it
				if (file.list().length == 0) {
					file.delete();
				}
			}

		} else {
			// if file, then delete it
			file.delete();
		}
	}
	
	/**
	 * Method used for getting the current time as a string
	 * @return
	 */
	public static String getCurrentTimeAsString() {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		return sdf.format(now);
	}
}
