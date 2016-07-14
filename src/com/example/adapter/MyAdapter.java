package com.example.adapter;

import java.util.ArrayList;

import com.example.bean.News;
import com.example.wg.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * author:RA
 * blog : http://blog.csdn.net/vipzjyno1/
 */
public class MyAdapter extends BaseAdapter {
	LayoutInflater inflater = null;
	Activity activity;
	ArrayList<News> newslist;
	private PopupWindow popupWindow;

	public MyAdapter(Activity activity, ArrayList<News> newslist) {
		this.activity = activity;
		this.newslist = newslist;
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		initPopWindow();
	}

	@Override
	public int getCount() {
		return newslist != null ? newslist.size() : 0;
	}

	@Override
	public News getItem(int position) {
		if (newslist != null && newslist.size() != 0) {
			return newslist.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		final ViewHolder holder;
		if (vi == null) {
			vi = inflater.inflate(R.layout.listview_item, null);
			holder = new ViewHolder();
			holder.item_title = (TextView) vi.findViewById(R.id.item_title);
			holder.item_content = (TextView) vi.findViewById(R.id.item_content);
			holder.button_showpop = (ImageView) vi.findViewById(R.id.button_showpop);
			vi.setTag(holder);
		} else {
			holder = (ViewHolder) vi.getTag();
		}
		News news = getItem(position);
		holder.item_title.setText(news.getTitle());
		holder.item_content.setText(news.getContent());
		holder.button_showpop .setOnClickListener(new popAction(position));
		return vi;
	}

	public class ViewHolder {
		TextView item_title;
		TextView item_content;
		ImageView button_showpop;
	}
	
	/** 
	 * 初始化popWindow
	 * */
	private void initPopWindow() {
		View popView = inflater.inflate(R.layout.listview_pop, null);
		popupWindow = new PopupWindow(popView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		popupWindow.setBackgroundDrawable(new ColorDrawable(0));
		//设置popwindow出现和消失动画
		popupWindow.setAnimationStyle(R.style.PopMenuAnimation);
		btn_pop_close = (ImageView) popView.findViewById(R.id.btn_pop_close);
	}
	
	/** popWindow 关闭按钮 */
	private ImageView btn_pop_close;
	
	/** 
	 * 显示popWindow
	 * */
	public void showPop(View parent, int x, int y,int postion) {
		//设置popwindow显示位置
		popupWindow.showAtLocation(parent, 0, x, y);
		//获取popwindow焦点
		popupWindow.setFocusable(true);
		//设置popwindow如果点击外面区域，便关闭。
		popupWindow.setOutsideTouchable(true);
		popupWindow.update();
		if (popupWindow.isShowing()) {
			
		}
		btn_pop_close.setOnClickListener(new OnClickListener() {
			public void onClick(View paramView) {
				popupWindow.dismiss();
			}
		});
	}
	
	/** 
	 * 每个ITEM中more按钮对应的点击动作
	 * */
	public class popAction implements OnClickListener{
		int position;
		public popAction(int position){
			this.position = position;
		}
		@Override
		public void onClick(View v) {
			int[] arrayOfInt = new int[2];
			//获取点击按钮的坐标
			v.getLocationOnScreen(arrayOfInt);
	        int x = arrayOfInt[0];
	        int y = arrayOfInt[1];
	        showPop(v, x , y, position);
		}
	}
}
