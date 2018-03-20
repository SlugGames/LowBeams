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

import com.sluggames.software.LowBeams.resources.FXML.Overlay.OverlayController;
import com.sluggames.software.LowBeams.utility.DoubleToPixelLabelConverter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

/**
 * This class is the controller for the preferences menu FXML. It primarily
 * exposes the relevant controls, as there is not much internal logic required.
 *
 *
 * @author david.boeger@sluggames.com
 *
 * @version 0.2.0
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
				\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
				\ CURSOR WINDOW DIMENSION SLIDERS \
				\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	*/
	/*
					/////////
					/ WIDTH /
					/////////
	*/
	public static final int CURSOR_WINDOW_WIDTH_SLIDER_MAJOR_TICK_COUNT = 4;
	public static final int CURSOR_WINDOW_WIDTH_SLIDER_MINOR_TICK_COUNT =
	    CURSOR_WINDOW_WIDTH_SLIDER_MAJOR_TICK_COUNT - 2;

	@FXML
	private Slider cursorWindowWidthSlider;

	/*
						\\\\\\\\\
						\ LABEL \
						\\\\\\\\\
	*/
	@FXML
	private Label cursorWindowWidthSliderLabel;

	/*
						\\\\\\\\\\\\\\
						\ INITIALIZE \
						\\\\\\\\\\\\\\
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
		cursorWindowWidthSlider.setShowTickMarks(true);

		/*
		Set the slider's tick mark label attributes.
		*/
		cursorWindowWidthSlider.setLabelFormatter(
		    new DoubleToPixelLabelConverter()
		);
		cursorWindowWidthSlider.setShowTickLabels(true);
	}

	/*
						\\\\\\\
						\ GET \
						\\\\\\\
	*/
	public Slider getCursorWindowWidthSlider() {
		return cursorWindowWidthSlider;
	}

	/*
					//////////
					/ HEIGHT /
					//////////
	*/
	public static final int CURSOR_WINDOW_HEIGHT_SLIDER_MAJOR_TICK_COUNT =
	    CURSOR_WINDOW_WIDTH_SLIDER_MAJOR_TICK_COUNT;
	public static final int CURSOR_WINDOW_HEIGHT_SLIDER_MINOR_TICK_COUNT =
	    CURSOR_WINDOW_WIDTH_SLIDER_MINOR_TICK_COUNT;

	@FXML
	private Slider cursorWindowHeightSlider;

	/*
						\\\\\\\\\
						\ LABEL \
						\\\\\\\\\
	*/
	@FXML
	private Label cursorWindowHeightSliderLabel;

	/*
						\\\\\\\\\\\\\\
						\ INITIALIZE \
						\\\\\\\\\\\\\\
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
		cursorWindowHeightSlider.setShowTickMarks(true);

		/*
		Set the slider's tick mark label attributes.
		*/
		cursorWindowHeightSlider.setLabelFormatter(
		    new DoubleToPixelLabelConverter()
		);
		cursorWindowHeightSlider.setShowTickLabels(true);
	}

	/*
						\\\\\\\
						\ GET \
						\\\\\\\
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
		initializeCursorWindowWidthSlider();
		initializeCursorWindowHeightSlider();
	}
}