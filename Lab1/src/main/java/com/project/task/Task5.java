package com.project.task;

import com.project.common.CreatureType;
import com.project.model.MythicalCreature;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Task 5: Groups mythical creatures by type, filtering by minimum years from first mention.
 */
public class Task5 {

    /**
     * Groups mythical creatures by their type, ensuring they meet the minimum years from first mention.
     *
     * @param mythicalCreatureList The list of mythical creatures
     * @param minYearsFromFirstMention The minimum number of years since the first mention
     * @return A map of creature types and their respective creatures
     */
    public static Map<CreatureType, List<MythicalCreature>> task5(List<MythicalCreature> mythicalCreatureList, int minYearsFromFirstMention) {
        return mythicalCreatureList.stream()
                .filter(creature -> {
                    Period period = Period.between(creature.getFirstMention(), LocalDate.now());
                    int yearsFromFirstMention = period.getYears();
                    return yearsFromFirstMention >= minYearsFromFirstMention;
                })
                .collect(Collectors.groupingBy(MythicalCreature::getType));
    }
}
