/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Mr.Son
 */
public class ValidatorUtil {

    public static boolean isEmail(String Value) {
        Pattern pattern = Pattern.compile("\\w+@[a-zA-Z_]+\\.[a-zA-Z]{2,3}");
        Matcher matcher = pattern.matcher(Value);
        return matcher.matches();
    }

    public static boolean isEmail1(String Value) {
        Pattern pattern = Pattern.compile("(\\w+@[a-zA-Z_]+\\.[a-zA-Z]{2,3})|(\\w+@[a-zA-Z_]+\\.[a-zA-Z]{2,3}\\.[a-zA-Z]{2,3})");
        Matcher matcher = pattern.matcher(Value);
        return matcher.matches();
    }

    public static boolean isPwdUsername(String Value) {
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(Value);
        return matcher.matches();
    }

    public static boolean isSpace(String Value) {
        Pattern pattern = Pattern.compile("[\\w\\s]+");
        Matcher matcher = pattern.matcher(Value);
        return matcher.matches();
    }

    public static boolean isAZSpace(String Value) {
        Pattern pattern = Pattern.compile("[\\w\\s]+");
        Matcher matcher = pattern.matcher(Value);
        return matcher.matches();
    }

    public static boolean isNumeric(String Value) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(Value);
        return matcher.matches();
    }

    public static boolean isEmpty(String Value) {
        if (Value.trim().equalsIgnoreCase("")) {
            return true;
        }
        return false;
    }

    public static boolean isDate(String Value) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            format.setLenient(false);
            Date date = new Date(format.parse(Value).getTime());
        } catch (ParseException ex) {
            return false;
        }
        return true;
    }

    public static boolean isCard(String data) {
        data = data.toUpperCase();
        Pattern pattern = Pattern.compile("^[[A-Z][0-9]]{3}\\d{1,6}$");
        Matcher matcher = pattern.matcher(data);
        return matcher.matches();
    }

   
}
