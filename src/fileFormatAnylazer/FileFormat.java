package fileFormatAnylazer;

import java.util.List;

public abstract class FileFormat {

    private final String name;
    private final String extension;

    protected FileFormat(String name, String extension) {
        this.name = name;
        this.extension = extension;
    }

    public String getName() {
        return name;
    }

    public String getExtension() {
        return extension;
    }

    public abstract boolean isThisFormat(byte[] data);

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