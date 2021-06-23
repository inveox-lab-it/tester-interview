package com.inveox;

import com.inveox.internal.Pack;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class NotesContainer {
    private List<Pack> notes = new ArrayList<>();
    private int size;
    private Clock clock;

    NotesContainer(int size, Clock clock) {
        this.size = size;
        this.clock = clock;
    }

    public NotesContainer(int size) {
        this.size = size;
        this.clock = Clock.systemUTC();
    }

    public void addNote(Note note) {
        if (notes.size() == size) {
            throw new RuntimeException("container is full");
        }
        notes.add(new Pack()
                .withNote(note)
                .withCreated(clock.millis()));
    }

    public void reset() {
        notes.clear();
    }

    public void removeFirstElement() {
        notes.remove(0);
    }

    public void removeLastElement() {
        notes.remove(notes.size());
    }

    public List<Note> findYoungerThan(LocalDateTime localDateTime) {
        long boundary = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        return notes.stream()
                .filter(p -> p.getCreated() > boundary)
                .map(Pack::getNote)
                .collect(toList());
    }

    public List<Note> findOlderThan(LocalDateTime localDateTime) {
        long boundary = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        return notes.stream()
                .filter(p -> p.getCreated() > boundary)
                .map(Pack::getNote)
                .collect(toList());
    }

    public List<Note> findNotesContainingText(String text) {
        return notes.stream()
                .map(Pack::getNote)
                .filter(note -> note.getText().contains(text))
                .collect(toList());
    }

    public List<Note> getAllNotes() {
        return notes.stream().map(Pack::getNote)
                .collect(toList());
    }

    public boolean isEmpty(){
        return notes.isEmpty();
    }

    public boolean containsNote(Note note) {
        return notes.stream().map(Pack::getNote)
                .anyMatch(n -> note.equals(note));
    }

    public int size() {
        return notes.size();
    }
}
