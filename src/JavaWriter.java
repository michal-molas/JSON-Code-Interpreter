import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class JavaWriter {
    private final Writer wr;
    private String className;
    
    public JavaWriter(String filename) throws IOException {
        if (filename == null)
            throw new IOException("No java file.");
        
        File file = new File(filename);
        className = file.getName();
        
        if (!className.endsWith(".java"))
            throw new IOException("Wrong java file extension.");
        
        className = className.substring(0, className.length() - 5);
        
        wr = new FileWriter(file);
    }
    
    public String getClassName() {
        return className;
    }
    
    private void writeTabs(int tabs) throws IOException {
        for (int i = 0; i < tabs; i++)
            wr.write("    ");
    }

    public void write(String s, int tabs, boolean new_line) throws IOException {
        writeTabs(tabs);
        wr.write(s);
        if (new_line)
            wr.write("\n");
    }
    
    public void flush() throws IOException {
        wr.flush();
    }
    
    public void close() throws IOException {
        wr.close();
    }
}
