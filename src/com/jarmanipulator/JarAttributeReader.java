package com.jarmanipulator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class JarAttributeReader {

	public static void main(String args[]) throws IOException {
		if (args.length == 0) {
			throw new RuntimeException("You should specify a folder location");
		}
		String attributeName = "Jar-Version";
		if (args.length >= 2) {
			attributeName = args[1];
		}
		String folderPath = args[0];
		File result = new File("result.txt");
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(
				result));
		out.write("List of attributes\r\n");
		File[] files = new File(folderPath).listFiles();
		for (File file : files) {
			JarFile jar;
			try {
				jar = new JarFile(file);
				Manifest manifest = jar.getManifest();
				out.append(file.getName() + " : "
						+ manifest.getMainAttributes().getValue(attributeName)
						+ "\r\n");
				System.out.println(file.getName() + " : "
						+ manifest.getMainAttributes().getValue(attributeName));
			} catch (IOException e) {
				System.out.println("Error handling jar file" + file.getName());
			}
		}
		out.flush();
		out.close();
	}
}
