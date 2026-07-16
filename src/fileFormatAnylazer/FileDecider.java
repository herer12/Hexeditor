package fileFormatAnylazer;

import java.io.File;
import java.util.LinkedList;

public class FileDecider {
    private final LinkedList<FileFormat> fileFormat = new LinkedList<>();

    public FileDecider() {
        fileFormat.add(new PNG());
    }

    public FileFormat decideFileFormat(File file) {
        String fileName = file.getName();
        String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
        for (FileFormat fileFormat : fileFormat) {
            if (fileFormat.equals(extension.toUpperCase())) return fileFormat;
        }
        return null;
    }

    public boolean decideFileFormat(String data) {
        byte[] bytes = data.getBytes();
        for (FileFormat fileFormat : fileFormat) {
            return fileFormat.isThisFormat(bytes);
        }
        return false;
    }
}
