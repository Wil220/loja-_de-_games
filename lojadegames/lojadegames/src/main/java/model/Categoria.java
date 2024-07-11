package model;

	import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

	@Entity
	@Table (name = "tb_categorias")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public class Categoria {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@NotBlank(message = "O atributo Gênero é Obrigatório!")
		private String genero;
		
		@OneToMany(fetch = FetchType.LAZY, mappedBy = "categorias", cascade = CascadeType.REMOVE)
		@JsonIgnoreProperties("categorias")
		private List<Produto> produto;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getGenero() {
			return genero;
		}

		public void setGenero(String genero) {
			this.genero = genero;
		}

		public List<Produto> getProduto() {
			return produto;
		}

		public void setProduto(List<Produto> produto) {
			this.produto = produto;
			
			
		}
		
		

	}
