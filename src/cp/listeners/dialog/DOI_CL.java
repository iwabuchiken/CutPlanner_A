package cp.listeners.dialog;


import cp.items.ListItem;
import cp.main.R;
import cp.utils.CONS;
import cp.utils.Methods;
import cp.utils.Methods_dlg;
import cp.utils.Tags;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class DOI_CL implements OnItemClickListener {

	//
	Activity actv;
	Dialog d1;
	Dialog d2;
	
	//
	Vibrator vib;
	private String file_Name;
	
	
	
	//
//	Methods.DialogTags dlgTag = null;

	public DOI_CL(Activity actv, Dialog dlg) {
		// 
		this.actv = actv;
		this.d1 = dlg;
		
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
	}//public DialogOnItemClickListener(Activity actv, Dialog dlg)

	public DOI_CL(Activity actv, Dialog dlg, Dialog dlg2) {
		// 
		this.actv = actv;
		this.d1 = dlg;
		this.d2 = dlg2;
		
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
	}//public DialogOnItemClickListener(Activity actv, Dialog dlg)


	public DOI_CL
	(Activity actv, Dialog dlg1, String file_Name) {
		// TODO Auto-generated constructor stub
		
		this.actv = actv;
		this.d1 = dlg1;
		
		this.file_Name	= file_Name;
		
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);

	}

	//	@Override
	public void 
	onItemClick
	(AdapterView<?> parent, View v, int position, long id) {
		/*----------------------------
		 * Steps
		 * 1. Get tag
		 * 2. Vibrate
		 * 3. Switching
			----------------------------*/
		
		Tags.DialogItemTags tag = (Tags.DialogItemTags) parent.getTag();
//		
		vib.vibrate(CONS.Admin.vibLength_click);
		
//		String item = (String) parent.getItemAtPosition(position);
		
		ListItem li;
		String item;
		
		/*----------------------------
		 * 3. Switching
			----------------------------*/
		switch (tag) {
		
		case ACTV_MAIN_ADMIN_LV://----------------------------------------------
			
			li = (ListItem) parent.getItemAtPosition(position);
			
			case_ACTV_MAIN_ADMIN_LV(li);
			
			break;// case dlg_add_memos_gv
			
		default:
			break;
		}//switch (tag)
		
	}//public void onItemClick(AdapterView<?> parent, View v, int position, long id)

	private void 
	case_ACTV_MAIN_ADMIN_LV
	(ListItem li) {
		// TODO Auto-generated method stub
		
		if (li.getText().equals(actv.getString(
				R.string.dlg_actvmain_admin_item_see_log))) {

			Methods.start_Activity_LogActv(actv, d1);
			
		} else if (li.getText().equals(actv.getString(
				R.string.dlg_actvmain_admin_item_Topol))) {
			
			Methods.start_Activity_TopolActv(actv, d1);
			
		} else {
			
			String msg = "Unknown choice => " + li.getText();
			Methods_dlg.dlg_ShowMessage_SecondDialog(actv, msg, d1);
			
		}

		
	}//case_ACTV_MAIN_ADMIN_LV

}//public class DialogOnItemClickListener implements OnItemClickListener
