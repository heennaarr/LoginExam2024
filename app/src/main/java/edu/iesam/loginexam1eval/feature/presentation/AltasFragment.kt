package edu.iesam.loginexam1eval.feature.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import edu.iesam.loginexam1eval.databinding.FragmentAltasBinding
import edu.iesam.loginexam1eval.feature.domain.User
import org.koin.androidx.viewmodel.ext.android.viewModel

class AltasFragment: Fragment() {
    private var _binding: FragmentAltasBinding? = null
    private val binding get() = _binding!!
    private val viewModel : UserViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAltasBinding.inflate(inflater, container, false)

        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        binding.action.setOnClickListener{
            viewModel.loadUsers(
                binding.username.text.toString(),
                binding.password.text.toString()
            )

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupObserver(){
        val nameObserver = Observer<UserViewModel.UiState> {

           if (it.users == true){

               findNavController().navigate(AltasFragmentDirections.actionFragmentUserToFragmentWelcome())
        }else{
            Log.d("@dev" , "Ya existe un user con ese name")
           }

    }
        viewModel.uiState.observe(viewLifecycleOwner, nameObserver)
    }


}