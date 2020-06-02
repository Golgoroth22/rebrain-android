package com.falin.valentin.foodapp.screen.main

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.RebrainApp
import com.falin.valentin.foodapp.di.module.UserDataStorageModule
import com.falin.valentin.foodapp.screen.BaseFragment
import com.falin.valentin.foodapp.utils.Logger
import com.falin.valentin.foodapp.utils.injectViewModel
import com.falin.valentin.foodapp.viewmodel.AccountFragmentViewModel
import com.falin.valentin.foodapp.viewmodel.AccountFragmentViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_account.view.*
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

/**
 * A simple [BaseFragment] subclass.
 * Use the [AccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountFragment : BaseFragment() {
    override val owner: Logger.Owner
        get() = Logger.Owner.ACCOUNT_FRAGMENT

    @Inject
    lateinit var factory: AccountFragmentViewModelFactory
    private lateinit var viewModel: AccountFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()
        viewModel = injectViewModel(factory)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_account, container, false)
        initViews(rootView)
        initLiveData(rootView)
        initListeners(rootView)
        return rootView
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    val bitmap = data?.extras?.get("data") as Bitmap
                    val scaledBitmap = Bitmap.createScaledBitmap(
                        bitmap,
                        bitmap.width / 5,
                        bitmap.height / 5,
                        false
                    )
                    viewModel.setAvatar(scaledBitmap)
                }
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            CAMERA_PERMISSION_REQUEST -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE), 2020)
                } else {
                    Snackbar.make(
                        fragment_account_rootLayout,
                        "We need next permissions: Camera",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    private fun initListeners(rootView: View) {
        rootView.fragment_account_avatarImage.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    activity!!,
                    Manifest.permission.CAMERA
                )
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    activity!!, arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_REQUEST
                )
            }
            startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE), CAMERA_REQUEST_CODE)
        }
    }

    private fun initLiveData(rootView: View) {
        viewModel.emailLiveData.observe(this, Observer { email ->
            rootView.fragment_account_userNameText.text = email
        })
        viewModel.responseLiveData.observe(this, Observer { response ->
            if (response.data != null) {
                setUserAvatar(viewModel.getUserAvatarLink())
            }
            if (response.error != null) {
                Snackbar.make(
                    fragment_account_rootLayout,
                    response.error.localizedMessage,
                    Snackbar.LENGTH_LONG
                ).show()
                setUserAvatar(null)
            }
            if (response.isLoading) {
                rootView.fragment_account_avatarImage.setImageDrawable(null)
                rootView.fragment_account_avatarProgress.visibility = View.VISIBLE
            } else {
                rootView.fragment_account_avatarProgress.visibility = View.GONE
            }
        })
    }

    private fun setUserAvatar(avatar: String?) {
        Glide.with(context!!)
            .load(avatar)
            .error(R.drawable.ic_account_no_image_100dp)
            .thumbnail(0.5f)
            .into(this.fragment_account_avatarImage)
    }

    private fun initViews(rootView: View) {
        rootView.fragment_account_pickupPointsButton.setOnClickListener {
            toast("In progress")
        }
    }

    private fun initDagger() {
        RebrainApp.DAGGER.initAccountFragmentComponent(UserDataStorageModule()).inject(this)
    }

    companion object {
        private const val CAMERA_REQUEST_CODE = 30039
        private const val CAMERA_PERMISSION_REQUEST = 30039

        fun newInstance() = AccountFragment()
    }
}