package com.theironyard;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String string = "I am a string. Yes I am.";
        System.out.println(string);
        String yourString = string.replaceAll("I", "You");
        System.out.println(yourString);

        String alphanumeric = "abcDeeeF12Ghhiiijkl99zabcDeee";
        System.out.println(alphanumeric.replaceAll(".", "Y"));
        //"." matches ANY character so this code will change the entire string to YYYYYYYYYYYYYY

        System.out.println(alphanumeric.replaceAll("^abcDeee", "YYY"));
        //only works on the first part of the string that matches the characters
        //won't change same characters at the end of the string

        System.out.println(alphanumeric.matches("^hello"));
        System.out.println(alphanumeric.matches("^abcDeee"));
        //returns true or false if matches full string

        System.out.println(alphanumeric.replaceAll("ijkl99z$", "THE END"));
        System.out.println(alphanumeric.replaceAll("[aei]", "X"));
        //square brackets replace just one matching character

        System.out.println(alphanumeric.replaceAll("[aie][Fj]", "X"));
        //replaces the first bracket's characters as followed by the second bracket's characters and replaces with X

        System.out.println("harry".replaceAll("[Hh]arry", "Harry"));
        //uppercase's the h in harry

        String newAlphanumeric = "abcDeeeF12Ghhiiijkl99zabcDeee";
        System.out.println(newAlphanumeric.replaceAll("[^e]", "X"));
        System.out.println(newAlphanumeric.replaceAll("[abcdef345678]", "X"));
        //regular expressions are case-sensitive

        System.out.println(newAlphanumeric.replaceAll("[a-f3-8]", "X"));
        //replaces all characters a through f and 3 through 8 in lowercase

        System.out.println(newAlphanumeric.replaceAll("[a-fA-F3-8]", "X"));
        //replaces all characters of both upper and lower case

        System.out.println(newAlphanumeric.replaceAll("(?i)[a-f3-8]", "X"));
        //replaces characters while ignoring case-sensitivity

        System.out.println(newAlphanumeric.replaceAll("\\d", "X"));
        //matches and replaces all digits

        System.out.println(newAlphanumeric.replaceAll("\\D", "X"));
        //replaces all non-digits in the string

        String hasWhiteSpace = "I have blanks and\ta tab, and also a newline\n";

        System.out.println(hasWhiteSpace);
        System.out.println(hasWhiteSpace.replaceAll("\\s", ""));
        //removes whitespace

        System.out.println(hasWhiteSpace.replaceAll("\\t", "X"));
        //replaces tab with an X

        System.out.println(hasWhiteSpace.replaceAll("\\S", "X"));
        //removes all non-whitespace characters

        System.out.println(newAlphanumeric.replaceAll("\\w", "X"));
        //removes a-z, A-Z, 0-9 and _

        System.out.println(hasWhiteSpace.replaceAll("\\w", "X"));
        //replaces everything but the whitespace characters

        System.out.println(hasWhiteSpace.replaceAll("\\b", "X"));
        //each word is surrounded by X - useful for wrapping words in HTML tags

        String thirdAlphanumericString = "abcDeF12Ghhiiijkl99z";
        System.out.println(thirdAlphanumericString.replaceAll("^abcDe{3}", "YYY"));
        //curly braces with a number come after the character that it applies to and multiplies it by that number

        System.out.println(thirdAlphanumericString.replaceAll("^abcDe+", "YYY"));
        //replaces one or more e's without specifying the number

        System.out.println(thirdAlphanumericString.replaceAll("^abcDe*", "YYY"));
        //replaces abcD, whether it has an e or not at the end

        System.out.println(thirdAlphanumericString.replaceAll("^abcDe{2,5}", "YYY"));
        //replaces between 2 & 5 e's

        System.out.println(thirdAlphanumericString.replaceAll("h+i*j", "Y"));
        //replaces all h, any number of i, followed by at least one j

        StringBuilder htmlText = new StringBuilder("<h1>My Heading<h1>");
        htmlText.append("<h2>Sub-heading</h2>");
        htmlText.append("<p>This is a paragraph about something.</p>");
        htmlText.append("<p>This is another paragraph about something else.</p>");
        htmlText.append("<h2>Summary</h2>");
        htmlText.append("<p>Here is the summary.</p>");


        //we want to find all occurrences of the h2 tag in a char sequence of html text
        //
        String h2Pattern = "<h2>"; //matches anything before or after tab
        Pattern pattern = Pattern.compile(h2Pattern);
        Matcher matcher = pattern.matcher(htmlText);
        System.out.println(matcher.matches());

        matcher.reset();
        int count = 0;
        while(matcher.find()) {
            count++;
            System.out.println("Occurrence " + count + " : " + matcher.start() + " to " + matcher.end());

        }
        //grabs everything in between tags with .*
        //lazily only grabs between it's own tags with .*?
        String h2GroupPattern = "(<h2>.*?</h2>)";
        Pattern groupPattern = Pattern.compile(h2GroupPattern);
        Matcher groupMatcher = groupPattern.matcher(htmlText);
        System.out.println(groupMatcher.matches());
        groupMatcher.reset();

        while(groupMatcher.find()) {
            System.out.println("Occurrence: " + groupMatcher.group(1));
        }

        String h2TextGroups = "(<h2>)(.+?)(</h2>)";
        Pattern h2TextPattern = Pattern.compile(h2TextGroups);
        Matcher h2TextMatcher = h2TextPattern.matcher(htmlText);

        while(h2TextMatcher.find()) {
            System.out.println("Occurrence: " + h2TextMatcher.group(2));
        }

        //if the character is a H or h, consider it a match and switch with L
        System.out.println("harry".replaceAll("[H|h]arry", "Larry"));
        System.out.println("Harry".replaceAll("[H|h]arry", "Larry"));

        //[^abc] the ^ means NOT when it's between []
        String tvTest = "tstvtkt";
        String tNotVRegExp = "t(?!v)";
        Pattern tNotVPattern = Pattern.compile(tNotVRegExp);
        Matcher tNotVMatcher = tNotVPattern.matcher(tvTest);

        count = 0;
        while(tNotVMatcher.find()) {
            count++;
            System.out.println("Occurrence " + count + " : " + tNotVMatcher.start() + " to " + tNotVMatcher.end());
        }


        //matching phone numbers
        String phone1 = "1234567890";//Shouldn't match
        String phone2 = "(123) 456-7890";//match
        String phone3 = "123 456-7890"; //shouldn't match
        String phone4 = "(123)456-7890";//shouldn't match

        System.out.println("phone1 = " + phone1.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"));
        System.out.println("phone2 = " + phone2.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"));
        System.out.println("phone3 = " + phone3.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"));
        System.out.println("phone4 = " + phone4.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"));

        //verifying a visa card
        String visa1 = "4444444444444"; //should match
        String visa2 = "5444444444444"; //shouldn't match
        String visa3 = "4444444444444444"; //should match
        String visa4 = "4444"; //shouldn't match

        System.out.println("visa1 "  + visa1.matches("^4[0-9]{12}([0-9]{3})?$"));
        System.out.println("visa2 "  + visa2.matches("^4[0-9]{12}([0-9]{3})?$"));
        System.out.println("visa3 "  + visa3.matches("^4[0-9]{12}([0-9]{3})?$"));
        System.out.println("visa4 "  + visa4.matches("^4[0-9]{12}([0-9]{3})?$"));
























    }
}
