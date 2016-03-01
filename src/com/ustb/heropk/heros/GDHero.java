/**
 * GDHero.java
 * Created by Zhang Peng at 2014-6-22
 * @Copyright (c) 2014 USTB All Rights Reserved.
 */
package com.ustb.heropk.heros;

import com.ustb.heropk.skills.DefHolyShield;
import com.ustb.heropk.skills.OffPunish;

/**
 * 
 * @author Zhang Peng 2014-6-22
 */
public class GDHero extends Hero {

    public GDHero() {
        super();
        this.name= "GD";
        this.chname = " •∆Ô ø";
        this.health = 750;
        this.healthLimit = 750;
        this.damage = 40;
        this.attackRate = 5000;
    }

    @Override
    public void initSkills() {
        this.setOffSkill(new OffPunish(this, getCompetitor()));
        this.setDefSkill(new DefHolyShield(this, getCompetitor()));
    }

}
