package cp.utils;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import cp.items.LogItem;
import cp.listeners.dialog.DL;
import cp.main.LogActv;
import cp.main.R;
import cp.main.ShowLogActv;
import cp.main.TopolActv;
import cp.views.CV;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.os.AsyncTask;

// Apache


// REF=> http://commons.apache.org/net/download_net.cgi
//REF=> http://www.searchman.info/tips/2640.html

//import org.apache.commons.net.ftp.FTPReply;

public class Methods {

	static int counter;		// Used => sortFileList()
	
	
	/****************************************
	 * Vars
	 ****************************************/
	public static final int vibLength_click = 35;

	static int tempRecordNum = 20;

	public static boolean
	clearPref (Activity actv,String prefName) {

		SharedPreferences prefs = 
						actv.getSharedPreferences(
								prefName,
								actv.MODE_PRIVATE);
		
		/****************************
		* 2. Get editor
		****************************/
		SharedPreferences.Editor editor = prefs.edit();
		
		/****************************
		* 3. Clear
		****************************/
		try {
		
			editor.clear();
			editor.commit();
			
			// Log
			Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "Prefs cleared");
			
			return true;
		
		} catch (Exception e) {
		
			// Log
			Log.e("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "Excption: " + e.toString());
			
			return false;
		}

	}//public static boolean clear_prefs_current_path(Activity actv, Strin newPath)

	
	public static void confirm_quit(Activity actv, int keyCode) {
		
		if (keyCode==KeyEvent.KEYCODE_BACK) {
			
			AlertDialog.Builder dialog=new AlertDialog.Builder(actv);
			
			dialog.setTitle(actv.getString(R.string.generic_tv_confirm));
	        dialog.setMessage(actv.getString(R.string.generic_message_quit));
	        
	        dialog.setPositiveButton(
	        				actv.getString(R.string.generic_bt_quit),
	        				new DL(actv, dialog, 0));
	        
	        dialog.setNegativeButton(
	        				actv.getString(R.string.generic_bt_cancel),
	        				new DL(actv, dialog, 1));
	        
	        dialog.create();
	        dialog.show();
			
		}//if (keyCode==KeyEvent.KEYCODE_BACK)
		
	}//public static void confirm_quit(Activity actv, int keyCode)

