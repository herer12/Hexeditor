package fileFormatAnylazer;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class FileDecider {
    private final LinkedList<FileFormat> formats = new LinkedList<>();

    public FileDecider() {
        formats.add(new PNG());
    }

    public FileFormat decideFileFormat(File file) {
        String fileName = file.getName();
        String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
        for (FileFormat fileFormat : formats) {
            if (fileFormat.equals(extension.toUpperCase())) return fileFormat;
        }
        return null;
    }

    public boolean decideFileFormat(String data) {
        byte[] bytes = Abschnitt.hexToBytes(data);
        for (FileFormat format : formats) {
            if (format.isThisFormat(bytes)) {
                return true;
            }
        }
        return false;
    }

    public List<Abschnitt> getAbschnitte(String data) {
        if (!decideFileFormat(data)) return null;
        byte[] bytes = Abschnitt.hexToBytes(data);
        for (FileFormat format : formats) {
            if (format.isThisFormat(bytes)) {
                return format.analyze(bytes);
            }
        }
        return null;
    }
}
