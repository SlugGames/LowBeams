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

import com.sluggames.software.LowBeams.resources.FXML.Overlay.OverlayController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author david.boeger@sluggames.com
 *
 * @version 0.1.0
 * @since 0.1.0
 */
public class OverlayManager {
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
	private final Stage stage = new Stage(StageStyle.TRANSPARENT);

	/*
				\\\\\\\\\\\\\\
				\ INITIALIZE \
				\\\\\\\\\\\\\\
	*/
	private void initializeStage() {
		/*
		Set the stage title and icon to match the application.
		*/
		stage.setTitle(LowBeams.APPLICATION_TITLE);
		stage.getIcons().add(LowBeams.APPLICATION_LOGO_ICON_IMAGE);

		/*
		Fit the stage to the primary screen. Eventually, the user should
		be able to select which screen to target.
		*/
		stage.setX(Screen.getPrimary().getBounds().getMinX());
		stage.setY(Screen.getPrimary().getBounds().getMinY());
		stage.setWidth(Screen.getPrimary().getBounds().getWidth());
		stage.setHeight(Screen.getPrimary().getBounds().getHeight());

		/*
		Set the stage to always be in front. This setting doesn't always
		work, depending on the platform and application permissions, so
		the stage shoudld be repeatedly moved to the front as a backup.
		*/
		stage.setAlwaysOnTop(true);
		stage.toFront();

		/*
		Set the stage to always be full-screen. This is not really
		necessary with all the prior steps, but again, it's just a
		backup.
		*/
		stage.setFullScreenExitHint("");
		stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		stage.setFullScreen(true);

		/*
		Show the stage.
		*/
		stage.show();
	}

	/*
			--------------
			| CONTROLLER |
			--------------
	*/
	private final OverlayController controller = new OverlayController();

	/*
				\\\\\\\\\\\\\\
				\ INITIALIZE \
				\\\\\\\\\\\\\\
	*/
	private void initializeController() {
		/*
		Load the controller's FXML file.
		*/
		FXMLLoader overlayLoader =
		    new FXMLLoader(OverlayController.FXML_FILE_URL);
		overlayLoader.setRoot(new GridPane());
		overlayLoader.setController(controller);

		try {
			overlayLoader.load();
		} catch (IOException ex) {
			Logger.getLogger(OverlayManager.class.getName()).log(Level.SEVERE, null, ex);
		}

		/*
		Create a transparent scene containing the root.
		*/
		Scene scene = new Scene(overlayLoader.getRoot());
		scene.setFill(Color.TRANSPARENT);

		/*
		............................
		... BIND ROOT DIMENSIONS ...
		............................
		*/
		GridPane root = overlayLoader.getRoot();

		/*
			.............
			... WIDTH ...
			.............
		*/
		root.minWidthProperty().bind(scene.widthProperty());
		root.prefWidthProperty().bind(scene.widthProperty());
		root.maxWidthProperty().bind(scene.widthProperty());

		/*
			..............
			... HEIGHT ...
			..............
		*/
		root.minHeightProperty().bind(scene.heightProperty());
		root.prefHeightProperty().bind(scene.heightProperty());
		root.maxHeightProperty().bind(scene.heightProperty());

		/*
		Set the stage to display the prepared scene.
		*/
		stage.setScene(scene);
	}


	/*
		********************
		*** CONSTRUCTION ***
		********************
	*/
	public OverlayManager() {
		/*
		Initialize components.
		*/
		initializeStage();
		initializeController();

		/*
		Start a new animation timer, which fires once per JavaFX pulse.
		*/
		new AnimationTimer() {
			@Override
			public void handle(long ignoredTime) {
				/*
				Repeatedly move the stage to the front, in case
				the request to always be in front is not honored
				by the OS due to platform restrictions or
				insufficient permissions.
				*/
				stage.toFront();
			}
		}.start();
	}
}