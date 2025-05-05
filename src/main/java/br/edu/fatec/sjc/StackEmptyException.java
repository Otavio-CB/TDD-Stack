package br.edu.fatec.sjc;

public class StackEmptyException extends Exception {
    public StackEmptyException() {
        super("A pilha está vazia");
    }
}