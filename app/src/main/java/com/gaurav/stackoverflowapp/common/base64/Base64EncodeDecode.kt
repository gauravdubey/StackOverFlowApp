package com.gaurav.stackoverflowapp.common.base64

import java.util.Base64

object Base64EncodeDecode {
    fun String.encodeBase64(): String = Base64.getUrlEncoder().encodeToString(this.toByteArray())

    fun String.decodeBase64(): String = String(Base64.getUrlDecoder().decode(this))

}