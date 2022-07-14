package com.lauravelasquezcano.ceiba.app.ui.main.posts

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.lauravelasquezcano.ceiba.R
import com.lauravelasquezcano.ceiba.app.ui.main.users.UsersFragmentDirections
import com.lauravelasquezcano.ceiba.app.ui.model.GetPostsByUserState
import com.lauravelasquezcano.ceiba.app.ui.model.GetUserByIdState
import com.lauravelasquezcano.ceiba.app.ui.utils.ProgressDialog
import com.lauravelasquezcano.ceiba.databinding.FragmentPostsBinding
import com.lauravelasquezcano.ceiba.domain.model.Post
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

    @Inject
    lateinit var adapter: PostsAdapter

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
        initRecyclerView()
    }

    private fun initRecyclerView() {
        with(binding.rvPosts) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = this@PostsFragment.adapter
        }
    }

    private fun initObservers() {
        postsViewModel.getUserByIdState.observe(viewLifecycleOwner) { getUserByIdState ->
            when (getUserByIdState) {
                GetUserByIdState.Loading -> {
                    progressDialog.showProgress()
                }
                is GetUserByIdState.Success -> {
                    handleUserInfo(getUserByIdState.user)
                    postsViewModel.getPostsByUser(args.userId)
                }
                GetUserByIdState.Failure -> {
                    progressDialog.hideProgress()
                    showMessageDialog(getString(R.string.service_error))
                }
            }
        }

        postsViewModel.getPostsByUserState.observe(viewLifecycleOwner) { getPostsByUserState ->
            when (getPostsByUserState) {
                is GetPostsByUserState.Success -> {
                    progressDialog.hideProgress()
                    handlePostsList(getPostsByUserState.posts)
                }
                GetPostsByUserState.Failure -> {
                    progressDialog.hideProgress()
                    showMessageDialog(getString(R.string.service_error))
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

    private fun showMessageDialog(message: String) {
        val messageDialog = AlertDialog.Builder(requireContext())
        messageDialog.setMessage(message)
        messageDialog.setNeutralButton(
            getString(R.string.btn_ok)
        ) { dialog, _ ->
            dialog.dismiss()
            Navigation.findNavController(requireView())
                .navigate(PostsFragmentDirections.actionGoToUsersFragment())
        }
        messageDialog.show()
    }

    private fun handlePostsList(posts: List<Post>) {
        adapter.setData(posts)
    }
}