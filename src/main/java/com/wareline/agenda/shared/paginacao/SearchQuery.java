package com.wareline.agenda.shared.paginacao;

import jakarta.validation.constraints.NotBlank;

public record SearchQuery(
        int page,
        int perPage,
        String terms,
        String sort,
        String direction
) {
}