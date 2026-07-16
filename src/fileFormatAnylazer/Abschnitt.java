package fileFormatAnylazer;

import java.util.Objects;

public class Abschnitt {
    private final String name;
    private int startChunk;
    private int endChunk;
    private byte[] data;

    public Abschnitt(String name, int startChunk, int endChunk, String hex) {
        this.name = name;
        this.startChunk = startChunk;
        this.endChunk = endChunk;

        if (hex.isEmpty()) {
            this.data = new byte[0];
            return;
        }

        data = hexToBytes(hex);
    }

    public static byte[] hexToBytes(String str) {
        String[] parts = str.trim().split("\\s+");
        byte[] data = new byte[parts.length];

        for (int i = 0; i < parts.length; i++) {
            if (Objects.equals(parts[i], "*")) {
                data[i] = 0;
                continue;
            }
            data[i] = (byte) Integer.parseInt(parts[i], 16);
        }
        return data;
    }

    public String getName() {
        return name;
    }

    public void setStartChunk(int startChunk) {
        this.startChunk = startChunk;
        setEndChunk(startChunk + data.length);
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

    public void addData(byte[] data) {
        this.data = data;
    }

    public static String bytesToHex(byte[] bytes) {
        String[] hex = new String[bytes.length];

        for (int i = 0; i < bytes.length; i++) {

            hex[i] = String.format("%02X", bytes[i] & 0xFF);
        }
        return String.join(" ", hex);
    }

    private void setEndChunk(int endChunk) {
        this.endChunk = endChunk;
    }

    @Override
    public String toString() {
        return "Abschnitt{" +
                "name='" + name + '\'' +
                ", startChunk=" + startChunk +
                ", endChunk=" + endChunk +
                ", data=" + bytesToHex(data) +
                '}';
    }
}
