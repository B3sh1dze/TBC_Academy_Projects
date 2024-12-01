fun main() {
    var continueProgram: Char = '0'
    while (continueProgram != 'N') {
        println("დაწყება")
        val x = getUserInput("შეიყვანეთ X ცვლადის მნიშვნელობა: ")
        val y = getUserInput("შეიყვანეთ Y ცვლადის მნიშვნელობა: ")
        var z: Double

        var numericPartOfX = findNumericPartOfString(x)
        var numericPartOfY = findNumericPartOfString(y)
        var xContainsNumOrNot = containsNumericDataOrNot(x)
        var yContainsNumOrNot = containsNumericDataOrNot(y)

        if(!xContainsNumOrNot) {
            numericPartOfX = "0"
        }
        if(!yContainsNumOrNot) {
            numericPartOfY = "0"
        }
        val numericValueOfX = numericPartOfX.toInt()
        val numericValueOfY = numericPartOfY.toInt()
        println("X-ის მნიშვნელობაა: $numericValueOfX")
        println("Y-ის მნიშვნელობაა: $numericValueOfY")

        if (numericValueOfY == 0) {
            println("ნულზე გაყოფა ნებადართული არაა.")
        } else {
            z = (numericValueOfX.toDouble() / numericValueOfY.toDouble())
            println("X და Y განაყოფი არის: $z")
        }
        var answer: String?
        do {
            print("გსურთ პროგრამის ხელახლა დაწყება <Y/N>? ")
            answer = readLine()?.trim()?.uppercase()

            if (answer.isNullOrEmpty() || answer.length > 1 || (answer != "Y" && answer != "N")) {
                println("არასწორი პასუხი. გთხოვთ, შეიყვანოთ მხოლოდ Y ან N.")
            }
        } while (answer.isNullOrEmpty() || answer.length > 1 || (answer != "Y" && answer != "N"))
        if (answer == "N") {
            println("დასასრული.")
            break
        }
    }
}
fun getUserInput(prompt: String): String {
    var input: String?
    do {
        print(prompt)
        input = readLine()
        if (input.isNullOrBlank()) {
            println("გთხოვთ, შეიყვანოთ ნებისმიერი მნიშვნელობა, რომ ველი ცარიელი არ იყოს.")
        }
    } while (input.isNullOrBlank())
    return input
}
fun findNumericPartOfString(string: String): String {
    var numericPartOfString: String = ""
    for (char in string!!) {
        if (char.isDigit()) {
            numericPartOfString += char
        }
    }
    return numericPartOfString
}
fun containsNumericDataOrNot(string: String): Boolean {
    if (!string.any { it.isDigit() }) {
        return false
    } else {
        return true
    }
}