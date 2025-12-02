package day01

import readInput
import kotlin.math.absoluteValue

fun main() {

    val testInput = readInput("day01/test")
    val input = readInput("day01/input")
    val parsed = parseInput(input)

    println("Part1 password is : " + part1(parsed))
    println("Part2 password is : " + part2(parsed))

}

fun part1(input: List<Int>): Int {

    return input
        .runningFold(50) { acc, value ->
            acc + value
        }.count { it % 100 == 0 }
}

fun part2(input: List<Int>): Int {
    return input
        .runningFold(50) { acc, clicks ->
            val withoutRotations = acc % 100
            val currentDial = if (withoutRotations < 0) (100 - withoutRotations.absoluteValue) else withoutRotations.absoluteValue

            if (currentDial == 0) {
                clicks
            } else {
                if (currentDial + clicks < 0) {
                    currentDial + clicks - 100
                } else {
                    currentDial + clicks
                }
            }

        }
        .map { dial ->
            if(dial == 0) 100 else dial
        }
        .sumOf { it.absoluteValue / 100 }
}

fun parseInput(input: List<String>): List<Int> {
    return input
        .map { line ->
            val value = line.substring(1).toInt()
            if(line.first() == 'L') value * -1 else value
        }
}
