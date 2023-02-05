package ru.headgrass.bininfo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import ru.headgrass.bininfo.R
import ru.headgrass.bininfo.databinding.ActivityMainBinding
import ru.headgrass.bininfo.model.BinInfoDTO
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

        binding.btnSearch.setOnClickListener { binInfo ->
            val bin = binding.et8bin.text
            Executors.newSingleThreadExecutor().submit {
                var urlConnection: HttpsURLConnection? = null

                try {
                    val uri = URL("https://lookup.binlist.net/$bin")
                    urlConnection = uri.openConnection() as HttpsURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.readTimeout = 1000
                    urlConnection.connectTimeout = 1000

                    val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
                    val result = reader.lines().collect(Collectors.joining("\n"))
                    Log.d("DEBUGLOG", "result: $result")


                    val binInfoDTO = Gson().fromJson(result, BinInfoDTO::class.java)
                    val bundle = Bundle()
//                    bundle.putParcelable("BIN_EXTRA", binInfoDTO)
                    settingData()
                } catch (e: Exception) {
                    Log.e("DEBUGLOG", "FAIL CONNECTION", e)
                } finally {
                    urlConnection?.disconnect()
                }
            }
        }
    }

    private fun settingData() {
        supportFragmentManager.beginTransaction().replace(
            R.id.main_container,
            InfoAboutCard.newInstance(bundle = Bundle())
        ).commit()
    }
}