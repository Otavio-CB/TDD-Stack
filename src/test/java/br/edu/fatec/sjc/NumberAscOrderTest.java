package br.edu.fatec.sjc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NumberAscOrderTest {

    @Mock
    private CalculableStrategy<Integer> calculableStrategy;

    private CustomStack<Integer> stackWithNumbers;
    private CustomStack<Integer> emptyStack;
    private CustomStack<Integer> mockStack;

    @BeforeEach
    public void setup() {
        when(calculableStrategy.calculateValue(Mockito.anyInt()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        stackWithNumbers = new CustomStack<>(6, calculableStrategy);
        try {
            stackWithNumbers.push(23);
            stackWithNumbers.push(5);
            stackWithNumbers.push(42);
            stackWithNumbers.push(16);
            stackWithNumbers.push(8);
            stackWithNumbers.push(35);
        } catch (StackFullException e) {
            fail("Não deveria lançar StackFullException");
        }

        emptyStack = new CustomStack<>(6, calculableStrategy);

        mockStack = mock(CustomStack.class);
    }

    @Test
    public void testSortWithNumbers() {
        NumberAscOrder<Integer> numberAscOrder = new NumberAscOrder<>(stackWithNumbers);
        List<Integer> sortedNumbers = numberAscOrder.sort();

        assertEquals(List.of(5, 8, 16, 23, 35, 42), sortedNumbers);

        assertTrue(stackWithNumbers.isEmpty());
    }

    @Test
    public void testSortWithEmptyStack() {
        NumberAscOrder<Integer> numberAscOrder = new NumberAscOrder<>(emptyStack);
        List<Integer> sortedNumbers = numberAscOrder.sort();

        assertTrue(sortedNumbers.isEmpty());
    }

    @Test
    public void testConstructorWithNullStack() {
        assertThrows(NullPointerException.class, () -> {
            new NumberAscOrder<>(null);
        });
    }

    @Test
    public void testSortWhenStackThrowsStackEmptyException() throws StackEmptyException {
        when(mockStack.isEmpty()).thenReturn(false);
        when(mockStack.pop()).thenThrow(StackEmptyException.class);

        NumberAscOrder<Integer> numberAscOrder = new NumberAscOrder<>(mockStack);

        assertDoesNotThrow(() -> {
            List<Integer> result = numberAscOrder.sort();
            assertTrue(result.isEmpty());
        });
    }

    @Test
    public void testSortWhenStackIsEmptyAfterChecking() throws StackEmptyException {
        when(mockStack.isEmpty()).thenReturn(false);
        when(mockStack.pop()).thenThrow(StackEmptyException.class);

        NumberAscOrder<Integer> numberAscOrder = new NumberAscOrder<>(mockStack);
        List<Integer> result = numberAscOrder.sort();

        assertTrue(result.isEmpty());
        verify(mockStack, times(1)).isEmpty();
        verify(mockStack, times(1)).pop();
    }
}