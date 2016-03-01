/**
 * GameConsole.java
 * Created by Zhang Peng at 2014-6-9
 * @Copyright (c) 2014 USTB All Rights Reserved.
 */
package com.ustb.heropk.utils;

import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import com.ustb.heropk.heros.BMHero;
import com.ustb.heropk.heros.DHHero;
import com.ustb.heropk.heros.GDHero;
import com.ustb.heropk.heros.Hero;
import com.ustb.heropk.heros.MGHero;
import com.ustb.heropk.heros.MKHero;

/**
 * 
 * @author Zhang Peng 2014-6-9
 */
public class GameConsole {

    private Hero heroA;
    private Hero heroB;
    private Timer timerA;
    private Timer timerB;
    private TimerTask taskA;
    private TimerTask taskB;
    private Date date;
    final Object lock = new Object();

    public GameConsole(String str) {
        String names[] = str.split(",");
        heroA = initHero(names[0]);
        heroB = initHero(names[1]);
        this.initTimersAndTasks();
        if (heroA == null || heroB == null) {
            System.out.println("���벻��ȷ���������롣");
            System.exit(0);
        }
        System.out.println(heroA.getName() + " VS " + heroB.getName() + "!");
    }

    /**
     * ��ʼ��timer��timertask
     * 
     * @author Zhang Peng 2014-6-21
     */
    private void initTimersAndTasks() {
        timerA = new Timer();
        timerB = new Timer();
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
        heroA.setTimer(timerA);
        heroB.setTimer(timerB);
        heroA.setTimerTask(taskA);
        heroB.setTimerTask(taskB);
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
        StringBuilder str = new StringBuilder(offHero.getName() + "��������,");
        if (offHero.getOffFlag()) {
            str.append(offHero.getName() + "������" + offHero.getOffSkill().getSkillname() + "��,");
        } else {
            str.append(offHero.getName() + "��ͨ����,");
        }
        if (defHero.getDefFlag()) {
            str.append(defHero.getName() + "������" + defHero.getDefSkill().getSkillname() + "��,");
        } else {
            str.append(defHero.getName() + "��ͨ����,");
        }
        str.append(heroA.getName() + ":" + healthA + "-->" + heroA.getHealth() + ",");
        str.append(heroB.getName() + ":" + healthB + "-->" + heroB.getHealth() + "," + (new Date().getTime() - date.getTime()));
        System.out.println(str.toString());
        if (offHero.getHealth() <= 0) {
            System.out.println("��Ϸ������" + defHero.getName() + "��ʤ��");
            System.exit(0);
        }
        if (defHero.getHealth() <= 0) {
            System.out.println("��Ϸ������" + offHero.getName() + "��ʤ��");
            System.exit(0);
        }
    }

    /**
     * ������ʱ��
     * 
     * @author Zhang Peng 2014-6-21
     */
    private void startTimers() {
        timerA.schedule(taskA, heroA.getAttackRate(), heroA.getAttackRate());
        timerB.schedule(taskB, heroB.getAttackRate(), heroB.getAttackRate());
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
        } else {
            System.out.println("�������������ԡ�");
            System.exit(0);
        }
        return hero;
    }

    /**
     * ��ʼ��Ϸ
     * 
     * @author Zhang Peng 2014-6-9
     */
    public void startGame() {
        heroA.setCompetitor(heroB);
        heroB.setCompetitor(heroA);
        heroA.initSkills();
        heroB.initSkills();
        System.out.println("ս����ʼ������");
        date = new Date();
        this.startTimers();
    }

    public Object getLock() {
        return lock;
    }

    /**
     * ������
     * 
     * @param args
     * @author Zhang Peng 2014-6-9
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        GameConsole console = new GameConsole(str);
        GameUtils.initGameUtils(console);
        console.startGame();
    }

}
