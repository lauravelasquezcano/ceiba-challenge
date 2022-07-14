package com.lauravelasquezcano.ceiba.app.ui.main.users

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.lauravelasquezcano.ceiba.R
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
        setupWatcher()
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

    private fun setupWatcher() {
        binding.etSearchUser.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                usersViewModel.getUsersByName(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun initRecyclerView() {
        with(binding.rvUsers) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = this@UsersFragment.adapter
        }

        adapter.onUserClicked = { userId ->
            goToPosts(userId)
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
                    showMessageDialog(getString(R.string.service_error))
                }
                GetUsersState.EmptySearch -> {
                    showEmptyState()
                }
            }

        }
    }

    private fun handleUserList(users: List<User>) {
        if (users.isNotEmpty()) {
            showRecyclerView()
            adapter.setData(users)
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

    private fun showMessageDialog(message: String) {
        val messageDialog = AlertDialog.Builder(requireContext())
        messageDialog.setMessage(message)
        messageDialog.setNeutralButton(
            getString(R.string.btn_ok)
        ) { dialog, _ -> dialog.dismiss() }
        messageDialog.show()
    }

    private fun goToPosts(userId: String) {
        Navigation.findNavController(requireView())
            .navigate(UsersFragmentDirections.actionGoToPostsFragment(userId.toInt()))
    }
}