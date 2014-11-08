package cp.listeners.button;

import cp.main.R;
import cp.utils.CONS;
import cp.utils.Methods;
import cp.utils.Tags;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;

public class BO_CL implements OnClickListener {
	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;

	//
	Vibrator vib;
	
	//
	int position;
	
	public BO_CL(Activity actv, int position) {
		//
		this.actv = actv;
		this.position = position;
		
		//
		CONS.Admin.vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
		
		
	}//public ButtonOnClickListener(Activity actv, int position)

	public BO_CL(Activity actv) {
		
		this.actv = actv;
		
		//
		CONS.Admin.vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);

	}

	//	@Override
	public void onClick(View v) {
//		//
		Tags.ButtonTags tag = (Tags.ButtonTags) v.getTag();
//
		CONS.Admin.vib.vibrate(CONS.Admin.vibLength_click);
		
		//
		switch (tag) {

		case ACTV_MAIN_BT_GO://-----------------------------------------------------------------------------
			
			case_ACTV_MAIN_BT_GO();
			
			break;
			
		default:
			break;
		}//switch (tag)
		
	}//public void onClick(View v)

	private void 
	case_ACTV_MAIN_BT_GO() {
		// TODO Auto-generated method stub
		
		Methods.save_Canvas_2(actv);
//		Methods.save_Canvas(actv);
		
	}//case_ACTV_MAIN_BT_GO

	

//	private void 
//	case_ACTV_PLAY_SAVE() {
//		// TODO Auto-generated method stub
//		////////////////////////////////
//		
//		// validate: any text?
//		
//		////////////////////////////////
//		EditText et = (EditText) actv.findViewById(R.id.actv_play_et);
//		
//		String tmp = et.getText().toString();
//		
//		if (tmp == null) {
//			
//			String msg = "Text => null";
//			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.gold2);
//			
//			return;
//			
//		}
//		
//		if (tmp.length() < 1) {
//			
//			String msg = "No text";
//			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.gold2);
//			
//			return;
//			
//		}
//		
//		////////////////////////////////
//		
//		// save memo
//		
//		////////////////////////////////
//		int res = Methods.save_Memo(actv, R.id.actv_play_et);
//		
////			-1	insertion => failed<br>
////			-2	Exception<br>
////			1	text => inserted<br>
//		
//		////////////////////////////////
//		
//		// clear view?
//		
//		////////////////////////////////
//		boolean pref = Methods.get_Pref_Boolean(
//				actv, 
//				CONS.Pref.pname_MainActv, 
//				actv.getString(R.string.prefs_ClearView_WhenSaved_key), 
//				false);
//		
//		if (pref == true) {
//			
//			et.setText("");
//			
//		}
//		
//		
//		Methods.report_Save_Memos(actv, res);
//		
//	}//ACTV_PLAY_SAVE


}//public class ButtonOnClickListener implements OnClickListener
