package cp.listeners.view;

import java.util.Locale;

import cp.utils.CONS;
import cp.utils.Tags;
import cp.views.CV;
import cp.views.CV2;
import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class V_OCL implements OnClickListener {

	Activity actv;
	
	CV cv;

	private CV2 cv2;
	
	public V_OCL(Activity actv, CV cv) {
		// TODO Auto-generated constructor stub
		
		this.actv	= actv;
		this.cv		= cv;
		
		CONS.Admin.vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
	}

	public V_OCL(Activity actv, CV2 cv2) {
		// TODO Auto-generated constructor stub
		
		this.actv	= actv;
		this.cv2	= cv2;
		
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

		// get: tag

		////////////////////////////////
		Tags.ViewTags tag_name = (Tags.ViewTags) v.getTag();
		
		////////////////////////////////

		// TopolActv

		////////////////////////////////
		if (tag_name == Tags.ViewTags.CANVAS_TOPOL) {
			
			// Log
			msg_Log = "onClick => Topol";
			Log.d("V_OTL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return;
			
		}
		
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
			
		case Rect_C:
			
			_case_Rect_C(v);
			
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
	
	private void 
	_case_Rect_C(View v) {
		// TODO Auto-generated method stub
		
		float tmp = CONS.Canvas.Rect_C_H;
		
		CONS.Canvas.Rect_C_H = CONS.Canvas.Rect_C_W;
		
		CONS.Canvas.Rect_C_W = tmp;
		
		((CV)v).invalidate();
		
		// Log
		String msg_Log = "CV => invalidated";
		Log.d("V_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		
	}
	
}
