/*
 * MIT License
 *
 * Copyright (c) 2018 Slug Games
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.sluggames.software.LowBeams.resources.FXML.ApplicationInformationView;

import com.sluggames.software.LowBeams.LowBeams;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * This class is the FXML controller for the application information view.
 *
 *
 * @author david.boeger@sluggames.com
 *
 * @version 0.10.0
 * @since 0.10.0
 */
public class ApplicationInformationViewController {
	/*
		************
		*** FXML ***
		************
	*/
	/*
			------------
			| FILE URL |
			------------
	*/
	public static final URL FXML_FILE_URL =
	    ApplicationInformationViewController.class.getResource(
	    "ApplicationInformationView.fxml"
	);

	/*
			--------------
			| COMPONENTS |
			--------------
	*/
	/*
				\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
				\ APPLICATION LOGO ICON IMAGE VIEW \
				\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	*/
	@FXML
	private ImageView applicationLogoIconImageView;

	/*
					//////////////
					/ INITIALIZE /
					//////////////
	*/
	private void initializeApplicationLogoIconImageView() {
		applicationLogoIconImageView.setImage(
		    LowBeams.APPLICATION_LOGO_ICON_IMAGE
		);
	}

	/*
				\\\\\\\\\\\\\\\\\\\\
				\ APPLICATION TEXT \
				\\\\\\\\\\\\\\\\\\\\
	*/
	@FXML
	private Text applicationText;

	/*
					/////////
					/ LABEL /
					/////////
	*/
	@FXML
	private Label applicationTextLabel;

	/*
					//////////////
					/ INITIALIZE /
					//////////////
	*/
	private void initializeApplicationText() {
		/*
		Associate with the corresponding label.
		*/
		applicationTextLabel.setLabelFor(applicationText);

		/*
		Set the text content.
		*/
		applicationText.setText(LowBeams.APPLICATION_TITLE);
	}

	/*
				\\\\\\\\\\\\\\\\
				\ VERSION TEXT \
				\\\\\\\\\\\\\\\\
	*/
	@FXML
	private Text versionText;

	/*
					/////////
					/ LABEL /
					/////////
	*/
	@FXML
	private Label versionTextLabel;

	/*
					//////////////
					/ INITIALIZE /
					//////////////
	*/
	private void initializeVersionText() {
		/*
		Associate with the corresponding label.
		*/
		versionTextLabel.setLabelFor(versionText);

		/*
		Set the text content.
		*/
		versionText.setText(LowBeams.APPLICATION_VERSION_STRING);
	}

	/*
				\\\\\\\\\\\\\\\\\\
				\ COPYRIGHT TEXT \
				\\\\\\\\\\\\\\\\\\
	*/
	@FXML
	private Text copyrightText;

	/*
					/////////
					/ LABEL /
					/////////
	*/
	@FXML
	private Label copyrightTextLabel;

	/*
					//////////////
					/ INITIALIZE /
					//////////////
	*/
	private void initializeCopyrightText() {
		/*
		Associate with the corresponding label.
		*/
		copyrightTextLabel.setLabelFor(copyrightText);

		/*
		Set the text content.
		*/
		copyrightText.setText(LowBeams.COPYRIGHT);
	}

	/*
				\\\\\\\\\\\\\\\\
				\ LICENSE TEXT \
				\\\\\\\\\\\\\\\\
	*/
	@FXML
	private Text licenseText;

	/*
					/////////
					/ LABEL /
					/////////
	*/
	@FXML
	private Label licenseTextLabel;

	/*
					//////////////
					/ INITIALIZE /
					//////////////
	*/
	private void initializeLicenseText() {
		/*
		Associate with the corresponding label.
		*/
		licenseTextLabel.setLabelFor(licenseText);

		/*
		Set the text content.
		*/
		licenseText.setText(LowBeams.LICENSE);
	}


	/*
		******************
		*** INITIALIZE ***
		******************
	*/
	@FXML
	public void initialize() {
		/*
		Initialize FXML components.
		*/
		initializeApplicationLogoIconImageView();
		initializeApplicationText();
		initializeVersionText();
		initializeCopyrightText();
		initializeLicenseText();
	}
}