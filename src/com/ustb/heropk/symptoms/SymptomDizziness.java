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
 * 眩晕的实现类，眩晕过程中目标不能产生任何动作，而且不能发动防御技能。眩晕时间不叠加，重复眩晕时重置眩晕时间。
 * 
 * @author Zhang Peng 2014-6-22
 */
public class SymptomDizziness extends Symptom {

    /** 记录防御技能原触发率的变量 */
    private double activeRate;

    public SymptomDizziness(long lastTime, Hero activeHero, Hero targetHero) {
        super("眩晕", lastTime, activeHero, targetHero);
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
