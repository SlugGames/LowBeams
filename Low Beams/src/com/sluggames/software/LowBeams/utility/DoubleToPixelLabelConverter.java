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

import javafx.util.StringConverter;

/**
 * This class converts between double values and pixel label strings. It is
 * intended to be used for dimension slider tick mark labeling. For example, the
 * double value {@code 20.0} would be converted to the label string
 * {@code "20 px"}.
 *
 *
 * @author david.boeger@sluggames.com
 *
 * @version 0.2.0
 * @since 0.2.0
 */
public class DoubleToPixelLabelConverter extends StringConverter<Double> {
	/*
		**************
		*** SUFFIX ***
		**************
	*/
	public static final String SUFFIX = " px";

	/*
		*****************
		*** TO STRING ***
		*****************
	*/
	@Override
	public String toString(Double value) {
		/*
		Use only the integer value of the double to avoid lengthy
		decimals, and append the pixel label suffix.
		*/
		return
		    value.intValue() +
		    SUFFIX;
	}

	/*
		*******************
		*** FROM STRING ***
		*******************
	*/
	@Override
	public Double fromString(String pixelLabel) {
		/*
		After removing the suffix from the pixel label string, parse and
		return the double value.
		*/
		return
		    Double.parseDouble(
		    pixelLabel.replace(SUFFIX, "")
		);
	}
}