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
package com.sluggames.software.LowBeams.resources.FXML.PreferencesMenu;

import com.sluggames.software.LowBeams.OverlayManager;
import com.sluggames.software.LowBeams.resources.FXML.Overlay.OverlayController;
import com.sluggames.software.LowBeams.utility.DoubleToHertzFrequencyLabelConverter;
import com.sluggames.software.LowBeams.utility.DoubleToPercentageLabelConverter;
import com.sluggames.software.LowBeams.utility.DoubleToPixelLabelConverter;
import com.sluggames.software.LowBeams.utility.NamedColor;
import com.sluggames.software.LowBeams.utility.ScreenToShortLabelConverter;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Screen;

/**
 * This class is the controller for the preferences menu FXML. It primarily
 * exposes the relevant controls, as there is not much internal logic required.
 *
 *
 * @author david.boeger@sluggames.com
 *
 * @version 0.8.0
 * @since 0.2.0
 */
public class PreferencesMenuController implements Initializable {
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
	    PreferencesMenuController.class.getResource(
	    "PreferencesMenu.fxml"
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
		enabledCheckBox.setSelected(
		    OverlayManager.DEFAULT_ENABLED
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
		gridLinesVisibleCheckBox.setSelected(
		    OverlayController.DEFAULT_GRID_LINES_VISIBLE
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
					///////////////////////////////
					/ BASE NAMED COLOR CHOICE BOX /
					///////////////////////////////
	*/
	@FXML
	private ChoiceBox<NamedColor> baseColorChoiceBox;

	/*
						\\\\\\\\\
						\ LABEL \
						\\\\\\\\\
	*/
	@FXML
	private Label baseColorChoiceBoxLabel;

	/*
						\\\\\\\\\\\\\\
						\ INITIALIZE \
						\\\\\\\\\\\\\\
	*/
	private void initializeBaseColorChoiceBox() {
		/*
		Associate with the corresponding label.
		*/
		baseColorChoiceBoxLabel.setLabelFor(baseColorChoiceBox);

		/*
		Populate the base color choice box with all the named colors,
		except for transparent, as transparency will be controlled by
		the opacity slider.
		*/
		baseColorChoiceBox.getItems().addAll(
		    Arrays.asList(NamedColor.values())
		);
		baseColorChoiceBox.getItems().remove(
		    NamedColor.TRANSPARENT
		);

		/*
		Set the default base color according to the default in the
		overlay controller.
		*/
		baseColorChoiceBox.setValue(
		    OverlayController.DEFAULT_BASE_COLOR
		);
	}

	/*
						\\\\\\\
						\ GET \
						\\\\\\\
	*/
	public ChoiceBox<NamedColor> getBaseColorChoiceBox() {
		return baseColorChoiceBox;
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
		opacitySlider.setMin(
		    OverlayController.MINIMUM_OPACITY
		);
		opacitySlider.setMax(
		    OverlayController.MAXIMUM_OPACITY
		);

		/*
		Set the slider's value to match the default.
		*/
		opacitySlider.setValue(
		    OverlayController.DEFAULT_OPACITY
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
	}

	/*
						\\\\\\\
						\ GET \
						\\\\\\\
	*/
	public Slider getOpacitySlider() {
		return opacitySlider;
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
		cursorTrackingFrequencySlider.setMin(
		    OverlayController.MINIMUM_CURSOR_TRACKING_FREQUENCY
		);
		cursorTrackingFrequencySlider.setMax(
		    OverlayController.MAXIMUM_CURSOR_TRACKING_FREQUENCY
		);

		/*
		Set the slider's value to match the default.
		*/
		cursorTrackingFrequencySlider.setValue(
		    OverlayController.DEFAULT_CURSOR_TRACKING_FREQUENCY
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
		cursorWindowWidthSlider.setMin(
		    OverlayController.MINIMUM_CURSOR_WINDOW_WIDTH
		);
		cursorWindowWidthSlider.setMax(
		    OverlayController.MAXIMUM_CURSOR_WINDOW_WIDTH
		);

		/*
		Set the slider's value to match the default.
		*/
		cursorWindowWidthSlider.setValue(
		    OverlayController.DEFAULT_CURSOR_WINDOW_WIDTH
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
		cursorWindowHeightSlider.setMin(
		    OverlayController.MINIMUM_CURSOR_WINDOW_HEIGHT
		);
		cursorWindowHeightSlider.setMax(
		    OverlayController.MAXIMUM_CURSOR_WINDOW_HEIGHT
		);

		/*
		Set the slider's value to match the default.
		*/
		cursorWindowHeightSlider.setValue(
		    OverlayController.DEFAULT_CURSOR_WINDOW_HEIGHT
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
	@Override
	public void initialize(
	    URL url,
	    ResourceBundle resourceBundle
	) {
		/*
		Initialize FXML components.
		*/
		initializeTargetScreenChoiceBox();
		initializeEnabledCheckBox();
		initializeGridLinesVisibleCheckBox();
		initializeBaseColorChoiceBox();
		initializeOpacitySlider();
		initializeCursorTrackingFrequencySlider();
		initializeCursorWindowWidthSlider();
		initializeCursorWindowHeightSlider();
	}
}