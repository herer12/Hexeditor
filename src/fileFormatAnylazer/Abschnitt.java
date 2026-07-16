package fileFormatAnylazer;

public class Abschnitt {
    private final String name;
    private final int startChunk;
    private final int endChunk;
    private final byte[] data;

    public Abschnitt(String name, int startChunk, int endChunk, String hex) {
        this.name = name;
        this.startChunk = startChunk;
        this.endChunk = endChunk;

        if (hex.isEmpty()) {
            this.data = new byte[0];
            return;
        }

        char[] chars = hex.toCharArray();
        data = new byte[chars.length];
        for (int i = 0; i < chars.length; i++) {
            data[i] = (byte) chars[i];
        }
    }

    public int getStartChunk() {
        return startChunk;
    }

    public int getEndChunk() {
        return endChunk;
    }

    public byte[] getData() {
        return data;
    }
}
