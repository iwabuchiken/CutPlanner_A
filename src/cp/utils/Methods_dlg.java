package cp.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import cp.adapters.Adp_ListItems;
import cp.items.ListItem;
import cp.items.WordPattern;
import cp.listeners.dialog.DB_OCL;
import cp.listeners.dialog.DB_OTL;
import cp.listeners.dialog.DOI_CL;
import cp.main.R;
import cp.utils.Tags.DialogTags;

import android.app.Activity;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Methods_dlg {

	public static
	Dialog dlg_Template_Cancel
	(Activity actv, int layoutId, int titleStringId,
			int cancelButtonId, Tags.DialogTags cancelTag) {
		/****************************
		* Steps
		* 1. Set up
		* 2. Add listeners => OnTouch
		* 3. Add listeners => OnClick
		****************************/
		
		// 
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(layoutId);
		
		// Title
		dlg.setTitle(titleStringId);
		
		/****************************
		* 2. Add listeners => OnTouch
		****************************/
		//
		Button btn_cancel = (Button) dlg.findViewById(cancelButtonId);
		
		//
		btn_cancel.setTag(cancelTag);
		
		//
		btn_cancel.setOnTouchListener(new DB_OTL(actv, dlg));
		
		/****************************
		* 3. Add listeners => OnClick
		****************************/
		//
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg));
		
		//
		//dlg.show();
		
		return dlg;
	
	}//public static Dialog dlg_template_okCancel()
	
	public static Dialog 
	dlg_Template_Cancel_SecondDialog
	(Activity actv, Dialog dlg1,
			int layoutId, int titleStringId,
			int cancelButtonId, Tags.DialogTags cancelTag) {
		/****************************
		 * Steps
		 * 1. Set up
		 * 2. Add listeners => OnTouch
		 * 3. Add listeners => OnClick
		 ****************************/
		
		// 
		Dialog dlg2 = new Dialog(actv);
		
		//
		dlg2.setContentView(layoutId);
		
		// Title
		dlg2.setTitle(titleStringId);
		
		/****************************
		 * 2. Add listeners => OnTouch
		 ****************************/
		//
		Button btn_cancel = (Button) dlg2.findViewById(cancelButtonId);
		
		//
		btn_cancel.setTag(cancelTag);
		
		//
		btn_cancel.setOnTouchListener(new DB_OTL(actv, dlg2));
		
		/****************************
		 * 3. Add listeners => OnClick
		 ****************************/
		//
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg1, dlg2));
		
		//
		//dlg.show();
		
		return dlg2;
		
	}//public static Dialog dlg_template_okCancel()

	public static Dialog 
	dlg_Template_Cancel_ThirdDialog
	(Activity actv, Dialog dlg1, Dialog dlg2,
			int layoutId, int titleStringId,
			int cancelButtonId, Tags.DialogTags cancelTag) {
		/****************************
		 * Steps
		 * 1. Set up
		 * 2. Add listeners => OnTouch
		 * 3. Add listeners => OnClick
		 ****************************/
		
		// 
		Dialog dlg3 = new Dialog(actv);
		
		//
		dlg3.setContentView(layoutId);
		
		// Title
		dlg3.setTitle(titleStringId);
		
		/****************************
		 * 2. Add listeners => OnTouch
		 ****************************/
		//
		Button btn_cancel = (Button) dlg3.findViewById(cancelButtonId);
		
		//
		btn_cancel.setTag(cancelTag);
		
		//
		btn_cancel.setOnTouchListener(new DB_OTL(actv, dlg1, dlg2, dlg3));
		
		/****************************
		 * 3. Add listeners => OnClick
		 ****************************/
		//
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg1, dlg2, dlg3));
		
		//
		//dlg.show();
		
		return dlg3;
		
	}//public static Dialog dlg_template_okCancel()
	
	public static Dialog 
	dlg_Template_Cancel_4thDialog
	(Activity actv, 
		Dialog d1, Dialog d2, Dialog d3,
		int layoutId, int titleStringId,
		int cancelButtonId, Tags.DialogTags cancelTag) {
		/****************************
		 * Steps
		 * 1. Set up
		 * 2. Add listeners => OnTouch
		 * 3. Add listeners => OnClick
		 ****************************/
		
		// 
		Dialog d4 = new Dialog(actv);
		
		//
		d4.setContentView(layoutId);
		
		// Title
		d4.setTitle(titleStringId);
		
		/****************************
		 * 2. Add listeners => OnTouch
		 ****************************/
		//
		Button btn_cancel = (Button) d4.findViewById(cancelButtonId);
		
		//
		btn_cancel.setTag(cancelTag);
		
		//
		btn_cancel.setOnTouchListener(new DB_OTL(actv, d1, d2, d3, d4));
		
		/****************************
		 * 3. Add listeners => OnClick
		 ****************************/
		//
		btn_cancel.setOnClickListener(new DB_OCL(actv, d1, d2, d3, d4));
		
		//
		//dlg.show();
		
		return d4;
		
	}//public static Dialog dlg_template_okCancel()
	
