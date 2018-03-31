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
package com.sluggames.software.LowBeams.resources.FXML.OverlayView;

import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This class is the FXML controller for the overlay view. In addition to
 * exposing properties which are necessary for integration with the main
 * application logic, it also implements the cursor tracking logic internally.
 *
 *
 * @author david.boeger@sluggames.com
 *
 * @version 0.10.0
 * @since 0.1.0
 */
public class OverlayViewController {
	/*
		******************
		*** PROPERTIES ***
		******************
	*/
	/*
			----------------------
			| GRID LINES VISIBLE |
			----------------------

	This property is a bit unique in that it is already built into the grid
	pane. As there is no need to create a separate property, any
	initialization should be done as part of the grid pane's initialization.
	*/
	public static final boolean DEFAULT_GRID_LINES_VISIBLE = false;

	/*
				\\\\\\\
				\ GET \
				\\\\\\\
	*/
	public BooleanProperty getGridLinesVisibleProperty() {
		return gridPane.gridLinesVisibleProperty();
	}

	/*
			---------
			| COLOR |
			---------
	*/
	public static final Color DEFAULT_BASE_COLOR = Color.BLACK;

	public static final double MINIMUM_OPACITY = 0.3;
	public static final double MAXIMUM_OPACITY = 0.7;
	public static final double DEFAULT_OPACITY = 0.4;

	public static final Color DEFAULT_COLOR =
	    Color.TRANSPARENT.interpolate(
	    DEFAULT_BASE_COLOR,
	    DEFAULT_OPACITY
	);

	private final SimpleObjectProperty<Color> colorProperty =
	    new SimpleObjectProperty<>(DEFAULT_COLOR);

	/*
				\\\\\\\\\\\\\\
				\ INITIALIZE \
				\\\\\\\\\\\\\\
	*/
	private void initializeColorProperty() {
		/*
		Add a change listener to the color property which enforces the
		valid opacity range.
		*/
		colorProperty.addListener((
		    ObservableValue<? extends Color> colorObservableValue,
		    Color colorOldValue,
		    Color colorNewValue
		) -> {
			/*
			Validate the new value.
			*/
			if (colorNewValue == null) {
				throw new NullPointerException(
				    "colorNewValue == null"
				);
			}
			if (colorNewValue.getOpacity() < MINIMUM_OPACITY) {
				throw new IllegalArgumentException(
				    "colorNewValue.getOpacity() (" + colorNewValue.getOpacity() + ")" +
				    " < " +
				    "MINIMUM_OPACITY (" + MINIMUM_OPACITY + ")"
				);
			}
			if (colorNewValue.getOpacity() > MAXIMUM_OPACITY) {
				throw new IllegalArgumentException(
				    "colorNewValue.getOpacity() (" + colorNewValue.getOpacity() + ")" +
				    " > " +
				    "MAXIMUM_OPACITY (" + MAXIMUM_OPACITY + ")"
				);
			}
		});
	}

