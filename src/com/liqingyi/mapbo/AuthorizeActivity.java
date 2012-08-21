/*
 * Copyright 2011 Sina.
 *
 * Licensed under the Apache License and Weibo License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.open.weibo.com
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.liqingyi.mapbo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.liqingyi.mapbo.actionbarcompat.BaseActivity;
import com.weibo.net.AccessToken;
import com.weibo.net.DialogError;
import com.weibo.net.Weibo;
import com.weibo.net.WeiboDialogListener;
import com.weibo.net.WeiboException;

public class AuthorizeActivity extends BaseActivity {
	/** Called when the activity is first created. */
	private Button mLogin;
	private TextView mToken;

	// 设置appkey及appsecret，如何获取新浪微博appkey和appsecret请另外查询相关信息，此处不作介绍
	private static final String CONSUMER_KEY = "3689728164";// 替换为开发者的appkey，例如"1646212960";
	private static final String CONSUMER_SECRET = "fda4b4c39fd69609a84f2abcbf751db7";// 替换为开发者的appkey，例如"94098772160b6f8ffc1315374d8861f9";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mToken = (TextView) this.findViewById(R.id.tvToken);
		mLogin = (Button) this.findViewById(R.id.btnLogin);
		mLogin.setText("oauth!");
		mLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (v == mLogin) {

					Weibo weibo = Weibo.getInstance();
					weibo.setupConsumerConfig(CONSUMER_KEY, CONSUMER_SECRET);

					// Oauth2.0 隐式授权认证方式
					weibo.setRedirectUrl("http://mapbo.com");// 此处回调页内容应该替换为与appkey对应的应用回调页
					weibo.authorize(AuthorizeActivity.this,
							new AuthDialogListener());
				}
			}
		});

	}

	public void onResume() {
		super.onResume();
	}

	class AuthDialogListener implements WeiboDialogListener {

		@Override
		public void onComplete(Bundle values) {
			String token = values.getString("access_token");
			String expires_in = values.getString("expires_in");
			mToken.setText("access_token : " + token + "  expires_in: "
					+ expires_in);
			AccessToken accessToken = new AccessToken(token, CONSUMER_SECRET);
			accessToken.setExpiresIn(expires_in);
			Weibo.getInstance().setAccessToken(accessToken);
			Intent intent = new Intent();
			intent.setClass(AuthorizeActivity.this, PoiListActivity.class);
			startActivity(intent);
		}

		@Override
		public void onError(DialogError e) {
			Toast.makeText(getApplicationContext(),
					"Auth error : " + e.getMessage(), Toast.LENGTH_LONG).show();
		}

		@Override
		public void onCancel() {
			Toast.makeText(getApplicationContext(), "Auth cancel",
					Toast.LENGTH_LONG).show();
		}

		@Override
		public void onWeiboException(WeiboException e) {
			Toast.makeText(getApplicationContext(),
					"Auth exception : " + e.getMessage(), Toast.LENGTH_LONG)
					.show();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.main, menu);

		// Calling super after populating the menu is necessary here to ensure
		// that the
		// action bar helpers have a chance to handle this event.
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_search:
			Intent intent = new Intent(getApplicationContext(),
					SearchPoiActivity.class);

			startActivity(intent);
			break;

		default:
			break;
		}

		return super.onMenuItemSelected(featureId, item);
	}

}