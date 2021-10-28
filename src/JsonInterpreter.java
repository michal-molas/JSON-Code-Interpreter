import com.squareup.moshi.Moshi;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class JsonInterpreter {
    private final JsonAdapter<Instruction> jsonAdapter;
    private Instruction program;
    
    public JsonInterpreter(String filename) {
        Moshi moshi = new Moshi.Builder()
                .add(
                        PolymorphicJsonAdapterFactory.of(Instruction.class, "type")
                                .withSubtype(Block.class, "Block")
                                .withSubtype(And.class, "And")
                                .withSubtype(Div.class, "Div")
                                .withSubtype(False.class, "False")
                                .withSubtype(If.class, "If")
                                .withSubtype(Num.class, "Num")
                                .withSubtype(Sub.class, "Sub")
                                .withSubtype(Less.class, "<")
                                .withSubtype(LessEq.class, "<=")
                                .withSubtype(Not.class, "Not")
                                .withSubtype(Or.class, "Or")
                                .withSubtype(Add.class, "Add")
                                .withSubtype(Assign.class, "Assign")
                                .withSubtype(Mul.class, "Mul")
                                .withSubtype(Eq.class, "==")
                                .withSubtype(True.class, "True")
                                .withSubtype(While.class, "While")
                                .withSubtype(Greater.class, ">")
                                .withSubtype(GreaterEq.class, ">=")
                                .withSubtype(Var.class, "Var")
                )
                .build();

        jsonAdapter = moshi.adapter(Instruction.class);
        
        try {
            program = fromJSON(filename);
            program.checkCorrectJSON();
        }
        catch (IncorrectProgram e) {
            e.printMsg();
            System.exit(1);   
        }
    }
    
    public Instruction fromJSON(String filename) throws IncorrectProgram {
        String json = null;
        try {
            json = Files.readString(Paths.get(filename));
        }
        catch (IOException e) {
            System.err.println("No file " + e.getMessage() + ".");
            System.exit(1);
        }
        
        Instruction program;
        try {
            program = jsonAdapter.fromJson(json);
        }
        catch (IOException e) {
            throw new IncorrectProgram();
        }
        
        return program;
    }

    public void toJSON(String filename) {
        try {
            Files.write(Paths.get(filename), jsonAdapter.indent("  ").toJson(program).getBytes());
        }
        catch (IOException e) {
            System.err.println("Exception in creating file " + filename + ".");
        }
    }

    // https://stackoverflow.com/a/15437952
    private boolean isCorrectName(String s) {
        if (s.isEmpty())
            return false;

        if (!Character.isJavaIdentifierStart(s.charAt(0)))
            return false;

        for (int i = 1; i < s.length(); i++)
            if (!Character.isJavaIdentifierPart(s.charAt(i)))
                return false;

        return true;
    }

    public void toJava(String filename) {
        JavaWriter wr;
        try {
            wr = new JavaWriter(filename);

            try {
                Set<String> zmienne = new HashSet<>();
                program.collectVars(zmienne);
                for (String z : zmienne){
                    if (!isCorrectName(z)) {
                        System.err.println("Incorrect variable name: \"" + z + "\"");
                        return;
                    }
                }

                wr.write("public class " + wr.getClassName() + " {", 0, true);

                for (String zmienna : zmienne)
                    wr.write("private static double " + zmienna + " = 0.0;", 1, true);
                wr.write("",1, true);

                program.printFunc(wr);

                wr.write("public static void main(String[] args) {", 1, true);
                wr.write("System.out.println(" + program.javaVal() + ");", 2, true);
                wr.write("}", 1, true);
                wr.write("}", 0, false);

                wr.flush();
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
            finally {
                wr.close();
            }
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public double execute() throws ExecutionException {
        Map<String, Num> vars = new HashMap<>();
        program.execute(vars);
        return program.value(vars);
    }
}
