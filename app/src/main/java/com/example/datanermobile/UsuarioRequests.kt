package com.example.datanermobile

import feign.RequestLine

interface UsuarioRequests {

    @RequestLine("POST /users")
    fun postUsuario(novoUsuario: Usuario) : Usuario
}