package com.cs.server.logs.utils;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FileUtilsTest {

    private FileUtils fileUtils = new FileUtils();

    @Test
    public void testFileAvailable(){
        assertTrue(fileUtils.fileAvailable("data.txt"));
    }

    @Test
    public void testFileNotAvailable(){
        assertFalse(fileUtils.fileAvailable("no_data.txt"));
    }
}
