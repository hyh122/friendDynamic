package com.example.frienddynamicdemo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;


import com.example.frienddynamicdemo.PullRefreshListView.IXListViewListener;



public class FriendNewsActivity extends Activity implements IXListViewListener{
	// 可上拉刷新下拉显示更多数据的listview
		private PullRefreshListView pullRefreshListView;
		// 线程handle
		private Handler mHandler;
		private int sizeOfAllFriendNews;
		
		private List<FriendNewsModal> friendNewsList=new ArrayList<FriendNewsModal>();

		
		private FriendNewsAdapter adapter;
		
	    
	

		/**
		 * 标记从该页面跳转到好友信息的页面
		 */
		public static int FROMFRIENDNewsACTIVITY = 3;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_friend_news);
			/** 使得打开界面后不自动跳出软键盘 */
			getWindow().setSoftInputMode(
					WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
			getActionBar().setDisplayHomeAsUpEnabled(true);
			setTitle("好友动态");
			initViews();
			
		}

		private void initViews() {
		
			pullRefreshListView = (PullRefreshListView) findViewById(R.id.pullRefreshListView);
			pullRefreshListView.setPullLoadEnable(true);

			// 下拉刷新后显示更新数据后的界面信息
			getItemsForRefresh();

			// 刚打开界面时不显示任何数据

			adapter = new FriendNewsAdapter(this, friendNewsList);

			pullRefreshListView.setAdapter(adapter);

			pullRefreshListView.setXListViewListener(this);
			mHandler = new Handler();

			

			
		}

		

		
	

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {// 创建系统功能菜单
			// TODO Auto-generated method stub
			// 将自己写的菜单项添加到actiovbar
			MenuInflater inflater = getMenuInflater();

			
			return super.onCreateOptionsMenu(menu);
		}

		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			switch (item.getItemId()) {
			case android.R.id.home:
				this.finish();
				return true;
			

			}
			return super.onOptionsItemSelected(item);
		}

		private void getItemsForRefresh() {
			// 每次刷新都得先从数据库获得最新的数据
//			User user=systemManagerService.getCurrentLoginedUser();
//			friends = user.getFriends();
//			sizeOfAllFriend = friends.size();

//			// 装配到viewModal
			int i;
//			friendNewsList = new ArrayList<FriendNewsModal>();
//			int head;
//			int tail;
//			if (friends.size() - 1 < 0) {
//				head = -1;
//			} else {
//				head = friends.size() - 1;// 7
//			}
//			//设置每一页最多只显示15条好友信息，多余的则通过上拉加载更多
//			if (friends.size() - 15 <= 0) {
//				tail = 0;
//				sizeOfAllFriend = 0;
//			} else {
//				tail = friends.size() - 15;// 3
//				sizeOfAllFriend = friends.size() - 15;// 3
//			}
//			for (i = head; i >= tail; i--) {
//
//				Friends myFriend = new Friends();
//				myFriend = friends.get(i);
//
//				FriendNewsModal friendNewsModal = new FriendNewsModal();
//				friendNewsList.add(friendNewsModal);
//			}
			
			

			

				FriendNewsModal friendNewsModal = new FriendNewsModal();
				friendNewsModal.setAvgHeartRate(66);
				friendNewsModal.setAvgSpeed(5.4);
				friendNewsModal.setDate("今天 15:12");
				friendNewsModal.setDistance(14.4);
				friendNewsModal.setEmail("1215605211@qq.com");
				friendNewsModal.setProtrait(ImageTools.drawableToBytes(getResources().getDrawable(R.drawable.start_running_title)));
				friendNewsModal.setTotalTime(16);
				friendNewsModal.setUserName("寂寞哥");
				friendNewsModal.setNumOfZan(3);
				
				List<FriendComment> friendComments=new ArrayList<FriendComment>();
				FriendComment friendComment=new FriendComment();
				friendComment.setUserName("大宝宝");
				friendComment.setComment("啦啦啦啦啦");
				friendComments.add(friendComment);
				FriendComment friendComment2=new FriendComment();
				friendComment2.setUserName("大宝宝");
				friendComment2.setComment("啦啦啦啦啦");
				friendComments.add(friendComment2);
				friendNewsModal.setFriendComments(friendComments);
				
				friendNewsList.add(friendNewsModal);
			

		}

		private void geneItemsForLoadMore() {

			// 装配到viewModal
//			int i;
//
//			int head;
//			int tail;
//			if (sizeOfAllFriend - 1 < 0) {
//				head = -1;
//			} else {
//				head = sizeOfAllFriend - 1;
//			}
//			if (sizeOfAllFriend - 15 <= 0) {
//				tail = 0;
//			} else {
//				tail = sizeOfAllFriend - 15;
//			}
//			for (i = head; i >= tail; i--) {
//
//				Friends myFriend = new Friends();
//				myFriend = friends.get(i);
//
//				FriendsListModel friendsListModal = new FriendsListModel(
//						myFriend.getAnotherName(), myFriend.getEmail(),myFriend.getPersonalWord(),myFriend.getProtrait());
//
//				friendsDataList.add(friendsListModal);
//			}
//			sizeOfAllFriend = i + 1;
			FriendNewsModal friendNewsModal = new FriendNewsModal();
			friendNewsModal.setAvgHeartRate(65);
			friendNewsModal.setAvgSpeed(4.4);
			friendNewsModal.setDate("今天 18:12");
			friendNewsModal.setDistance(14.4);
			friendNewsModal.setEmail("1215605211@qq.com");
			friendNewsModal.setProtrait(ImageTools.drawableToBytes(getResources().getDrawable(R.drawable.start_running_title)));
			friendNewsModal.setTotalTime(36);
			friendNewsModal.setUserName("啊哦工");
			List<FriendComment> friendComments=new ArrayList<FriendComment>();
			FriendComment friendComment=new FriendComment();
			friendComment.setUserName("耀辉");
			friendComment.setComment("你好啊");
			friendComments.add(friendComment);
			friendNewsModal.setFriendComments(friendComments);
			friendNewsList.add(friendNewsModal);
		}

		private void onLoad() {
			pullRefreshListView.stopRefresh();
			pullRefreshListView.stopLoadMore();
			pullRefreshListView.setRefreshTime("刚刚");
		}

		@Override
		public void onRefresh() {
			mHandler.postDelayed(new Runnable() {
				@Override
				public void run() {

					getItemsForRefresh();
					adapter.updateListView(friendNewsList);

					onLoad();
				}
			}, 2000);
		}

		@Override
		public void onLoadMore() {
			mHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					geneItemsForLoadMore();
					adapter.notifyDataSetChanged();
					adapter.updateListView(friendNewsList);

					onLoad();
				}
			}, 2000);
		}
}
