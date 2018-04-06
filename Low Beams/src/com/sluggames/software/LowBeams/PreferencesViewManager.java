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
package com.sluggames.software.LowBeams;

import com.sluggames.software.LowBeams.resources.FXML.PreferencesView.PreferencesViewController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

/**
 * This class manages the preferences view.
 *
 *
 * @author david.boeger@sluggames.com
 *
 * @version 0.12.0
 * @since 0.2.0
 */
public class PreferencesViewManager {
	/*
		******************
		*** COMPONENTS ***
		******************
	*/
	/*
			---------
			| STAGE |
			---------
	*/
	private Stage stage;

	/*
				\\\\\\\\\\\\\\
				\ INITIALIZE \
				\\\\\\\\\\\\\\
	*/
	private void initializeStage() {
		/*
		Create the default stage.
		*/
		stage = new Stage();

		/*
		Set the stage title and icon to match the application.
		*/
		stage.setTitle(LowBeams.APPLICATION_TITLE);
		stage.getIcons().add(LowBeams.APPLICATION_LOGO_ICON_IMAGE);
	}

	/*
			--------------
			| CONTROLLER |
			--------------
	*/
	private final PreferencesViewController controller =
	    new PreferencesViewController();

	/*
				\\\\\\\\\\\\\\
				\ INITIALIZE \
				\\\\\\\\\\\\\\
	*/
	/**
	 * @throws IOException		Failed to load controller FXML.
	 */
	private void initializeController()
	    throws
	    IOException
	{
		/*
		Load the controller's FXML file.
		*/
		FXMLLoader preferencesMenuLoader = new FXMLLoader(
		    PreferencesViewController.FXML_FILE_URL
		);
		preferencesMenuLoader.setRoot(new ScrollPane());
		preferencesMenuLoader.setController(controller);
		preferencesMenuLoader.load();

		/*
		Set the stage to display a scene with the loaded FXML content.
		*/
		stage.setScene(new Scene(
		    preferencesMenuLoader.getRoot()
		));
	}


	/*
		********************
		*** CONSTRUCTION ***
		********************
	*/
	/**
	 * @throws IOException		Failed to load controller FXML.
	 */
	public PreferencesViewManager()
	    throws
	    IOException
	{
		/*
		Initialize components.
		*/
		initializeStage();
		initializeController();
	}


	/*
		***************
		*** DISPLAY ***
		***************
	*/
	public void display() {
		/*
		Show the stage in front.
		*/
		stage.show();
		stage.toFront();
	}
}