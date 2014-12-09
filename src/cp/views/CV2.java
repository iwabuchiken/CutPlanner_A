package cp.views;

import java.util.ArrayList;

import cp.utils.CONS;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

// CanvasView
public class CV2 extends View {
	
	Context con;
	
	// �`�悷��_�i�[�p���X�g
	private ArrayList<Point> points;
	
	private Paint paint;	

	Paint paint_2;
	float x1, y1, x2, y2;

	public static Canvas can;
	
	////////////////////////////////
	
	// test
	
	////////////////////////////////
	
	// �R���X�g���N�^
	public CV2(Context context, AttributeSet attrs) {
		super(context, attrs);
		setFocusable(true);
		
		// Log
		String msg_Log = "CV => constructed";
		Log.d("CV.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

		////////////////////////////////

		// vars

		////////////////////////////////
		this.con		= context;
		this.paint_2	= new Paint();
//		
//		paint_2.setColor(Color.BLUE);
////      paint.setColor(Color.argb(75, 255, 255, 255));
//		paint_2.setStrokeWidth(5);
//      paint_2.setStrokeWidth(1);

	}

	// onMeasure���\�b�h(�r���[�̃T�C�Y�ݒ菈��)
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
		
		// Log
		String msg_Log = "Dimension => set";
		Log.d("CV.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
	}

	// onDraw���\�b�h(�`�揈��)
	@Override
	protected void 
	onDraw(Canvas canvas) {
		
		String msg_Log;
		
		if (CV2.can == null) {
			
			CV2.can = canvas;
			
		}
		
		////////////////////////////////

		// draw: Rect A

		////////////////////////////////
		if (CONS.Canvas2.f_Draw_Rect_A == true) {
			
			_onDraw__Rect_A(canvas);
			
		}
		
		////////////////////////////////
		
		// draw: Rect B
		
		////////////////////////////////
		if (CONS.Canvas2.f_Draw_Rect_B == true) {
			
			_onDraw__Rect_B(canvas);
			
		}
		
//		////////////////////////////////
//
//		// draw: boxes
//
//		////////////////////////////////
//		if (CONS.Canvas.DrawA == true) {
//			
//			//REF http://developer.android.com/reference/android/graphics/Canvas.html#drawLines(float[], android.graphics.Paint)
//			canvas.drawLines(CONS.Canvas.pointsA, CONS.Canvas.p_A);
////			canvas.drawLines(CONS.Canvas.pointsA, this.paint_2);
//			
//		}
//		
//		if (CONS.Canvas.DrawB == true) {
//			
//			canvas.drawLines(CONS.Canvas.pointsB, CONS.Canvas.p_B);
//			
//		}
		
		
//		////////////////////////////////
//		
//		// draw: Rect: B
//		
//		////////////////////////////////
//		if (CONS.Canvas.Draw_Rect_B == true) {
//			
//			canvas.drawRect(
//					CONS.Canvas.Rect_B_X1, 
//					CONS.Canvas.Rect_B_Y1, 
//					CONS.Canvas.Rect_B_X1 + CONS.Canvas.Rect_B_W, 
//					CONS.Canvas.Rect_B_Y1 + CONS.Canvas.Rect_B_H,
//					CONS.Canvas.p_Rect_B
//					);
//			
//		}
		
//		this.draw_Boxes_A((Activity) con);
//		this.draw_Boxes_B((Activity) con);
		
//		canvas.drawLine(this.x1, this.y1, this.x2, this.y2, this.paint);
////		canvas.drawLine(this.x1, this.x2, this.y1, this.y2, this.paint);
//		
//		canvas.drawLines(CONS.Canvas.pointsA, this.paint_2);
//		canvas.drawLines(CONS.Canvas.pointsB, this.paint_2);
		
//		canvas.drawText("あいうえお", 10, 400, paint);
		
	}

	private void 
	_onDraw__Rect_A(Canvas canvas) {
		// TODO Auto-generated method stub
		////////////////////////////////

		// paint

		////////////////////////////////
		CONS.Canvas2.p_Rect_A = new Paint();
		
		CONS.Canvas2.p_Rect_A.setColor(CONS.Canvas2.col_Rect_A);
		CONS.Canvas2.p_Rect_A.setStrokeWidth(CONS.Canvas2.LineWidth_Rect_A);

		////////////////////////////////

		// draw

		////////////////////////////////
		canvas.drawRect(
				CONS.Canvas2.Rect_A_X1, 
				CONS.Canvas2.Rect_A_Y1, 
				CONS.Canvas2.Rect_A_X1 + CONS.Canvas2.Rect_A_W, 
				CONS.Canvas2.Rect_A_Y1 + CONS.Canvas2.Rect_A_H,
				CONS.Canvas2.p_Rect_A
				);
		
		// Log
		String msg_Log = "Rect A => drawn";
		Log.d("CV2.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
	}//_onDraw__Rect_A

	private void 
	_onDraw__Rect_B(Canvas canvas) {
		// TODO Auto-generated method stub
		////////////////////////////////
		
		// paint
		
		////////////////////////////////
		CONS.Canvas2.p_Rect_B = new Paint();
		
		CONS.Canvas2.p_Rect_B.setColor(CONS.Canvas2.col_Rect_B);
		CONS.Canvas2.p_Rect_B.setStrokeWidth(CONS.Canvas2.LineWidth_Rect_B);
		
		////////////////////////////////
		
		// draw
		
		////////////////////////////////
		canvas.drawRect(
				CONS.Canvas2.Rect_B_X1, 
				CONS.Canvas2.Rect_B_Y1, 
				CONS.Canvas2.Rect_B_X1 + CONS.Canvas2.Rect_B_W, 
				CONS.Canvas2.Rect_B_Y1 + CONS.Canvas2.Rect_B_H,
				CONS.Canvas2.p_Rect_B
				);
		
		// Log
		String msg_Log = "Rect B => drawn";
		Log.d("CV2.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
	}//_onDraw__Rect_B
	
	public void _go() {
		
		// Log
		String msg_Log = "_onDraw_DrawLine => started";
		Log.d("CV.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
        // Log
		msg_Log = "_onDraw_DrawLine => done";
		Log.d("CV.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		this.invalidate();
		
	}
	
	public void _clear() {
		
		// Log
		String msg_Log = "_clear => started";
		Log.d("CV.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		// Log
		msg_Log = "_clear => done";
		Log.d("CV.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		this.invalidate();
		
	}
	
	// clearDrawList���\�b�h(�N���A����)
	public void clearDrawList() {
		points.clear();
		this.invalidate();
	}

	public void 
	draw_A
	(Activity actv) {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// flag

		////////////////////////////////
		CONS.Canvas2.f_Draw_Rect_A = true;

		////////////////////////////////

		// invalidate

		////////////////////////////////
		this.invalidate();
		
	}//draw_A

	public void 
	draw_B
	(Activity actv) {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// flag

		////////////////////////////////
		CONS.Canvas2.f_Draw_Rect_B = true;

		////////////////////////////////

		// invalidate

		////////////////////////////////
		this.invalidate();
		
		
	}//draw_B
	

	////////////////////////////////

	// DrawableView.java

	
}
