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
            System.out.println("输入不正确请重新输入。");
            System.exit(0);
        }
        System.out.println(heroA.getName() + " VS " + heroB.getName() + "!");
    }

    /**
     * 初始化timer和timertask
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
     * 最终结算的实现，同步化防止数据出错
     * 
     * @param damageOutput
     * @param offHero
     * @param defHero
     * @author Zhang Peng 2014-6-21
     */
    public synchronized void finalOutput(Hero offHero, Hero defHero) {
        // 先记录下结算前两个英雄的生命值
        int healthA = heroA.getHealth();
        int healthB = heroB.getHealth();
        // 结算伤害
        offHero.setHealth(offHero.getHealth() - defHero.getDamageOutput());
        defHero.setHealth(defHero.getHealth() - offHero.getDamageOutput());
        // 结算后伤害输出重置
        offHero.setDamageOutput(0);
        defHero.setDamageOutput(0);
        // 输出对应的文本
        StringBuilder str = new StringBuilder(offHero.getName() + "发动进攻,");
        if (offHero.getOffFlag()) {
            str.append(offHero.getName() + "发动【" + offHero.getOffSkill().getSkillname() + "】,");
        } else {
            str.append(offHero.getName() + "普通攻击,");
        }
        if (defHero.getDefFlag()) {
            str.append(defHero.getName() + "发动【" + defHero.getDefSkill().getSkillname() + "】,");
        } else {
            str.append(defHero.getName() + "普通防御,");
        }
        str.append(heroA.getName() + ":" + healthA + "-->" + heroA.getHealth() + ",");
        str.append(heroB.getName() + ":" + healthB + "-->" + heroB.getHealth() + "," + (new Date().getTime() - date.getTime()));
        System.out.println(str.toString());
        if (offHero.getHealth() <= 0) {
            System.out.println("游戏结束！" + defHero.getName() + "获胜！");
            System.exit(0);
        }
        if (defHero.getHealth() <= 0) {
            System.out.println("游戏结束！" + offHero.getName() + "获胜！");
            System.exit(0);
        }
    }

    /**
     * 启动计时器
     * 
     * @author Zhang Peng 2014-6-21
     */
    private void startTimers() {
        timerA.schedule(taskA, heroA.getAttackRate(), heroA.getAttackRate());
        timerB.schedule(taskB, heroB.getAttackRate(), heroB.getAttackRate());
    }

    /**
     * 初始化英雄
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
            System.out.println("输入有误，请重试。");
            System.exit(0);
        }
        return hero;
    }

    /**
     * 开始游戏
     * 
     * @author Zhang Peng 2014-6-9
     */
    public void startGame() {
        heroA.setCompetitor(heroB);
        heroB.setCompetitor(heroA);
        heroA.initSkills();
        heroB.initSkills();
        System.out.println("战斗开始！！！");
        date = new Date();
        this.startTimers();
    }

    public Object getLock() {
        return lock;
    }

    /**
     * 主方法
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
