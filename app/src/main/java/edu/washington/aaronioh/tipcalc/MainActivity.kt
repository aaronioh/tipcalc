package edu.washington.aaronioh.tipcalc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tEdit = findViewById<EditText>(R.id.editAmount) as EditText
        val tButton = findViewById<Button>(R.id.buttonTip) as Button
        val tSpinner = findViewById<Spinner>(R.id.spinnerTip) as Spinner

        tEdit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                val text = tEdit.text.toString()
                tButton.isEnabled = text.isNotEmpty() && text.matches(Regex("^[0-9]*\$"))
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        tButton.isEnabled=false
        tButton.setOnClickListener {
            val percent = tSpinner.selectedItem.toString().substring(0, 2).toDouble() / 100.0
            val amount = tEdit.text.toString().toDouble()

            val tip = "%.2f".format(percent*amount)

            Toast.makeText(this, "$" + tip, Toast.LENGTH_SHORT).show()
        }

        val percentList = arrayOf("10%", "15%", "20%", "25%")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, percentList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        tSpinner.adapter=adapter
    }

}
