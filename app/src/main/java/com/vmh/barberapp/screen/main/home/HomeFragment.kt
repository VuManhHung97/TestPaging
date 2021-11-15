package com.vmh.barberapp.screen.main.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.vmh.barberapp.R
import com.vmh.barberapp.screen.auth.AuthResource
import com.vmh.barberapp.screen.main.home.adapter.LoadingGridStateAdapter
import com.vmh.barberapp.screen.main.home.adapter.MoviesRxAdapter
import com.vmh.barberapp.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment : DaggerFragment() {
    private lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    private lateinit var mAdapter: MoviesRxAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    private fun initView() {
        mAdapter = MoviesRxAdapter()
        list.setHasFixedSize(true)
        list.adapter = mAdapter
        list.adapter = mAdapter.withLoadStateFooter(
            footer = LoadingGridStateAdapter()
        )
        mAdapter.addLoadStateListener { loadState ->
            Log.d("loadState", loadState.toString())
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error

            errorState?.let {
                AlertDialog.Builder(context)
                    .setTitle("Lỗi")
                    .setMessage(it.error.localizedMessage)
                    .setNegativeButton("Cancel") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .setPositiveButton("thử lại") { _, _ ->
                        mAdapter.retry()
                    }
                    .show()
            }
        }


    }

    private fun initData() {
        viewModel = ViewModelProviders.of(this, providerFactory).get(HomeViewModel::class.java)
        mDisposable.add(viewModel.movies().subscribe {
            mAdapter.submitData(lifecycle, it)
        })
        subscribeObservers()
    }
    private val  mDisposable = CompositeDisposable()

    @SuppressLint("SimpleDateFormat")
    private fun subscribeObservers() {


        viewModel.errorLiveData.observe(viewLifecycleOwner, {
            Toast.makeText(context, it.localizedMessage, Toast.LENGTH_SHORT).show()
        })

        viewModel.movieLiveData.observe(viewLifecycleOwner, {
            Log.d("okhttp", "submitData")
            mAdapter.submitData(lifecycle, it)
        })

        viewModel.getAuthenticatedUser().removeObservers(viewLifecycleOwner)

        viewModel.getAuthenticatedUser().observe(viewLifecycleOwner, {
            if (it != null) {
                when (it.status) {
                    AuthResource.AuthStatus.AUTHENTICATED -> {

                    }

                    AuthResource.AuthStatus.ERROR -> {
                        Log.d(TAG, "subscribeObservers: " + it.message)
                    }

                }
            }
        })

    }

    companion object {
        private const val TAG = "SelectOptionFragment"
    }
}
