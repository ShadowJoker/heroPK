package com.zhangpeng.heropk;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import com.ustb.heropk.heros.BMHero;
import com.ustb.heropk.heros.DHHero;
import com.ustb.heropk.heros.GDHero;
import com.ustb.heropk.heros.Hero;
import com.ustb.heropk.heros.MGHero;
import com.ustb.heropk.heros.MKHero;
import com.ustb.heropk.utils.GameConsole;
import com.ustb.heropk.utils.GameUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Ӣ�۶�ս��Activity
 * 
 * @author Zhang Peng 2014-8-2
 */
public class FightGroundActivity extends Activity {

	TableRow tablerow;
	ScrollView scroll;
	Button startButton;
	ImageView hero1image;
	ImageView hero2image;
	TextView hero1name;
	TextView hero1health;
	TextView hero1damage;
	TextView hero1attactrate;
	TextView hero2name;
	TextView hero2health;
	TextView hero2damage;
	TextView hero2attactrate;
	TextView fightTimerText;
	TextView fightInfo;
	/** Ӣ�����Ƶ����� */
	private String[] heros = new String[] { "Ұ����", "��ħ����", "��ɮ", "ʥ��ʿ", "ħ��ʦ" };
	/** Ӣ��������д������ */
	private String[] herosEng = new String[] { "BM", "DH", "MK", "GD", "MG" };
	private String hero1engname;
	private String hero2engname;
	private int hero1id;
	private int hero2id;
	private Hero heroA;
	private Hero heroB;
	private Timer timerA;
	private Timer timerB;
	private Timer globalTimer;
	private double globalTime = 0;
	private TimerTask taskA;
	private TimerTask taskB;
	private TimerTask globalTimerTask;
	private Date date;
	private int movecount = 1;
	final Object lock = new Object();
	DecimalFormat formatter = new DecimalFormat("0.0");
	/** ������Ϣ��handler */
	final Handler handler = new Handler() {
		// ����Ƿ�ս�������ı���������������п��ܶ�һ�غϵ�BUG
		boolean gameOver = false;

		/**
		 * 0x123 ��ͨս����Ϣ 0x124 ��ʤ��Ϣ 0x125 ����ȫ�ּ�ʱ����ʾ�ı�
		 */
		@Override
		public void handleMessage(Message msg) {
			Bundle bundle = msg.getData();
			if (gameOver) {
				return;
			}
			if (msg.what == 0x123) {
				fightInfo.setText("\n��" + movecount++ + "�غ�    " + bundle.getString("time") + "��" + "\n"
						+ bundle.getString("data") + "\n" + fightInfo.getText());
				hero1health.setText("����ֵ��" + String.valueOf(heroA.getHealth()));
				hero2health.setText("����ֵ��" + String.valueOf(heroB.getHealth()));
			}
			if (msg.what == 0x124) {
				gameOver = true;
				fightInfo.setText("\n" + bundle.getString("data") + "\n" + fightInfo.getText() + "\n");
				heroA.getTimerTask().cancel();
				heroB.getTimerTask().cancel();
				globalTimerTask.cancel();
				heroA.getTimer().cancel();
				heroB.getTimer().cancel();
				globalTimer.cancel();
				tablerow.setVisibility(View.VISIBLE);
				startButton.setText("����ѡ��");
				startButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(FightGroundActivity.this, SelectHeroActivity.class);
						Bundle bundle = new Bundle();
						bundle.putInt("hero1id", hero1id);
						bundle.putInt("hero2id", hero2id);
						intent.putExtras(bundle);
						startActivity(intent);
						finish();
					}
				});
			}
			if (msg.what == 0x125) {
				globalTime += (double) 0.1;
				fightTimerText.setText("ս��ʱ�䣺" + formatter.format(globalTime) + "s");
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fightground);
		tablerow = (TableRow) findViewById(R.id.fg_tablerow);
		scroll = (ScrollView) findViewById(R.id.fg_scorllview);
		startButton = (Button) findViewById(R.id.fg_bt_startfight);
		hero1image = (ImageView) findViewById(R.id.fg_hero1icon);
		hero2image = (ImageView) findViewById(R.id.fg_hero2icon);
		hero1name = (TextView) findViewById(R.id.fg_hero1name);
		hero1health = (TextView) findViewById(R.id.fg_hero1health);
		hero1damage = (TextView) findViewById(R.id.fg_hero1damage);
		hero1attactrate = (TextView) findViewById(R.id.fg_hero1attackrate);
		hero2name = (TextView) findViewById(R.id.fg_hero2name);
		hero2health = (TextView) findViewById(R.id.fg_hero2health);
		hero2damage = (TextView) findViewById(R.id.fg_hero2damage);
		hero2attactrate = (TextView) findViewById(R.id.fg_hero2attackrate);
		fightTimerText = (TextView) findViewById(R.id.fg_tv_fighttimer);
		fightInfo = (TextView) findViewById(R.id.fg_tv_fightinfo);
		// ����ʼ��ť��Ӽ���
		startButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// ����ʼ��ť��Ӽ���
				FightGroundActivity.this.startGame();
			}
		});
		// ��ȡ�������Ĳ���
		Intent intent = getIntent();
		hero1engname = intent.getStringExtra("hero1");
		hero2engname = intent.getStringExtra("hero2");
		hero1id = intent.getIntExtra("hero1id", -1);
		hero2id = intent.getIntExtra("hero2id", -1);
		// �ȳ�ʼ��Ӣ��ʵ��
		heroA = initHero(hero1engname);
		heroB = initHero(hero2engname);
		this.initTimersAndTasks();
		fightInfo.setText(heroA.getChname() + " VS " + heroB.getChname() + "!");
		// ��Ӧ����ĸ���
		initHeroIcon();
		hero1name.setText(heroA.getChname());
		hero1health.setText("����ֵ��" + String.valueOf(heroA.getHealth()));
		hero1damage.setText("��������" + String.valueOf(heroA.getDamage()));
		hero1attactrate.setText("����Ƶ�ʣ�" + String.valueOf((double) heroA.getAttackRate() / 1000) + "s");
		hero2name.setText(heroB.getChname());
		hero2health.setText("����ֵ��" + String.valueOf(heroB.getHealth()));
		hero2damage.setText("��������" + String.valueOf(heroB.getDamage()));
		hero2attactrate.setText("����Ƶ�ʣ�" + String.valueOf((double) heroB.getAttackRate() / 1000) + "s");
	}

	private void initHeroIcon() {
		switch (hero1id) {
		case 0:
			hero1image.setImageResource(R.drawable.bm_icon);
			break;
		case 1:
			hero1image.setImageResource(R.drawable.dh_icon);
			break;
		case 2:
			hero1image.setImageResource(R.drawable.mk_icon);
			break;
		case 3:
			hero1image.setImageResource(R.drawable.gd_icon);
			break;
		case 4:
			hero1image.setImageResource(R.drawable.mg_icon);
			break;
		}
		switch (hero2id) {
		case 0:
			hero2image.setImageResource(R.drawable.bm_icon);
			break;
		case 1:
			hero2image.setImageResource(R.drawable.dh_icon);
			break;
		case 2:
			hero2image.setImageResource(R.drawable.mk_icon);
			break;
		case 3:
			hero2image.setImageResource(R.drawable.gd_icon);
			break;
		case 4:
			hero2image.setImageResource(R.drawable.mg_icon);
			break;
		}
	}

	/**
	 * ��ʼ��timer��timertask
	 * 
	 * @author Zhang Peng 2014-6-21
	 */
	private void initTimersAndTasks() {
		timerA = new Timer();
		timerB = new Timer();
		globalTimer = new Timer();
		taskA = new TimerTask() {
			@Override
			public void run() {
				synchronized (lock) {
					heroA.attackMove();
					heroB.defenceMove();
					finalOutput(heroA, heroB);
				}
			}
		};
		taskB = new TimerTask() {
			@Override
			public void run() {
				synchronized (lock) {
					heroB.attackMove();
					heroA.defenceMove();
					finalOutput(heroB, heroA);
				}
			}
		};
		globalTimerTask = new TimerTask() {
			@Override
			public void run() {
				handler.sendEmptyMessage(0x125);
			}
		};
		heroA.setTimer(timerA);
		heroB.setTimer(timerB);
		heroA.setTimerTask(taskA);
		heroB.setTimerTask(taskB);
	}

	/**
	 * ������ʱ��
	 * 
	 * @author Zhang Peng 2014-6-21
	 */
	private void startTimers() {
		timerA.schedule(taskA, heroA.getAttackRate(), heroA.getAttackRate());
		timerB.schedule(taskB, heroB.getAttackRate(), heroB.getAttackRate());
		globalTimer.schedule(globalTimerTask, 0, 100);
	}

	/**
	 * ��ʼ��Ӣ��
	 * 
	 * @author Zhang Peng 2014-6-9
	 */
	private Hero initHero(String name) {
		Hero hero = null;
		if ("BM".equals(name)) {
			hero = new BMHero();
		} else if ("DH".equals(name)) {
			hero = new DHHero();
		} else if ("MK".equals(name)) {
			hero = new MKHero();
		} else if ("GD".equals(name)) {
			hero = new GDHero();
		} else if ("MG".equals(name)) {
			hero = new MGHero();
		}
		return hero;
	}

	/**
	 * ��ʼ��Ϸ
	 * 
	 * @author Zhang Peng 2014-6-9
	 */
	public void startGame() {
		tablerow.setVisibility(View.GONE);
		GameUtils.initGameUtils(this);
		heroA.setCompetitor(heroB);
		heroB.setCompetitor(heroA);
		heroA.initSkills();
		heroB.initSkills();
		fightInfo.setText("ս����ʼ����\n" + fightInfo.getText());
		date = new Date();
		this.startTimers();
	}

	public Object getLock() {
		return lock;
	}

	/**
	 * ���ս����ʵ�֣�ͬ������ֹ���ݳ���
	 * 
	 * @param damageOutput
	 * @param offHero
	 * @param defHero
	 * @author Zhang Peng 2014-6-21
	 */
	public synchronized void finalOutput(Hero offHero, Hero defHero) {
		// �ȼ�¼�½���ǰ����Ӣ�۵�����ֵ
		int healthA = heroA.getHealth();
		int healthB = heroB.getHealth();
		// �����˺�
		offHero.setHealth(offHero.getHealth() - defHero.getDamageOutput());
		defHero.setHealth(defHero.getHealth() - offHero.getDamageOutput());
		// ������˺��������
		offHero.setDamageOutput(0);
		defHero.setDamageOutput(0);
		// �����Ӧ���ı�
		StringBuilder str = new StringBuilder(offHero.getChname() + "����������");
		if (offHero.getOffFlag()) {
			str.append(offHero.getChname() + "������" + offHero.getOffSkill().getSkillname() + "����");
		} else {
			str.append(offHero.getChname() + "��ͨ������");
		}
		if (defHero.getDefFlag()) {
			str.append(defHero.getChname() + "������" + defHero.getDefSkill().getSkillname() + "����");
		} else {
			// str.append(defHero.getName() + "��ͨ����,");
		}
		str.append(heroA.getChname() + "��" + healthA + "-->" + heroA.getHealth() + "��");
		str.append(heroB.getChname() + "��" + healthB + "-->" + heroB.getHealth() + "��");
		String time = formatter.format((double) (new Date().getTime() - date.getTime()) / 1000);
		Message msg = new Message();
		msg.what = 0x123;
		Bundle data = new Bundle();
		data.putString("data", str.toString());
		data.putString("time", time);
		msg.setData(data);
		handler.sendMessage(msg);
		if (offHero.getHealth() <= 0) {
			String string = "��Ϸ������" + defHero.getChname() + "��ʤ��";
			msg = new Message();
			msg.what = 0x124;
			data = new Bundle();
			data.putString("data", string);
			msg.setData(data);
			handler.sendMessage(msg);
		}
		if (defHero.getHealth() <= 0) {
			String string = "��Ϸ������" + offHero.getChname() + "��ʤ��";
			msg = new Message();
			msg.what = 0x124;
			data = new Bundle();
			data.putString("data", string);
			msg.setData(data);
			handler.sendMessage(msg);
		}
	}
}