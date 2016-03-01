package com.zhangpeng.heropk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 选择英雄的Activity
 * 
 * @author Zhang Peng 2014-8-2
 */
public class SelectHeroActivity extends Activity {

	private ImageView image1;
	private ImageView image2;
	private TextView heroName1;
	private TextView heroName2;
	private Button button;
	/** 第一个英雄的名字缩写，初始MG */
	private String hero1 = "MG";
	/** 第二个英雄的名字缩写，初始BM */
	private String hero2 = "BM";
	/** 英雄名称的数组 */
	private String[] heros = new String[] { "野蛮人", "恶魔猎人", "武僧", "圣骑士", "魔法师" };
	/** 英雄名称缩写的数组 */
	private String[] herosEng = new String[] { "BM", "DH", "MK", "GD", "MG" };
	/** 选取英雄的序号 */
	private int heroid;
	/** 英雄1的序号,初始MG */
	private int hero1id = 4;
	/** 英雄2的序号,初始BM */
	private int hero2id = 0;
	ImageView image;
	Handler handler;

	@SuppressLint("HandlerLeak")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selecthero);
		image1 = (ImageView) findViewById(R.id.sh_imageView1);
		image2 = (ImageView) findViewById(R.id.sh_imageView2);
		heroName1 = (TextView) findViewById(R.id.sh_heroText1);
		heroName2 = (TextView) findViewById(R.id.sh_heroText2);
		button = (Button) findViewById(R.id.sh_button1);
		Intent intent = getIntent();
		Bundle data = intent.getExtras();
		if (data != null) {
			hero1id = data.getInt("hero1id");
			hero2id = data.getInt("hero2id");
			setImageAndText(hero1id, image1);
			setImageAndText(hero2id, image2);
		}
		image1.setOnClickListener(new ImageClickListener());
		image2.setOnClickListener(new ImageClickListener());
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SelectHeroActivity.this, FightGroundActivity.class);
				Bundle bundle = new Bundle();
				bundle.putCharSequence("hero1", hero1);
				bundle.putCharSequence("hero2", hero2);
				bundle.putInt("hero1id", hero1id);
				bundle.putInt("hero2id", hero2id);
				intent.putExtras(bundle);
				startActivity(intent);
				finish();
			}
		});
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 0x123) {
					setImageAndText(heroid, image);
					// 如果两个英雄是一样的，弹出提示将开始按钮失效，如果不一样才可以开始游戏
					if (hero1.equals(hero2)) {
						Toast.makeText(SelectHeroActivity.this, "两个英雄不能相同", Toast.LENGTH_SHORT).show();
						button.setEnabled(false);
					} else {
						button.setEnabled(true);
					}
				}
			}
		};
	}

	private void setImageAndText(int heroId, ImageView image) {
		// 根据选取英雄的序号来替换图标
		switch (heroId) {
		case 0:
			image.setImageResource(R.drawable.bm_icon);
			break;
		case 1:
			image.setImageResource(R.drawable.dh_icon);
			break;
		case 2:
			image.setImageResource(R.drawable.mk_icon);
			break;
		case 3:
			image.setImageResource(R.drawable.gd_icon);
			break;
		case 4:
			image.setImageResource(R.drawable.mg_icon);
			break;
		}

		// 将对应的文字也更新
		if (image.getId() == R.id.sh_imageView1) {
			heroName1.setText(heros[heroId]);
			hero1 = herosEng[heroId];
			hero1id = heroId;
		} else {
			heroName2.setText(heros[heroId]);
			hero2 = herosEng[heroId];
			hero2id = heroId;
		}
	}

	class ImageClickListener implements OnClickListener {

		/** 标记是否点了选项，解决不点选项直接点确定结果不对的BUG */
		boolean hasSelected = false;

		@Override
		public void onClick(View v) {
			image = (ImageView) v;
			AlertDialog.Builder builder = new AlertDialog.Builder(SelectHeroActivity.this);
			builder.setTitle("选择英雄").setIcon(R.drawable.ic_launcher)
					.setSingleChoiceItems(heros, 0, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							heroid = which;
							hasSelected = true;
						}
					}).setPositiveButton("确定", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							if (!hasSelected) {
								heroid = 0;
							}
							handler.sendEmptyMessage(0x123);
							hasSelected = false;
						}
					}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// do nothing
						}
					}).create().show();
		}
	}
}
