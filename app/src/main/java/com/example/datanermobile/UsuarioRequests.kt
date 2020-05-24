package com.example.datanermobile

import feign.RequestLine

interface UsuarioRequests {

    @RequestLine("POST /login")
    fun postUsuario(novoUsuario: Usuario) : Usuario
}