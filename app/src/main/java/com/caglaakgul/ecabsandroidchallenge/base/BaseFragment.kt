package com.caglaakgul.ecabsandroidchallenge.base

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.caglaakgul.ecabsandroidchallenge.R
import com.caglaakgul.ecabsandroidchallenge.data.remote.ErrorDto
import com.caglaakgul.ecabsandroidchallenge.enums.ServiceErrorType
import com.caglaakgul.ecabsandroidchallenge.extension.observe

abstract class BaseFragment<DB : ViewDataBinding, VM : BaseViewModel> : Fragment() {
    protected abstract fun getLayoutRes(): Int
    protected abstract fun getViewModelClass(): Class<VM>

    private var _binding: DB? = null
    protected val binding get() = _binding!!
    protected val viewModel: VM by lazy { ViewModelProvider(this)[getViewModelClass()] }
    private val progressDialog: Dialog by lazy { DialogHelper.setProgressDialog(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        observeBaseLiveData()
        initObservers()
        initListeners()
        _binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    private fun observeBaseLiveData() {
        observe(viewModel.errorLiveData) {
            showToast(parseError(it))
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) {
                showLoading()
            } else {
                hideLoading()
            }
        }
    }

    protected open fun initObservers() {}

    protected open fun initListeners() {}

    private fun showToast(message: String) {
        val toast = Toast.makeText(requireContext(), message, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    fun showLoading() {
        hideLoading()
        progressDialog.show()
    }

    fun hideLoading() {
        if (progressDialog.isShowing) progressDialog.cancel()
    }

    private fun parseError(errorDto: ErrorDto): String {
        return when (errorDto.errorType) {
            ServiceErrorType.FROM_SERVICE -> {
                errorDto.message
            }
            ServiceErrorType.NO_CONNECTION -> {
                getString(R.string.offline_text)
            }
            ServiceErrorType.BAD_REQUEST -> {
                getString(R.string.message_error_unknown_error)
            }
            ServiceErrorType.NOT_FOUND -> {
                getString(R.string.message_error_unknown_error)
            }
            ServiceErrorType.SERVICE_UNAVAILABLE -> {
                getString(R.string.message_error_service_unavailable)
            }
            else -> {
                getString(R.string.message_error_unknown_error)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        hideLoading()
        progressDialog.cancel()
        super.onDestroy()
    }
}
