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
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 * This class manages a single overlay. In addition to encapsulating all related
 * components, it implements any corresponding application logic. It is
 * important to note that this class automatically creates the necessary
 * {@link javafx.stage.Stage} upon construction, so no further action is
 * required to begin using the overlay.
 *
 *
 * @author david.boeger@sluggames.com
 *
 * @version 0.8.0
 * @since 0.1.0
 */
public class OverlayManager {
	/*
		******************
		*** PROPERTIES ***
		******************
	*/
	/*
			-----------
			| EXPOSED |
			-----------
	*/
	/*
				\\\\\\\\\\\\\\\\\
				\ TARGET SCREEN \
				\\\\\\\\\\\\\\\\\
	*/
	private final SimpleObjectProperty<Screen> targetScreenProperty =
	    new SimpleObjectProperty<>(Screen.getPrimary());

	/*
					//////////////
					/ INITIALIZE /
					//////////////
	*/
	private void initializeTargetScreenProperty() {
		/*
		Add a change listener to the target screen property which
		updates the internal target screen bounds property.
		*/
		targetScreenProperty.addListener((
		    ObservableValue<? extends Screen> targetScreenObservableValue,
		    Screen targetScreenOldValue,
		    Screen targetScreenNewValue
		) -> {
			/*
			Validate the new value.
			*/
			if (targetScreenNewValue == null) {
				throw new NullPointerException(
				    "targetScreenNewValue == null"
				);
			}

			/*
			Update the target screen bounds property.
			*/
			targetScreenBoundsProperty.set(
			    targetScreenNewValue.getBounds()
			);
		});
	}

	/*
					///////
					/ GET /
					///////
	*/
	public ObjectProperty<Screen> getTargetScreenProperty() {
		return targetScreenProperty;
	}

	/*
				\\\\\\\\\\\
				\ ENABLED \
				\\\\\\\\\\\
	*/
	public static final boolean DEFAULT_ENABLED = true;

	private final SimpleBooleanProperty enabledProperty =
	    new SimpleBooleanProperty(DEFAULT_ENABLED);

	/*
					//////////////
					/ INITIALIZE /
					//////////////
	*/
	private void initializeEnabledProperty() {
		/*
		Add a change listener to the check box to verify that new values
		are valid.
		*/
		enabledProperty.addListener((
		    ObservableValue<? extends Boolean> enabledObservableValue,
		    Boolean enabledOldValue,
		    Boolean enabledNewValue
		) -> {
			/*
			Validate the new value.
			*/
			if (enabledNewValue == null) {
				throw new NullPointerException(
				    "enabledNewValue == null"
				);
			}
		});
	}

	/*
					///////
					/ GET /
					///////
	*/
	public BooleanProperty getEnabledProperty() {
		return enabledProperty;
	}

	/*
			------------
			| INTERNAL |
			------------
	*/
	/*
				\\\\\\\\\\\\\\\\\\\\\\\\
				\ TARGET SCREEN BOUNDS \
				\\\\\\\\\\\\\\\\\\\\\\\\

	Even though the user controls the exposed target screen property, it's
	the target screen's bounds themselves which are used to fit the stage to
	the target screen.
	*/
	private final SimpleObjectProperty<Rectangle2D> targetScreenBoundsProperty =
	    new SimpleObjectProperty<>(Screen.getPrimary().getBounds());

	/*
					//////////////
					/ INITIALIZE /
					//////////////
	*/
	private void initializeTargetScreenBoundsProperty() {
		/*
		Add a change listener to the target screen bounds property which
		fits the stage to the target screen any time the bounds change.
		*/
		targetScreenBoundsProperty.addListener((
		    ObservableValue<? extends Rectangle2D> targetScreenBoundsObservableValue,
		    Rectangle2D targetScreenBoundsOldValue,
		    Rectangle2D targetScreenBoundsNewValue
		) -> {
			/*
			Validate the new value.
			*/
			if (targetScreenBoundsNewValue == null) {
				throw new NullPointerException(
					"targetScreenBoundsNewValue == null"
				);
			}

			/*
			Fit the stage to the target screen's new bounds.
			*/
			fitStageToTargetScreenBounds();
		});
	}


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
		Set a window event handler to ignore close requests on the
		overlay stage. This prevents the user from directly closing
		overlay stages, allowing them to be managed by the application
		logic.
		*/
		stage.setOnCloseRequest((
		    WindowEvent windowEvent
		) -> {
			/*
			Ignoring window events is achieved by consuming them in
			the event handler.
			*/
			windowEvent.consume();
		});

		/*
		Set the stage title and icon to match the application.
		*/
		stage.setTitle(LowBeams.APPLICATION_TITLE);
		stage.getIcons().add(LowBeams.APPLICATION_LOGO_ICON_IMAGE);

		/*
		Fit the stage to the default target screen bounds.
		*/
		fitStageToTargetScreenBounds();

		/*
		Set the stage to always be in front. This setting doesn't always
		work, depending on the platform and application permissions, so
		the stage shoudld be repeatedly moved to the front as a backup.
		*/
		stage.setAlwaysOnTop(true);
		stage.toFront();

		/*
		Set the stage's full screen properties in such a way that it
		minimizes unwanted visual distractions. This is not necessary,
		as the stage should never be set to full screen, but if for
		whatever reason it is, it would be annoying for users to have to
		see exit hints or other unexpected intrusions.
		*/
		stage.setFullScreenExitHint("");
		stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
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
				\\\\\\\
				\ GET \
				\\\\\\\
	*/
	public OverlayController getController() {
		return controller;
	}


	/*
		********************
		*** CONSTRUCTION ***
		********************
	*/
	public OverlayManager() {
		/*
		Initialize exposed properties.
		*/
		initializeTargetScreenProperty();
		initializeEnabledProperty();

		/*
		Initialize internal properties.
		*/
		initializeTargetScreenBoundsProperty();

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
				Check if the target screen's bounds have
				changed. Unfortunately, it is not clear from the
				JavaFX documentation whether or not the bounds
				object of a screen can change. It seems likely
				that any such changes to a screen would manifest
				themselves as entirely new screen objects, but
				the documentation does not provide any
				definitive answers. Therefore, it is best to be
				safe and check for updates.
				*/
				if (
				    targetScreenProperty.get().getBounds() !=
				    targetScreenBoundsProperty.get()
				) {
					/*
					If so, update the target screen bounds
					property to the new bounds, which should
					refit the stage to the target screen.
					*/
					targetScreenBoundsProperty.set(
					    targetScreenProperty.get().getBounds()
					);
				}

				/*
				Check if the enabled property is set to true.
				*/
				if (enabledProperty.get()) {
					/*
					If so, move the stage to the front, in
					case the request to always be in front
					is not honored by the OS due to platform
					restrictions or insufficient
					permissions.
					*/
					stage.show();
					stage.toFront();
				} else {
					/*
					Otherwise, hide the stage.
					*/
					stage.hide();
				}
			}
		}.start();
	}


	/*
		*****************************************
		*** FIT STAGE TO TARGET SCREEN BOUNDS ***
		*****************************************
	*/
	private void fitStageToTargetScreenBounds() {
		/*
		Set the stage's position to match the target screen bounds.
		*/
		stage.setX(targetScreenBoundsProperty.get().getMinX());
		stage.setY(targetScreenBoundsProperty.get().getMinY());

		/*
		Set the stage's dimensions to match the target screen bounds.
		*/
		stage.setWidth(targetScreenBoundsProperty.get().getWidth());
		stage.setHeight(targetScreenBoundsProperty.get().getHeight());
	}
}