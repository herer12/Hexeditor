package fileFormatAnylazer;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public abstract class FileFormat {

    protected final String name;
    protected final String description;
    protected final String extension;
    protected final List<Abschnitt> abschnittList = new LinkedList<>();

    protected FileFormat(String name, String description, String extension) {
        this.name = name;
        this.description = description;
        this.extension = extension;

        try (Scanner scanner = new Scanner(new File("FileFormats/" + name))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] split = line.split(", ");
                abschnittList.add(new Abschnitt(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2]), split[3]));
            }

        } catch (Exception e) {
            System.out.println("Error: 205");
            throw new RuntimeException(e);
        }
    }

    public String getName() {
        return name;
    }

    public String getExtension() {
        return extension;
    }

    public boolean isThisFormat(byte[] data) {
        for (int i = abschnittList.getFirst().getStartChunk(); i < abschnittList.getFirst().getEndChunk(); i++) {
            byte b = abschnittList.getFirst().getData()[i];
            if (data[i] != b) return false;
        }
        return true;
    }

    /**
     * Analyzes the given binary data and identifies meaningful sections within it.
     * This method should provide a detailed breakdown of the data into specific segments
     * based on the file format's structure and specifications.
     *
     * @param data the binary data to be analyzed, typically representing the contents of a file.
     * @return a list of {@code Abschnitt} objects, each representing a section of the analyzed data.
     * The sections provide details such as the name, starting point, ending point,
     * and the raw data of that segment.
     */
    public abstract List<Abschnitt> analyze(byte[] data);


}