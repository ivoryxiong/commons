package org.ivory.commons.algo.string;

import java.util.Random;

public class BoyerMooreSearcher implements Searcher{
    private int[] bcr;
    private int[] gsr;

    public int indexOf(char[] text, char[] pattern){
        calcBCR(pattern);
        calcGSR(pattern);
        int i, j = 0;
        while (j <= text.length - pattern.length) {
            for ( i = pattern.length - 1; i >= 0 && pattern[i] == text[i + j]; --i);
            if (i < 0){
//            	System.out.println(j);
//            	j+=gsr[0];
                return j;
            } else {
                j += Math.max(gsr[i], bcr[text[i + j]] + 1 + i - pattern.length);
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

//       for (int i = 0; i < pattern.length; i ++) {
//           System.out.print(" " + bcr[pattern[i]]); 
//       }
//       System.out.println();
    }

    private void calcGSR(char[] pattern){
        gsr = new int[pattern.length];
        int lastp = pattern.length; 
        for (int i = pattern.length - 1; i >= 0; i --){
            if (isPrefix(pattern, i+1)) {
                lastp = i+1;
            }
//            System.out.println("lastp: " + lastp);
            gsr[i] = pattern.length - (pattern.length - lastp);
//           System.out.print(" " + gsr[i]); 
        }
//       System.out.println();
        for (int i = 0; i < pattern.length - 1; i ++){
            int slen = suffix(pattern, i);
            gsr[pattern.length - slen - 1] = pattern.length - 1 - i; 
        }
//       for (int i = 0; i < pattern.length; i ++) {
//           System.out.print(" " + gsr[i]); 
//       }
//       System.out.println();
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
        Random rand = new Random();
        char [] chars = new char [] {'A','B','C','D','E','F','G'}; 
        int cnt = 0;
        for( int i = 0; i< 10000000 ; i ++){
        	int n = rand.nextInt(10) + 10;
        	int m = rand.nextInt(10) + 1;
        	StringBuffer text = new StringBuffer();
        	for( int j = 0 ;j <n ; j ++) {
        		text.append(chars[rand.nextInt(chars.length)]);
        	}
        	StringBuffer pattern = new StringBuffer();
        	for( int j = 0 ;j <m ; j ++) {
        		pattern.append(chars[rand.nextInt(chars.length)]);
        	}
        	int a = text.toString().indexOf(pattern.toString());
        	int b = searcher.indexOf(text.toString().toCharArray(),pattern.toString().toCharArray());
        	if( a != b ) {
        		System.out.println(text.toString() + " " + pattern.toString());
        	}
        	if( a >= 0 ){
        		++ cnt;
        	}
        }
        System.out.println(cnt);
    }
}
