/**
 * AbstractSkill.java
 * Created by Zhang Peng at 2014-6-9
 * @Copyright (c) 2014 USTB All Rights Reserved.
 */
package com.ustb.heropk.skills;

import com.ustb.heropk.heros.Hero;

/**
 * 所有技能的抽象类，技能的通用方法都在这里
 * 
 * @author Zhang Peng 2014-6-9
 */
public abstract class AbstractSkill {
    /** 技能名 */
    protected String skillname;
    /** 发动几率 */
    protected double activeRate;
    /** 技能发动者 */
    protected Hero activeHero;
    /** 技能目标者 */
    protected Hero targetHero;
    /** 技能是否发动的标识 */
    protected boolean activeFlag;

    public AbstractSkill(String skillname, double activeRate, Hero activeHero, Hero targetHero) {
        super();
        this.skillname = skillname;
        this.activeRate = activeRate;
        this.activeHero = activeHero;
        this.targetHero = targetHero;
    }

    /**
     * 获取该技能是否发动的方法
     * 
     * @author Zhang Peng 2014-6-9
     */
    public boolean isActive() {
        double random = Math.random();
        if (random > activeRate) {
            activeFlag = false;
        } else {
            activeFlag = true;
        }
        return activeFlag;
    }

    /**
     * 技能执行过程，此过程要注意设定好攻防双方的伤害输出量
     * 
     * @author Zhang Peng 2014-6-9
     */
    public abstract void excute();

    public String getSkillname() {
        return skillname;
    }

    public void setSkillname(String skillname) {
        this.skillname = skillname;
    }

    public double getActiveRate() {
        return activeRate;
    }

    public void setActiveRate(double activeRate) {
        this.activeRate = activeRate;
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

    /**
     * 获取此技能是否发动了
     * 
     * @return
     * @author Zhang Peng 2014-6-9
     */
    public boolean isOnActive() {
        return activeFlag;
    }

}
