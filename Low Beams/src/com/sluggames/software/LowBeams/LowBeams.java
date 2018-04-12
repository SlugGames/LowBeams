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

import java.awt.AWTException;
import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.application.Platform;
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
 * @version 0.13.0
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
	public static final int MINOR_VERSION_NUMBER = 13;

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
			-------------
			| COPYRIGHT |
			-------------
	*/
	public static final String COPYRIGHT =
	    "Copyright (c) 2018 Slug Games";

	/*
			-----------
			| LICENSE |
			-----------
	*/
	public static final String LICENSE =
	    "MIT License\n" +
	    "\n" +
	    "Copyright (c) 2018 Slug Games\n" +
	    "\n" +
	    "Permission is hereby granted, free of charge, to any person " +
	    "obtaining a copy of this software and associated documentation " +
	    "files (the \"Software\"), to deal in the Software without " +
	    "restriction, including without limitation the rights to use, " +
	    "copy, modify, merge, publish, distribute, sublicense, and/or " +
	    "sell copies of the Software, and to permit persons to whom the " +
	    "Software is furnished to do so, subject to the following " +
	    "conditions:\n" +
	    "\n" +
	    "The above copyright notice and this permission notice shall be " +
	    "included in all copies or substantial portions of the " +
	    "Software.\n" +
	    "\n" +
	    "THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY " +
	    "KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE " +
	    "WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE " +
	    "AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT " +
	    "HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, " +
	    "WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING " +
	    "FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR " +
	    "OTHER DEALINGS IN THE SOFTWARE.";

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
	    LowBeams.class.getResource(
	    "resources/images/Logo Icon.png"
	);

	/*
					/////////
					/ IMAGE /
					/////////
	*/
	public static final Image APPLICATION_LOGO_ICON_IMAGE = new Image(
	    APPLICATION_LOGO_ICON_URL.toString()
	);


	/*
		******************
		*** COMPONENTS ***
		******************
	*/
	/*
			-------
			| AWT |
			-------

	In general, AWT components should be initialized in the init method,
	prior to starting the JavaFX application thread. According to various
	scattered posts and bug reports, there exists the potential for
	incompabilities between AWT and JavaFX when AWT is not initialized
	first. Conversely, they should be terminated in the stop method, keeping
	consistent with the reverse ordering convention.
	*/
	/*
				\\\\\\\\\\\\\\\\\\\\\\\\\\\\
				\ SYSTEM TRAY MENU MANAGER \
				\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	*/
	private final SystemTrayMenuManager systemTrayMenuManager =
	    new SystemTrayMenuManager();


	/*
		**************
		*** LAUNCH ***
		**************
	*/
	/*
			--------
			| MAIN |
			--------
	*/
	public static void main(String[] args) {
		/*
		Launch the JavaFX platform.
		*/
		launch(args);
	}

	/*
			--------
			| INIT |
			--------

	This method is executed by the JavaFX platform prior to starting the
	JavaFX application thread. It provides the opportunity to initialize
	components which require running on the main thread, such as AWT
	components.
	*/
	@Override
	public void init()
	    throws
	    AWTException,
	    IOException
	{
		/*
		Initialize AWT components.
		*/
		systemTrayMenuManager.initialize();
	}

	/*
			---------
			| START |
			---------
	*/
	@Override
	public void start(
	    Stage ignoredStage
	) {
		/*
		Disable the default implicit exit behavior whenever the last
		JavaFX stage is closed.
		*/
		Platform.setImplicitExit(false);
	}


	/*
		*******************
		*** TERMINATION ***
		*******************
	*/
	/*
			--------
			| STOP |
			--------

	This method is executed by the JavaFX platform as it exits. It provides
	the opportunity to terminate components which are not automatically
	cleaned up by the JavaFX platform exit, such as AWT components.
	*/
	@Override
	public void stop() {
		/*
		Terminate AWT components.
		*/
		systemTrayMenuManager.quit();
	}
}