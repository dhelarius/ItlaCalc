package com.itla.itlacalc;

class StringUtil {

    static String evaluateString(String string){
        int index = string.indexOf(".");
        String stringToEval = string.substring(index);

        if(stringToEval.contains("1") ||
                stringToEval.contains("2") ||
                stringToEval.contains("3") ||
                stringToEval.contains("4") ||
                stringToEval.contains("5") ||
                stringToEval.contains("6") ||
                stringToEval.contains("7") ||
                stringToEval.contains("8") ||
                stringToEval.contains("9")){
            return string;
        }else{
            return string.substring(0, index);
        }
    }

}
