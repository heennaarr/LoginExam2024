package edu.iesam.loginexam1eval.feature.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import edu.iesam.loginexam1eval.databinding.FragmentUserBinding
import edu.iesam.loginexam1eval.feature.domain.User
import org.koin.androidx.viewmodel.ext.android.viewModel

class AltasFragment: Fragment() {
    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private val viewModel : UserViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        viewModel.loadUsers()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupObserver(){
        val nameObserver = Observer<UserViewModel.UiState> {

           it.users?.let{
               bindData(it)
        }

    }
        viewModel.uiState.observe(viewLifecycleOwner, nameObserver)
    }

    private fun bindData(users : List<User>) {
        if(users.size == 0){
            Log.d("@dev" , "save me! I have 0")
        }else{
            Log.d("@dev" , "There are more! Confirm that the name it's diferent")
        }

        binding.action.setOnClickListener{
            Log.d("@dev" , binding.username.text.toString())
            Log.d("@dev" , binding.password.text.toString())
            findNavController().navigate(AltasFragmentDirections.actionFragmentUserToFragmentWelcome())
        }
    }
}