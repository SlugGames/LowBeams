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
import com.sluggames.software.LowBeams.utility.ColorToRGBALabelConverter;
import com.sluggames.software.LowBeams.utility.DoubleToHertzFrequencyLabelConverter;
import com.sluggames.software.LowBeams.utility.DoubleToPercentageLabelConverter;
import com.sluggames.software.LowBeams.utility.DoubleToPixelLabelConverter;
import java.net.URL;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

/**
 * This class is the FXML controller for the preferences view. It implements the
 * logic for creating and controlling arbitrary numbers of
 * {@link com.sluggames.software.LowBeams.OverlayViewManager} instances.
 *
 *
 * @author david.boeger@sluggames.com
 *
 * @version 0.12.0
 * @since 0.2.0
 */
public class PreferencesViewController {
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
				\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
				\ OVERLAY VIEW MANAGER TABLE VIEW \
				\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	*/
	@FXML
	private TableView<OverlayViewManager> overlayViewManagerTableView;

	/*
					/////////
					/ LABEL /
					/////////
	*/
	@FXML
	private Label overlayViewManagerTableViewLabel;

	/*
					///////////
					/ CONTEXT /
					///////////
	*/
	private OverlayViewManager contextOverlayViewManager;

	/*
					//////////////
					/ INITIALIZE /
					//////////////
	*/
	private void initializeOverlayViewManagerTableView() {
		/*
		Associate with the corresponding label.
		*/
		overlayViewManagerTableViewLabel.setLabelFor(
		    overlayViewManagerTableView
		);

		/*
		Add a change listener to the overlay view manager table view's
		selection model which switches the control context between
		individual overlay view managers as they are selected.
		*/
		overlayViewManagerTableView.getSelectionModel().selectedItemProperty().addListener((
		    ObservableValue<? extends OverlayViewManager> overlayViewManagerObservableValue,
		    OverlayViewManager overlayViewManagerOldValue,
		    OverlayViewManager overlayViewManagerNewValue
		) -> {
			/*
			Switch context to the newly selected overlay view
			manager.
			*/
			setContext(overlayViewManagerNewValue);
		});
	}

	/*
				\\\\\\\\\\\\\\\\\
				\ TABLE COLUMNS \
				\\\\\\\\\\\\\\\\\
	*/
	/*
					/////////////////
					/ TARGET SCREEN /
					/////////////////
	*/
	@FXML
	private TableColumn<OverlayViewManager, Screen> targetScreenTableColumn;

	/*
						\\\\\\\\\\\\\\
						\ INITIALIZE \
						\\\\\\\\\\\\\\
	*/
	private void initializeTargetScreenTableColumn() {
		/*
		Set the column's cell value factory.
		*/
		//targetScreenTableColumn.setCellValueFactory(
		//    new PropertyValueFactory<>("targetScreen")
		//);

		/*
		Set the column's cell factory.
		*/
		//targetScreenTableColumn.setCellFactory(
		//    ChoiceBoxTableCell.forTableColumn(Screen.getScreens())
		//);

		/*
		Set the target choice box's string converter to represent screen
		objects by a short label, which is designed to appear more
		user-friendly.
		*/
		//targetScreenChoiceBox.setConverter(
		//    new ScreenToShortLabelConverter()
		//);

		/*
		Populate the target choice box with the list of available
		screens.
		*/
		//targetScreenChoiceBox.setItems(Screen.getScreens());

		/*
		Set the default target screen to the primary screen.
		*/
		//targetScreenChoiceBox.setValue(Screen.getPrimary());
	}

	/*
					///////////
					/ ENABLED /
					///////////
	*/
	@FXML
	private TableColumn<OverlayViewManager, Boolean> enabledTableColumn;

