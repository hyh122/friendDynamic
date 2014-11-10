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
	 * ��ListView���ݷ����仯ʱ,���ô˷���������ListView
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
		//�õ�������̬����������
		List<FriendComment> friendComments=list.get(position).getFriendComments();
		View view = null;
		ViewHolder viewHolder = null;
		
		// �ж�convertView��״̬�����ﵽ���õ�Ч��
		if (convertView == null) {
			// ���convertViewΪ�գ����ʾ��һ����ʾ����Ҫ����һ��view
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
			
		
			
			// ��view��viewHolder��
			view.setTag(viewHolder);
		} else {
			// �����ʾ���Ը���convertView
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
			
			
			
		}
		
		//�ڸò������涯̬����LinearLayout���֣������ŵ�����ʾ������Ϣ������textview
		LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.generatedLinearLayout);
		//��ղ�����������пؼ�,ÿ�θ���ʱҪ����յ����пؼ��ٴ��¼���,����ؼ��ظ�����
		linearLayout.removeAllViews();
		//ÿ�μ���ʱ������е��������������ʾ���е��������������ظ���ʾ
		for(int i=0;i<friendComments.size();i++){
			
			LinearLayout newLinearLayout=new LinearLayout(mContext);  
	          
	         
	        newLinearLayout=generateLinearLayout(friendComments.get(i).getUserName(), friendComments.get(i).getComment());
	        linearLayout.addView(newLinearLayout);//ȫ���ø����Ĳ��ֲ���  
	    }
		
		//����Ѿ����˵�������ʾ����
		if (list.get(position).getNumOfZan() != 0) {
			viewHolder.tv_zan.setVisibility(View.VISIBLE);
			viewHolder.tv_zan.setText(list.get(position).getNumOfZan()
					+ "����:��������,����ι,��������");

		} else {
			//���û�˵���������
			viewHolder.tv_zan.setVisibility(View.GONE);
		}
		//������û��Ѿ����˸ú��ѵĶ�̬������޵�ͼƬ��ʾ��ɫ
		if (list.get(position).isZan()) {
			viewHolder.ib_zan.setBackground(mContext.getResources()
					.getDrawable(R.drawable.feed_liked_normal));

		} else {
			//������û�û�����˸ú��ѵĶ�̬������޵�ͼƬ��ʾ��ɫ
			viewHolder.ib_zan.setBackground(mContext.getResources()
					.getDrawable(R.drawable.feed_like_normal));

		}
		
		//�õ�ͷ����ֽ�����
		portrait = list.get(position).getProtrait();
		//��ͷ����ֽ�����תΪͷ��
		Bitmap bmProtrait = ImageTools.byteToBitmap(portrait);
		//����ͷ��
		viewHolder.importrait.setImageBitmap(ImageTools.zoomBitmap(bmProtrait,
				200, 210));

		viewHolder.tv_email.setText(list.get(position).getEmail());
		viewHolder.tv_userName.setText(list.get(position).getUserName());
		viewHolder.tv_date.setText(list.get(position).getDate());
		viewHolder.tv_distanceAndTime.setText("����"
				+ list.get(position).getDistance() + "km����ʱ"
				+ list.get(position).getTotalTime() + "min");
		viewHolder.tv_avgHeartAndavgSpeed.setText("ƽ������:"
				+ list.get(position).getAvgHeartRate() + "��/min,ƽ���ٶ�"
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
	 * ֻ��Ҫ����Ҫ���ü����¼������д�������ⷽ����Ϳ������� ��Ĳ���Ҫ�޸ģ�
	 */
	public void addListener(final View convertView, final int position) {
		convertView.findViewById(R.id.ib_comment).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
		
			
			final ImageButton ib_comment = (ImageButton) convertView
					.findViewById(R.id.ib_comment);
			//��������۰�ťʱ�����۰�ť�ĵ�ɫ������ǻ����ű���ͼ
			ib_comment.setBackground(mContext.getResources()
					.getDrawable(R.drawable.feed_comment_hover));
	
			//����Զ��������
			final View layout = LayoutInflater.from(mContext).inflate(
					R.layout.activity_friends_dialog, null);
			//��ʾ�Զ�������򣬲���Ӽ����¼�
			new AlertDialog.Builder(mContext)
					.setTitle("����")
					.setView(layout)//��alertdialog��Ϊ�Զ����
					.setPositiveButton("ȷ��",
					new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int which) {
		//�������ʧ�����۰�ť��ɫ��Ϊԭ����ǳɫ
		ib_comment.setBackground(mContext.getResources().getDrawable(R.drawable.feed_comment_normal));
		//���������id
		EditText et_comment = (EditText) layout
				.findViewById(R.id.et_comment);
		//ȡ������������������Ϣ
		String comment=et_comment.getText().toString();
		
		List<FriendComment> friendComments=list.get(position).getFriendComments();
		//��������Ϣ��ӵ�list��
		FriendComment friendComment=new FriendComment();
		friendComment.setUserName("����ι");
		friendComment.setComment(comment);
		friendComments.add(friendComment);
		
		
		list.get(position).setFriendComments(friendComments);

		//�����б�
		updateListView(list);
					}
					})
					.setNegativeButton("ȡ��",
					new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog,int which) {
		//�������ʧ�����۰�ť��ɫ��Ϊԭ����ǳɫ			
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
	 * ����һ��LinearLayout���ֵķ���,�ò���������textview��ɣ�һ����ʾ�����˸�����̬���û�����һ����ʾ���۵�����
	 * @param user �û���
	 * @param comment ��������
	 * @return
	 */
	private LinearLayout generateLinearLayout(String user,String comment)  
    {  
		//����һ��LinearLayout�����������textview
		LinearLayout layout_root_linear=new LinearLayout(mContext);  
        layout_root_linear.setOrientation(LinearLayout.HORIZONTAL);  
       
        //����textview����
        LinearLayout.LayoutParams LP_WW = new LinearLayout.LayoutParams(  
        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
          
        TextView tv_user = new TextView(mContext);  
        tv_user.setText(user+":"); 
        //��values�ļ��������color.xml�ļ������Ҫ����ɫ
        tv_user.setTextColor(mContext.getResources().getColor(R.color.bluegreen));  
        
        TextView tv_comment = new TextView(mContext);  
        tv_comment.setText(comment);  
        //����textview����
        layout_root_linear.addView(tv_user,LP_WW);  
        layout_root_linear.addView(tv_comment,LP_WW);  
        
        return layout_root_linear;  
}  
        
        
        
        
  


}
