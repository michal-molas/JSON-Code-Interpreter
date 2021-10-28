import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class Assign extends Instruction {
    private String name;
    private Instruction value;
    
    @Override
    public double value(Map<String, Num> variables) throws ExecutionException {
        return value.value(variables);
    }
    
    @Override
    public void execute(Map<String, Num> variables) throws ExecutionException {
        variables.put(name, new Num(value.value(variables)));
    }

    @Override
    protected String javaVal() {
        return name + " = " + value.javaValBracket();
    }
    
    @Override
    public void printFunc(JavaWriter wr) throws IOException {
        value.printFunc(wr);
    }

    @Override
    public void collectVars(Set<String> variables) {
        variables.add(name);
        value.collectVars(variables);
    }

    @Override
    public void checkCorrectJSON() throws IncorrectProgram {
        if (name == null || value == null)
            throw new IncorrectProgram();
        
        value.checkCorrectJSON();
    }
}
