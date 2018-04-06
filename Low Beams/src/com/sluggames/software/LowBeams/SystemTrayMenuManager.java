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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * supported. Both frameworks can schedule asynchronous operations on each
 * other's threads by calling
 * {@link javafx.application.Platform#runLater(java.lang.Runnable)} or
 * {@link javax.swing.SwingUtilities#invokeLater(java.lang.Runnable)}.
 *
 * To facilitate proper initialization and termination of the application with
 * regards to AWT, the following guidelines should be followed:
 *
 *	1)	To initialize an instance of this class, the
 *		{@link #initialize()} method should be called from the JavaFX
 *		application's {@link javafx.application.Application#init()}
 *		method, which executes prior to starting the JavaFX application
 *		thread.
 *
 *	2)	To terminate an instance of this class, the {@link #quit()}
 *		method should be called from the JavaFX application's
 *		{@link javafx.application.Application#stop()} method, which
 *		executes after exiting the JavaFX application thread.
 *
 * See the documentation for {@link #initialize()} and {@link #quit()} for more
 * specific details. Failing to follow these guidelines may result in undefined
 * behavior.
 *
 *
 * @author david.boeger@sluggames.com
 *
 * @see #initialize()
 * @see #quit()
 *
 * @see javafx.application.Application#init()
 * @see javafx.application.Application#stop()
 * @see javafx.application.Platform#runLater(java.lang.Runnable)
 * @see javax.swing.SwingUtilities#invokeLater(java.lang.Runnable)
 *
 * @version 0.12.0
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
		Add an action event listener to the system tray icon which
		queues the display of the preferences view. The conditions which
		trigger an action event directly on the tray icon are
		platform-dependent.
		*/
		systemTrayIcon.addActionListener((
		    ActionEvent actionEvent
		) -> {
			/*
			Queue the display of the preferences view.
			*/
			queueDisplayPreferencesView();
		});

		/*
		Because the action listener above is platform-dependent, it's
		best to add a mouse listener which responds to double left
		clicks as a backup.
		*/
		systemTrayIcon.addMouseListener(new MouseListener() {
			/*
			Respond to mouse click events.
			*/
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				/*
				Check if the mouse event represents at least a
				double click of the left mouse button.
				*/
				if (
				    mouseEvent.getButton() == MouseEvent.BUTTON1 &&
				    mouseEvent.getClickCount() >= 2
				) {
					/*
					If so, queue the display of the
					preferences view.
					*/
					queueDisplayPreferencesView();
				}
			}

			/*
			Ignore the following mouse event handlers.
			*/
			@Override
			public void mousePressed(MouseEvent mouseEvent) {}
			@Override
			public void mouseReleased(MouseEvent mouseEvent) {}
			@Override
			public void mouseEntered(MouseEvent mouseEvent) {}
			@Override
			public void mouseExited(MouseEvent mouseEvent) {}
		});

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
					////////////////
					/ VIEW MANAGER /
					////////////////

	Construction of the preferences view manager must be deferred till after
	the JavaFX platform has started, and must be performed on the JavaFX
	application thread. To satisfy these requirements, the preferences menu
	item lazily queues construction with the JavaFX platform whenever the
	user first selects the menu item. As a result, this field is not safe to
	set from any other thread, including the AWT event dispatch thread.
	*/
	private PreferencesViewManager preferencesViewManager;

	/*
						\\\\\\\\\\\\\\\\\
						\ QUEUE DISPLAY \
						\\\\\\\\\\\\\\\\\
	*/
	private void queueDisplayPreferencesView() {
		/*
		Queue the operations to be run asynchronously from the JavaFX
		thread.
		*/
		Platform.runLater(() -> {
			/*
			Check if the preferences view manager has yet to be
			instantiated. This check does not require
			synchronization, as queued operations are run
			sequentially on the JavaFX application thread.
			*/
			if (preferencesViewManager == null) {
				/*
				If so, attempt to create the preferences view
				manager. This is safe as long as no other thread
				attempts to set the field asynchronously, as
				described above.
				*/
				try {
					preferencesViewManager =
						new PreferencesViewManager();
				} catch (IOException exception) {
					/*
					Log the exception trace.
					*/
					Logger.getLogger(
					    SystemTrayMenuManager.class.getName()).log(
					    Level.SEVERE,
					    null,
					    exception
					);

					/*
					Exit the JavaFX platform, quit the AWT
					framework, and return.
					*/
					Platform.exit();
					quit();
					return;
				}
			}

			/*
			Display the preferences view.
			*/
			preferencesViewManager.display();
		});
	}

	/*
					//////////////
					/ INITIALIZE /
					//////////////
	*/
	private void initializePreferencesMenuItem() {
		/*
		Add an action event listener to the preferences menu item which
		queues the display of the preferences view.
		*/
		preferencesMenuItem.addActionListener((
		    ActionEvent actionEvent
		) -> {
			/*
			Queue the display of the preferences view.
			*/
			queueDisplayPreferencesView();
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
					////////////////
					/ VIEW MANAGER /
					////////////////

	Construction of the application information view manager must be
	deferred till after the JavaFX platform has started, and must be
	performed on the JavaFX application thread. To satisfy these
	requirements, the about menu item lazily queues construction with the
	JavaFX platform whenever the user first selects the menu item. As a
	result, this field is not safe to set from any other thread, including
	the AWT event dispatch thread.
	*/
	private ApplicationInformationViewManager applicationInformationViewManager;

	/*
						\\\\\\\\\\\\\\\\\
						\ QUEUE DISPLAY \
						\\\\\\\\\\\\\\\\\
	*/
	private void queueDisplayApplicationInformationView() {
		/*
		Queue the operations to be run asynchronously from the JavaFX
		thread.
		*/
		Platform.runLater(() -> {
			/*
			Check if the application information view manager has
			yet to be instantiated. This check does not require
			synchronization, as queued operations are run
			sequentially on the JavaFX application thread.
			*/
			if (applicationInformationViewManager == null) {
				/*
				If so, attempt to create the application
				information view manager. This is safe as long
				as no other thread attempts to set the field
				asynchronously, as described above.
				*/
				try {
					applicationInformationViewManager =
						new ApplicationInformationViewManager();
				} catch (IOException exception) {
					/*
					Log the exception trace.
					*/
					Logger.getLogger(
					    SystemTrayMenuManager.class.getName()).log(
					    Level.SEVERE,
					    null,
					    exception
					);

					/*
					Exit the JavaFX platform, quit the AWT
					framework, and return.
					*/
					Platform.exit();
					quit();
					return;
				}
			}

			/*
			Display the application information view.
			*/
			applicationInformationViewManager.display();
		});
	}

	/*
					//////////////
					/ INITIALIZE /
					//////////////
	*/
	private void initializeAboutMenuItem() {
		/*
		Add an action event listener to the about menu item which queues
		the display of the application information view.
		*/
		aboutMenuItem.addActionListener((
		    ActionEvent actionEvent
		) -> {
			/*
			Queue the display of the application information view.
			*/
			queueDisplayApplicationInformationView();
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
		Add an action event listener to the quit menu item which quits
		the application.
		*/
		quitMenuItem.addActionListener((
		    ActionEvent actionEvent
		) -> {
			/*
			Quit the application by exiting both the JavaFX platform
			and the AWT framework. Calling the JavaFX platform's
			exit method is safe from any thread according to the
			documentation.
			*/
			Platform.exit();
			quit();
		});
	}


	/*
		**********************
		*** INITIALIZATION ***
		**********************
	*/
	/**
	 * This method performs any initialization required outside of
	 * construction. In particular, this includes initializing the AWT
	 * framework, which can throw exceptions, justifying the separation from
	 * construction in cases where instances need to be constructed before
	 * circumstances are appropriate for AWT initialization.
	 *
	 * This method must be called from the main Java application thread,
	 * prior to AWT or JavaFX initialization. Unfortunately, there is no
	 * guaranteed way of verifying that the current thread is the main
	 * thread, nor is there a way to verify that neither of the GUI
	 * frameworks have been initialized. As a compromise, this method only
	 * verifies that it is not running on either the AWT event dispatch
	 * thread or the JavaFX application thread, but it is ultimately the
	 * caller's duty to make sure that it is running on the main Java
	 * application thread, as any other scenario may lead to undefined
	 * behavior. The ideal way to satisfy this requirement is to call this
	 * method from the JavaFX application's
	 * {@link javafx.application.Application#init()} method.
	 *
	 *
	 * @throws AWTException		Failed to add icon to system tray.
	 *
	 * @throws IllegalStateException	Not on main Java application
	 *					thread.
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
		Verify that the method is not being called from either the AWT
		event dispatch thread or the JavaFX application thread.
		*/
		if (
		    SwingUtilities.isEventDispatchThread() ||
		    Platform.isFxApplicationThread()
		) {
			throw new IllegalStateException(
			    "Not on main Java application thread."
			);
		}

		/*
		Initialize the AWT framework.
		*/
		Toolkit.getDefaultToolkit();

		/*
		Verify that system tray functionality is supported.
		*/
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
		*** QUIT ***
		************
	*/
	/**
	 * This method gracefully quits the application with regards to AWT. It
	 * performs an internal check to see if it is running on the AWT event
	 * dispatch thread, and if not, the quit logic is scheduled to run
	 * asynchronously on the EDT. As a result, this method is safe to call
	 * from any thread, including the JavaFX application thread. In
	 * particular, this allows it to be called from a JavaFX application's
	 * {@link javafx.application.Application#stop()} method, so that exiting
	 * the JavaFX platform by calling
	 * {@link javafx.application.Platform#exit()} from any JavaFX
	 * application logic class will naturally lead to a graceful shutdown of
	 * AWT, and by extension, the application as a whole.
	 *
	 * Note that the reverse does not hold true, meaning that calling this
	 * method does not automatically exit the JavaFX platform by calling
	 * {@link javafx.application.Platform#exit()}}. Thus, the JavaFX
	 * application logic is still responsible for exiting the JavaFX
	 * platform as usual.
	 */
	public void quit() {
		/*
		Check if this method is being called from the AWT event dispatch
		thread.
		*/
		if (SwingUtilities.isEventDispatchThread()) {
			/*
			Call the internal quit method directly.
			*/
			quitInternal();
		} else {
			/*
			Otherwise, schedule a the internal quit method to be
			called asynchronously on the AWT event dispatch thread.
			*/
			SwingUtilities.invokeLater(() -> {
				/*
				Call the internal quit method.
				*/
				quitInternal();
			});
		}
	}

	/*
			------------
			| INTERNAL |
			------------
	*/
	/**
	 * This method implements the internal AWT termination logic. It must be
	 * called from the AWT event dispatch thread. Users should generally see
	 * the {@link #quit()} method instead, as it is publicly accessible and
	 * safe to call from any thread.
	 *
	 *
	 * @throws IllegalStateException	Not on AWT event dispatch
	 *					thread.
	 *
	 * @see #quit()
	 */
	private void quitInternal() {
		/*
		Verify that the method is being run on the AWT event dispatch
		thread.
		*/
		if (!SwingUtilities.isEventDispatchThread()) {
			throw new IllegalStateException(
			    "Not on AWT event dispatch thread."
			);
		}

		/*
		Shut down AWT by removing the system tray icon from the system
		tray. The documentation states that attempting to remove a
		system tray icon which is not present in the system tray simply
		results in no operation, making it safe to perform duplicate
		calls, and eliminating the need to track termination state.
		*/
		SystemTray.getSystemTray().remove(
		    systemTrayIcon
		);
	}
}