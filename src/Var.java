import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class Var extends Instruction {
    private String name;

    @Override
    public double value(Map<String, Num> variables) throws ExecutionException {
        if (variables.containsKey(name))
            return variables.get(name).value(variables);
        else
            return 0.0;
    }

    @Override
    public void execute(Map<String, Num> variables) throws ExecutionException {}

    @Override
    protected String javaVal() {
        return name;
    }
    
    @Override
    public String javaValBracket() {
        return javaVal();
    }

    @Override
    public void collectVars(Set<String> variables) {
        variables.add(name);
    }

    @Override
    public void checkCorrectJSON() throws IncorrectProgram {
        if (name == null)
            throw new IncorrectProgram();
    }
    
    @Override
    public void writeToJava(JavaWriter wr, int tabs) throws IOException {}

    @Override
    protected void writeToJavaForce(JavaWriter wr, int tabs) throws IOException {
        wr.write(";", tabs, true);
    }
}
