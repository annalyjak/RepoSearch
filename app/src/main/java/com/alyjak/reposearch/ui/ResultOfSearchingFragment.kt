package com.alyjak.reposearch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alyjak.reposearch.R
import com.alyjak.reposearch.databinding.ResultOfSearchingFragmentBinding

class ResultOfSearchingFragment : Fragment() {

    companion object {
        fun newInstance() = ResultOfSearchingFragment()
    }

    private val viewModel: ResultOfSearchingViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this).get(ResultOfSearchingViewModel::class.java)
    }

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

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}
