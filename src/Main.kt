fun main() {
    var mathOperations = MathematicalOperations()
    var prompt = ""

    while (true) {
        displayMainBoard()
        val choice = usersNumericInput(prompt)
        when (choice) {
            1 -> findGCD(mathOperations)
            2 -> findLCM(mathOperations)
            3 -> checkDollarSign(mathOperations)
            4 -> useCounter(mathOperations)
            5 -> reverseNumber(mathOperations)
            6 -> checkPalindrome(mathOperations)
            7 -> {
                println("Exiting the program. Goodbye!")
                break
            }
            else -> println("Invalid input. Please enter a number between 1 and 7.")
        }
    }
}

fun displayMainBoard() {
    println("Welcome to the dashboard! Enter the operation you want to proceed: ")
    println("1. Find the greatest common divisor of two numbers")
    println("2. Find the least common multiple")
    println("3. Check if the string contains a dollar sign")
    println("4. Use the counter function")
    println("5. Return the reversed number")
    println("6. Check if the given string is a palindrome")
    println("7. Exit")
}
fun findGCD(mathOperationGCD: MathematicalOperations) {
    val prompt1 = "Enter the first number: "
    val firstNum = usersNumericInput(prompt1)
    val prompt2 = "Enter the second number: "
    var secondNum = usersNumericInput(prompt2)
    var greatestCommonDivisor = mathOperationGCD.greatestCommonDivisor(firstNum, secondNum)
    println("The greatest common divisor of $firstNum and $secondNum is: $greatestCommonDivisor")
}
fun findLCM(mathOperationLCM: MathematicalOperations) {
    val prompt1 = "Enter the first number: "
    val firstNum = usersNumericInput(prompt1)
    val prompt2 = "Enter the second number: "
    var secondNum = usersNumericInput(prompt2)
    var leastCommonMultiple = mathOperationLCM.leastCommonMultiple(firstNum, secondNum)
    println("The least common multiple of $firstNum and $secondNum is: $leastCommonMultiple")
}
fun checkDollarSign(checkingDollarSign: MathematicalOperations) {
    var prompt = "Enter the string you want to check: "
    var string = usersStringInput(prompt)
    if(checkingDollarSign.containsDollarSignOrNot(string)) {
        println("This string contains dollar sign")
    } else {
        println("This string doesn't contain dollar sign")
    }
}
fun useCounter(mathOperationCounter: MathematicalOperations) {
    val prompt1 = "Enter the first number: "
    val firstNum = usersNumericInput(prompt1)
    val prompt2 = "Enter the last number: "
    var lastNum = usersNumericInput(prompt2)
    println("Here is count from $firstNum to $lastNum: ")
    mathOperationCounter.counterFunc(firstNum, lastNum)
}
fun reverseNumber(mathOperReturnReversedNum: MathematicalOperations) {
    val prompt = "Enter the first number: "
    val number = usersNumericInput(prompt)
    val reversedNumber = mathOperReturnReversedNum.returnReversedNumber(number)
    println("Reversed number of $number is $reversedNumber")
}
fun checkPalindrome(checkingPalindrome: MathematicalOperations) {
    var prompt = "Enter the string you want to check: "
    var string = usersStringInput(prompt)
    if(checkingPalindrome.isPalindromeOrNot(string)) {
        println("This string is palindrome.")
    } else {
        println("This string isn't palindrome.")
    }
}
fun usersNumericInput(prompt: String, allowNegative: Boolean = false): Int {
    println(prompt)
    while (true) {
        val input = readLine() ?: ""
        val num = input.toIntOrNull()

        if (num != null) {
            if (allowNegative || num >= 0) {
                return num
            } else {
                println("Invalid input. Please enter a non-negative integer.")
            }
        } else {
            print("Invalid input. Please enter a valid integer: ")
        }
    }
}
fun usersStringInput(string: String) : String {
    print(string)
    val input = readLine()
    while (true) {

        if (!input.isNullOrBlank()) {
            return input
        } else {
            print("Empty input. Please enter something: ")
        }
    }
}