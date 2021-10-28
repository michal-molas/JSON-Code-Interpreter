import java.util.Map;

public class And extends InstructionTwoArgs {
    @Override
    public double value(Map<String, Num> variables) throws ExecutionException {
        return arg1.logicVal(variables) && arg2.logicVal(variables) ? 1.0 : 0.0;
    }
    
    @Override
    public boolean isSecondChecked(Map<String, Num> variables) throws ExecutionException {
        return arg1.logicVal(variables);
    }

    @Override
    protected String javaVal() {
        return arg1.javaValBracket() + " != 0.0 && " + arg2.javaValBracket() + " != 0.0 ? 1.0 : 0.0";
    }
}