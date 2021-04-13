package com.cs.server.logs.processor;

import com.cs.server.logs.database.EventsDao;
import com.cs.server.logs.utils.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EventProcessorTest {
    EventProcessor eventProcessor;

    @Mock
    FileUtils fileUtils;

    @Mock
    EventsDao eventsDao;

    @Before
    public void setUp(){
        eventProcessor = new EventProcessor();
        eventProcessor.setFileUtils(fileUtils);
        eventProcessor.setEventsDao(eventsDao);
    }

    @Test
    public void testParseLogFile(){
        when(fileUtils.parseLogFile(anyString())).thenReturn(anyList());
        eventProcessor.parseLogFile("data.txt");
        verify(fileUtils, times(1)).parseLogFile(anyString());
    }

    @Test
    public void testPersistEvents(){
        doNothing().when(eventsDao).createConnection();
        doNothing().when(eventsDao).createTable();
        doNothing().when(eventsDao).insertDbRecords(anyList());
        eventProcessor.persistEvents();
        verify(eventsDao, times(1)).createConnection();
        verify(eventsDao, times(1)).createTable();
        verify(eventsDao, times(1)).createTable();
    }

}
