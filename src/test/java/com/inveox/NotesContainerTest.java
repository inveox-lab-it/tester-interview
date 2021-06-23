package com.inveox;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class NotesContainerTest {
    //TODO implement tests

    @Test
    void shoudl() {
        Note x = new Note("ala");
        NotesContainer notesContainer = new NotesContainer(3);
        notesContainer.addNote(x);
      //  notesContainer.addNote(new Note("bela"));

        Assertions.assertThat(notesContainer.containsNote(new Note("bela")))
                .isEqualTo(true);
    }
}