package com.example.gjlunchbox

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.auth.FirebaseUser

object AuthManager{

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()

    /**
     * Sign up a new user with email and password, and store additional user information in Realtime Database.
     * @param name The full name of the user.
     * @param email The email address of the user.
     * @param password The password for the user account.
     * @param onComplete A callback function to handle the result of the operation.
     */
    fun signUp(name: String, email: String, password: String, onComplete: (String) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val userData = mapOf(
                        "name" to name,
                        "email" to email
                    )
                    // Save additional user information in Realtime Database
                    user?.let {
                        val userRef = database.getReference("users").child(it.uid)
                        userRef.setValue(userData)
                            .addOnSuccessListener {
                                // Update user profile with display name
                                val profileUpdates = UserProfileChangeRequest.Builder()
                                    .setDisplayName(name)
                                    .build()

                                user.updateProfile(profileUpdates)
                                    .addOnCompleteListener { updateTask ->
                                        if (updateTask.isSuccessful) {
                                            onComplete("Sign Up Successful")
                                        } else {
                                            onComplete("Sign Up Failed: ${updateTask.exception?.message}")
                                        }
                                    }
                            }
                            .addOnFailureListener { e ->
                                onComplete("Failed to save user data: ${e.message}")
                            }
                    }
                } else {
                    onComplete("Sign Up Failed: ${task.exception?.message}")
                }
            }
    }

    /**
     * Sign in an existing user with email and password.
     * @param email The email address of the user.
     * @param password The password for the user account.
     * @param onComplete A callback function to handle the result of the operation.
     */
    fun login(email: String, password: String, onComplete: (String) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onComplete("Sign In Successful")
                } else {
                    onComplete("Sign In Failed: ${task.exception?.message}")
                }
            }
    }

    /**
     * Get the currently signed-in user.
     * @return The current FirebaseUser or null if no user is signed in.
     */
    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }
}


