package com.flowsoft.component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.vaadin.server.FileResource;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Upload;

public class PictureUpload extends CustomComponent implements
		Upload.SucceededListener, Upload.FailedListener, Upload.Receiver {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public File file;
	public Panel root;
	Panel imagePanel;

	public PictureUpload() {
		root = new Panel("My Upload Component");
		setCompositionRoot(root);

		// Create the Upload component.
		final Upload upload = new Upload("Upload the file here", this);

		// Use a custom button caption instead of plain "Upload".
		upload.setButtonCaption("Upload Now");

		// Listen for events regarding the success of upload.
		upload.addListener((Upload.SucceededListener) this);
		upload.addListener((Upload.FailedListener) this);

		root.addComponent(upload);
		root.addComponent(new Label("Click 'Browse' to "
				+ "select a file and then click 'Upload'."));

		// Create a panel for displaying the uploaded image.
		imagePanel = new Panel("Uploaded image");
		imagePanel.addComponent(new Label("No image uploaded yet"));
		root.addComponent(imagePanel);
	}

	public OutputStream receiveUpload(String filename, String MIMEType) {
		FileOutputStream fos = null; // Output stream to write to
		file = new File("" + filename);
		try {
			// Open the file for writing.
			fos = new FileOutputStream(file);
		} catch (final java.io.FileNotFoundException e) {
			// Error while opening the file. Not reported here.
			e.printStackTrace();
			return null;
		}

		return fos; // Return the output stream to write to
	}

	// This is called if the upload is finished.
	public void uploadSucceeded(Upload.SucceededEvent event) {
		// Log the upload on screen.
		root.addComponent(new Label("File " + event.getFilename()
				+ " of type '" + event.getMIMEType() + "' uploaded."));

		// Display the uploaded file in the image panel.
		final FileResource imageResource = new FileResource(file);
		imagePanel.removeAllComponents();
		imagePanel.addComponent(new Embedded("", imageResource));
	}

	// This is called if the upload fails.
	public void uploadFailed(Upload.FailedEvent event) {
		// Log the failure on screen.
		root.addComponent(new Label("Uploading " + event.getFilename()
				+ " of type '" + event.getMIMEType() + "' failed."));
	}

	public byte[] getPicture() {
		FileInputStream fin;
		try {
			fin = new FileInputStream(file);
			byte fileContent[] = new byte[(int) file.length()];
			fin.read(fileContent);
			fin.close();
			return fileContent;
		} catch (FileNotFoundException e) {

			e.printStackTrace();
			return null;
		} catch (IOException e) {

			e.printStackTrace();
			return null;
		}

	}
}