package services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import overall.End;

public class Setfos {
	public static void setfos(String filename) {
		try {
			File file = new File("C:\\Client\\" + filename);
			End.fos = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
