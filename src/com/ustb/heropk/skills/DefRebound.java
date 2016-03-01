/**
 * DefRebound.java
 * Created by Zhang Peng at 2014-6-22
 * @Copyright (c) 2014 USTB All Rights Reserved.
 */
package com.ustb.heropk.skills;

import com.ustb.heropk.heros.Hero;

/**
 * 反弹技能
 * 
 * @author Zhang Peng 2014-6-22
 */
public class DefRebound extends DefenceSkill {

    public DefRebound(Hero activeHero, Hero targetHero) {
        super("反弹", 0.3, activeHero, targetHero);
    }

    @Override
    public void excute() {
        activeHero.setDamageOutput(activeHero.getDamageOutput() + targetHero.getDamageOutput());
    }

}
