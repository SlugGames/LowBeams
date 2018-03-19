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
package com.sluggames.software.LowBeams;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This is the main application class, serving as the entry point.
 *
 *
 * @author david.boeger@sluggames.com
 */
public class LowBeams extends Application {
	/*
		**************
		*** LAUNCH ***
		**************
	*/
	/*
			--------
			| MAIN |
			--------

	This method is called when the application is first launched, but before
	the JavaFX platform is initialized. Although most application
	initialization should be deferred to the JavaFX start method below,
	there are valid use cases which require the main method, namely:

		1)	JavaFX still does not implement all of the features of
			AWT or Swing, and occasionally, features from multiple
			platforms may be required. A good example is system tray
			integration, which is present in AWT but not yet in
			JavaFX. Due to an older bug, AWT needed to be
			initialized prior to JavaFX. It appears that this is no
			longer the case, but it is still advisable to do so just
			in case.

		2)	Although it has been years since this was a common
			problem, when JavaFX was new, there were cases when the
			JavaFX platform needed to be explicitly launched from
			the main method. This was common when running
			development builds from older IDE versions which lacked
			true JavaFX support. Therefore, it's still good practice
			to provide the main method just in case.
	*/
	public static void main(String[] args) {
	}

	/*
			---------
			| START |
			---------

	This method is called after the initialization of the JavaFX platform,
	and is executed by the JavaFX application thread. It is where most
	application initialization should generally be performed, although the
	main method may be necessary for the cases described above.
	*/
	@Override
	public void start(Stage primaryStage) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}