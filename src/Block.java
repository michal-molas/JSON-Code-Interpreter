import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Block extends Instruction {
    private final List<Instruction> insts;
    
    private int idx;
    
    public Block() {
        // in case of no parameter the block is empty
        insts = new ArrayList<>();
    }
    
    @Override
    public double value(Map<String, Num> variables) throws ExecutionException {
        if (insts.size() == 0)
            return 0.0;
        else
            return insts.get(insts.size() - 1).value(variables);
    }
    
    @Override
    public void execute(Map<String, Num> variables) throws ExecutionException {
        for (Instruction inst : insts)
            inst.execute(variables);
    }

    @Override
    protected String javaVal() {
        return "block" + idx + "()";
    }

    public String javaValBracket() {
        return javaVal();
    }

    @Override
    public void collectVars(Set<String> variables) {
        for (Instruction inst : insts)
            inst.collectVars(variables);
    }

    @Override
    public void checkCorrectJSON() throws IncorrectProgram {
        for (Instruction inst : insts)
            if (inst == null)
                throw new IncorrectProgram();

        for (Instruction inst : insts)
            inst.checkCorrectJSON();
    }
    
    @Override
    public void printFunc(JavaWriter wr) throws IOException {
        
        for (Instruction inst : insts)
            inst.printFunc(wr);

        idx = func_idx;
        func_idx++;
        wr.write("private static double block" + idx + "() {", 1, true);

        if (insts.size() == 0) {
            wr.write("return 0.0;", 2, true);
        }
        else {
            for (int i = 0; i < insts.size() - 1; i++)
                insts.get(i).writeToJava(wr, 2);

            wr.write("return " + insts.get(insts.size() - 1).javaVal() + ";", 2, true);
        }

        wr.write("}", 1, true);
        wr.write("", 0, true);
    }
}
