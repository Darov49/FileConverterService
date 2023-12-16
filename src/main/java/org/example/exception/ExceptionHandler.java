package org.example.exception;

import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

@Log4j2
@UtilityClass
public class ExceptionHandler {
    public void handleException(Exception exception) {
        System.err.println(exception.getMessage()  + "Смотри подробности в файле логов\nЗавершение программы");
        log.error(exception.getMessage(), exception);
    }
}
