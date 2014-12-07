package TestNotification.TestNotificationSuite;

import android.os.RemoteException;
import android.util.Log;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

/**
 * StringFileTest
 * 
 * NOTE:1.This class can contain only one test case. 2.Do not change the class
 * name.
 * 
 * Improve:1. bỏ check notification với Recipient 2. Sign in xong cho chờ thời
 * gian khoảng 10s để file sign in already 3. thử sử dụng biến dùng chung để
 * giao tiếp với 2 testcase 4. Xuât log
 */
public class StringFileDocx extends UiAutomatorTestCase {

	private final static String APPTRAY_MOTOROLA = "Apps";
	private final static String APPTRAY_SAMSUNG_TAB = "Applications";
	private final static String APPTAB_MOTOROLA = "Apps";
	private final static String APPTAB_SAMSUNG_TAB = "Apps";
	private final static String APP_NAME = "FileString";
	private final static String EMAIL_SIGNIN = "sta19.2@yopmail.com";
	private final static String EMAIL_SHARE_FILE = "sta001@yopmail.com";
	private final static String PASSWORD = "1234";
	private final static String STRING_A_FILE = "String a File";
	private final static String FILE_NAME = "File info.txt";
	private final static String FILE_UPLOADED_SUCCESSFUL = "File uploaded";
	// Scroll không có className không scroll list nhé
	public static UiScrollable scrollableListView = new UiScrollable(
			new UiSelector().className("android.widget.ListView").scrollable(
					true));

	// =====================Sign In field================================//

	// =====================End Sign In field==========================//

	// Method Name has to name with the first word is "test"
	public void testStringFile() throws UiObjectNotFoundException,
			RemoteException {
		System.out.println("testcase execution started");
		// CODE:START
		launchFileStringApp(APP_NAME);
		 signIn(EMAIL_SIGNIN, PASSWORD);
		 menuStringFile(STRING_A_FILE);
		 selectFile(FILE_NAME);
		 inputEmailShare(EMAIL_SHARE_FILE);
		 sendStringFile();
		 openNotificationPanel();
		 notificationCheckFileUploaded(FILE_UPLOADED_SUCCESSFUL);
		 checkFileExistInAllFile(FILE_NAME);
		 signOut();
		// CODE:END
		System.out.println("testcase execution completed");
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

	public static void checkFileExistInAllFile(final String fileName) {

		UiSelector fileSelector;
		fileSelector = new UiSelector().className(android.widget.TextView.class
				.getName());
		UiObject fileCatch;

		for (int i = 0; i < 10; i++) {

			try {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				fileCatch = scrollableListView.getChildByText(fileSelector,
						fileName, true);

				if (fileCatch != null) {
					Log.d("", "File: " + fileName + " Here");
					//fileCatch.clickAndWaitForNewWindow();
					break;
				}
			} catch (UiObjectNotFoundException e) {
				System.out.println("Did not find match for "
						+ e.getLocalizedMessage());
				// test.getUiDevice().pressBack();
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
				getUiDevice().pressBack();
			}
		}
	}

	public static void openNotificationPanel() {
		UiDevice.getInstance().openNotification();
	}

	private void sendStringFile() throws UiObjectNotFoundException {
		UiObject send = new UiObject(
				new UiSelector()
						.resourceId("com.filestring.lattedouble:id/menu_recipients_send"));
		send.clickAndWaitForNewWindow(5000);
	}

	private void inputEmailShare(String emailShareFile)
			throws UiObjectNotFoundException {
		UiObject emailField = new UiObject(
				new UiSelector()
						.resourceId("com.filestring.lattedouble:id/auto_text_add_recipient"));
		emailField.clickAndWaitForNewWindow();
		emailField.setText(emailShareFile);
	}

	private void selectFile(String fileName) throws UiObjectNotFoundException {
		// select FileManager
		UiObject fileManager = new UiObject(new UiSelector().resourceId(
				"com.filestring.lattedouble:id/source_title").text(
				"File Manager"));
		fileManager.clickAndWaitForNewWindow();
		// select folder "data"
		UiObject folderResource = scrollableListView.getChildByText(
				new UiSelector().resourceId("com.rhmsoft.fm:id/name"), "data");
		folderResource.clickAndWaitForNewWindow();
		// select file with file name is "FILE_NAME"
		UiObject fileResource = scrollableListView.getChildByText(
				new UiSelector().resourceId("com.rhmsoft.fm:id/name"),
				FILE_NAME);
		fileResource.clickAndWaitForNewWindow();
	}

	private void menuStringFile(String actionStringFrom)
			throws UiObjectNotFoundException {
		UiObject menuStringFile = new UiObject(new UiSelector().resourceId(
				"com.filestring.lattedouble:id/menu_files_list_action_add")
				.description("Add"));
		menuStringFile.clickAndWaitForNewWindow();

		UiObject menu = new UiObject(
				new UiSelector()
						.resourceId("com.filestring.lattedouble:id/string_file"));
		menu.clickAndWaitForNewWindow();
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

	protected static void launchAppCalled(String nameOfAppToLaunch)
			throws UiObjectNotFoundException {
		UiScrollable appViews = new UiScrollable(
				new UiSelector().scrollable(true));
		// Set the swiping mode to horizontal (the default is vertical)
		appViews.setAsHorizontalList();
		appViews.scrollToBeginning(10); // Otherwise the Apps may be on a later
										// page of apps.
		int maxSearchSwipes = appViews.getMaxSearchSwipes();

		UiSelector selector;
		selector = new UiSelector().className(android.widget.TextView.class
				.getName());

		UiObject appToLaunch;

		// The following loop is to workaround a bug in Android 4.2.2 which
		// fails to scroll more than once into view.
		for (int i = 0; i < maxSearchSwipes; i++) {

			try {
				appToLaunch = appViews.getChildByText(selector,
						nameOfAppToLaunch);
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

	// Launch FileString App
	private void launchFileStringApp(String appName) throws RemoteException,
			UiObjectNotFoundException {
		UiDevice.getInstance().wakeUp();
		// getUiDevice().wakeUp();
		getUiDevice().pressHome();

		UiObject appTray = new UiObject(
				new UiSelector().description(APPTRAY_MOTOROLA));
		appTray.clickAndWaitForNewWindow();

		UiObject appsTab = new UiObject(new UiSelector().text(APPTAB_MOTOROLA));
		appsTab.clickAndWaitForNewWindow();

		UiScrollable appViews = new UiScrollable(
				new UiSelector().scrollable(true));
		appViews.setAsHorizontalList();
		appViews.scrollToBeginning(10);

		int maxSearchSwipes = appViews.getMaxSearchSwipes();
		UiSelector selector;
		selector = new UiSelector().className(android.widget.TextView.class
				.getName());
		UiObject appToLaunch;

		for (int i = 0; i < maxSearchSwipes; i++) {
			//if do not try/catch: search app not work well
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

}
