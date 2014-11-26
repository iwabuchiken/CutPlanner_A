package cp.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cp.main.BluetoothServerThread;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.TextView;

public class CONS {

//	// Sort order
//	public static enum SORT_ORDER {
//			ASC, DEC,
//			CREATED_AT,
//	};

	
	

	// Table => show_history
//	public static String tname_show_history = "show_history";
	
	public static enum MoveMode {
		// TIListAdapter.java
		ON, OFF,
		
	}//public static enum MoveMode

	public static class Intent {
		
		////////////////////////////////

		// commons

		////////////////////////////////
		public static long dflt_LongExtra_value = -1;
		
		public static int dflt_IntExtra_value = -1;
		
		
		/***************************************
		 * Request codes
		 ***************************************/
		public final static int REQUEST_CODE_SEE_BOOKMARKS = 0;
		
		public final static int REQUEST_CODE_HISTORY = 1;
		
		public final static int REQUEST_CODE_DISCOEVERABLE = 2;
		
		/***************************************
		 * Result code
		 ***************************************/
		public final static int RESULT_CODE_SEE_BOOKMARKS_OK = 1;
		
		public final static int RESULT_CODE_SEE_BOOKMARKS_CANCEL = 0;
		
	}//public static class Intent
	
	public static class DB {
		////////////////////////////////

		// Paths and names

		////////////////////////////////
		public static String dbName = "cp.db";
		
		public static String dPath_dbFile;
//		public static String dPath_dbFile = "/data/data/cm7.main/databases";
		
		public final static String dPath_Data_Root = "/mnt/sdcard-ext/cp_data";
		
		public static String dPath_dbFile_Backup = dPath_Data_Root + "/cp_backup";
		
		public final static String dPath_Data = dPath_Data_Root + "/data";
		
		public final static String dPath_Log = dPath_Data_Root + "/log";
				
		public final static String dPath_Image = dPath_Data_Root + "/image";
		
		public static String fname_DB_Backup_Trunk = "cp_backup";
		
		public static String fname_DB_Backup_ext = ".bk";
		
		public static String fname_Image_trunk = "image_";
		
		public static String fname_Image_ext = ".jpg";
		
		////////////////////////////////
		
		// Table: cm7
		
		////////////////////////////////
		public static final String tname_CM7 = "cm7";

		public static final String[] col_names_CM7 = {
			
			"file_name", "file_path",	// 0, 1
			"title", "memo",			// 2, 3
			"last_played_at",			// 4
			"table_name",				// 5
			"length",					// 6
			"audio_created_at"			// 7
			
		};
		
		public static final String[] col_names_CM7_full = {
			
			android.provider.BaseColumns._ID,	// 0
			"created_at", "modified_at",		// 1, 2
			"file_name", "file_path",			// 3, 4
			"title", "memo",					// 5, 6
			"last_played_at",					// 7
			"table_name",						// 8
			"length",							// 9
			"audio_created_at"					// 10
			
		};

		public static final String[] col_types_CM7 = {
			"TEXT", "TEXT",			// 0, 1
			"TEXT", "TEXT",			// 2,3
			"TEXT",					// 4
			"TEXT",					// 5
			"TEXT",				// 6
//			"INTEGER",				// 6
			"TEXT"					// 7
		};
		
		
		////////////////////////////////

		// Table: BM (bookmark)

		////////////////////////////////
		public static final String tname_BM = "bm";

		public static final String[] col_names_BM = {
			"ai_id", "position", 			// 0,1
			"title", "memo", "aiTableName"	// 2,3,4
		};
		
		public static final String[] col_names_BM_full = {
			android.provider.BaseColumns._ID,		// 0
			"created_at", "modified_at",			// 1,2
			"ai_id", "position",					// 3,4
			"title", "memo", "aiTableName"			// 5,6,7
		};

		public static final String[] col_types_BM = {
//			"INTEGER", "INTEGER",			// 0,1
			"INTEGER", "TEXT",			// 0,1
			"TEXT", "TEXT", "TEXT"			// 2,3,4
		};

		////////////////////////////////
		
		// Table: refresh_history
		
		////////////////////////////////
		public static String tname_RefreshHistory = "refresh_history";
		
		public static String[] col_names_RefreshHistory = {
			"last_refreshed", "num_of_items_added"
		};
		
		public static String[] col_names_RefreshHistory_full = {
			android.provider.BaseColumns._ID,		// 0
			"created_at", "modified_at",			// 1,2
			"last_refreshed", "num_of_items_added"	// 3,4
		};
		
		public static String[] col_types_RefreshHistory = {
			"TEXT", 			"INTEGER"
//			"INTEGER", 			"INTEGER"
		};

		////////////////////////////////
		
		// Table: memo_patterns
		
		////////////////////////////////
		public static String tname_MemoPatterns = "memo_patterns";
		
