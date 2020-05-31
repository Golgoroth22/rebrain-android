package com.falin.valentin.foodapp.repository

import android.content.Context
import android.graphics.Bitmap
import com.falin.valentin.foodapp.interactor.UserStorage
import com.falin.valentin.foodapp.network.retrofit.pojo.login.UserResponse
import com.falin.valentin.foodapp.network.retrofit.service.RetrofitCallback
import com.falin.valentin.foodapp.network.retrofit.service.UserAvatarService
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.io.*

/**
 * Repository-layer class for work with [AccountFragment].
 *
 */
class AccountFragmentRepository(
    private val context: Context,
    private val storage: UserStorage<String>,
    private val userAvatarService: UserAvatarService
) {
    /**
     * This method can be called for get user email.
     *
     * @return User email
     */
    fun getUserEmail() = storage.getEmail()

    /**
     * This method can be called for send user avatar on server.
     *
     * @param bitmap user avatar bitmap
     * @param onSuccess use it if request success
     * @param onFailure use it if request filed
     */
    fun setAvatar(
        bitmap: Bitmap,
        onSuccess: (Unit) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        val file = convertBitmapToFile(bitmap, context.cacheDir)
        val body = MultipartBody.Part.createFormData(AVATAR_NAME, file.name, file.asRequestBody())
        userAvatarService.setAvatar(body).enqueue(RetrofitCallback<Unit>(onSuccess, onFailure))
    }

    private fun convertBitmapToFile(bitmap: Bitmap, parentDir: File): File {
        val file = File(parentDir, AVATAR_NAME)
        file.createNewFile()

        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, bos)
        val bitMapData = bos.toByteArray()

        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(file)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        try {
            fos?.write(bitMapData)
            fos?.flush()
            fos?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return file
    }

    companion object {
        private const val AVATAR_NAME = "avatar"
    }
}