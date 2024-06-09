package com.wareline.agenda.shared.helpers;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class InstantHelper {
    private InstantHelper() {
    }

    public static Instant now() {
        return Instant.now().truncatedTo(ChronoUnit.MICROS);
    }
}
