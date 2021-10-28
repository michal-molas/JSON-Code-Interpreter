public class ExecutionException extends Exception {
    public ExecutionException(String s) {
        super(s);
    }
    
    public void printErrorMsg() {
        System.err.println("Execution exception: " + getMessage() + ".");
    }
}
