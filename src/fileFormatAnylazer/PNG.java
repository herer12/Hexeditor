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
            if (!Abschnitt.bytesToHex(abschnitt.getData()).equals("89 50 4E 47 0D 0A 1A 0A")) {
                byte[] dataToSearch = abschnitt.getData();
                abschnitt.setStartChunk(getStartChunk(data, dataToSearch));
                if (abschnitt.getStartChunk() < 8) continue;
                byte[] lenghtData = new byte[4];
                System.arraycopy(data, abschnitt.getStartChunk() - 4, lenghtData, 0, lenghtData.length);
                Abschnitt lenghtAbschnitt = new Abschnitt(abschnitt.getName() + " Lenght", abschnitt.getStartChunk() - 4, abschnitt.getStartChunk() + dataToSearch.length - 4, Abschnitt.bytesToHex(lenghtData));
                Abschnitt identifierAbschnitt = abschnitt;

                byte[] dataChunk = new byte[getDataLength(lenghtData)];
                System.arraycopy(data, abschnitt.getStartChunk(), dataChunk, 0, dataChunk.length);
                Abschnitt dataAbschnitt = new Abschnitt(abschnitt.getName() + " Data", abschnitt.getEndChunk(), abschnitt.getEndChunk() + getDataLength(lenghtData), Abschnitt.bytesToHex(dataChunk));

                byte[] crcData = new byte[4];
                System.arraycopy(data, dataAbschnitt.getEndChunk(), crcData, 0, crcData.length);
                Abschnitt crcAbschnitt = new Abschnitt(abschnitt.getName() + " CRC", dataAbschnitt.getEndChunk(), dataAbschnitt.getEndChunk() + 4, Abschnitt.bytesToHex(crcData));

                abschnittInThisData.add(lenghtAbschnitt);
                abschnittInThisData.add(identifierAbschnitt);
                abschnittInThisData.add(dataAbschnitt);
                abschnittInThisData.add(crcAbschnitt);
            } else {
                abschnittInThisData.add(abschnitt);
            }



        }
        return abschnittInThisData;
    }

    private int getStartChunk(byte[] data, byte[] dataToSearch) {
        int counter = 0;
        int startChunk = 0;
        for (int i = 0; i < data.length; i++) {
            if (counter == dataToSearch.length) {
                startChunk = i - counter;
                break;
            }
            if (data[i] == dataToSearch[counter]) counter++;
            else counter = 0;
        }
        return startChunk;
    }

    private int getDataLength(byte[] data) {
        String hex = Abschnitt.bytesToHex(data);
        hex = hex.replace(" ", "");
        return Integer.parseInt(hex, 16);
    }
}


