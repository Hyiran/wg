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
	 * ��ʼ��popWindow
	 * */
	private void initPopWindow() {
		View popView = inflater.inflate(R.layout.listview_pop, null);
		popupWindow = new PopupWindow(popView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		popupWindow.setBackgroundDrawable(new ColorDrawable(0));
		//����popwindow���ֺ���ʧ����
		popupWindow.setAnimationStyle(R.style.PopMenuAnimation);
		btn_pop_close = (ImageView) popView.findViewById(R.id.btn_pop_close);
	}
	
	/** popWindow �رհ�ť */
	private ImageView btn_pop_close;
	
	/** 
	 * ��ʾpopWindow
	 * */
	public void showPop(View parent, int x, int y,int postion) {
		//����popwindow��ʾλ��
		popupWindow.showAtLocation(parent, 0, x, y);
		//��ȡpopwindow����
		popupWindow.setFocusable(true);
		//����popwindow�������������򣬱�رա�
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
	 * ÿ��ITEM��more��ť��Ӧ�ĵ������
	 * */
	public class popAction implements OnClickListener{
		int position;
		public popAction(int position){
			this.position = position;
		}
		@Override
		public void onClick(View v) {
			int[] arrayOfInt = new int[2];
			//��ȡ�����ť������
			v.getLocationOnScreen(arrayOfInt);
	        int x = arrayOfInt[0];
	        int y = arrayOfInt[1];
	        showPop(v, x , y, position);
		}
	}
}
