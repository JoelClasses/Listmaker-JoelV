package com.raywenderlich.listmaker

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.raywenderlich.listmaker.databinding.MainFragmentBinding

class MainFragment : Fragment(),
    ListSelectionRecyclerViewAdapter.ListSelectionRecyclerViewClickListener {

    // Interface to communicate with the MainActivity
    lateinit var clickListener: MainFragmentInteractionListener

    interface MainFragmentInteractionListener {
        fun listItemTapped(list: TaskList)
    }

    // Binding object instance corresponding to the main_fragment.xml layout
    private lateinit var binding: MainFragmentBinding

    // ViewModel instance that manages the data for the UI
    private lateinit var viewModel: MainViewModel

    companion object {
        fun newInstance() = MainFragment()
    }

    // Inflate the layout for this fragment
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        binding.listsRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

    // Called once the fragment's activity has been created and this fragment's view hierarchy instantiated
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), MainViewModelFactory(PreferenceManager.getDefaultSharedPreferences(requireActivity()))).get(MainViewModel::class.java)

        val recyclerViewAdapter = ListSelectionRecyclerViewAdapter(viewModel.lists, this)
        binding.listsRecyclerview.adapter = recyclerViewAdapter

        // Update the RecyclerView when a new list is added
        viewModel.onListAdded = {
            recyclerViewAdapter.listsUpdated()
        }
    }

    // Handle the event when a list item is clicked
    override fun listItemClicked(list: TaskList) {
        clickListener.listItemTapped(list)
    }
}
