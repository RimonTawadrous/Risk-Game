package Statistics;

import maps.Nodes;

public class Attack {
    private Nodes attacker,defender;
    private int numberOfAttackingSoliders=0;
    public Attack(Nodes attacker , Nodes defender,int numberOfAttackingSoliders){
        this.attacker = attacker;
        this.defender = defender;
        this.numberOfAttackingSoliders = numberOfAttackingSoliders;
    }
    public Nodes getAttacker() {
        return attacker;
    }

    public Nodes getDefender() {
        return defender;
    }
    public int getAttackerID() {
        return attacker.getId();
    }
    public int getDefenderID() {
        return defender.getId();
    }

    public int getNumberOfAttackingSoliders() {
        return numberOfAttackingSoliders;
    }
    public String toString() {
        return (new String("attacker ID = " + attacker.getId() +"  defender ID = "+defender.getId() +"  numberOfAttackingSoliders = "+numberOfAttackingSoliders));
    }
}
