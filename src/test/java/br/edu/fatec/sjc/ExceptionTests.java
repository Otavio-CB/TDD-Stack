package br.edu.fatec.sjc;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExceptionTests {

    @Test
    public void testStackEmptyException() {
        assertDoesNotThrow(() -> {
            throw new StackEmptyException();
        });
    }

    @Test
    public void testStackFullException() {
        assertDoesNotThrow(() -> {
            throw new StackFullException();
        });
    }
}