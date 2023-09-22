package com.route.chatapplication1
import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity


fun AppCompatActivity.showDialog(
    errorMessageApi:String,
    posActionName:String?=null,
    posAction: OnDialogActionClick?=null,
    negActionName:String?=null,
    negAction:OnDialogActionClick?=null,
    isCancelable:Boolean=true)
:AlertDialog{
val dialog=AlertDialog.Builder(this)
    dialog.setMessage(errorMessageApi)
    if (posActionName!=null){
        dialog.setPositiveButton(posActionName){
            dialog,id->
            dialog.dismiss()
            posAction?.onActionClick()
        }
    }
   if (negActionName!=null){
        dialog.setPositiveButton(negActionName){
                dialog,id->
            dialog.dismiss()
            negAction?.onActionClick()
        }
    }
return dialog.show()


}