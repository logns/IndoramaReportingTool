package com.lognsys.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class WriteJSONToFile {

	private static WriteJSONToFile instance;

	private WriteJSONToFile() {
	}

	public static synchronized WriteJSONToFile getInstance() {
		if (instance == null) {
			instance = new WriteJSONToFile();
		}
		return instance;
	}

	public void write (Resource resource, String str) throws IOException{
		// Get the file reference
		Path path = Paths.get(resource.getFile().getPath());

		// Use try-with-resource to get auto-closeable writer instance
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			writer.write(str);
		} 
	}
}
