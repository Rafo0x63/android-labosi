package hr.tvz.android.rafajeckalkulator

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import hr.tvz.android.rafajeckalkulator.databinding.ActivityMainBinding
import kotlin.math.round

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        val view = binding.root
        setContentView(view)

        var result = binding.result
        var liters = binding.liters
        var distance = binding.distance
        var btn = binding.calculateBtn

        btn.setOnClickListener { view ->
            if (!liters.text.toString().isEmpty() && !distance.text.toString().isEmpty()) {
                val res = liters.text.toString().toFloat() / distance.text.toString().toFloat() * 100
                result.text = String.format("%.2f", res).plus(" l/km")
            }
        }

        val colorSpinner : Spinner = binding.colorSpinner
        val colors = listOf(getString(R.string.white), getString(R.string.lightGrey), getString(R.string.purple), getString(R.string.lightYellow))
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, colors)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        colorSpinner.adapter = adapter

        colorSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                when (selectedItem) {
                    getString(R.string.white) -> changeBackgroundColor(Color.WHITE)
                    getString(R.string.lightGrey) -> changeBackgroundColor(Color.LTGRAY)
                    getString(R.string.purple) -> changeBackgroundColor(Color.parseColor("#d6b4fc"))
                    getString(R.string.lightYellow) -> changeBackgroundColor(Color.parseColor("#FFF59E"))
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    private fun changeBackgroundColor(color: Int) {
        val mainLayout: LinearLayout = binding.main
        mainLayout.setBackgroundColor(color)
    }
}

