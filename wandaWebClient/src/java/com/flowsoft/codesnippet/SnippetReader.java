package com.flowsoft.codesnippet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SnippetReader {

	public final static Logger logger = LoggerFactory
			.getLogger(SnippetReader.class);

	public String read(String string) {
		StringBuilder builder = new StringBuilder();

		try {
			BufferedReader br = null;
			if (string.endsWith(".snip")) {
				br = new BufferedReader(new InputStreamReader(getClass()
						.getClassLoader().getResourceAsStream(
								"/codesnippets/" + string)));
			} else {
				br = new BufferedReader(new InputStreamReader(getClass()
						.getClassLoader().getResourceAsStream(
								"/staticContent/" + string)));
			}
			// String path = WandaVaadinClient.captions.getString("path");
			// File sni = new File(path);
			// logger.debug(sni.getAbsolutePath());
			// BufferedReader br = new BufferedReader(new FileReader(sni));

			while (br.ready()) {

				builder.append(br.readLine());
				builder.append(System.getProperty("line.separator"));
			}
			br.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return builder.toString();
	}
}
