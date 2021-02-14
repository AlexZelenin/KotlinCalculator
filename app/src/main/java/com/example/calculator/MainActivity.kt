package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        var view  = binding.root
        setContentView(view)

        binding.btn0.setOnClickListener { setTextFields("0") }
        binding.btn1.setOnClickListener { setTextFields("1") }
        binding.btn2.setOnClickListener { setTextFields("2") }
        binding.btn3.setOnClickListener { setTextFields("3") }
        binding.btn4.setOnClickListener { setTextFields("4") }
        binding.btn5.setOnClickListener { setTextFields("5") }
        binding.btn6.setOnClickListener { setTextFields("6") }
        binding.btn7.setOnClickListener { setTextFields("7") }
        binding.btn8.setOnClickListener { setTextFields("8") }
        binding.btn9.setOnClickListener { setTextFields("9") }

        binding.btnMinus.setOnClickListener { setTextFields("-") }
        binding.btnPlus.setOnClickListener { setTextFields("+") }
        binding.btnMultiply.setOnClickListener { setTextFields("*") }
        binding.btnDivide.setOnClickListener { setTextFields("/") }
        binding.btnLeftRng.setOnClickListener { setTextFields("(") }
        binding.btnRightRng.setOnClickListener { setTextFields(")") }
        binding.btnDot.setOnClickListener { setTextFields(".") }

        binding.btnAc.setOnClickListener {
            binding.mathOperation.text = ""
            binding.mathResult.text = ""
        }

        binding.btnBack.setOnClickListener {
            var str = binding.mathOperation.text.toString()
            if (str.isNotEmpty()) {
                binding.mathOperation.text = str.substring(0, str.length - 1)
            }

            binding.mathResult.text = ""
        }

        binding.btnResult.setOnClickListener {
            try {

                var ex = ExpressionBuilder(binding.mathOperation.text.toString()).build()
                var result = ex.evaluate()
                var longRes = result.toLong()
                if (result == longRes.toDouble()) {
                    binding.mathResult.text = longRes.toString()
                } else {
                    binding.mathResult.text = result.toString()
                }

            } catch (e: Exception) {
                Log.d("Error", "MSG: ${e.message}")
            }
        }
    }


    fun setTextFields(str: String) {
        if (binding.mathResult.text != "" && str == "+" ||
            binding.mathResult.text != "" && str == "-" ||
            binding.mathResult.text != "" && str == "*" ||
            binding.mathResult.text != "" && str == "/") {

            binding.mathOperation.text = binding.mathResult.text
            binding.mathResult.text = ""
        }
        binding.mathOperation.append(str)
    }
}