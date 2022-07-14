package com.lauravelasquezcano.ceiba.app.ui.main.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.lauravelasquezcano.ceiba.app.ui.model.GetUsersState
import com.lauravelasquezcano.ceiba.app.ui.utils.ProgressDialog
import com.lauravelasquezcano.ceiba.databinding.FragmentUsersBinding
import com.lauravelasquezcano.ceiba.domain.model.User
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UsersFragment : Fragment() {

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    private lateinit var progressDialog: ProgressDialog

    @Inject
    lateinit var usersViewModel: UsersViewModel

    @Inject
    lateinit var adapter: UsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initConfiguration()
        startObserver()
        getUsers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initConfiguration() {
        progressDialog = ProgressDialog(requireContext())
        initRecyclerView()
    }

    private fun initRecyclerView() {
        with(binding.rvUsers) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = this@UsersFragment.adapter
        }
    }

    private fun startObserver() {
        usersViewModel.getUsersState.observe(viewLifecycleOwner) { getUserState ->
            when (getUserState) {
                GetUsersState.Loading -> {
                    progressDialog.showProgress()
                }
                is GetUsersState.Success -> {
                    progressDialog.hideProgress()
                    handleUserList(getUserState.users)
                }
                GetUsersState.Failure -> {
                    progressDialog.hideProgress()
                }
            }

        }
    }

    private fun handleUserList(users: List<User>) {
        if (users.isNotEmpty()) {
            showRecyclerView()
            adapter?.let {
                it.setData(users)
            }
        } else {
            showEmptyState()
        }
    }

    private fun showRecyclerView() {
        hideEmptyState()
        binding.rvUsers.visibility = View.VISIBLE
    }

    private fun hideRecyclerView() {
        binding.rvUsers.visibility = View.GONE
    }

    private fun showEmptyState() {
        hideRecyclerView()
        binding.tvEmptyList.visibility = View.VISIBLE
    }

    private fun hideEmptyState() {
        binding.tvEmptyList.visibility = View.GONE
    }

    private fun getUsers() {
        usersViewModel.getUsers()
    }
}