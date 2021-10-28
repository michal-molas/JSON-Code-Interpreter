import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class Not extends Instruction {
    private Instruction arg;

    @Override
    public double value(Map<String, Num> variables) throws ExecutionException {
        return arg.logicVal(variables) ? 0.0 : 1.0;
    }

    @Override
    public void execute(Map<String, Num> variables) throws ExecutionException {
        arg.execute(variables);
    }

    @Override
    protected String javaVal() {
        return arg.javaValBracket() + " != 0.0 ? 0.0 : 1.0";
    }

    @Override
    public void collectVars(Set<String> variables) {
        arg.collectVars(variables);
    }

    @Override
    public void checkCorrectJSON() throws IncorrectProgram {
        if (arg == null)
            throw new IncorrectProgram();
        
        arg.checkCorrectJSON();
    }

    @Override
    public void printFunc(JavaWriter wr) throws IOException {
        arg.printFunc(wr);
    }
}
