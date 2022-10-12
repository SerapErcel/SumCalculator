package com.serapercel.sumcalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.serapercel.sumcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var addAction = false
    private var addDecimal = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    fun numberAction(view: View) {
        if (view is Button) {
            binding.textViewInput.append(view.text)
            addAction = true
            addDecimal = true
            /*if (view.text == "." && addDecimal) {
                Log.e("sonuc", "if e girdi")
                binding.textViewInput.append(view.text)
                addDecimal = false
            } else {
                binding.textViewInput.append(view.text)
                Log.e("sonuc", "else e girdi")
                addAction = true

            }*/

        }
    }

    fun sumAction(view: View) {
        if (view is Button && addAction) {
            binding.textViewInput.append(view.text)
            addAction = false
            addDecimal = false
        }
    }

    fun decimalAction(view: View) {
        if (view is Button && addDecimal) {
            binding.textViewInput.append(view.text)
            addAction = false
            // addDecimal = true
            addDecimal = false
        }
    }

    fun clearAction(view: View) {
        binding.textViewInput.text = ""
        binding.textViewResult.text = ""
    }

    fun deleteAction(view: View) {
        val size = binding.textViewInput.length()
        if (size > 0) binding.textViewInput.text =
            binding.textViewInput.text.subSequence(0, size - 1)
    }


    fun resultAction(view: View) {
        binding.textViewResult.text = binding.textViewInput.text
        binding.textViewInput.text = calculateResult()

    }

    private fun calculateResult(): String {
        val digitsOperators = digitsOperators()
        if (digitsOperators.isEmpty()) return ""
        var result = 0f
        digitsOperators.remove('+')
        digitsOperators.forEach { if (it is Float) result += it.toFloat() }
        return result.toString()
    }

    private fun digitsOperators(): MutableList<Any> {
        val list = mutableListOf<Any>()
        var currentDigit = ""
        for (element in binding.textViewInput.text) {
            if (element.isDigit() || element == '.')
                currentDigit += element
            else {
                list.add(currentDigit.toFloat())
                currentDigit = ""
                list.add(element)

            }
        }
        if (currentDigit != "")
            list.add(currentDigit.toFloat())

        return list
    }
}