	/****************************
	 * deleteDirectory(File target)()
	 * 
	 * 1. REF=> http://www.rgagnon.com/javadetails/java-0483.html
		****************************/
	public static boolean deleteDirectory(File target) {
		
		if(target.exists()) {
			//
			File[] files = target.listFiles();
			
			//
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					
					deleteDirectory(files[i]);
					
				} else {//if (files[i].isDirectory())
					
					String path = files[i].getAbsolutePath();
					
					files[i].delete();
					
					// Log
					Log.d("Methods.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", "Removed => " + path);
					
					
				}//if (files[i].isDirectory())
				
			}//for (int i = 0; i < files.length; i++)
			
		}//if(target.exists())
		
		return (target.delete());
	}//public static boolean deleteDirectory(File target)

	public static long getMillSeconds(int year, int month, int date) {
		// Calendar
		Calendar cal = Calendar.getInstance();
		
		// Set time
		cal.set(year, month, date);
		
		// Date
		Date d = cal.getTime();
		
		return d.getTime();
		
	}//private long getMillSeconds(int year, int month, int date)

	/****************************************
	 *	getMillSeconds_now()
	 * 
	 * <Caller> 
	 * 1. ButtonOnClickListener # case main_bt_start
	 * 
	 * <Desc> 1. <Params> 1.
	 * 
	 * <Return> 1.
	 * 
	 * <Steps> 1.
	 ****************************************/
	public static long getMillSeconds_now() {
		
		Calendar cal = Calendar.getInstance();
		
		return cal.getTime().getTime();
		
	}//private long getMillSeconds_now(int year, int month, int date)

	public static String get_TimeLabel(long millSec) {
		
		 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd_HHmmss");
		 
		return sdf1.format(new Date(millSec));
		
	}//public static String get_TimeLabel(long millSec)

	public static String convert_intSec2Digits(int t) {
		
		int sec = t % 60;
		
		if (t / 60 < 1) {
			
//			return "00:00:" + String.valueOf(sec);
			return "00:00:" + Methods.convert_sec2digits(sec, 2);
			
		}//if (t / 60 < 1)
		
//		int min = (t - sec) % 60;
		int min = ((t - sec) % (60 * 60)) / 60;
		
		if ((t - sec) / (60 * 60) < 1) {
			
//			return "00:" + String.valueOf(min) + ":" + String.valueOf(sec);
			return "00:"
				+ Methods.convert_sec2digits(min, 2) + ":"
				+ Methods.convert_sec2digits(sec, 2);
			
		}//if (variable == condition)
		
//		int hour = (t - min) / 60;
		int hour = (t - sec) / (60 * 60);
				
//		return String.valueOf(hour) + ":"
//				+ String.valueOf(min) + ":"
//				+ String.valueOf(sec);

		return Methods.convert_sec2digits(min, 2) + ":"
		+ Methods.convert_sec2digits(min, 2) + ":"
		+ Methods.convert_sec2digits(sec, 2);

		
	}//public static String convert_intSec2Digits(int time)

	/***************************************
	 * 20130320_120437<br>
	 * @param t ... Value in seconds, <i>not</i> in mill seconds
	 ***************************************/
	public static String convert_intSec2Digits_lessThanHour(int t) {
		
		int sec = t % 60;
		
		if (t / 60 < 1) {
			
//			return "00:00:" + String.valueOf(sec);
//			return "00:00:" + Methods.convert_sec2digits(sec, 2);
			return "00:" + Methods.convert_sec2digits(sec, 2);
			
		}//if (t / 60 < 1)
		
//		int min = (t - sec) % 60;
		int min = ((t - sec) % (60 * 60)) / 60;
		
		return Methods.convert_sec2digits(min, 2) + ":"
			+ Methods.convert_sec2digits(sec, 2);
			
	}//public static String convert_intSec2Digits(int time)

	private static String convert_sec2digits(int sec, int i) {
		
		int current_len = String.valueOf(sec).length();
		
		if (current_len < i) {
			
			StringBuilder sb = new StringBuilder();
			
			for (int j = 0; j < i - current_len; j++) {
				
				sb.append("0");
			}
			
			sb.append(String.valueOf(sec));
			
			return sb.toString();
			
		}//if (current_len == condition)
		
		return String.valueOf(sec);
		
	}//private static String convert_sec2digits(int sec, int i)

	public static int getArrayIndex(String[] targetArray, String targetString) {
		int index = -1;
		
		for (int i = 0; i < targetArray.length; i++) {
			
			if (targetArray[i].equals(targetString)) {
				
				index = i;
				
				break;
				
			}//if (targetArray[i] == )
			
		}//for (int i = 0; i < targetArray.length; i++)
		
		return index;
	}//public static int getArrayIndex(String[] targetArray, String targetString)

	/******************************
		@return true => pref set
	 ******************************/
	public static boolean set_Pref_Int
	(Activity actv, String pref_name, String pref_key, int value) {
		SharedPreferences prefs = 
				actv.getSharedPreferences(pref_name, Context.MODE_PRIVATE);

		/****************************
		 * 2. Get editor
			****************************/
		SharedPreferences.Editor editor = prefs.edit();

		/****************************
		 * 3. Set value
			****************************/
		editor.putInt(pref_key, value);
		
		try {
			editor.commit();
			
			return true;
			
		} catch (Exception e) {
			
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Excption: " + e.toString());
			
			return false;
		}

	}//public static boolean set_pref(String pref_name, String value)

	public static int get_Pref_Int
	(Activity actv, String pref_name, String pref_key, int defValue) {
		
		SharedPreferences prefs = 
				actv.getSharedPreferences(pref_name, Context.MODE_PRIVATE);

		/****************************
		 * Return
			****************************/
		return prefs.getInt(pref_key, defValue);

	}//public static boolean set_pref(String pref_name, String value)
	
	public static long getPref_Long
	(Activity actv, String pref_name, String pref_key, long defValue) {
		
		SharedPreferences prefs = 
				actv.getSharedPreferences(pref_name, Context.MODE_PRIVATE);
		
		/****************************
		 * Return
		 ****************************/
		return prefs.getLong(pref_key, defValue);
		
	}//public static boolean set_pref(String pref_name, String value)

	public static boolean db_Backup(Activity actv)
	{
		/****************************
		 * 1. Prep => File names
		 * 2. Prep => Files
		 * 2-2. Folder exists?
		 * 
		 * 2-3. Dst folder => Files within the limit?
		 * 3. Copy
			****************************/
		String time_label = Methods.get_TimeLabel(Methods.getMillSeconds_now());
		
		String db_Src = StringUtils.join(
					new String[]{
							actv.getDatabasePath(CONS.DB.dbName).getPath()},
//							CONS.fname_db},
					File.separator);
		
		String db_Dst_Folder = StringUtils.join(
					new String[]{
							CONS.DB.dPath_dbFile_Backup,
							CONS.DB.fname_DB_Backup_Trunk},
//							CONS.dpath_db_backup,
//							CONS.fname_db_backup_trunk},
					File.separator);
		
		String db_Dst = db_Dst_Folder + "_"
				+ time_label
//				+ MainActv.fileName_db_backup_ext;
				+ CONS.DB.fname_DB_Backup_ext;
//		+ CONS.fname_db_backup_ext;
//				+ MainActv.fname_db_backup_trunk;

		// Log
		String msg_log = "db_Src = " + db_Src
						+ " / "
						+ "db_Dst = " + db_Dst;
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_log);
		
		/****************************
		 * 2. Prep => Files
			****************************/
		File src = new File(db_Src);
		File dst = new File(db_Dst);
		
		/****************************
		 * 2-2. Folder exists?
			****************************/
		File db_Backup = new File(CONS.DB.dPath_dbFile_Backup);
//		File db_backup = new File(CONS.dpath_db_backup);
		
		if (!db_Backup.exists()) {
			
			try {
				db_Backup.mkdir();
				
				// Log
				Log.d("Methods.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ "]", "Folder created: " + db_Backup.getAbsolutePath());
			} catch (Exception e) {
				
				// Log
				Log.e("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "Create folder => Failed");
				
				return false;
				
			}
			
		} else {//if (!db_backup.exists())
			
			// Log
			Log.i("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Folder exists: ");
			
		}//if (!db_backup.exists())
		
		/*********************************
		 * 2-3. Dst folder => Files within the limit?
		 *********************************/
		File[] files_dst_folder = new File(CONS.DB.dPath_dbFile_Backup).listFiles();
//		File[] files_dst_folder = new File(CONS.dpath_db_backup).listFiles();
		
		int num_of_files = files_dst_folder.length;
		
		// Log
		Log.i("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "num of backup files = " + num_of_files);
		
		/****************************
		 * 3. Copy
			****************************/
		try {
			FileChannel iChannel = new FileInputStream(src).getChannel();
			FileChannel oChannel = new FileOutputStream(dst).getChannel();
			iChannel.transferTo(0, iChannel.size(), oChannel);
			iChannel.close();
			oChannel.close();
			
			// Log
			Log.i("ThumbnailActivity.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "DB file copied");
			
			// debug
			Toast.makeText(actv, "DB backup => Done", Toast.LENGTH_LONG).show();

		} catch (FileNotFoundException e) {
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());
			
			return false;
			
		} catch (IOException e) {
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());
			
			return false;
			
		}//try

		return true;
		
	}//public static boolean db_backup(Activity actv)

	public static String
	conv_CurrentPath_to_TableName(String currentPath)
	{
		String full = currentPath;
//		String full = CONS.Paths.dpath_Storage_Sdcard + CONS.Paths.dname_Base;
		
		////////////////////////////////

		// Get: raw strings

		////////////////////////////////
		String head = CONS.Paths.dpath_Storage_Sdcard;
		
		int len = head.length();
		
		String target = full.substring(len + 1);

//		// Log
//		String msg_log = "full = " + full
//						+ " // "
//						+ "target = " + target;
//		Log.d("Methods.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_log);

		////////////////////////////////

		// Split: target

		////////////////////////////////
		// Log
		String msg_log = "File.separator = " + File.separator;
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_log);
		
		String[] tokens = target.split(File.separator);
		
		////////////////////////////////

		// Build: table name

		////////////////////////////////
		if (tokens == null) {
			
			// Log
			msg_log = "Split => returned null";
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_log);
			
			return null;
			
		} else if (tokens.length == 1) {
			
			return target;
			
		} else {

			return StringUtils.join(tokens, CONS.DB.jointString_TableName);
			
		}
		
	}//conv_CurrentPath_to_TableName(String currentPath)

	/******************************
		Format => yyyy/MM/dd hh:mm:ss.SSS
	 ******************************/
	public static String
	conv_MillSec_to_TimeLabel(long millSec)
	{
		//REF http://stackoverflow.com/questions/7953725/how-to-convert-milliseconds-to-date-format-in-android answered Oct 31 '11 at 12:59
		String dateFormat = CONS.Admin.format_Date;
//		String dateFormat = "yyyy/MM/dd hh:mm:ss.SSS";
		
		DateFormat formatter = new SimpleDateFormat(dateFormat);

		// Create a calendar object that will convert the date and time value in milliseconds to date. 
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTimeInMillis(millSec);
		
		return formatter.format(calendar.getTime());
		
	}//conv_MillSec_to_TimeLabel(long millSec)

	public static long
	conv_TimeLabel_to_MillSec(String timeLabel)
