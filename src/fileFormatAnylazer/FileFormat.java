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

    public abstract List<Abschnitt> analyze(byte[] data);


}