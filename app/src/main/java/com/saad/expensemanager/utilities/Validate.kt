package com.saad.expensemanager.utilities

object Validate {

    fun validateTitle(text: String): String {
        if (text.isNotEmpty()) {
            if (text.length > 15) {
                return "Title is too long.."
            } else if (!text.matches(Regex("^[a-zA-Z]+$"))) {
                return "Title must not contain any number or special character"
            }
        } else {
            return "Please enter Title"
        }
        return ""
    }

    fun validateName(text: String): String {
        if (text.isNotEmpty()) {
            if (text.length > 15) {
                return "Name is too long.."
            } else if (!text.matches(Regex("^[a-zA-Z]+$"))) {
                return "Name must not contain any number or special character"
            }
        } else {
            return "Please enter Name"
        }
        return ""
    }

    fun validateAmount(text: String): String {
        if (text.isNotEmpty()) {
            if (text.length < 2) {
                return "Please enter a valid amount"
            }
        } else {
            return "Please enter amount"
        }
        return ""
    }

    fun validateEmail(text: String): String {
        if (text.isNotEmpty()) {
            if (!text.matches(Regex("[a-zA-Z0-9._-]+@(gmail\\.com|yahoo\\.com)$"))) {
                return "Please enter valid email"
            }
        } else {
            return "Please enter email"
        }
        return ""
    }

    fun validatePhone(text: String): String {
        if (text.isNotEmpty()) {
            if (text.length > 12) {
                return "Please enter a valid number"
            }
        } else {
            return "Please enter phone number"
        }
        return ""
    }

    fun validatePassword(text: String): String {
        if (text.isNotEmpty()) {
            if (text.length < 9) {
                return "Password should be greater than 8"
            }
        } else {
            return "Please set a password"
        }
        return ""
    }

}