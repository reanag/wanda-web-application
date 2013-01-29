package com.flowsoft.aviews;

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

			BufferedReader br = new BufferedReader(
					new FileReader(
							new File(
									"C:/Users/Andika/Desktop/wanda/workspace/workspace-sts-cfx-3.1.0.RELEASE/wandaWebClient/resources/codesnippets/"
											+ string)));

			// BufferedReader br = new BufferedReader(new FileReader(new File(
			// "/wandaWebClient/resources/codesnippets/" + string)));

			while (br.ready()) {

				builder.append(br.readLine());
				builder.append(System.getProperty("line.separator"));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return builder.toString();
	}
}
