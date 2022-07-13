package com.lauravelasquezcano.ceiba.app.ui.main.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lauravelasquezcano.ceiba.databinding.FragmentUsersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : Fragment() {

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}