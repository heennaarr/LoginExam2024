package edu.iesam.loginexam1eval.feature.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import edu.iesam.loginexam1eval.R
import edu.iesam.loginexam1eval.databinding.FragmentLoginBinding
import edu.iesam.loginexam1eval.feature.domain.User
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentLogin : Fragment(R.layout.fragment_login) {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel : LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        viewModel.getLogin()
        return  binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        binding.actionAltas.setOnClickListener{
            findNavController().navigate(FragmentLoginDirections.actionFragmentLoginToFragmentUser())

        }
        binding.action.setOnClickListener{
            viewModel.loadUsers(
                binding.username.text.toString(),
                binding.password.text.toString()
            )

        }

        if(binding.reminder.isChecked){
            viewModel.setLogin(
                User(
                    "1",
                    binding.username.text.toString(),
                    binding.password.text.toString()
                )
            )
        }else{
            viewModel.setLogin(null)
        }




    }

    private fun setupObserver(){
        val nameObserver = Observer<LoginViewModel.UiState> {
            if (it.users == true){
                    findNavController().navigate(FragmentLoginDirections.actionFragmentLoginToFragmentWelcome())
            }
            it.userReminder?.let{
                binding.apply {
                    reminder.isChecked = true
                    username.setText(it.name)
                    password.setText(it.password)
                }
            }

        }
        viewModel.uiState.observe(viewLifecycleOwner, nameObserver)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}