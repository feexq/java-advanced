package com.project.task;

import com.project.model.MythicalCreature;
import com.project.util.MythicalCreaturesGenerator;

import java.util.stream.Stream;

/**
 * Task 3: Generates a stream of mythical creatures.
 */
public class Task3 {

    /**
     * Generates and returns a stream of mythical creatures.
     *
     * @return Stream of mythical creatures
     */
    public static Stream<MythicalCreature> task3() {
        return MythicalCreaturesGenerator.generateMythicalCreatures();
    }
}
