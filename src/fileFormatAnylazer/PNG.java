package fileFormatAnylazer;

import java.util.LinkedList;
import java.util.List;

public final class PNG extends FileFormat {
    private static final String NAME = "PNG";
    private static final String EXTENSION = ".png";
    private static final String DESCRIPTION = "Portable Network Graphics";

    public PNG() {
        super(NAME, EXTENSION, DESCRIPTION);
    }

    @Override
    public boolean isThisFormat(byte[] data) {
        return super.isThisFormat(data);
    }

    @Override
    public List<Abschnitt> analyze(byte[] data) {
        LinkedList<Abschnitt> abschnittInThisData = new LinkedList<>();
        for (Abschnitt abschnitt : abschnittList) {
            if (data.length > abschnitt.getEndChunk()) {
                boolean isThisAbschnitt = true;
                for (int i = abschnittList.getFirst().getStartChunk(); i < abschnittList.getFirst().getEndChunk(); i++) {
                    if (data[i] != abschnittList.getFirst().getData()[i]) isThisAbschnitt = false;
                }
                if (isThisAbschnitt) abschnittInThisData.add(abschnitt);
            }
        }
        return abschnittInThisData;
    }
}
