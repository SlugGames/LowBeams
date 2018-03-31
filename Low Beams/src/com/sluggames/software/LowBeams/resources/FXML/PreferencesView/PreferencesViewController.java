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
package com.sluggames.software.LowBeams.resources.FXML.PreferencesView;

import com.sluggames.software.LowBeams.OverlayViewManager;
import com.sluggames.software.LowBeams.resources.FXML.OverlayView.OverlayViewController;
import com.sluggames.software.LowBeams.utility.DoubleToHertzFrequencyLabelConverter;
import com.sluggames.software.LowBeams.utility.DoubleToPercentageLabelConverter;
import com.sluggames.software.LowBeams.utility.DoubleToPixelLabelConverter;
import com.sluggames.software.LowBeams.utility.ScreenToShortLabelConverter;
import java.net.URL;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

/**
 * This class is the FXML controller for the preferences view. It primarily
 * exposes the relevant controls, as there is not much internal logic required.
 * Currently, the only notable exception are the color controls. There are some
 * incompatibilities between the overlay functionality and the standard JavaFX
 * {@link javafx.scene.control.ColorPicker} control, so custom color controls
 * are used instead. The consolidation logic for these color controls is
 * implemented internally, and the resulting color is exposed as a property via
 * the {@link #getColorProperty()} method.
 *
 *
 * @author david.boeger@sluggames.com
 *
 * @version 0.10.0
 * @since 0.2.0
 */
public class PreferencesViewController {
	/*
		******************
		*** PROPERTIES ***
		******************
	*/
	/*
			---------
			| COLOR |
			---------
	*/
	private final SimpleObjectProperty<Color> colorProperty =
	    new SimpleObjectProperty<>(OverlayViewController.DEFAULT_COLOR);

