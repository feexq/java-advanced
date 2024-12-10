package com.project.task;

import com.project.model.MythicalCreature;

import java.util.List;
import java.util.Map;


/**
 * Task 7: Analyzes attack power, identifying outliers based on interquartile range.
 */
public class Task7 {

    /**
     * Analyzes the attack power of mythical creatures, calculating outliers based on the IQR.
     *
     * @param mythicalCreatureList The list of mythical creatures
     * @return A map containing the count of data points and outliers
     */
    public static Map<String, Integer> analyzeAttackPower(List<MythicalCreature> mythicalCreatureList) {
        List<Integer> sortedPowers = mythicalCreatureList.stream()
                .map(MythicalCreature::getAttackPower)
                .sorted()
                .toList();

        int q1Index = sortedPowers.size() / 4;
        int q3Index = sortedPowers.size() / 4 * 3;

        int q1 = sortedPowers.get(q1Index);
        int q3 = sortedPowers.get(q3Index);


        int iqr = q3 - q1;

        double lowerBound = q1 - (1.5 * iqr);
        double upperBound = q3 + (1.5 * iqr);

        List<Integer> data = mythicalCreatureList.stream()
                .map(MythicalCreature::getAttackPower)
                .filter(p -> p >= lowerBound && p <= upperBound)
                .toList();

        List<Integer> outliers = mythicalCreatureList.stream()
                .map(MythicalCreature::getAttackPower)
                .filter(p -> p < lowerBound || p > upperBound)
                .toList();

        return Map.of(
                "data", data.size(),
                "outliers", outliers.size()
        );
    }
}
