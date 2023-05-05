package com.dhandev.haditsbooks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dhandev.haditsbooks.adapter.book.HadithDelegate
import com.dhandev.haditsbooks.adapter.book.HadithBooksAdapter
import com.dhandev.haditsbooks.data.remote.response.DataItem
import com.dhandev.haditsbooks.databinding.FragmentBooksBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class BooksFragment : Fragment() {

    private var _binding: FragmentBooksBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var adapter: HadithBooksAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBooksBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        adapter = HadithBooksAdapter()
        binding.rvBooks.adapter = adapter
        linearLayoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.rvBooks.layoutManager = linearLayoutManager

        adapter.delegate = object : HadithDelegate {
            override fun onItemClicked(selected: DataItem) {
                val bookId = selected.id!!
                val bookName = selected.name!!
                val action = BooksFragmentDirections.actionBooksFragmentToListHadithFragment(bookId, bookName)
                findNavController().navigate(action)
            }
        }

        viewModel.getDataBook((0..8).random().toString())
        viewModel.hadithBook.observe(requireActivity()){
            adapter.setAdapter(it.data!!)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}