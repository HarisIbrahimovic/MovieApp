package com.sirahi.movieapp.view.fragment.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.sirahi.movieapp.R
import com.sirahi.movieapp.databinding.FragmentNowPlayingBinding
import com.sirahi.movieapp.presentation.MenuViewModel
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMediaData
import com.sirahi.movieapp.view.adapters.VerticalMediaAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NowPlayingFragment : Fragment(), VerticalMediaAdapter.OnVerticalMediaClicked {

    private val viewModel: MenuViewModel by activityViewModels()
    private lateinit var binding: FragmentNowPlayingBinding
    private lateinit var vAdapter: VerticalMediaAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_now_playing, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vAdapter = VerticalMediaAdapter(requireContext(), this)
        binding.adapter = vAdapter
        viewModel.nowPlayingData.observe(viewLifecycleOwner, {
            when (it) {
                is IncomingMediaData.Success -> it.data?.let { list -> vAdapter.setList(list) }
                is IncomingMediaData.Failure -> it.data?.let { list -> vAdapter.setList(list) }
                else -> Unit
            }
        })
    }

    override fun onVerticalItemClicked(id: Int, type: String) {
    }
}