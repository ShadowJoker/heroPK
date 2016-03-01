/**
 * SymptomDizziness.java
 * Created by Zhang Peng at 2014-6-22
 * @Copyright (c) 2014 USTB All Rights Reserved.
 */
package com.ustb.heropk.symptoms;

import java.util.Timer;
import java.util.TimerTask;

import com.ustb.heropk.heros.Hero;
import com.ustb.heropk.utils.GameUtils;

/**
 * ѣ�ε�ʵ���࣬ѣ�ι�����Ŀ�겻�ܲ����κζ��������Ҳ��ܷ����������ܡ�ѣ��ʱ�䲻���ӣ��ظ�ѣ��ʱ����ѣ��ʱ�䡣
 * 
 * @author Zhang Peng 2014-6-22
 */
public class SymptomDizziness extends Symptom {

    /** ��¼��������ԭ�����ʵı��� */
    private double activeRate;

    public SymptomDizziness(long lastTime, Hero activeHero, Hero targetHero) {
        super("ѣ��", lastTime, activeHero, targetHero);
    }

    @Override
    public void activeEffectByReset() {
        activeRate = targetHero.getDefSkill().getActiveRate();
        targetHero.getDefSkill().setActiveRate(0);
        targetHero.getTimerTask().cancel();
        targetHero.getTimer().cancel();
    }

    @Override
    public void removeEffect() {
        targetHero.getDefSkill().setActiveRate(activeRate);
        Timer timer = new Timer();
        TimerTask task = GameUtils.getNewTask(targetHero);
        targetHero.setTimer(timer);
        timer.schedule(task, targetHero.getAttackRate(), targetHero.getAttackRate());
    }

}
