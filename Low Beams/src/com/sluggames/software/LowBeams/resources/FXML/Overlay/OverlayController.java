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
package com.sluggames.software.LowBeams.resources.FXML.Overlay;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This class is the controller for the overlay FXML. In addition to exposing
 * properties which are necessary for integration with the main application
 * logic, it also implements the cursor tracking logic internally.
 *
 * @author david.boeger@sluggames.com
 *
 * @version 0.1.0
 * @since 0.1.0
 */
public class OverlayController implements Initializable {
	/*
		**************************
		*** EXPOSED PROPERTIES ***
		**************************
	*/
	/*
			----------------------------
			| CURSOR WINDOW DIMENSIONS |
			----------------------------
	*/
	/*
				\\\\\\\\\
				\ WIDTH \
				\\\\\\\\\
	*/
	public static final double MINIMUM_CURSOR_WINDOW_WIDTH = 16;
	public static final double MAXIMUM_CURSOR_WINDOW_WIDTH = 128;
	public static final double DEFAULT_CURSOR_WINDOW_WIDTH = 32;

	private final SimpleDoubleProperty cursorWindowWidthProperty =
	    new SimpleDoubleProperty(DEFAULT_CURSOR_WINDOW_WIDTH);

	/*
					//////////////
					/ INITIALIZE /
					//////////////
	*/
	private void initializeCursorWindowWidthProperty() {
		/*
		Add a change listener to the cursor window width property which
		enforces the acceptable range.
		*/
		cursorWindowWidthProperty.addListener((
		    ObservableValue<? extends Number> cursorWindowWidthObservableValue,
		    Number cursorWindowWidthOldValue,
		    Number cursorWindowWidthNewValue
		) -> {
			/*
			Get the double value of the new cursor window width for
			convenience.
			*/
			double cursorWindowWidthDoubleValue =
			    cursorWindowWidthNewValue.doubleValue();

			/*
			Verify that the new cursor window width is within the
			acceptable range.
			*/
			if (cursorWindowWidthDoubleValue < MINIMUM_CURSOR_WINDOW_WIDTH) {
				throw new IllegalStateException(
				    "cursorWindowWidthDoubleValue (" + cursorWindowWidthDoubleValue + ")" +
				    " < " +
				    "MINIMUM_CURSOR_WINDOW_WIDTH (" + MINIMUM_CURSOR_WINDOW_WIDTH + ")"
				);
			}
			if (cursorWindowWidthDoubleValue > MAXIMUM_CURSOR_WINDOW_WIDTH) {
				throw new IllegalStateException(
				    "cursorWindowWidthDoubleValue (" + cursorWindowWidthDoubleValue + ")" +
				    " > " +
				    "MAXIMUM_CURSOR_WINDOW_WIDTH (" + MAXIMUM_CURSOR_WINDOW_WIDTH + ")"
				);
			}
		});
	}

	/*
					///////
					/ GET /
					///////
	*/
	public DoubleProperty getCursorWindowWidthProperty() {
		return cursorWindowWidthProperty;
	}

	/*
				\\\\\\\\\\
				\ HEIGHT \
				\\\\\\\\\\
	*/
	public static final double MINIMUM_CURSOR_WINDOW_HEIGHT =
	    MINIMUM_CURSOR_WINDOW_WIDTH;
	public static final double MAXIMUM_CURSOR_WINDOW_HEIGHT =
	    MAXIMUM_CURSOR_WINDOW_WIDTH;
	public static final double DEFAULT_CURSOR_WINDOW_HEIGHT =
	    DEFAULT_CURSOR_WINDOW_WIDTH;

	private final SimpleDoubleProperty cursorWindowHeightProperty =
	    new SimpleDoubleProperty(DEFAULT_CURSOR_WINDOW_HEIGHT);

