/**
 * MGHero.java
 * Created by Zhang Peng at 2014-6-22
 * @Copyright (c) 2014 USTB All Rights Reserved.
 */
package com.ustb.heropk.heros;

import com.ustb.heropk.skills.DefIceShield;
import com.ustb.heropk.skills.OffFireBall;

/**
 * 元素法师
 * 
 * @author Zhang Peng 2014-6-22
 */
public class MGHero extends Hero {

    public MGHero() {
        super();
        this.name = "MG";
        this.chname = "魔法师";
        this.health = 500;
        this.healthLimit = 500;
        this.damage = 20;
        this.attackRate = 2500;
    }

    @Override
    public void initSkills() {
        this.setOffSkill(new OffFireBall(this, getCompetitor()));
        this.setDefSkill(new DefIceShield(this, getCompetitor()));
    }

}
