package hexreadback;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class FileManipulation {

    private final Path filePath;
    private OutputClass outputClass;

    private FileManipulation() {
        this.filePath = Path.of("Test");
        this.outputClass = OutputClass.Text;
    }

    public FileManipulation(Path filePath, OutputClass outputClass) {
        this.filePath = filePath;
        this.outputClass = outputClass;


    }

    public void changeOutputClass(OutputClass outputClass) {
        this.outputClass = outputClass;
    }

    /**
     * Reads all bytes from the file specified by the internal filePath field.
     *
     * @return a byte array containing the raw data read from the file.
     * @throws IOException if an error occurs while reading the file.
     */
    private byte[] loadFileBytes() throws IOException {
        return Files.readAllBytes(filePath);
    }

    /**
     * Reads all bytes from the file and converts each byte into its hexadecimal string representation.
     * Each byte is interpreted as an unsigned 8-bit value and formatted into a 2-character hexadecimal string.
     *
     * @return an array of strings representing the hexadecimal values of the file's bytes.
     * @throws IOException if an error occurs while reading the file.
     */
    private String[] loadFileHex() throws IOException {
        byte[] bytes = loadFileBytes();
        String[] hex = new String[bytes.length];

        for (int i = 0; i < bytes.length; i++) {
            hex[i] = String.format("%02X", bytes[i] & 0xFF);
        }

        return hex;
    }

    /**
     * Reads all bytes from the file and converts each byte into its binary string representation.
     * Each byte is interpreted as an unsigned 8-bit value and formatted into an 8-character binary string.
     *
     * @return an array of strings representing the binary values of the file's bytes.
     * @throws IOException if an error occurs while reading the file.
     */
    private String[] loadFileBinary() throws IOException {
        byte[] bytes = loadFileBytes();
        String[] binary = new String[bytes.length];

        for (int i = 0; i < bytes.length; i++) {
            binary[i] = String.format("%8s",
                            Integer.toBinaryString(bytes[i] & 0xFF))
                    .replace(' ', '0');
        }

        return binary;
    }


    /**
     * Reads all bytes from the file and converts each byte into its octal string representation.
     * Each byte is interpreted as an unsigned 8-bit value and formatted into a 3-digit octal string.
     *
     * @return an array of strings representing the octal values of the file's bytes.
     * @throws IOException if an error occurs while reading the file.
     */
    private String[] loadFileOctal() throws IOException {
        byte[] bytes = loadFileBytes();
        String[] octal = new String[bytes.length];

        for (int i = 0; i < bytes.length; i++) {
            octal[i] = String.format("%03o", bytes[i] & 0xFF);
        }

        return octal;
    }

    /**
     * Reads all bytes from the file and converts each byte into its decimal string representation.
     * Each byte is interpreted as an unsigned 8-bit value and converted into a corresponding String.
     *
     * @return an array of strings representing the decimal values of the file's bytes.
     * @throws IOException if an error occurs while reading the file.
     */
    private String[] loadFileDecimal() throws IOException {
        byte[] bytes = loadFileBytes();
        String[] decimal = new String[bytes.length];

        for (int i = 0; i < bytes.length; i++) {
            decimal[i] = Integer.toString(bytes[i] & 0xFF);
        }

        return decimal;
    }

    /**
     * Retrieves the content of the file represented by the specified filePath
     * and formats it according to the specified outputClass.
     * The format includes Hexadecimal, Decimal, Binary, Octal, or Text representation.
     * In case of any I/O error, an error message containing the exception details is returned.
     *
     * @return a formatted string representation of the file content based on the specified outputClass,
     * or an error message in case of an exception.
     */
    public String getFileValue() {

        try {
            switch (outputClass) {

                case Hexadecimal -> {
                    return Arrays.toString(loadFileHex()).replace("[", "").replace("]", "").replace(",", "");
                }

                case Decimal -> {
                    return Arrays.toString(loadFileDecimal());
                }

                case Octal -> {
                    return Arrays.toString(loadFileOctal());
                }

                case Binary -> {
                    return Arrays.toString(loadFileBinary());
                }

                case Text -> {
                    return new String(loadFileBytes());
                }

            }

        } catch (IOException e) {
            return "Error reading file: " + e.getMessage();
        }
        return "";
    }
}