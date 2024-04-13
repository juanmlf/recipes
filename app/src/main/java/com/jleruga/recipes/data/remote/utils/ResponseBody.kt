package com.jleruga.recipes.data.remote.utils

import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.BufferedSource

class EmptyResponseBody : ResponseBody() {
    override fun contentLength(): Long {
        return 0
    }

    override fun contentType(): MediaType? {
        return null
    }

    override fun source(): BufferedSource {
        TODO("Not yet implemented")
    }
}