	/*
						\\\\\\\\\\\\\\
						\ INITIALIZE \
						\\\\\\\\\\\\\\
	*/
	private void initializeEnabledTableColumn() {
		/*
		Set the column's cell value factory.
		*/
		enabledTableColumn.setCellValueFactory(
		    new PropertyValueFactory<>("enabled")
		);

		/*
		Set the column's cell factory.
		*/
		enabledTableColumn.setCellFactory(
		    CheckBoxTableCell.forTableColumn(
		    enabledTableColumn
		));
	}

	/*
					//////////////////////
					/ GRID LINES VISIBLE /
					//////////////////////
	*/
	@FXML
	private TableColumn<OverlayViewManager, Boolean> gridLinesVisibleTableColumn;

	/*
						\\\\\\\\\\\\\\
						\ INITIALIZE \
						\\\\\\\\\\\\\\
	*/
	private void initializeGridLinesVisibleTableColumn() {
		/*
		Set the column's cell value factory.
		*/
		gridLinesVisibleTableColumn.setCellValueFactory(
		    new PropertyValueFactory<>("gridLinesVisible")
		);

		/*
		Set the column's cell factory.
		*/
		gridLinesVisibleTableColumn.setCellFactory(
		    CheckBoxTableCell.forTableColumn(
		    gridLinesVisibleTableColumn
		));
	}

	/*
					/////////
					/ COLOR /
					/////////
	*/
	@FXML
	private TableColumn<OverlayViewManager, Color> colorTableColumn;

	/*
						\\\\\\\\\\\\\\
						\ INITIALIZE \
						\\\\\\\\\\\\\\
	*/
	private void initializeColorTableColumn() {
		/*
		Set the column's cell value factory.
		*/
		colorTableColumn.setCellValueFactory(
		    new PropertyValueFactory<>("color")
		);

		/*
		Set the column's cell factory.
		 */
		colorTableColumn.setCellFactory(
		    TextFieldTableCell.forTableColumn(
		    new ColorToRGBALabelConverter()
		));
	}

	/*
					/////////////////
					/ CURSOR WINDOW /
					/////////////////
	*/
	/*
						\\\\\\\\\\\\\\\\\\\\\\
						\ TRACKING FREQUENCY \
						\\\\\\\\\\\\\\\\\\\\\\
	*/
	@FXML
	private TableColumn<OverlayViewManager, Double> cursorWindowTrackingFrequencyTableColumn;

	/*
							//////////////
							/ INITIALIZE /
							//////////////
	*/
	private void initializeCursorWindowTrackingFrequencyTableColumn() {
		/*
		Set the column's cell value factory.
		*/
		cursorWindowTrackingFrequencyTableColumn.setCellValueFactory(
		    new PropertyValueFactory<>("cursorWindowTrackingFrequency")
		);
	}

	/*
						\\\\\\\\\
						\ WIDTH \
						\\\\\\\\\
	*/
	@FXML
	private TableColumn<OverlayViewManager, Double> cursorWindowWidthTableColumn;

	/*
							//////////////
							/ INITIALIZE /
							//////////////
	*/
	private void initializeCursorWindowWidthTableColumn() {
		/*
		Set the column's cell value factory.
		*/
		cursorWindowWidthTableColumn.setCellValueFactory(
		    new PropertyValueFactory<>("cursorWindowWidth")
		);
	}

	/*
						\\\\\\\\\\
						\ HEIGHT \
						\\\\\\\\\\
	*/
	@FXML
	private TableColumn<OverlayViewManager, Double> cursorWindowHeightTableColumn;

	/*
							//////////////
							/ INITIALIZE /
							//////////////
	*/
	private void initializeCursorWindowHeightTableColumn() {
		/*
		Set the column's cell value factory.
		*/
		cursorWindowHeightTableColumn.setCellValueFactory(
		    new PropertyValueFactory<>("cursorWindowHeight")
		);
	}

	/*
				\\\\\\\\\\\\\\\\\
				\ TABLE BUTTONS \
				\\\\\\\\\\\\\\\\\
	*/
	/*
					///////
					/ ADD /
					///////
	*/
	@FXML
	private Button addButton;

