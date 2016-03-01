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
    /** 英雄名字 */
    protected String name;
    /** 英雄中文名*/
    protected String chname;
    /** 生命值 */
    protected int health;
    /** 生命值上限 */
    protected int healthLimit;
    /** 攻击力 */
    protected int damage;
    /** 攻击技能 */
    protected OffenceSkill offSkill;
    /** 防御技能 */
    protected DefenceSkill defSkill;
    /** 攻击频率 */
    protected long attackRate;
    /** 对方英雄 */
    protected Hero competitor;
    /** 是否阵亡 */
    protected boolean isAlive = true;
    /** 是否发动了攻击技能的标识 */
    protected boolean offFlag = false;
    /** 是否发动了防御技能的标识 */
    protected boolean defFlag = false;
    /** 记录本回合最终输出了多少伤害 */
    protected int damageOutput;
    /** 本英雄使用的计时器 */
    protected Timer timer;
    /** 本英雄使用的计时器任务 */
    protected TimerTask timerTask;
    /** 英雄身上的症状集合 */
    protected HashMap<String,Symptom> symptoms = new HashMap<String,Symptom>();

    /**
     * 发动攻击动作
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
     * 普通攻击执行
     * 
     * @author Zhang Peng 2014-6-9
     */
    private void normalAttack() {
        damageOutput = this.getDamage();
    }

    /**
     * 发动防御动作
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
     * 初始化技能的方法，确保在英雄定义完对手是谁之后再调用
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
