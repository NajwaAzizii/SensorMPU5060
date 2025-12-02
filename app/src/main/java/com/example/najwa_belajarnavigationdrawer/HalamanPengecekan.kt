package com.example.najwa_belajarnavigationdrawer

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class HalamanPengecekan : AppCompatActivity() {

    private lateinit var inputAcX: EditText
    private lateinit var inputAcY: EditText
    private lateinit var inputAcZ: EditText
    private lateinit var inputGyX: EditText
    private lateinit var inputGyY: EditText
    private lateinit var inputGyZ: EditText
    private lateinit var btnPrediksi: Button
    private lateinit var txtHasilPrediksi: TextView
    private lateinit var scrollView: ScrollView

    private lateinit var predictor: TFLitePitchPredictor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.halaman_pengecekan)

        // Initialize views
        inputAcX = findViewById(R.id.inputAcX)
        inputAcY = findViewById(R.id.inputAcY)
        inputAcZ = findViewById(R.id.inputAcZ)
        inputGyX = findViewById(R.id.inputGyX)
        inputGyY = findViewById(R.id.inputGyY)
        inputGyZ = findViewById(R.id.inputGyZ)
        btnPrediksi = findViewById(R.id.btnPrediksi)
        txtHasilPrediksi = findViewById(R.id.txtHasilPrediksi)
        scrollView = findViewById(R.id.scrollViewPengecekan)

        predictor = TFLitePitchPredictor(this)

        btnPrediksi.setOnClickListener {
            lakukanPrediksi()
        }
    }

    private fun lakukanPrediksi() {
        try {
            val acXVal = inputAcX.text.toString().toFloatOrNull()
            val acYVal = inputAcY.text.toString().toFloatOrNull()
            val acZVal = inputAcZ.text.toString().toFloatOrNull()
            val gyXVal = inputGyX.text.toString().toFloatOrNull()
            val gyYVal = inputGyY.text.toString().toFloatOrNull()
            val gyZVal = inputGyZ.text.toString().toFloatOrNull()

            if (acXVal == null || acYVal == null || acZVal == null ||
                gyXVal == null || gyYVal == null || gyZVal == null) {

                Toast.makeText(this, "⚠️ Semua kolom harus angka!", Toast.LENGTH_SHORT).show()
                return
            }

            val hasilPrediksi = predictor.predictPitch(
                acXVal, acYVal, acZVal,
                gyXVal, gyYVal, gyZVal
            )

            txtHasilPrediksi.visibility = TextView.VISIBLE
            txtHasilPrediksi.text = "Hasil Prediksi:\nSudut Kemiringan: %.2f°".format(hasilPrediksi)

            scrollView.post { scrollView.fullScroll(ScrollView.FOCUS_DOWN) }

        } catch (e: Exception) {
            Toast.makeText(this, "❌ Error: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        predictor.close()
    }
}
