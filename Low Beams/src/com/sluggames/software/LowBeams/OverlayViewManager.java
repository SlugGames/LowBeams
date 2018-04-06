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

import com.sluggames.software.LowBeams.resources.FXML.OverlayView.OverlayViewController;
import java.io.IOException;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
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
 * This class manages the overlay view.
 *
 *
 * @author david.boeger@sluggames.com
 *
 * @version 0.12.0
 * @since 0.1.0
 */
public class OverlayViewManager {
	/*
		******************
		*** PROPERTIES ***
		******************
	*/
	/*
			-----------------
			| TARGET SCREEN |
			-----------------
	*/
	private final SimpleObjectProperty<Screen> targetScreenProperty =
	    new SimpleObjectProperty<>();

	/*
				\\\\\\\\\\\\\\\\\\\\\\\\
				\ TARGET SCREEN BOUNDS \
				\\\\\\\\\\\\\\\\\\\\\\\\

	Even though the user controls the target screen property, it's actually
	the target screen's bounds which are used to fit the stage to the target
	screen. Unfortunately, it's unclear from the JavaFX documentation
	whether screen bounds can change, or screen objects are effectively
	immutable. As a result, the screen bounds must be monitored directly for
	changes.
	*/
	private final SimpleObjectProperty<Rectangle2D> targetScreenBoundsProperty =
	    new SimpleObjectProperty<>();

	/*
				\\\\\\\\\\\\\\
				\ INITIALIZE \
				\\\\\\\\\\\\\\
	*/
	private void initializeTargetScreenProperty() {
		/*
		Add a change listener to the target screen property which
		updates the target screen bounds property.
		*/
		targetScreenProperty.addListener((
		    ObservableValue<? extends Screen> targetScreenObservableValue,
		    Screen targetScreenOldValue,
		    Screen targetScreenNewValue
		) -> {
			/*
			Check if the new value is null.
			*/
			if (targetScreenNewValue == null) {
				/*
				If so, set the target screen bounds property to
				null.
				*/
				targetScreenBoundsProperty.set(null);
			} else {
				/*
				Otherwise, update the target screen bounds
				property according to the new target screen.
				*/
				targetScreenBoundsProperty.set(
				    targetScreenNewValue.getBounds()
				);
			}
		});

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
			Check if the new value is null.
			*/
			if (targetScreenBoundsNewValue == null) {
				/*
				If so, reset the stage back to the origin with
				no area.
				*/
				stage.setX(0);
				stage.setY(0);
				stage.setWidth(0);
				stage.setHeight(0);
			} else {
				/*
				Otherwise, set the stage's position to match the
				new bounds.
				*/
				stage.setX(
				    targetScreenBoundsProperty.get().getMinX()
				);
				stage.setY(
				    targetScreenBoundsProperty.get().getMinY()
				);

				/*
				Set the stage's dimensions to match the new
				bounds.
				*/
				stage.setWidth(
				    targetScreenBoundsProperty.get().getWidth()
				);
				stage.setHeight(
				    targetScreenBoundsProperty.get().getHeight()
				);
			}
		});

		/*
		Set the default value of the target screen property to the
		primary screen. Because both change listeners were added above,
		this will propagate to the target screen bounds property and the
		stage.
		*/
		targetScreenProperty.set(Screen.getPrimary());
	}

	/*
				\\\\\\\
				\ GET \
				\\\\\\\
	*/
	/**
	 * @return	target screen property
	 */
	public ObjectProperty<Screen> targetScreenProperty() {
		return targetScreenProperty;
	}

	/*
			-----------
			| ENABLED |
			-----------
	*/
	public static final boolean DEFAULT_ENABLED = false;

	private final SimpleBooleanProperty enabledProperty =
	    new SimpleBooleanProperty(DEFAULT_ENABLED);

	/*
				\\\\\\\\\\\\\\
				\ INITIALIZE \
				\\\\\\\\\\\\\\
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
				\\\\\\\
				\ GET \
				\\\\\\\
	*/
	/**
	 * @return	enabled property
	 */
	public BooleanProperty enabledProperty() {
		return enabledProperty;
	}

	/*
			----------------------
			| GRID LINES VISIBLE |
			----------------------
	*/
	/*
				\\\\\\\
				\ GET \
				\\\\\\\
	*/
	public BooleanProperty gridLinesVisibleProperty() {
		return controller.gridLinesVisibleProperty();
	}

	/*
			---------
			| COLOR |
			---------
	*/
	/*
				\\\\\\\
				\ GET \
				\\\\\\\
	*/
	public ObjectProperty<Color> colorProperty() {
		return controller.colorProperty();
	}

	/*
			-----------------
			| CURSOR WINDOW |
			-----------------
	*/
	/*
				\\\\\\\\\\\\\\\\\\\\\\
				\ TRACKING FREQUENCY \
				\\\\\\\\\\\\\\\\\\\\\\
	*/
	/*
					///////
					/ GET /
					///////
	*/
	public DoubleProperty cursorWindowTrackingFrequencyProperty() {
		return controller.cursorWindowTrackingFrequencyProperty();
	}

	/*
				\\\\\\\\\
				\ WIDTH \
				\\\\\\\\\
	*/
	/*
					///////
					/ GET /
					///////
	*/
	public DoubleProperty cursorWindowWidthProperty() {
		return controller.cursorWindowWidthProperty();
	}

	/*
				\\\\\\\\\\
				\ HEIGHT \
				\\\\\\\\\\
	*/
	/*
					///////
					/ GET /
					///////
	*/
	public DoubleProperty cursorWindowHeightProperty() {
		return controller.cursorWindowHeightProperty();
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
	private final Stage stage = new Stage(
	    StageStyle.TRANSPARENT
	);

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
		stage.setFullScreenExitKeyCombination(
		    KeyCombination.NO_MATCH
		);
	}

	/*
			--------------
			| CONTROLLER |
			--------------
	*/
	private final OverlayViewController controller =
	    new OverlayViewController();

	/*
				\\\\\\\\\\\\\\
				\ INITIALIZE \
				\\\\\\\\\\\\\\
	*/
	private void initializeController() {
		/*
		Load the controller's FXML file.
		*/
		FXMLLoader overlayLoader = new FXMLLoader(
		    OverlayViewController.FXML_FILE_URL
		);
		overlayLoader.setRoot(new GridPane());
		overlayLoader.setController(controller);

		try {
			overlayLoader.load();
		} catch (IOException exception) {
			/*
			Print a descriptive error message. Eventually, the
			application should append to a log of some sort to aid
			in debugging outside of the IDE. For now, a simple error
			message on the console is enough.
			*/
			System.err.println(
			    "Failed to load controller FXML:\n" +
			    exception.getMessage()
			);

			/*
			Exit the JavaFX platform and return.
			*/
			Platform.exit();
			return;
		}

		/*
		Create a transparent scene containing the root.
		*/
		Scene scene = new Scene(
		    overlayLoader.getRoot()
		);
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
	public OverlayViewManager() {
		/*
		Initialize properties.
		*/
		initializeTargetScreenProperty();
		initializeEnabledProperty();

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
				changed.
				*/
				if (
				    targetScreenProperty.get().getBounds() !=
				    targetScreenBoundsProperty.get()
				) {
					/*
					If so, update the target screen bounds
					property to the new bounds, which should
					automatically propagate the new bounds
					to the stage.
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
}