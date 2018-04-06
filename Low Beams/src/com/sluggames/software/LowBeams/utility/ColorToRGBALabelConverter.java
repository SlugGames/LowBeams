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

import javafx.scene.paint.Color;
import javafx.util.StringConverter;

/**
 * This class converts between {@link javafx.scene.paint.Color} instances and
 * RGBA label strings. It is intended to be used for data cells in controls such
 * as table views. For example, the color {@link javafx.scene.paint.Color#BLACK}
 * would be converted to the label string {@code "#000000"}.
 *
 *
 * @author david.boeger@sluggames.com
 *
 * @version 0.12.0
 * @since 0.12.0
 */
public class ColorToRGBALabelConverter extends StringConverter<Color> {
	/*
		**************
		*** PREFIX ***
		**************
	*/
	public static final String PREFIX = "#";


	/*
		*****************
		*** TO STRING ***
		*****************
	*/
	@Override
	public String toString(Color color) {
		/*
		Validate arguments.
		*/
		if (color == null) {
			throw new NullPointerException(
			    "color == null"
			);
		}

		/*
		Format the string to match the web format used for
		reconstruction below.
		*/
		return String.format(
		    "%s%02X%02X%02X%02X",
		    PREFIX,
		    (int) color.getRed() * 255,
		    (int) color.getGreen() * 255,
		    (int) color.getBlue() * 255,
		    (int) color.getOpacity() * 255
		);
	}


	/*
		*******************
		*** FROM STRING ***
		*******************
	*/
	@Override
	public Color fromString(String rgbLabel) {
		/*
		Validate arguments.
		*/
		if (rgbLabel == null) {
			throw new NullPointerException(
			    "rgbLabel == null"
			);
		}

		/*
		Use the provided web color constructor to reconstruct the color.
		Unfortunately, because precision is lost when converting to the
		string format in the first place, the return color instance may
		not be exactly the same as the original.
		*/
		return Color.web(rgbLabel);
	}
}