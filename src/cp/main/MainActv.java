package cp.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;



import cp.listeners.button.BO_CL;
import cp.listeners.button.BO_TL;
import cp.listeners.view.V_OCL;
import cp.listeners.view.V_OTL;
import cp.utils.CONS;
import cp.utils.Methods;
import cp.utils.Methods_dlg;
import cp.utils.Tags;
import cp.views.CV;

//import app.main.R;


public class MainActv extends Activity {
	
	public static Vibrator vib;

	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	/*----------------------------
		 * 1. super
		 * 2. Set content
		 * 2-2. Set title
		 * 3. Initialize => vib
		 * 
		 *  4. Set list
		 *  5. Set listener => Image buttons
		 *  6. Set path label
		 *  
		 *  7. Initialize preferences
		 *  
		 *  8. Refresh DB
			----------------------------*/
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actv_main_cv);
//        setContentView(R.layout.actv_main);
        
        /*----------------------------
		 * 2-2. Set title
			----------------------------*/
		this.setTitle(this.getClass().getName());
        
        vib = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

        
    }//public void onCreate(Bundle savedInstanceState)

    @Override
	protected void onDestroy() {
		/*----------------------------
		 * 1. Reconfirm if the user means to quit
		 * 2. super
		 * 3. Clear => prefs_main
		 * 4. Clear => list_root_dir
			----------------------------*/
		// Log
		Log.d("MainActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "onDestroy()");
		
		super.onDestroy();
		
		////////////////////////////////

		// bt

		////////////////////////////////
		if (CONS.BT.mBtAdapter != null && CONS.BT.mBtAdapter.isEnabled()) {
			
			CONS.BT.mBtAdapter.disable();
			
			// Log
			String msg_Log = "CONS.BT.mBtAdapter => disabled";
			Log.d("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}
		
	}//protected void onDestroy()

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		// Log
		String msg_Log = "onKeyDown()";
		Log.d("MainActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		
		Methods.confirm_quit(this, keyCode);
		
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// 
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.menu_actv_main, menu);
		
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.menu_main_admin://----------------------------------
			
			Methods_dlg.dlg_ACTV_MAIN_Admin(this);
			
			break;// case R.id.main_opt_menu_create_folder
			
		}//switch (item.getItemId())
		
		return super.onOptionsItemSelected(item);
		
	}//public boolean onOptionsItemSelected(MenuItem item)

	@Override
	protected void onPause() {
		
		super.onPause();

		Log.d("MainActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "onPause()");

//		// Log
//		Log.d("MainActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "prefs_main: " + Methods.get_currentPath_from_prefs(this));
		
		
	}

	@Override
	protected void onResume() {
		/*********************************
		 * 1. super
		 * 2. Set enables
		 *********************************/
		super.onResume();

		Log.d("MainActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "onResume()");

		
		/*********************************
		 * 2. Set enables
		 *********************************/
//		ImageButton ib_up = (ImageButton) findViewById(R.id.v1_bt_up);
//		
//		String curDirPath = Methods.get_currentPath_from_prefs(this);
//		
//		if (curDirPath.equals(dname_base)) {
//			
//			ib_up.setEnabled(false);
//			
//			ib_up.setImageResource(R.drawable.ifm8_up_disenabled);
//			
//		} else {//if (this.currentDirPath == this.dpath_base)
//		
//			ib_up.setEnabled(true);
//
//			
//			ib_up.setImageResource(R.drawable.ifm8_up);
//		
//		}//if (this.currentDirPath == this.dpath_base)
		
	}//protected void onResume()

	@Override
	protected void onStart() {
		
		boolean res;
		String msg_Log;
		
		super.onStart();
		
//		_test_DrawLine();
		
		this._Setup();
		
		////////////////////////////////

		// setup: layer

		////////////////////////////////
		_Setup__Layer();
		
		////////////////////////////////

		// listener

		////////////////////////////////
		
		_Setup__SetListener();

		////////////////////////////////

		// LAB-1: bluetooth

		////////////////////////////////
		res = _Bluetooth();
		
		if (res == true) {
			
			// Log
			msg_Log = "setup: bluetooth => done";
			Log.d("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} else {

			// Log
			msg_Log = "setup: bluetooth => not done";
			Log.d("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return;

		}
		
//		String log_msg = "MainActv => starts";
//		Methods.write_Log(this, log_msg,
//				Thread.currentThread().getStackTrace()[2].getFileName(), Thread
//						.currentThread().getStackTrace()[2].getLineNumber());
		
//		//debug
//		// Log
//		msg_Log = "this.getFilesDir().getPath() => "
//						+ this.getFilesDir().getPath();
//		
//		Log.d("MainActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
//		
//		File f = new File(this.getFilesDir().getPath());
//		
//		// Log
//		msg_Log = "f.isDirectory() => " + f.isDirectory();
//		Log.d("MainActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
	}//protected void onStart()

	@Override
	protected void 
	onActivityResult
	(int requestCode, int ResultCode, Intent date){
	    //ダイアログ画面から結果を受けた後の処理を記述
		
		String msg = "requestCode => " + requestCode
					+ "/"
					+ "ResultCode => " + ResultCode;
		
//		Methods_dlg.dlg_ShowMessage(this, msg);
		
		// Log
		Log.d("MainActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg);
		
		if(requestCode == CONS.BT.REQUEST_ENABLE_BLUETOOTH){
            if(ResultCode == Activity.RESULT_OK){
                //BluetoothがONにされた場合の処理
            	
            	msg = "Bluetooth => turned on";
//				Methods_dlg.dlg_ShowMessage(this, msg);
            	
            	// Log
				Log.d("MainActv.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg);
            	
            	Intent intent = new Intent(getApplicationContext(), DeviceListActv.class);
        		startActivity(intent);
				
            }else{

            	msg = "Bluetooth => can't be turned on";
				Methods_dlg.dlg_ShowMessage(this, msg);
				
            }
            
        }
		
	}//onActivityResult
	
	private boolean 
	_Bluetooth() {
		// TODO Auto-generated method stub
		
		Methods.setup_Bluetooth(this);
		
		////////////////////////////////

		// adapter

		////////////////////////////////
        BluetoothAdapter Bt = BluetoothAdapter.getDefaultAdapter();
        
        if(Bt.equals(null)){
        	
        	return false;
        	
        }

        CONS.BT.mBtAdapter = Bt;

        return true;
        
	}

	private void 
	_Setup__Layer() {
		
		if (CONS.Canvas.list_Layer == null) {
			
			CONS.Canvas.list_Layer = new ArrayList<CONS.Canvas.Layer>();
			
//			CONS.Canvas.list_Layer.add(CONS.Canvas.Layer.Cir_A);
			CONS.Canvas.list_Layer.add(CONS.Canvas.Layer.Rect_A);
			CONS.Canvas.list_Layer.add(CONS.Canvas.Layer.Rect_B);
			CONS.Canvas.list_Layer.add(CONS.Canvas.Layer.Rect_C);
			
		}
		
//		CONS.Canvas.list_Layer.add(CONS.Canvas.Layer.Cir_A);
//		CONS.Canvas.list_Layer.add(CONS.Canvas.Layer.Rect_A);
		
		// Log
		String msg_Log = "layer setup => done";
		Log.d("MainActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
	}

	private void 
	_Setup__SetListener() {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// listener

		////////////////////////////////
		final View cv = (View) findViewById(R.id.actv_main_cv_canvas);
		
		cv.setTag(Tags.ViewTags.CANVAS_MAIN);
		
		// onTouch
		cv.setOnTouchListener(new V_OTL(this, (CV) cv));

		// onClick
		cv.setOnClickListener(new V_OCL(this, (CV) cv));
		
		////////////////////////////////

		// button: go

		////////////////////////////////
		Button bt_Go = (Button) findViewById(R.id.actv_main_cv_bt_go);
		
		bt_Go.setTag(Tags.ButtonTags.ACTV_MAIN_BT_GO);
		
		bt_Go.setOnTouchListener(new BO_TL(this));
		bt_Go.setOnClickListener(new BO_CL(this));
		
		////////////////////////////////
		
		// button: clear
		
		////////////////////////////////
		Button bt_Clear = (Button) findViewById(R.id.actv_main_cv_bt_clear);
		
		bt_Clear.setTag(Tags.ButtonTags.ACTV_MAIN_BT_CLEAR);
		
		bt_Clear.setOnTouchListener(new BO_TL(this));
		bt_Clear.setOnClickListener(new BO_CL(this));
		
	}//_Setup__SetListener

	private void 
	_test_DrawLine() {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// paint

		////////////////////////////////
		Paint paint = new Paint();
		paint.setColor(Color.BLUE);
//		paint.setColor(0xFF4444FF);
//		paint.setStyle(Paint.Style.FILL);
		paint.setStrokeWidth(30);
		
		////////////////////////////////

		// get: view

		////////////////////////////////
		cp.views.CV cv = (cp.views.CV) findViewById(R.id.actv_main_cv_canvas);
//		
//		cv.drawLine(10, 10, 100, 100, paint);
//		
		// box: A
		CONS.Canvas.DrawA = true;
		
		cv.draw_Boxes_A(this);
		
//		CONS.Canvas.DrawA = false;
		
		// box: B
		CONS.Canvas.DrawB = true;
		cv.draw_Boxes_B(this);
		
	}

	private void 
	_Setup() {
		// TODO Auto-generated method stub
		
		////////////////////////////////
		
		// paint
		
		////////////////////////////////
		Paint paint = new Paint();
		paint.setColor(Color.BLUE);
//		paint.setColor(0xFF4444FF);
//		paint.setStyle(Paint.Style.FILL);
		paint.setStrokeWidth(30);
		
		////////////////////////////////
		
		// get: view
		
		////////////////////////////////
		cp.views.CV cv = (cp.views.CV) findViewById(R.id.actv_main_cv_canvas);
//		
//		////////////////////////////////
//
//		// circle: A
//
//		////////////////////////////////
//		CONS.Canvas.Draw_Circle_A = true;
//		
//		CONS.Canvas.Cir_A_X = 200;
//		CONS.Canvas.Cir_A_Y = 400;
//		
//		CONS.Canvas.Cir_A_X_prev = 200;
//		CONS.Canvas.Cir_A_Y_prev = 400;
//		
//		CONS.Canvas.Cir_A_Radius	= CONS.Canvas.Cir_A_Radius_dflt;
//		
//		cv.draw_Circle_A(
//					this, 
//					(int)CONS.Canvas.Cir_A_X, 
//					(int)CONS.Canvas.Cir_A_Y);

		////////////////////////////////

		// rect: A

		////////////////////////////////
//		CONS.Canvas.Draw_Rect_A = true;
		
		CONS.Canvas.Rect_A_X1 = 200;
		CONS.Canvas.Rect_A_Y1 = 400;
		
		CONS.Canvas.Rect_A_W = 200;
		CONS.Canvas.Rect_A_H = 150;
		
		cv.draw_Rect_A(this);
		
		////////////////////////////////
		
		// rect: B
		
		////////////////////////////////
//		CONS.Canvas.Draw_Rect_A = true;
		
		CONS.Canvas.Rect_B_W = 200;
		CONS.Canvas.Rect_B_H = 150;
		
		CONS.Canvas.Rect_B_X1 = 200;
		CONS.Canvas.Rect_B_Y1 = CONS.Canvas.Rect_A_Y1 - CONS.Canvas.Rect_B_H;
//		CONS.Canvas.Rect_B_Y1 = 200;
		
		cv.draw_Rect_B(this);
		
		////////////////////////////////
		
		// rect: C
		
		////////////////////////////////
//		CONS.Canvas.Draw_Rect_A = true;
		
		CONS.Canvas.Rect_C_W = 200;
		CONS.Canvas.Rect_C_H = 150;
		
		CONS.Canvas.Rect_C_X1 = 200;
		CONS.Canvas.Rect_C_Y1 = CONS.Canvas.Rect_B_Y1 - CONS.Canvas.Rect_B_H;
//		CONS.Canvas.Rect_B_Y1 = 200;
		
		cv.draw_Rect_C(this);
		
	}//_Setup
	
	

}//public class MainActv extends Activity
