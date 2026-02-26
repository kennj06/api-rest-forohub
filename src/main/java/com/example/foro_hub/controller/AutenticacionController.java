package com.example.foro_hub.controller;

import com.example.foro_hub.entity.dto.DatosAutenticacionUsuario;
import com.example.foro_hub.entity.usuario.Usuario;
import com.example.foro_hub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datos) {

        var authToken = new UsernamePasswordAuthenticationToken(
                datos.login(),
                datos.clave()
        );

        var authentication = authenticationManager.authenticate(authToken);

        var usuario = (Usuario) authentication.getPrincipal();

        var token = tokenService.generarToken(usuario);

        return ResponseEntity.ok(token);
    }
}