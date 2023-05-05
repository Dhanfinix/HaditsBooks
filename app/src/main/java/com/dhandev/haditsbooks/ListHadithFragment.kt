package com.dhandev.haditsbooks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dhandev.haditsbooks.adapter.book.HadithDelegate
import com.dhandev.haditsbooks.adapter.book.HadithBooksAdapter
import com.dhandev.haditsbooks.adapter.list.HadithListAdapter
import com.dhandev.haditsbooks.adapter.list.HadithListDelegate
import com.dhandev.haditsbooks.data.remote.response.DataItem
import com.dhandev.haditsbooks.data.remote.response.Datas
import com.dhandev.haditsbooks.data.remote.response.HadithsItem
import com.dhandev.haditsbooks.databinding.FragmentListHadithBinding
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.createSkeleton

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ListHadithFragment : Fragment() {

    private var _binding: FragmentListHadithBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var adapter: HadithListAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var viewModel: MainViewModel
    private lateinit var skeleton: Skeleton
    private val args : ListHadithFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListHadithBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        adapter = HadithListAdapter()
        binding.rvBooks.adapter = adapter
        linearLayoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.rvBooks.layoutManager = linearLayoutManager

        adapter.delegate = object : HadithListDelegate {
            override fun onItemClicked(selected: HadithsItem) {
//                val action = ListHadithFragmentDirections.actionListHadithFragmentToDailyFragment(selected)
//                findNavController().navigate(action)
            }
        }
        viewModel.getDataList(args.bookId, "1-100")
        skeleton = binding.rvBooks.createSkeleton()
        skeleton.showSkeleton()
        viewModel.hadithList.observe(requireActivity()){
            adapter.setAdapter(it.datas?.hadiths!!)
            skeleton.showOriginal()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}