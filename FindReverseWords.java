package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/*
Find reverse words in java
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileBufReader = new BufferedReader(new FileReader(bufferedReader.readLine()));
        StringBuilder stringBuilder = new StringBuilder();
        String nextLine;
        while ((nextLine = fileBufReader.readLine()) != null) {
            stringBuilder.append(nextLine);
        }
        while (stringBuilder.toString().contains("\uFEFF")) {
            stringBuilder.deleteCharAt(stringBuilder.toString().indexOf("\uFEFF"));
        }
        fileBufReader.close();
        bufferedReader.close();
        String[] strings1 = new String(stringBuilder).split(" ");
        /*for (String string :strings1) {
            System.out.println(string);
        }*/
        System.out.println(strings1);

        for (int i = 0; i < strings1.length - 1; i++) {
            for (int j = i + 1; j < strings1.length; j++) {
                String s1Org = strings1[i];
                String s1 = new StringBuilder(s1Org).reverse().toString();
                String s2 = strings1[j];
                Pair pair = new Pair(s1Org, s2);

                if (s1.equals(s2) && !result.contains(pair) && !pair.equals(new Pair("", ""))) result.add(pair);

            }
        }
       /* for (Pair pair :result) {
            System.out.println(pair);
        }*/
        System.out.println(result);

    }

    public static class Pair {
        String first;
        String second;

        public Pair(String first, String second) {
            this.first = first;
            this.second = second;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;
            Pair pair = (Pair) o;
            return (Objects.equals(first, pair.first) &&
                    Objects.equals(second, pair.second)) || (Objects.equals(first, pair.second) && Objects.equals(second, pair.first));
        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null && second != null ? second :
                            second == null && first != null ? first :
                                    first + " " + second;

        }
    }

}