//	public static Dialog 
//	dlg_Template_Cancel_4thDialog_Duration
//	(Activity actv, 
//		Dialog d1, Dialog d2, Dialog d3,
//		int layoutId, int titleStringId,
//		int cancelButtonId, Tags.DialogTags cancelTag,
//		int duration) {
//		/****************************
//		 * Steps
//		 * 1. Set up
//		 * 2. Add listeners => OnTouch
//		 * 3. Add listeners => OnClick
//		 ****************************/
//		aa
//		// 
//		Dialog d4 = new Dialog(actv);
//		
//		//
//		d4.setContentView(layoutId);
//		
//		// Title
//		d4.setTitle(titleStringId);
//		
//		/****************************
//		 * 2. Add listeners => OnTouch
//		 ****************************/
//		//
//		Button btn_cancel = (Button) d4.findViewById(cancelButtonId);
//		
//		//
//		btn_cancel.setTag(cancelTag);
//		
//		//
//		btn_cancel.setOnTouchListener(new DB_OTL(actv, d1, d2, d3, d4));
//		
//		/****************************
//		 * 3. Add listeners => OnClick
//		 ****************************/
//		//
//		btn_cancel.setOnClickListener(new DB_OCL(actv, d1, d2, d3, d4));
//		
//		//
//		//dlg.show();
//		
//		return d4;
//		
//	}//public static Dialog dlg_template_okCancel()
	
	public static Dialog 
	dlg_Template_OkCancel_SecondDialog
	(Activity actv, Dialog dlg1,
			int layoutId, int titleStringId,
			
			int okButtonId, Tags.DialogTags okTag,
			int cancelButtonId, Tags.DialogTags cancelTag) {
		/****************************
		 * Steps
		 * 1. Set up
		 * 2. Add listeners => OnTouch
		 * 3. Add listeners => OnClick
		 ****************************/
		
		// 
		Dialog dlg2 = new Dialog(actv);
		
		//
		dlg2.setContentView(layoutId);
		
		// Title
		dlg2.setTitle(titleStringId);
		
		////////////////////////////////
		
		// button: cancel
		
		////////////////////////////////
		Button btn_Ok = (Button) dlg2.findViewById(okButtonId);
		
		btn_Ok.setTag(okTag);
		
		btn_Ok.setOnTouchListener(new DB_OTL(actv, dlg1, dlg2));
		
		btn_Ok.setOnClickListener(new DB_OCL(actv, dlg1, dlg2));
		
		////////////////////////////////

		// button: cancel

		////////////////////////////////
		Button btn_cancel = (Button) dlg2.findViewById(cancelButtonId);
		
		btn_cancel.setTag(cancelTag);
		
		btn_cancel.setOnTouchListener(new DB_OTL(actv, dlg1, dlg2));
		
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg1, dlg2));
		
		//
		//dlg.show();
		
		return dlg2;
		
	}//public static Dialog dlg_template_okCancel()
	
	public static Dialog 
	dlg_Template_Cancel_SecondDialog
	(Activity actv, Dialog dlg1,
			int layoutId, String title,
			int cancelButtonId, Tags.DialogTags cancelTag) {
		/****************************
		 * Steps
		 * 1. Set up
		 * 2. Add listeners => OnTouch
		 * 3. Add listeners => OnClick
		 ****************************/
		
		// 
		Dialog dlg2 = new Dialog(actv);
		
		//
		dlg2.setContentView(layoutId);
		
		// Title
		dlg2.setTitle(title);
		
		/****************************
		 * 2. Add listeners => OnTouch
		 ****************************/
		//
		Button btn_cancel = (Button) dlg2.findViewById(cancelButtonId);
		
		//
		btn_cancel.setTag(cancelTag);
		
		//
		btn_cancel.setOnTouchListener(new DB_OTL(actv, dlg2));
		
		/****************************
		 * 3. Add listeners => OnClick
		 ****************************/
		//
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg1, dlg2));
		
		//
		//dlg.show();
		
		return dlg2;
		
	}//public static Dialog dlg_template_okCancel()
	
	public static void
	dlg_ShowMessage(Activity actv, String message) {
		
		Dialog dlg = Methods_dlg.dlg_Template_Cancel(
				actv, R.layout.dlg_tmpl_toast_ok, 
				R.string.generic_tv_confirm, 
				R.id.dlg_tmpl_toast_ok_bt_cancel, 
//				R.id.dlg_db_admin_bt_cancel, 
				Tags.DialogTags.GENERIC_DISMISS);
		
		TextView tv_Message = 
				(TextView) dlg.findViewById(R.id.dlg_tmpl_toast_ok_tv_message);
		
		tv_Message.setText(message);
		
		dlg.show();
		
	}
	
	public static void
	dlg_ShowMessage
	(Activity actv, String message, int colorId) {
		
		Dialog dlg = Methods_dlg.dlg_Template_Cancel(
				actv, R.layout.dlg_tmpl_toast_ok_scrollview, 
//				actv, R.layout.dlg_tmpl_toast_ok, 
				R.string.generic_tv_confirm, 
				R.id.dlg_tmpl_toast_ok_scrollview_bt_cancel, 
//				R.id.dlg_db_admin_bt_cancel, 
				Tags.DialogTags.GENERIC_DISMISS);
		
		TextView tv_Message = 
				(TextView) dlg.findViewById(R.id.dlg_tmpl_toast_ok_scrollview_tv_message);
		
		tv_Message.setText(message);
		
		////////////////////////////////

		// background

		////////////////////////////////
		tv_Message.setBackgroundColor(actv.getResources().getColor(colorId));
		
		dlg.show();
		
	}//dlg_ShowMessage

	public static void
	dlg_ShowMessage_SecondDialog
	(Activity actv, String message, Dialog dlg1) {
		
		Dialog dlg2 = Methods_dlg.dlg_Template_Cancel_SecondDialog(
				actv, dlg1,
				R.layout.dlg_tmpl_toast_ok, 
				R.string.generic_tv_confirm, 
				
				R.id.dlg_tmpl_toast_ok_bt_cancel, 
				Tags.DialogTags.GENERIC_DISMISS_SECOND_DIALOG);
		
		TextView tv_Message = 
				(TextView) dlg2.findViewById(R.id.dlg_tmpl_toast_ok_tv_message);
		
		tv_Message.setText(message);
		
		dlg2.show();
		
	}

	public static void
	dlg_ShowMessage_SecondDialog
	(Activity actv, String message, Dialog dlg1, int colorID) {
		
		Dialog d2 = Methods_dlg.dlg_Template_Cancel_SecondDialog(
				actv, dlg1,
				R.layout.dlg_tmpl_toast_ok, 
				R.string.generic_tv_confirm, 
				
				R.id.dlg_tmpl_toast_ok_bt_cancel, 
				Tags.DialogTags.GENERIC_DISMISS_SECOND_DIALOG);
		
		TextView tv_Message = 
				(TextView) d2.findViewById(R.id.dlg_tmpl_toast_ok_tv_message);
		
		tv_Message.setText(message);
		
		tv_Message.setBackgroundColor(actv.getResources().getColor(colorID));
		
		tv_Message.setTextColor(Color.WHITE);
		
		d2.show();
		
	}
	
	public static void
	dlg_ShowMessage_ThirdDialog
	(Activity actv, 
		String message, Dialog dlg1, Dialog dlg2) {
		
		Dialog dlg3 = Methods_dlg.dlg_Template_Cancel_ThirdDialog(
				actv, dlg1, dlg2,
				R.layout.dlg_tmpl_toast_ok, 
				R.string.generic_tv_confirm, 
				
				R.id.dlg_tmpl_toast_ok_bt_cancel, 
				Tags.DialogTags.GENERIC_DISMISS_THIRD_DIALOG);
		
		TextView tv_Message = 
				(TextView) dlg3.findViewById(R.id.dlg_tmpl_toast_ok_tv_message);
		
		tv_Message.setText(message);
		
		dlg3.show();
		
	}
	
	public static void
	dlg_ShowMessage_4thDialog
	(Activity actv, Dialog d1, Dialog d2, Dialog d3,
			String message, int colorID) {
		
		Dialog d4 = Methods_dlg.dlg_Template_Cancel_4thDialog(
				actv, d1, d2, d3,
				R.layout.dlg_tmpl_toast_ok, 
				R.string.generic_tv_confirm, 
				
				R.id.dlg_tmpl_toast_ok_bt_cancel, 
				Tags.DialogTags.GENERIC_DISMISS_4TH_DIALOG);
		
		TextView tv_Message = 
				(TextView) d4.findViewById(R.id.dlg_tmpl_toast_ok_tv_message);
		
		tv_Message.setText(message);
		
		d4.show();
		
	}
	
	public static Dialog 
	dlg_Template_Cancel
	(Activity actv,
			int layoutId, String title, 
			int cancelButtonId, DialogTags cancelTag) {
		// TODO Auto-generated method stub
		
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(layoutId);
		
		// Title
		dlg.setTitle(title);
		
		/****************************
		* 2. Add listeners => OnTouch
		****************************/
		//
		Button btn_cancel = (Button) dlg.findViewById(cancelButtonId);
		
		//
		btn_cancel.setTag(cancelTag);
		
		//
		btn_cancel.setOnTouchListener(new DB_OTL(actv, dlg));
		
		/****************************
		* 3. Add listeners => OnClick
		****************************/
		//
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg));
		
		//
		//dlg.show();
		
		return dlg;		
	}//dlg_Template_Cancel

	public static
	Dialog dlg_Tmpl_OkCancel_ThirdDialog
	(Activity actv, 
		int layoutId, int titleStringId,
		
		int okButtonId, int cancelButtonId,
		Tags.DialogTags okTag, Tags.DialogTags cancelTag,
		
		Dialog dlg1, Dialog dlg2) {
		/****************************
		 * Steps
		 * 1. Set up
		 * 2. Add listeners => OnTouch
		 * 3. Add listeners => OnClick
		 ****************************/
		
		// 
		Dialog dlg3 = new Dialog(actv);
		
		//
		dlg3.setContentView(layoutId);
		
		// Title
		dlg3.setTitle(titleStringId);
		
		/****************************
		 * 2. Add listeners => OnTouch
		 ****************************/
		//
		Button btn_ok = (Button) dlg3.findViewById(okButtonId);
		Button btn_cancel = (Button) dlg3.findViewById(cancelButtonId);
		
		//
		btn_ok.setTag(okTag);
		btn_cancel.setTag(cancelTag);
		
		//
		btn_ok.setOnTouchListener(new DB_OTL(actv, dlg3));
		btn_cancel.setOnTouchListener(new DB_OTL(actv, dlg3));
		
		/****************************
		 * 3. Add listeners => OnClick
		 ****************************/
		//
		btn_ok.setOnClickListener(new DB_OCL(actv, dlg1, dlg2, dlg3));
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg1, dlg2, dlg3));
		
		
		return dlg3;
		
	}//public static Dialog dlg_template_okCancel_SecondDialog()

	public static Dialog 
	dlg_Tmpl_OkCancel
	(Activity actv, 
		int layoutId, int titleStringId,
		int okButtonId, int cancelButtonId, 
		DialogTags okTag, DialogTags cancelTag) {
		/*----------------------------
		* Steps
		* 1. Set up
		* 2. Add listeners => OnTouch
		* 3. Add listeners => OnClick
		----------------------------*/
		
		// 
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(layoutId);
		
		// Title
		dlg.setTitle(titleStringId);
		
		/*----------------------------
		* 2. Add listeners => OnTouch
		----------------------------*/
		//
		Button btn_ok = (Button) dlg.findViewById(okButtonId);
		Button btn_cancel = (Button) dlg.findViewById(cancelButtonId);
		
		//
		btn_ok.setTag(okTag);
		btn_cancel.setTag(cancelTag);
		
		//
		btn_ok.setOnTouchListener(new DB_OTL(actv, dlg));
		btn_cancel.setOnTouchListener(new DB_OTL(actv, dlg));
		
		/*----------------------------
		* 3. Add listeners => OnClick
		----------------------------*/
		//
		btn_ok.setOnClickListener(new DB_OCL(actv, dlg));
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg));
		
		//
		//dlg.show();
		
		return dlg;
	
	}//public static Dialog dlg_template_okCancel()

	/******************************
		@param duration => millseconds
	 ******************************/
	public static void
	dlg_ShowMessage_Duration
	(Activity actv, String message, int duration) {
		
		final Dialog dlg = Methods_dlg.dlg_Template_Cancel(
				actv, R.layout.dlg_tmpl_toast_ok, 
				R.string.generic_tv_confirm, 
				R.id.dlg_tmpl_toast_ok_bt_cancel, 
//				R.id.dlg_db_admin_bt_cancel, 
				Tags.DialogTags.GENERIC_DISMISS);
		
		TextView tv_Message = 
				(TextView) dlg.findViewById(R.id.dlg_tmpl_toast_ok_tv_message);
		
		tv_Message.setText(message);
		
		////////////////////////////////

		// show

		////////////////////////////////
		dlg.show();
		
		//REF http://xjaphx.wordpress.com/2011/07/13/auto-close-dialog-after-a-specific-time/
		final Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                dlg.dismiss(); // when the task active then close the dialog
                t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
            }
        }, duration); // after 2 second (or 2000 miliseconds), the task will be active.
		
	}

	/******************************
		@param duration => millseconds
	 ******************************/
	public static void
	dlg_ShowMessage_Duration
	(Activity actv, String message, int colorID, int duration) {
		
		final Dialog dlg = Methods_dlg.dlg_Template_Cancel(
				actv, R.layout.dlg_tmpl_toast_ok_scrollview, 
//				actv, R.layout.dlg_tmpl_toast_ok, 
				R.string.generic_tv_confirm, 
				R.id.dlg_tmpl_toast_ok_scrollview_bt_cancel, 
//				R.id.dlg_db_admin_bt_cancel, 
				Tags.DialogTags.GENERIC_DISMISS);
		
		TextView tv_Message = 
				(TextView) dlg.findViewById(R.id.dlg_tmpl_toast_ok_scrollview_tv_message);
		
		tv_Message.setText(message);
		
		////////////////////////////////

		// background

		////////////////////////////////
//		tv_Message.setBackgroundColor(colorID);
		tv_Message.setBackgroundColor(actv.getResources().getColor(colorID));

		////////////////////////////////
		
		// show
		
		////////////////////////////////
		dlg.show();
		
		//REF http://xjaphx.wordpress.com/2011/07/13/auto-close-dialog-after-a-specific-time/
		final Timer t = new Timer();
		t.schedule(new TimerTask() {
			public void run() {
				dlg.dismiss(); // when the task active then close the dialog
				t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
			}
		}, duration); // after 2 second (or 2000 miliseconds), the task will be active.
		
	}

	/******************************
		@param duration => millseconds
	 ******************************/
	public static void
	dlg_ShowMessage_2_Duration
	(Activity actv, String message, int colorID, int duration) {
		
		final Dialog dlg = Methods_dlg.dlg_Template_Cancel(
				actv, R.layout.dlg_tmpl_toast_ok_scrollview, 
				R.string.generic_tv_confirm, 
				R.id.dlg_tmpl_toast_ok_bt_cancel, 
//				R.id.dlg_db_admin_bt_cancel, 
				Tags.DialogTags.GENERIC_DISMISS);
		
		TextView tv_Message = 
				(TextView) dlg.findViewById(R.id.dlg_tmpl_toast_ok_tv_message);
		
		tv_Message.setText(message);
		
		////////////////////////////////
		
		// background
		
		////////////////////////////////
//		tv_Message.setBackgroundColor(colorID);
		tv_Message.setBackgroundColor(actv.getResources().getColor(colorID));
		
		////////////////////////////////
		
		// show
		
		////////////////////////////////
		dlg.show();
		
		//REF http://xjaphx.wordpress.com/2011/07/13/auto-close-dialog-after-a-specific-time/
		final Timer t = new Timer();
		t.schedule(new TimerTask() {
			public void run() {
				dlg.dismiss(); // when the task active then close the dialog
				t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
			}
		}, duration); // after 2 second (or 2000 miliseconds), the task will be active.
		
	}
	
	/******************************
		@param duration => millseconds
	 ******************************/
	public static void
	dlg_ShowMessage_4thDialog_2_Duration
	(Activity actv, 
		Dialog d1, Dialog d2, Dialog d3,
		String message, int colorID, int duration) {
		
		final Dialog d4 = Methods_dlg.dlg_Template_Cancel_4thDialog(
				actv, d1, d2, d3,
				R.layout.dlg_tmpl_toast_ok_scrollview, 
				R.string.generic_tv_confirm, 
				R.id.dlg_tmpl_toast_ok_bt_cancel, 
//				R.id.dlg_db_admin_bt_cancel, 
				Tags.DialogTags.GENERIC_DISMISS_4TH_DIALOG);
		
		TextView tv_Message = 
				(TextView) d4.findViewById(R.id.dlg_tmpl_toast_ok_tv_message);
		
		tv_Message.setText(message);
		
		////////////////////////////////
		
		// background
		
		////////////////////////////////
//		tv_Message.setBackgroundColor(colorID);
		tv_Message.setBackgroundColor(actv.getResources().getColor(colorID));
		
		////////////////////////////////
		
		// show
		
		////////////////////////////////
		d4.show();
		
		//REF http://xjaphx.wordpress.com/2011/07/13/auto-close-dialog-after-a-specific-time/
		final Timer t = new Timer();
		t.schedule(new TimerTask() {
			public void run() {
				d4.dismiss(); // when the task active then close the dialog
				t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
			}
		}, duration); // after 2 second (or 2000 miliseconds), the task will be active.
		
	}
	
	public static void
	dlg_ShowMessage_ThirdDialog
	(Activity actv, 
			String message, Dialog dlg1, Dialog dlg2, int colorID) {
		
		Dialog dlg3 = Methods_dlg.dlg_Template_Cancel_ThirdDialog(
				actv, dlg1, dlg2,
				R.layout.dlg_tmpl_toast_ok, 
				R.string.generic_tv_confirm, 
				
				R.id.dlg_tmpl_toast_ok_bt_cancel, 
				Tags.DialogTags.GENERIC_DISMISS_THIRD_DIALOG);
		
		TextView tv_Message = 
				(TextView) dlg3.findViewById(R.id.dlg_tmpl_toast_ok_tv_message);
		
		tv_Message.setBackgroundColor(actv.getResources().getColor(colorID));
		
		tv_Message.setTextColor(Color.WHITE);
		
		tv_Message.setText(message);
		
		dlg3.show();
		
	}
	
	public static void 
	dlg_ACTV_MAIN_Admin
	(Activity actv) {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// vars

		////////////////////////////////
		String msg_Log;
		
		
		////////////////////////////////

		// dlg

		////////////////////////////////
		Dialog d1 = Methods_dlg.dlg_Template_Cancel(
						actv,
						R.layout.dlg_tmpl_cancel_lv,
						R.string.dlg_actvmain_admin_title,
						
						R.id.dlg_tmpl_cancel_lv_bt_cancel,
						Tags.DialogTags.GENERIC_DISMISS);
		
		/****************************
		* 2. Prep => List
		****************************/
//		String[] choices = {
//					actv.getString(R.string.dlg_actvmain_lv_delete),
//					};
		
		List<ListItem> list = new ArrayList<ListItem>();
//		List<String> list = new ArrayList<String>();
		
		list.add(new ListItem.Builder()
						.setText(actv.getString(
								R.string.dlg_actvmain_admin_item_see_log))
								.setIconID(R.drawable.menu_icon_admin_32x32_yellow)
								.setTextColor_ID(R.color.yellow_dark)
								.build());
//		list.add(actv.getString(R.string.dlg_actvmain_admin_item_restore_db));
//		list.add(actv.getString(R.string.dlg_actvmain_admin_item_operations));
		
		/****************************
		* 3. Adapter
		****************************/
		Adp_ListItems adapter = new Adp_ListItems(
							actv,
							//R.layout.dlg_db_admin,
							R.layout.list_row_simple_iv_1,
							//android.R.layout.simple_list_item_1,
							list
		);
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//							actv,
//							//R.layout.dlg_db_admin,
//							R.layout.list_row_simple_1,
//							//android.R.layout.simple_list_item_1,
//							list
//		);
		
		/****************************
		* 4. Set adapter
		****************************/
		ListView lv = (ListView) d1.findViewById(R.id.dlg_tmpl_cancel_lv_lv);
		
		lv.setAdapter(adapter);
		
		/****************************
		* 5. Set listener to list
		****************************/
		lv.setTag(Tags.DialogItemTags.ACTV_MAIN_ADMIN_LV);
		
		lv.setOnItemClickListener(new DOI_CL(actv, d1));
		
		/***************************************
		* Modify the list view height
		***************************************/
//		lv.setLayoutParams(
//				new LinearLayout.LayoutParams(
//						300,	//	Width
//						LayoutParams.WRAP_CONTENT	//	Height
//				));
		
		/***************************************
		* Modify: Button layout
		***************************************/
		LinearLayout llButton =
					(LinearLayout) d1.findViewById(R.id.dlg_tmpl_cancel_lv_ll_filepath);
//		(LinearLayout) dlg1.findViewById(R.id.actv_imp_ll_filepath);
		
		LinearLayout.LayoutParams params =
				new LinearLayout.LayoutParams(
								LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT);
		
		params.gravity = Gravity.CENTER_HORIZONTAL;
		
		llButton.setLayoutParams(params);

		////////////////////////////////

		// get: screen size

		////////////////////////////////
		//REF size http://stackoverflow.com/questions/19155559/how-to-get-android-device-screen-size answered Oct 3 '13 at 10:00
		DisplayMetrics displayMetrics = actv.getResources()
                			.getDisplayMetrics();
		
		int w = displayMetrics.widthPixels;
		
//		// Log
//		String msg_Log = "w => " + w;
//		Log.d("Methods_dlg.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		int dialog_Width = w * CONS.Admin.ratio_Dialog_to_Screen_W / 100;
		
//		// Log
//		msg_Log = "dialog_Width => " + dialog_Width;
//		Log.d("Methods_dlg.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		////////////////////////////////

		// linear layot: main

		////////////////////////////////
		LinearLayout ll_Main = (LinearLayout) d1.findViewById(R.id.dlg_tmpl_cancel_lv_ll_main);
		
		// Log
		msg_Log = "ll_Main => created";
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		//REF parent layout http://stackoverflow.com/questions/4631966/set-relativelayout-layout-params-programmatically-throws-classcastexception answered Jan 8 '11 at 5:42
//		08-21 11:30:45.434: E/AndroidRuntime(20722): java.lang.ClassCastException: android.widget.LinearLayout$LayoutParams
//		08-21 11:30:45.434: E/AndroidRuntime(20722): 	at android.widget.FrameLayout.onLayout(FrameLayout.java:293)
//		08-21 11:30:45.434: E/AndroidRuntime(20722): 	at android.view.View.layout(View.java:7184)

		FrameLayout.LayoutParams params2 =
				new FrameLayout.LayoutParams(
//						LinearLayout.LayoutParams params2 =
//						new LinearLayout.LayoutParams(
						dialog_Width,
//						400,
//						200,
//						LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT);
		
		// Log
		msg_Log = "setting params...";
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		ll_Main.setLayoutParams(params2);
		
		// Log
		msg_Log = "params => set";
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		/****************************
		* 6. Show dialog
		****************************/
		d1.show();
		
		
	}//dlg_ACTV_MAIN_Admin

	public static void 
	dlg_filter_ShowLogList
	(Activity actv) {
		// TODO Auto-generated method stub
		boolean res;
		
		////////////////////////////////

		// get dialog

		////////////////////////////////
		Dialog d = _filter_ShowList__GetDialog(actv);

		////////////////////////////////

		// gridview

		////////////////////////////////
//		res = _filter_ShowList__GridView(actv, d);

		////////////////////////////////

		// set previous string

		////////////////////////////////
		res = _filter_ShowList__SetString(actv, d);
		
		////////////////////////////////

		// show

		////////////////////////////////
		d.show();
		
	}//filter_ShowList

	/******************************
		@return
			false => pref val is null<br>
	 ******************************/
	private static boolean 
	_filter_ShowList__SetString
	(Activity actv, Dialog d) {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// get pref

		////////////////////////////////
		String pref_FilterString = Methods.get_Pref_String(
						actv, 
						CONS.Pref.pname_MainActv, 
						CONS.Pref.pkey_ShowListActv_Filter_String, 
						null);
		
		if (pref_FilterString == null) {
			
			// Log
			String msg_Log = "pref_FilterString => null";
			Log.d("Methods_dlg.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return false;
			
		}
		
		////////////////////////////////

		// set: text

		////////////////////////////////
		EditText et = (EditText) d.findViewById(R.id.dlg_filter_showlist_et_content);
		
		et.setText(pref_FilterString);
		
		////////////////////////////////

		// selection

		////////////////////////////////
		et.setSelection(pref_FilterString.length());
		
		return true;
		
	}//_filter_ShowList__SetString

	/******************************
		@return
			false 1. Can't get list<br>
				2. Can't build adapter<br>
	 ******************************/
//	private static boolean 
//	_filter_ShowList__GridView
//	(Activity actv, Dialog d) {
//		// TODO Auto-generated method stub
//		
//		////////////////////////////////
//
//		// Get: GV
//
//		////////////////////////////////
//		GridView gv_Patterns =
//				(GridView) d.findViewById(R.id.dlg_filter_showlist_gv);
//
//		////////////////////////////////
//
//		// get list
//
//		////////////////////////////////
//		List<WordPattern> list_WPs = DBUtils.find_All_WP(actv);
//		
//		if (list_WPs == null) {
//			
//			String msg = "Can't get list";
//			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
//			
//			return false;
//			
//		}
//		
//		////////////////////////////////
//
//		// sort
//
//		////////////////////////////////
//		Collections.sort(
//				list_WPs, 
//				new Comp_WP(
//						
//						CONS.Enums.SortType.WORD,
//						CONS.Enums.SortOrder.ASC
//				));
//
//		Collections.sort(
//				list_WPs, 
//				new Comp_WP(
//						
//						CONS.Enums.SortType.USED,
//						CONS.Enums.SortOrder.DESC
//						));
//		
//		
//		////////////////////////////////
//
//		// Adapter
//
//		////////////////////////////////
//		CONS.ShowListActv.adp_List_WPs = new Adp_WordPatterns(
//				actv,
//				R.layout.list_row_gv,
//				list_WPs
//				);
//
//		if (CONS.ShowListActv.adp_List_WPs == null) {
//			
//			String msg = "Can't build adapter";
//			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
//			
//			return false;
//			
//		}
//
//		////////////////////////////////
//
//		// set: adapter
//
//		////////////////////////////////
//		gv_Patterns.setAdapter(CONS.ShowListActv.adp_List_WPs);
////		gv.setAdapter(adapter);
//		
//		////////////////////////////////
//
//		// Listener
//
//		////////////////////////////////
//		gv_Patterns.setTag(Tags.DialogItemTags.GV_FILTER_SHOWLIST);
//		
//		// OnClick
//		gv_Patterns.setOnItemClickListener(new DOI_CL(actv, d));
//		
//		////////////////////////////////
//
//		// return
//
//		////////////////////////////////
//		return true;
//		
//	}//_filter_ShowList__GridView
	

	private static Dialog 
	_filter_ShowList__GetDialog
	(Activity actv) {
		// TODO Auto-generated method stub

		////////////////////////////////

		// setup dialog

		////////////////////////////////
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(R.layout.dlg_filter_showlog);
		
		// Title
		dlg.setTitle(actv.getString(R.string.menu_showlist_filter));
		
		////////////////////////////////

		// Buttons

		////////////////////////////////
		ImageButton bt_OK	= (ImageButton) dlg.findViewById(R.id.dlg_filter_showlist_bt_ok);
//		Button bt_OK	= (Button) dlg.findViewById(R.id.dlg_filter_showlist_bt_ok);
		ImageButton bt_Cancel =
				(ImageButton) dlg.findViewById(R.id.dlg_filter_showlist_bt_cancel);
//		Button bt_Cancel =
//				(Button) dlg.findViewById(R.id.dlg_filter_showlist_bt_cancel);
		ImageButton bt_Clear	=
				(ImageButton) dlg.findViewById(R.id.dlg_filter_showlist_bt_clear);
		ImageButton bt_Reset =
				(ImageButton) dlg.findViewById(R.id.dlg_filter_showlist_bt_reset);
		
		
		////////////////////////////////

		// Listeners

		////////////////////////////////
//		bt_OK.setTag(Tags.DialogTags.dlg_Filter_Timeline_OK);
		bt_OK.setTag(Tags.DialogTags.DLG_FILTER_SHOWLIST_OK);
		bt_Clear.setTag(Tags.DialogTags.DLG_FILTER_SHOWLIST_CLEAR);
		bt_Reset.setTag(Tags.DialogTags.DLG_FILTER_SHOWLIST_RESET);
		
		bt_Cancel.setTag(Tags.DialogTags.GENERIC_DISMISS);
		
		// On touch
		bt_OK.setOnTouchListener(new DB_OTL(actv));
		bt_Clear.setOnTouchListener(new DB_OTL(actv));
		bt_Reset.setOnTouchListener(new DB_OTL(actv));
		
		bt_Cancel.setOnTouchListener(new DB_OTL(actv));
		
		// On click
		bt_OK.setOnClickListener(new DB_OCL(actv, dlg));
		bt_Clear.setOnClickListener(new DB_OCL(actv, dlg));
		bt_Reset.setOnClickListener(new DB_OCL(actv, dlg));
		
		bt_Cancel.setOnClickListener(new DB_OCL(actv, dlg));
		
		return dlg;
		
	}//_filter_ShowList__GetDialog

}//public class Methods_dialog
