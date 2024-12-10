package com.project.util;

import com.project.common.CreatureType;
import com.project.model.MythicalCreature;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Utility class for generating random mythical creatures.
 */
public class MythicalCreaturesGenerator {

    private static final List<String> UPYR_NAMES = List.of("Кривавий", "Темний", "Нічний", "Старий");
    private static final List<String> MAVKA_NAMES = List.of("Лісова", "Зелена", "Тіньова", "Весняна");
    private static final List<String> DOMOVYK_NAMES = List.of("Хатній", "Родинний", "Старий");
    private static final List<String> VODYANYK_NAMES = List.of("Водяний", "Річковий", "Глибинний");
    private static final List<String> POLIOVYK_NAMES = List.of("Польовий", "Степовий", "Жнивний");
    private static final List<String> LISOVYK_NAMES = List.of("Лісовий", "Темний", "Мисливський");

    private static final Random random = new Random();

    /**
     * Generates a random name for a given creature type.
     *
     * @param type The type of creature
     * @return A randomly generated name for the creature
     */
    private static String generateName(CreatureType type) {
        return switch (type) {
            case UPYR -> UPYR_NAMES.get(random.nextInt(UPYR_NAMES.size()));
            case MAVKA -> MAVKA_NAMES.get(random.nextInt(MAVKA_NAMES.size()));
            case DOMOVYK -> DOMOVYK_NAMES.get(random.nextInt(DOMOVYK_NAMES.size()));
            case VODYANYK -> VODYANYK_NAMES.get(random.nextInt(VODYANYK_NAMES.size()));
            case POLIOVYK -> POLIOVYK_NAMES.get(random.nextInt(POLIOVYK_NAMES.size()));
            case LISOVYK -> LISOVYK_NAMES.get(random.nextInt(LISOVYK_NAMES.size()));
        };
    }

    /**
     * Generates a random date for the first mention of a mythical creature.
     *
     * @return A randomly generated date
     */
    private static LocalDate generateFirstMentionDate() {
        int year = random.nextInt(LocalDate.now().getYear() - 1500 + 1) + 1500;
        return LocalDate.of(year, random.nextInt(12) + 1, random.nextInt(28) + 1);
    }

    /**
     * Generates a random attack power for a mythical creature.
     *
     * @return A randomly generated attack power
     */
    private static int generateAttackPower() {
        return 20_000 + random.nextInt(30_000);
    }

    /**
     * Generates a random creature type.
     *
     * @return A randomly generated creature type
     */
    private static CreatureType generateCreatureType() {
        return CreatureType.values()[random.nextInt(CreatureType.values().length)];
    }

    /**
     * Generates a stream of random mythical creatures.
     *
     * @return A stream of randomly generated mythical creatures
     */
    public static Stream<MythicalCreature> generateMythicalCreatures() {
        return Stream.generate(() -> {
            CreatureType type = generateCreatureType();
            String name = generateName(type);
            LocalDate firstMention = generateFirstMentionDate();
            int attackPower = generateAttackPower();
            return new MythicalCreature(name, type, firstMention, attackPower);
        });
    }

}
