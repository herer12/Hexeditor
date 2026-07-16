import fileFormatAnylazer.FileDecider;
import hexreadback.FileManipulation;
import hexreadback.OutputClass;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Path path = Path.of("Test2.jpg");
        OutputClass outputClass = OutputClass.Hexadecimal;
        FileManipulation fileManipulation = new FileManipulation(path, outputClass);
        String file = fileManipulation.getFileValue();
        PrintWriter printWriter = new PrintWriter("oute");
        printWriter.println(file);
        printWriter.close();
        FileDecider fileDecider = new FileDecider();

        System.out.println(fileDecider.decideFileFormat(file));
    }
}