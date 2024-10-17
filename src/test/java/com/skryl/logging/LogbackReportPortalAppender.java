package com.skryl.logging;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import com.epam.reportportal.service.ReportPortal;

import java.util.Date;

/**
 * Logback classic module appender for report portal
 */
public class LogbackReportPortalAppender extends AppenderBase<ILoggingEvent> {


    @Override
    protected void append(ILoggingEvent iLoggingEvent) {
        ReportPortal.emitLog(
                iLoggingEvent.getFormattedMessage(),
                iLoggingEvent.getLevel().toString(),
                new Date()
        );
    }
}
