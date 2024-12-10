package com.project.task;

import com.project.common.CreatureType;
import com.project.model.MythicalCreature;
import com.project.util.MythicalCreatureGatherer;

import java.util.List;
import java.util.stream.Stream;

/**
 * Task 4: Processes a stream of mythical creatures with filtering and limiting operations.
 */
public class Task4 {

    /**
     * Filters and limits the list of mythical creatures based on provided conditions.
     *
     * @param stream The stream of mythical creatures
     * @param typeToSkip The creature type to skip
     * @param skip The number of creatures to skip
     * @param limit The maximum number of creatures to include
     * @return A list of filtered mythical creatures
     */
    public static List<MythicalCreature> task4(Stream<MythicalCreature> stream, CreatureType typeToSkip, int skip, int limit) {
        return stream
                .gather(new MythicalCreatureGatherer(typeToSkip, skip,limit))
                .toList();
    }
}