	/*
				\\\\\\\\\\\\\\
				\ INITIALIZE \
				\\\\\\\\\\\\\\
	*/
	private void initializeColorProperty() {
		/*
		There is currently nothing to initialize here, but the method is
		provided as a guideline for future contributors.
		*/
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
	    PreferencesViewController.class.getResource(
	    "PreferencesView.fxml"
	);

	/*
			--------------
			| COMPONENTS |
			--------------
	*/
	/*
				\\\\\\\\\\\\\\\\\\\\\\\\\\\\
				\ TARGET SCREEN CHOICE BOX \
				\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	*/
	@FXML
	private ChoiceBox<Screen> targetScreenChoiceBox;

	/*
					/////////
					/ LABEL /
					/////////
	*/
	@FXML
	private Label targetScreenChoiceBoxLabel;

	/*
					//////////////
					/ INITIALIZE /
					//////////////
	*/
	private void initializeTargetScreenChoiceBox() {
		/*
		Associate with the corresponding label.
		*/
		targetScreenChoiceBoxLabel.setLabelFor(targetScreenChoiceBox);

		/*
		Set the target choice box's string converter to represent screen
		objects by a short label, which is designed to appear more
		user-friendly.
		*/
		targetScreenChoiceBox.setConverter(
		    new ScreenToShortLabelConverter()
		);

		/*
		Populate the target choice box with the list of available
		screens.
		*/
		targetScreenChoiceBox.setItems(Screen.getScreens());

		/*
		Set the default target screen to the primary screen.
		*/
		targetScreenChoiceBox.setValue(Screen.getPrimary());
	}

	/*
					///////
					/ GET /
					///////
	*/
	public ChoiceBox<Screen> getTargetScreenChoiceBox() {
		return targetScreenChoiceBox;
	}

	/*
				\\\\\\\\\\\\\\\\\\\\\
				\ ENABLED CHECK BOX \
				\\\\\\\\\\\\\\\\\\\\\
	*/
	@FXML
	private CheckBox enabledCheckBox;

	/*
					//////////////
					/ INITIALIZE /
					//////////////
	*/
	private void initializeEnabledCheckBox() {
		/*
		Set the selected value according to the default in the overlay
		manager.
		*/
		enabledCheckBox.setSelected(OverlayViewManager.DEFAULT_ENABLED
		);
	}

	/*
					///////
					/ GET /
					///////
	*/
	public CheckBox getEnabledCheckBox() {
		return enabledCheckBox;
	}

	/*
				\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
				\ GRID LINES VISIBLE CHECK BOX \
				\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	*/
	@FXML
	private CheckBox gridLinesVisibleCheckBox;

	/*
					//////////////
					/ INITIALIZE /
					//////////////
	*/
	private void initializeGridLinesVisibleCheckBox() {
		/*
		Set the selected value according to the default in the overlay
		controller.
		*/
		gridLinesVisibleCheckBox.setSelected(OverlayViewController.DEFAULT_GRID_LINES_VISIBLE
		);
	}

	/*
					///////
					/ GET /
					///////
	*/
	public CheckBox getGridLinesVisibleCheckBox() {
		return gridLinesVisibleCheckBox;
	}

	/*
				\\\\\\\\\
				\ COLOR \
				\\\\\\\\\
	*/
	/*
					///////////////
					/ RGB SLIDERS /
					///////////////
	*/
	/*
						\\\\\\\
						\ RED \
						\\\\\\\
	*/
	public static final int RED_SLIDER_MAJOR_TICK_COUNT = 4;
	public static final int RED_SLIDER_MINOR_TICK_COUNT =
	    RED_SLIDER_MAJOR_TICK_COUNT - 2;

	@FXML
	private Slider redSlider;

	/*
							/////////
							/ LABEL /
							/////////
	*/
	@FXML
	private Label redSliderLabel;

	/*
							//////////////
							/ INITIALIZE /
							//////////////
	*/
	private void initializeRedSlider() {
		/*
		Associate with the corresponding label.
		*/
		redSliderLabel.setLabelFor(redSlider);

		/*
		Set the range of the slider to match the acceptable range of
		values.
		*/
		redSlider.setMin(Color.BLACK.getRed());
		redSlider.setMax(Color.WHITE.getRed());

		/*
		Set the slider's value to match the default.
		*/
		redSlider.setValue(OverlayViewController.DEFAULT_COLOR.getRed()
		);

		/*
		Set the slider's tick mark attributes.
		*/
		redSlider.setMajorTickUnit(
		    (redSlider.getMax() - redSlider.getMin()) /
		    (RED_SLIDER_MAJOR_TICK_COUNT - 1)
		);
		redSlider.setMinorTickCount(
		    RED_SLIDER_MINOR_TICK_COUNT
		);

		/*
		Add a change listener to the slider which propagates changes to
		the color property.
		*/
		redSlider.valueProperty().addListener((
		    ObservableValue<? extends Number> redObservableValue,
		    Number redOldValue,
		    Number redNewValue
		) -> {
			/*
			Validate the new value.
			*/
			if (redNewValue == null) {
				throw new NullPointerException(
				    "redNewValue == null"
				);
			}

			/*
			Adjust the color property's red value to match the new
			value.
			*/
			colorProperty.set(new Color(
			    redNewValue.doubleValue(),
			    colorProperty.get().getGreen(),
			    colorProperty.get().getBlue(),
			    colorProperty.get().getOpacity()
			));
		});
	}

	/*
						\\\\\\\\\
						\ GREEN \
						\\\\\\\\\
	*/
	public static final int GREEN_SLIDER_MAJOR_TICK_COUNT =
	    RED_SLIDER_MAJOR_TICK_COUNT;
	public static final int GREEN_SLIDER_MINOR_TICK_COUNT =
	    GREEN_SLIDER_MAJOR_TICK_COUNT - 2;

	@FXML
	private Slider greenSlider;

	/*
							/////////
							/ LABEL /
							/////////
	*/
	@FXML
	private Label greenSliderLabel;

	/*
							//////////////
							/ INITIALIZE /
							//////////////
	*/
	private void initializeGreenSlider() {
		/*
		Associate with the corresponding label.
		*/
		greenSliderLabel.setLabelFor(greenSlider);

		/*
		Set the range of the slider to match the acceptable range of
		values.
		*/
		greenSlider.setMin(Color.BLACK.getRed());
		greenSlider.setMax(Color.WHITE.getRed());

		/*
		Set the slider's value to match the default.
		*/
		greenSlider.setValue(OverlayViewController.DEFAULT_COLOR.getGreen()
		);

		/*
		Set the slider's tick mark attributes.
		*/
		greenSlider.setMajorTickUnit(
		    (greenSlider.getMax() - greenSlider.getMin()) /
		    (GREEN_SLIDER_MAJOR_TICK_COUNT - 1)
		);
		greenSlider.setMinorTickCount(
		    GREEN_SLIDER_MINOR_TICK_COUNT
		);

		/*
		Add a change listener to the slider which propagates changes to
		the color property.
		*/
		greenSlider.valueProperty().addListener((
		    ObservableValue<? extends Number> greenObservableValue,
		    Number greenOldValue,
		    Number greenNewValue
		) -> {
			/*
			Validate the new value.
			*/
			if (greenNewValue == null) {
				throw new NullPointerException(
				    "greenNewValue == null"
				);
			}

			/*
			Adjust the color property's green value to match the new
			value.
			*/
			colorProperty.set(new Color(
			    colorProperty.get().getRed(),
			    greenNewValue.doubleValue(),
			    colorProperty.get().getBlue(),
			    colorProperty.get().getOpacity()
			));
		});
	}

	/*
						\\\\\\\\
						\ BLUE \
						\\\\\\\\
	*/
	public static final int BLUE_SLIDER_MAJOR_TICK_COUNT =
	    RED_SLIDER_MAJOR_TICK_COUNT;
	public static final int BLUE_SLIDER_MINOR_TICK_COUNT =
	    BLUE_SLIDER_MAJOR_TICK_COUNT - 2;

	@FXML
	private Slider blueSlider;

	/*
							/////////
							/ LABEL /
							/////////
	*/
	@FXML
	private Label blueSliderLabel;

	/*
							//////////////
							/ INITIALIZE /
							//////////////
	*/
	private void initializeBlueSlider() {
		/*
		Associate with the corresponding label.
		*/
		blueSliderLabel.setLabelFor(blueSlider);

		/*
		Set the range of the slider to match the acceptable range of
		values.
		*/
		blueSlider.setMin(Color.BLACK.getRed());
		blueSlider.setMax(Color.WHITE.getRed());

		/*
		Set the slider's value to match the default.
		*/
		blueSlider.setValue(OverlayViewController.DEFAULT_COLOR.getBlue()
		);

		/*
		Set the slider's tick mark attributes.
		*/
		blueSlider.setMajorTickUnit(
		    (blueSlider.getMax() - blueSlider.getMin()) /
		    (BLUE_SLIDER_MAJOR_TICK_COUNT - 1)
		);
		blueSlider.setMinorTickCount(
		    BLUE_SLIDER_MINOR_TICK_COUNT
		);

		/*
		Add a change listener to the slider which propagates changes to
		the color property.
		*/
		blueSlider.valueProperty().addListener((
		    ObservableValue<? extends Number> blueObservableValue,
		    Number blueOldValue,
		    Number blueNewValue
		) -> {
			/*
			Validate the new value.
			*/
			if (blueNewValue == null) {
				throw new NullPointerException(
				    "blueNewValue == null"
				);
			}

			/*
			Adjust the color property's blue value to match the new
			value.
			*/
			colorProperty.set(new Color(
			    colorProperty.get().getRed(),
			    colorProperty.get().getGreen(),
			    blueNewValue.doubleValue(),
			    colorProperty.get().getOpacity()
			));
		});
	}

	/*
					//////////////////
					/ OPACITY SLIDER /
					//////////////////
	*/
	public static final int OPACITY_SLIDER_MAJOR_TICK_COUNT = 4;
	public static final int OPACITY_SLIDER_MINOR_TICK_COUNT =
	    OPACITY_SLIDER_MAJOR_TICK_COUNT - 2;

	@FXML
	private Slider opacitySlider;

	/*
						\\\\\\\\\
						\ LABEL \
						\\\\\\\\\
	*/
	@FXML
	private Label opacitySliderLabel;

	/*
						\\\\\\\\\\\\\\
						\ INITIALIZE \
						\\\\\\\\\\\\\\
	*/
	private void initializeOpacitySlider() {
		/*
		Associate with the corresponding label.
		*/
		opacitySliderLabel.setLabelFor(
		    opacitySlider
		);

		/*
		Set the range of the slider to match the acceptable range of
		values.
		*/
		opacitySlider.setMin(OverlayViewController.MINIMUM_OPACITY
		);
		opacitySlider.setMax(OverlayViewController.MAXIMUM_OPACITY
		);

		/*
		Set the slider's value to match the default.
		*/
		opacitySlider.setValue(OverlayViewController.DEFAULT_OPACITY
		);

		/*
		Set the slider's tick mark attributes.
		*/
		opacitySlider.setMajorTickUnit(
		    (opacitySlider.getMax() - opacitySlider.getMin()) /
		    (OPACITY_SLIDER_MAJOR_TICK_COUNT - 1)
		);
		opacitySlider.setMinorTickCount(
		    OPACITY_SLIDER_MINOR_TICK_COUNT
		);

		/*
		Set the slider's tick mark label attributes.
		*/
		opacitySlider.setLabelFormatter(
		    new DoubleToPercentageLabelConverter()
		);

		/*
		Add a change listener to the slider which propagates changes to
		the color property.
		*/
		opacitySlider.valueProperty().addListener((
		    ObservableValue<? extends Number> opacityObservableValue,
		    Number opacityOldValue,
		    Number opacityNewValue
		) -> {
			/*
			Validate the new value.
			*/
			if (opacityNewValue == null) {
				throw new NullPointerException(
				    "opacityNewValue == null"
				);
			}

			/*
			Adjust the color property's opacity value to match the
			new value.
			*/
			colorProperty.set(new Color(
			    colorProperty.get().getRed(),
			    colorProperty.get().getGreen(),
			    colorProperty.get().getBlue(),
			    opacityNewValue.doubleValue()
			));
		});
	}

	/*
				\\\\\\\\\\\\\\\\\
				\ CURSOR WINDOW \
				\\\\\\\\\\\\\\\\\
	*/
	/*
					/////////////////////////////
					/ TRACKING FREQUENCY SLIDER /
					/////////////////////////////
	*/
	public static final int CURSOR_TRACKING_FREQUENCY_SLIDER_MAJOR_TICK_COUNT = 4;
	public static final int CURSOR_TRACKING_FREQUENCY_SLIDER_MINOR_TICK_COUNT =
	    CURSOR_TRACKING_FREQUENCY_SLIDER_MAJOR_TICK_COUNT - 2;

	@FXML
	private Slider cursorTrackingFrequencySlider;

	/*
						\\\\\\\\\
						\ LABEL \
						\\\\\\\\\
	*/
	@FXML
	private Label cursorTrackingFrequencySliderLabel;

	/*
						\\\\\\\\\\\\\\
						\ INITIALIZE \
						\\\\\\\\\\\\\\
	*/
	private void initializeCursorTrackingFrequencySlider() {
		/*
		Associate with the corresponding label.
		*/
		cursorTrackingFrequencySliderLabel.setLabelFor(
		    cursorTrackingFrequencySlider
		);

		/*
		Set the range of the slider to match the acceptable range of
		values.
		*/
		cursorTrackingFrequencySlider.setMin(OverlayViewController.MINIMUM_CURSOR_TRACKING_FREQUENCY
		);
		cursorTrackingFrequencySlider.setMax(OverlayViewController.MAXIMUM_CURSOR_TRACKING_FREQUENCY
		);

		/*
		Set the slider's value to match the default.
		*/
		cursorTrackingFrequencySlider.setValue(OverlayViewController.DEFAULT_CURSOR_TRACKING_FREQUENCY
		);

		/*
		Set the slider's tick mark attributes.
		*/
		cursorTrackingFrequencySlider.setMajorTickUnit(
		    (cursorTrackingFrequencySlider.getMax() -
		    cursorTrackingFrequencySlider.getMin()) /
		    (CURSOR_TRACKING_FREQUENCY_SLIDER_MAJOR_TICK_COUNT - 1)
		);
		cursorTrackingFrequencySlider.setMinorTickCount(
		    CURSOR_TRACKING_FREQUENCY_SLIDER_MINOR_TICK_COUNT
		);

		/*
		Set the slider's tick mark label attributes.
		*/
		cursorTrackingFrequencySlider.setLabelFormatter(
		    new DoubleToHertzFrequencyLabelConverter()
		);
	}

	/*
						\\\\\\\
						\ GET \
						\\\\\\\
	*/
	public Slider getCursorTrackingFrequencySlider() {
		return cursorTrackingFrequencySlider;
	}

	/*
					/////////////////////
					/ DIMENSION SLIDERS /
					/////////////////////
	*/
	/*
						\\\\\\\\\
						\ WIDTH \
						\\\\\\\\\
	*/
	public static final int CURSOR_WINDOW_WIDTH_SLIDER_MAJOR_TICK_COUNT = 4;
	public static final int CURSOR_WINDOW_WIDTH_SLIDER_MINOR_TICK_COUNT =
	    CURSOR_WINDOW_WIDTH_SLIDER_MAJOR_TICK_COUNT - 2;

	@FXML
	private Slider cursorWindowWidthSlider;

	/*
							/////////
							/ LABEL /
							/////////
	*/
	@FXML
	private Label cursorWindowWidthSliderLabel;

	/*
							//////////////
							/ INITIALIZE /
							//////////////
	*/
	private void initializeCursorWindowWidthSlider() {
		/*
		Associate with the corresponding label.
		*/
		cursorWindowWidthSliderLabel.setLabelFor(
		    cursorWindowWidthSlider
		);

		/*
		Set the range of the slider to match the acceptable range of
		values.
		*/
		cursorWindowWidthSlider.setMin(OverlayViewController.MINIMUM_CURSOR_WINDOW_WIDTH
		);
		cursorWindowWidthSlider.setMax(OverlayViewController.MAXIMUM_CURSOR_WINDOW_WIDTH
		);

		/*
		Set the slider's value to match the default.
		*/
		cursorWindowWidthSlider.setValue(OverlayViewController.DEFAULT_CURSOR_WINDOW_WIDTH
		);

		/*
		Set the slider's tick mark attributes.
		*/
		cursorWindowWidthSlider.setMajorTickUnit(
		    (cursorWindowWidthSlider.getMax() -
		    cursorWindowWidthSlider.getMin()) /
		    (CURSOR_WINDOW_WIDTH_SLIDER_MAJOR_TICK_COUNT - 1)
		);
		cursorWindowWidthSlider.setMinorTickCount(
		    CURSOR_WINDOW_WIDTH_SLIDER_MINOR_TICK_COUNT
		);

		/*
		Set the slider's tick mark label attributes.
		*/
		cursorWindowWidthSlider.setLabelFormatter(
		    new DoubleToPixelLabelConverter()
		);
	}

	/*
							///////
							/ GET /
							///////
	*/
	public Slider getCursorWindowWidthSlider() {
		return cursorWindowWidthSlider;
	}

	/*
						\\\\\\\\\\
						\ HEIGHT \
						\\\\\\\\\\
	*/
	public static final int CURSOR_WINDOW_HEIGHT_SLIDER_MAJOR_TICK_COUNT =
	    CURSOR_WINDOW_WIDTH_SLIDER_MAJOR_TICK_COUNT;
	public static final int CURSOR_WINDOW_HEIGHT_SLIDER_MINOR_TICK_COUNT =
	    CURSOR_WINDOW_WIDTH_SLIDER_MINOR_TICK_COUNT;

	@FXML
	private Slider cursorWindowHeightSlider;

	/*
							/////////
							/ LABEL /
							/////////
	*/
	@FXML
	private Label cursorWindowHeightSliderLabel;

	/*
							//////////////
							/ INITIALIZE /
							//////////////
	*/
	private void initializeCursorWindowHeightSlider() {
		/*
		Associate with the corresponding label.
		*/
		cursorWindowHeightSliderLabel.setLabelFor(
		    cursorWindowHeightSlider
		);

		/*
		Set the range of the slider to match the acceptable range of
		values.
		*/
		cursorWindowHeightSlider.setMin(OverlayViewController.MINIMUM_CURSOR_WINDOW_HEIGHT
		);
		cursorWindowHeightSlider.setMax(OverlayViewController.MAXIMUM_CURSOR_WINDOW_HEIGHT
		);

		/*
		Set the slider's value to match the default.
		*/
		cursorWindowHeightSlider.setValue(OverlayViewController.DEFAULT_CURSOR_WINDOW_HEIGHT
		);

		/*
		Set the slider's tick mark attributes.
		*/
		cursorWindowHeightSlider.setMajorTickUnit(
		    (cursorWindowHeightSlider.getMax() -
		    cursorWindowHeightSlider.getMin()) /
		    (CURSOR_WINDOW_HEIGHT_SLIDER_MAJOR_TICK_COUNT - 1)
		);
		cursorWindowHeightSlider.setMinorTickCount(
		    CURSOR_WINDOW_HEIGHT_SLIDER_MINOR_TICK_COUNT
		);

		/*
		Set the slider's tick mark label attributes.
		*/
		cursorWindowHeightSlider.setLabelFormatter(
		    new DoubleToPixelLabelConverter()
		);
	}

	/*
							///////
							/ GET /
							///////
	*/
	public Slider getCursorWindowHeightSlider() {
		return cursorWindowHeightSlider;
	}


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

		/*
		Initialize FXML components.
		*/
		initializeTargetScreenChoiceBox();
		initializeEnabledCheckBox();
		initializeGridLinesVisibleCheckBox();
		initializeRedSlider();
		initializeGreenSlider();
		initializeBlueSlider();
		initializeOpacitySlider();
		initializeCursorTrackingFrequencySlider();
		initializeCursorWindowWidthSlider();
		initializeCursorWindowHeightSlider();
	}
}