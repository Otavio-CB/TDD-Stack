package br.edu.fatec.sjc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NumberAscOrder<T extends Number> {
    private final CustomStack<T> stack;

    public NumberAscOrder(CustomStack<T> stack) {
        if (stack == null) {
            throw new NullPointerException("A pilha não pode ser nula");
        }
        this.stack = stack;
    }

    public List<T> sort() {
        try {
            return sortInternal();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    private List<T> sortInternal() throws StackEmptyException {
        List<T> sortedList = new ArrayList<>();

        while (!stack.isEmpty()) {
            sortedList.add(stack.pop());
        }

        sortedList.sort(Comparator.comparingDouble(Number::doubleValue));
        return sortedList;
    }
}