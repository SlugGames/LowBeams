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

import com.sluggames.software.LowBeams.resources.FXML.ApplicationInformationView.ApplicationInformationViewController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * This class manages the application information view.
 *
 *
 * @author david.boeger@sluggames.com
 *
 * @version 0.12.0
 * @since 0.10.0
 */
public class ApplicationInformationViewManager {
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
		Create the utility stage.
		*/
		stage = new Stage(StageStyle.UTILITY);

		/*
		Set the stage title and icon to match the application.
		*/
		stage.setTitle(
		    LowBeams.APPLICATION_TITLE
		);
		stage.getIcons().add(
		    LowBeams.APPLICATION_LOGO_ICON_IMAGE
		);
	}

	/*
			--------------
			| CONTROLLER |
			--------------
	*/
	private final ApplicationInformationViewController controller =
	    new ApplicationInformationViewController();

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
		FXMLLoader aboutLoader = new FXMLLoader(
		    ApplicationInformationViewController.FXML_FILE_URL
		);
		aboutLoader.setRoot(new ScrollPane());
		aboutLoader.setController(controller);
		aboutLoader.load();

		/*
		Set the stage to display a scene with the loaded FXML content.
		*/
		stage.setScene(new Scene(
		    aboutLoader.getRoot()
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
	public ApplicationInformationViewManager()
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
		Check if the stage is hiding and there is a primary screen
		available.
		*/
		if (!stage.isShowing() && Screen.getPrimary() != null) {
			/*
			If so, adjust the stage size and position to fit
			sensibly on the primary screen before showing it.
			*/
			stage.setWidth(
			    Screen.getPrimary().getBounds().getWidth() / 2
			);
			stage.setHeight(
			    Screen.getPrimary().getBounds().getHeight() / 2
			);
			stage.setX(
			    Screen.getPrimary().getBounds().getMinX() +
			    (stage.getWidth() / 2)
			);
			stage.setY(
			    Screen.getPrimary().getBounds().getMinY() +
			    (stage.getHeight() / 2)
			);
		}

		/*
		Show the stage in front.
		*/
		stage.show();
		stage.toFront();
	}
}