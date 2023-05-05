package com.dhandev.haditsbooks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.dhandev.haditsbooks.databinding.FragmentDailyBinding
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.createSkeleton

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class DailyFragment : Fragment() {

    private var _binding: FragmentDailyBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel : MainViewModel
    private lateinit var skeleton : Skeleton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDailyBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
        skeleton = binding.cardView.createSkeleton()
        skeleton.showSkeleton()
        viewModel.hadithId.observe(requireActivity()) {
            if (it?.data?.contents != null){
                binding.apply {
                    tvTitle.text = it.data.name
                    tvSubtitle.text = getString(R.string.nomor, it.data.contents.number.toString())
                    tvArab.text = it.data.contents.arab
                    tvTranslation.text = it.data.contents.id
                }
                skeleton.showOriginal()
            } else {
                val randomNum = (0..8).random()
                val randomBooks = resources.getStringArray(R.array.books_id)[randomNum]
                val booksAvailable = resources.getIntArray(R.array.books_available)[randomNum]
                viewModel.getData(randomBooks, (1..booksAvailable).random().toString())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}