import java.util.Map;

public class Div extends InstructionArithm {
    @Override
    public double performOperation(Map<String, Num> variables) throws ExecutionException {
        return arg1.value(variables) / arg2.value(variables);
    }
    
    @Override
    public void checkExceptions(Map<String, Num> variables) throws ExecutionException {
        if (arg2.value(variables) == 0)
            throw new ExecutionException("division by 0");
    }

    @Override
    protected String javaVal() {
        return arg1.javaValBracket() + " / " + arg2.javaValBracket();
    }
}
