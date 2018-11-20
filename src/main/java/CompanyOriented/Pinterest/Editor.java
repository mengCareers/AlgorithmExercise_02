package CompanyOriented.Pinterest;

import java.util.Stack;

public class Editor {

    Stack<Character> stackLeft;
    Stack<Character> stackRight; //

    public Editor() {
        stackLeft = new Stack<>();
        stackRight = new Stack<>();
    }

    public void append(char ch) {
        stackLeft.push(ch);
    }

    public void delete() {
        if (!stackLeft.isEmpty())
            stackLeft.pop();
    }

    public char get() {
        return stackLeft.peek();
    }

    public void insert(char ch, int i) { // abcd
        moveCursorLeft(stackLeft.size() - i);
    }

    public void moveCursorLeft(int k) {
        while (!stackLeft.isEmpty() && --k >= 0)
            stackRight.push(stackLeft.pop());
    }

    public void moveCursorRight(int k) {
        while (!stackRight.isEmpty() && --k >= 0)
            stackLeft.push(stackRight.pop()); //
    }

    public int size() {
        return stackLeft.size() + stackRight.size();
    }
}
