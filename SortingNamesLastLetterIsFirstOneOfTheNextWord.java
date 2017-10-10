
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/*
Make the chain of words
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReaderConsole = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader bufferedReaderFile = new BufferedReader(new FileReader(bufferedReaderConsole.readLine()));
        StringBuilder inputStringFromFile = new StringBuilder();
        String sTemp;
        while ((sTemp = bufferedReaderFile.readLine()) != null) {
            inputStringFromFile.append(sTemp);
        }
        String[] words = inputStringFromFile.toString().split(" ");

        StringBuilder result = getLine(words);

        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        if (words.length == 0) return new StringBuilder();


        ArrayList<String> al = new ArrayList<>();
        Collections.addAll(al, words);
        Collections.sort(al);

       /* for (int i = 0; i < al.size(); i++) {
            System.out.println(al.get(i));
        }*/
        //   System.out.println(al.get(0));
        StringBuilder sb = new StringBuilder();
        sb.append(al.get(0));

        ArrayList<String> alreadyInResult = new ArrayList<>();
        alreadyInResult.add(al.remove(0));

        while (al.size() > 0) {

            Iterator<String> iter = al.iterator();

            while (iter.hasNext()) {

                String a = sb.toString();
                String b = iter.next();

                if (alreadyInResult.contains(b)) {
                    iter.remove();
                    continue;
                }

                String aForCompar = a.toLowerCase();
                String bForCompar = b.toLowerCase();

                if (a.length() == 0 || b.length() == 0) continue;

                // to the beginning
                if (aForCompar.charAt(0) == bForCompar.charAt(bForCompar.length() - 1)) {
                    sb.insert(0, " ");
                    sb.insert(0, b);
                    alreadyInResult.add(b);
                    iter.remove();
                }
                // to the end
                if (aForCompar.charAt(aForCompar.length() - 1) == bForCompar.charAt(0)) {
                    sb.append(" ").append(b);
                    alreadyInResult.add(b);
                    iter.remove();
                }

            }
        }
        return sb;
    }
}
