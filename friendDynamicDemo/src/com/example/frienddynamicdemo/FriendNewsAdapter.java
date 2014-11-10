package com.example.frienddynamicdemo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import android.view.View.OnClickListener;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FriendNewsAdapter extends BaseAdapter {

	private List<FriendNewsModal> list = null;
	private int position;
	private Context mContext;
	private byte[] portrait = null;

	public FriendNewsAdapter(Context mContext, List<FriendNewsModal> list) {
		this.mContext = mContext;
		this.list = list;

	}

	/**
	 * 当ListView数据发生变化时,调用此方法来更新ListView
	 * 
	 * @param list
	 */
	public void updateListView(List<FriendNewsModal> list) {
		this.list = list;
		notifyDataSetChanged();
	}

	public int getCount() {
		return this.list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		//得到该条动态的所有评论
		List<FriendComment> friendComments=list.get(position).getFriendComments();
		View view = null;
		ViewHolder viewHolder = null;
		
		// 判断convertView的状态，来达到复用的效果
		if (convertView == null) {
			// 如果convertView为空，则表示第一次显示，需要创建一个view
			view = LayoutInflater.from(mContext).inflate(
					R.layout.friendnews_item, null);
			

			viewHolder = new ViewHolder();
			viewHolder.importrait = (ImageView) view
					.findViewById(R.id.ib_protrait);
			viewHolder.tv_email = (TextView) view.findViewById(R.id.tv_email);
			viewHolder.tv_userName = (TextView) view
					.findViewById(R.id.tv_username);
			viewHolder.tv_date = (TextView) view.findViewById(R.id.tv_time);
			viewHolder.tv_distanceAndTime = (TextView) view
					.findViewById(R.id.tv_distanceAndTime);
			viewHolder.tv_avgHeartAndavgSpeed = (TextView) view
					.findViewById(R.id.tv_avgHeartAndavgSpeed);
			viewHolder.tv_zan = (TextView) view.findViewById(R.id.tv_zan);
			viewHolder.ib_zan = (ImageButton) view.findViewById(R.id.ib_zan);
			
			viewHolder.ib_comment = (ImageButton) view
					.findViewById(R.id.ib_comment);
			
		
			
			// 将view和viewHolder绑定
			view.setTag(viewHolder);
		} else {
			// 否则表示可以复用convertView
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
			
			
			
		}
		
		//在该布局里面动态生成LinearLayout布局，里面存放的是显示评论信息的两个textview
		LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.generatedLinearLayout);
		//清空布局里面的所有控件,每次更新时要先清空掉所有控件再从新加载,避免控件重复出现
		linearLayout.removeAllViews();
		//每次加载时候把已有的评论数情空再显示所有的评论数，避免重复显示
		for(int i=0;i<friendComments.size();i++){
			
			LinearLayout newLinearLayout=new LinearLayout(mContext);  
	          
	         
	        newLinearLayout=generateLinearLayout(friendComments.get(i).getUserName(), friendComments.get(i).getComment());
	        linearLayout.addView(newLinearLayout);//全部用父结点的布局参数  
	    }
		
		//如果已经有人点赞则显示出来
		if (list.get(position).getNumOfZan() != 0) {
			viewHolder.tv_zan.setVisibility(View.VISIBLE);
			viewHolder.tv_zan.setText(list.get(position).getNumOfZan()
					+ "个赞:海比天蓝,哎呦喂,啦啦啦等");

		} else {
			//如果没人点赞则隐藏
			viewHolder.tv_zan.setVisibility(View.GONE);
		}
		//如果我用户已经赞了该好友的动态，则点赞的图片显示亮色
		if (list.get(position).isZan()) {
			viewHolder.ib_zan.setBackground(mContext.getResources()
					.getDrawable(R.drawable.feed_liked_normal));

		} else {
			//如果我用户没有赞了该好友的动态，则点赞的图片显示暗色
			viewHolder.ib_zan.setBackground(mContext.getResources()
					.getDrawable(R.drawable.feed_like_normal));

		}
		
		//得到头像的字节数组
		portrait = list.get(position).getProtrait();
		//将头像的字节数组转为头像
		Bitmap bmProtrait = ImageTools.byteToBitmap(portrait);
		//设置头像
		viewHolder.importrait.setImageBitmap(ImageTools.zoomBitmap(bmProtrait,
				200, 210));

		viewHolder.tv_email.setText(list.get(position).getEmail());
		viewHolder.tv_userName.setText(list.get(position).getUserName());
		viewHolder.tv_date.setText(list.get(position).getDate());
		viewHolder.tv_distanceAndTime.setText("跑了"
				+ list.get(position).getDistance() + "km，用时"
				+ list.get(position).getTotalTime() + "min");
		viewHolder.tv_avgHeartAndavgSpeed.setText("平均心率:"
				+ list.get(position).getAvgHeartRate() + "次/min,平均速度"
				+ list.get(position).getAvgSpeed() + "m/s");

		addListener(view, position);
		return view;

	}

	final static class ViewHolder {
		TextView userTextView;
		TextView commentTextView;

		ImageView importrait;

		TextView tv_userName;
		TextView tv_date;
		TextView tv_email;
		TextView tv_distanceAndTime;
		TextView tv_avgHeartAndavgSpeed;
		TextView tv_zan;

		ImageButton ib_zan;
		ImageButton ib_comment;
		List<TextView> userTextViews = new ArrayList<TextView>();
		List<TextView> commentTextViews = new ArrayList<TextView>();

		public void addUserTextView() {

			userTextViews.add(userTextView);
		}

		public void addCommentTextView() {
			commentTextViews.add(commentTextView);
		}
	}

	/**
	 * 只需要将需要设置监听事件的组件写在下面这方法里就可以啦！ 别的不需要修改！
	 */
	public void addListener(final View convertView, final int position) {
		convertView.findViewById(R.id.ib_comment).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
		
			
			final ImageButton ib_comment = (ImageButton) convertView
					.findViewById(R.id.ib_comment);
			//当点击评论按钮时，评论按钮的底色变深，就是换了张背景图
			ib_comment.setBackground(mContext.getResources()
					.getDrawable(R.drawable.feed_comment_hover));
	
			//获得自定义输入框
			final View layout = LayoutInflater.from(mContext).inflate(
					R.layout.activity_friends_dialog, null);
			//显示自定义输入框，并添加监听事件
			new AlertDialog.Builder(mContext)
					.setTitle("评论")
					.setView(layout)//将alertdialog设为自定义的
					.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int which) {
		//输入框消失后将评论按钮底色设为原来的浅色
		ib_comment.setBackground(mContext.getResources().getDrawable(R.drawable.feed_comment_normal));
		//获得输入框的id
		EditText et_comment = (EditText) layout
				.findViewById(R.id.et_comment);
		//取得输入框输入的评论信息
		String comment=et_comment.getText().toString();
		
		List<FriendComment> friendComments=list.get(position).getFriendComments();
		//将评论信息添加到list中
		FriendComment friendComment=new FriendComment();
		friendComment.setUserName("哎呦喂");
		friendComment.setComment(comment);
		friendComments.add(friendComment);
		
		
		list.get(position).setFriendComments(friendComments);

		//更新列表
		updateListView(list);
					}
					})
					.setNegativeButton("取消",
					new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog,int which) {
		//输入框消失后将评论按钮底色设为原来的浅色			
		ib_comment.setBackground(mContext.getResources().getDrawable(R.drawable.feed_comment_normal));
					}

				}).show();
		}
					
	});
		convertView.findViewById(R.id.ib_zan).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

						ImageButton ib_zan = (ImageButton) convertView
								.findViewById(R.id.ib_zan);

						int numOfZan = list.get(position).getNumOfZan();

						if (list.get(position).isZan()) {

							numOfZan--;
							list.get(position).setZan(false);

						} else {

							numOfZan++;
							list.get(position).setZan(true);
						}

						list.get(position).setNumOfZan(numOfZan);
						updateListView(list);
					}
				});

	}
	
	/**
	 * 生成一个LinearLayout布局的方法,该布局由两个textview组成，一个显示评论了该条动态的用户名，一个显示评论的内容
	 * @param user 用户名
	 * @param comment 评论内容
	 * @return
	 */
	private LinearLayout generateLinearLayout(String user,String comment)  
    {  
		//生成一个LinearLayout里面包含两个textview
		LinearLayout layout_root_linear=new LinearLayout(mContext);  
        layout_root_linear.setOrientation(LinearLayout.HORIZONTAL);  
       
        //设置textview布局
        LinearLayout.LayoutParams LP_WW = new LinearLayout.LayoutParams(  
        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
          
        TextView tv_user = new TextView(mContext);  
        tv_user.setText(user+":"); 
        //从values文件夹里面的color.xml文件获得所要的颜色
        tv_user.setTextColor(mContext.getResources().getColor(R.color.bluegreen));  
        
        TextView tv_comment = new TextView(mContext);  
        tv_comment.setText(comment);  
        //设置textview布局
        layout_root_linear.addView(tv_user,LP_WW);  
        layout_root_linear.addView(tv_comment,LP_WW);  
        
        return layout_root_linear;  
}  
        
        
        
        
  


}
