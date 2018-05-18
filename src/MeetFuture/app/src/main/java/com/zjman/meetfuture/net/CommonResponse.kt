package home.sixroom.com.myapplication.net

/**
 * Created by wukewei on 16/5/26.
 */
class CommonResponse {
    var code: Int = 0
    var status: Boolean? = false

    val isSuccess: Boolean
        get() {
            if (this.status!!) {
                return true
            } else {
                return false
            }
        }

    companion object {

        val SUCCESS_CODE = 200
    }
}
