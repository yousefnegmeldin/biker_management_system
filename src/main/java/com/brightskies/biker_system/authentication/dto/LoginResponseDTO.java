package com.brightskies.biker_system.authentication.dto;

public record LoginResponseDTO(String token, long expiresIn) {
}
