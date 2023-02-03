package ru.headgrass.bininfo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.json.JSONException
import org.json.JSONObject
import ru.headgrass.bininfo.R

class InfoAboutCard : Fragment() {

    private var _binding: InfoAboutCard? = null
    private val binding get() = _binding

    companion object {
        fun newInstance(bundle: Bundle) : InfoAboutCard {
            val fragment = InfoAboutCard()
            fragment.arguments = bundle
            return fragment
        }
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info_about_card, container, false)
    }

    //Чтобы избежать утечек памяти
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}