package com.example.vktestapp.fragmentsUi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vktestapp.FriendListAdapter
import com.example.vktestapp.databinding.FragmentMainBinding
import com.example.vktestapp.viewModel.MainViewModel
import com.example.vktestapp.viewModel.VkRequestState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ListFragment : Fragment() {
    companion object {
        fun newInstance() = ListFragment()
    }
    private lateinit var binding: FragmentMainBinding
    val viewModel by sharedViewModel<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bundle = arguments
        val args = ListFragmentArgs.fromBundle(bundle!!)
        val friendListAdapter = FriendListAdapter()
        viewModel.getFriends(args.id, args.token)
        binding = FragmentMainBinding.inflate(layoutInflater)
        binding.friendList.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = friendListAdapter
            addItemDecoration(DividerItemDecoration(this.context, LinearLayoutManager.VERTICAL))
        }

            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                    viewModel.appState.collect {
                        when (it.requestState){
                            VkRequestState.Error -> {} //Тут пока ничего, но под развитие приложения пусть будет
                            VkRequestState.Loading -> {}
                            is VkRequestState.Success ->{
                                friendListAdapter.updateFriendList(it.requestState.friendList)
                            }

                        }
                    }
                }
            }
            return binding.root
        }
    }


