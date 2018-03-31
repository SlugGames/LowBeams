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

import static com.sluggames.software.LowBeams.LowBeams.APPLICATION_LOGO_ICON_URL;
import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javafx.application.Platform;
import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

/**
 * This class is responsible for managing the application's presence in the
 * system tray. This includes initialization and termination of the AWT
 * framework, as the rest of the application is implemented using JavaFX.
 *
 * Care must be taken not to call the methods of this class from the wrong
 * threads, as the AWT and JavaFX frameworks each have their own single-threaded
 * execution models which are not particularly designed to work together.
 * Methods will be documented as necessary to indicate which calling threads are
 * supported.
 *
 * To avoid conflicts between AWT and JavaFX, this class is made responsible for
 * exiting the application gracefully, properly shutting down both frameworks.
 * The JavaFX application logic can schedule a graceful application exit with
 * the {@link #scheduleApplicationExit()} method.
 *
 *
 * @author david.boeger@sluggames.com
 *
 * @version 0.10.0
 * @since 0.10.0
 */
public class SystemTrayMenuManager {
	/*
		******************
		*** COMPONENTS ***
		******************
	*/
	/*
			--------------------
			| SYSTEM TRAY ICON |
			--------------------
	*/
	private TrayIcon systemTrayIcon;

	/*
				\\\\\\\\\\\\\\
				\ INITIALIZE \
				\\\\\\\\\\\\\\
	*/
	/**
	 * @throws AWTException		Failed to add icon to system tray.
	 *
	 * @throws IOException		Failed to read system tray icon image.
	 */
	private void initializeSystemTrayIcon()
	    throws
	    AWTException,
	    IOException
	{
		/*
		Create a system tray icon displaying the application logo.
		*/
		systemTrayIcon = new TrayIcon(
		    ImageIO.read(
		    APPLICATION_LOGO_ICON_URL
		));
		systemTrayIcon.setImageAutoSize(true);

		/*
		Set the system tray icon's popup menu.
		*/
		systemTrayIcon.setPopupMenu(popupMenu);

		/*
		Add the system tray icon to the system tray.
		*/
		SystemTray.getSystemTray().add(systemTrayIcon);
	}

	/*
			--------------
			| POPUP MENU |
			--------------
	*/
	private final PopupMenu popupMenu = new PopupMenu();

	/*
				\\\\\\\\\\\\\\
				\ INITIALIZE \
				\\\\\\\\\\\\\\
	*/
	private void initializePopupMenu() {
		/*
		Add the popup menu items.
		*/
		popupMenu.add(preferencesMenuItem);
		popupMenu.add(aboutMenuItem);
		popupMenu.addSeparator();
		popupMenu.add(quitMenuItem);
	}

	/*
			--------------
			| MENU ITEMS |
			--------------
	*/
	/*
				\\\\\\\\\\\\\\\
				\ PREFERENCES \
				\\\\\\\\\\\\\\\
	*/
	private final MenuItem preferencesMenuItem =
	    new MenuItem("Preferences");

	/*
					///////////
					/ MANAGER /
					///////////
	*/
	private final PreferencesViewManager preferencesViewManager;

	/*
					//////////////
					/ INITIALIZE /
					//////////////
	*/
	private void initializePreferencesMenuItem() {
		/*
		Add an action event listener to the preferences menu item which
		displays the preferences view from the JavaFX thread.
		*/
		preferencesMenuItem.addActionListener((
		    ActionEvent actionEvent
		) -> {
			/*
			Queue the operation to be run asynchronously from the
			JavaFX thread.
			*/
			Platform.runLater(() -> {
				/*
				Display the preferences view.
				*/
				preferencesViewManager.display();
			});
		});
	}

	/*
				\\\\\\\\\
				\ ABOUT \
				\\\\\\\\\
	*/
	private final MenuItem aboutMenuItem =
	    new MenuItem("About");

	/*
					///////////
					/ MANAGER /
					///////////
	*/
	private final ApplicationInformationViewManager applicationInformationViewManager;

