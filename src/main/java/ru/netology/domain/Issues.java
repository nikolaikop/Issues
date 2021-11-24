package ru.netology.domain;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.HashSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class Issues {
    private int id;
    private String author;
    private String header;
    private String body;
    private Boolean openOrClosed;
    private HashSet<String> lable;
    private HashSet<String> assignedTo;
}
