package com.liqingyi.mapbo.fragment;

import java.util.ArrayList;

import com.liqingyi.mapbo.R;
import com.liqingyi.mapbo.adapter.CommentAdapter;
import com.liqingyi.mapbo.model.Comment;
import com.liqingyi.mapbo.model.CommentList;
import com.liqingyi.mapbo.pulltorefresh.PullToRefreshListView;
import com.liqingyi.mapbo.pulltorefresh.PullToRefreshBase.OnRefreshListener;
import com.weibo.net.Utility;
import com.weibo.net.Weibo;
import com.weibo.net.WeiboException;
import com.weibo.net.WeiboParameters;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class CommentsFragment extends Fragment {

	String id;

	private CommentAdapter commentAdapter;
	private PullToRefreshListView mPullToRefreshListView;
	private ListView actualListView;
	private ArrayList<Comment> list;
	private int page = 1;

	public static CommentsFragment newInstance(String id) {
		CommentsFragment fragment = new CommentsFragment();
		Bundle bundle = new Bundle();
		bundle.putString("id", id);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle args = getArguments();
		if (args != null) {
			id = args.getString("id");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_comments, container,
				false);
		mPullToRefreshListView = (PullToRefreshListView) view
				.findViewById(R.id.comment_list);

		mPullToRefreshListView.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				page++;
				new LoadCommentsTasks().execute(Weibo.getInstance());

			}
		});

		actualListView = mPullToRefreshListView.getRefreshableView();
		actualListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub

			}
		});

		new LoadCommentsTasks().execute(Weibo.getInstance());

		return view;
	}

	class LoadCommentsTasks extends AsyncTask<Weibo, Integer, String> {

		@Override
		protected String doInBackground(Weibo... params) {
			// TODO Auto-generated method stub
			return getComments(params[0]);
		}

		@Override
		protected void onPostExecute(String result) {
			if (!"".equals(result) && !"[]".equals(result)) {
				CommentList commentList = CommentList
						.initializeCommentList(result);

				if (list == null)
					list = new ArrayList<Comment>();

				list.addAll(commentList.getComments());
				commentAdapter = new CommentAdapter(getActivity(), list);
				actualListView.setAdapter(commentAdapter);
				commentAdapter.notifyDataSetChanged();

				actualListView.setSelection(10 * (page - 1));

			}

			mPullToRefreshListView.onRefreshComplete();
			super.onPostExecute(result);
		}

	}

	private String getComments(Weibo parameter) {
		String url = Weibo.SERVER + "comments/show.json";
		WeiboParameters bundle = new WeiboParameters();
		bundle.add("source", Weibo.getAppKey());
		bundle.add("id", id);
		bundle.add("since_id", "0");
		bundle.add("max_id", "0");
		bundle.add("count", "10");
		bundle.add("page", Integer.toString(page));
		bundle.add("filter_by_author", "0");

		try {
			return parameter.request(getActivity(), url, bundle,
					Utility.HTTPMETHOD_GET, parameter.getAccessToken());
		} catch (WeiboException e) {
			e.printStackTrace();
		}

		return null;

	}

}
