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

import java.net.URL;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * This is the main application class. In addition to serving as the application
 * entry point, it contains basic application information, such as
 * {@link #APPLICATION_TITLE title} and
 * {@link #APPLICATION_VERSION_STRING version}.
 *
 *
 * @author david.boeger@sluggames.com
 *
 * @version 0.2.0
 * @since 0.0.0
 */
public class LowBeams extends Application {
	/*
		*******************************
		*** APPLICATION INFORMATION ***
		*******************************
	*/
	/*
			---------
			| TITLE |
			---------
	*/
	public static final String APPLICATION_TITLE = "Low Beams";

	/*
			-----------
			| VERSION |
			-----------
	*/
	/*
				\\\\\\\\\\\\\\\\
				\ MAJOR NUMBER \
				\\\\\\\\\\\\\\\\

	Differences in the major version number indicate a conscious choice by
	the application developer to identify a distinctly separate new version
	of the application. While there are no strict rules for when to declare
	a new major version, the important thing to note is that it does not
	merely capture a change to an existing application, but a conscious
	decision to identify as a new application. For this reason, new major
	versions are generally forked into new projects from legacy versions,
	enabling developers to support multiple major versions simultaneously.
	Some common reasons to begin development of a new major version include:

		1)	An intentional break from compatibility with a previous
			version, in order to accomodate new features or remove
			deprecated ones.

		2)	An expanded or otherwise improved feature set, which
			either meets large new design goals or opens up the
			possibility of new use cases.

		3)	Support for new target platforms, deployment methods,
			and/or problem domains.

		4)	Any uniquely identifiable shift in direction which is
			combined with a new marketing campaign designed to
			attract new users.

	Perhaps the only exception would be major version number 0, which
	philosophically belongs to version 1, but indicates that it has yet to
	reach a stable, feature-complete state. The major version number should
	be bumped to 1 once the application meets its original design goals and
	is ready for full deployment.

	Upon incrementing the major version number, both the minor and revision
	version numbers should be reset to 0.
	*/
	public static final int MAJOR_VERSION_NUMBER = 0;

	/*
				\\\\\\\\\\\\\\\\
				\ MINOR NUMBER \
				\\\\\\\\\\\\\\\\

	Unlike the major version number, the minor version does not represent
	arbitrary development decisions. Instead, increments correspond to
	individual feature changes and enhancements. Most gradual evolution of
	applications takes the form of these incremental minor changes.

	Upon incrementing the minor version number, the revision version number
	should be reset to 0.
	*/
	public static final int MINOR_VERSION_NUMBER = 1;

	/*
				\\\\\\\\\\\\\\\\\\\
				\ REVISION NUMBER \
				\\\\\\\\\\\\\\\\\\\

	Finally, the revision version number should correspond to changes that
	do not affect the application's design or intended functionality. The
	typical example would be bug fixes or spelling corrections.
	*/
	public static final int REVISION_VERSION_NUMBER = 0;

	/*
				\\\\\\\\\\
				\ STRING \
				\\\\\\\\\\

	The complete version string should be of the following form:

		<MAJOR>.<MINOR>.<REVISION>
	*/
	public static final String APPLICATION_VERSION_STRING =
	    MAJOR_VERSION_NUMBER + "." +
	    MINOR_VERSION_NUMBER + "." +
	    REVISION_VERSION_NUMBER;


	/*
			--------
			| LOGO |
			--------
	*/
	/*
				\\\\\\\\
				\ ICON \
				\\\\\\\\

	Icons are small images to be used by the desktop environment in system
	areas, such as task bars and window decorations, for easy identification
	by users. The application logo icon should be an image with a small
	resolution, as it will likely be downsized by the OS to fit in small
	areas. 128x128 seems to work well in practice, as it is large enough to
	display prominently in JavaFX interfaces, but small enough to be usable
	by typical OS configurations.
	*/
	/*
					///////
					/ URL /
					///////
	*/
	public static final URL APPLICATION_LOGO_ICON_URL =
	    LowBeams.class.getResource("resources/images/Logo Icon.png");

	/*
					/////////
					/ IMAGE /
					/////////
	*/
	public static final Image APPLICATION_LOGO_ICON_IMAGE =
	    new Image(APPLICATION_LOGO_ICON_URL.toString());


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
		/*
		Launch the JavaFX platform.
		*/
		launch(args);
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
	public void start(Stage ignoredStage) {
		/*
		Create a single overlay manager, which will handle the
		initialization and operation of a single overlay on the primary
		screen.
		*/
		OverlayManager overlayManager = new OverlayManager();

		/*
		Create the preferences menu manager.
		*/
		PreferencesMenuManager preferencesMenuManager =
		    new PreferencesMenuManager();

		/*
		Bind the overlay manager to the preferences menu manager so that
		the user can adjust the overlay's properties.
		*/
		preferencesMenuManager.bindOverlayManager(overlayManager);
	}
}