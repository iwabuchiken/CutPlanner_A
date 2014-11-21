package cp.listeners.dialog;

import cp.utils.Tags;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;

public class DB_OTL implements OnTouchListener {

	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;
	Dialog d1;
	private Dialog d2;
	private Dialog d3;
	private Dialog d4;
	
	public DB_OTL(Activity actv, Dialog dlg) {
		//
		this.actv = actv;
		this.d1 = dlg;
	}
	
	public DB_OTL(Activity actv) {
		//
		this.actv = actv;
	}

	public 
	DB_OTL
	(Activity actv, Dialog dlg1, Dialog dlg2) {
		// TODO Auto-generated constructor stub
		
		this.actv = actv;
		this.d1 = dlg1;
		this.d2 = dlg2;

	}

	public DB_OTL(Activity actv, Dialog dlg1, Dialog dlg2, Dialog dlg3) {
		// TODO Auto-generated constructor stub
		
		this.actv = actv;
		
		this.d1 = dlg1;
		this.d2 = dlg2;
		this.d3 = dlg3;
		
	}

	public DB_OTL(Activity actv, Dialog d1, Dialog d2, Dialog d3, Dialog d4) {
		// TODO Auto-generated constructor stub
		
		this.actv = actv;
		
		this.d1 = d1;
		this.d2 = d2;
		this.d3 = d3;
		this.d4 = d4;
		
	}

	//	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		Tags.DialogTags tag_name = (Tags.DialogTags) v.getTag();
		
		ImageButton ib;
		
		switch (event.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
				switch (tag_name) {
				
				case GENERIC_DISMISS:
				case GENERIC_DISMISS_THIRD_DIALOG:
				case GENERIC_DISMISS_SECOND_DIALOG:
				case GENERIC_DISMISS_4TH_DIALOG:
					
					
					v.setBackgroundColor(Color.GRAY);
					
					break;
					
				}//switch (tag_name)
		
			break;//case MotionEvent.ACTION_DOWN:
			
		case MotionEvent.ACTION_UP:
			switch (tag_name) {

			case GENERIC_DISMISS:
			case GENERIC_DISMISS_SECOND_DIALOG:
			case GENERIC_DISMISS_THIRD_DIALOG:
			case GENERIC_DISMISS_4TH_DIALOG:

					v.setBackgroundColor(Color.WHITE);
					
					break;
					
			}//switch (tag_name)
		
			break;//case MotionEvent.ACTION_UP:
		
		}//switch (event.getActionMasked())
		
		return false;
		
	}//public boolean onTouch(View v, MotionEvent event)

}//public class DB_OTL implements OnTouchListener
