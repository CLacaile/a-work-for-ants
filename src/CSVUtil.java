import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class CSVUtil {
    /**
     *
     * This class is dedicated to the import/exports from CSV methods in Company
     *
     **/
    private static final char DEFAULT_SEPARATOR = ',';

    /**
     * This method is used to write a line
     * @param w writer
     * @param values values to write
     * @return void
     */
    public static void writeLine(Writer w, List<String> values) throws IOException {
        writeLine(w, values, DEFAULT_SEPARATOR, ' ');
    }

    /**
     * This method is used to write a line with a separator
     * @param w writer
     * @param values values to write
     * @param separators separators
     * @return void
     */
    public static void writeLine(Writer w, List<String> values, char separators) throws IOException {
        writeLine(w, values, separators, ' ');
    }
    /**
     * This method is used to stick to the CSV format
     * @param value string to check
     * @return String string correctly formated
     */
    private static String followCVSformat(String value) {

        String result = value;
        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"");
        }
        return result;

    }

    /**
     * This method is used to write a line with a separator and custom quote
     * @param w writer
     * @param values values to write
     * @param separators separators
     * @param customQuote custom quote
     * @return void
     */
    public static void writeLine(Writer w, List<String> values, char separators, char customQuote) throws IOException {

        boolean first = true;

        //default customQuote is empty

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            if (!first) {
                sb.append(separators);
            }
            if (customQuote == ' ') {
                sb.append(followCVSformat(value));
            } else {
                sb.append(customQuote).append(followCVSformat(value)).append(customQuote);
            }

            first = false;
        }
        sb.append("\n");
        w.append(sb.toString());
    }
}
