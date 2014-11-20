package cp.listeners.view;

import java.util.Locale;

import cp.utils.CONS;
import cp.views.CV;
import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class V_OCL implements OnClickListener {

	Activity actv;
	
	CV cv;
	
	public V_OCL(Activity actv, CV cv) {
		// TODO Auto-generated constructor stub
		
		this.actv	= actv;
		this.cv		= cv;
		
		CONS.Admin.vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
	}

	@Override
	public void 
	onClick(View v) {
		// TODO Auto-generated method stub
		
		String msg_Log;
		
		// Log
		msg_Log = "clicked!";
		Log.d("V_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

		////////////////////////////////

		// identify

		////////////////////////////////
		// Log
		msg_Log = "clicked obj => " + CONS.Canvas.currentObj.toString();
		Log.d("V_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

		////////////////////////////////

		// judge

		////////////////////////////////
		if ((Math.abs(CONS.Canvas.diff_X) > 1) 
				&& (Math.abs(CONS.Canvas.diff_Y) > 1)
				) {
//			if ((CONS.Canvas.diff_X > 1 && CONS.Canvas.diff_Y > 1)
//					|| (CONS.Canvas.diff_X < -1 && CONS.Canvas.diff_Y < -1)
//					) {
			
			// Log
			msg_Log = "click => no action";
			Log.d("V_OCL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return;
			
		} else {
			
			msg_Log = String.format(Locale.JAPAN, 
					"diff_x = %f, diff_y = %f", 
					CONS.Canvas.diff_X, CONS.Canvas.diff_Y);
	
			Log.d("V_OTL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			// Log
			msg_Log = "click => action starts!";
			Log.d("V_OCL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);

			
		}
		
		////////////////////////////////

		// dispatch

		////////////////////////////////
		switch(CONS.Canvas.currentObj) {
		
		case Rect_A:
			
			_case_Rect_A(v);
			
			break;
		
		case Rect_B:
			
			_case_Rect_B(v);
			
			break;
			
		default:
			
			break;
			
		}
		
	}//onClick

	private void 
	_case_Rect_A(View v) {
		// TODO Auto-generated method stub
		
		float tmp = CONS.Canvas.Rect_A_H;
		
		CONS.Canvas.Rect_A_H = CONS.Canvas.Rect_A_W;
		
		CONS.Canvas.Rect_A_W = tmp;
		
		((CV)v).invalidate();
		
		// Log
		String msg_Log = "CV => invalidated";
		Log.d("V_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		
	}

	private void 
	_case_Rect_B(View v) {
		// TODO Auto-generated method stub
		
		float tmp = CONS.Canvas.Rect_B_H;
		
		CONS.Canvas.Rect_B_H = CONS.Canvas.Rect_B_W;
		
		CONS.Canvas.Rect_B_W = tmp;
		
		((CV)v).invalidate();
		
		// Log
		String msg_Log = "CV => invalidated";
		Log.d("V_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		
	}
	
}
