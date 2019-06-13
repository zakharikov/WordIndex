package org.zakharikov;

import jdk.nashorn.internal.ir.WhileNode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class WordIndex {
    private String text;
    private Trie trie;
    public void loadFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        text = br.lines().collect(Collectors.joining(" "));
        br.close();
        trie = new Trie();
        String [] words = text.split(" "); // supposing that file contains only words and spaces
        for (String s : words) {
            trie.insert(s);
        }
        System.out.println(text);
    }

    public Set<Integer> getIndexes4Word(String searchWord) {
        Set<Integer> set = new HashSet<>();
        for (int index = text.indexOf(searchWord.charAt(0)); index < text.length() - searchWord.length(); index = text.indexOf(searchWord.charAt(0), index + 1)) {
            if (trie.containsNode(searchWord) && text.substring(index, index + searchWord.length()).equals(searchWord)) {
                set.add(index);
            }
        }
        return set;
    }

    public static void main(String[] args) {
        WordIndex wi;
        try {
            wi = new WordIndex();
            wi.loadFile("C:\\Users\\Sigma\\Desktop\\trie.txt");
            Set<Integer> set = wi.getIndexes4Word("лайм");
            for (Integer i : set) {
                System.out.println(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Trie trie = new Trie();
//        trie.insert("string");
//        trie.insert("strung");
//        trie.insert("String");
//        trie.insert("tri");
//        System.out.println(trie.containsNode("tri"));
    }
}
