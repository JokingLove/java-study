package com.fastrun.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * DateTimeUtil
 * @author joking
 */
@Slf4j
public class DateTimeUtil {

    public static LocalDateTime parse(Date date) {
        log.info("date: {}", date);
        return LocalDateTime.from(date.toInstant().atZone(ZoneId.systemDefault()));
    }

}
