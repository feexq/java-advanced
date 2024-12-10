package com.project.util;

import com.project.model.AttackStatistics;
import com.project.model.MythicalCreature;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Collector implementation for calculating attack statistics (min, max, avg, std dev) for mythical creatures.
 * This custom collector collects the attack powers of mythical creatures, and calculates various statistics such as
 * minimum, maximum, average attack power, and standard deviation.
 */
public class MythicalCreatureAttackCollector implements Collector<MythicalCreature, List<Integer>, AttackStatistics> {

    /**
     * Provides the initial container (List<Integer>) for accumulating attack powers.
     * This will be used to store the attack powers of all the mythical creatures in the stream.
     *
     * @return A new ArrayList to accumulate attack powers.
     */
    @Override
    public Supplier<List<Integer>> supplier() {
        return ArrayList::new;
    }

    /**
     * Accumulates the attack power of each mythical creature into the provided List<Integer>.
     * The List will hold the attack powers of all creatures encountered so far in the stream.
     *
     * @return A BiConsumer that appends the attack power of a mythical creature to the list.
     */
    @Override
    public BiConsumer<List<Integer>, MythicalCreature> accumulator() {
        return ((accumulator, creature) -> {
            accumulator.add(creature.getAttackPower());
        }
        );
    }

    /**
     * Combines the results from two partial accumulations into one.
     * Used during parallel processing to merge the results from different threads.
     *
     * @return A BinaryOperator that combines two lists of attack powers into one.
     */
    @Override
    public BinaryOperator<List<Integer>> combiner() {
        return ((left, right) -> {
            List<Integer> result = new ArrayList<>(left);
            result.addAll(right);
            return result;
        });
    }

    /**
     * Finalizes the collection process and computes the attack statistics.
     * It calculates the minimum, maximum, average, and standard deviation of the attack powers.
     *
     * @return A Function that computes and returns the AttackStatistics object.
     */
    @Override
    public Function<List<Integer>, AttackStatistics> finisher() {
        return attackStatisticList -> {
            int min = attackStatisticList.stream().min(Integer::compare).orElseThrow(IllegalArgumentException::new);
            int max = attackStatisticList.stream().max(Integer::compare).orElseThrow(IllegalArgumentException::new);
            double avg = (attackStatisticList.stream().mapToDouble(Integer::doubleValue).average().orElseThrow(IllegalArgumentException::new));
            double standartDeviation = Math.sqrt(attackStatisticList.stream()
                    .mapToDouble(attack -> Math.pow(attack - avg, 2))
                    .average().orElseThrow(IllegalArgumentException::new));
            return new AttackStatistics(min, max, avg, standartDeviation);
        };
    }

    /**
     * Returns a set of characteristics that describe the behavior of this collector.
     * This collector can be used concurrently, meaning it supports parallel processing.
     *
     * @return A Set of Characteristics indicating the parallelizable nature of this collector.
     */
    @Override
    public Set<Characteristics> characteristics() {
        Set<Characteristics> characteristics = new HashSet<>();
        characteristics.add(Characteristics.CONCURRENT);
        return characteristics;
    }
}
