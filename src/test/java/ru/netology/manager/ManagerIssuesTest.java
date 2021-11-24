package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issues;
import ru.netology.repository.RepositoryIssues;

import java.util.*;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class ManagerIssuesTest {

    private ManagerIssues manager = new ManagerIssues();
    private RepositoryIssues repository = new RepositoryIssues();

    private Issues issue1 = new Issues(1, "Ivanov", "Header1", "Text1", false,
            new HashSet<String>(Arrays.asList("label1", "label2")),
            new HashSet<String>(Arrays.asList("assignedTo1", "assignedTo2")));

    private Issues issue2 = new Issues(2, "Petrov", "Header2", "Text2", true,
            new HashSet<String>(Arrays.asList("label3", "label4")),
            new HashSet<String>(Arrays.asList("assignedTo3", "assignedTo4")));

    private Issues issue3 = new Issues(3, "Sidorov", "Header3", "Text3", false,
            new HashSet<String>(Arrays.asList("label5", "label6")),
            new HashSet<String>(Arrays.asList("assignedTo5", "assignedTo6")));

    private Issues issue4 = new Issues(4, "Pechkin", "Header4", "Text4", true,
            new HashSet<String>(Arrays.asList("label7", "label8")),
            new HashSet<String>(Arrays.asList("assignedTo7", "assignedTo8")));

    @BeforeEach
    public void before() {
        manager.save(issue1);
        manager.save(issue2);
        manager.save(issue3);
        manager.save(issue4);
    }

    @Test
    public void addNewIssue() {
        Issues issue5 = new Issues(5, "Nikolayev", "Header5", "Text5", false,
                new HashSet<String>(Arrays.asList("label9", "label10")),
                new HashSet<String>(Arrays.asList("assignedTo9", "assignedTo10")));
        manager.save(issue5);
        List<Issues> expected = List.of(issue1, issue2, issue3, issue4, issue5);
        List<Issues> actual = manager.returnAll();
        assertEquals(expected, actual);
    }

    @Test
    public void saveAllIssues() {
        Collection<Issues> expected = List.of(issue1, issue2, issue3, issue4);
        repository.addAll(expected);
        Collection<Issues> actual = repository.returnAll();
        assertEquals(expected, actual);
    }

    @Test
    public void listOfOpen() {
        Collection<Issues> expected = List.of(issue2, issue4);
        Collection<Issues> actual = manager.listOpenOrClosed(true);
        assertEquals(expected, actual);
    }

    @Test
    public void listOfClosed() {
        List<Issues> expected = List.of(issue1, issue3);
        List<Issues> actual = manager.listOpenOrClosed(false);
        assertEquals(expected, actual);
    }

    @Test
    public void filterByAuthor() {
        List<Issues> expected = List.of(issue3);
        List<Issues> actual = manager.filterByAuthor("Sidorov");
        assertEquals(expected, actual);
    }

    @Test
    public void filterByAuthorPredicate() {
        Predicate<String> equalsByAuthor = t -> t.equals("Pechkin");
        List<Issues> expected = List.of(issue4);
        List<Issues> actual = manager.filterByAuthorPredicate(equalsByAuthor);
        assertEquals(expected, actual);
    }

    @Test
    public void filterByLabelPredicate() {
        List<Issues> expected = List.of(issue1);
        String label = "label1";
        Predicate<String> equalsByLabel = a -> a.equals(label);
        List<Issues> actual = manager.filterByLabelPredicate(equalsByLabel);
        assertEquals(expected, actual);
    }


    @Test
    public void openOrClosedById() {
        Issues issue6 = new Issues(4, "Pechkin", "Header4", "Text4", false,
                new HashSet<String>(Arrays.asList("label7", "label8")),
                new HashSet<String>(Arrays.asList("assignedTo7", "assignedTo8")));
        List<Issues> expected = List.of(issue6);
        List<Issues> actual = Arrays.asList(manager.openOrClosedById(4));
        assertEquals(expected, actual);
    }

}