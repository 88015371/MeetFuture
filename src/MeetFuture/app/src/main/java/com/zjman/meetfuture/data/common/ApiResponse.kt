package com.zjman.meetfuture.data.common

/**
 * Created by zjman on 2018/5/17.
 */

class ApiResponse<T> {
    var code: Int = 0
    var status: Boolean? = false
    var data: T? = null

    val isSuccess: Boolean
        get() {
            if (this.status as Boolean) {
                return true
            } else {
                return false
            }
        }
}

