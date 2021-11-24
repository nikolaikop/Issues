package ru.netology.manager;

import ru.netology.domain.Issues;
import ru.netology.repository.RepositoryIssues;

import java.util.*;
import java.util.List;
import java.util.function.Predicate;

public class ManagerIssues {

    private RepositoryIssues repository = new RepositoryIssues();

    public void save(Issues item) {
        repository.save(item);
    }

    public List<Issues> returnAll() {
        return repository.returnAll();
    }

    public List<Issues> listOpenOrClosed(Boolean index) {
        List<Issues> result = new ArrayList<>();
        for (Issues issue : repository.returnAll())
            if (index == issue.getOpenOrClosed()) {
                result.add(issue);
            }
        return result;
    }

    public List<Issues> filterByAuthor(String author) {
        List<Issues> result = new ArrayList<>();
        for (Issues issue : returnAll()) {
            if (author.equals(issue.getAuthor())) {
                result.add(issue);
            }
        }
        return result;
    }

    public List<Issues> filterByAuthorPredicate(Predicate<String> byAuthor) {
        List<Issues> result = new ArrayList<>();
        for (Issues issue : returnAll()) {
            if (byAuthor.test(issue.getAuthor())) {
                result.add(issue);
            }
        }
        return result;
    }

    public List<Issues> filterByLabelPredicate(Predicate<String> predicateLabel) {
        List<Issues> result = new ArrayList<>();
        for (Issues issue : returnAll()) {
            HashSet<String> labels = issue.getLable();
            for (String label : labels) {
                if (predicateLabel.test(label)) {
                    result.add(issue);
                }
            }
        }
        return result;
    }

    public List<Issues> filterByAssignedToPredicate(Predicate<String> prAssignedTo) {
        List<Issues> result = new ArrayList<>();
        for (Issues issue : returnAll()) {
            HashSet<String> assignees = issue.getAssignedTo();
            for (String assignedTo : assignees)
                if (prAssignedTo.test(assignedTo)) {
                    result.add(issue);
                }
        }
        return result;
    }

    public List<Issues> filterByAssignedTo(String assignedTo) {
        List<Issues> result = new ArrayList<>();
        for (Issues issue : returnAll()) {
            Set<String> set = issue.getAssignedTo();
            if (set.contains(assignedTo)) result.add(issue);
        }
        return result;
    }

    Comparator<Issues> comparatorId = new Comparator<Issues>() {
        @Override
        public int compare(Issues o1, Issues o2) {
            return o1.getId() - o2.getId();
        }
    };

    public List<Issues> sortById() {
        List<Issues> issueList = new ArrayList<>(repository.returnAll());
        Collections.sort(issueList, comparatorId);
        return issueList;
    }

    Comparator<Issues> comparatorAuthor = new Comparator<Issues>() {
        @Override
        public int compare(Issues o1, Issues o2) {
            return o1.getAuthor().compareTo(o2.getAuthor());
        }
    };

    public List<Issues> sortByAuthor() {
        List<Issues> issueList = new ArrayList<>(repository.returnAll());
        Collections.sort(issueList, comparatorAuthor);
        return issueList;
    }

    public Issues openOrClosedById(int id) {
        Issues result = repository.findById(id);
        boolean openOrClosed = result.getOpenOrClosed();
        result.setOpenOrClosed(!openOrClosed);
        return result;
    }
}
