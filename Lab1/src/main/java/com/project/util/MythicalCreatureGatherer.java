package com.project.util;

import com.project.common.CreatureType;
import com.project.model.MythicalCreature;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Gatherer;

/**
 * Custom gatherer that processes mythical creatures, skipping and limiting the list.
 * The gatherer allows filtering out creatures of a specific type and limiting the number of creatures collected.
 */
public class MythicalCreatureGatherer implements Gatherer<MythicalCreature, List<MythicalCreature>,  MythicalCreature> {
    private final CreatureType typeToSkip;
    private int skipCount;
    private final int limit;

    /**
     * Constructor to create a MythicalCreatureGatherer object with parameters for filtering and limiting.
     *
     * @param typeToSkip The type of creatures to skip during collection. If a creature's type matches
     *                   this parameter, it will be skipped (if there are still skips remaining).
     * @param skipCount The number of creatures to skip (based on typeToSkip). This count will decrease
     *                  as creatures of the specified type are skipped.
     * @param limit The maximum number of creatures to be included in the result. The collection stops
     *              once this limit is reached, even if there are more creatures that meet the conditions.
     */
    public MythicalCreatureGatherer(CreatureType typeToSkip, int skipCount, int limit) {
        this.typeToSkip = typeToSkip;
        this.skipCount = skipCount;
        this.limit = limit;
    }

    /**
     * Initializes the accumulator (a List of mythical creatures) to store the creatures that pass the filtering criteria.
     *
     * @return A Supplier that returns a new list to accumulate the filtered creatures.
     */
    @Override
    public Supplier<List<MythicalCreature>> initializer() {
        return () -> new ArrayList<>(limit);
    }

    /**
     * Integrates each mythical creature into the accumulator list, based on the filtering criteria:
     * - Skip creatures of the specified type (if skipCount > 0).
     * - Limit the number of creatures collected (if limit is reached).
     *
     * @return An Integrator that adds creatures to the list based on the conditions.
     */
    @Override
    public Integrator<List<MythicalCreature>, MythicalCreature, MythicalCreature> integrator() {
        return Integrator.of((creatures, creature, downstream) -> {
            // If the limit is reached, do not add more creatures
            if(creatures.size() >= limit) {
                return false;
            }

            // If the creature type matches the one to skip and we still have skipCount remaining, skip the creature
            if(creature.getType().equals(typeToSkip) && skipCount > 0) {
                skipCount--;    // Decrement the skip count
                return true;
            }

            // Add the creature to the list
            creatures.add(creature);
            return true;
        });
    }

    /**
     * Finalizes the collection by pushing all gathered creatures to the downstream processor.
     * This method is called once the collection process is complete.
     *
     * @return A BiConsumer that passes the list of collected creatures to the downstream processor.
     */
    @Override
    public BiConsumer<List<MythicalCreature>, Downstream<? super MythicalCreature>> finisher() {
        return (creatures, downstream) -> {
            // If there are any creatures left and the downstream processor is not rejecting, push the creatures
            if(!downstream.isRejecting() && !creatures.isEmpty()) {
                creatures.forEach(downstream::push);
                creatures.clear(); // Clear the list after processing
            }
        };
    }
}