		public static String[] col_names_MemoPatterns = {
			"word"
		};
		
		public static String[] col_names_MemoPatterns_full = {
			android.provider.BaseColumns._ID,		// 0
			"created_at", "modified_at",			// 1,2
			"word"									// 3
		};
		
		public static String[] col_types_MemoPatterns = {
			"TEXT"
//			"INTEGER", 			"INTEGER"
		};
		

		////////////////////////////////

		// Others

		////////////////////////////////
		public static String jointString_TableName = "__";
		
		public static int pastXDays		= -10;

		////////////////////////////////

		// FileFilter

		////////////////////////////////
		public static enum FFType {
			
			RefreshHistory
		}
		
		
	}//public static class DB

	public static class Pref {
		////////////////////////////////

		// Commons

		////////////////////////////////
		public static long dflt_LongExtra_value = -1;
		
		public static int dflt_IntExtra_value = -1;
		
		////////////////////////////////

		// PlayActv.java

		////////////////////////////////
		public static final String pname_PlayActv = "pname_PlayActv";
		
		public static final String pkey_PlayActv_CurrentPosition = 
							"pkey_PlayActv_CurrentPosition";
		
		public static final String pkey_PlayActv_CurrentFileName = 
							"pkey_PlayActv_CurrentFileName";
		
		////////////////////////////////

		// MainActv.java

		////////////////////////////////
		
		public static SharedPreferences prefs_MainActv;
		
		public static String pname_MainActv = "main_activity";
//		public static String pname_CurrentPath = "current_path";
		
		public static String pkey_CurrentPath = "pkey_CurrentPath";
		
		public static String pkey_CurrentPosition = "pkey_CurrentPosition";
		
		////////////////////////////////

		// ALActv

		////////////////////////////////
		public static SharedPreferences prefs_ALActv;
		
		public static String pname_ALActv = "al_activity";
		
		public static String pkey_CurrentPosition_ALActv
									= "pkey_CurrentPosition_ALActv";
		
		////////////////////////////////
		
		// BMActv
		
		////////////////////////////////
		public static SharedPreferences prefs_BMActv;
		
		public static String pname_BMActv = "al_activity";
		
		public static String pkey_CurrentPosition_BMActv
							= "pkey_CurrentPosition_BMActv";
		
		public static String pkey_LastVisiblePosition_BMActv
							= "pkey_LastVisiblePosition_BMActv";
		
	}

	public static class MainActv {
		
		
	}

	public static class Admin {
		
		public static final float DLG_WIDTH_RATIO = 0.8f;
		
		public static final String dName_backup = "cm5_backup";
		
		////////////////////////////////

		// MainActv.java

		////////////////////////////////
		public static String fname_List = "list.txt";
		
		////////////////////////////////

		// Utilities

		////////////////////////////////
		public static Vibrator vib;
		
		public static final int vibLength_click = 35;
		
		public static final String format_Date = "yyyy/MM/dd hh:mm:ss.SSS";
//		public static final String date_Format = "yyyy/MM/dd hh:mm:ss.SSS";
		
		public static final String format_Clock = "%02d:%02d";
		
	}//public static class Admin

	public static class Paths {
		////////////////////////////////

		// MainActv.java

		////////////////////////////////
		
		public static String dpath_Storage_Sdcard = "/mnt/sdcard-ext";
		
		public static String dpath_Storage_Internal = "/mnt/sdcard";
		
		public static String  dname_Base = "cm7";
		
	}
	
	public static class Retval {
		////////////////////////////////

		// Errors

		////////////////////////////////
		/******************************
			Refresh DB
		 ******************************/
		public static int CantCreateTable =		-10;
		
		public static int CantRefreshAudioDir=	-11;
		
		public static int NoNewFiles =			-12;
		
		
		
	}

	public static class Enums {
		
		public static enum SortType {
			
			FileName, POSITION,
			
		}

		// Sort order
		public static enum SortOrder {
				ASC, DEC,
				CREATED_AT,
		};

	}
	
	public static class Canvas {
		
		////////////////////////////////

		// commons

		////////////////////////////////
		public static int lineWidth		= 10;
		
		public static enum ChosenObj {
			
			Rect_A, Rect_B, Cir_A, Rect_C,
			
			Others,
			
		}
		
		public static ChosenObj currentObj;

		public static List<Layer> list_Layer = null;
		
		public static enum Layer {
			
			Cir_A, Rect_A, Rect_B, Rect_C
			
		}
		
		////////////////////////////////

		// onClick-related

		////////////////////////////////
		public static float x_Down;
		public static float y_Down;
		
		public static float x_Up;
		public static float y_Up;
		
		public static float diff_X;
		public static float diff_Y;
		
		////////////////////////////////

		// box A

		////////////////////////////////
		public static long Ax1;
		public static long Ay1;
		
		public static long AH;
		public static long AW;

		public static float[] pointsA;
		
