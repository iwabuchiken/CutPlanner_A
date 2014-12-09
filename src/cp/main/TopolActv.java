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
import cp.views.CV2;

//import app.main.R;


public class TopolActv extends Activity {
	
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
        setContentView(R.layout.actv_topol);
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
		
    	super.onDestroy();
    	
	}//protected void onDestroy()

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		
		
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onBackPressed() {
		
		this.finish();
		
		overridePendingTransition(0, 0);
		
	}//public void onBackPressed()

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// 
//		MenuInflater mi = getMenuInflater();
//		mi.inflate(R.menu.menu_actv_main, menu);
		
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

//		case R.id.menu_main_admin://----------------------------------
//			
//			Methods_dlg.dlg_ACTV_MAIN_Admin(this);
//			
//			break;// case R.id.main_opt_menu_create_folder
			
		}//switch (item.getItemId())
		
		return super.onOptionsItemSelected(item);
		
	}//public boolean onOptionsItemSelected(MenuItem item)

	@Override
	protected void onPause() {
		
		super.onPause();

	}

	@Override
	protected void onResume() {
		/*********************************
		 * 1. super
		 * 2. Set enables
		 *********************************/
		super.onResume();

	}//protected void onResume()

	@Override
	protected void onStart() {
		
		boolean res;
		String msg_Log;
		
		super.onStart();
		
		this._Setup();
		
		
		////////////////////////////////

		// listener

		////////////////////////////////
		
		_Setup__SetListener();

	}//protected void onStart()

	@Override
	protected void 
	onActivityResult
	(int requestCode, int ResultCode, Intent date){
	    //ダイアログ画面から結果を受けた後の処理を記述
		
		
	}//onActivityResult
	
	private void 
	_Setup__SetListener() {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// listener

		////////////////////////////////
		final View cv = (View) findViewById(R.id.actv_topol_cv_canvas);
		
		cv.setTag(Tags.ViewTags.CANVAS_TOPOL);
		
		// onTouch
		cv.setOnTouchListener(new V_OTL(this, (CV2) cv));

		// onClick
		cv.setOnClickListener(new V_OCL(this, (CV2) cv));
		
		////////////////////////////////

		// button: prev

		////////////////////////////////
		ImageButton bt_Prev = (ImageButton) findViewById(R.id.actv_topol_IB_Prev);
		
		bt_Prev.setTag(Tags.ButtonTags.ACTV_TOPOL_IB_PREV);
		
		bt_Prev.setOnTouchListener(new BO_TL(this));
		bt_Prev.setOnClickListener(new BO_CL(this));
		
		////////////////////////////////
		
		// button: next
		
		////////////////////////////////
		ImageButton bt_Next = (ImageButton) findViewById(R.id.actv_topol_IB_Next);
		
		bt_Next.setTag(Tags.ButtonTags.ACTV_TOPOL_IB_NEXT);
		
		bt_Next.setOnTouchListener(new BO_TL(this));
		bt_Next.setOnClickListener(new BO_CL(this));
		
		////////////////////////////////
		
		// button: next
		
		////////////////////////////////
		ImageButton bt_Back = (ImageButton) findViewById(R.id.actv_topol_IB_Back);
		
		bt_Back.setTag(Tags.ButtonTags.ACTV_TOPOL_IB_BACK);
		
		bt_Back.setOnTouchListener(new BO_TL(this));
		bt_Back.setOnClickListener(new BO_CL(this));
		
	}//_Setup__SetListener

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
		
	}//_Setup
	
	

}//public class MainActv extends Activity
