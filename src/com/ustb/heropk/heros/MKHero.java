/**
 * MKHero.java
 * Created by Zhang Peng at 2014-6-9
 * @Copyright (c) 2014 USTB All Rights Reserved.
 */
package com.ustb.heropk.heros;

import com.ustb.heropk.skills.DefGodArmor;
import com.ustb.heropk.skills.OffHeavyHit;

/**
 * 
 * @author Zhang Peng 2014-6-9
 */
public class MKHero extends Hero {
    public MKHero() {
        super();
        this.name = "MK";
        this.chname = "Œ‰…Æ";
        this.health = 700;
        this.healthLimit = 700;
        this.damage = 50;
        this.attackRate = 6000;
    }

    @Override
    public void initSkills() {
        this.setOffSkill(new OffHeavyHit(this, getCompetitor()));
        this.setDefSkill(new DefGodArmor(this, getCompetitor()));
    }
}