//	conv_MillSec_to_TimeLabel(long millSec)
	{
//		String input = "Sat Feb 17 2012";
		Date date;
		try {
			date = new SimpleDateFormat(
						CONS.Admin.format_Date, Locale.JAPAN).parse(timeLabel);
			
			return date.getTime();
//			long milliseconds = date.getTime();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			// Log
			String msg_Log = "Exception: " + e.toString();
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return -1;
			
		}
		
//		Locale.ENGLISH).parse(input);
		
//		Date date = new SimpleDateFormat("EEE MMM dd yyyy", Locale.ENGLISH).parse(input);
//		long milliseconds = date.getTime();
		
	}//conv_TimeLabel_to_MillSec(String timeLabel)
	
	/******************************
		REF http://stackoverflow.com/questions/625433/how-to-convert-milliseconds-to-x-mins-x-seconds-in-java answered Mar 9 '09 at 10:01
	 ******************************/
	public static String
	conv_MillSec_to_ClockLabel(long millSec)
	{
		return String.format(
			Locale.JAPAN,
			CONS.Admin.format_Clock, 
//			"%02d:%02d", 
			TimeUnit.MILLISECONDS.toMinutes(millSec),
			TimeUnit.MILLISECONDS.toSeconds(millSec) - 
			TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millSec))
		);
		
	}//conv_MillSec_to_ClockLabel(long millSec)
	
	public static long
	conv_ClockLabel_to_MillSec(String clockLabel)
	{
		
		String[] tokens = clockLabel.split(":");
		
		/******************************
			Validate
		 ******************************/
		if (tokens == null || tokens.length != 2) {

			// Log
			String msg_Log = "Label format => unknown: " + clockLabel;
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return -1;
			
		}
		
		/******************************
			Build: long number
		 ******************************/
		long mill_Min = Integer.parseInt(tokens[0]) * (60 * 1000);
		long mill_Sec = Integer.parseInt(tokens[1]) * (1000);
		
		return mill_Min + mill_Sec;
		
//		SimpleDateFormat formatter = 
//				new SimpleDateFormat("mm:ss"); // I assume d-M, you may refer to M-d for month-day instead.
////		new SimpleDateFormat("d-M-yyyy hh:mm"); // I assume d-M, you may refer to M-d for month-day instead.
//		Date date;
//		try {
//			
//			date = formatter.parse(clockLabel);
//			return date.getTime();
//			
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			// Log
//			String msg_Log = "Exception: " + e.toString();
//			Log.e("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//			
//			return -1;
//			
//		} // You will need try/catch around this
//		long millis = date.getTime();
		
	}//conv_ClockLabel_to_MillSec(String clockLabel)

	public static String get_Pref_String
	(Activity actv, String pref_name,
			String pref_key, String defValue) {
		
		SharedPreferences prefs = 
				actv.getSharedPreferences(
						pref_name, Context.MODE_PRIVATE);

		/****************************
		 * Return
			****************************/
		return prefs.getString(pref_key, defValue);

	}//public static String get_Pref_String
	
	public static int get_Pref_String
	(Activity actv, String pref_name,
			String pref_key, int defValue) {
		
		SharedPreferences prefs = 
				actv.getSharedPreferences(
						pref_name, Context.MODE_PRIVATE);
		
		/****************************
		 * Return
		 ****************************/
		return prefs.getInt(pref_key, defValue);
		
	}//public static String get_Pref_String
	
	public static boolean
	setPref_Long
	(Activity actv, String pName, String pKey, long value) {
		
		SharedPreferences prefs = 
				actv.getSharedPreferences(pName, Context.MODE_PRIVATE);

		/****************************
		 * 2. Get editor
			****************************/
		SharedPreferences.Editor editor = prefs.edit();

		/****************************
		 * 3. Set value
			****************************/
		editor.putLong(pKey, value);
		
		try {
			
			editor.commit();
			
			return true;
			
		} catch (Exception e) {
			
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Excption: " + e.toString());
			
			return false;
			
		}

	}//public static boolean setPref_long(Activity actv, String pref_name, String pref_key, long value)
	
	public static boolean
	setPref_String
	(Activity actv, String pName, String pKey, String value) {
		
		SharedPreferences prefs = 
				actv.getSharedPreferences(pName, Context.MODE_PRIVATE);
		
		/****************************
		 * 2. Get editor
		 ****************************/
		SharedPreferences.Editor editor = prefs.edit();
		
		/****************************
		 * 3. Set value
		 ****************************/
		editor.putString(pKey, value);
		
		try {
			
			editor.commit();
			
			return true;
			
		} catch (Exception e) {
			
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Excption: " + e.toString());
			
			return false;
			
		}
		
	}//public static boolean setPref_long(Activity actv, String pref_name, String pref_key, long value)


	private static long get_AudioLength(String fileFullPath) {
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "Methods: " + Thread.currentThread().getStackTrace()[2].getMethodName());
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "File path=" + fileFullPath);
		
		MediaPlayer mp = new MediaPlayer();
		
