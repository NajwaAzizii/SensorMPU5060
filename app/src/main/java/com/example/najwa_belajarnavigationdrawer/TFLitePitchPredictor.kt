package com.example.najwa_belajarnavigationdrawer

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.channels.FileChannel

class TFLitePitchPredictor(private val context: Context) {

    private val interpreter: Interpreter
    private val mean: FloatArray
    private val scale: FloatArray
    init {

        val modelBuffer = loadModelFile("model_regresi_mpu6050.tflite")
        interpreter = Interpreter(modelBuffer)
        Log.i("TFLITE", "MODEL LOADED ✔")

        val scalerJson = context.assets.open("scaler_params.json")
            .bufferedReader()
            .use { it.readText() }

        val scaler = Gson().fromJson(scalerJson, ScalerParams::class.java)

        mean = scaler.mean.map { it.toFloat() }.toFloatArray()
        scale = scaler.scale.map { it.toFloat() }.toFloatArray()

        Log.i("TFLITE", "SCALER LOADED ✔")
    }


    private fun loadModelFile(modelName: String): ByteBuffer {
        val fileDescriptor = context.assets.openFd(modelName)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel

        return fileChannel.map(
            FileChannel.MapMode.READ_ONLY,
            fileDescriptor.startOffset,
            fileDescriptor.declaredLength
        )
    }

    fun predictPitch(
        acX: Float, acY: Float, acZ: Float,
        gyX: Float, gyY: Float, gyZ: Float
    ): Float {

        // Pastikan urutan input SAMA dengan dataset saat training!
        val rawInput = floatArrayOf(acX, acY, acZ, gyX, gyY, gyZ)

        // Normalisasi
        val normalized = FloatArray(6)
        for (i in 0 until 6) {
            normalized[i] = (rawInput[i] - mean[i]) / scale[i]
        }

        // TFLite membutuhkan input 2D: [1][6]
        val inputTensor = arrayOf(normalized)

        // Output 2D juga: [1][1]
        val outputTensor = Array(1) { FloatArray(1) }

        interpreter.run(inputTensor, outputTensor)

        return outputTensor[0][0]
    }

    fun close() {
        interpreter.close()
    }

    data class ScalerParams(
        val mean: List<Double>,
        val scale: List<Double>
    )
}
