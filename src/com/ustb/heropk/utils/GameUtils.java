/**
 * GameUtils.java
 * Created by Zhang Peng at 2014-6-22
 * @Copyright (c) 2014 USTB All Rights Reserved.
 */
package com.ustb.heropk.utils;

import java.util.TimerTask;

import com.ustb.heropk.heros.Hero;
import com.zhangpeng.heropk.FightGroundActivity;

/**
 * ��Ϸ���ƹ�����
 * 
 * @author Zhang Peng 2014-6-22
 */
public class GameUtils {
	private static GameUtils instance;
	private static FightGroundActivity activity;
	@SuppressWarnings("unused")
	private static GameConsole console;

	private GameUtils() {
	}

	/**
	 * ��ʼ����Ϸ���ƹ�����
	 * 
	 * @param console
	 * @author Zhang Peng 2014-6-22
	 */
	public static void initGameUtils(GameConsole console) {
		instance = new GameUtils();
		GameUtils.console = console;
	}

	/**
	 * ��ʼ����Ϸ���ƹ�����
	 * 
	 * @param activity
	 * @author Zhang Peng 2014-8-2
	 */
	public static void initGameUtils(FightGroundActivity activity) {
		instance = new GameUtils();
		GameUtils.activity = activity;
	}

	/**
	 * ��ȡ��Ϸ������ʵ��
	 * 
	 * @return
	 * @author Zhang Peng 2014-6-22
	 */
	public static GameUtils getInstance() {
		return instance;
	}

	/**
	 * ��ȡ�µ�TimerTaskʵ��
	 * 
	 * @param hero
	 * @return
	 * @author Zhang Peng 2014-6-22
	 */
	public static TimerTask getNewTask(Hero hero) {
		final Hero herof = hero;
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				synchronized (activity.getLock()) {
					herof.attackMove();
					herof.getCompetitor().defenceMove();
					activity.finalOutput(herof, herof.getCompetitor());
				}
			}
		};
		return task;
	}
}
