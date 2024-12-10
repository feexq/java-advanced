package com.project.model;

import com.project.common.CreatureType;

import java.time.LocalDate;

/**
 * Class representing a mythical creature with its attributes.
 */
public class MythicalCreature {

    private String name;           // Name of the mythical creature
    private CreatureType type;    // Type of the mythical creature
    private LocalDate firstMention; // The first date the creature was mentioned
    private int attackPower;      // The attack power of the creature

    public MythicalCreature(String name, CreatureType type, LocalDate firstMention, int attackPower) {
        this.name = name;
        this.attackPower = attackPower;
        this.firstMention = firstMention;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CreatureType getType() {
        return type;
    }

    public void setType(CreatureType type) {
        this.type = type;
    }

    public LocalDate getFirstMention() {
        return firstMention;
    }

    public void setFirstMention(LocalDate firstMention) {
        this.firstMention = firstMention;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    @Override
    public String toString() {
        return "MythicalCreature{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", firstMention=" + firstMention +
                ", attackPower=" + attackPower +
                '}';
    }

}
