package TestNotification.Logger;

import java.io.File;

import android.os.Build;
import android.util.Log;
import com.android.uiautomator.core.*;
import com.android.uiautomator.testrunner.*;
import static TestNotification.BBAT_Utility.BBATUtility.*;

/**
 * Logger
 *  "File info.txt"
 *  "PL_09_Presentation_skill - VN.pptx"
 *  HR-DEPENDENT REGISTRATION FORM.doc
 *  PL_15_businessethics.ppt
 *  "timetable-PNV2M & 2T_week 36.xls"
 *  "English Classes-Preintermediate.pdf"
 *  "C360_2014-11-28-22-04-59-538.jpg"
 *  Can do list_softskills.docx
 *  "Tonight I Celebrate My Love For You - Peabo Bryson Roberta Flack.mp3"
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
