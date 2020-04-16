package com.alyjak.reposearch.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alyjak.reposearch.R
import com.alyjak.reposearch.databinding.ResultOfSearchingFragmentBinding
import com.alyjak.reposearch.events.InternetConnectionErrorEvent
import com.alyjak.reposearch.events.MakeSearchEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import timber.log.Timber

class ResultOfSearchingFragment : Fragment() {

    companion object {
        fun newInstance() = ResultOfSearchingFragment()
    }

    private val viewModel: ResultOfSearchingViewModel by lazy {
        ViewModelProvider(this).get(ResultOfSearchingViewModel::class.java)
    }

    private lateinit var adapter: RepositoryInfoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: ResultOfSearchingFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.result_of_searching_fragment,
            container,
            false)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        adapter = RepositoryInfoAdapter()
        binding.resultsRecycleView.adapter = adapter

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun onMakeSearchEvent(event: MakeSearchEvent) {
        viewModel.searchResult(event)
    }

    @Subscribe
    fun onInternetConnectionErrorEvent(event: InternetConnectionErrorEvent) {
        Timber.e("No internet connection!" + event.exception.printStackTrace())
        viewModel.setInternetError()
    }

}
