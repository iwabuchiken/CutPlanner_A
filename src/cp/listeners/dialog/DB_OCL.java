package cp.listeners.dialog;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cp.main.R;
import cp.utils.CONS;
import cp.utils.Methods;
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
