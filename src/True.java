import java.util.Map;

public class True extends InstructionBool {
    @Override
    public double value(Map<String, Num> variables) throws ExecutionException {
        return 1.0;
    }

    @Override
    public String javaVal() {
        return "1.0";
    }
}
