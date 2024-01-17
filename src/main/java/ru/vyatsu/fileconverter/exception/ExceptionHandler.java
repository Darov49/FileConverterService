package ru.vyatsu.fileconverter.exception;

import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

@Log4j2
@UtilityClass
public class ExceptionHandler {
    public void handleException(final Exception exception) {
        System.err.println(exception.getMessage()  + "\nСмотри подробности в файле логов\nЗавершение программы");
        log.error(exception.getMessage(), exception);
    }
}
