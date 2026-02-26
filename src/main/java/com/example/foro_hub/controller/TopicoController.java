package com.example.foro_hub.controller;

import com.example.foro_hub.domain.topico.Topico;
import com.example.foro_hub.repository.TopicoRepository;
import com.example.foro_hub.entity.dto.DatosRegistroTopico;
import com.example.foro_hub.entity.dto.DatosActualizarTopico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    // ✅ POST - Crear tópico
    @PostMapping
    public ResponseEntity<?> registrarTopico(@RequestBody @Valid DatosRegistroTopico datos) {

        if (repository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            return ResponseEntity.badRequest().body("Tópico duplicado");
        }

        Topico topico = new Topico(
                null,
                datos.titulo(),
                datos.mensaje(),
                LocalDateTime.now(),
                "ABIERTO",
                datos.autor(),
                datos.curso()
        );

        Topico topicoGuardado = repository.save(topico);

        return ResponseEntity.status(201).body(topicoGuardado);
    }

    // ✅ GET - Listar todos
    @GetMapping
    public ResponseEntity<List<Topico>> listarTopicos() {
        return ResponseEntity.ok(repository.findAll());
    }

    // ✅ GET - Detallar por id
    @GetMapping("/{id}")
    public ResponseEntity<Topico> detallarTopico(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ PUT - Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarTopico(
            @PathVariable Long id,
            @RequestBody @Valid DatosActualizarTopico datos) {

        return repository.findById(id)
                .map(topico -> {

                    if (repository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())
                            && !(topico.getTitulo().equals(datos.titulo())
                            && topico.getMensaje().equals(datos.mensaje()))) {
                        return ResponseEntity.badRequest().body("Tópico duplicado");
                    }

                    topico.setTitulo(datos.titulo());
                    topico.setMensaje(datos.mensaje());
                    topico.setAutor(datos.autor());
                    topico.setCurso(datos.curso());

                    return ResponseEntity.ok(repository.save(topico));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ DELETE - Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}