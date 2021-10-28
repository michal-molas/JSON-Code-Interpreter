import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class If extends Instruction {
    private Instruction cond;
    private Instruction trueBlock;
    private Instruction falseBlock;
    
    private int idx;

    @Override
    public double value(Map<String, Num> variables) throws ExecutionException {
        if (cond.logicVal(variables))
            return trueBlock.value(variables);
        else if (falseBlock != null)
            return falseBlock.value(variables);
        else
            return 0.0;
    }
    
    @Override
    public void execute(Map<String, Num> variables) throws ExecutionException {
        cond.execute(variables);
        if (cond.logicVal(variables))
            trueBlock.execute(variables);
        else if (falseBlock != null)
            falseBlock.execute(variables);
    }

    @Override
    protected String javaVal() {
        return "if" + idx + "()";
    }

    @Override
    public String javaValBracket() {
        return javaVal();
    }

    @Override
    public void collectVars(Set<String> variables) {
        cond.collectVars(variables);
        trueBlock.collectVars(variables);
        if (falseBlock != null)
            falseBlock.collectVars(variables);
    }

    @Override
    public void checkCorrectJSON() throws IncorrectProgram {
        if (cond == null || trueBlock == null)
            throw new IncorrectProgram();
        
        cond.checkCorrectJSON();
        trueBlock.checkCorrectJSON();
        if (falseBlock != null)
            falseBlock.checkCorrectJSON();
    }

    @Override
    public void printFunc(JavaWriter wr) throws IOException {
        cond.printFunc(wr);
        trueBlock.printFunc(wr);
        if (falseBlock != null)
            falseBlock.printFunc(wr);
        
        idx = func_idx;
        func_idx++; 
        wr.write("private static double if" + idx + "() {", 1, true);

        wr.write("if (" + cond.javaValBracket() + " != 0.0)", 2, true);
        wr.write("return " + trueBlock.javaVal() + ";", 3, true);

        if (falseBlock != null) {
            wr.write("else", 2, true);
            wr.write("return " + falseBlock.javaVal() + ";", 3, true);
        }
        else
        {
            wr.write("return 0.0;", 2, true);
        }
        
        wr.write("}", 1, true);
        wr.write("", 0, true);
    }
}
