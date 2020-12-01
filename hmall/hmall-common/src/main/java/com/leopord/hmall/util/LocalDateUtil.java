package com.leopord.hmall.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public final class LocalDateUtil {

    private static final ZoneId ZONE = ZoneId.systemDefault();

    public static LocalDateTime dateToLocalDateTime(Date d) {
        Instant instant = d.toInstant();
        return LocalDateTime.ofInstant(instant, ZONE);
    }
}
