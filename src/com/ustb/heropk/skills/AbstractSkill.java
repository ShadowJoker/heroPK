/**
 * AbstractSkill.java
 * Created by Zhang Peng at 2014-6-9
 * @Copyright (c) 2014 USTB All Rights Reserved.
 */
package com.ustb.heropk.skills;

import com.ustb.heropk.heros.Hero;

/**
 * ���м��ܵĳ����࣬���ܵ�ͨ�÷�����������
 * 
 * @author Zhang Peng 2014-6-9
 */
public abstract class AbstractSkill {
    /** ������ */
    protected String skillname;
    /** �������� */
    protected double activeRate;
    /** ���ܷ����� */
    protected Hero activeHero;
    /** ����Ŀ���� */
    protected Hero targetHero;
    /** �����Ƿ񷢶��ı�ʶ */
    protected boolean activeFlag;

    public AbstractSkill(String skillname, double activeRate, Hero activeHero, Hero targetHero) {
        super();
        this.skillname = skillname;
        this.activeRate = activeRate;
        this.activeHero = activeHero;
        this.targetHero = targetHero;
    }

    /**
     * ��ȡ�ü����Ƿ񷢶��ķ���
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
     * ����ִ�й��̣��˹���Ҫע���趨�ù���˫�����˺������
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
     * ��ȡ�˼����Ƿ񷢶���
     * 
     * @return
     * @author Zhang Peng 2014-6-9
     */
    public boolean isOnActive() {
        return activeFlag;
    }

}
