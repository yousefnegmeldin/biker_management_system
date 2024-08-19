package com.brightskies.biker_system.Authentication.dto;

public record LoginResponseDTO(String token, long expiresIn) {
}
