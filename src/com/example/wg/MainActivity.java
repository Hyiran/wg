package com.example.wg;

import java.util.ArrayList;

import com.example.adapter.MyAdapter;
import com.example.bean.News;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class MainActivity extends Activity {
	private ListView listview;
	MyAdapter adapter;
	ArrayList<News> newslist;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();
		initData();
	}

	private void initData() {
		newslist = new ArrayList<News>();
		for(int i = 0; i < 5; i ++){
			News news = new News();
			news.setNo(i);
			news.setTitle("第" + i + "条新闻");
			news.setContent(i + "的内容是。。。。。");
			newslist.add(news);
		}
		if(newslist != null){
			adapter = new MyAdapter(this, newslist);
			listview.setAdapter(adapter);
		}
	}

	private void initView() {
		listview = (ListView) findViewById(R.id.listview);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
