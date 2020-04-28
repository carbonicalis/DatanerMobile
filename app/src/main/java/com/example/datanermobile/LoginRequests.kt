package com.example.datanermobile

import android.R.xml
import feign.*


interface LoginRequests {
    @RequestLine("POST /")
    @Headers("Content-Type: application/xml")
    @Body("<login \"user_name\"=\"{user_name}\" \"password\"=\"{password}\"/>")
    fun xml(@Param("user_name") user: String, @Param("password") password: String)

    @RequestLine("POST /")
    @Headers("Content-Type: application/json")
    // json curly braces must be escaped!
    @Body("%7B\"user_name\": \"{user_name}\", \"password\": \"{password}\"%7D")

    fun json(@Param("user_name") user: String, @Param("password") password: String)


public class Example {
    @JvmStatic
    fun main(args: Array<String>) {
        client.xml(
            "denominator",
            "secret"
        ) // <login "user_name"="denominator" "password"="secret"/>
        client.json("denominator", "secret") // {"user_name": "denominator", "password": "secret"}
    }
}}