//		int len = 0;
		long len = 0;
		
		try {
			mp.setDataSource(fileFullPath);
			
			mp.prepare();
			
//			len = mp.getDuration() / 1000;
			len = mp.getDuration();
			
			// REF=> http://stackoverflow.com/questions/9609479/android-mediaplayer-went-away-with-unhandled-events
			mp.reset();
			
			// REF=> http://stackoverflow.com/questions/3761305/android-mediaplayer-throwing-prepare-failed-status-0x1-on-2-1-works-on-2-2
			mp.release();
			
		} catch (IllegalArgumentException e) {
			
			// Log
			Log.d("Methods.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + "]", "Exception=" + e.toString());
			
		} catch (IllegalStateException e) {
			// Log
			Log.d("Methods.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + "]", "Exception=" + e.toString());

		} catch (IOException e) {
			// Log
			Log.d("Methods.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + "]", "Exception=" + e.toString());
		}//try
		
		return len;
	}//private static long getFileLength(String fileFullPath)


	public static double 
	get_CircleA_Change
	(Activity actv, 
		float cir_A_X_prev, float cir_A_Y_prev, float x, float y) {
		// TODO Auto-generated method stub
		
		return Math.sqrt(
					Math.pow(x - cir_A_X_prev, 2) 
					+ Math.pow(y - cir_A_Y_prev, 2));
		
	}//get_CircleA_Change


	/******************************
	 * set => CONS.Canvas.currentObj
		@return
			0	=> Rect A<br>
			1	=> Rect B<br>
			2	=> Cir A<br>
			-1	=> Other<br>
			-2	=> Not identified<br>
	 ******************************/
	public static int 
	identify
	(Activity actv, float x, float y) {
		// TODO Auto-generated method stub
		
		String msg_Log;
		
		int x_i = (int) x;
		int y_i = (int) y;
		
		////////////////////////////////

		// judge

		////////////////////////////////
		float dist_Cir_A = Methods.get_Distance_2D(
				actv, 
				new Point(x_i, y_i),
				new Point((int)CONS.Canvas.Cir_A_X, (int)CONS.Canvas.Cir_A_Y));
		
		////////////////////////////////

		// iteration

		////////////////////////////////
		CONS.Canvas.Layer tmp_Layer;
		
		boolean identified = false;
		
		for (int i = 0; i < CONS.Canvas.list_Layer.size(); i++) {
			
			tmp_Layer = CONS.Canvas.list_Layer.get(i);
			
			switch(tmp_Layer) {
			
			case Rect_A:
				
				if (Methods.is_In_Rect_A(actv, x_i, y_i)) {
					
					_identify__Rect_A(actv);
					
					identified = true;
					
					return 0;
					
				}
				
				break;
				
			case Rect_B:
				
				if(is_In_Rect_B(actv, x, y)) {
					
					_identify__Rect_B(actv);
					
					identified = true;
					
					return 1;
					
				}
				
				break;
				
			case Rect_C:
				
				if(is_In_Rect_C(actv, x, y)) {
					
					_identify__Rect_C(actv);
					
					identified = true;
					
					return 1;
					
				}
				
				break;
				
//			case Cir_A:
//				
//				if(dist_Cir_A < CONS.Canvas.Cir_A_Radius) {
//					
//					Methods._identify__Cir_A(actv);
//					
//					identified = true;
//					
//					return 2;
//					
//				}
//				
//				break;
				
//			default:
//				
//				_case_ACTION_MOVE__Cir_A(x, y);
//				
//				break;
			
			}//switch(tmp_Layer)
			
		}//for (int i = 0; i < CONS.Canvas.list_Layer.size(); i++)
		
		/******************************
			In case of no match
		 ******************************/
		if (identified == false) {
			
			_identify__Other(actv);
			
			return -1;
			
		} else {
			
			// Log
			msg_Log = "identified => true; but, switch process passed";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return -2;
			
		}
		
//		////////////////////////////////
//
//		// obj => Rect_A
//
//		////////////////////////////////
//		if (Methods.is_In_Rect_A(actv, x_i, y_i)) {
//			
//			// Log
//			msg_Log = "is in => Rect A";
//			Log.i("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//			
//			_identify__Rect_A(actv);
//			
//			return 0;
//			
//		} else if(dist_Cir_A < CONS.Canvas.Cir_A_Radius) {
//			
//			// Log
//			msg_Log = "is in => Cir A";
//			Log.i("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//			
////			_identify__Other(actv);
//			
//			Methods._identify__Cir_A(actv);
//			
//			return 2;
//			
//		} else if(is_In_Rect_B(actv, x, y)){
//			
//			// Log
//			msg_Log = "is in => Rect B";
//			Log.i("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//			
//			_identify__Rect_B(actv);
//			
//			return 1;
//			
//		} else {
//			
//			_identify__Other(actv);
//			
//			return -1;
//			
//		}
		
	}//identify


	private static boolean 
	is_In_Rect_B
	(Activity actv, float x, float y) {
		// TODO Auto-generated method stub
		
		boolean res = (x >= CONS.Canvas.Rect_B_X1
				&& x <= CONS.Canvas.Rect_B_X1 + CONS.Canvas.Rect_B_W
			&& y >= CONS.Canvas.Rect_B_Y1
				&& y <= CONS.Canvas.Rect_B_Y1 + CONS.Canvas.Rect_B_H);
		
		
		return res;
		
	}//is_In_Rect_B

	private static boolean 
	is_In_Rect_C
	(Activity actv, float x, float y) {
		// TODO Auto-generated method stub
		
		boolean res = (x >= CONS.Canvas.Rect_C_X1
				&& x <= CONS.Canvas.Rect_C_X1 + CONS.Canvas.Rect_C_W
				&& y >= CONS.Canvas.Rect_C_Y1
				&& y <= CONS.Canvas.Rect_C_Y1 + CONS.Canvas.Rect_C_H);
		
		
		return res;
		
	}//is_In_Rect_C
	
	private static boolean 
	is_In_Rect_A
	(Activity actv, float x, float y) {
		// TODO Auto-generated method stub
		
		boolean res = (x >= CONS.Canvas.Rect_A_X1)
				&& (x <= CONS.Canvas.Rect_A_X1 + CONS.Canvas.Rect_A_W)
			&& (y >= CONS.Canvas.Rect_A_Y1)
				&& (y <= CONS.Canvas.Rect_A_Y1 + CONS.Canvas.Rect_A_H);
		
		
		return res;
		
	}//is_In_Rect_A
	

	private static float 
	get_Distance_2D
	(Activity actv, 
		Point pnt_Target, Point pnt_Ref) {
		// TODO Auto-generated method stub
		
		String msg_Log;
		
		int dif_X = pnt_Target.x - pnt_Ref.x;
		int dif_Y = pnt_Target.y - pnt_Ref.y;
		
//		// Log
//		msg_Log = String.format(
//				Locale.JAPAN, 
//				"pnt_Target.x = %d, pnt_Target.y = %d",
//				pnt_Target.x, pnt_Target.y);
//		
//		Log.d("Methods.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
//		
//		// Log
//		msg_Log = String.format(
//				Locale.JAPAN, 
//				"pnt_Ref.x = %d, pnt_Ref.y = %d",
//				pnt_Ref.x, pnt_Ref.y);
//		
//		Log.d("Methods.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
//		msg_Log = String.format(
//				Locale.JAPAN, 
//				"dif_X = %d, dif_Y = %d",
//				
//				dif_X, dif_Y);
//		
//		Log.d("Methods.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		float result = (float) Math.sqrt(
				Math.pow(Math.abs(dif_X), 2)
				+ Math.pow(Math.abs(dif_Y), 2));

