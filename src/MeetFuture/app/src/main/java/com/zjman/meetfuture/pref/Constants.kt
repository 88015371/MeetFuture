package com.zjman.meetfuture.pref

/**
 * Created by zjman on 2018/5/18.
 */

object Constants{


    var Base_Url:String = "http://minik.ycvod.cn/manage/v1/"

    const val HTTP_CONNECT_TIMEOUT :Long=  8000

    const val KEY = "key"


    const val MAIN_START = "MAIN_START"

    const  val PAGE_SIZE = 15

    const val DOWN_PATH = "/sixroom/music"//歌曲下载保存地址

    const val SAVE_LOCAL = "/sixVideo"

    const val SONG = "http://app.6room.com.cn/weixin/song/music"//点歌地址

    const val SHARE_MUSIC = "http://app.6room.com.cn/weixin/api/hear?id=%s"//点歌地址

    const val DEFAULT_HEAD_PIC_URL = "http://app.6room.com.cn/upload/image/group.png"//預設大頭照地址

    val FOLDER_NAME_APP = "Six"


    const val APP_IMAGE_BASE_WIDTH = 1080
    const val APP_IMAGE_BASE_HEIGHT = 1920


    val FOLDER_MY_WORK = "MyWork"   //  本地錄音/影 存放位置
    val FOLDER_SOURCE = "Sources"    // 下載的伴奏檔存放位址

    val FOLDER_NAME_VIDEO = "Video"
    val FOLDER_NAME_AUDIO = "Audio"
    val FOLDER_DEFAULT_MV = "DefaultMV"


}
