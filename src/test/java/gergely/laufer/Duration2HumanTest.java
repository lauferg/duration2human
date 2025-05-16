package gergely.laufer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Duration2HumanTest {

    @Test
    void toHumanReadable_whenValuesPlural_thenNamesPlural() {
        String aHugeDuration = Duration2Human.toHumanReadable(315_359_999_999L);
        Assertions.assertEquals("9999 years, 364 days, 23 hours, 59 minutes and 59 seconds", aHugeDuration);
    }

    @Test
    void toHumanReadable_whenHasSingular_thenEndsWithoutPlural() {
        String aHugeDuration = Duration2Human.toHumanReadable(315_328_467_600L);
        Assertions.assertEquals("9999 years and 1 hour", aHugeDuration);
    }

    @Test
    void toHumanReadable_whenYearsAreEnough_thenUsesYearsOnly() {
        String aHugeDuration = Duration2Human.toHumanReadable(4_730_400_000L);
        Assertions.assertEquals("150 years", aHugeDuration);
    }

    @Test
    void toHumanReadable_whenSingularYear_thenNoPluralUsed() {
        String aHugeDuration = Duration2Human.toHumanReadable(31_536_000L);
        Assertions.assertEquals("1 year", aHugeDuration);
    }

    @Test
    void toHumanReadable_whenDaysAreEnough_thenUsesYearsOnly() {
        String aHugeDuration = Duration2Human.toHumanReadable(31_449_600L);
        Assertions.assertEquals("364 days", aHugeDuration);
    }

    @Test
    void toHumanReadable_whenSingularDay_thenNoPluralUsed() {
        String aHugeDuration = Duration2Human.toHumanReadable(86_400L);
        Assertions.assertEquals("1 day", aHugeDuration);
    }

    @Test
    void toHumanReadable_whenHoursAreEnough_thenUsesHoursOnly() {
        String aHugeDuration = Duration2Human.toHumanReadable(82_800);
        Assertions.assertEquals("23 hours", aHugeDuration);
    }

    @Test
    void toHumanReadable_whenSingularHour_thenNoPluralUsed() {
        String aHugeDuration = Duration2Human.toHumanReadable(3_600L);
        Assertions.assertEquals("1 hour", aHugeDuration);
    }

    @Test
    void toHumanReadable_whenMinutesAreEnough_thenUsesMinutesOnly() {
        String aHugeDuration = Duration2Human.toHumanReadable(3_540L);
        Assertions.assertEquals("59 minutes", aHugeDuration);
    }

    @Test
    void toHumanReadable_whenSingularMinute_thenNoPluralUsed() {
        String aHugeDuration = Duration2Human.toHumanReadable(60L);
        Assertions.assertEquals("1 minute", aHugeDuration);
    }

    @Test
    void toHumanReadable_whenSecondsAreEnough_thenOnlySecondsUsed() {
        String aHugeDuration = Duration2Human.toHumanReadable(59L);
        Assertions.assertEquals("59 seconds", aHugeDuration);
    }

    @Test
    void toHumanReadable_whenSingularSecond_thenNoPluralUsed() {
        String aHugeDuration = Duration2Human.toHumanReadable(1L);
        Assertions.assertEquals("1 second", aHugeDuration);
    }
}