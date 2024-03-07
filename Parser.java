public class Parser {
    // takes in a character, returns its index in the reference table
    public static int charToRefIndex(char c) {
        int i = (int)c;
        int diff;
        if (i >= 40 && i <= 47) {
            // ()*+,-./
            diff = 4;
        } else if (i >= 48 && i <= 57) {
            // 0-9
            diff = 22;
        } else if (i >= 65 && i <= 90) {
            // A-Z
            diff = 65;
        } else {
            // not in reference table
            return -1;
        }
        int refIndex = i - diff;
        return refIndex;
    }

    // takes in an index in the reference table, returns the corresponding character
    public static char refIndexToChar(int i) {
        int diff;
        if (i <= 25) {
            // A-Z
            diff = 65;
        } else if (i >= 36) {
            // ()*+,-./
            diff = 4;
        } else {
            // 0-9
            diff = 22;
        }
        int ascii = i + diff;
        return (char)ascii;
    }

    // extracts the offset character from encoded text
    public static int getOffset(String text) {
        char offsetChar = text.charAt(0);
        int offset = charToRefIndex(offsetChar);
        return offset;
    }
}
