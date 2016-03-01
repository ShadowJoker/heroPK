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
 * ѡ��Ӣ�۵�Activity
 * 
 * @author Zhang Peng 2014-8-2
 */
public class SelectHeroActivity extends Activity {

	private ImageView image1;
	private ImageView image2;
	private TextView heroName1;
	private TextView heroName2;
	private Button button;
	/** ��һ��Ӣ�۵�������д����ʼMG */
	private String hero1 = "MG";
	/** �ڶ���Ӣ�۵�������д����ʼBM */
	private String hero2 = "BM";
	/** Ӣ�����Ƶ����� */
	private String[] heros = new String[] { "Ұ����", "��ħ����", "��ɮ", "ʥ��ʿ", "ħ��ʦ" };
	/** Ӣ��������д������ */
	private String[] herosEng = new String[] { "BM", "DH", "MK", "GD", "MG" };
	/** ѡȡӢ�۵���� */
	private int heroid;
	/** Ӣ��1�����,��ʼMG */
	private int hero1id = 4;
	/** Ӣ��2�����,��ʼBM */
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
					// �������Ӣ����һ���ģ�������ʾ����ʼ��ťʧЧ�������һ���ſ��Կ�ʼ��Ϸ
					if (hero1.equals(hero2)) {
						Toast.makeText(SelectHeroActivity.this, "����Ӣ�۲�����ͬ", Toast.LENGTH_SHORT).show();
						button.setEnabled(false);
					} else {
						button.setEnabled(true);
					}
				}
			}
		};
	}

	private void setImageAndText(int heroId, ImageView image) {
		// ����ѡȡӢ�۵�������滻ͼ��
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

		// ����Ӧ������Ҳ����
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

		/** ����Ƿ����ѡ��������ѡ��ֱ�ӵ�ȷ��������Ե�BUG */
		boolean hasSelected = false;

		@Override
		public void onClick(View v) {
			image = (ImageView) v;
			AlertDialog.Builder builder = new AlertDialog.Builder(SelectHeroActivity.this);
			builder.setTitle("ѡ��Ӣ��").setIcon(R.drawable.ic_launcher)
					.setSingleChoiceItems(heros, 0, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							heroid = which;
							hasSelected = true;
						}
					}).setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							if (!hasSelected) {
								heroid = 0;
							}
							handler.sendEmptyMessage(0x123);
							hasSelected = false;
						}
					}).setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// do nothing
						}
					}).create().show();
		}
	}
}
