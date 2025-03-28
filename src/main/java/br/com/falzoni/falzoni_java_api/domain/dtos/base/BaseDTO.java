package br.com.falzoni.falzoni_java_api.domain.dtos.base;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public class BaseDTO {
    @Schema(description = "Id de registro", accessMode = Schema.AccessMode.READ_ONLY, example = "XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX")
    protected UUID id;
}
