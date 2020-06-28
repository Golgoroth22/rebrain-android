package com.falin.valentin.foodapp.repository

import android.net.Uri
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import timber.log.Timber

/**
 * Repository-layer class for work with data from firebase cloud storage.
 *
 */
class FirebaseCloudStorageRepository {
    private val storageReference = Firebase.storage.reference

    private val imageRefs = listOf(
        storageReference.child("images/dwards.jpg"),
        storageReference.child("images/empire.jpg"),
        storageReference.child("images/khorne.jpg"),
        storageReference.child("images/scavens.jpg")
    )

    suspend fun getUris(): List<Uri> {
        val resultList = mutableListOf<Uri>()
        var error: Throwable? = null
        imageRefs.forEach {
            it.downloadUrl.addOnSuccessListener { uri ->
                resultList.add(uri)
            }.addOnFailureListener { exception ->
                error = exception
                Timber.i("FirebaseCloudStorageRepository getUris $exception")
            }
        }
        while (resultList.size != imageRefs.size && error == null) {
            //wait
        }
        return if (error != null) {
            emptyList()
        } else {
            resultList
        }
    }
}