package com.aluraforo.foro.dto.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoRegistroTopico(
@NotBlank    
String titulo,
@NotBlank
String mensaje,
@NotNull
Long id_curso,
@NotNull
Long id_usuario
) {
}
