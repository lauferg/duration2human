package gergely.laufer;

import java.util.*;
import java.util.stream.Stream;

public final class Duration2Human {

    public static String toHumanReadable(long duration) {
        DurationComponent currentDurationComponent;
        StringBuilder humanReadableBuilder = new StringBuilder();

        if (duration <= 0) {
            humanReadableBuilder.append("now");
        }

        SortedSet<DurationComponent> sortedDurationComponents = DurationComponent.getSortedDurationComponents();

        for (DurationComponent sortedDurationComponent : sortedDurationComponents) {
            currentDurationComponent = sortedDurationComponent;

            if (currentDurationComponent.isInvalidDurationComponent(duration)) {
                continue;
            }

            long dcValue = currentDurationComponent.calculateValueFromDuration(duration);
            duration -= dcValue * currentDurationComponent.conversionRateToBaseUnit;

            if (!humanReadableBuilder.isEmpty() && duration > 0) {
                humanReadableBuilder.append(", ");
            } else if (!humanReadableBuilder.isEmpty()) {
                humanReadableBuilder.append(" and ");
            }

            humanReadableBuilder.append(formatDcValue(dcValue, currentDurationComponent));
        }

        return humanReadableBuilder.toString();
    }

    private static String formatDcValue(long dcValue, DurationComponent durationComponent) {
        String dcNamePluralised = dcValue == 1 ?
                durationComponent.name :
                durationComponent.name.concat("s");
        return String.format("%d %s", dcValue, dcNamePluralised);
    }

    private enum DurationComponent {
        SECOND("second", 1),
        MINUTE("minute", 60),
        HOUR("hour", 3_600),
        DAY("day", 86_400),
        YEAR("year", 31_536_000);

        private final String name;
        private final long conversionRateToBaseUnit;

        DurationComponent(String name, long conversionRateToBaseUnit) {
            this.name = name;
            this.conversionRateToBaseUnit = conversionRateToBaseUnit;
        }

        public long getConversionRateToBaseUnit() {
            return conversionRateToBaseUnit;
        }

        public long calculateValueFromDuration(long duration) {
            return duration / conversionRateToBaseUnit;
        }

        public boolean isInvalidDurationComponent(long duration) {
            return conversionRateToBaseUnit > duration;
        }

        public static SortedSet<DurationComponent> getSortedDurationComponents() {
            TreeSet<DurationComponent> durationComponents = new TreeSet<>(Comparator.comparingLong(DurationComponent::getConversionRateToBaseUnit).reversed());
            durationComponents.addAll(Arrays.asList(values()));
            return Collections.unmodifiableSortedSet(durationComponents);
        }
    }
}
