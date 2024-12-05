package edu.iesam.loginexam1eval.feature.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import edu.iesam.loginexam1eval.databinding.FragmentBajasBinding
import edu.iesam.loginexam1eval.feature.domain.User
import org.koin.androidx.viewmodel.ext.android.viewModel

class BajasFragment:Fragment() {
    private var _binding: FragmentBajasBinding? = null
    val binding get() = _binding!!
    val bajasViewModel: BajasViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBajasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.action.setOnClickListener {
            bindData()
        }
        setupObservers()
    }

    fun setupObservers() {
        val observer = Observer<BajasViewModel.UiState> { uiState ->
            if (uiState.delete == true) {
                findNavController().navigate(BajasFragmentDirections.actionFragmentBajasToFragmentLogin())
            } else {
                Log.d("Login", "Usuario no encontrado")
            }
        }
        bajasViewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    fun bindData() {
        val username = binding.username.text.toString()
        val password = binding.password.text.toString()
        val user = User(username, username, password)
        bajasViewModel.delete(user)
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
