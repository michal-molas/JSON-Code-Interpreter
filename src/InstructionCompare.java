import java.util.Map;

public abstract class InstructionCompare extends InstructionTwoArgs {
    @Override
    public double value(Map<String, Num> variables) throws ExecutionException {
        return compareArgs(variables) ? 1.0 : 0.0;
    }
    
    public abstract boolean compareArgs(Map<String, Num> variables) throws ExecutionException;
}