package org.pnpl.analysis.invariant.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class InvariantFile {
	private static String INVARIANT_FILENAME = "invariant.ser";

	public static void write(Map<String, List<String>> listOfInvariants) {
		try {
			FileOutputStream fileOut = new FileOutputStream(INVARIANT_FILENAME);
			ObjectOutputStream oos = new ObjectOutputStream(fileOut);
			oos.writeObject(listOfInvariants);
			oos.flush();
			oos.close();
			fileOut.close();			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}

	@SuppressWarnings("unchecked")
	public static Map<String, List<String>> read() {
		Map<String, List<String>> listOfInvariants = null;

		if(exist()) {
			try {
				FileInputStream fileIn = new FileInputStream(INVARIANT_FILENAME);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				listOfInvariants = (Map<String, List<String>>) in.readObject();
				in.close();
				fileIn.close();
				delete();
			} catch (IOException e) {
				e.printStackTrace();		
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		return listOfInvariants;
	}

	// Remove the file just read
	public static void delete() { 
		try {
			Files.delete(FileSystems.getDefault().getPath(INVARIANT_FILENAME));
		} catch (NoSuchFileException x) {
			System.err.format("%s: no such" + " file or directory%n", INVARIANT_FILENAME);
		} catch (IOException x) {
			System.err.println(x);
		}
	}

	private static boolean exist() {
		File archivo = new File(INVARIANT_FILENAME);
		return archivo.exists();
	}
}
