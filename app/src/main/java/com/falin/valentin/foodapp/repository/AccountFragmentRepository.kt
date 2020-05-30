package com.falin.valentin.foodapp.repository

import android.content.ContentResolver
import android.graphics.Bitmap
import android.net.Uri
import com.falin.valentin.foodapp.interactor.UserStorage
import com.falin.valentin.foodapp.network.retrofit.pojo.login.UserResponse
import com.falin.valentin.foodapp.network.retrofit.service.UserAvatarService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
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
     * @param parentDir parent directory file
     * @param contentResolver activity content resolver
     * @param onSuccess use it if request success
     * @param onFailure use it if request filed
     */
    fun tryToSendAvatar(
        bitmap: Bitmap,
        parentDir: File,
        contentResolver: ContentResolver,
        onSuccess: (UserResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        val file = convertBitmapToFile(bitmap, parentDir)
        val requestFile = file
            .asRequestBody(contentResolver.getType(Uri.fromFile(file)).toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData(AVATAR_NAME, file.name, requestFile)
        val descriptionString = "hello, this is description speaking"
        val description = descriptionString.toRequestBody(MultipartBody.FORM)

        userAvatarService.putAvatar(description, body).enqueue(object : Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Timber.e("AccountFragmentRepository putAvatar onFailure ${t.message}")
                onFailure.invoke(t)
            }

            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                Timber.i("AccountFragmentRepository putAvatar onResponse ${response.body()}")
                response.body()?.let { onSuccess.invoke(it) }
            }
        })
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