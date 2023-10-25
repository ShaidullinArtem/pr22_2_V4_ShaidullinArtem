package com.example.testapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private var country: Array<String> = arrayOf("", "")
    private var convertFromValue: String = ""
    private var convertToValue: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val convertFromDropdownTextView = findViewById<TextView>(R.id.convert_from_dropdown_menu)
        val convertToDropdownTextView = findViewById<TextView>(R.id.convert_to_dropdown_menu)
        val convertButton = findViewById<Button>(R.id.conversionButton)

        val conversionRateText = findViewById<TextView>(R.id.conversionRateText)
        val amountToConvert = findViewById<EditText>(R.id.amountToConvertValueEditText)

        var arrayList: ArrayList<String> = ArrayList()
        for (i in country) {
            arrayList.add(i)
        }

        convertFromDropdownTextView.setOnClickListener {
            val fromDialog = Dialog(this)
            fromDialog.setContentView(R.layout.from_spinner)
            fromDialog.window?.setLayout(650, 650)
            fromDialog.show()

            val editText = fromDialog.findViewById<EditText>(R.id.edit_text)
            val listView = fromDialog.findViewById<ListView>(R.id.list_view)

            val adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList)
            listView.adapter = adapter

            editText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    adapter.filter.filter(s)
                }
            })

            listView.setOnItemClickListener(object : AdapterView.OnItemClickListener {
                override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    convertFromDropdownTextView.setText(adapter.getItem(p2))
                    fromDialog.dismiss()
                    convertFromValue = adapter.getItem(p2).toString()
                }
            })

            convertToDropdownTextView.setOnClickListener {
                val toDialog = Dialog(this)
                toDialog.setContentView(R.layout.to_spinner)
                toDialog.window?.setLayout(650, 650)
                toDialog.show()

                val editText = findViewById<EditText>(R.id.edit_text)
                val listView = findViewById<ListView>(R.id.list_view)

                val adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList)
                listView.adapter = adapter

                editText.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        adapter.filter.filter(s)
                    }
                })

                listView.setOnItemClickListener(object : AdapterView.OnItemClickListener {
                    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        convertToDropdownTextView.setText(adapter.getItem(p2))
                        toDialog.dismiss()
                        convertToValue = adapter.getItem(p2).toString()
                    }
                })
            }

            convertButton.setOnClickListener {
                try {
//                    val amountToConvert: Double =
                } catch (e: Exception) {

                }
            }
        }
    }
}