import java.io.File;

public class Test {
    public static void main(String[] args) {
        // here you should put a name of .json file (without any extension or path)
        String filename = "Pythagoras";

        JsonInterpreter jsonInter = new JsonInterpreter("Programs/" + filename + ".json");

        File folderJava = new File("ProgramsJava");
        if (!folderJava.exists())
            folderJava.mkdir();
        
        jsonInter.toJava("ProgramsJava/" + filename + ".java");

        try {
            System.out.println(jsonInter.execute());
        }
        catch (ExecutionException e) {
            e.printErrorMsg();
            System.exit(1);
        }

        File folderJson = new File("CreatedPrograms");
        if (!folderJson.exists())
            folderJson.mkdir();
        
        jsonInter.toJSON("CreatedPrograms/" + filename + "_copy.json");
    }
}
