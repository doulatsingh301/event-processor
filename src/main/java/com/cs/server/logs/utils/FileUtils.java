package com.cs.server.logs.utils;

import com.cs.server.logs.model.DbEvent;
import com.cs.server.logs.model.Events;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonStreamParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class FileUtils {

    List<DbEvent> eventsForDatabase = new ArrayList<>();
    BufferedReader bufferedReader;
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
    private static final int ALERT_DURATION = 4;

    public List<DbEvent> parseLogFile(String file){
        if(fileAvailable(file)){
            processRecords();
        }else{
            logger.error("Please provide the correct file path.");
        }
        return eventsForDatabase;
    }

    public boolean fileAvailable(String file){
        boolean fileAvailable = true;
        try{
            bufferedReader = new BufferedReader(new FileReader(file));
        }catch(FileNotFoundException ex){
            logger.error("Could not find the log file");
            fileAvailable = false;
        }
        return fileAvailable;
    }

    public void processRecords(){
        boolean fileProcessed = true;
        Gson gson = new GsonBuilder().create();
        JsonStreamParser parser = new JsonStreamParser(bufferedReader);
        ConcurrentHashMap<String, Events> map = new ConcurrentHashMap<>();

        while (parser.hasNext()) {
            JsonElement element = parser.next();
            if (element.isJsonObject()) {
                Events event = gson.fromJson(element, Events.class);
                if(map.containsKey(event.getId())){
                    createRecords(event, map.get(event.getId()));
                }else{
                    map.put(event.getId(), event);
                }
            }
        }

        logger.info(" {} events created", eventsForDatabase.size());
    }

    public void createRecords(Events event1, Events event2){
        boolean alert = false;
        long duration = event1.getTimestamp() - event2.getTimestamp();
        if(duration > ALERT_DURATION){
            alert = true;
        }
        eventsForDatabase.add(new DbEvent(event1.getId(), duration,  event1.getHost(), event1.getType(), alert));
    }
}
