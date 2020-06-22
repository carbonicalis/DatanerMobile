package com.example.datanermobile

import feign.Headers
import feign.RequestLine

interface UsuarioRequests {

    @RequestLine("POST /login")
    @Headers("content-type: application/json")
    fun postUsuario(novoUsuario: Usuario) : Usuario
}