package cp.listeners.view;

import java.util.Locale;

import cp.utils.CONS;
import cp.utils.Methods;
import cp.utils.Tags;
import cp.views.CV;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;

public class V_OTL implements OnTouchListener {

	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;
	Dialog d1;
	private Dialog d2;
	private Dialog d3;
	private Dialog d4;
	private CV cv;
	
	public V_OTL(Activity actv) {
		//
		this.actv = actv;
	}

	public V_OTL(Activity actv, CV cv) {
		// TODO Auto-generated constructor stub
		
		this.actv	= actv;
		this.cv		= cv;
		
	}

	//	@Override
	public boolean onTouchEvent(MotionEvent event) {
//		public boolean onTouch(View v, MotionEvent event) {
		
//		Tags.ViewTags tag_name = (Tags.ViewTags) v.getTag();
		
		ImageButton ib;
		
		float x;
		float y;
		
		switch (event.getActionMasked()) {
		
			case MotionEvent.ACTION_DOWN://--------------------
				
				x = event.getX();
				y = event.getY();
				
				// Log
				String msg_Log = String.format(
							Locale.JAPAN,
							"[DOWN] x = %f / y = %f", x, y);
				
				Log.d("V_OTL.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
//				int res = Methods.identify(actv, x, y);
				
//				switch (tag_name) {
//					
//				
//				
//	//				case DLG_FILTER_SHOWLIST_RESET:
//	//					
//	//					ib = (ImageButton) v;
//	//					ib.setImageResource(R.drawable.general_ib_cancel_red_64x64_disdabled);
//	//					
//	//					break;
//				
//				}//switch (tag_name)
	
				break;//case MotionEvent.ACTION_DOWN:
			
			case MotionEvent.ACTION_UP://--------------------
				
				x = event.getX();
				y = event.getY();
				
				// Log
				msg_Log = String.format(
							Locale.JAPAN,
							"[UP] x = %f / y = %f", x, y);
				
				Log.d("V_OTL.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
//				switch (tag_name) {
//	
//	//			case DLG_FILTER_SHOWLIST_RESET:
//	//				
//	//				ib = (ImageButton) v;
//	//				ib.setImageResource(R.drawable.general_ib_cancel_red_64x64);
//	//				
//	//				break;
//					
//				}//switch (tag_name)
			
				break;//case MotionEvent.ACTION_UP:
		
			case MotionEvent.ACTION_MOVE://--------------------
				
				x = event.getX();
				y = event.getY();
				
				// Log
				msg_Log = String.format(
							Locale.JAPAN,
							"[MOVE] x = %f / y = %f", x, y);
				
				Log.d("V_OTL.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
//				switch (tag_name) {
//				
//				//			case DLG_FILTER_SHOWLIST_RESET:
//				//				
//				//				ib = (ImageButton) v;
//				//				ib.setImageResource(R.drawable.general_ib_cancel_red_64x64);
//				//				
//				//				break;
//				
//				}//switch (tag_name)
//				
//				break;//case MotionEvent.ACTION_UP:
				
		}//switch (event.getActionMasked())
		
		return false;
		
	}//public boolean onTouch(View v, MotionEvent event)

	//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
	public boolean onTouch(View v, MotionEvent event) {
		
		Tags.ViewTags tag_name = (Tags.ViewTags) v.getTag();
		
		ImageButton ib;
		
		float x;
		float y;
		
		switch (event.getActionMasked()) {
		
		case MotionEvent.ACTION_DOWN://--------------------

			// Log
			String msg_Log = "view => " + v.getClass().getName();
			Log.d("V_OTL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			x = event.getX();
			y = event.getY();

			////////////////////////////////

			// identify

			////////////////////////////////
			int res = Methods.identify(actv, x, y);
			
			////////////////////////////////

			// draw: box

			////////////////////////////////
			this._case_ACTION_DOWN(x, y);
			
//			this.cv.draw_Circle_A(actv, (int)x, (int)y);
//			this.cv.draw_Boxes_A(actv, (int)x, (int)y);
			
				switch (tag_name) {
					
				
				
	//				case DLG_FILTER_SHOWLIST_RESET:
	//					
	//					ib = (ImageButton) v;
	//					ib.setImageResource(R.drawable.general_ib_cancel_red_64x64_disdabled);
	//					
	//					break;
				
				}//switch (tag_name)
			
			break;//case MotionEvent.ACTION_DOWN:
			
		case MotionEvent.ACTION_UP://--------------------
			
			x = event.getX();
			y = event.getY();
			
			// Log
			msg_Log = String.format(
					Locale.JAPAN,
					"[UP] x = %f / y = %f", x, y);
			
			Log.d("V_OTL.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + "]", msg_Log);
			
				switch (tag_name) {
	
	//			case DLG_FILTER_SHOWLIST_RESET:
	//				
	//				ib = (ImageButton) v;
	//				ib.setImageResource(R.drawable.general_ib_cancel_red_64x64);
	//				
	//				break;
					
				}//switch (tag_name)
			
			break;//case MotionEvent.ACTION_UP:
			
		case MotionEvent.ACTION_MOVE://--------------------
			
			x = event.getX();
			y = event.getY();
			
//			// Log
//			msg_Log = String.format(
//					Locale.JAPAN,
//					"[MOVE] x = %f / y = %f", x, y);
//			
//			Log.d("V_OTL.java"
//					+ "["
//					+ Thread.currentThread().getStackTrace()[2]
//							.getLineNumber() + "]", msg_Log);

			////////////////////////////////

			// draw: box

			////////////////////////////////
			this._case_ACTION_MOVE(x, y);
			
//			this.cv.draw_Boxes_A(actv, (int)x, (int)y);
//			this.cv.draw_Circle_A(actv, (int)x, (int)y);

				switch (tag_name) {
				
				//			case DLG_FILTER_SHOWLIST_RESET:
				//				
				//				ib = (ImageButton) v;
				//				ib.setImageResource(R.drawable.general_ib_cancel_red_64x64);
				//				
				//				break;
				
				}//switch (tag_name)
//				
			break;//case MotionEvent.ACTION_MOVE:
			
		}//switch (event.getActionMasked())
		
		return true;
//		return false;	//=> if false, MOVE_DOWN only; no further cases logged out
		
	}//public boolean onTouch(View v, MotionEvent event)

	private void 
	_case_ACTION_MOVE
	(float x, float y) {
		// TODO Auto-generated method stub
	
		////////////////////////////////

		// get distance

		////////////////////////////////
//		String msg_Log = String.format(
//				Locale.JAPAN,
//				"DIF_X = %f / DIF_Y = %f", 
//				(x - CONS.Canvas.Cir_A_DOWN_X),
//				(y - CONS.Canvas.Cir_A_DOWN_Y)
//				);
//		
//		Log.d("V_OTL.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		float dif_X = x - CONS.Canvas.Cir_A_X_prev;
		float dif_Y = y - CONS.Canvas.Cir_A_Y_prev;
		
		CONS.Canvas.Cir_A_X_prev = x;
		CONS.Canvas.Cir_A_Y_prev = y;
		
//		float dif_X = x - CONS.Canvas.Cir_A_DOWN_X;
//		float dif_Y = y - CONS.Canvas.Cir_A_DOWN_Y;
		
//		double change = Methods.get_CircleA_Change(
//							actv,
//							CONS.Canvas.Cir_A_X_prev,
//							CONS.Canvas.Cir_A_Y_prev,
//							x, y
//							);
		
		this.cv.draw_Circle_A(
					actv, 
					(int)(CONS.Canvas.Cir_A_X + dif_X), 
					(int)(CONS.Canvas.Cir_A_Y + dif_Y) 
					);
//		this.cv.draw_Circle_A(actv, (int)x, (int)y);
		
	}//_case_ACTION_MOVE
	

	private void 
	_case_ACTION_DOWN
	(float x, float y) {
		// TODO Auto-generated method stub

		////////////////////////////////

		// record: positon

		////////////////////////////////
		CONS.Canvas.Cir_A_X_prev = x;
		CONS.Canvas.Cir_A_Y_prev = y;
//		CONS.Canvas.Cir_A_DOWN_X = x;
//		CONS.Canvas.Cir_A_DOWN_Y = y;
//		
//		// Log
//		String msg_Log = String.format("DOWN_X = %f / DOWN_Y = %f", 
//					CONS.Canvas.Cir_A_DOWN_X,
//					CONS.Canvas.Cir_A_DOWN_Y
//					);
		
//		Log.d("V_OTL.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		////////////////////////////////

		// detection

		////////////////////////////////
		double distance = Math.sqrt(
							Math.pow((CONS.Canvas.Cir_A_X - x), 2)
							+ Math.pow((CONS.Canvas.Cir_A_Y - y), 2));
		
		// Log
//		msg_Log = "distance => " + distance;
		String msg_Log = "distance => " + distance;
		Log.d("V_OTL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////

		// draw

		////////////////////////////////
		if (distance > CONS.Canvas.Cir_A_Radius) {
			
			this.cv.draw_Circle_A(actv, (int)x, (int)y);
			
		}
		
		
		
	}//_case_ACTION_DOWN
	
}//public class DB_OTL implements OnTouchListener