	/*
				\\\\\\\
				\ GET \
				\\\\\\\
	*/
	public ObjectProperty<Color> getColorProperty() {
		return colorProperty;
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
	public static final double MINIMUM_CURSOR_TRACKING_FREQUENCY = 15;
	public static final double MAXIMUM_CURSOR_TRACKING_FREQUENCY = 60;
	public static final double DEFAULT_CURSOR_TRACKING_FREQUENCY = 30;

	private final SimpleDoubleProperty cursorTrackingFrequencyProperty =
	    new SimpleDoubleProperty(DEFAULT_CURSOR_TRACKING_FREQUENCY);

	/*
					//////////////
					/ INITIALIZE /
					//////////////
	*/
	private void initializeCursorTrackingFrequencyProperty() {
		/*
		Add a change listener to the cursor tracking frequency property
		which enforces the acceptable range.
		*/
		cursorTrackingFrequencyProperty.addListener((
		    ObservableValue<? extends Number> cursorTrackingFrequencyObservableValue,
		    Number cursorTrackingFrequencyOldValue,
		    Number cursorTrackingFrequencyNewValue
		) -> {
			/*
			Validate the new value.
			*/
			if (cursorTrackingFrequencyNewValue == null) {
				throw new NullPointerException(
				    "cursorTrackingFrequencyNewValue == null"
				);
			}
			if (cursorTrackingFrequencyNewValue.doubleValue() < MINIMUM_CURSOR_TRACKING_FREQUENCY) {
				throw new IllegalArgumentException(
				    "cursorTrackingFrequencyNewValue.doubleValue() (" + cursorTrackingFrequencyNewValue.doubleValue() + ")" +
				    " < " +
				    "MINIMUM_CURSOR_TRACKING_FREQUENCY (" + MINIMUM_CURSOR_TRACKING_FREQUENCY + ")"
				);
			}
			if (cursorTrackingFrequencyNewValue.doubleValue() > MAXIMUM_CURSOR_TRACKING_FREQUENCY) {
				throw new IllegalArgumentException(
				    "cursorTrackingFrequencyNewValue.doubleValue() (" + cursorTrackingFrequencyNewValue.doubleValue() + ")" +
				    " > " +
				    "MAXIMUM_CURSOR_TRACKING_FREQUENCY (" + MAXIMUM_CURSOR_TRACKING_FREQUENCY + ")"
				);
			}
		});

		/*
		Start a new animation timer, which is responsible for updating
		the tracked cursor coordinates according to the cursor tracking
		frequency.
		*/
		new AnimationTimer() {
			/*
			........................
			... ACCUMULATED TIME ...
			........................
			*/
			private Duration accumulatedTime = Duration.ZERO;

			/*
				...........................
				... PREVIOUS PULSE TIME ...
				...........................
			*/
			private Instant previousPulseTime = Instant.now();


			/*
			..............
			... HANDLE ...
			..............
			*/
			@Override
			public void handle(long ignoredTime) {
				/*
				Calculdate the time elapsed between pulses.
				*/
				Instant currentPulseTime = Instant.now();
				Duration elapsedTime = Duration.between(
				    previousPulseTime,
				    currentPulseTime
				);
				previousPulseTime = currentPulseTime;

				/*
				Accumulate the elapsed time.
				*/
				accumulatedTime =
				    accumulatedTime.plus(
				    elapsedTime
				);

				/*
				Derive the cursor tracking time step from the
				cursor tracking frequency.
				*/
				Duration cursorTrackingTimeStep =
				    Duration.ofSeconds(1).dividedBy(
				    cursorTrackingFrequencyProperty.getValue().longValue()
				);

				/*
				Check if the time accumulated exceeds the cursor
				tracking time step.
				*/
				if (accumulatedTime.compareTo(cursorTrackingTimeStep) >= 0) {
					/*
					If so, update the tracked cursor
					coordinates.
					*/
					trackedCursorXProperty.set(latestCursorX);
					trackedCursorYProperty.set(latestCursorY);

					/*
					Repeatedly consume accumulated time in
					chunks equal to the cursor tracking time
					step, until left with the remainder.
					This could technically be achieved as a
					constant-time division operation, but
					subtraction preserves the precision of
					integer arithmetic, and is effectively
					bounded above by the maximum cursor
					tracking frequency.
					*/
					while (accumulatedTime.compareTo(cursorTrackingTimeStep) >= 0) {
						accumulatedTime =
						    accumulatedTime.minus(
						    cursorTrackingTimeStep
						);
					}
				}
			}
		}.start();
	}

	/*
					///////
					/ GET /
					///////
	*/
	public DoubleProperty getCursorTrackingFrequencyProperty() {
		return cursorTrackingFrequencyProperty;
	}

	/*
				\\\\\\\\\\\\\\
				\ DIMENSIONS \
				\\\\\\\\\\\\\\
	*/
	/*
					/////////
					/ WIDTH /
					/////////
	*/
	public static final double MINIMUM_CURSOR_WINDOW_WIDTH = 16;
	public static final double MAXIMUM_CURSOR_WINDOW_WIDTH = 128;
	public static final double DEFAULT_CURSOR_WINDOW_WIDTH = 64;

	private final SimpleDoubleProperty cursorWindowWidthProperty =
	    new SimpleDoubleProperty(DEFAULT_CURSOR_WINDOW_WIDTH);

	/*
						\\\\\\\\\\\\\\
						\ INITIALIZE \
						\\\\\\\\\\\\\\
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
			Validate the new value.
			*/
			if (cursorWindowWidthNewValue == null) {
				throw new NullPointerException(
				    "cursorWindowWidthNewValue == null"
				);
			}
			if (cursorWindowWidthNewValue.doubleValue() < MINIMUM_CURSOR_WINDOW_WIDTH) {
				throw new IllegalArgumentException(
				    "cursorWindowWidthNewValue.doubleValue() (" + cursorWindowWidthNewValue.doubleValue() + ")" +
				    " < " +
				    "MINIMUM_CURSOR_WINDOW_WIDTH (" + MINIMUM_CURSOR_WINDOW_WIDTH + ")"
				);
			}
			if (cursorWindowWidthNewValue.doubleValue() > MAXIMUM_CURSOR_WINDOW_WIDTH) {
				throw new IllegalArgumentException(
				    "cursorWindowWidthNewValue.doubleValue() (" + cursorWindowWidthNewValue.doubleValue() + ")" +
				    " > " +
				    "MAXIMUM_CURSOR_WINDOW_WIDTH (" + MAXIMUM_CURSOR_WINDOW_WIDTH + ")"
				);
			}
		});
	}

	/*
						\\\\\\\
						\ GET \
						\\\\\\\
	*/
	public DoubleProperty getCursorWindowWidthProperty() {
		return cursorWindowWidthProperty;
	}

	/*
					//////////
					/ HEIGHT /
					//////////
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
						\\\\\\\\\\\\\\
						\ INITIALIZE \
						\\\\\\\\\\\\\\
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
			Validate the new value.
			*/
			if (cursorWindowHeightNewValue == null) {
				throw new NullPointerException(
				    "cursorWindowHeightNewValue == null"
				);
			}
			if (cursorWindowHeightNewValue.doubleValue() < MINIMUM_CURSOR_WINDOW_HEIGHT) {
				throw new IllegalArgumentException(
				    "cursorWindowHeightNewValue.doubleValue() (" + cursorWindowHeightNewValue.doubleValue() + ")" +
				    " < " +
				    "MINIMUM_CURSOR_WINDOW_HEIGHT (" + MINIMUM_CURSOR_WINDOW_HEIGHT + ")"
				);
			}
			if (cursorWindowHeightNewValue.doubleValue() > MAXIMUM_CURSOR_WINDOW_HEIGHT) {
				throw new IllegalArgumentException(
				    "cursorWindowHeightNewValue.doubleValue() (" + cursorWindowHeightNewValue.doubleValue() + ")" +
				    " > " +
				    "MAXIMUM_CURSOR_WINDOW_HEIGHT (" + MAXIMUM_CURSOR_WINDOW_HEIGHT + ")"
				);
			}
		});
	}

	/*
						\\\\\\\
						\ GET \
						\\\\\\\
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
	    OverlayViewController.class.getResource(
	    "OverlayView.fxml"
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
		Set the grid lines visible property to match the default value.
		*/
		gridPane.setGridLinesVisible(
		    DEFAULT_GRID_LINES_VISIBLE
		);

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
				Bind the fill property of each rectangle to the
				color property.
				*/
				rectangle[column][row].fillProperty().bind(
				    colorProperty
				);

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
		******************
		*** INITIALIZE ***
		******************
	*/
	@FXML
	public void initialize() {
		/*
		Initialize properties.
		*/
		initializeColorProperty();
		initializeCursorTrackingFrequencyProperty();
		initializeCursorWindowWidthProperty();
		initializeCursorWindowHeightProperty();

		/*
		Initialize FXML components.
		*/
		initializeGridPane();
		initializeRectangles();
	}
}