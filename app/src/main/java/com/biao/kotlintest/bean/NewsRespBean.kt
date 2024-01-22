package com.biao.kotlintest.bean

data class NewsRespBean(
    val error_code: Int,
    val reason: String,
    val result: Result
)

data class Result(
    val `data`: MutableList<Data>,
    val page: String,
    val pageSize: String,
    val stat: String,
    val content: String,
    val detail: Detail,
    val uniquekey: String
)

data class Data(
    val author_name: String,
    val category: String,
    val date: String,
    val is_content: String,
    val thumbnail_pic_s: String,
    val thumbnail_pic_s02: String,
    val thumbnail_pic_s03: String,
    val title: String,
    val uniquekey: String,
    val url: String
)

data class DetailsRespBean(
    val error_code: Int,
    val reason: String,
    val result: Result
)

data class Detail(
    val author_name: String,
    val category: String,
    val date: String,
    val thumbnail_pic_s: String,
    val thumbnail_pic_s02: String,
    val thumbnail_pic_s03: String,
    val title: String,
    val url: String
)