//		msg_Log = String.format(
//				Locale.JAPAN, 
//				"result = %f", result);
//		
//		Log.d("Methods.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);

		return result;
//		return (float) Math.sqrt(Math.pow(dif_X, 2) + Math.pow(dif_Y, 2));
		
//		return 0;
		
	}//get_Distance_2D
	


	private static void
	_identify__Other
	(Activity actv) {
		// TODO Auto-generated method stub
	
		String msg_Log;
		
		////////////////////////////////

		// obj => not Rect_A

		////////////////////////////////
		
		// Log
		msg_Log = "not in => Rect A";
		Log.i("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////
		
		// set: enum
		
		////////////////////////////////
		CONS.Canvas.currentObj = CONS.Canvas.ChosenObj.Others;
		
		////////////////////////////////

		// update: layer

		////////////////////////////////
		CONS.Canvas.list_Layer.remove(CONS.Canvas.Layer.Rect_A);
		
		CONS.Canvas.list_Layer.add(1, CONS.Canvas.Layer.Rect_A);

		// Log
		for (int i = 0; i < CONS.Canvas.list_Layer.size(); i++) {
			
			msg_Log = String.format(
					Locale.JAPAN,
					"layer %d => %s", 
					i,
					CONS.Canvas.list_Layer.get(i).toString()
				);
			
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}

	}//_identify__Other
	
	private static void
	_identify__Cir_A
	(Activity actv) {
		// TODO Auto-generated method stub
		
		String msg_Log;
		
		////////////////////////////////
		
		// obj => not Rect_A
		
		////////////////////////////////
		
		// Log
		msg_Log = "is in => Cir_A";
		Log.i("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////
		
		// set: enum
		
		////////////////////////////////
		CONS.Canvas.currentObj = CONS.Canvas.ChosenObj.Cir_A;
		
		////////////////////////////////
		
		// update: layer
		
		////////////////////////////////
		CONS.Canvas.list_Layer.remove(CONS.Canvas.Layer.Cir_A);
		
		CONS.Canvas.list_Layer.add(0, CONS.Canvas.Layer.Cir_A);
		
		// Log
		for (int i = 0; i < CONS.Canvas.list_Layer.size(); i++) {
			
			msg_Log = String.format(
					Locale.JAPAN,
					"layer %d => %s", 
					i,
					CONS.Canvas.list_Layer.get(i).toString()
					);
			
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}
		
	}//_identify__Other__Cir_A
	


	private static void 
	_identify__Rect_A
	(Activity actv) {
		// TODO Auto-generated method stub
		
		String msg_Log;
		
		////////////////////////////////

		// set: enum

		////////////////////////////////
		CONS.Canvas.currentObj = CONS.Canvas.ChosenObj.Rect_A;
		
		////////////////////////////////

		// update: layer

		////////////////////////////////
		CONS.Canvas.list_Layer.remove(CONS.Canvas.Layer.Rect_A);
		
		CONS.Canvas.list_Layer.add(0, CONS.Canvas.Layer.Rect_A);
		
		// Log
		for (int i = 0; i < CONS.Canvas.list_Layer.size(); i++) {
			
			msg_Log = String.format(
					Locale.JAPAN,
					"layer %d => %s", 
					i,
					CONS.Canvas.list_Layer.get(i).toString()
				);
			
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}
		
	}//_identify__Rect_A

	private static void
	_identify__Rect_B
	(Activity actv) {
		// TODO Auto-generated method stub
		
		String msg_Log;
		
		////////////////////////////////
		
		// set: enum
		
		////////////////////////////////
		CONS.Canvas.currentObj = CONS.Canvas.ChosenObj.Rect_B;
		
		////////////////////////////////
		
		// update: layer
		
		////////////////////////////////
		CONS.Canvas.list_Layer.remove(CONS.Canvas.Layer.Rect_B);
		
		CONS.Canvas.list_Layer.add(0, CONS.Canvas.Layer.Rect_B);
		
		// Log
		for (int i = 0; i < CONS.Canvas.list_Layer.size(); i++) {
			
			msg_Log = String.format(
					Locale.JAPAN,
					"layer %d => %s", 
					i,
					CONS.Canvas.list_Layer.get(i).toString()
					);
			
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}
		
	}//_identify__Rect_A
	
	private static void
	_identify__Rect_C
	(Activity actv) {
		// TODO Auto-generated method stub
		
		String msg_Log;
		
		////////////////////////////////
		
		// set: enum
		
		////////////////////////////////
		CONS.Canvas.currentObj = CONS.Canvas.ChosenObj.Rect_C;
		
		////////////////////////////////
		
		// update: layer
		
		////////////////////////////////
		CONS.Canvas.list_Layer.remove(CONS.Canvas.Layer.Rect_C);
		
		CONS.Canvas.list_Layer.add(0, CONS.Canvas.Layer.Rect_C);
		
		// Log
		for (int i = 0; i < CONS.Canvas.list_Layer.size(); i++) {
			
			msg_Log = String.format(
					Locale.JAPAN,
					"layer %d => %s", 
					i,
					CONS.Canvas.list_Layer.get(i).toString()
					);
			
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}
		
	}//_identify__Rect_A
	

	public static void 
	save_Canvas
	(Activity actv) {
		// TODO Auto-generated method stub
		
		if (CV.can != null) {
			
			// Log
			String msg_Log = "CV.can.getHeight() => " + CV.can.getHeight();
			Log.i("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			Bitmap bmpBase = 
					Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888);
//			Bitmap.createBitmap(image_width, image_height, Bitmap.Config.ARGB_8888);
			
			CV.can.setBitmap(bmpBase);
			
			String dpath_Data = "/mnt/sdcard-ext/cp/data";
			
			File dir_Data = new File(dpath_Data);
			
			if (!dir_Data.exists()) {
				
				boolean res = dir_Data.mkdirs();
				
				if (res == true) {
					
					// Log
					msg_Log = "dir created => " + dpath_Data;
					Log.d("Methods.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", msg_Log);
					
				} else {

					// Log
					msg_Log = "dir not created => " + dpath_Data;;
					Log.d("Methods.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", msg_Log);
					
					return;
					
				}
				
			} else {
				
				// Log
				msg_Log = "dir exists => " + dpath_Data;
				Log.d("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);

			}
			
			////////////////////////////////

			// test

			////////////////////////////////
			View cv = (View) actv.findViewById(R.id.actv_main_cv_canvas);
			
			//REF http://stackoverflow.com/questions/18550754/i-want-to-save-my-canvas-drawing-to-a-bitmap-image answered Aug 31 '13 at 18:10
			Bitmap bmp = cv.getDrawingCache();
			
			if (bmp == null) {
				
				// Log
				msg_Log = "bmp => null";
				Log.e("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
				return;
				
			}
			////////////////////////////////

			// output

			////////////////////////////////
			String fname = dpath_Data + "/image_"
						+ Methods.get_TimeLabel(Methods.getMillSeconds_now())
						+ ".jpg";
			
			try {
				
				FileOutputStream fos = new FileOutputStream(fname);
				
				//REF http://stackoverflow.com/questions/2174875/image-on-canvas-to-jpeg-file answered Mar 7 '12 at 11:14
//				bmpBase.compress(Bitmap.CompressFormat.JPEG, 100, fos);
				bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
				
				fos.flush();
				fos.close();
				fos = null;
				
				// Log
				msg_Log = "FileOutputStream => done";
				Log.d("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
					
					
		} else {
			
			// Log
			String msg_Log = "CV.can => null";
			Log.i("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);

		}
		
	}//save_Canvas

	public static void 
	save_Canvas_2
	(Activity actv) {
		// TODO Auto-generated method stub

		String msg_Log;
		
		String dpath_Data = CONS.DB.dPath_Image;
//		String dpath_Data = "/mnt/sdcard-ext/cp/data";
		
		File dir_Data = new File(dpath_Data);
		
		if (!dir_Data.exists()) {
			
			boolean res = dir_Data.mkdirs();
			
			if (res == true) {
				
				// Log
				msg_Log = "dir created => " + dpath_Data;
				Log.d("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
			} else {
				
				// Log
				msg_Log = "dir not created => " + dpath_Data;;
				Log.d("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
				return;
				
			}
			
		} else {
			
			// Log
			msg_Log = "dir exists => " + dpath_Data;
			Log.d("Methods.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + "]", msg_Log);
			
		}
		
		////////////////////////////////
		
		// test
		
		////////////////////////////////
		View cv = (View) actv.findViewById(R.id.actv_main_cv_canvas);

		// Log
//		msg_Log = "cv.getHeight() => " + cv.getHeight();
		msg_Log = String.format(
					Locale.JAPAN,
					"w = %d / h = %d",
					cv.getWidth(),
					cv.getHeight()
					);
					
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		Bitmap bmp =  Bitmap.createBitmap (400, 500, Bitmap.Config.ARGB_8888);

		Canvas canvas = new Canvas(bmp);

		canvas.drawColor(Color.WHITE);
		
		cv.draw(canvas);
		
		// Log
		msg_Log = "canvas => drawn";
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////

		// test: dimensions

		////////////////////////////////
//		Methods.get_ViewDimensions(actv);
		
		////////////////////////////////
		
		// output
		
		////////////////////////////////
		String fname = StringUtils.join(
				new String[]{
					
						CONS.DB.dPath_Image,
						CONS.DB.fname_Image_trunk 
							+ Methods.get_TimeLabel(Methods.getMillSeconds_now())
							+ CONS.DB.fname_Image_ext
				}, 
				File.separator
		);
//				dpath_Data + "/image_"
//				+ Methods.get_TimeLabel(Methods.getMillSeconds_now())
//				+ ".jpg";
		
		try {
			
			FileOutputStream fos = new FileOutputStream(fname);
			
			//REF http://stackoverflow.com/questions/2174875/image-on-canvas-to-jpeg-file answered Mar 7 '12 at 11:14
//				bmpBase.compress(Bitmap.CompressFormat.JPEG, 100, fos);
			bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
			
			fos.flush();
			fos.close();
			fos = null;
			
			// Log
			msg_Log = "FileOutputStream => done";
			Log.d("Methods.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + "]", msg_Log);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}//save_Canvas

	//REF http://www.sherif.mobi/2013/01/how-to-get-widthheight-of-view.html
	public static void
	get_ViewDimensions(Activity actv) {
		
		final View cv = (View) actv.findViewById(R.id.actv_main_cv_canvas);		
		
		cv.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@Override
			public void onGlobalLayout() {
				// TODO Auto-generated method stub
				
				int w = cv.getWidth();
				int h = cv.getHeight();
				
				// Log
				String msg_Log = String.format(Locale.JAPAN, "w = %d, h = %d", w, h);
				Log.d("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
			}
			
		});
		
	}


	public static void 
	setup_Bluetooth
	(Activity actv) {
		// TODO Auto-generated method stub
		
		String msg_Log;
		
		////////////////////////////////

		// validate: bluetooth

		////////////////////////////////
		BluetoothAdapter Bt = BluetoothAdapter.getDefaultAdapter();

		if(!Bt.equals(null)){
		    //Bluetooth対応端末の場合の処理
			// Log
			msg_Log = "bluetooth => available";
			Log.i("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
		    
		}else{
		    //Bluetooth非対応端末の場合の処理
			
			// Log
			msg_Log = "bluetooth => not available";
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}
		
		////////////////////////////////

		// validate: bt => on

		////////////////////////////////
		boolean btEnable = Bt.isEnabled();
		
	    if(btEnable == true) {
	    	
			// Log
			msg_Log = "bluetooth => on";
			Log.i("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
	    	
	    } else {
	    	
//	    	String msg = "bluetooth => please turn on";
//			Methods_dlg.dlg_ShowMessage(actv, msg);
	    	
	    	Intent btOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
	        actv.startActivityForResult(btOn, CONS.BT.REQUEST_ENABLE_BLUETOOTH);
	    	
	    }
		
	}//setup_Bluetooth


	public static void 
	discover_Devices
	(Activity actv,
		BroadcastReceiver devieFoundReceiver) {
		// TODO Auto-generated method stub
		
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        
//        startActivity(discoverableIntent);
        actv.startActivityForResult(discoverableIntent, CONS.Intent.REQUEST_CODE_DISCOEVERABLE);
        
        // Log
		String msg_Log = "ACTION_REQUEST_DISCOVERABLE => started";
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

    	TextView nonPairedListTitle = (TextView) actv.findViewById(R.id.nonPairedListTitle);
    	nonPairedListTitle.setText("list of devices with no history");

//    	if(item.getItemId() == Menu.FIRST){
    		//�C���e���g�t�B���^�[��BroadcastReceiver�̓o�^
	        IntentFilter filter = new IntentFilter();
	        filter.addAction(CONS.BT.ACTION_DISCOVERY_STARTED);
	        filter.addAction(CONS.BT.ACTION_FOUND);
	        filter.addAction(CONS.BT.ACTION_NAME_CHANGED);
	        filter.addAction(CONS.BT.ACTION_DISCOVERY_FINISHED);
	        actv.registerReceiver(devieFoundReceiver, filter);
	        
	        CONS.BT.registered = true;
	        
	        // Log
			msg_Log = "receiver => registered";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
	        
    		CONS.BT.nonPairedDeviceAdapter = new ArrayAdapter<String>(actv, R.layout.rowdata);
	        //�ڑ��\�ȃf�o�C�X�����o
	        if(CONS.BT.mBtAdapter.isDiscovering()){
	        	//�������̏ꍇ�͌��o���L�����Z������
	        	CONS.BT.mBtAdapter.cancelDiscovery();
	        }
	        //�f�o�C�X����������
	        //��莞�Ԃ̊Ԍ��o���s��
	        CONS.BT.mBtAdapter.startDiscovery();
		
	}//discover_Devices

	public static void 
	start_Activity_ShowLogActv
	(Activity actv, String itemName) {
		
		
		// Log
		String msg_Log = "itemName => " + itemName;
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		Intent i = new Intent();
		
		i.setClass(actv, ShowLogActv.class);

		i.putExtra(CONS.Intent.iKey_LogActv_LogFileName, itemName);
		
		i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		
		actv.startActivity(i);
		
		
	}//start_Activity_LogActv

	public static List<String> 
	get_LogLines
	(Activity actv, String fpath_LogFile) {
		
		
		int count_Lines = 0;
		int count_Read = 0;
		
		List<String> list = new ArrayList<String>();
		
//		File f = new File(fpath_LogFile);
		
		try {
			
//			fis = new FileInputStream(fpath_Log);

			//REF BufferedReader http://stackoverflow.com/questions/7537833/filereader-for-text-file-in-android answered Sep 24 '11 at 8:29
			BufferedReader br = new BufferedReader(
						new InputStreamReader(new FileInputStream(fpath_LogFile)));
//			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			
			String line = null;
			
			line = br.readLine();
					
			while(line != null) {
				
				list.add(line);
				
				count_Lines += 1;
				count_Read += 1;
				
				line = br.readLine();
				
			}
			
			////////////////////////////////

			// close

			////////////////////////////////
			br.close();
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
			String msg = "FileNotFoundException";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			return null;
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
			count_Lines += 1;
			
		}

		// Log
		String msg_Log = String.format(
							Locale.JAPAN,
							"count_Lines => %d / count_Read => %d", 
							count_Lines, count_Read);
		
		Log.d("ShowLogActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

		
		return list;
		
	}//get_LogLines

	public static List<LogItem> 
	conv_LogLinesList_to_LogItemList
	(Activity actv, List<String> list_RawLines) {
		
		String msg_Log;
		
		List<LogItem> list_LogItems = new ArrayList<LogItem>();
		
		String reg = "\\[(.+?)\\] \\[(.+?)\\] (.+)";
//		String reg = "\\[(.+)\\] \\[(.+)\\] (.+)";
		
		Pattern p = Pattern.compile(reg);
		
		Matcher m = null;
		
		LogItem loi = null;
		
		for (String string : list_RawLines) {
			
			m = p.matcher(string);
			
			if (m.find()) {

				loi = _build_LogItem_from_Matcher(actv, m);
				
				if (loi != null) {
					
					list_LogItems.add(loi);
					
				}
				
			}//if (m.find())
			
		}//for (String string : list_RawLines)
		
		/******************************
			validate
		 ******************************/
		if (list_LogItems.size() < 1) {
			
			// Log
			msg_Log = "list_LogItems.size() => " + list_LogItems.size();
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
		}
		
		return list_LogItems;
		
	}//conv_LogLinesList_to_LogItemList

	/******************************
		@return
			null => Matcher.groupCount() != 3
	 ******************************/
	private static LogItem 
	_build_LogItem_from_Matcher
	(Activity actv, Matcher m) {
		
	
		////////////////////////////////
	
		// validate
	
		////////////////////////////////
		if (m.groupCount() != 3) {
			
			return null;
			
		}
		
		////////////////////////////////
	
		// prep: data
	
		////////////////////////////////
		String[] tokens_TimeLabel = m.group(1).split(" ");
		
		String[] tokens_FileInfo = m.group(2).split(" : ");
		
		String text = m.group(3);
		
		String date = tokens_TimeLabel[0];
		
		String time = tokens_TimeLabel[1].split("\\.")[0];
		
		String fileName = tokens_FileInfo[0];
		
		String line = tokens_FileInfo[1];
		
		////////////////////////////////
	
		// LogItem
	
		////////////////////////////////
		LogItem loi = new LogItem.Builder()
					
					.setDate(date)
					.setTime(time)
					.setMethod(fileName)
					.setLine(Integer.parseInt(line))
					.setText(text)
					.build();
		
		return loi;
		
	}//_build_LogItem_from_Matcher

	public static void 
	start_Activity_LogActv
	(Activity actv, Dialog d1) {
		// TODO Auto-generated method stub
		Intent i = new Intent();
		
		i.setClass(actv, LogActv.class);
		
		i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		
		actv.startActivity(i);
		
		////////////////////////////////

		// dismiss

		////////////////////////////////
		d1.dismiss();
		
	}//start_Activity_LogActv

	public static void 
	start_Activity_TopolActv
	(Activity actv, Dialog d1) {
		// TODO Auto-generated method stub
		Intent i = new Intent();
		
		i.setClass(actv, TopolActv.class);
		
		i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		
		actv.startActivity(i);
		
		////////////////////////////////
		
		// dismiss
		
		////////////////////////////////
		d1.dismiss();
		
	}//start_Activity_TopolActv
	
	public static void 
	write_Log
	(Activity actv, String message,
			String fileName, int lineNumber) {
		
		////////////////////////////////

		// validate: dir exists

		////////////////////////////////
		File dpath_Log = new File(CONS.DB.dPath_Log);
		
		if (!dpath_Log.exists()) {
			
			dpath_Log.mkdirs();
			
			// Log
			String msg_Log = "log dir => created: " + dpath_Log.getAbsolutePath();
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} else {
			
			// Log
			String msg_Log = "log dir => exists: " + dpath_Log.getAbsolutePath();
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}
		
		////////////////////////////////

		// file

		////////////////////////////////
		File fpath_Log = new File(CONS.DB.dPath_Log, CONS.DB.fname_Log);
		
		////////////////////////////////

		// file exists?

		////////////////////////////////
		if (!fpath_Log.exists()) {
			
			try {
				
				fpath_Log.createNewFile();
				
			} catch (IOException e) {
				
				e.printStackTrace();
				
				String msg = "Can't create a log file";
				Methods_dlg.dlg_ShowMessage_Duration(actv, msg, R.color.gold2);
				
				return;
				
			}
			
		} else {
			
			// Log
			String msg_Log = "log file => exists";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}
		
		////////////////////////////////

		// validate: size

		////////////////////////////////
		long len = fpath_Log.length();
		
		if (len > CONS.DB.logFile_MaxSize) {
		
			fpath_Log.renameTo(new File(
						fpath_Log.getParent(), 
						CONS.DB.fname_Log_Trunk
						+ "_"
//						+ Methods.get_TimeLabel(Methods.getMillSeconds_now())
						+ Methods.get_TimeLabel(fpath_Log.lastModified())
						+ CONS.DB.fname_Log_ext
						));
			
			// new log.txt
			try {
				
				fpath_Log = new File(fpath_Log.getParent(), CONS.DB.fname_Log);
//				File f = new File(fpath_Log.getParent(), CONS.DB.fname_Log);
				
				fpath_Log.createNewFile();
				
				// Log
				String msg_Log = "new log.txt => created";
				Log.d("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
				// Log
				String msg_Log = "log.txt => can't create!";
				Log.e("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
				e.printStackTrace();
				
				return;
				
			}
			
		}//if (len > CONS.DB.logFile_MaxSize)

		////////////////////////////////

		// write

		////////////////////////////////
		try {
			
			//REF append http://stackoverflow.com/questions/8544771/how-to-write-data-with-fileoutputstream-without-losing-old-data answered Dec 17 '11 at 12:37
			FileOutputStream fos = new FileOutputStream(fpath_Log, true);
//			FileOutputStream fos = new FileOutputStream(fpath_Log);
			
			String text = String.format(Locale.JAPAN,
							"[%s] [%s : %d] %s\n", 
							Methods.conv_MillSec_to_TimeLabel(
											Methods.getMillSeconds_now()),
							fileName, lineNumber,
							message
						);
			
			//REF getBytes() http://www.adakoda.com/android/000240.html
			fos.write(text.getBytes());
//			fos.write(message.getBytes());
			
//			fos.write("\n".getBytes());
			
			fos.close();
			
			// Log
			String msg_Log = "log => written";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
//			FileChannel oChannel = new FileOutputStream(fpath_Log).getChannel();
//			
//			oChannel.wri
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}//write_Log

}//public class Methods

