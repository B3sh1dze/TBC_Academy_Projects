open class MathematicalOperations {
    fun greatestCommonDivisor(num1: Int, num2: Int): Int {
        var a = num1
        var b = num2
        while(b != 0) {
            var tempNum = a % b
            a = b
            b = tempNum
        }
        return a
    }
    fun leastCommonMultiple(num1: Int, num2: Int): Int {
        return (num1 * num2) / greatestCommonDivisor(num1, num2)
    }
    fun containsDollarSignOrNot(string: String): Boolean {
        for (char in string ) {
            if (char == '$') {
                return true
            }
        }
        return false
    }
    fun counterFunc(startNumber: Int, maxNumber: Int) {
        if (startNumber > maxNumber) {
            return
        }
        print("$startNumber,  ")
        counterFunc(startNumber + 1, maxNumber)
    }
    fun returnReversedNumber(num: Int): Int {
        var tempString = num.toString()
        var reversedTempString = ""
        for (i in tempString.length - 1 downTo 0) {
            reversedTempString += tempString[i]
        }
        return reversedTempString.toInt()
    }
    fun isPalindromeOrNot(string: String): Boolean {
        val reversedString = string.reversed()
        return string == reversedString
    }
}