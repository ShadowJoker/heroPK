/**
 * DefHolyShield.java
 * Created by Zhang Peng at 2014-6-22
 * @Copyright (c) 2014 USTB All Rights Reserved.
 */
package com.ustb.heropk.skills;

import com.ustb.heropk.heros.Hero;

/**
 * 
 * @author Zhang Peng 2014-6-22
 */
public class DefHolyShield extends DefenceSkill {

    public DefHolyShield(Hero activeHero, Hero targetHero) {
        super("Ê¥¶Ü", 0.5, activeHero, targetHero);
    }

    @Override
    public void excute() {
        int damage = targetHero.getDamageOutput() - 20;
        targetHero.setDamageOutput(damage > 0 ? damage : 0);
    }

}
