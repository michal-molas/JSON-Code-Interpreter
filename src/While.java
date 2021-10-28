import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class While extends Instruction {
    private Instruction cond;
    private Instruction block;
    
    private int idx;
    
    @Override
    public double value(Map<String, Num> variables) throws ExecutionException {
        return 0.0;
    }
    
    @Override
    public void execute(Map<String, Num> variables) throws ExecutionException {
        while (cond.logicVal(variables)) {
            block.execute(variables);
            cond.execute(variables);
        }
        cond.execute(variables);
    }

    @Override
    public String javaVal() {
        return "while" + idx + "()";
    }

    @Override
    public String javaValBracket() {
        return javaVal();
    }

    @Override
    public void collectVars(Set<String> variables) {
        cond.collectVars(variables);
        block.collectVars(variables);
    }

    @Override
    public void checkCorrectJSON() throws IncorrectProgram {
        if (cond == null || block == null)
            throw new IncorrectProgram();
        
        cond.checkCorrectJSON();
        block.checkCorrectJSON();
    }

    @Override
    public void printFunc(JavaWriter wr) throws IOException {
        cond.printFunc(wr);
        block.printFunc(wr);

        idx = func_idx;
        func_idx++;
        wr.write("private static double while" + idx + "() {", 1, true);

        wr.write("while (" + cond.javaValBracket() + " != 0.0)", 2, true);
        block.writeToJavaForce(wr, 3);
        wr.write("", 0, true);

        wr.write("return 0.0;", 2, true);
        
        wr.write("}", 1, true);
        wr.write("", 0, true);
    }
}