	/*
					//////////////
					/ INITIALIZE /
					//////////////
	*/
	private void initializeAboutMenuItem() {
		/*
		Add an action event listener to the about menu item which
		displays the application information view from the JavaFX
		thread.
		*/
		aboutMenuItem.addActionListener((
		    ActionEvent actionEvent
		) -> {
			/*
			Queue the operation to be run asynchronously from the
			JavaFX thread.
			*/
			Platform.runLater(() -> {
				/*
				Display the application information view.
				*/
				applicationInformationViewManager.display();
			});
		});
	}

	/*
				\\\\\\\\
				\ QUIT \
				\\\\\\\\
	*/
	private final MenuItem quitMenuItem =
	    new MenuItem("Quit");

	/*
					//////////////
					/ INITIALIZE /
					//////////////
	*/
	private void initializeQuitMenuItem() {
		/*
		Add an action event listener to the quit menu item which exits
		the application.
		*/
		quitMenuItem.addActionListener((
		    ActionEvent actionEvent
		) -> {
			/*
			Exit the application.
			*/
			exitApplication();
		});
	}


	/*
		********************
		*** CONSTRUCTION ***
		********************
	*/
	/**
	 * This class can be instantiated from any thread.
	 *
	 *
	 * @param preferencesViewManager
	 * @param applicationInformationViewManager
	 *
	 * @throws NullPointerException		{@code
	 *					preferencesViewManager == null ||
	 *					applicationInformationViewManager == null
	 *					}
	 */
	public SystemTrayMenuManager(
	    PreferencesViewManager preferencesViewManager,
	    ApplicationInformationViewManager applicationInformationViewManager
	) {
		/*
		Validate arguments.
		*/
		if (preferencesViewManager == null) {
			throw new NullPointerException(
			    "preferencesViewManager == null"
			);
		}
		if (applicationInformationViewManager == null) {
			throw new NullPointerException(
			    "applicationInformationViewManager == null"
			);
		}

		this.preferencesViewManager =
		    preferencesViewManager;
		this.applicationInformationViewManager =
		    applicationInformationViewManager;
	}


	/*
		******************
		*** INITIALIZE ***
		******************
	*/
	/**
	 * @throws AWTException		Failed to add icon to system tray.
	 *
	 * @throws IOException		Failed to read system tray icon image.
	 *
	 * @throws UnsupportedOperationException	The system tray is not
	 *						supported.
	 */
	public void initialize()
	    throws
	    AWTException,
	    IOException
	{
		/*
		Initialize the AWT framework, checking that system tray
		functionality is supported.
		*/
		Toolkit.getDefaultToolkit();

		if (!SystemTray.isSupported()) {
			throw new UnsupportedOperationException(
			    "The system tray is not supported."
			);
		}

		/*
		Initialize components.
		*/
		initializeSystemTrayIcon();
		initializePopupMenu();
		initializePreferencesMenuItem();
		initializeAboutMenuItem();
		initializeQuitMenuItem();
	}


	/*
		************
		*** EXIT ***
		************
	*/
	private void exitApplication() {
		/*
		Exit the JavaFX platform before removing the system tray icon
		and closing AWT. It is safe to exit the JavaFX platform from
		threads other than the JavaFX application thread.
		*/
		Platform.exit();
		SystemTray.getSystemTray().remove(
		    systemTrayIcon
		);
	}

	/*
			-------------------------------------------
			| SCHEDULE FROM JAVAFX APPLICATION THREAD |
			-------------------------------------------
	*/
	/**
	 * This method must be called from the JavaFX application thread, as it
	 * is intended to allow the JavaFX application logic to schedule an
	 * exit, which must also remove the icon from the system tray while
	 * running on the AWT thread.
	 *
	 *
	 * @throws IllegalStateException	Not on JavaFX application
	 *					thread.
	 */
	public void scheduleApplicationExit() {
		/*
		Check if the caller is running on a thread other than the JavaFX
		application thread.
		*/
		if (!Platform.isFxApplicationThread()) {
			/*
			If so, throw an exception.
			*/
			throw new IllegalStateException(
			    "Not on JavaFX application thread."
			);
		}

		/*
		Schedule an application exit to run asynchronously on the AWT
		thread.
		*/
		SwingUtilities.invokeLater(() -> {
			/*
			Exit the application.
			*/
			exitApplication();
		});
	}
}