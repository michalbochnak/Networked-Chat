package ClientModel;
import java.math.BigInteger;
import java.util.ArrayList;


public class RSAModel{

    private ArrayList<Integer> message;

    public RSAModel() {
        this.message = new ArrayList<>();
    }


    //encode message using RSA
    public ArrayList encrypt(String message, int e, int n) {

        String msg = stringPadding(message);
        ArrayList<Integer> asciiValue = convertToAscii(msg);
        ArrayList<Integer> fourCharacters = calculateFourChars(asciiValue);
        ArrayList<Integer> encrypted = encodeMsg(fourCharacters, e, n);

        return encrypted;
    }

    public String decrypt(ArrayList<Integer> array, int d, int n) {

        ArrayList<Integer> fourChars = decryptFourChars(array, d, n);
        String msg = unconvertedFourChars(fourChars);

        return msg;
    }

    //changes array of ascii values into string
    private String unconvertedFourChars(ArrayList<Integer> fourChars) {

        String message = "";

        for (int i = 0; i < fourChars.size(); i++) {
            double number = fourChars.get(i);
            double first = number % 128;
            message += (char) first;
            double second = ((number - first) % Math.pow(128, 2)) / 128;
            message += (char) second;
            double third = ((number - second) % Math.pow(128, 3)) / Math.pow(128, 2);
            message += (char) third;

        }
        return message;
    }

    //decrypts encrypted message using rsa return array with sum of ascii values
    private ArrayList<Integer> decryptFourChars(ArrayList<Integer> array, int d, int n) {

        ArrayList<Integer> decrypted = new ArrayList<>();
        BigInteger N = new BigInteger(Integer.toString(n));

        for (int i = 0; i < array.size(); i++) {

            BigInteger C = new BigInteger(array.get(i).toString());
            BigInteger M1 = C.pow(d);
            BigInteger M = M1.mod(N);
            decrypted.add(M.intValue());
        }

        return decrypted;
    }


    //encodes array with converted ascii using RSA
    private ArrayList<Integer> encodeMsg(ArrayList<Integer> fourCharacters, int e, int n) {
        ArrayList<Integer> encrypted = new ArrayList<>();

        BigInteger N = new BigInteger(Integer.toString(n));
        BigInteger E = new BigInteger(Integer.toString(e));

        for (int i = 0; i < fourCharacters.size(); i++) {
            int num = fourCharacters.get(i);
            BigInteger MSG = new BigInteger(Integer.toString(num));
            BigInteger C1 = MSG.pow(11);
            BigInteger C = C1.mod(N);
            encrypted.add(C.intValue());
        }

        return encrypted;
    }


    //checks if string is evenly divided by 3 if not adds '\0' at the end
    private String stringPadding(String msg) {

        //make sure msg length is multiple of 3
        while (msg.length() % 3 != 0) {
            msg = msg + '\0';
        }

        return msg;
    }

    //convert four characters to single value using "ascii value" * 128^(pos)
    private ArrayList<Integer> calculateFourChars(ArrayList<Integer> asciiValue) {
        int value = 0;
        ArrayList<Integer> converted = new ArrayList<>();
        for (int i = 0; i < asciiValue.size(); i++) {
            value += asciiValue.get(i) * Math.pow(128, power(i));
            //4 for 4
            if ((i + 1) % 3 == 0) {
                converted.add(value);
                value = 0;
            }
        }

        return converted;
    }

    //convert string to the ascii value
    private ArrayList<Integer> convertToAscii(String msg) {
        ArrayList<Integer> ascii = new ArrayList<>();
        int character;
        for (int i = 0; i < msg.length(); i++) {
            character = msg.charAt(i);
            ascii.add(character);
        }
        return ascii;
    }


    //calculate power of index
    private int power(int num) {
        int power = num % 3;
        return power;
    }


}


