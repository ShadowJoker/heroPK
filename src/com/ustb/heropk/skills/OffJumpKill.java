/**
 * OffJumpKill.java
 * Created by Zhang Peng at 2014-6-22
 * @Copyright (c) 2014 USTB All Rights Reserved.
 */
package com.ustb.heropk.skills;

import com.ustb.heropk.heros.Hero;

/**
 * ÌøÅü¼¼ÄÜ
 * 
 * @author Zhang Peng 2014-6-22
 */
public class OffJumpKill extends OffenceSkill {

    public OffJumpKill(Hero activeHero, Hero targetHero) {
        super("ÌøÅü", 0.3, activeHero, targetHero);
    }

    @Override
    public void excute() {
        activeHero.setDamageOutput(activeHero.getDamage() * 2);
    }

}