	/*
						\\\\\\\\\\\\\\
						\ INITIALIZE \
						\\\\\\\\\\\\\\
	*/
	private void initializeAddButton() {
		/*
		Add an event handler to the add button which adds a new overlay
		view manager to the table view.
		*/
		addButton.setOnAction((
		    ActionEvent actionEvent
		) -> {
			/*
			Add a new overlay view manager to the table view.
			*/
			overlayViewManagerTableView.getItems().add(
			    new OverlayViewManager()
			);
		});
	}

	/*
					//////////
					/ REMOVE /
					//////////
	*/
	@FXML
	private Button removeButton;

	/*
						\\\\\\\\\\\\\\
						\ INITIALIZE \
						\\\\\\\\\\\\\\
	*/
	private void initializeRemoveButton() {
		/*
		Add an event handler to the remove button which removes the
		current context overlay view manager.
		*/
		removeButton.setOnAction((
		    ActionEvent actionEvent
		) -> {
			/*
			Verify that there is a context overlay view manager.
			*/
			if (contextOverlayViewManager == null) {
				throw new IllegalStateException(
				    "There is currently no selected overlay " +
				    "view manager context."
				);
			}
			
			contextOverlayViewManager.enabledProperty().set(false);

			/*
			Remove the current context overlay view manager. This
			will automatically update the context.
			*/
			overlayViewManagerTableView.getItems().remove(
			    contextOverlayViewManager
			);
		});
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
	public static final int RED_SLIDER_MAJOR_TICK_COUNT = 4;
	public static final int RED_SLIDER_MINOR_TICK_COUNT =
	    RED_SLIDER_MAJOR_TICK_COUNT - 2;

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
		Set the slider's tick mark label attributes.
		*/
		redSlider.setLabelFormatter(
		    new DoubleToPercentageLabelConverter()
		);

		/*
		Add a change listener to the slider which propagates changes to
		the context overlay's color property.
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
			Check if there is a context overlay view manager.
			*/
			if (contextOverlayViewManager != null) {
				/*
				If so, adjust the context color property's green
				value to match the new value.
				*/
				contextOverlayViewManager.colorProperty().set(new Color(
				    redNewValue.doubleValue(),
				    contextOverlayViewManager.colorProperty().get().getGreen(),
				    contextOverlayViewManager.colorProperty().get().getBlue(),
				    contextOverlayViewManager.colorProperty().get().getOpacity()
				));
			}
		});
	}

	/*
							///////////
							/ DISABLE /
							///////////
	*/
	public static final double RED_SLIDER_DISABLED_VALUE =
	    Color.BLACK.getRed();

	private void disableRedSlider() {
		/*
		Disable the slider, and set the slider's value to the default
		disabled value.
		*/
		redSlider.setDisable(true);
		redSlider.setValue(
		    RED_SLIDER_DISABLED_VALUE
		);
	}

	/*
						\\\\\\\\\
						\ GREEN \
						\\\\\\\\\
	*/
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
	public static final int GREEN_SLIDER_MAJOR_TICK_COUNT =
	    RED_SLIDER_MAJOR_TICK_COUNT;
	public static final int GREEN_SLIDER_MINOR_TICK_COUNT =
	    GREEN_SLIDER_MAJOR_TICK_COUNT - 2;

	private void initializeGreenSlider() {
		/*
		Associate with the corresponding label.
		*/
		greenSliderLabel.setLabelFor(greenSlider);

		/*
		Set the range of the slider to match the acceptable range of
		values.
		*/
		greenSlider.setMin(Color.BLACK.getGreen());
		greenSlider.setMax(Color.WHITE.getGreen());

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
		Set the slider's tick mark label attributes.
		*/
		greenSlider.setLabelFormatter(
		    new DoubleToPercentageLabelConverter()
		);

		/*
		Add a change listener to the slider which propagates changes to
		the context overlay's color property.
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
			Check if there is a context overlay view manager.
			*/
			if (contextOverlayViewManager != null) {
				/*
				If so, adjust the context color property's green
				value to match the new value.
				*/
				contextOverlayViewManager.colorProperty().set(new Color(
				    contextOverlayViewManager.colorProperty().get().getRed(),
				    greenNewValue.doubleValue(),
				    contextOverlayViewManager.colorProperty().get().getBlue(),
				    contextOverlayViewManager.colorProperty().get().getOpacity()
				));
			}
		});
	}

	/*
							///////////
							/ DISABLE /
							///////////
	*/
	public static final double GREEN_SLIDER_DISABLED_VALUE =
	    Color.BLACK.getGreen();

	private void disableGreenSlider() {
		/*
		Disable the slider, and set the slider's value to the default
		disabled value.
		*/
		greenSlider.setDisable(true);
		greenSlider.setValue(
		    GREEN_SLIDER_DISABLED_VALUE
		);
	}

	/*
						\\\\\\\\
						\ BLUE \
						\\\\\\\\
	*/
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
	public static final int BLUE_SLIDER_MAJOR_TICK_COUNT =
	    RED_SLIDER_MAJOR_TICK_COUNT;
	public static final int BLUE_SLIDER_MINOR_TICK_COUNT =
	    BLUE_SLIDER_MAJOR_TICK_COUNT - 2;

	private void initializeBlueSlider() {
		/*
		Associate with the corresponding label.
		*/
		blueSliderLabel.setLabelFor(blueSlider);

		/*
		Set the range of the slider to match the acceptable range of
		values.
		*/
		blueSlider.setMin(Color.BLACK.getBlue());
		blueSlider.setMax(Color.WHITE.getBlue());

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
		Set the slider's tick mark label attributes.
		*/
		blueSlider.setLabelFormatter(
		    new DoubleToPercentageLabelConverter()
		);

		/*
		Add a change listener to the slider which propagates changes to
		the context overlay's color property.
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
				    "greenNewValue == null"
				);
			}

			/*
			Check if there is a context overlay view manager.
			*/
			if (contextOverlayViewManager != null) {
				/*
				If so, adjust the context color property's blue
				value to match the new value.
				*/
				contextOverlayViewManager.colorProperty().set(new Color(
				    contextOverlayViewManager.colorProperty().get().getRed(),
				    contextOverlayViewManager.colorProperty().get().getGreen(),
				    blueNewValue.doubleValue(),
				    contextOverlayViewManager.colorProperty().get().getOpacity()
				));
			}
		});
	}

	/*
							///////////
							/ DISABLE /
							///////////
	*/
	public static final double BLUE_SLIDER_DISABLED_VALUE =
	    Color.BLACK.getBlue();

	private void disableBlueSlider() {
		/*
		Disable the slider, and set the slider's value to the default
		disabled value.
		*/
		blueSlider.setDisable(true);
		blueSlider.setValue(
		    BLUE_SLIDER_DISABLED_VALUE
		);
	}

	/*
					//////////////////
					/ OPACITY SLIDER /
					//////////////////
	*/
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

	public static final int OPACITY_SLIDER_MAJOR_TICK_COUNT = 4;
	public static final int OPACITY_SLIDER_MINOR_TICK_COUNT =
	    OPACITY_SLIDER_MAJOR_TICK_COUNT - 2;

	private void initializeOpacitySlider() {
		/*
		Associate with the corresponding label.
		*/
		opacitySliderLabel.setLabelFor(opacitySlider);

		/*
		Set the range of the slider to match the acceptable range of
		values.
		*/
		opacitySlider.setMin(
		    OverlayViewController.MINIMUM_OPACITY
		);
		opacitySlider.setMax(
		    OverlayViewController.MAXIMUM_OPACITY
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
		the context overlay's color property.
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
			Check if there is a context overlay view manager.
			*/
			if (contextOverlayViewManager != null) {
				/*
				If so, adjust the context color property's
				opacity value to match the new value.
				*/
				contextOverlayViewManager.colorProperty().set(new Color(
				    contextOverlayViewManager.colorProperty().get().getRed(),
				    contextOverlayViewManager.colorProperty().get().getGreen(),
				    contextOverlayViewManager.colorProperty().get().getBlue(),
				    opacityNewValue.doubleValue()
				));
			}
		});
	}

	/*
							///////////
							/ DISABLE /
							///////////
	*/
	public static final double OPACITY_SLIDER_DISABLED_VALUE =
	    OverlayViewController.MINIMUM_OPACITY;

	private void disableOpacitySlider() {
		/*
		Disable the slider, and set the slider's value to the default
		disabled value.
		*/
		opacitySlider.setDisable(true);
		opacitySlider.setValue(
		    OPACITY_SLIDER_DISABLED_VALUE
		);
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
	@FXML
	private Slider cursorWindowTrackingFrequencySlider;

	/*
						\\\\\\\\\
						\ LABEL \
						\\\\\\\\\
	*/
	@FXML
	private Label cursorWindowTrackingFrequencySliderLabel;

	/*
						\\\\\\\\\\\\\\
						\ INITIALIZE \
						\\\\\\\\\\\\\\
	*/
	public static final int CURSOR_WINDOW_TRACKING_FREQUENCY_SLIDER_MAJOR_TICK_COUNT = 4;
	public static final int CURSOR_WINDOW_TRACKING_FREQUENCY_SLIDER_MINOR_TICK_COUNT =
	    CURSOR_WINDOW_TRACKING_FREQUENCY_SLIDER_MAJOR_TICK_COUNT - 2;

	private void initializeCursorWindowTrackingFrequencySlider() {
		/*
		Associate with the corresponding label.
		*/
		cursorWindowTrackingFrequencySliderLabel.setLabelFor(
		    cursorWindowTrackingFrequencySlider
		);

		/*
		Set the range of the slider to match the acceptable range of
		values.
		*/
		cursorWindowTrackingFrequencySlider.setMin(
		    OverlayViewController.MINIMUM_CURSOR_WINDOW_TRACKING_FREQUENCY
		);
		cursorWindowTrackingFrequencySlider.setMax(
		    OverlayViewController.MAXIMUM_CURSOR_WINDOW_TRACKING_FREQUENCY
		);

		/*
		Set the slider's tick mark attributes.
		*/
		cursorWindowTrackingFrequencySlider.setMajorTickUnit(
		    (cursorWindowTrackingFrequencySlider.getMax() -
		    cursorWindowTrackingFrequencySlider.getMin()) /
		    (CURSOR_WINDOW_TRACKING_FREQUENCY_SLIDER_MAJOR_TICK_COUNT - 1)
		);
		cursorWindowTrackingFrequencySlider.setMinorTickCount(
		    CURSOR_WINDOW_TRACKING_FREQUENCY_SLIDER_MINOR_TICK_COUNT
		);

		/*
		Set the slider's tick mark label attributes.
		*/
		cursorWindowTrackingFrequencySlider.setLabelFormatter(
		    new DoubleToHertzFrequencyLabelConverter()
		);
	}

	/*
							///////////
							/ DISABLE /
							///////////
	*/
	public static final double CURSOR_WINDOW_TRACKING_FREQUENCY_SLIDER_DISABLED_VALUE =
	    OverlayViewController.MINIMUM_CURSOR_WINDOW_TRACKING_FREQUENCY;

	private void disableCursorWindowTrackingFrequencySlider() {
		/*
		Disable the slider, and set the slider's value to the default
		disabled value.
		*/
		cursorWindowTrackingFrequencySlider.setDisable(true);
		cursorWindowTrackingFrequencySlider.setValue(
		    CURSOR_WINDOW_TRACKING_FREQUENCY_SLIDER_DISABLED_VALUE
		);
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
	public static final int CURSOR_WINDOW_WIDTH_SLIDER_MAJOR_TICK_COUNT = 4;
	public static final int CURSOR_WINDOW_WIDTH_SLIDER_MINOR_TICK_COUNT =
	    CURSOR_WINDOW_WIDTH_SLIDER_MAJOR_TICK_COUNT - 2;

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
		    OverlayViewController.MINIMUM_CURSOR_WINDOW_WIDTH
		);
		cursorWindowWidthSlider.setMax(
		    OverlayViewController.MAXIMUM_CURSOR_WINDOW_WIDTH
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

		/*
		Add a change listener to the slider which restricts it to
		integral values. Unfrotunately, the snap to ticks functionality
		doesn't appear to work well for this in practice. It's unclear
		if the tick mark placement is incorrect, the snapping is
		imprecise, or a combination of both. Regardless, this seems to
		be the only way to enforce the restriction.
		*/
		cursorWindowWidthSlider.valueProperty().addListener((
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

			/*
			Set the slider value to the closest integer.
			*/
			cursorWindowWidthSlider.setValue(
			    cursorWindowWidthNewValue.intValue()
			);
		});
	}

	/*
							///////////
							/ DISABLE /
							///////////
	*/
	public static final double CURSOR_WINDOW_WIDTH_SLIDER_DISABLED_VALUE =
	    OverlayViewController.MINIMUM_CURSOR_WINDOW_WIDTH;

	private void disableCursorWindowWidthSlider() {
		/*
		Disable the slider, and set the slider's value to the default
		disabled value.
		*/
		cursorWindowWidthSlider.setDisable(true);
		cursorWindowWidthSlider.setValue(
		    CURSOR_WINDOW_WIDTH_SLIDER_DISABLED_VALUE
		);
	}

	/*
						\\\\\\\\\\
						\ HEIGHT \
						\\\\\\\\\\
	*/
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
	public static final int CURSOR_WINDOW_HEIGHT_SLIDER_MAJOR_TICK_COUNT =
	    CURSOR_WINDOW_WIDTH_SLIDER_MAJOR_TICK_COUNT;
	public static final int CURSOR_WINDOW_HEIGHT_SLIDER_MINOR_TICK_COUNT =
	    CURSOR_WINDOW_WIDTH_SLIDER_MINOR_TICK_COUNT;

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
		    OverlayViewController.MINIMUM_CURSOR_WINDOW_HEIGHT
		);
		cursorWindowHeightSlider.setMax(
		    OverlayViewController.MAXIMUM_CURSOR_WINDOW_HEIGHT
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

		/*
		Add a change listener to the slider which restricts it to
		integral values. Unfrotunately, the snap to ticks functionality
		doesn't appear to work well for this in practice. It's unclear
		if the tick mark placement is incorrect, the snapping is
		imprecise, or a combination of both. Regardless, this seems to
		be the only way to enforce the restriction.
		*/
		cursorWindowHeightSlider.valueProperty().addListener((
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

			/*
			Set the slider value to the closest integer.
			*/
			cursorWindowHeightSlider.setValue(
			    cursorWindowHeightNewValue.intValue()
			);
		});
	}

	/*
							///////////
							/ DISABLE /
							///////////
	*/
	public static final double CURSOR_WINDOW_HEIGHT_SLIDER_DISABLED_VALUE =
	    OverlayViewController.MINIMUM_CURSOR_WINDOW_HEIGHT;

	private void disableCursorWindowHeightSlider() {
		/*
		Disable the slider, and set the slider's value to the default
		disabled value.
		*/
		cursorWindowHeightSlider.setDisable(true);
		cursorWindowHeightSlider.setValue(
		    CURSOR_WINDOW_HEIGHT_SLIDER_DISABLED_VALUE
		);
	}


	/*
		******************
		*** INITIALIZE ***
		******************
	*/
	@FXML
	public void initialize() {
		/*
		Initialize FXML components.
		*/
		initializeOverlayViewManagerTableView();
		initializeTargetScreenTableColumn();
		initializeEnabledTableColumn();
		initializeGridLinesVisibleTableColumn();
		initializeColorTableColumn();
		initializeCursorWindowTrackingFrequencyTableColumn();
		initializeCursorWindowWidthTableColumn();
		initializeCursorWindowHeightTableColumn();
		initializeAddButton();
		initializeRemoveButton();
		initializeRedSlider();
		initializeGreenSlider();
		initializeBlueSlider();
		initializeOpacitySlider();
		initializeCursorWindowTrackingFrequencySlider();
		initializeCursorWindowWidthSlider();
		initializeCursorWindowHeightSlider();

		/*
		Set the default context.
		*/
		setContext(contextOverlayViewManager);
	}


	/*
		*******************
		*** SET CONTEXT ***
		*******************
	*/
	private void setContext(
	    OverlayViewManager newContextOverlayViewManager
	) {
		/*
		Check if there is a previous context overlay view manager bound
		to the context controls.
		*/
		if (contextOverlayViewManager != null) {
			/*
			If so, unbind the previous context overlay view
			manager's properties from the context controls as
			necessary.
			*/
			contextOverlayViewManager.cursorWindowHeightProperty().unbind();
			contextOverlayViewManager.cursorWindowWidthProperty().unbind();
			contextOverlayViewManager.cursorWindowTrackingFrequencyProperty().unbind();
		}

		/*
		Update the context overlay view manager field to the new
		context. It is important to perform this step prior to adjusting
		any context control values to avoid propagating unwanted changes
		to the previous context overlay view manager.
		*/
		contextOverlayViewManager = newContextOverlayViewManager;

		/*
		Check if there is a new context overlay view manager.
		*/
		if (contextOverlayViewManager == null) {
			/*
			If not, disable all context controls.
			*/
			disableCursorWindowHeightSlider();
			disableCursorWindowWidthSlider();
			disableCursorWindowTrackingFrequencySlider();
			disableOpacitySlider();
			disableBlueSlider();
			disableGreenSlider();
			disableRedSlider();
			removeButton.setDisable(true);
		} else {
			/*
			Otherwise, set the values of the context controls to
			match the current values of the corresponding properties
			which they will control.
			*/
			redSlider.setValue(
			    contextOverlayViewManager.colorProperty().get().getRed()
			);
			greenSlider.setValue(
			    contextOverlayViewManager.colorProperty().get().getGreen()
			);
			blueSlider.setValue(
			    contextOverlayViewManager.colorProperty().get().getBlue()
			);
			opacitySlider.setValue(
			    contextOverlayViewManager.colorProperty().get().getOpacity()
			);
			cursorWindowTrackingFrequencySlider.setValue(
			    contextOverlayViewManager.cursorWindowTrackingFrequencyProperty().get()
			);
			cursorWindowWidthSlider.setValue(
			    contextOverlayViewManager.cursorWindowWidthProperty().get()
			);
			cursorWindowHeightSlider.setValue(
			    contextOverlayViewManager.cursorWindowHeightProperty().get()
			);

			/*
			Bind the new context overlay view manager's properties
			to the context controls as necessary.
			*/
			contextOverlayViewManager.cursorWindowTrackingFrequencyProperty().bind(
			    cursorWindowTrackingFrequencySlider.valueProperty()
			);
			contextOverlayViewManager.cursorWindowWidthProperty().bind(
			    cursorWindowWidthSlider.valueProperty()
			);
			contextOverlayViewManager.cursorWindowHeightProperty().bind(
			    cursorWindowHeightSlider.valueProperty()
			);

			/*
			Enable all context controls.
			*/
			removeButton.setDisable(false);
			redSlider.setDisable(false);
			greenSlider.setDisable(false);
			blueSlider.setDisable(false);
			opacitySlider.setDisable(false);
			cursorWindowTrackingFrequencySlider.setDisable(false);
			cursorWindowWidthSlider.setDisable(false);
			cursorWindowHeightSlider.setDisable(false);
		}
	}
}