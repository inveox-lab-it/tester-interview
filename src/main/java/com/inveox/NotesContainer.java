package com.inveox;

import com.inveox.internal.Pack;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class NotesContainer {
    private final List<Pack> notes = new ArrayList<>();
    private final int size;
    private final Clock clock;

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
                .withCreated(clock.instant().toEpochMilli()));
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
        long boundary = localDateTime.atZone(ZoneOffset.UTC).toInstant().toEpochMilli();
        return notes.stream()
                .filter(p -> p.getCreated() > boundary)
                .map(Pack::getNote)
                .collect(toList());
    }

    public List<Note> findOlderThan(LocalDateTime localDateTime) {
        long boundary = localDateTime.atZone(ZoneOffset.UTC).toInstant().toEpochMilli();
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

    public boolean isEmpty() {
        return notes.isEmpty();
    }

    public boolean containsNote(Note note) {
        return notes.stream().map(Pack::getNote)
                .anyMatch(n -> note.equals(note));
    }

    public int size() {
        return notes.size();
    }

    public Note getFirstElement() {
        return notes.get(0).getNote();
    }

    public Note getLastElement() {
        if (notes.size() == 0) {
            throw new RuntimeException("container is empty");
        }
        return notes.get(notes.size()).getNote();
    }
}
