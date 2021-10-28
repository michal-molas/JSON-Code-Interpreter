import java.io.IOException;
import java.util.Map;
import java.util.Set;

public abstract class InstructionTwoArgs extends Instruction {
    protected Instruction arg1;
    protected Instruction arg2;
    
    @Override
    public void execute(Map<String, Num> variables) throws ExecutionException {
        checkExceptions(variables);
        arg1.execute(variables);
        if (isSecondChecked(variables))
            arg2.execute(variables);
    }

    @Override
    public void collectVars(Set<String> variables) {
        arg1.collectVars(variables);
        arg2.collectVars(variables);
    }
    
    @Override
    public void checkCorrectJSON() throws IncorrectProgram {
        if (arg1 == null || arg2 == null)
            throw new IncorrectProgram();
        
        arg1.checkCorrectJSON();
        arg2.checkCorrectJSON();
    }
    
    @Override
    public void printFunc(JavaWriter wr) throws IOException {
        arg1.printFunc(wr);
        arg2.printFunc(wr);
    }
    
    public boolean isSecondChecked(Map<String, Num> variables) throws ExecutionException { return true; }
}
