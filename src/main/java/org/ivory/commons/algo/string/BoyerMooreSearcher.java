package org.ivory.commons.algo.string;

import org.ivory.commons.algo.string.Searcher;

public class BoyerMooreSearcher implements Searcher{
    private int[] bcr;
    private int[] gsr;

    public int indexOf(char[] text, char[] pattern){
        calcBCR(pattern);
        calcGSR(pattern);
        int i, j = 0;
        while (j <= text.length - pattern.length) {
            for ( i = pattern.length - 1; i >= 0 && pattern[i] == text[i + j]; --i);
            if (i <= 0){
                return j;
            } else {
                System.out.println("BB " + j);
                j += Math.max(gsr[i], bcr[text[i + j]] + 1 + i - pattern.length);
                System.out.println("AA " + j);
            }
        }
        return -1;
    }

    private void calcBCR(char[] pattern){
       bcr = new int[256];
       for (int i = 0; i < 256; i ++){
           bcr[i] = pattern.length;
       }
       for (int i = 0; i < pattern.length; i ++) {
           bcr[pattern[i]] = pattern.length - i - 1; 
       }

       for (int i = 0; i < pattern.length; i ++) {
           System.out.print(" " + bcr[pattern[i]]); 
       }
       System.out.println();
    }

    private void calcGSR(char[] pattern){
        gsr = new int[pattern.length];
        int lastp = pattern.length; 
        for (int i = pattern.length - 1; i >= 0; i --){
            if (isPrefix(pattern, i)) {
                lastp = i;
            }
            gsr[i] = pattern.length - (pattern.length - lastp);
           System.out.print(" " + gsr[i]); 
        }
       System.out.println();
        for (int i = 0; i < pattern.length - 1; i ++){
            int slen = suffix(pattern, i);
            gsr[pattern.length - slen - 1] = pattern.length - 1 - i; 
        }
       for (int i = 0; i < pattern.length; i ++) {
           System.out.print(" " + gsr[i]); 
       }
       System.out.println();
    }

    private boolean isPrefix(char[] p, int idx){
        int i = idx, j = 0;
        for (; i < p.length && p[i] == p[j] ; i ++, j ++);
        if (i == p.length) return true;
        return false;
    }

    private int suffix(char[] p, int idx){
        int len = 0;
        for (int i = idx, j = p.length - 1; i >= 0; i --, j--) {
            if (p[i] == p[j]) {
                len ++;
            } else{
                break;
            }
        }
        return len;
    }

    public static void main(String [] args){
        Searcher searcher = new BoyerMooreSearcher();
        System.out.println(searcher.indexOf("abdcbcacbcad".toCharArray(),"bcacb".toCharArray()));
    }
}
