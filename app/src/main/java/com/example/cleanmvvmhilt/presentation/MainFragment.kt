package com.example.cleanmvvmhilt.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.cleanmvvmhilt.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    //Используем аннотацию @HiltViewModel, поэтому нам не нужно явно ингжектить фабрику
    //@Inject
    //lateinit var mainViewModelFactory: MainViewModelFactory
    //private val mainViewModel:MainViewModel by viewModels{mainViewModelFactory}

    private val mainViewModel:MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.RefreshButton.setOnClickListener {
            mainViewModel.ovRefreshButtonClick()
        }

        //Слущаем поток activity
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            mainViewModel.activity.collect{activity ->
                binding.activityTextView.text = activity
            }
        }

        //Слущаем поток activityType
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            mainViewModel.activityType.collect{activityType ->
                binding.typeTextView.text = activityType
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}