package com.example.customers.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

	private final KeycloakRoleConverter roleConverter;

	public JwtAuthConverter(KeycloakRoleConverter roleConverter) {
		this.roleConverter = roleConverter;
	}

	@Override
	public AbstractAuthenticationToken convert(Jwt jwt) {

		var authorities = roleConverter.convert(jwt);

		return new JwtAuthenticationToken(jwt, authorities, jwt.getClaimAsString("preferred_username"));
	}
}
