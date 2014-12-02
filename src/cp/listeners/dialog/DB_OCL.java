package cp.listeners.dialog;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cp.items.LogItem;
import cp.main.R;
import cp.utils.CONS;
import cp.utils.Methods;
import cp.utils.Methods_dlg;
import cp.utils.Tags;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class DB_OCL implements OnClickListener {
	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;
	Dialog d1;
	Dialog d2;		//=> Used in dlg_input_empty_btn_XXX
	Dialog d3;
	private Dialog d4;

	//
	Vibrator vib;
	
	// Used in => Methods.dlg_addMemo(Activity actv, long file_id, String tableName)
	long db_Id;
	String tableName;
	private String item_Name;	// Methods_dlg.conf_DropTable
	public DB_OCL(Activity actv, Dialog dlg1) {
		//
		this.actv = actv;
		this.d1 = dlg1;
		
		CONS.Admin.vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
	}

	public DB_OCL(Activity actv, Dialog dlg1,
			Dialog dlg2) {
		//
		this.actv = actv;
		this.d1 = dlg1;
		this.d2 = dlg2;
		
		//
//		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
		CONS.Admin.vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
	}

	public DB_OCL(Activity actv, Dialog dlg1,
			Dialog dlg2, Dialog dlg3) {
		//
		this.actv = actv;
		this.d1 = dlg1;
		this.d2 = dlg2;
		this.d3 = dlg3;
		
		//
//		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
		CONS.Admin.vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
	}

	public DB_OCL(Activity actv, Dialog dlg1, long file_id, String tableName) {
		// 
		this.actv = actv;
		this.d1 = dlg1;
		
		this.tableName = tableName;
		
		this.db_Id = file_id;
		
//		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
		CONS.Admin.vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
	}//public DialogButtonOnClickListener(Activity actv, Dialog dlg1, long file_id, String tableName)


	public DB_OCL
	(Activity actv, Dialog dlg1, Dialog dlg2, String item_Name) {
		// TODO Auto-generated constructor stub
		
		this.actv = actv;
		this.d1 = dlg1;
		this.d2 = dlg2;

		this.item_Name	= item_Name;
		
//		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
		CONS.Admin.vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
	}

	public 
	DB_OCL
	(Activity actv, Dialog dlg1, long db_id) {
		// TODO Auto-generated constructor stub
		
		this.actv	= actv;
		this.d1	= dlg1;
		
		this.db_Id	= db_id;
		
		CONS.Admin.vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);

	}

	public DB_OCL
	(Activity actv, 
		Dialog d1, Dialog d2, Dialog d3, Dialog d4) {
		// TODO Auto-generated constructor stub
		
		this.actv	= actv;
		this.d1	= d1;
		this.d2	= d2;
		this.d3	= d3;
		this.d4	= d4;

		CONS.Admin.vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);

	}

	public void 
	onClick(View v) {
		//
		Tags.DialogTags tag_name = (Tags.DialogTags) v.getTag();

		CONS.Admin.vib.vibrate(CONS.Admin.vibLength_click);
		
		// Used for sound effect
		boolean val;
		
		// Log
		Log.d("DialogButtonOnClickListener.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "tag_name.name()=" + tag_name.name());
		
		//
		switch (tag_name) {

		case DLG_FILTER_SHOWLIST_CLEAR://------------------------------------------------
			
			case_DLG_FILTER_SHOWLIST_CLEAR();
			
			break;

		case DLG_FILTER_SHOWLIST_RESET://------------------------------------------------
			
			case_DLG_FILTER_SHOWLIST_RESET();
			
			break;

		case DLG_FILTER_SHOWLIST_OK://------------------------------------------------
			
			case_DLG_FILTER_SHOWLIST_OK();
//			case_DLG_FILTER_SHOWLIST_OK();
			
			break;

		case GENERIC_DISMISS://------------------------------------------------
			
			d1.dismiss();
			
			break;

		case GENERIC_DISMISS_SECOND_DIALOG: // ----------------------------------------------------
			
			d2.dismiss();
			
			break;// case dlg_generic_dismiss_second_dialog

		case GENERIC_DISMISS_THIRD_DIALOG://------------------------------------------------
			
			d3.dismiss();
			
			break;

		case GENERIC_DISMISS_4TH_DIALOG://------------------------------------------------
			
			d4.dismiss();
			
			break;
			
		case GENERIC_CLEAR_SECOND_DIALOG://------------------------------------------------
			
			case_GENERIC_CLEAR_SECOND_DIALOG();
			
			break;
			
		default: // ----------------------------------------------------
			break;
		}//switch (tag_name)
	}//public void onClick(View v)

	private void 
	case_DLG_FILTER_SHOWLIST_CLEAR() {
		// TODO Auto-generated method stub
		////////////////////////////////
	
		// view
	
		////////////////////////////////
		EditText et = (EditText) d1.findViewById(R.id.dlg_filter_showlist_et_content);
		
		////////////////////////////////

		// clear

		////////////////////////////////
		et.setText("");
		
	}

	private void 
	case_DLG_FILTER_SHOWLIST_RESET() {
		// TODO Auto-generated method stub
		////////////////////////////////

		// validate: raw lines list

		////////////////////////////////
		if (CONS.ShowLogActv.list_RawLines == null
				|| CONS.ShowLogActv.list_RawLines.size() < 1) {
			
			File fpath_Log = new File(
					CONS.DB.dPath_Log,
					CONS.ShowLogActv.fname_Target_LogFile);
			
			List<String> list = 
					Methods.get_LogLines(actv, fpath_Log.getAbsolutePath());
			
			////////////////////////////////
			
			// list => reverse
			
			////////////////////////////////
			Collections.reverse(list);
			
			////////////////////////////////

			// add all

			////////////////////////////////
			CONS.ShowLogActv.list_RawLines.addAll(list);
			
		}

		////////////////////////////////

		// build: LogItem list

		////////////////////////////////
		List<LogItem> list_LogItem = 
				Methods.conv_LogLinesList_to_LogItemList(
									actv, CONS.ShowLogActv.list_RawLines);
		
		////////////////////////////////

		// reset

		////////////////////////////////
		CONS.ShowLogActv.list_ShowLog_Files.clear();
		
		CONS.ShowLogActv.list_ShowLog_Files.addAll(list_LogItem);

		CONS.ShowLogActv.adp_ShowLog_File_List.notifyDataSetChanged();
		
		////////////////////////////////

		// dismiss

		////////////////////////////////
		d1.dismiss();
		
	}//case_DLG_FILTER_SHOWLIST_RESET

	@SuppressWarnings("unused")
	private void 
	case_DLG_FILTER_SHOWLIST_OK() {
		// TODO Auto-generated method stub
		
		String msg_Log;
		
		String msg;
		
		int res_i;
		
		////////////////////////////////
		
		// get view
		
		////////////////////////////////
		EditText et = (EditText) d1.findViewById(R.id.dlg_filter_showlist_et_content);
		
		////////////////////////////////

		// validate: any input

		////////////////////////////////
		String input = et.getText().toString();
		
		if (input == null || input.equals("")) {
			
			msg = "no input";
			Methods_dlg.dlg_ShowMessage_SecondDialog(actv, msg, d1, R.color.red);
			
			return;
			
		}
		
		////////////////////////////////
		
		// validate: multiple keywords
		
		////////////////////////////////
		input = input.trim();
		
		input = input.replaceAll("　", " ");
		
		input = input.replaceAll(" +", " ");
		
		// Log
		msg_Log = "input is now => " + input;
		Log.d("DB_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
//		String[] tokens = input.split(" +");
		String[] tokens = input.split(" ");
		
		if (tokens == null) {
			
			msg = "Split the input => null";
			Methods_dlg.dlg_ShowMessage_SecondDialog(actv, msg, d1, R.color.gold2);
			
			return;
			
		}
		
		// Log
		msg_Log = "tokens.length => " + tokens.length;
		Log.d("DB_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

		////////////////////////////////

		// filter: OR

		////////////////////////////////
		List<LogItem> list_LogItem = new ArrayList<LogItem>();
		
		String main_Line;
		
		boolean is_In;
		
		for (LogItem logItem : CONS.ShowLogActv.list_ShowLog_Files) {
			
			is_In = false;
			
			main_Line = logItem.getText();
			
			for (String token : tokens) {
				
				if (main_Line.contains(token)) {
					
					is_In = true;
					
					break;
					
				}
				
			}//for (String token : tokens)
			
			if (is_In == true) {
				
				list_LogItem.add(logItem);
				
			}
			
		}
		
		// Log
		msg_Log = "list_LogItem.size() => " + list_LogItem.size();
		Log.d("DB_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

		////////////////////////////////

		// update list

		////////////////////////////////
		CONS.ShowLogActv.list_ShowLog_Files.clear();
		
		CONS.ShowLogActv.list_ShowLog_Files.addAll(list_LogItem);
		
		CONS.ShowLogActv.adp_ShowLog_File_List.notifyDataSetChanged();
		
		////////////////////////////////

		// dismiss

		////////////////////////////////
		d1.dismiss();
	}//case_DLG_FILTER_SHOWLIST_OK
	
	

////=======
//		Methods_dlg.dlg_ShowMessage_SecondDialog(actv, msg, d1);
//		
//	}//case_DLG_EDIT_MEMOS_ACTV_IMAGE_BT_OK
	


	

//	private void 
//	case_DLG_CONF_CLEAR_VIEW_PLAY_ACTV_OK() {
//		// TODO Auto-generated method stub
//		////////////////////////////////
//		
//		// view
//		
//		////////////////////////////////
//		EditText et = (EditText) actv.findViewById(R.id.actv_play_et);
//		
//		////////////////////////////////
//		
//		// clear
//		
//		////////////////////////////////
//		et.setText("");
//		
//		////////////////////////////////
//		
//		// dismiss
//		
//		////////////////////////////////
//		d1.dismiss();
//		
//	}//case_DLG_CONF_CLEAR_VIEW_PLAY_ACTV_OK
	
	private void 
	case_GENERIC_CLEAR_SECOND_DIALOG() {
		// TODO Auto-generated method stub
		
		// Log
		String msg_Log = "clearing all dialogues...";
		Log.d("DB_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		d2.dismiss();
		d1.dismiss();
		
		
		
	}

}//DialogButtonOnClickListener
