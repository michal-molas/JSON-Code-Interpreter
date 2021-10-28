import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class Num extends Instruction {
    private double value;
    
    public Num() { this.value = 0.0; }
    
    public Num(double value) {
        this.value = value;
    }

    @Override
    public double value(Map<String, Num> variables) throws ExecutionException {
        return this.value;
    }

    @Override
    public void execute(Map<String, Num> variables) throws ExecutionException {}

    @Override
    protected String javaVal() {
        return String.valueOf(value);
    }

    @Override
    public String javaValBracket() {
        return javaVal();
    }

    @Override
    public void writeToJava(JavaWriter wr, int tabs) throws IOException {}

    @Override
    protected void writeToJavaForce(JavaWriter wr, int tabs) throws IOException {
        wr.write(";", tabs, true);
    }

    @Override
    public void collectVars(Set<String> variables) {}

    @Override
    public void checkCorrectJSON() throws IncorrectProgram {}
    
    public void changeVal(double newVal) {
        value = newVal;
    }
}
