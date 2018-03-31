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
 * This class manages the overlay view. Although it can be instantiated prior to
 * launching the JavaFX platform, in order to use it, it must be initialized
 * exactly once by calling the {@link #initialize()} method from the JavaFX
 * application thread.
 *
 *
 * @author david.boeger@sluggames.com
 *
 * @version 0.10.0
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
	/**
	 * The stage initialization method must be called prior to this one so
	 * that the stage can be fitted to the default target screen bounds
	 * value.
	 *
	 *
	 * @throws IllegalStateException	Stage has not been initialized.
	 */
	private void initializeTargetScreenProperty() {
		/*
		Check if the stage has not been initialized.
		*/
		if (stage == null) {
			/*
			If it hasn't, throw an exception.
			*/
			throw new IllegalStateException(
			    "Stage has not been initialized."
			);
		}

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
	 *
	 * @throws IllegalStateException	Not yet initialized.
	 */
	public ObjectProperty<Screen> getTargetScreenProperty() {
		/*
		Check if the instance has yet to be initialized.
		*/
		if (!initialized) {
			/*
			If it hasn't, throw an exception.
			*/
			throw new IllegalStateException(
			    "Not yet initialized."
			);
		}

		return targetScreenProperty;
	}

	/*
			-----------
			| ENABLED |
			-----------
	*/
	public static final boolean DEFAULT_ENABLED = true;

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
	 *
	 * @throws IllegalStateException	Not yet initialized.
	 */
	public BooleanProperty getEnabledProperty() {
		/*
		Check if the instance has yet to be initialized.
		*/
		if (!initialized) {
			/*
			If it hasn't, throw an exception.
			*/
			throw new IllegalStateException(
			    "Not yet initialized."
			);
		}

		return enabledProperty;
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
	private Stage stage;

	/*
				\\\\\\\\\\\\\\
				\ INITIALIZE \
				\\\\\\\\\\\\\\
	*/
	private void initializeStage() {
		/*
		Create the transparent stage.
		*/
		stage = new Stage(StageStyle.TRANSPARENT);

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
		FXMLLoader overlayLoader = new FXMLLoader(
		    OverlayViewController.FXML_FILE_URL
		);
		overlayLoader.setRoot(new GridPane());
		overlayLoader.setController(controller);
		overlayLoader.load();

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
				\\\\\\\
				\ GET \
				\\\\\\\
	*/
	/**
	 * @return	controller
	 *
	 * @throws IllegalStateException	Not yet initialized.
	 */
	public OverlayViewController getController() {
		/*
		Check if the instance has yet to be initialized.
		*/
		if (!initialized) {
			/*
			If it hasn't, throw an exception.
			*/
			throw new IllegalStateException(
			    "Not yet initialized."
			);
		}

		return controller;
	}


	/*
		**********************
		*** INITIALIZATION ***
		**********************
	*/
	private boolean initialized;

	/*
			------
			| IS |
			------
	*/
	public boolean isInitialized() {
		return initialized;
	}

	/*
			--------------
			| INITIALIZE |
			--------------
	*/
	/**
	 * This method must be called from the JavaFX application thread, to
	 * allow access to JavaFX resources.
	 *
	 *
	 * @throws IllegalStateException	Already initialized, or not on
	 *					JavaFX application thread.
	 *
	 * @throws IOException		Failed to load controller FXML.
	 */
	public void initialize()
	    throws
	    IOException
	{
		/*
		Check if the instance has already been initialized.
		*/
		if (initialized) {
			/*
			If so, throw an exception.
			*/
			throw new IllegalStateException(
			    "Already initialized."
			);
		}

		/*
		Check if the caller is running on a thread other than the JavaFX
		application thread.
		*/
		if (!Platform.isFxApplicationThread()) {
			/*
			If so, throw an exception.
			*/
			throw new IllegalStateException(
			    "Not on JavaFX application thread."
			);
		}

		/*
		Initialize components. This is done before initializing
		properties because the target screen property depends on the
		stage having been initialized.
		*/
		initializeStage();
		initializeController();

		/*
		Initialize properties. This is done after initializing
		components because the target screen property depends on the
		stage having been initialized.
		*/
		initializeTargetScreenProperty();
		initializeEnabledProperty();

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

		/*
		Set to initialized.
		*/
		initialized = true;
	}
}