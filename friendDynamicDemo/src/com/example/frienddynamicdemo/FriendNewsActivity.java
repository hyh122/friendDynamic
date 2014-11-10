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
	// ������ˢ��������ʾ�������ݵ�listview
		private PullRefreshListView pullRefreshListView;
		// �߳�handle
		private Handler mHandler;
		private int sizeOfAllFriendNews;
		
		private List<FriendNewsModal> friendNewsList=new ArrayList<FriendNewsModal>();

		
		private FriendNewsAdapter adapter;
		
	    
	

		/**
		 * ��ǴӸ�ҳ����ת��������Ϣ��ҳ��
		 */
		public static int FROMFRIENDNewsACTIVITY = 3;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_friend_news);
			/** ʹ�ô򿪽�����Զ���������� */
			getWindow().setSoftInputMode(
					WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
			getActionBar().setDisplayHomeAsUpEnabled(true);
			setTitle("���Ѷ�̬");
			initViews();
			
		}

		private void initViews() {
		
			pullRefreshListView = (PullRefreshListView) findViewById(R.id.pullRefreshListView);
			pullRefreshListView.setPullLoadEnable(true);

			// ����ˢ�º���ʾ�������ݺ�Ľ�����Ϣ
			getItemsForRefresh();

			// �մ򿪽���ʱ����ʾ�κ�����

			adapter = new FriendNewsAdapter(this, friendNewsList);

			pullRefreshListView.setAdapter(adapter);

			pullRefreshListView.setXListViewListener(this);
			mHandler = new Handler();

			

			
		}

		

		
	

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {// ����ϵͳ���ܲ˵�
			// TODO Auto-generated method stub
			// ���Լ�д�Ĳ˵�����ӵ�actiovbar
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
			// ÿ��ˢ�¶����ȴ����ݿ������µ�����
//			User user=systemManagerService.getCurrentLoginedUser();
//			friends = user.getFriends();
//			sizeOfAllFriend = friends.size();

//			// װ�䵽viewModal
			int i;
//			friendNewsList = new ArrayList<FriendNewsModal>();
//			int head;
//			int tail;
//			if (friends.size() - 1 < 0) {
//				head = -1;
//			} else {
//				head = friends.size() - 1;// 7
//			}
//			//����ÿһҳ���ֻ��ʾ15��������Ϣ���������ͨ���������ظ���
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
				friendNewsModal.setDate("���� 15:12");
				friendNewsModal.setDistance(14.4);
				friendNewsModal.setEmail("1215605211@qq.com");
				friendNewsModal.setProtrait(ImageTools.drawableToBytes(getResources().getDrawable(R.drawable.start_running_title)));
				friendNewsModal.setTotalTime(16);
				friendNewsModal.setUserName("��į��");
				friendNewsModal.setNumOfZan(3);
				
				List<FriendComment> friendComments=new ArrayList<FriendComment>();
				FriendComment friendComment=new FriendComment();
				friendComment.setUserName("�󱦱�");
				friendComment.setComment("����������");
				friendComments.add(friendComment);
				FriendComment friendComment2=new FriendComment();
				friendComment2.setUserName("�󱦱�");
				friendComment2.setComment("����������");
				friendComments.add(friendComment2);
				friendNewsModal.setFriendComments(friendComments);
				
				friendNewsList.add(friendNewsModal);
			

		}

		private void geneItemsForLoadMore() {

			// װ�䵽viewModal
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
			friendNewsModal.setDate("���� 18:12");
			friendNewsModal.setDistance(14.4);
			friendNewsModal.setEmail("1215605211@qq.com");
			friendNewsModal.setProtrait(ImageTools.drawableToBytes(getResources().getDrawable(R.drawable.start_running_title)));
			friendNewsModal.setTotalTime(36);
			friendNewsModal.setUserName("��Ŷ��");
			List<FriendComment> friendComments=new ArrayList<FriendComment>();
			FriendComment friendComment=new FriendComment();
			friendComment.setUserName("ҫ��");
			friendComment.setComment("��ð�");
			friendComments.add(friendComment);
			friendNewsModal.setFriendComments(friendComments);
			friendNewsList.add(friendNewsModal);
		}

		private void onLoad() {
			pullRefreshListView.stopRefresh();
			pullRefreshListView.stopLoadMore();
			pullRefreshListView.setRefreshTime("�ո�");
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
