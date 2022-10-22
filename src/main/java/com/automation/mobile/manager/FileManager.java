package com.automation.mobile.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FileManager {
    private FileInputStream inputStream;
    //private static final Logger LOGGER = Logger.getLogger(FileManager.class.getSimpleName());

    public FileManager(String filePath) throws IOException {
        inputStream = new FileInputStream(filePath);
    }

    public Map<String, String> getPropertyInMap() throws IOException{
        Map<String, String> propertyFileMap = new HashMap<>();
        Properties properties = new Properties();
        properties.load(inputStream);
        properties.forEach((k, v) -> propertyFileMap.put(k.toString(), v.toString()));
        return propertyFileMap;
    }

}
