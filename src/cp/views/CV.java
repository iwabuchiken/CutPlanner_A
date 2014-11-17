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
public class CV extends View {
	
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
	public CV(Context context, AttributeSet attrs) {
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
	protected void onDraw(Canvas canvas) {
		
		String msg_Log;
		
		if (CV.can == null) {
			
			CV.can = canvas;
			
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
		
		////////////////////////////////

		// draw: order

		////////////////////////////////
		CONS.Canvas.Layer tmp_Layer;
		
		for (int i = CONS.Canvas.list_Layer.size() - 1; i >= 0 ; i--) {
//			for (int i = 0; i < CONS.Canvas.list_Layer.size(); i++) {
			
			tmp_Layer = CONS.Canvas.list_Layer.get(i);
			
			switch(tmp_Layer) {
			
			case Cir_A:
				
				_onDraw__CirA(canvas);
				
				break;
			
			case Rect_A:
				
				_onDraw__RectA(canvas);
				
				break;
				
			}
			
		}
		
		////////////////////////////////
		
		// draw: Rect: B
		
		////////////////////////////////
		if (CONS.Canvas.Draw_Rect_B == true) {
			
			canvas.drawRect(
					CONS.Canvas.Rect_B_X1, 
					CONS.Canvas.Rect_B_Y1, 
					CONS.Canvas.Rect_B_X1 + CONS.Canvas.Rect_B_W, 
					CONS.Canvas.Rect_B_Y1 + CONS.Canvas.Rect_B_H,
					CONS.Canvas.p_Rect_B
					);
			
		}
		
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
	_onDraw__RectA(Canvas canvas) {
		// TODO Auto-generated method stub
	
		////////////////////////////////
		
		// draw: Rect: A
		
		////////////////////////////////
		if (CONS.Canvas.Draw_Rect_A == true) {
			
			canvas.drawRect(
					CONS.Canvas.Rect_A_X1, 
					CONS.Canvas.Rect_A_Y1, 
					CONS.Canvas.Rect_A_X1 + CONS.Canvas.Rect_A_W, 
					CONS.Canvas.Rect_A_Y1 + CONS.Canvas.Rect_A_H,
					CONS.Canvas.p_Rect_A
					);
			
		}

	}
	

	private void 
	_onDraw__CirA(Canvas canvas) {
		
		////////////////////////////////

		// draw: Cicle: A

		////////////////////////////////
		if (CONS.Canvas.Draw_Circle_A == true) {
			
			canvas.drawCircle(
						CONS.Canvas.Cir_A_X, 
						CONS.Canvas.Cir_A_Y, 
						CONS.Canvas.Cir_A_Radius, 
						CONS.Canvas.p_Cir_A);
			
		}
		
	}

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


	////////////////////////////////

	// DrawableView.java

	public void 
	drawLine
	(float x1, float y1, float x2, float y2, Paint p) {
		
//		this.drawLine(10, 10, 100, 100, p);

		this.paint	= p;

		this.x1		= x1;
		this.x2		= x2;
		this.y1		= y1;
		this.y2		= y2;
		
		// Log
		String msg_Log = "drawLine() => done";
		Log.d("CV.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		// Log
		msg_Log = "p.getStrokeWidth() => " + p.getStrokeWidth();
		Log.d("CV.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		this.invalidate();
		
	}//drawLine

	public void
	draw_Boxes_A
	(Activity actv) {
		////////////////////////////////

		// set: paint

		////////////////////////////////
		CONS.Canvas.p_A = new Paint();
		
		CONS.Canvas.p_A.setColor(Color.BLUE);
		CONS.Canvas.p_A.setStrokeWidth(CONS.Canvas.lineWidth_A);
//		CONS.Canvas.p_A.setStrokeWidth(5);
		
////		this.paint_2 = new Paint();
//		
//		this.paint_2.setColor(Color.BLUE);
////      paint.setColor(Color.argb(75, 255, 255, 255));
//		this.paint_2.setStrokeWidth(5);
////		this.paint_2.setStrokeWidth(1);
		
		
		////////////////////////////////

		// set: values: A

		////////////////////////////////
		CONS.Canvas.Ax1	= 10;
		CONS.Canvas.Ay1	= 100;
		
		CONS.Canvas.AW	= 100;
		CONS.Canvas.AH	= 100;
		
		CONS.Canvas.pointsA = new float[]{

				// line 1
				CONS.Canvas.Ax1,
				CONS.Canvas.Ay1,
				
				CONS.Canvas.Ax1 + CONS.Canvas.AW,
				CONS.Canvas.Ay1,
				
				// line 2
				CONS.Canvas.Ax1 + CONS.Canvas.AW,
				CONS.Canvas.Ay1,
				
				CONS.Canvas.Ax1 + CONS.Canvas.AW,
				CONS.Canvas.Ay1 + CONS.Canvas.AH,

				// line 3
				CONS.Canvas.Ax1 + CONS.Canvas.AW,
				CONS.Canvas.Ay1 + CONS.Canvas.AH,
				
				CONS.Canvas.Ax1,
				CONS.Canvas.Ay1 + CONS.Canvas.AH,
				
				// line 4
				CONS.Canvas.Ax1,
				CONS.Canvas.Ay1 + CONS.Canvas.AH,

				CONS.Canvas.Ax1,
				CONS.Canvas.Ay1,

		};
		
//		////////////////////////////////};
		
		this.invalidate();
		
		// Log
		String msg_Log = "draw_Boxes => done";
		Log.d("CV.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
	}//draw_Boxes
	
	public void
	draw_Boxes_A
	(Activity actv, int x, int y) {
		////////////////////////////////
		
		// set: paint
		
		////////////////////////////////
		CONS.Canvas.p_A = new Paint();
		
		CONS.Canvas.p_A.setColor(Color.BLUE);
		CONS.Canvas.p_A.setStrokeWidth(CONS.Canvas.lineWidth_A);
		
		////////////////////////////////
		
		// set: values: A
		
		////////////////////////////////
		CONS.Canvas.Ax1	= x;
		CONS.Canvas.Ay1	= y;
		
		CONS.Canvas.AW	= 100;
		CONS.Canvas.AH	= 100;
		
		CONS.Canvas.pointsA = new float[]{
				
				// line 1
				CONS.Canvas.Ax1,
				CONS.Canvas.Ay1,
				
				CONS.Canvas.Ax1 + CONS.Canvas.AW,
				CONS.Canvas.Ay1,
				
				// line 2
				CONS.Canvas.Ax1 + CONS.Canvas.AW,
				CONS.Canvas.Ay1,
				
				CONS.Canvas.Ax1 + CONS.Canvas.AW,
				CONS.Canvas.Ay1 + CONS.Canvas.AH,
				
				// line 3
				CONS.Canvas.Ax1 + CONS.Canvas.AW,
				CONS.Canvas.Ay1 + CONS.Canvas.AH,
				
				CONS.Canvas.Ax1,
				CONS.Canvas.Ay1 + CONS.Canvas.AH,
				
				// line 4
				CONS.Canvas.Ax1,
				CONS.Canvas.Ay1 + CONS.Canvas.AH,
				
				CONS.Canvas.Ax1,
				CONS.Canvas.Ay1,
				
		};
		
//		////////////////////////////////};
		
		this.invalidate();
		
		// Log
		String msg_Log = "draw_Boxes => done";
		Log.d("CV.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
	}//draw_Boxes
	
	public void
	draw_Circle_A
	(Activity actv, int x, int y) {
		////////////////////////////////
		
		// set: paint
		
		////////////////////////////////
		CONS.Canvas.p_Cir_A = new Paint();
		
		CONS.Canvas.p_Cir_A.setColor(Color.BLACK);
		CONS.Canvas.p_Cir_A.setStrokeWidth(CONS.Canvas.LineWidth_Cir_A);
//		CONS.Canvas.p_A.setColor(Color.BLUE);
//		CONS.Canvas.p_A.setStrokeWidth(CONS.Canvas.LineWidth_Cir_A);
		
		////////////////////////////////
		
		// set: values: A
		
		////////////////////////////////
		CONS.Canvas.Cir_A_X	= x;
		CONS.Canvas.Cir_A_Y	= y;
		
//		CONS.Canvas.Cir_A_Radius	= CONS.Canvas.Cir_A_Radius_dflt;

		////////////////////////////////

		// flag

		////////////////////////////////
		CONS.Canvas.Draw_Circle_A = true;
		
		////////////////////////////////

		// draw

		////////////////////////////////
		this.invalidate();
		
//		// Log
//		String msg_Log = "draw_Circle_A => done";
//		Log.d("CV.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
	}//draw_Circle_A
	
	public void
	draw_Rect_A
	(Activity actv) {
		////////////////////////////////
		
		// set: paint
		
		////////////////////////////////
		CONS.Canvas.p_Rect_A = new Paint();
		
		CONS.Canvas.p_Rect_A.setColor(Color.BLUE);
		CONS.Canvas.p_Rect_A.setStrokeWidth(CONS.Canvas.LineWidth_Rect_A);
		
		////////////////////////////////
		
		// set: values: A
		
		////////////////////////////////
//		CONS.Canvas.Cir_A_X	= x;
//		CONS.Canvas.Cir_A_Y	= y;
		
//		CONS.Canvas.Cir_A_Radius	= CONS.Canvas.Cir_A_Radius_dflt;
		
		////////////////////////////////
		
		// flag
		
		////////////////////////////////
		CONS.Canvas.Draw_Rect_A = true;
		
		////////////////////////////////
		
		// draw
		
		////////////////////////////////
		this.invalidate();
		
	}//draw_Rect_A
	
	public void
	draw_Rect_B
	(Activity actv) {
		////////////////////////////////
		
		// set: paint
		
		////////////////////////////////
		CONS.Canvas.p_Rect_B = new Paint();
		
		CONS.Canvas.p_Rect_B.setColor(Color.RED);
		CONS.Canvas.p_Rect_B.setStrokeWidth(CONS.Canvas.LineWidth_Rect_B);
		
		////////////////////////////////
		
		// set: values: B
		
		////////////////////////////////
//		CONS.Canvas.Cir_A_X	= x;
//		CONS.Canvas.Cir_A_Y	= y;
		
//		CONS.Canvas.Cir_A_Radius	= CONS.Canvas.Cir_A_Radius_dflt;
		
		////////////////////////////////
		
		// flag
		
		////////////////////////////////
		CONS.Canvas.Draw_Rect_B = true;
		
		////////////////////////////////
		
		// draw
		
		////////////////////////////////
		this.invalidate();
		
	}//draw_Rect_B
	
	
	public void
	draw_Boxes_B
	(Activity actv) {
		////////////////////////////////
		
		// set: paint
		
		////////////////////////////////
		CONS.Canvas.p_B = new Paint();
		
		CONS.Canvas.p_B.setColor(Color.GREEN);
		CONS.Canvas.p_B.setStrokeWidth(CONS.Canvas.lineWidth_B);
//		CONS.Canvas.p_B.setStrokeWidth(5);

////		this.paint_2 = new Paint();
//		
//		this.paint_2.setColor(Color.GREEN);
////      paint.setColor(Color.argb(75, 255, 255, 255));
//		this.paint_2.setStrokeWidth(5);
////		this.paint_2.setStrokeWidth(1);
		
		
		////////////////////////////////
		
		// set: values: B
		
		////////////////////////////////
		CONS.Canvas.Bx1	= CONS.Canvas.Ax1;
		CONS.Canvas.By1	= CONS.Canvas.Ay1 
							+ CONS.Canvas.AH
//							+ CONS.Canvas.AH + 1 
							+ CONS.Canvas.lineWidth_B;
		
		CONS.Canvas.BW	= 250;
		CONS.Canvas.BH	= 100;
		
		CONS.Canvas.pointsB = new float[]{
				
				// line 1
				CONS.Canvas.Bx1,
				CONS.Canvas.By1,
				
				CONS.Canvas.Bx1 + CONS.Canvas.BW,
				CONS.Canvas.By1,
				
				// line 2
				CONS.Canvas.Bx1 + CONS.Canvas.BW,
				CONS.Canvas.By1,
				
				CONS.Canvas.Bx1 + CONS.Canvas.BW,
				CONS.Canvas.By1 + CONS.Canvas.BH,
				
				// line 3
				CONS.Canvas.Bx1 + CONS.Canvas.BW,
				CONS.Canvas.By1 + CONS.Canvas.BH,
				
				CONS.Canvas.Bx1,
				CONS.Canvas.By1 + CONS.Canvas.BH,
				
				// line 4
				CONS.Canvas.Bx1,
				CONS.Canvas.By1 + CONS.Canvas.BH,
				
				CONS.Canvas.Bx1,
				CONS.Canvas.By1,
				
		};
		
//		////////////////////////////////};
		
		this.invalidate();
		
		// Log
		String msg_Log = "draw_Boxes => done";
		Log.d("CV.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
	}//draw_Boxes_B
	
}
