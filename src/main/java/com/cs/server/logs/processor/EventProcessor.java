package com.cs.server.logs.processor;

import com.cs.server.logs.database.EventsDao;
import com.cs.server.logs.model.DbEvent;
import com.cs.server.logs.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class EventProcessor {

    private FileUtils fileUtils = new FileUtils();
    private EventsDao eventsDao = new EventsDao();
    List<DbEvent> eventsForDatabase = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(EventProcessor.class);

    public void parseLogFile(String file){
        eventsForDatabase =  fileUtils.parseLogFile(file);
    }

    public void persistEvents(){
        eventsDao.createConnection();
        eventsDao.createTable();
        eventsDao.insertDbRecords(eventsForDatabase);
        logger.info("{} events inserted in database", eventsForDatabase.size());
    }


    public FileUtils getFileUtils() {
        return fileUtils;
    }

    public void setFileUtils(FileUtils fileUtils) {
        this.fileUtils = fileUtils;
    }

    public EventsDao getEventsDao() {
        return eventsDao;
    }

    public void setEventsDao(EventsDao eventsDao) {
        this.eventsDao = eventsDao;
    }

}
