import java.util.Map;

public class False extends InstructionBool {
    @Override
    public double value(Map<String, Num> variables) throws ExecutionException {
        return 0.0;
    }

    @Override
    protected String javaVal() {
        return "0.0";
    }
}
