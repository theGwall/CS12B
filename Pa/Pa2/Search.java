// Search.java

import java.io.*;
import java.util.Scanner;

public class Search {

    public static void mergeSort(String[] word, int[] lineNumber, int p, int r){
       int q;
       if(p < r) {
          q = (p+r)/2;
          mergeSort(word, lineNumber, p, q);
          mergeSort(word, lineNumber, q+1, r);
          merge(word, lineNumber, p, q, r);
       }
    }

    public static void merge(String[] word, int[] lineNumber, int p, int q, int r){
       int n1 = q-p+1;
       int n2 = r-q;
       String[] L = new String[n1];
       String[] R = new String[n2];
       int[] L1 = new int[n1];
       int[] R1 = new int[n2];
       int i, j, k;

       for(i=0; i<n1; i++) L[i] = word[p+i];
       for(j=0; j<n2; j++) R[j] = word[q+j+1];

       for(i=0; i<n1; i++) L1[i] = lineNumber[p+i];
       for(j=0; j<n2; j++) R1[j] = lineNumber[q+j+1];

       i = 0; j = 0;

       for(k=p; k<=r; k++){
          if( i<n1 && j<n2 ){
             if( L[i].compareTo(R[j]) < 0 ){
                word[k] = L[i];
                lineNumber[k] = L1[i];
                i++;
             }else{
                word[k] = R[j];
                lineNumber[k] = R1[j];
                j++;
             }
          }else if( i<n1 ){
             word[k] = L[i];
             lineNumber[k] = L1[i];
             i++;
          }else{
             word[k] = R[j];
             lineNumber[k] = R1[j];
             j++;
          }
       }

    }

    public static int binarySearch(String[] word, int p, int r,  String target){
       int q;
       if(p > r) {
          return -1;
       }else{

          q = (p+r)/2;


         if(target.compareTo(word[q]) == 0){
             return q;


         }else if(target.compareTo(word[q]) < 0) {
             return binarySearch(word, p, q-1, target);

          }else{
             return binarySearch(word, q+1, r, target);
          }
       }
    }

    public static void main(String[] args) throws IOException {
       Scanner in = null;
       //String s = null;
       //String[] words = null;

       if(args.length < 2){
           System.err.println("Usage: Search file target1 [target2...]");
           System.exit(1);
       }

       in = new Scanner(new File(args[0]));
       in.useDelimiter("\\Z");
       String s = in.next();
       in.close();
       String[] word = s.split("\n");

       int[] lineNumber = new int[word.length];
       // lineNumber[k] = words[k]
       for(int i=0; i<word.length; i++){
           lineNumber[i] = i+1;
       }

       mergeSort(word, lineNumber, 0, word.length-1);


       for(int i=1; i<args.length; i++) {
           if (binarySearch(word, 0, word.length-1, args[i]) > -1 ){
               System.out.println(args[i]+" found on line "+lineNumber[binarySearch(word, 0, word.length-1, args[i])]);
           }
           else System.out.println(args[i]+" not found");
       }
    }
}
