package me.salai.jarvis;


import org.junit.Test;

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

        // FixMe -- Need to rewrite the Test Cases properly and a little seriously
        assertThat(todo.isPresent("Complete Jarvis ASAP"),is(true));
    }

}