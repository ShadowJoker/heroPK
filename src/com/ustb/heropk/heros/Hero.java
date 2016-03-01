/**
 * AbstractHero.java
 * Created by Zhang Peng at 2014-6-9
 * @Copyright (c) 2014 USTB All Rights Reserved.
 */
package com.ustb.heropk.heros;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import com.ustb.heropk.skills.DefenceSkill;
import com.ustb.heropk.skills.OffenceSkill;
import com.ustb.heropk.symptoms.Symptom;

/**
 * 
 * @author Zhang Peng 2014-6-9
 */
public abstract class Hero {
    /** Ӣ������ */
    protected String name;
    /** Ӣ��������*/
    protected String chname;
    /** ����ֵ */
    protected int health;
    /** ����ֵ���� */
    protected int healthLimit;
    /** ������ */
    protected int damage;
    /** �������� */
    protected OffenceSkill offSkill;
    /** �������� */
    protected DefenceSkill defSkill;
    /** ����Ƶ�� */
    protected long attackRate;
    /** �Է�Ӣ�� */
    protected Hero competitor;
    /** �Ƿ����� */
    protected boolean isAlive = true;
    /** �Ƿ񷢶��˹������ܵı�ʶ */
    protected boolean offFlag = false;
    /** �Ƿ񷢶��˷������ܵı�ʶ */
    protected boolean defFlag = false;
    /** ��¼���غ���������˶����˺� */
    protected int damageOutput;
    /** ��Ӣ��ʹ�õļ�ʱ�� */
    protected Timer timer;
    /** ��Ӣ��ʹ�õļ�ʱ������ */
    protected TimerTask timerTask;
    /** Ӣ�����ϵ�֢״���� */
    protected HashMap<String,Symptom> symptoms = new HashMap<String,Symptom>();

    /**
     * ������������
     * 
     * @author Zhang Peng 2014-6-9
     */
    public void attackMove() {
        if (offSkill.isActive()) {
            offSkill.excute();
            offFlag = true;
        } else {
            this.normalAttack();
            offFlag = false;
        }
    }

    /**
     * ��ͨ����ִ��
     * 
     * @author Zhang Peng 2014-6-9
     */
    private void normalAttack() {
        damageOutput = this.getDamage();
    }

    /**
     * ������������
     * 
     * @author Zhang Peng 2014-6-9
     */
    public void defenceMove() {
        if (defSkill.isActive()) {
            defSkill.excute();
            defFlag = true;
        } else {
            defFlag = false;
        }
    }

    /**
     * ��ʼ�����ܵķ�����ȷ����Ӣ�۶����������˭֮���ٵ���
     * 
     * @author Zhang Peng 2014-6-9
     */
    public abstract void initSkills();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public OffenceSkill getOffSkill() {
        return offSkill;
    }

    public void setOffSkill(OffenceSkill offSkill) {
        this.offSkill = offSkill;
    }

    public DefenceSkill getDefSkill() {
        return defSkill;
    }

    public void setDefSkill(DefenceSkill defSkill) {
        this.defSkill = defSkill;
    }

    public long getAttackRate() {
        return attackRate;
    }

    public void setAttackRate(long attackRate) {
        this.attackRate = attackRate;
    }

    public Hero getCompetitor() {
        return competitor;
    }

    public void setCompetitor(Hero competitor) {
        this.competitor = competitor;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean getOffFlag() {
        return offFlag;
    }

    public boolean getDefFlag() {
        return defFlag;
    }

    public int getDamageOutput() {
        return damageOutput;
    }

    public void setDamageOutput(int damageOutput) {
        this.damageOutput = damageOutput;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public int getHealthLimit() {
        return healthLimit;
    }

    public void setHealthLimit(int healthLimit) {
        this.healthLimit = healthLimit;
    }

    public TimerTask getTimerTask() {
        return timerTask;
    }

    public void setTimerTask(TimerTask timerTask) {
        this.timerTask = timerTask;
    }

    public HashMap<String, Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(HashMap<String, Symptom> symptoms) {
        this.symptoms = symptoms;
    }

	public String getChname() {
		return chname;
	}

	public void setChname(String chname) {
		this.chname = chname;
	}

}
