package com.barberexperience.domain.valueobjects;

import lombok.Value;

@Value
public class NomeBarbearia {
    String value;

    public NomeBarbearia(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da barbearia não pode ser vazio");
        }
        this.value = value;
    }
} 