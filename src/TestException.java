public class TestException {
    private void methodWithException() {
        throw new RuntimeException("we throw a runtime exception");
        //int result = 1 / 0;
    }

    public void methodCaller() {
        methodWithException();
    }

    public static void main(String[] args) {
        TestException inst = new TestException();
        inst.methodCaller();
    }
}
