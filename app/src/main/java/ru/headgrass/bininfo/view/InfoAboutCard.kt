package ru.headgrass.bininfo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import ru.headgrass.bininfo.databinding.FragmentInfoAboutCardBinding
import ru.headgrass.bininfo.model.BinInfo
import ru.headgrass.bininfo.viewmodel.AppState
import ru.headgrass.bininfo.viewmodel.MainViewModel

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

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInfoAboutCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.getData().observe(viewLifecycleOwner) { state ->
            render(state as AppState)
        }

        viewModel.getData()

        val info = arguments?.getParcelable<BinInfo>("BIN_EXTRA")
        binding.binInfoBrand.text = info?.brand ?: ""
        binding.binInfoCurrency.text = info?.currency
    }

    private fun render(state: AppState) {
        when(state) {
            is AppState.Success<*> -> {
                val binInf = state.data as BinInfo
                binding.binInfoNetwork.text = binInf.network
                binding.binInfoBrand.text = binInf.brand
                binding.binInfoType.text = binInf.type
                binding.binInfoBank.text = binInf.bank
                binding.binInfoCountry.text = binInf.country
                binding.binInfoPrepaid.text = binInf.prepaid
                binding.binInfoCurrency.text = binInf.currency
                binding.binInfoPhone.text = binInf.phone
                binding.binInfoSite.text = binInf.site
            }
            is AppState.Error -> {
                Snackbar.make(binding.root,
                state.error.message.toString(),
                Snackbar.LENGTH_INDEFINITE)
                    .setAction("Попробовать снова") {
                        viewModel.getBinInfo()
                    }.show()
            }
        }
    }

    //Чтобы избежать утечек памяти
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}