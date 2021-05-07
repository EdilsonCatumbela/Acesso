package ao.co.catumbela.acesso.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ao.co.catumbela.acesso.models.Usuario;
import ao.co.catumbela.acesso.repository.UsuarioRepository;

@Controller
public class IndexController {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("")
	private String index() {
		return "index";
	}

	@GetMapping("/registro")
	public String mostrarFormulario(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "cadastroUsuario";

	}
	
	@PostMapping("/process_register")
	public String processRegister(Usuario usuario) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String senha = passwordEncoder.encode(usuario.getSenha());
	    usuario.setSenha(senha);
	     
	    usuarioRepository.save(usuario);	     
	    return "mensagem_sucesso";
	}
	
	@GetMapping("/usuarios")
	public String listUsers(Model model) {
	    List<Usuario> listaUsuarios = usuarioRepository.findAll();
	    model.addAttribute("listaUsuarios", listaUsuarios);
	     
	    return "usuarios";
	}

}
