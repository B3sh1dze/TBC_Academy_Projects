package com.example.myfirsandroidapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val inputButton: AppCompatButton = findViewById(R.id.ClickButton)
        val inputText: AppCompatEditText = findViewById(R.id.NumberInputField)
        val outputext: AppCompatTextView = findViewById(R.id.TextDisplay)

        inputButton.setOnClickListener {
            val inputValue = inputText.text.toString()

            if (inputValue.isNotEmpty() && inputValue.toIntOrNull() != null) {
                val number = inputValue.toInt()
                val result = getStringOfNumbersInGeorgian(number)
                outputext.text = result
            } else {
                outputext.text = "Wrong input."
            }
        }
    }
}
fun getStringOfNumbersInGeorgian(number: Int): String {
    var resultString: String= ""
    val units = arrayOf(
        "", "ერთი", "ორი", "სამი", "ოთხი", "ხუთი", "ექვსი", "შვიდი", "რვა", "ცხრა", "ათი")
    val unitsFrom10To20 = arrayOf(
        "", "თერთმეტი", "თორმეტი", "ცამეტი", "თოთხმეტი", "თხუთმეტი", "თექვსმეტი", "ჩვიდმეტი", "თვრამეტი", "ცხრამეტი")
    val twentys = arrayOf(
        "", "", "ოცი", "ოცი", "ორმოცი", "ორმოცი", "სამოცი", "სამოცი", "ოთხმოცი", "ოთხმოცი"
    )
    val tens = arrayOf(
        "", "", "","ოცდაათი", "", "ორმოცდაათი", "", "სამოცდაათი", "", "ოთხმოცდაათი")
    val hundreds = arrayOf(
        "", "ასი", "ორასი", "სამასი", "ოთხასი", "ხუთასი", "ექვსასი", "შვიდასი", "რვაასი", "ცხრაასი", "ათასი")

    if (number <= 10) {
        resultString = units[number]
        return resultString
    }else if (number > 10 && number < 20) {
        resultString = unitsFrom10To20[number % 10]
        resultString
    } else if (number < 100) {
        resultString = twoDigitNums(number, units, twentys, tens, hundreds, unitsFrom10To20)
        return resultString
    }
    else if (number < 1000){
        var tempNum = number % 100
        var dividedBy100 = number / 100
        if (tempNum == 0){
            resultString = hundreds[dividedBy100]
            return resultString
        } else if (tempNum != 0 && tempNum <= 10) {
            resultString = hundreds[dividedBy100].dropLast(1) + " " + units[number % 100]
            return resultString
        } else if (tempNum > 10 && tempNum < 20) {
            resultString = hundreds[dividedBy100].dropLast(1) + " " + unitsFrom10To20[(number % 100) % 10]
            return resultString
        }
        resultString = hundreds[dividedBy100].dropLast(1) + " " + twoDigitNums(tempNum, units, twentys, tens, hundreds, unitsFrom10To20)
    }
    else if (number == 1000){
        resultString = hundreds[number / 100]
        return resultString
    }
    else {
        println("Wrong input.")
    }
    return resultString
}
fun isOdd(number: Int): Boolean {
    return number % 2 != 0
}
fun isEven(number: Int): Boolean {
    return number % 2 == 0
}
fun twoDigitNums(number: Int, units: Array<String>, twentys: Array<String>, tens: Array<String>,
                 hundreds: Array<String>, unitsFrom10To20: Array<String>): String {
    var resultString = ""
    var numberDividedBy10 = number / 10
    var remainder = number % 10
    if (remainder == 0 && isEven(numberDividedBy10)) {
        resultString = twentys[numberDividedBy10]
        return resultString
    } else if (remainder == 0 && isOdd(numberDividedBy10)) {
        resultString = tens[numberDividedBy10]
        return resultString
    }
    if ((number - remainder) % 20 == 0) {
        var tempString = twentys[numberDividedBy10].dropLast(1)
        resultString = tempString + "და" + units[remainder]
        return resultString
    } else {
        var tempString = twentys[numberDividedBy10].dropLast(1)
        resultString = tempString + "და" + unitsFrom10To20[remainder]
        return resultString
    }
}



