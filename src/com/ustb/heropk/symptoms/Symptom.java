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
 * ֢״�ĳ�����
 * 
 * @author Zhang Peng 2014-6-22
 */
public abstract class Symptom extends Thread {
    /** ֢״���� */
    protected String name;
    /** ֢״����ʱ�� */
    protected long lastTime;
    /** ֢״������ */
    protected Hero activeHero;
    /** ֢״Ŀ���� */
    protected Hero targetHero;
    /** ��ʱ������task */
    protected TimerTask task;
    /** ��֢״�ļ�ʱ����Ŀ�����ڶ�δ����� */
    protected int timerCount = 0;

    public Symptom(String name, long lastTime, Hero activeHero, Hero targetHero) {
        this.name = name;
        this.lastTime = lastTime;
        this.activeHero = activeHero;
        this.targetHero = targetHero;
    }

    /**
     * ��ʼ����ʱ������task
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
     * ����֢״��ʱ��
     * 
     * @author Zhang Peng 2014-6-22
     */
    public void setupTimer() {
        Timer timer = new Timer();
        this.setTimerCount(this.getTimerCount() + 1);
        timer.schedule(task, lastTime);
    }

    /**
     * ֢״����Ч����ʵ�ַ������ظ�ʩ�ų���ʱ�䲻���ӣ������ó���ʱ��
     * 
     * @author Zhang Peng 2014-6-22
     */
    abstract public void activeEffectByReset();

    /**
     * ���֢״�ķ���
     * 
     * @author Zhang Peng 2014-6-22
     */
    abstract public void removeEffect();

    public void run() {
        if (targetHero.getSymptoms().get(this.getName()) == null) { // û�и�֢״
            targetHero.getSymptoms().put(this.getName(), this);
            activeEffectByReset();
            initTask();
            setupTimer();
        } else { // ���и�֢״
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
