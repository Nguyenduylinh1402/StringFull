package TestNotification.VerifyFileReceived;

import android.os.RemoteException;
import android.util.Log;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class VerifyFileMp3 extends UiAutomatorTestCase {

	// CODE:START
	private final static String APPTRAY_MOTOROLA = "Apps";
	private final static String APPTRAY_SAMSUNG_TAB = "Applications";
	private final static String APPTAB_MOTOROLA = "Apps";
	private final static String APP_NAME = "FileString";
	private final static String APPTAB_SAMSUNG_TAB = "Apps";
	private final static String FILESTRING_RECEIVE_FILES = "FileString Received Files";
	private final static String FILESTRING_SENDER_FOLDER = "sta 005";
	private final static String FILE_NAME = "Tonight I Celebrate My Love For You - Peabo Bryson Roberta Flack.fcs";
	private final static String EMAIL_SHARE_FILE = "sta001@yopmail.com";
	private final static String PASSWORD = "1234";

	public final static int ACTION_CLICK = 0;
	public final static int ACTION_TAKE_SCREENSHOT = 1;

	public static UiScrollable scrollableListView = new UiScrollable(
			new UiSelector().className("android.widget.ListView").scrollable(
					true));

	public void testDemo() throws UiObjectNotFoundException, RemoteException,
			InterruptedException {
		loggerd(VerifyFileMp3.class.getName(), "Start Testing");

		loggerd(VerifyFileMp3.class.getName(), "Launch FileString App");
		launchFileStringApp(APP_NAME);

		loggerd(VerifyFileMp3.class.getName(), "Sign In");
		signIn(EMAIL_SHARE_FILE, PASSWORD);

		loggerd(VerifyFileMp3.class.getName(), "Sleeping 60s ...");
		Thread.sleep(60000);

		// loggerd(StringFileImprovement1.class.getName(),
		// "Check Received Notification");
		// openNotificationPanel();
		// notificationCheckFileUploaded("Sta 001 shared file Screenshot taosua02.fcs with you");

		clickListViewItem(FILESTRING_RECEIVE_FILES, ACTION_CLICK);

		clickListViewItem(FILESTRING_SENDER_FOLDER, ACTION_CLICK);

		clickListViewItem(FILE_NAME, ACTION_TAKE_SCREENSHOT);

		loggerd(VerifyFileMp3.class.getName(), "Sign Out");
		signOut();

		loggerd(VerifyFileMp3.class.getName(), "Test Complete");
	}

	public void clickListViewItem(String name, int action)
			throws UiObjectNotFoundException {

		UiScrollable listView = new UiScrollable(
				new UiSelector().className("android.widget.ListView"));

		listView.setMaxSearchSwipes(100);
		listView.scrollTextIntoView(name);
		listView.waitForExists(2000);
		UiSelector listViewItemSelector;

		listViewItemSelector = new UiSelector()
				.className(android.widget.TextView.class.getName());
		UiObject listViewItem;
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(5000);
				listViewItem = listView.getChildByText(listViewItemSelector,
						name);

				if (listViewItem != null) {
					if (action == ACTION_CLICK) {
						listViewItem.click();
						System.out
								.println("\"" + name + "\" item was clicked.");
					} else if (action == ACTION_TAKE_SCREENSHOT) {
						// TAKE SCREENSHOT
					}

					break;
				}
			} catch (Exception e) {
				System.out.println("Did not find match for "
						+ e.getLocalizedMessage());
			}
		}

	}

	public static void fileFileReceivedFrom(final String fileName) {

		UiSelector fileSelector;
		fileSelector = new UiSelector()
				.resourceId("com.filestring.lattedouble:id/txtfile_item_name");
		UiObject fileCatch;

		for (int i = 0; i < 10; i++) {

			try {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				fileCatch = scrollableListView.getChildByText(fileSelector,
						fileName, true);

				if (fileCatch != null) {
					Log.d("", "File: " + fileName + " Here");
					// fileCatch.clickAndWaitForNewWindow();
					break;
				}
			} catch (UiObjectNotFoundException e) {
				System.out.println("Did not find match for "
						+ e.getLocalizedMessage());
			}

		}

	}

	// xu ly lai phan quet notification list
	private void notificationCheckFileUploaded(String keyCheckState) {

		// why scrollable(=true) this function not work
		UiScrollable scrollableScrollView = new UiScrollable(new UiSelector()
				.className("android.widget.ScrollView")
				.resourceId("com.android.systemui:id/scroll").scrollable(false));

		UiSelector notiSelector;
		notiSelector = new UiSelector().className(
				android.widget.TextView.class.getName()).resourceId(
				"android:id/title");

		UiObject notiFileUploaded;

		for (int i = 0; i < 20; i++) {
			try {
				Thread.sleep(5000);
				notiFileUploaded = scrollableScrollView.getChildByText(
						notiSelector, keyCheckState);

				if (notiFileUploaded != null) {
					// notiFileUploaded.click();
					getUiDevice().pressBack();
					break;
				}
			} catch (Exception e) {
				System.out.println("Did not find match for "
						+ e.getLocalizedMessage());
				// getUiDevice().pressBack();
			}
		}
	}

	public static void openNotificationPanel() {
		UiDevice.getInstance().openNotification();
	}

	// Launch FileString App
	private void launchFileStringApp(String appName) throws RemoteException,
			UiObjectNotFoundException {

		getUiDevice().wakeUp();
		getUiDevice().pressHome();

		UiObject appTray = new UiObject(
				new UiSelector().description(APPTRAY_SAMSUNG_TAB));
		appTray.clickAndWaitForNewWindow();

		UiObject appsTab = new UiObject(
				new UiSelector().text(APPTAB_SAMSUNG_TAB));
		appsTab.clickAndWaitForNewWindow();

		UiScrollable appViews = new UiScrollable(new UiSelector().className(
				"android.view.View").scrollable(true));
		appViews.setAsHorizontalList();
		appViews.scrollToBeginning(10);

		int maxSearchSwipes = appViews.getMaxSearchSwipes();
		UiSelector selector;
		selector = new UiSelector().className(android.widget.TextView.class
				.getName());
		UiObject appToLaunch;

		for (int i = 0; i < maxSearchSwipes; i++) {
			try {
				appToLaunch = appViews.getChildByText(selector, appName);
				if (appToLaunch != null) {
					// Create a UiSelector to find the Settings app and simulate
					// a user click to launch the app.
					appToLaunch.clickAndWaitForNewWindow();
					break;
				}
			} catch (UiObjectNotFoundException e) {
				System.out.println("Did not find match for "
						+ e.getLocalizedMessage());
			}

			for (int j = 0; j < i; j++) {
				appViews.scrollForward();
				System.out.println("scrolling forward 1 page of apps.");
			}
		}
	}

	private void signIn(String email, String password)
			throws UiObjectNotFoundException {
		UiObject emailSignIn = new UiObject(
				new UiSelector()
						.resourceId("com.filestring.lattedouble:id/edtSignInEmail"));

		UiObject passwordSignIn = new UiObject(
				new UiSelector()
						.resourceId("com.filestring.lattedouble:id/edtSignInPassword"));

		UiObject signIn = new UiObject(
				new UiSelector()
						.resourceId("com.filestring.lattedouble:id/btnSignIn"));
		if (emailSignIn.exists()) {
			emailSignIn.click();
			emailSignIn.setText(email);
		}
		if (passwordSignIn.exists()) {
			passwordSignIn.click();
			passwordSignIn.setText(password);
		}
		if (signIn.exists()) {
			signIn.clickAndWaitForNewWindow(10000);
		}
	}

	private void signOut() throws UiObjectNotFoundException {
		UiObject hamberger = new UiObject(new UiSelector().resourceId(
				"android:id/up").className("android.widget.ImageView"));
		hamberger.click();

		UiObject info = new UiObject(new UiSelector().resourceId(
				"com.filestring.lattedouble:id/drawer_email").className(
				"android.widget.TextView"));
		info.clickAndWaitForNewWindow();

		UiObject signout = new UiObject(
				new UiSelector().text("Sign Out from FileString"));
		signout.clickAndWaitForNewWindow();
	}

	public static void loggerd(final String tag, final String message) {
		System.out.println(tag + ": " + message);
		Log.d(tag, message);
	}
	// CODE:END
}
