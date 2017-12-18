package com.zzti.youbang.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

public class XWEditText extends android.support.v7.widget.AppCompatEditText{
	private XWEditText mthis;

	public XWEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mthis = this;
		// TODO Auto-generated constructor stub
	}
	public XWEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		mthis = this;
		// TODO Auto-generated constructor stub
	}
	
//	private PointF c

	public XWEditText(Context context) {
		super(context);
		mthis = this;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		if(e.getAction()==MotionEvent.ACTION_DOWN){
			
			//֪ͨ���ؼ���Ҫ����
			getParent().requestDisallowInterceptTouchEvent(true);
		}else if(e.getAction()==MotionEvent.ACTION_MOVE){
			
			//֪ͨ���ؼ���Ҫ����
			getParent().requestDisallowInterceptTouchEvent(true);
		}else if(e.getAction()==MotionEvent.ACTION_UP){
			
//			getParent().requestDisallowInterceptTouchEvent(true);
		}
		return super.onTouchEvent(e);
	}

}
