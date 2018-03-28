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

import com.sluggames.software.LowBeams.resources.FXML.PreferencesMenu.PreferencesMenuController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * This class manages the applications main preferences menu. In addition to
 * encapsulating all related components, it implements any corresponding
 * application logic. It is important to note that this class automatically
 * creates the necessary {@link javafx.stage.Stage} upon construction, so no
 * further action is required to begin using the preferences menu.
 *
 *
 * @author david.boeger@sluggames.com
 *
 * @version 0.9.0
 * @since 0.2.0
 */
public class PreferencesMenuManager {
	/*
		******************
		*** COMPONENTS ***
		******************
	*/
	/*
			\\\\\\\\\
			\ STAGE \
			\\\\\\\\\
	*/
	private final Stage stage = new Stage();

	/*
				//////////////
				/ INITIALIZE /
				//////////////
	*/
	private void initializeStage() {
		/*
		Set a window event handler to exit the application when the
		preferences menu stage receives a close request.
		*/
		stage.setOnCloseRequest((
		    WindowEvent windowEvent
		) -> {
			/*
			Exit the JavaFX platform.
			*/
			Platform.exit();
		});

		/*
		Set the stage title and icon to match the application.
		*/
		stage.setTitle(LowBeams.APPLICATION_TITLE);
		stage.getIcons().add(LowBeams.APPLICATION_LOGO_ICON_IMAGE);

		/*
		Show the stage.
		*/
		stage.show();
	}

	/*
			\\\\\\\\\\\\\\
			\ CONTROLLER \
			\\\\\\\\\\\\\\
	*/
	private final PreferencesMenuController controller =
	    new PreferencesMenuController();

	/*
				//////////////
				/ INITIALIZE /
				//////////////
	*/
	private void initializeController() {
		/*
		Load the controller's FXML file.
		*/
		FXMLLoader preferencesMenuLoader =
		    new FXMLLoader(PreferencesMenuController.FXML_FILE_URL);
		preferencesMenuLoader.setRoot(new ScrollPane());
		preferencesMenuLoader.setController(controller);

		try {
			preferencesMenuLoader.load();
		} catch (IOException ex) {
			Logger.getLogger(PreferencesMenuManager.class.getName()).log(Level.SEVERE, null, ex);
		}

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
	public PreferencesMenuManager() {
		/*
		Initialize components.
		*/
		initializeStage();
		initializeController();
	}


	/*
		****************************
		*** BIND OVERLAY MANAGER ***
		****************************
	*/
	public void bindOverlayManager(OverlayManager overlayManager) {
		/*
		Validate arguments.
		*/
		if (overlayManager == null) {
			throw new NullPointerException(
			    "overlayManager == null"
			);
		}

		/*
		.....................
		... BIND CONTROLS ...
		.....................
		*/
		/*
			................................
			... TARGET SCREEN CHOICE BOX ...
			................................
		*/
		overlayManager.getTargetScreenProperty().bind(
		    controller.getTargetScreenChoiceBox().valueProperty()
		);

		/*
			.........................
			... ENABLED CHECK BOX ...
			.........................
		*/
		overlayManager.getEnabledProperty().bind(
		    controller.getEnabledCheckBox().selectedProperty()
		);

		/*
			....................................
			... GRID LINES VISIBLE CHECK BOX ...
			....................................
		*/
		overlayManager.getController().getGridLinesVisibleProperty().bind(
		    controller.getGridLinesVisibleCheckBox().selectedProperty()
		);

		/*
			.............
			... COLOR ...
			.............
		*/
		overlayManager.getController().getColorProperty().bind(
		    controller.getColorProperty()
		);

		/*
			.....................
			... CURSOR WINDOW ...
			.....................
		*/
		/*
				.................................
				... TRACKING FREQUENCY SLIDER ...
				.................................
		*/
		overlayManager.getController().getCursorTrackingFrequencyProperty().bind(
		    controller.getCursorTrackingFrequencySlider().valueProperty()
		);

		/*
				.........................
				... DIMENSION SLIDERS ...
				.........................
		*/
		/*
					.............
					... WIDTH ...
					.............
		*/
		overlayManager.getController().getCursorWindowWidthProperty().bind(
		    controller.getCursorWindowWidthSlider().valueProperty()
		);

		/*
					..............
					... HEIGHT ...
					..............
		*/
		overlayManager.getController().getCursorWindowHeightProperty().bind(
		    controller.getCursorWindowHeightSlider().valueProperty()
		);
	}
}