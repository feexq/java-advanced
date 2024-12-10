package com.project.model;

import com.project.common.CreatureType;
import lombok.*;

import java.time.LocalDate;

/**
 * Class representing a mythical creature with its attributes.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MythicalCreature {
    private String name;           // Name of the mythical creature
    private CreatureType type;    // Type of the mythical creature
    private LocalDate firstMention; // The first date the creature was mentioned
    private int attackPower;      // The attack power of the creature
}
