/**
 * SymptomInterrupt.java
 * Created by Zhang Peng at 2014-6-22
 * @Copyright (c) 2014 USTB All Rights Reserved.
 */
package com.ustb.heropk.symptoms;

import java.util.Timer;
import java.util.TimerTask;

import com.ustb.heropk.heros.Hero;
import com.ustb.heropk.utils.GameUtils;

/**
 * 打断效果的实现类，将目标停顿1秒并重置攻击计时器
 * 
 * @author Zhang Peng 2014-6-22
 */
public class SymptomInterrupt extends Symptom {

    public SymptomInterrupt(Hero activeHero, Hero targetHero) {
        super("打断", 0, activeHero, targetHero);
    }

    @Override
    public void activeEffectByReset() {
        targetHero.getTimerTask().cancel();
        targetHero.getTimer().cancel();
    }

    @Override
    public void removeEffect() {
        Timer timer = new Timer();
        TimerTask task = GameUtils.getNewTask(targetHero);
        targetHero.setTimer(timer);
        timer.schedule(task, targetHero.getAttackRate() + 2000, targetHero.getAttackRate());
    }

}
