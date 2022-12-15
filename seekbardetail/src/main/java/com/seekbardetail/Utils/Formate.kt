package com.seekbardetail.Utils

import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.abs
import kotlin.math.nextUp
import kotlin.math.pow
import kotlin.math.sign

object Formate {
    private val LEADING_DECIMALS = arrayOf(
        "0.".toCharArray(), "0.0".toCharArray(),
        "0.00".toCharArray(), "0.000".toCharArray(), "0.0000".toCharArray(),
        "0.00000".toCharArray(),
        "0.000000".toCharArray(), "0.0000000".toCharArray(), "0.00000000".toCharArray(),
        "0.000000000".toCharArray(), "0.0000000000".toCharArray(), "0.00000000000".toCharArray(),
        "0.000000000000".toCharArray(), "0.0000000000000".toCharArray(),
        "0.00000000000000".toCharArray(),
        "0.000000000000000".toCharArray()
    )

    @JvmStatic
    fun fastFormat(d: Double, precision: Int): String {
        val posPrecision = abs(precision)
        val roundUpVal = abs(d) * 10.0.pow(posPrecision.toDouble()) + 0.5
        if (roundUpVal > 999999999999999.0 || posPrecision > 16) {
            return bigDecFormat(d, posPrecision)
        }
        val longPart = roundUpVal.nextUp().toLong()
        if (longPart < 1) {
            return "0"
        }
        val longPartChars = longPart.toString().toCharArray()
        val formatChars: CharArray
        if (longPartChars.size > posPrecision) {
            var end = longPartChars.size - 1
            val decIndex = longPartChars.size - posPrecision
            while (end >= decIndex && longPartChars[end] == '0') {
                end--
            }
            if (end >= decIndex) {
                formatChars = CharArray(end + 2)
                System.arraycopy(longPartChars, 0, formatChars, 0, decIndex)
                formatChars[decIndex] = '.'
                System.arraycopy(
                    longPartChars, decIndex, formatChars,
                    decIndex + 1, end - decIndex + 1
                )
            } else {
                formatChars = CharArray(decIndex)
                System.arraycopy(longPartChars, 0, formatChars, 0, decIndex)
            }
        } else {
            var end = longPartChars.size - 1
            while (end >= 0 && longPartChars[end] == '0') {
                end--
            }
            val leadings = LEADING_DECIMALS[posPrecision - longPartChars.size]
            formatChars = leadings.copyOf(leadings.size + end + 1)
            System.arraycopy(longPartChars, 0, formatChars, leadings.size, end + 1)
        }
        return if (sign(d) > 0) String(formatChars) else "-" + String(formatChars)
    }

    private fun bigDecFormat(d: Double, precision: Int): String {
        var formatStr = BigDecimal(d.toString()).setScale(
            abs(precision),
            RoundingMode.HALF_UP
        )
            .toString()
        if (precision == 0) {
            return formatStr
        }
        var end = formatStr.length - 1
        while (end >= 0 && formatStr[end] == '0') {
            end--
        }
        formatStr = formatStr.substring(0, end + 1)
        if (formatStr[formatStr.length - 1] == '.') {
            formatStr = formatStr.substring(0, formatStr.length - 1)
        }
        return formatStr
    }
}