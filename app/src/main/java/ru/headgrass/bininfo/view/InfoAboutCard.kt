package ru.headgrass.bininfo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.headgrass.bininfo.databinding.FragmentInfoAboutCardBinding
import ru.headgrass.bininfo.model.BinInfoDTO

class InfoAboutCard : Fragment() {

    companion object {
        fun newInstance(bundle: Bundle) : InfoAboutCard {
            val fragment = InfoAboutCard()
            fragment.arguments = bundle
            return fragment
        }
    }

    private var _binding: FragmentInfoAboutCardBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoAboutCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val info = arguments?.getParcelable<BinInfoDTO>("BIN_EXTRA")
        binding.binInfoNetwork.text = info?.network
        binding.binInfoType.text = info?.type
        binding.binInfoBrand.text = info?.brand

        if (info?.prepaid == "false") {
            binding.binInfoPrepaid.text = "NO"
        } else {
            binding.binInfoPrepaid.text = "YES"
        }

        binding.binInfoCountry.text = info?.countryDTO?.country
        binding.binInfoCurrency.text = info?.countryDTO?.currency
        binding.binInfoBank.text = info?.bankDTO?.bank
        binding.binInfoPhone.text = info?.bankDTO?.phone
        binding.binInfoSite.text = info?.bankDTO?.site
    }

    //Чтобы избежать утечек памяти
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}