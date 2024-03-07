import java.util.Random;

public class Encoder {
    private Random random;

    public Encoder() {
        this.random = new Random();
    }

    public char encodeChar(char c, int offset) {
        int refIndex = Parser.charToRefIndex(c);
        
        // check if character is in reference table
        if (refIndex == -1) {
            return c;
        }

        // shift character
        int newRefIndex = refIndex - offset;

        // wrap around
        if (newRefIndex < 0) {
            newRefIndex += 44;
        }
        return Parser.refIndexToChar(newRefIndex);
    }

    public char decodeChar(char c, int offset) {
        int refIndex = Parser.charToRefIndex(c);
        
        // check if character is in reference table
        if (refIndex == -1) {
            return c;
        }

        // shift character
        int newRefIndex = refIndex + offset;

        // wraparound
        if (newRefIndex  >43) {
            newRefIndex -= 44;
        }
        return Parser.refIndexToChar(newRefIndex);
    }

    public String encode(String plainText) {
        // choose random offset character
        int offset = random.nextInt(44);

        // generate encoded text
        char offsetChar = Parser.refIndexToChar(offset);
        String encodedText = "" + offsetChar;
        for (int i = 0; i < plainText.length(); i++) {
            char curr = plainText.charAt(i);
            encodedText += encodeChar(curr, offset);
        }
        return encodedText;
    }

    public String decode(String encodedText) {
        // identify offset 
        int offset = Parser.getOffset(encodedText);

        // generate plain text
        String plainText = "";
        for (int i = 1; i < encodedText.length(); i++) {
            char curr = encodedText.charAt(i);
            plainText += decodeChar(curr, offset);
        }
        return plainText;
    }
}
