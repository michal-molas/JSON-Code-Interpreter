public class IncorrectProgram extends Exception {
    public void printMsg() {
        System.err.println("Incorrect JSON file.");
    }
}
