package com.route.chatapplication1.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

import com.route.chatapplication1.Message
import com.route.chatapplication1.NavigateToView
import com.route.chatapplication1.SessionProvider
import com.route.chatapplication1.SingleLiveEvent
import com.route.chatapplication1.firestore.Doa
import com.route.chatapplication1.firestore.model.User

class LoginViewModel :ViewModel(){

    var email= MutableLiveData<String?>()
    var password= MutableLiveData<String?>()
    var emailError= MutableLiveData<String?>()
    var passwordError= MutableLiveData<String?>()
    var isLoading= MutableLiveData<Boolean?>()
    private var auth= FirebaseAuth.getInstance()
    var messageLiveData= SingleLiveEvent<Message>()
    var navigateToView=SingleLiveEvent<NavigateToView>()

    private fun validate(): Boolean {
        var isValid=true

        if (email.value.isNullOrBlank()){
            emailError.postValue("please enter email")
            isValid=false

        } else{
            emailError.postValue(null)
        }
        if (password .value.isNullOrBlank()){
            passwordError.postValue("please enter password")
            isValid=false
        } else{
            passwordError.postValue(null)
        }

        return isValid
    }

    fun login(){

        if(validate()){
            isLoading.postValue(true)
            auth.signInWithEmailAndPassword(email.value!!,password.value!!)//this method return some thing of type Task<AuthResult>
                //بترجع اوبجكت يعني فيه نتيجه اللي حصل
                .addOnCompleteListener{task->

                    if (task.isSuccessful){
                        getUserFromFireStore(task.result.user?.uid)
                    }
                    else
                    {
                        isLoading.postValue(false)
                        //show error
                        messageLiveData.postValue(
                            Message(
                            message = task.exception?.localizedMessage)
                        )
                    }
                }

        }
        else
            return


    }

    private fun getUserFromFireStore(uid: String?) {
        Doa.getUser(uid){task->
            if (task.isSuccessful){
                // save user and navigate to home
                SessionProvider.user= task.result?.toObject(User::class.java)
                navigateToView.postValue(NavigateToView.NavigateToHome)
            }
            else{
                messageLiveData.postValue(
                    Message(
                        message = task.exception?.localizedMessage)
                )
            }

        }
    }
}