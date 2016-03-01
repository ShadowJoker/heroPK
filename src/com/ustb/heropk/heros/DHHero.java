/**
 * DHHero.java
 * Created by Zhang Peng at 2014-6-9
 * @Copyright (c) 2014 USTB All Rights Reserved.
 */
package com.ustb.heropk.heros;

import com.ustb.heropk.skills.DefDodge;
import com.ustb.heropk.skills.OffBloodSucking;

/**
 * 
 * @author Zhang Peng 2014-6-9
 */
public class DHHero extends Hero {
    public DHHero() {
        super();
        this.name = "DH";
        this.chname = "∂Òƒß¡‘»À";
        this.health = 600;
        this.healthLimit = 600;
        this.damage = 30;
        this.attackRate = 3000;
    }

    @Override
    public void initSkills() {
        this.setOffSkill(new OffBloodSucking(this, getCompetitor()));
        this.setDefSkill(new DefDodge(this, getCompetitor()));
    }
}
