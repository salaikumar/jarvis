package me.salaikumar.jarvis;


import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Unit Test Cases for TodoTest.
 */
public class TodoTest {

    @Test
    public void shouldTestTodo(){
        Todo todo = new Todo();
        todo.addTask("Complete Jarvis ASAP");
        todo.addTask("Solve atleast 5 simple problems");
        todo.save();

        assertThat(todo.search("Complete Jarvis ASAP"),is(true));
        assertThat(todo.getAllTasks().size(),is(2));
        assertThat(todo.nextTasks().size(),is(2));
        assertThat(todo.archivedTasks().size(),is(0));

    }

}