package com.project.model;

import lombok.*;

/**
 * Class representing attack statistics.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AttackStatistics {

    private int min;               // Minimum attack power
    private int max;               // Maximum attack power
    private double average;        // Average attack power
    private double standardDeviation;   // Standard deviation of attack power

}