	/*
					//////////////
					/ INITIALIZE /
					//////////////
	*/
	private void initializeCursorWindowHeightProperty() {
		/*
		Add a change listener to the cursor window height property which
		enforces the acceptable range.
		*/
		cursorWindowHeightProperty.addListener((
		    ObservableValue<? extends Number> cursorWindowHeightObservableValue,
		    Number cursorWindowHeightOldValue,
		    Number cursorWindowHeightNewValue
		) -> {
			/*
			Get the double value of the new cursor window height for
			convenience.
			*/
			double cursorWindowHeightDoubleValue =
			    cursorWindowHeightNewValue.doubleValue();

			/*
			Verify that the new cursor window height is within the
			acceptable range.
			*/
			if (cursorWindowHeightDoubleValue < MINIMUM_CURSOR_WINDOW_HEIGHT) {
				throw new IllegalStateException(
				    "cursorWindowHeightDoubleValue (" + cursorWindowHeightDoubleValue + ")" +
				    " < " +
				    "MINIMUM_CURSOR_WINDOW_HEIGHT (" + MINIMUM_CURSOR_WINDOW_HEIGHT + ")"
				);
			}
			if (cursorWindowHeightDoubleValue > MAXIMUM_CURSOR_WINDOW_HEIGHT) {
				throw new IllegalStateException(
				    "cursorWindowHeightDoubleValue (" + cursorWindowHeightDoubleValue + ")" +
				    " > " +
				    "MAXIMUM_CURSOR_WINDOW_HEIGHT (" + MAXIMUM_CURSOR_WINDOW_HEIGHT + ")"
				);
			}
		});
	}

	/*
					///////
					/ GET /
					///////
	*/
	public DoubleProperty getCursorWindowHeightProperty() {
		return cursorWindowHeightProperty;
	}


	/*
		*************
		*** FXML  ***
		*************
	*/
	/*
			------------
			| FILE URL |
			------------
	*/
	public static final URL FXML_FILE_URL =
	    OverlayController.class.getResource(
	    "Overlay.fxml"
	);

	/*
			--------------
			| COMPONENTS |
			--------------
	*/
	/*
				\\\\\\\\\\\\\
				\ GRID PANE \
				\\\\\\\\\\\\\
	*/
	@FXML
	private GridPane gridPane;

	/*
					//////////////
					/ INITIALIZE /
					//////////////
	*/
	private void initializeGridPane() {
		/*
		Set a mouse event listener which tracks the latest cursor
		coordinates any time the mouse cursor is moved.
		*/
		gridPane.setOnMouseMoved((
		    MouseEvent mouseEvent
		) -> {
			latestCursorX = mouseEvent.getSceneX();
			latestCursorY = mouseEvent.getSceneY();
		});
	}

	/*
				\\\\\\\\\\\\\\
				\ RECTANGLES \
				\\\\\\\\\\\\\\
	*/
	@FXML
	private Rectangle topLeftRectangle;
	@FXML
	private Rectangle centerLeftRectangle;
	@FXML
	private Rectangle bottomLeftRectangle;
	@FXML
	private Rectangle topCenterRectangle;
	@FXML
	private Rectangle bottomCenterRectangle;
	@FXML
	private Rectangle topRightRectangle;
	@FXML
	private Rectangle centerRightRectangle;
	@FXML
	private Rectangle bottomRightRectangle;

