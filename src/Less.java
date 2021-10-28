import java.util.Map;

public class Less extends InstructionCompare {
    @Override
    public boolean compareArgs(Map<String, Num> variables) throws ExecutionException {
        return arg1.value(variables) < arg2.value(variables);
    }

    @Override
    protected String javaVal() {
        return arg1.javaValBracket() + " < " + arg2.javaValBracket() + " ? 1.0 : 0.0";
    }
}
