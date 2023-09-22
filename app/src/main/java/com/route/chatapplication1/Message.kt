package com.route.chatapplication1

data class Message (
    var message:String?=null,
    var posActionName:String?=null,
    var posAction: OnDialogActionClick?=null,
    var negActionName:String?=null,
    var negAction: OnDialogActionClick?=null,
    var isCancelable:Boolean=true)

fun interface OnDialogActionClick{
    fun onActionClick()
}