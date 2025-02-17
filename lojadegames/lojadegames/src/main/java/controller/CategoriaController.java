package controller;

	
	import java.util.List;
	import java.util.Optional;

	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.DeleteMapping;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.PutMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.ResponseStatus;
	import org.springframework.web.bind.annotation.RestController;
	import org.springframework.web.server.ResponseStatusException;


	import jakarta.validation.Valid;
import model.Categoria;
import repository.CategoriaRepository;

	@RestController
	@RequestMapping("/categoria")
	public class CategoriaController {

		private CategoriaRepository categoriaRepository;

		@GetMapping
		public ResponseEntity<List<Categoria>> getAll() {	
			return ResponseEntity.ok(categoriaRepository.findAll());

		}

		@GetMapping("/{id}")
		public ResponseEntity<Categoria> getById(@PathVariable Long id) {
			return categoriaRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
					.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		}

		@GetMapping("/genero/{genero}")
		public ResponseEntity<List<Categoria>> getByTitle(@PathVariable String genero) {
			return ResponseEntity.ok(categoriaRepository.findAllByGeneroContainingIgnoreCase(genero));
		}

		@PostMapping
		public ResponseEntity<Categoria> post(@Valid @RequestBody Categoria categoria) {
			return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
		}

		@PutMapping
		public ResponseEntity<Categoria> put(@Valid @RequestBody Categoria categoria) {
			return categoriaRepository.findById(categoria.getId())
					.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria)))
					.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		}

		@ResponseStatus(HttpStatus.NO_CONTENT)
		@DeleteMapping("/{id}")
		public <categoriaRepository> void delete(@PathVariable Long id) {
			Optional<Categoria> tema = categoriaRepository.findById(id);

			if (tema.isEmpty())
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);

			categoriaRepository.deleteById(id);
		}
	}