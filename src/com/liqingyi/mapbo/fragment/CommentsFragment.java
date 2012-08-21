package com.liqingyi.mapbo.fragment;

import com.liqingyi.mapbo.R;
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

public class CommentsFragment extends Fragment {

	String id;

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
			System.out.println(result);
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
		bundle.add("page", "1");
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
