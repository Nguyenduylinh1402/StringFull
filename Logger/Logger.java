package TestNotification.Logger;

import java.io.File;

import android.os.Build;
import android.util.Log;
import com.android.uiautomator.core.*;
import com.android.uiautomator.testrunner.*;
import static TestNotification.BBAT_Utility.BBATUtility.*;

/**
 * Logger
 * 
 * NOTE:1.This class can contain only one test case. 2.Do not change the class
 * name.
 */
public class Logger {

	private static String TAG = "Logger";

	public static void d(final String tag, final String message) {
		System.out.println(tag + ": " + message);
		Log.d(tag, message);
	}

}
