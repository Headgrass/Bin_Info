package ru.headgrass.bininfo.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import ru.headgrass.bininfo.R
import ru.headgrass.bininfo.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL
import java.util.concurrent.Executors
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSearch.setOnClickListener {
        val bin = binding.et8bin.toString()

        Executors.newSingleThreadExecutor().submit {
            var urlConnection: HttpsURLConnection? = null

            try {
                val uri = URL("https://lookup.binlist.net/47900430")
                urlConnection = uri.openConnection() as HttpsURLConnection
                urlConnection.requestMethod = "GET"
                urlConnection.readTimeout = 1000
                urlConnection.connectTimeout = 1000

                val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
                val result = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    reader.lines().collect(Collectors.joining("\n"))
                } else {
                    ""
                }

                Log.d("DEBUGLOG", "result: $result")

                runOnUiThread {
                    println(result)
                    settingData()
                }
            } catch (e: Exception) {
                Log.e("DEBUGLOG", "FAIL CONNECTION", e)
            } finally {
                urlConnection?.disconnect()
            }
        }
    }}

    private fun settingData() {
        supportFragmentManager.beginTransaction().replace(
            R.id.main_container,
            InfoAboutCard.newInstance(bundle = Bundle())
        )
            .commit()
    }

    fun parse(json: String): JSONObject? {
        var jsonObject: JSONObject? = null
        try {
            jsonObject = JSONObject(json)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return jsonObject
    }

}