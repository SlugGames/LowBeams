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
 * This class converts between double values and percentage label strings. It is
 * intended to be used for percentage slider tick mark labeling. For example,
 * the double value {@code 0.4} would be converted to the label string
 * {@code "40%"}.
 *
 *
 * @author david.boeger@sluggames.com
 *
 * @version 0.4.0
 * @since 0.4.0
 */
public class DoubleToPercentageLabelConverter extends StringConverter<Double> {
	/*
		**************
		*** SUFFIX ***
		**************
	*/
	public static final String SUFFIX = "%";


	/*
		*****************
		*** TO STRING ***
		*****************
	*/
	@Override
	public String toString(Double value) {
		/*
		Convert the value to a percentage by multiplying by 100. Use
		only the integer value of the result to avoid lengthy decimals,
		and append the percentage label suffix.
		*/
		return
		    (int)(value * 100) +
		    SUFFIX;
	}


	/*
		*******************
		*** FROM STRING ***
		*******************
	*/
	@Override
	public Double fromString(String percentageLabel) {
		/*
		After removing the suffix from the percentage label string,
		parse and return the double value divided by 100.
		*/
		return
		    Double.parseDouble(
		    percentageLabel.replace(SUFFIX, "")) /
		    100;
	}
}