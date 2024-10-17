package com.skryl.logging;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import io.qameta.allure.Allure;

public class LogbackAllureAppender extends AppenderBase<ILoggingEvent> {
    @Override
    protected void append(ILoggingEvent iLoggingEvent) {
        var logMessage = iLoggingEvent.getFormattedMessage();
        Allure.step(logMessage);
    }
}
