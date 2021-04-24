package by.gomel.noyvik.library.util;

import java.util.Arrays;

public class StringArrayEqual {

    public static boolean equals(String[] arr1, String[] arr2){
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        return Arrays.equals(arr1,arr2);

    }
}
