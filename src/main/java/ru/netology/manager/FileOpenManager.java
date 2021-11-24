package ru.netology.manager;

import java.util.*;

public class FileOpenManager {

    Map<String, String> map = new HashMap();

    public void save(String key, String element) {
        map.put(key, element);
    }


    public Map<String, String> registerNewApp(String key, String element) {
        map.put(key, element);
        return map;
    }

    public String getAppToOpenFile(String key) {
        String element = map.get(key);
        return element;
    }

    public Map<String, String> deleteApps(String key) {
        map.remove(key);
        return map;
    }

    public Set<String> getListOfAllRegisteredApps() {
        Set<String> keySet = map.keySet();
        return keySet;
    }

    public ArrayList<String> getListOfAllApps() {
        ArrayList<String> value = new ArrayList<>(map.values());
        return value;
    }
}
