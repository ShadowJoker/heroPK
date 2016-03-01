/**
 * OffFireBall.java
 * Created by Zhang Peng at 2014-6-22
 * @Copyright (c) 2014 USTB All Rights Reserved.
 */
package com.ustb.heropk.skills;

import com.ustb.heropk.heros.Hero;

/**
 * »ðÇò¼¼ÄÜ
 * 
 * @author Zhang Peng 2014-6-22
 */
public class OffFireBall extends OffenceSkill {

    public OffFireBall(Hero activeHero, Hero targetHero) {
        super("»ðÇò", 0.6, activeHero, targetHero);
    }

    @Override
    public void excute() {
        activeHero.setDamageOutput(activeHero.getDamage() + 30);
    }

}
