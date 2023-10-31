package com.example.testapp

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.testapp.types.CurrencyData
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    private var country: Array<String> = arrayOf(
        "AFN",
        "EUR",
        "USD",
        "DZD",
        "AOA",
        "XCD",
        "ARS",
        "AWG",
        "BDT",
        "INR",
        "BHD",
        "BTN",
        "ALL",
        "XOF",
        "AUD"
    )
    private var convertFromValue: String = ""
    private var convertToValue: String = ""
    private var conversionValue: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val convertFromDropdownTextView = findViewById<TextView>(R.id.convert_from_dropdown_menu)
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

            convertButton.setOnClickListener {
                try {
                    val result = getConversionRate("USD", 1)
                    Toast.makeText(this, "${result}", Toast.LENGTH_LONG).show()
                } catch (e: Exception) {
                    Toast.makeText(this, "Что-то пошло не так. Попробуйте попытаться позже", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    public fun getConversionRate(convertFrom: String, amountToConvert: Number): String {
        val queue: RequestQueue = Volley.newRequestQueue(this)
        val url: String = "https://finance.rambler.ru/api/sources/cbr/instruments/${convertFrom}/?step=1"
        val stringRequest: StringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                response ->
                val obj = JSONObject(response)
                Log.d("RootLogs", obj.toString())
                Log.d("RootLogs", conversionValue)
            },
            {
                Log.d("RootLogs", "Volley Error: $it")
            }
            )
        return conversionValue
    }
}