package com.wareline.agenda.shared.helpers;

import java.util.UUID;

public final class IdHelper {
    private IdHelper() {
    }

    public static String uuid() {
        return UUID.randomUUID().toString().toLowerCase().replace("-", "");
    }
}
