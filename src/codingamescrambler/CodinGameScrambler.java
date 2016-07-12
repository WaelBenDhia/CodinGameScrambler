/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingamescrambler;

import java.util.ArrayList;
import java.util.List;

/**
 * op
 *
 * @author waelb
 */
class Unscrambler {

    public static String unscramble(String scrambled) {
        String unscrambled;
        StringBuilder unscrambledSB;

        //STEP 4: Count the number of letters in each word and reverse that list of numbers and reapply to the sentence
        String[] split = scrambled.split(" ");
        unscrambledSB = new StringBuilder(scrambled.replace(" ", ""));
        int length = 0;
        for (int i = split.length - 1; i >= 0; i--) {
            length += split[i].length();
            unscrambledSB.insert(length, " ");
            length++;
        }
        unscrambled = unscrambledSB.toString();

        //STEP 3: Find every fourth letter of the alphabet and shift them right, with the last wrapping around to the first position;
        List<Character> chars = new ArrayList<>();
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < unscrambled.length(); i++) {
            char c = unscrambled.charAt(i);
            if ((c + 1 - 'A') % 4 == 0 && c != ' ') {
                chars.add(c);
                indexes.add(i);
            }
        }
        for (int i = 0; i < chars.size(); i++) {
            int prev = i - 1;
            if (i == 0) {
                prev = chars.size() - 1;
            }
            unscrambledSB.deleteCharAt(indexes.get(i));
            unscrambledSB.insert(indexes.get(i).intValue(), chars.get(prev));
        }
        unscrambled = unscrambledSB.toString();

        //STEP 2: Find every third letter of the alphabet and shift them left, with the first wrapping around to the last position;
        chars.clear();
        indexes.clear();
        for (int i = 0; i < unscrambled.length(); i++) {
            char c = unscrambled.charAt(i);
            if ((c + 1 - 'A') % 3 == 0 && c != ' ') {
                chars.add(c);
                indexes.add(i);
            }
        }
        for (int i = 0; i < chars.size(); i++) {
            int next = (i + 1) % (chars.size());
            unscrambledSB.deleteCharAt(indexes.get(i));
            unscrambledSB.insert(indexes.get(i).intValue(), chars.get(next));
        }
        unscrambled = unscrambledSB.toString();

        //STEP 1: Find every second letter of the alphabet and reverse their order
        chars.clear();
        indexes.clear();
        for (int i = 0; i < unscrambled.length(); i++) {
            char c = unscrambled.charAt(i);
            if ((c + 1 - 'A') % 2 == 0 && c != ' ') {
                chars.add(c);
                indexes.add(i);
            }
        }
        for (int i = 0; i < chars.size(); i++) {
            int reverse = chars.size() - 1 - i;
            unscrambledSB.deleteCharAt(indexes.get(i));
            unscrambledSB.insert(indexes.get(i).intValue(), chars.get(reverse));
        }
        unscrambled = unscrambledSB.toString();
        
        return unscrambled.trim();
    }
}

public class CodinGameScrambler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(Unscrambler.unscramble("MLSOHYTA RMLESS"));
    }

}
