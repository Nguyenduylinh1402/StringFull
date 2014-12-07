package TestNotification.TestNotificationSuite;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

/**
 * TestNotification
 * 
 * NOTE:1.This class can contain only one test case. 2.Do not change the class
 * name.
 */
public class TestNotification extends UiAutomatorTestCase {

	private static String TAG = "TestNotification";

	/**
	 * @generated All your test code will go here NOTE: Do not change the method
	 *            signature
	 */
	public void test() throws UiObjectNotFoundException {
		System.out.println("testcase execution started");
		// takeScreenShot();
		// CODE:START
		UiDevice.getInstance().openNotification();
		UiObject noti = new UiObject(new UiSelector().className(
				"android.widget.FrameLayout"));
		int x = noti.getChildCount();
		UiObject notifile = noti.getChild(new UiSelector().resourceId(
				"android:id/title"));
		int y = notifile.getChildCount();

		// UiScrollable listNoti = new UiScrollable(new UiSelector()
		// .className("android.widget.FrameLayout")
		// .resourceId("com.android.systemui:id/panel_holder")
		// .scrollable(true));
		// listNoti.setAsVerticalList();
		//
		// UiObject notiItem = listNoti.getChildByText(
		// new UiSelector().className(
		// "android.widget.TextView").resourceId("android:id/title"),
		// "USB debugging connected");
		// // UiObject notiItem = new UiObject(new
		// //
		// UiSelector().className("android.widget.TextView").text("USB debugging connected"));
//		if (notifile.exists())
//			notifile.click();
		// CODE:END
		System.out.println("testcase execution completed");
		System.out.println(x + "Count" + y);
	}
}
