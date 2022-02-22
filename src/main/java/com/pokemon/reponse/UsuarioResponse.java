package com.pokemon.reponse;


import java.util.ArrayList;
import java.util.List;

import com.pokemon.entity.Pokemon;
import com.pokemon.entity.Usuario;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioResponse {
	
	private Long id;
	
	private String nombre_team;
	
	private String nombre_entrenador;
	
	private String rol;
	
	private String usuario;
	
	private String password;
	
	private List<PokemonResponse> tipoPokemon;
	
	public UsuarioResponse (Usuario usuario) {
		this.id = usuario.getId();
		this.nombre_team = usuario.getNombre_team();
		this.nombre_entrenador = usuario.getNombre_entrenador();
		this.rol = usuario.getRol();
		this.usuario = usuario.getUsuario();
		this.password = usuario.getPassword();

		
		
		if (usuario.getTipoPokemon() != null) {
			tipoPokemon = new ArrayList<PokemonResponse>();
			for (Pokemon pokemon: usuario.getTipoPokemon()) {
				tipoPokemon.add(new PokemonResponse(pokemon));
			}
		}
	}


}
