import java.io.IOException;
import java.util.Map;
import java.util.Set;

public abstract class InstructionBool extends Instruction {
    @Override
    public void execute(Map<String, Num> variables) throws ExecutionException {}

    @Override
    public void collectVars(Set<String> variables) {}

    @Override
    public void checkCorrectJSON() throws IncorrectProgram {}

    @Override
    public void writeToJava(JavaWriter wr, int tabs) throws IOException {}

    @Override
    protected void writeToJavaForce(JavaWriter wr, int tabs) throws IOException {
        wr.write(";", tabs, true);
    }

    @Override
    public String javaValBracket() {
        return javaVal();
    }
}
