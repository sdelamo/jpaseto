/*
 * Copyright (C) 2014 jsonwebtoken.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.paseto.jpaseto.lang;

import java.time.DateTimeException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * @since 0.1.0
 */
public final class DateFormats {

    private DateFormats() {}

    public static final DateTimeFormatter ISO_OFFSET_DATE_TIME = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .append(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                .appendOffset("+HH:MM", "+00:00")
                .toFormatter()
                .withZone(ZoneOffset.UTC);

    // This formatter is identical to the one above, except that it can parse (and generate)
    // timestamps with 'Z' instead of '+00:00'.
    private static final DateTimeFormatter ISO_OFFSET_DATE_TIME_Z = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .append(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            .appendOffsetId()
            .toFormatter()
            .withZone(ZoneOffset.UTC);

    public static String formatIso8601(Instant instant) {
        return ISO_OFFSET_DATE_TIME.format(instant);
    }

    public static Instant parseIso8601Date(String s) throws DateTimeException {
        Assert.notNull(s, "String argument cannot be null.");
        return Instant.from(ISO_OFFSET_DATE_TIME_Z.parse(s));
    }
}
