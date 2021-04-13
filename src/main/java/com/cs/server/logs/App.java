package com.cs.server.logs;

import com.cs.server.logs.processor.EventProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        String file = args[0];
        logger.info("starting processing for file : {}", file);
        EventProcessor eventProcessor = new EventProcessor();

        eventProcessor.parseLogFile(file);
        eventProcessor.persistEvents();
        logger.info("event processing completed");
    }







}
