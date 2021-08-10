package com.harny.a7shifts


import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import java.lang.IllegalArgumentException

class StringCalcTest {
    private lateinit var calculator: StringCalc

    @Before
    fun setUp(){
        calculator = StringCalc
    }

    @Test
    fun emptyStringReturnZero(){
        assertEquals(0, calculator.add(""))
    }

    @Test
    fun valuesSeparatedByCommasReturnSum(){
        assertEquals(8, calculator.add("1,2,5"))
    }

    @Test
    fun inputFormatHandleNewLineValue(){
        assertEquals(6, calculator.add("1\n,2,3"))
        assertEquals(7, calculator.add("1,\n2,4"))
    }

    @Test
    fun inputFormatHandleCustomDelimiter(){
        assertEquals(8, calculator.add("//;\n1;3;4"))
        assertEquals(6, calculator.add("//$\n1$2$3"))
        assertEquals(13, calculator.add("//@\n2@3@8"))
    }

    @Test
    fun negativeNotSupported(){
        try {
            calculator.add("1,-5,4,3,-2")
            fail("exception is thrown")
        }catch (e:IllegalArgumentException){
            assertEquals("negatives not allowed -5,-2", e.message)
        }

        try {
            calculator.add("//@\n2@-3@-8")
            fail("exception is thrown")
        }catch (e:IllegalArgumentException){
            assertEquals("negatives not allowed -3,-8", e.message)
        }
    }

    @Test
    fun numbersLargerThan1000shouldBeIgnored(){
        assertEquals(2, calculator.add("2,1001"))
    }

    @Test
    fun inputFormatHandlesDelimitersOfArbitraryLength(){
        assertEquals(6, calculator.add("//***\n1***2***3"))
    }

    @Test
    fun inputFormatHandlesMultipleCustomDelimiters(){
        assertEquals(6, calculator.add("//$@\n1$2@3"))
    }

    @Test
    fun combineArbitraryLengthAndMultipleCustomDelimiters(){
        assertEquals(10, calculator.add("//***$@\n1***2$3@4"))
        assertEquals(10, calculator.add("//$@***\n1$2@3***4"))
    }
}