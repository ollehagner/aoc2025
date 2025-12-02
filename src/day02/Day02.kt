package day02

import readInput

fun main() {
    val testinput = parseInput("testinput")
    val input = parseInput("input")
    println("Part 1: ${part1(testinput)}")
    println("Part 2: ${part2(input)}")
}

fun part1(input: List<LongRange>): Long {
    return input
        .flatMap { range -> range.toList() }
        .filter { isInvalid(it.toString()) }
        .sum()
}

fun part2(input: List<LongRange>): Long {
    return input
        .flatMap { range -> range.toList() }
        .filter { isInvalid(it.toString(), 2) }
        .sum()
}

fun isInvalid(id: String): Boolean {
    return id.take(id.length / 2) == id.substring(id.length / 2)
}

fun isInvalid(id: String, parts: Int): Boolean {
    if(parts > id.length) { return false }
    if(id.length % parts != 0) {
        return isInvalid(id, parts + 1)
    }
    val windowSize = id.length / parts
    val invalid = id.windowed(windowSize, windowSize)
        .zipWithNext()
        .all { (a, b) -> a == b }
    return if(!invalid) {
        isInvalid(id, parts + 1)
    } else {
        true
    }
}

fun parseInput(file: String): List<LongRange> {
    return readInput("day02/$file").first().split(",")
        .map { LongRange(it.substringBefore("-").toLong(), it.substringAfter("-").toLong()) }
}
