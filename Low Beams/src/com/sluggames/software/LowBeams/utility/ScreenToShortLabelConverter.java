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
package com.sluggames.software.LowBeams.utility;

import javafx.stage.Screen;
import javafx.util.StringConverter;

/**
 * This class converts between {@link javafx.stage.Screen} values and short
 * label strings. The default string representations of screens are very
 * verbose, making them unsuitable for use as identifiers in user-friendly
 * interfaces. However, shorter label strings can be preferable for simple use
 * cases like screen choice boxes, rather than having to implement a custom
 * interface or component which thoroughly details each screen to the user.
 *
 *
 * @author david.boeger@sluggames.com
 *
 * @version 0.8.0
 * @since 0.8.0
 */
public class ScreenToShortLabelConverter extends StringConverter<Screen> {
	/*
		*****************
		*** TO STRING ***
		*****************
	*/
	@Override
	public String toString(Screen screen) {
		/*
		Validate arguments.
		*/
		if (screen == null) {
			throw new NullPointerException(
			    "screen == null"
			);
		}

		/*
		Return a short label string, which consists of the identifying
		hash code, followed by the screen resolution.
		*/
		return
		    Integer.toHexString(screen.hashCode()) +
		    " [" +
		    (int) screen.getBounds().getWidth() +
		    "x" +
		    (int) screen.getBounds().getHeight() +
		    "]";
	}


	/*
		*******************
		*** FROM STRING ***
		*******************
	*/
	@Override
	public Screen fromString(String shortLabel) {
		/*
		Validate arguments.
		*/
		if (shortLabel == null) {
			throw new NullPointerException(
			    "shortLabel == null"
			);
		}

		/*
		Iterate through each of the available screens.
		*/
		for (Screen screen : Screen.getScreens()) {
			/*
			Check if the short label matches the screen.
			*/
			if (shortLabel.equals(toString(screen))) {
				/*
				If so, return the matching screen.
				*/
				return screen;
			}
		}

		/*
		Throw an exception indicating that the short label did not match
		any screens.
		*/
		throw new IllegalArgumentException(
		    "shortLabel (" + shortLabel + ") does not match any screens"
		);
	}
}