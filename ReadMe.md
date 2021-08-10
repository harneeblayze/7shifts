String Calculator Technical Test Solution

Submission by Otuoniyo Harny (hotuoniyo@gmail.com)

OVERVIEW
Provide a simple add function that handles
 1) default set of delimiters
 2) custom set of delimiters
 3) implement unit test for the add function to validate given inputs

 APPROACH AND CODE STRUCTURE
 The add(numbers:String) function is the base function, its job is
 1) check if input is empty
 2) check if is a customDelimiterString
 3) return sum of numbers in the string if or not it is a customDelimiterString

  isCustomDelimiterString(input:String)
 this function simply checks if the given input is in the format of a custom delimiter or not
 it returns a boolean based on its checks.

 getListOfCustomDelimiters(input:String)
 as the name implies, here we want to get all the possible custom delimiters used in the string to split the numbers
 This function returns a list of all custom delimiters used for the given input string.
 Inorder to get this list, we have to do a couple of steps
 1) The custom delimiters are always between the double slash and the new line ("//" and "\n")
 we simply want to get the strings within those characters so we can act on it.

 2) now we need to split this items into a list of string so we can iterate over them
 3)while we iterate, we need to take into consideration that there could be delimiters of arbitrary length e.g "***"
 those delimiters if not handle could be seen as different delimiters.
 4) create a simple logic that checks if the delimiter you are at in the loop is equal to the previous delimiter
 and if it is, you simply just want to append to the previous delimiter e.g "*" and "*" will now be "**"
 this way, delimiter of arbitrary length are taken as one entity.
 5) we can also get multiple delimiters and this loop would handle the case easily.
 add all the delimiters found into a list of string called delimiterList and return it.

 addAllNumbersFromGivenString(inputs: List<String>)
 as the name implies, we want to add all the numbers from the List of String.
 this function checks for
1) negative numbers and throws an exception when negative numbers are present in the list
2) ignores numbers larger than 1000
3) returns the sum of all positive numbers found in the string

addCustomDelimiterNumbers(input: String)
This function splits the input given by
1) get the list of all possible delimiters used in the string using the function getListOfCustomDelimiters(input:String)
2) using the split function that takes in a varargs of delimiters to split the string and get a list of numbers as string
3) calls the addAllNumbersFromGivenString(inputs: List<String>) and passes the resulting list from step 2 to get the sum of all the numbers in the string.

addDefaultDelimiterNumbers(input: String)
This function does the same thing as addCustomDelimiterNumbers(input: String) except it uses the default delimiters given, to do its split.



