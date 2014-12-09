package cp.listeners.button;

import cp.main.R;
import cp.utils.CONS;
import cp.utils.Methods;
import cp.utils.Tags;
import android.app.Activity;
import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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

	private BroadcastReceiver devieFoundReceiver;
	
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

	public 
	BO_CL
	(Activity actv, BroadcastReceiver devieFoundReceiver) {
		// TODO Auto-generated constructor stub
		
		this.actv = actv;
		
		this.devieFoundReceiver	= devieFoundReceiver;
		
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

		case ACTV_DEVLIST_BT_OPTIONS://-----------------------------------------------------------------------------
			
			case_ACTV_DEVLIST_BT_OPTIONS();
			
			break;
			
		case ACTV_MAIN_BT_GO://-----------------------------------------------------------------------------
			
			case_ACTV_MAIN_BT_GO();
			
			break;
			
		case ACTV_TOPOL_IB_NEXT://-----------------------------------------------------------------------------
			
			case_ACTV_TOPOL_IB_NEXT();
			
			break;
			
		case ACTV_TOPOL_IB_PREV://-----------------------------------------------------------------------------
			
			case_ACTV_TOPOL_IB_PREV();
			
			break;
			
		default:
			break;
		}//switch (tag)
		
	}//public void onClick(View v)

	private void 
	case_ACTV_TOPOL_IB_NEXT() {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// update: params

		////////////////////////////////
//		CONS.Canvas2.Rect_B_X1 = CONS.Canvas2.Rect_B_X1;
		
		// Y1
		CONS.Canvas2.Rect_B_Y1 = CONS.Canvas2.Rect_A_Y1 - CONS.Canvas2.Rect_B_W;
		
		// W
		float temp = CONS.Canvas2.Rect_B_W;
		
		CONS.Canvas2.Rect_B_W = CONS.Canvas2.Rect_B_H;
		
		// H
		CONS.Canvas2.Rect_B_H = temp;
		
		////////////////////////////////

		// view

		////////////////////////////////
		final View cv = (View) actv.findViewById(R.id.actv_topol_cv_canvas);
		
		cv.invalidate();
		
		// Log
		String msg_Log = "view => NEXT done";
		Log.d("BO_CL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
	}//ACTV_TOPOL_IB_NEXT

	void
	case_ACTV_TOPOL_IB_PREV() {
		// TODO Auto-generated method stub
		
		////////////////////////////////
		
		// update: params
		
		////////////////////////////////
//		CONS.Canvas2.Rect_B_X1 = CONS.Canvas2.Rect_B_X1;
		
		// Y1
		CONS.Canvas2.Rect_B_Y1 = CONS.Canvas2.Rect_A_Y1 - CONS.Canvas2.Rect_B_H_orig;
		
		// W
		float temp = CONS.Canvas2.Rect_B_W;
		
		CONS.Canvas2.Rect_B_W = CONS.Canvas2.Rect_B_H;
		
		// H
		CONS.Canvas2.Rect_B_H = temp;
		
		////////////////////////////////
		
		// view
		
		////////////////////////////////
		final View cv = (View) actv.findViewById(R.id.actv_topol_cv_canvas);
		
		cv.invalidate();
		
		// Log
		String msg_Log = "view => NEXT done";
		Log.d("BO_CL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
	}//ACTV_TOPOL_IB_NEXT
	
	private void 
	case_ACTV_DEVLIST_BT_OPTIONS() {
		// TODO Auto-generated method stub
		
		Methods.discover_Devices(actv, devieFoundReceiver);
		
//        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
//        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
//        
////        startActivity(discoverableIntent);
//        actv.startActivityForResult(discoverableIntent, CONS.Intent.REQUEST_CODE_DISCOEVERABLE);
//        
//        // Log
//		String msg_Log = "ACTION_REQUEST_DISCOVERABLE => started";
//		Log.d("DeviceListActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
//
//    	TextView nonPairedListTitle = (TextView) actv.findViewById(R.id.nonPairedListTitle);
//    	nonPairedListTitle.setText("list of devices with no history");
//
////    	if(item.getItemId() == Menu.FIRST){
//    		//�C���e���g�t�B���^�[��BroadcastReceiver�̓o�^
//	        IntentFilter filter = new IntentFilter();
//	        filter.addAction(CONS.BT.ACTION_DISCOVERY_STARTED);
//	        filter.addAction(CONS.BT.ACTION_FOUND);
//	        filter.addAction(CONS.BT.ACTION_NAME_CHANGED);
//	        filter.addAction(CONS.BT.ACTION_DISCOVERY_FINISHED);
//	        actv.registerReceiver(this.devieFoundReceiver, filter);
//	        
//    		CONS.BT.nonPairedDeviceAdapter = new ArrayAdapter<String>(actv, R.layout.rowdata);
//	        //�ڑ��\�ȃf�o�C�X�����o
//	        if(CONS.BT.mBtAdapter.isDiscovering()){
//	        	//�������̏ꍇ�͌��o���L�����Z������
//	        	CONS.BT.mBtAdapter.cancelDiscovery();
//	        }
//	        //�f�o�C�X����������
//	        //��莞�Ԃ̊Ԍ��o���s��
//	        CONS.BT.mBtAdapter.startDiscovery();
	        
//    	}

	}//case_ACTV_DEVLIST_BT_OPTIONS

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
