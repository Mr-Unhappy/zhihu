package com.liu.zhihu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.liu.zhihu.R;
import com.liu.zhihu.entity.BaseEntity;
import com.liu.zhihu.entity.StartImageEntity;
import com.liu.zhihu.net.NetRequest;
import com.liu.zhihu.net.RequestService;
import com.squareup.picasso.Picasso;


/**
 * Created by Ming on 2016/3/20.
 */
public class SplashActivity extends BaseActivity {

	private ImageView iv_splash;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			Intent intent = new Intent(SplashActivity.this,MainActivity.class);
			startActivity(intent);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		iv_splash = (ImageView) findViewById(R.id.iv_splash);

		queryData();
	}

	private void startAnimation() {
		AnimationSet as = new AnimationSet(true);
		as.setDuration(2*1000);

		ScaleAnimation scaleAnimation = new ScaleAnimation(1, 1.2F, 1, 1.2F, Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);
		scaleAnimation.setDuration(1500);

		AlphaAnimation alphaAnimation = new AlphaAnimation(1,0);
		alphaAnimation.setDuration(500);
		alphaAnimation.setStartOffset(1500);

		as.addAnimation(scaleAnimation);
		as.addAnimation(alphaAnimation);
		as.setFillAfter(true);
		iv_splash.setAnimation(as);
	}

	private void queryData() {
		RequestService.getInstance().startImage(this, StartImageEntity.class, new NetRequest.RequestListener() {
			@Override
			public void onSuccess(BaseEntity result) {
				if (result.isOk()) {
					StartImageEntity entity = (StartImageEntity) result;

					Picasso.with(SplashActivity.this).load(entity.getImg()).into(iv_splash);
					startAnimation();
					handler.sendEmptyMessageDelayed(0, 2100);
				} else {
					Log.e("errorType", result.getError_type() + "");
					Log.e("errorMsg", result.getError_msg());
				}
			}

			@Override
			public void onFailed(Exception exception, String msg) {
				exception.printStackTrace();
			}
		});
	}
}
