/**
 * Symptom.java
 * Created by Zhang Peng at 2014-6-22
 * @Copyright (c) 2014 USTB All Rights Reserved.
 */
package com.ustb.heropk.symptoms;

import java.util.Timer;
import java.util.TimerTask;

import com.ustb.heropk.heros.Hero;

/**
 * 症状的抽象父类
 * 
 * @author Zhang Peng 2014-6-22
 */
public abstract class Symptom extends Thread {
    /** 症状名称 */
    protected String name;
    /** 症状持续时间 */
    protected long lastTime;
    /** 症状发起者 */
    protected Hero activeHero;
    /** 症状目标者 */
    protected Hero targetHero;
    /** 计时器任务task */
    protected TimerTask task;
    /** 该症状的计时器数目，用于多次处理触发 */
    protected int timerCount = 0;

    public Symptom(String name, long lastTime, Hero activeHero, Hero targetHero) {
        this.name = name;
        this.lastTime = lastTime;
        this.activeHero = activeHero;
        this.targetHero = targetHero;
    }

    /**
     * 初始化计时器任务task
     * 
     * @author Zhang Peng 2014-6-22
     */
    public void initTask() {
        task = new TimerTask() {
            @Override
            public void run() {
                setTimerCount(getTimerCount() - 1);
                if (Symptom.this.getTimerCount() == 0) {
                    removeEffect();
                    targetHero.getSymptoms().remove(getName());
                }
            }
        };
    }

    /**
     * 配置症状计时器
     * 
     * @author Zhang Peng 2014-6-22
     */
    public void setupTimer() {
        Timer timer = new Timer();
        this.setTimerCount(this.getTimerCount() + 1);
        timer.schedule(task, lastTime);
    }

    /**
     * 症状产生效果的实现方法，重复施放持续时间不叠加，但重置持续时间
     * 
     * @author Zhang Peng 2014-6-22
     */
    abstract public void activeEffectByReset();

    /**
     * 解除症状的方法
     * 
     * @author Zhang Peng 2014-6-22
     */
    abstract public void removeEffect();

    public void run() {
        if (targetHero.getSymptoms().get(this.getName()) == null) { // 没有该症状
            targetHero.getSymptoms().put(this.getName(), this);
            activeEffectByReset();
            initTask();
            setupTimer();
        } else { // 已有该症状
            initTask();
            setupTimer();
        }
    }

    public String getSymptomName() {
        return name;
    }

    public void setSymptomName(String name) {
        this.name = name;
    }

    public long getLastTime() {
        return lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }

    public Hero getActiveHero() {
        return activeHero;
    }

    public void setActiveHero(Hero activeHero) {
        this.activeHero = activeHero;
    }

    public Hero getTargetHero() {
        return targetHero;
    }

    public void setTargetHero(Hero targetHero) {
        this.targetHero = targetHero;
    }

    public int getTimerCount() {
        return timerCount;
    }

    public void setTimerCount(int timerCount) {
        this.timerCount = timerCount;
    }

}
