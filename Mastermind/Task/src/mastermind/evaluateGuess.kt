package mastermind

import java.lang.Math.min

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    var rightPosition = 0
    var wrongPosition = 0

    var processedGuess = guess
    var processedSecret = secret

    for ((index, ch) in secret.withIndex()) {
        when (ch) {
            guess[index] -> {
                rightPosition++
                processedGuess = processedGuess.replaceFirst(ch, '*')

                if (secret[index] == processedSecret[index]) {
                    processedSecret = processedSecret.replaceFirst(ch, '*')
                } else {
                    wrongPosition--
                }
            }
            else -> {
                wrongPosition += min(
                    processedSecret.count { it == ch },
                    processedGuess.count { it == ch })
                processedSecret = processedSecret.replace(ch, '*')
            }
        }
    }
    return Evaluation(rightPosition, wrongPosition)
}
