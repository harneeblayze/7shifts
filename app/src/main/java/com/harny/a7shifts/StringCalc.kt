package com.harny.a7shifts

object StringCalc {

    fun add(numbers: String): Int {
        if (numbers.isEmpty()) {
            return 0
        }

        val isCustomDelimiterString = isCustomDelimiterString(numbers)
        return if (isCustomDelimiterString) {
            addCustomDelimiterNumbers(numbers)
        } else {
            addDefaultDelimiterNumbers(numbers)
        }
    }

    private fun isCustomDelimiterString(input: String): Boolean {
        if (input.length <= 2) {
            return false
        }

        if (input.substring(0, 2) != "//") {
            return false
        }

        return true
    }

    private fun getListOfCustomDelimiters(input: String): List<String> {
        //get the string between the last / and \n, this is where all our delimiters are
        val delimiterList: MutableList<String> = ArrayList()
        val customDelimiterString = input.substring(2, input.indexOf("\n"))

        val stringList = customDelimiterString.split("")
        var customDelimiterValue = ""
        var duplicateChecker = stringList[1]

        for (index in 1 until stringList.size) {
            if (duplicateChecker == stringList[index]) {
                customDelimiterValue += stringList[index]
            } else {
                delimiterList.add(customDelimiterValue)
                customDelimiterValue = stringList[index]
                duplicateChecker = customDelimiterValue
            }
        }

        return delimiterList
    }

    private fun addAllNumbersFromGivenString(inputs: List<String>): Int {
        val negativeNumbers: MutableList<String> = ArrayList()
        var sum = 0
        for (token in inputs) {
            var digit = Integer.parseInt(token)
            if (digit < 0) {
                negativeNumbers.add(token)
            }

            if (digit > 1000) {
                digit = 0
            }
            sum += digit
        }

        if (negativeNumbers.isNotEmpty()) {
            throw IllegalArgumentException(
                "negatives not allowed " + negativeNumbers.joinToString(",")
            )
        }
        return sum
    }

    private fun addCustomDelimiterNumbers(input: String): Int {
        val stringContainingNumbers = input.substringAfter("\n")
        val delimiters = getListOfCustomDelimiters(input)
        val tokens = stringContainingNumbers.split(*delimiters.toTypedArray()).map { it.trim() }
        return addAllNumbersFromGivenString(tokens)
    }

    private fun addDefaultDelimiterNumbers(input: String): Int {
        val defaultDelimiter = ",\n|\n,|,"
        val tokens = input.split(defaultDelimiter.toRegex()).map { it.trim() }
        return addAllNumbersFromGivenString(tokens)
    }

}