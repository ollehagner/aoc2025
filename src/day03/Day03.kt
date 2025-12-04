package day03

import readInput


fun main() {
    val testinput = parseInput("testinput")
    val input = parseInput("input")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

fun part1(input: List<List<Int>>): Long {
    return input
        .map { findLargest(it, 2) }
        .sumOf { it.toLong() }
}

fun part2(input: List<List<Int>>): Long {
    return input
        .map { findLargest(it, 12) }
        .sumOf { it.toLong() }
}

fun findLargest(batteryBank: List<Int>, remaining: Int): String {
    if(remaining == 0) {
        return ""
    }
    val (index, digit) = batteryBank
        .dropLast(remaining - 1)
        .withIndex()
        .maxBy { it.value }
    return digit.toString() + findLargest(batteryBank.drop(index + 1), remaining - 1)
}

fun parseInput(file: String) : List<List<Int>> {
    return readInput("day03/$file")
        .map { it.toCharArray().map { it.digitToInt() } }
}
