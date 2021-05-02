package com.meetup.ap.view.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.meetup.ap.R
import com.meetup.ap.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (_binding == null)
            _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        replaceFragmentOfTag("event_fragment") { EventFragment.newInstance() }

        binding.bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.eventFragment -> {
                    replaceFragmentOfTag("me_fragment") { MeFragment.newInstance() }
                    showFragmentOfTag("event_fragment") { EventFragment.newInstance() }
                    hideFragmentOfTag("me_fragment") { MeFragment.newInstance() }
                }
                R.id.meFragment -> {
                    replaceFragmentOfTag("me_fragment") { MeFragment.newInstance() }
                    showFragmentOfTag("me_fragment") { MeFragment.newInstance() }
                    hideFragmentOfTag("event_fragment") { EventFragment.newInstance() }
                }
            }
            true
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    private fun <T : Fragment> hideFragmentOfTag(tag: String, factory: () -> T) {
        val primaryFragment = findFragmentByTagOrElse(tag, factory)
        childFragmentManager.commit {
            hide(primaryFragment)
        }
    }

    private fun <T : Fragment> showFragmentOfTag(tag: String, factory: () -> T) {
        val primaryFragment = findFragmentByTagOrElse(tag, factory)
        childFragmentManager.commit {
            show(primaryFragment)
        }
    }

    private fun <T : Fragment> replaceFragmentOfTag(tag: String, factory: () -> T) {
        val primaryFragment = findFragmentByTagOrElse(tag, factory)
        childFragmentManager.commit {
            setReorderingAllowed(true)
            if (!primaryFragment.isAdded)
                add(R.id.fragmentContainer, primaryFragment, tag)
            setPrimaryNavigationFragment(primaryFragment)
        }
    }


    @Suppress("UNCHECKED_CAST")
    private fun <T : Fragment> findFragmentByTagOrElse(tag: String, factory: () -> T): T {
        return childFragmentManager.findFragmentByTag(tag) as? T ?: factory()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}