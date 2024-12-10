package com.project.task;

import com.project.model.AttackStatistics;
import com.project.model.MythicalCreature;
import com.project.util.MythicalCreatureAttackCollector;

import java.util.List;

/**
 * Task 6: Calculates attack statistics from a list of mythical creatures.
 */
public class Task6 {

    /**
     * Calculates the attack statistics (min, max, average, standard deviation) for a list of creatures.
     *
     * @param mythicalCreatureList The list of mythical creatures
     * @return The attack statistics for the creatures
     */
    public static AttackStatistics task6(List<MythicalCreature> mythicalCreatureList) {
        return mythicalCreatureList.stream()
                .collect(new MythicalCreatureAttackCollector());
    }
}