	/*
					//////////////
					/ INITIALIZE /
					//////////////
	*/
	private void initializeRectangles() {
		/*
		..........................
		... DIMENSION BINDINGS ...
		..........................
		*/
		/*
			....................
			... COLUMN WIDTH ...
			....................
		*/
		ArrayList<ObservableValue<? extends Number>> columnWidthBinding =
		    new ArrayList<>();

		/*
				............
				... LEFT ...
				............
		*/
		columnWidthBinding.add(
		    trackedCursorXProperty.subtract(
		    cursorWindowWidthProperty.divide(2)
		));

		/*
				..............
				... CENTER ...
				..............
		*/
		columnWidthBinding.add(
		    cursorWindowWidthProperty
		);

		/*
				.............
				... RIGHT ...
				.............
		*/
		columnWidthBinding.add(
		    gridPane.widthProperty().subtract(
		    trackedCursorXProperty.add(
		    cursorWindowWidthProperty.divide(2)
		)));

		/*
			..................
			... ROW HEIGHT ...
			..................
		*/
		ArrayList<ObservableValue<? extends Number>> rowHeightBinding =
		    new ArrayList<>();

		/*
				...........
				... TOP ...
				...........
		*/
		rowHeightBinding.add(
		    trackedCursorYProperty.subtract(
		    cursorWindowHeightProperty.divide(2)
		));

		/*
				..............
				... CENTER ...
				..............
		*/
		rowHeightBinding.add(
		    cursorWindowHeightProperty
		);

		/*
				..............
				... BOTTOM ...
				..............
		*/
		rowHeightBinding.add(
		    gridPane.heightProperty().subtract(
		    trackedCursorYProperty.add(
		    cursorWindowHeightProperty.divide(2)
		)));

		/*
		Create a convenience array of rectangles, which can be
		referenced by column and row, modeled after the grid pane.
		*/
		Rectangle[][] rectangle = {
		    {topLeftRectangle, centerLeftRectangle, bottomLeftRectangle},
		    {topCenterRectangle, null, bottomCenterRectangle},
		    {topRightRectangle, centerRightRectangle, bottomRightRectangle}
		};

		/*
		Iterate through each of the rectangles.
		*/
		for (int column = 0; column < 3; column++) {
			for (int row = 0; row < 3; row++) {
				/*
				Skip the center grid cell, as it is the empty
				cursor window containing no rectangle.
				*/
				if (column == 1 && row == 1) {
					continue;
				}

				/*
				Set the color of each rectangle to a
				semi-transparent black color. Eventually, the
				fill property should be bound to an exposed
				color property, which can be adjusted by the
				user.
				*/
				rectangle[column][row].setFill(
				    Color.TRANSPARENT.interpolate(
				    Color.BLACK,
				    0.5
				));

				/*
				Bind the dimensions of the rectangle, using the
				corresponding column width and row height
				bindings established earlier.
				*/
				rectangle[column][row].widthProperty().bind(
				    columnWidthBinding.get(column)
				);
				rectangle[column][row].heightProperty().bind(
				    rowHeightBinding.get(row)
				);
			}
		}
	}


	/*
		**************************
		*** CURSOR COORDINATES ***
		**************************
	*/
	/*
			----------
			| LATEST |
			----------

	These latest cursor coordinates are updated by the grid pane on each and
	every mouse move event, so they represent the most recent available
	coordinates. However, they should not be used directly for things like
	rectangle dimension bindings, because the high rate of change would
	result in excessive CPU/GPU utilization. Instead, such calculations
	should use the tracked coordinate properties below, which are
	synchronized with the latest coordinates at a more controlled frequency.
	*/
	/*
				\\\\\
				\ X \
				\\\\\
	*/
	private double latestCursorX;

	/*
				\\\\\
				\ Y \
				\\\\\
	*/
	private double latestCursorY;

	/*
			-----------
			| TRACKED |
			-----------

	In addition to being synchronized with the latest cursor coordinates at
	a more controlled frequency, these tracked cursor coordinate properties
	enable convenient operations like binding, making them more suitable for
	use in more expensive operations.
	*/
	/*
				\\\\\
				\ X \
				\\\\\
	*/
	private final SimpleDoubleProperty trackedCursorXProperty =
	    new SimpleDoubleProperty();

	/*
				\\\\\
				\ Y \
				\\\\\
	*/
	private final SimpleDoubleProperty trackedCursorYProperty =
	    new SimpleDoubleProperty();


	/*
		********************
		*** CONSTRUCTION ***
		********************
	*/
	public OverlayController() {
		/*
		Start a new animation timer, which fires once per JavaFX pulse.
		*/
		new AnimationTimer() {
			@Override
			public void handle(long ignoredTime) {
				/*
				Because the JavaFX pulse rate is typically
				capped at a frequency of 60 Hz, this is an
				appropriate place to synchronize the tracked
				cursor coordinate properties with the latest
				available coordinates.
				*/
				trackedCursorXProperty.set(latestCursorX);
				trackedCursorYProperty.set(latestCursorY);
			}
		}.start();
	}


	/*
		******************
		*** INITIALIZE ***
		******************
	*/
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		/*
		Initialize properties.
		*/
		initializeCursorWindowWidthProperty();
		initializeCursorWindowHeightProperty();

		/*
		Initialize FXML components.
		*/
		initializeGridPane();
		initializeRectangles();
	}
}