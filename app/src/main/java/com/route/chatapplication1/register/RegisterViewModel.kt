package com.route.chatapplication1.register
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

import com.route.chatapplication1.Message
import com.route.chatapplication1.NavigateToView
import com.route.chatapplication1.SessionProvider
import com.route.chatapplication1.SingleLiveEvent
import com.route.chatapplication1.firestore.Doa
import com.route.chatapplication1.firestore.model.User

class RegisterViewModel :ViewModel(){
    var userName=MutableLiveData<String?>()
    var email=MutableLiveData<String?>()
    var password=MutableLiveData<String?>()
    var confirmPassword=MutableLiveData<String?>()
    var userNameError=MutableLiveData<String?>()
    var emailError=MutableLiveData<String?>()
    var passwordError=MutableLiveData<String?>()
    var confirmPasswordError=MutableLiveData<String?>()
    var isLoading=MutableLiveData<Boolean?>()
    private var auth=FirebaseAuth.getInstance()
    var messageLiveData=SingleLiveEvent<Message>()
    var navigateToView= SingleLiveEvent<NavigateToView>()

    private fun validate(): Boolean {
        var isValid=true
        if (userName.value.isNullOrBlank()){
            userNameError.postValue("please enter user name")
            isValid=false
        } else{
            userNameError.postValue(null)
        }
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
        if (confirmPassword.value.isNullOrBlank()){
            confirmPasswordError.postValue("please enter confirmation of password")
            isValid=false
        } else if (password.value.equals(confirmPassword.value)) {
            confirmPasswordError.postValue(null)
        } else{
            confirmPasswordError.postValue("password not match")
            isValid=false
        }
        return isValid
    }

    fun register(){

        if(validate()){
            isLoading.postValue(true)
            auth.createUserWithEmailAndPassword(email.value!!,password.value!!)//this method return some thing of type Task<AuthResult>
                //بترجع اوبجكت يعني فيه نتيجه اللي حصل
                .addOnCompleteListener{task->

                    if (task.isSuccessful){
                        //insert user to database
                        insertUserToFireStore(task.result.user!!.uid )
                    }
                    else
                    {
                        //show error
                        isLoading.postValue(false)
                        messageLiveData.postValue(Message(
                            message = task.exception?.localizedMessage))
                    }
                }

        }
        else
            return
    }

    private fun insertUserToFireStore(uid: String) {
        val user = User(id = uid, name = userName.value, email = email.value)
        Doa.createUser(user){ task->

            isLoading.postValue(false)
            if (task.isSuccessful){
                messageLiveData.postValue(Message(
                    message = "user registered successfully",
                    posActionName = "ok",
                    posAction = {

                        //save user?imagine if you want to get id of user should i get it from the database?
                        //it will take time so i want to save the user in memory to use it in run time
                        //while the application is working
                        //navigate to home
                        SessionProvider.user =user
                        navigateToView.postValue(NavigateToView.NavigateToHome)
                    }
                ))

            }
            else{
                messageLiveData.postValue(Message(
                    message = task.exception?.localizedMessage))
            }
        }
    }


}