		public static int lineWidth_A	= lineWidth;
		
		////////////////////////////////

		// box B

		////////////////////////////////
		public static long Bx1;
		public static long By1;
		
		public static long BH;
		public static long BW;
		
		public static float[] pointsB;
		
		public static int lineWidth_B	= lineWidth;
		
		////////////////////////////////
		
		// circle: A
		
		////////////////////////////////
		public static float Cir_A_X;
		public static float Cir_A_Y;
		
		public static float Cir_A_Radius;
		
		public static int LineWidth_Cir_A	= lineWidth;
		
		public static float Cir_A_Radius_dflt = 100;
		
		public static float Cir_A_X_prev;
		public static float Cir_A_Y_prev;
		
		public static float Cir_A_DOWN_X;
		public static float Cir_A_DOWN_Y;
		
		////////////////////////////////
		
		// rect: A
		
		////////////////////////////////
		public static float Rect_A_X1;
		public static float Rect_A_Y1;
		
		public static float Rect_A_X2;
		public static float Rect_A_Y2;
		
		public static float Rect_A_W;
		public static float Rect_A_H;
		
		public static int LineWidth_Rect_A	= lineWidth;
		
		public static float Rect_A_X1_prev;
		public static float Rect_A_Y1_prev;
		
		// paint
		public static Paint p_Rect_A;
		
		// flag
		public static boolean Draw_Rect_A		= false;

		// prev position
		public static float Rect_A_X_prev;
		public static float Rect_A_Y_prev;

		// distance to the base point
		public static float Rect_A_X_dist_from_base;
		public static float Rect_A_Y_dist_from_base;
		
		////////////////////////////////
		
		// rect: B
		
		////////////////////////////////
		public static float Rect_B_X1;
		public static float Rect_B_Y1;
		
		public static float Rect_B_X2;
		public static float Rect_B_Y2;
		
		public static float Rect_B_W;
		public static float Rect_B_H;
		
		public static int LineWidth_Rect_B	= lineWidth;
		
		public static float Rect_B_X1_prev;
		public static float Rect_B_Y1_prev;

		// distance to the base point
		public static float Rect_B_X_dist_from_base;
		public static float Rect_B_Y_dist_from_base;

		// paint
		public static Paint p_Rect_B;
		
		// flag
		public static boolean Draw_Rect_B		= false;
		
		////////////////////////////////
		
		// rect: C
		
		////////////////////////////////
		public static float Rect_C_X1;
		public static float Rect_C_Y1;
		
		public static float Rect_C_X2;
		public static float Rect_C_Y2;
		
		public static float Rect_C_W;
		public static float Rect_C_H;
		
		public static int LineWidth_Rect_C	= lineWidth;
		
		public static float Rect_C_X1_prev;
		public static float Rect_C_Y1_prev;
		
		// distance to the base point
		public static float Rect_C_X_dist_from_base;
		public static float Rect_C_Y_dist_from_base;
		
		// paint
		public static Paint p_Rect_C;
		
		// flag
		public static boolean Draw_Rect_C		= false;
		
		////////////////////////////////

		// paints

		////////////////////////////////
		public static Paint p1;
		
		public static Paint p_A;
		public static Paint p_B;
		
		public static Paint p_Cir_A;
		
//		public static Paint p_Rect_A;
		
		////////////////////////////////

		// flags

		////////////////////////////////
		public static boolean DrawA		= false;
		
		public static boolean DrawB		= false;
		
		public static boolean Draw_Circle_A		= false;
		
//		public static boolean Draw_Rect_A		= false;
		
	}
	
	// bluetooth-related
	public static class BT {
		
		public static final int REQUEST_ENABLE_BLUETOOTH = 0;
		
		public static ArrayAdapter<String> pairedDeviceAdapter;
		
		public static BluetoothAdapter mBtAdapter;

//		public static BluetoothAdapter mBtAdapter = CONS.BT.mBtAdapter;
		
		public static ArrayAdapter<String> nonPairedDeviceAdapter;

		public static ArrayList<BluetoothDevice> foundDeviceList = 
											new ArrayList<BluetoothDevice>();
		
		public static List<String> list_FoundDevices;
		
		public static ArrayAdapter<String> adp_FoundDeviceList;
		
		public static String myNumber;
		
		public static BluetoothServerThread BtServerThread;
			
		////////////////////////////////

		// intent filter

		////////////////////////////////
		public static final String ACTION_DISCOVERY_STARTED = BluetoothAdapter.ACTION_DISCOVERY_STARTED;
		public static final String ACTION_FOUND = BluetoothDevice.ACTION_FOUND;
		public static final String ACTION_NAME_CHANGED = BluetoothDevice.ACTION_NAME_CHANGED;
		public static final String ACTION_DISCOVERY_FINISHED = BluetoothAdapter.ACTION_DISCOVERY_FINISHED;

		
	}
}//public class CONS
