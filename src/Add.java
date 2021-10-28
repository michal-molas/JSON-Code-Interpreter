import java.util.Map;

public class Add extends InstructionArithm {
    @Override
    public double performOperation(Map<String, Num> variables) throws ExecutionException {
        return arg1.value(variables) + arg2.value(variables);
    }

    @Override
    protected String javaVal() {
        return arg1.javaValBracket() + " + " + arg2.javaValBracket();
    }
}
