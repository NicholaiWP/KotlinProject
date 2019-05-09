package com.example.blog

import java.time.LocalDateTime
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

fun LocalDateTime.format() = this.format(englishDateFormatter)

private val daysLookup = (1..31).associate { it.toLong() to getOrdinal(it) }

private val englishDateFormatter = DateTimeFormatterBuilder()
        .appendPattern("yyyy-MM-dd")
        .appendLiteral(" ")
        .appendText(ChronoField.DAY_OF_MONTH, daysLookup)
        .appendLiteral(" ")
        .appendPattern("yyyy")
        .toFormatter(Locale.ENGLISH)

private fun getOrdinal(n: Int) = when {
    n in 11..13 -> "${n}th"
    n % 10 == 1 -> "${n}st"
    n % 10 == 2 -> "${n}nd"
    n % 10 == 3 -> "${n}rd"
    else -> "${n}th"
}

fun String.toSlug() = toLowerCase()
        .replace("\n", " ")
        .replace("[^a-z\\d\\s]".toRegex(), " ")
        .split(" ")
        .joinToString("-")
        .replace("-+".toRegex(), "-")

//LIX fun to calculate reading difficulty of a text
fun String.lix():Int{
    //To get the lIX number I am assigning a variable to a function and then return the outputs of the variables using the following formula:
    ////O = amount of words in the text
    //P = amount of punctuations in the text
    //L = amount of long words (over 6 letters long)
    // val result: Int = (O/P) + (L*100)/O

    val o = ::o
    val p = ::p
    val l = ::l

    //if there's no punctuation, exclude punctuation function. It makes no sense to divide by 0 anyway and it would throw an error.
    return if(p(this) == 0f || o(this) == 0){
        0
    //else return whole LIX formula if there's dots.
    } else{
        val words = o(this)
        val dots = p(this)
        val sixWords = l(this)

        (words/dots + sixWords * 100f / words).toInt()
    }
}

//finds amount of words by splitting the string while using a regex pattern and returns the count
fun o(sentence: String): Int {
    return sentence.toLowerCase()
            .split(Regex(" "))
            .count()
}

//The regex expression looks for punctuation matches, and for each match I Increase my integer value
//https://regexr.com/397dr
//to find a suitable and acceptable regex expression, this website was used to verify that it works.
//I'm no expert in Regex but it seems to work as it should.
//finds matches of dots and returns amount of dots
fun p(input: String): Float {
    var amountOfDots = 0f
    val userInput:String = input
    val p:Pattern = Pattern.compile("\\p{Punct}")
    val m:Matcher = p.matcher(userInput)
    while (m.find()){
        amountOfDots++
        if(m.hitEnd()){
            break
        }
    }

    return amountOfDots
}


//https://regexr.com/397dr
//to find a suitable and acceptable regex expression for this function,
// the website above was used to verify that the expression was valid
fun l(input: String): Int{

    var amount = 0
    val userInput:String = input
    val p:Pattern = Pattern.compile(" [A-Z0-9a-z]{6,}")
    val m:Matcher = p.matcher(userInput)
    while (m.find()){
        amount++
        if(m.hitEnd()){
            break
        }
    }
    return amount
}
