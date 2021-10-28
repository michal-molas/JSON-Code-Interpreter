import java.util.Map;

public abstract class InstructionArithm extends InstructionTwoArgs {
    @Override
    public double value(Map<String, Num> variables) throws ExecutionException {
        checkExceptions(variables);
        arg1.execute(variables);
        arg2.execute(variables);
        return performOperation(variables);
    }
    
    public abstract double performOperation(Map<String, Num> variables) throws ExecutionException;
}
