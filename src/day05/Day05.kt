package day05

import readInput


fun main() {
    val testinput = parseInput("testinput")
    val input = parseInput("input")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

fun part1(ingredientData: IngredientData): Int {
    return ingredientData.ingredientIds.count { ingredientId ->
        ingredientData.freshIngredients
            .any {
                it.containsValue(ingredientId)
            }
    }
}

fun part2(ingredientData: IngredientData): Long {
     return ingredientData.freshIngredients
         .sortedBy { it.first }
        .fold(Pair(0L, LongRange(0L, 0L))) { acc, ingredients ->
            val runningCount = acc.first
            val previous = acc.second
              if(ingredients.first <= previous.last && ingredients.last <= previous.last) {
                acc
              } else if(ingredients.first <= previous.last) {
                  val newRange = LongRange(previous.last + 1, ingredients.last)
                  Pair(runningCount + newRange.size(), newRange)
              } else {
                  Pair(runningCount + ingredients.size(), ingredients)
              }
        }
         .first

}

fun parseInput(file: String) : IngredientData {
    val lines = readInput("day05/${file}")
    val freshIngredients = lines
        .takeWhile { it.isNotEmpty() }
        .map { line -> LongRange(line.substringBefore("-").toLong(), line.substringAfter("-").toLong()) }
    val ingredientIds = lines
        .dropWhile { it.isNotEmpty() }
        .drop(1)
        .map { it.toLong() }
    return IngredientData(freshIngredients, ingredientIds)
}

data class IngredientData(val freshIngredients: List<LongRange>, val ingredientIds: List<Long>)

fun LongRange.containsValue(value: Long): Boolean {
    return  this.first <= value && this.last >= value
}

fun LongRange.size(): Long {
    return this.last - this.first + 1
}
