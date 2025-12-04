package day04

import readInput
import common.Grid
import common.Point

const val ROLL = '@'
const val EMPTY = '.'

fun main() {
    val testinput = parseInput("testinput")
    val input = parseInput("input")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

fun part1(grid: Grid<Char>): Int {
    return removableRolls(grid).count()
}

fun part2(grid: Grid<Char>): Int {
    return removeRolls(grid)
}

fun removeRolls(grid: Grid<Char>): Int {
    val removableRolls = removableRolls(grid)
    if(removableRolls.size == 0) {
        return 0
    }
    removableRolls.forEach { point -> grid.set(point, EMPTY) }
    return removableRolls.size + removeRolls(grid)
}

fun removableRolls(grid: Grid<Char>) : Set<Point> {
    return grid.allPoints()
        .filter { grid.valueOf(it) == ROLL }
        .filter { countNeighborRolls(grid, it.neighbors()) < 4 }
        .toSet()
}

fun countNeighborRolls(grid: Grid<Char>, neighbors: Set<Point>): Int {
    return neighbors.count { grid.valueOrDefault(it, EMPTY) == ROLL }
}

fun parseInput(file: String) : Grid<Char> {
    return readInput("day04/$file")
        .map { it.toCharArray().toList() }
        .let { Grid(it) }
}

