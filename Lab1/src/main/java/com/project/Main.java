package com.project;

import com.project.common.CreatureType;
import com.project.model.AttackStatistics;
import com.project.model.MythicalCreature;
import com.project.task.*;

import java.util.List;
import java.util.Map;

/**
 * Main class for executing the mythical creatures analysis tasks.
 */
public class Main {
    public static void main(String[] args) {
        // Task 3: Generate and print random mythical creatures
        Task3.task3().limit(10).forEach(System.out::println);

        // Task 4: Filter and limit creatures
        List<MythicalCreature> filteredCreatures = Task4.task4(Task3.task3(), CreatureType.VODYANYK, 10, 500);
        System.out.println("Filtered Creatures: " + filteredCreatures.size());

        // Task 5: Group creatures by type and filter by years since first mention
        Map<CreatureType, List<MythicalCreature>> groupedCreatures = Task5.task5(
                filteredCreatures, 50);
        System.out.println("Grouped Creatures: ");
        for (Map.Entry<CreatureType, List<MythicalCreature>> entry : groupedCreatures.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().size());
        }

        // Task 6: Calculate attack statistics
        AttackStatistics stats = Task6.task6(filteredCreatures);
        System.out.println("Attack Statistics: " + stats);

        // Task 7: Analyze attack power and identify outliers
        Map<String, Integer> analysisResults = Task7.analyzeAttackPower(filteredCreatures);
        System.out.println("Analysis Results: " + analysisResults);
    }

}