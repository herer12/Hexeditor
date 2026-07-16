package fileFormatAnylazer;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public final class PNG extends FileFormat {
    private static final String name = "Portable Network Graphics";
    private static final String extension = ".png";

    private final List<Abschnitt> abschnittList = new LinkedList<>();

    public PNG() {
        super(name, extension);
        try (Scanner scanner = new Scanner(new File("FileFormats/PNG"))) {
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

    @Override
    public boolean isThisFormat(byte[] data) {
        for (int i = abschnittList.getFirst().getStartChunk(); i <= abschnittList.getFirst().getEndChunk(); i++) {
            if (data[i] != abschnittList.getFirst().getData()[i]) return false;
        }
        return true;

    }

    @Override
    public List<Abschnitt> analyze(byte[] data) {
        return null;
    }
}
