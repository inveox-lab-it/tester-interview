package com.inveox.internal;

import com.inveox.Note;

public class Pack {
    private Note note;
    private long created;

    public long getCreated() {
        return created;
    }

    public Pack withCreated(long created) {
        this.created = created;
        return this;
    }

    public Note getNote() {
        return note;
    }

    public Pack withNote(Note note) {
        this.note = note;
        return this;
    }

}
