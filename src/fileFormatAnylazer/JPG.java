package fileFormatAnylazer;

import java.util.LinkedList;
import java.util.List;

public final class JPG extends FileFormat {
    private static final String NAME = "JPG";
    private static final String EXTENSION = ".jpg";
    private static final String DESCRIPTION = "Joint Photographic Experts Group";

    public JPG() {
        super(NAME, EXTENSION, DESCRIPTION);
    }

    @Override
    public boolean isThisFormat(byte[] data) {
        return super.isThisFormat(data);
    }

    @Override
    public List<Abschnitt> analyze(byte[] data) {
        if (!isThisFormat(data)) return null;

        LinkedList<Abschnitt> abschnittInThisData = new LinkedList<>();

        String hex = Abschnitt.bytesToHex(data);

        hex = hex.replaceFirst("FF ", "");

        String[] parts = hex.split("FF ");

        for (String part : parts) {
            if (!part.startsWith("FF")) {
                String markerIdentifer = part.substring(0, 2);
                String payload = part.substring(2);

                for (Abschnitt abschnitt : abschnittList) {
                    if (("FF " + markerIdentifer).equals(Abschnitt.bytesToHex(abschnitt.getData()))) {
                        abschnittInThisData.add(abschnitt);
                        abschnittInThisData.getLast().addData(Abschnitt.hexToBytes("FF" + markerIdentifer + payload));
                    }
                }
            }
        }

        return abschnittInThisData;
    }
}
