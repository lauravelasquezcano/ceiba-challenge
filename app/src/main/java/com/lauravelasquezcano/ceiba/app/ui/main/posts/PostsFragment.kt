package com.lauravelasquezcano.ceiba.app.ui.main.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.lauravelasquezcano.ceiba.app.ui.model.GetUserByIdState
import com.lauravelasquezcano.ceiba.app.ui.utils.ProgressDialog
import com.lauravelasquezcano.ceiba.databinding.FragmentPostsBinding
import com.lauravelasquezcano.ceiba.domain.model.User
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PostsFragment : Fragment() {

    private var _binding: FragmentPostsBinding? = null
    private val binding get() = _binding!!

    private lateinit var progressDialog: ProgressDialog

    private val args: PostsFragmentArgs by navArgs()

    @Inject
    lateinit var postsViewModel: PostsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initConfiguration()
        initObservers()
        getUserById()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initConfiguration() {
        progressDialog = ProgressDialog(requireContext())
    }

    private fun initObservers() {
        postsViewModel.getUserByIdState.observe(viewLifecycleOwner) { getUserByIdState ->
            when (getUserByIdState) {
                GetUserByIdState.Loading -> {
                    progressDialog.showProgress()
                }
                is GetUserByIdState.Success -> {
                    progressDialog.hideProgress()
                    handleUserInfo(getUserByIdState.user)
                }
                GetUserByIdState.Failure -> {
                    progressDialog.hideProgress()
                }
            }
        }
    }

    private fun getUserById() {
        postsViewModel.getUserById(args.userId)
    }

    private fun handleUserInfo(user: User) {
        with(binding) {
            tvUserName.text = user.name
            tvTelephone.text = user.phone
            tvEmail.text = user.email
        }
    }
}