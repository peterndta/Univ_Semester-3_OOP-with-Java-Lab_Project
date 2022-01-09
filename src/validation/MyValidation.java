/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

/**
 *
 * @author Peter
 */
public class MyValidation {
    public static boolean checkString(String inputString, String pattern) throws Exception {
        if(inputString==null) throw new Exception("\tInput is NULL!");
        if(pattern==null) throw new Exception("\tPattern is NULL!");
        if(!inputString.matches(pattern)) throw new Exception("\tInvalid, Try again!\n");
        return true;
    }   
}
