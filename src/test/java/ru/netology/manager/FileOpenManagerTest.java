package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class FileOpenManagerTest {

    private FileOpenManager fileOpenManager = new FileOpenManager();

    @BeforeEach
    public void before() {
        fileOpenManager.save("doc", "Word");
        fileOpenManager.save("exe", "Executable");
    }

    @Test
    public void registerNewApp() {
        Map<String, String> mapT = new HashMap<>();
        mapT.put("ipg", "ACDSee");
        mapT.put("doc", "Word");
        mapT.put("exe", "Executable");
        Map expected = mapT;
        Map actual = fileOpenManager.registerNewApp("ipg", "ACDSee");
        assertEquals(expected, actual);
    }

    @Test
    public void getApplicationToOpenFile() {
        String expected = "Word";
        String actual = String.valueOf(fileOpenManager.getAppToOpenFile("doc"));
        assertEquals(expected, actual);
    }

    @Test
    public void removeTheBindingApplications() {
        Map<String, String> mapT = new HashMap<>();
        mapT.put("doc", "Word");
        Map expected = mapT;
        Map actual = fileOpenManager.deleteApps("exe");
        assertEquals(expected, actual);
    }

    @Test
    public void getListAllRegisteredExtensions() {
        Set<String> expected = Set.of("exe", "doc");
        Set actual = fileOpenManager.getListOfAllRegisteredApps();
        assertEquals(expected, actual);
    }


    @Test
    public void getListAllApp() {
        List<String> expected = List.of("Executable", "Word");
        ArrayList actual = fileOpenManager.getListOfAllApps();
        assertEquals(expected, actual);
    }

}