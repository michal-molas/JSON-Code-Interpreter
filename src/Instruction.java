import java.io.IOException;
import java.util.Map;
import java.util.Set;

public abstract class Instruction {
    protected static int func_idx = 0;
    
    public abstract double value(Map<String, Num> variables) throws ExecutionException;
    
    public abstract void execute(Map<String, Num> variables) throws ExecutionException;
    
    public boolean logicVal(Map<String, Num> variables) throws ExecutionException {
        return value(variables) != 0.0;
    }
    
    public void writeToJava(JavaWriter wr, int tabs) throws IOException {
        wr.write( javaVal() + ";", tabs, true);
    }
    
    protected void writeToJavaForce(JavaWriter wr, int tabs) throws IOException {
        writeToJava(wr, tabs);
    }
    
    protected abstract String javaVal();
    
    public String javaValBracket() {
        return "(" + javaVal() + ")";
    }
    
    public abstract void collectVars(Set<String> variables);
    
    public void checkExceptions(Map<String, Num> variables) throws ExecutionException {}
    
    public abstract void checkCorrectJSON() throws IncorrectProgram;
    
    public void printFunc(JavaWriter wr) throws IOException {}
}
