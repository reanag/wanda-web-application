package com.flowsoft.codesnippet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SnippetReader {

	public static Logger logger = LoggerFactory.getLogger(SnippetReader.class);

	public static String read(String string) {
		StringBuilder builder = new StringBuilder();

		try {

			BufferedReader br = new BufferedReader(new FileReader(new File(
					"codesnippets/" + string)));

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
