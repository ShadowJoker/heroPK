/**
 * BMHero.java
 * Created by Zhang Peng at 2014-6-9
 * @Copyright (c) 2014 USTB All Rights Reserved.
 */
package com.ustb.heropk.heros;

import com.ustb.heropk.skills.DefRebound;
import com.ustb.heropk.skills.OffJumpKill;

/**
 * 
 * @author Zhang Peng 2014-6-9
 */
public class BMHero extends Hero {
    public BMHero() {
        super();
        this.name= "BM";
        this.chname = "Ò°ÂùÈË";
        this.health = 650;
        this.healthLimit = 650;
        this.damage = 40;
        this.attackRate = 4500;
    }

    @Override
    public void initSkills() {
        this.setOffSkill(new OffJumpKill(this, getCompetitor()));
        this.setDefSkill(new DefRebound(this, getCompetitor()));
    }
}