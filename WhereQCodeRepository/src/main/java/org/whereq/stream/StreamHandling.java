package org.whereq.stream;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class StreamHandling {
	
	protected byte[] file2ByteArray(String localResourceURI) {
		File file = new File(localResourceURI);
		InputStream inputStream = null;

		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		byte[] buff = new byte[1024];

		int bytesRead = 0;

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		try {
			while ((bytesRead = inputStream.read(buff)) != -1) {
				byteArrayOutputStream.write(buff, 0, bytesRead);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		byte[] data = byteArrayOutputStream.toByteArray();

		try {
			byteArrayOutputStream.flush();
			byteArrayOutputStream.close();
			byteArrayOutputStream = null;
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			inputStream.close();
			inputStream = null;
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return data;
	}
}
