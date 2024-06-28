package de.syntax_institut.funappsvorlage.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import de.syntax_institut.funappsvorlage.adapter.MemeAdapter
import de.syntax_institut.funappsvorlage.databinding.FragmentMemesBinding

class MemesFragment : Fragment() {

    private val viewModel: MemesViewModel by viewModels()
    private lateinit var binding: FragmentMemesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMemesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Zu Beginn werden die Daten geladen
        viewModel.loadData()

        // Über einen Button Klick werden die Daten erneut geladen
        binding.btnRefresh.setOnClickListener {
            viewModel.loadData()
        }

        // Der SnapHelper sorgt dafür, dass die RecyclerView immer auf das aktuelle List Item springt
        val helper: SnapHelper = PagerSnapHelper()
        helper.attachToRecyclerView(binding.rvMemes)

        val adapter = MemeAdapter()
        binding.rvMemes.adapter = adapter

        // Bei Änderungen in der memes Variable des ViewModels wird die Liste automatisch neu zugewiesen
        viewModel.memes.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
    }
}
