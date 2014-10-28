package cp.listeners.view;

import java.util.Locale;

import cp.utils.Tags;
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
	
	public V_OTL(Activity actv) {
		//
		this.actv = actv;
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
			
			// Log
			msg_Log = String.format(
					Locale.JAPAN,
					"[MOVE] x = %f / y = %f", x, y);
			
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
//				
			break;//case MotionEvent.ACTION_MOVE:
			
		}//switch (event.getActionMasked())
		
		return true;
//		return false;	//=> if false, MOVE_DOWN only; no further cases logged out
		
	}//public boolean onTouch(View v, MotionEvent event)
	
}//public class DB_OTL implements OnTouchListener
