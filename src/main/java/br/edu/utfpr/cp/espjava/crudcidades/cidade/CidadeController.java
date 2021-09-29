package br.edu.utfpr.cp.espjava.crudcidades.cidade;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CidadeController {

    private final CidadeRepository cidadeRepository;
    private final EstadoRepository estadoRepository;

    public CidadeController(CidadeRepository cidadeRepository,
    						EstadoRepository estadoRepository) {
    	
        this.cidadeRepository = cidadeRepository;
        this.estadoRepository = estadoRepository;
    }
    

    @GetMapping("/")
    public String listar(Model memoria) {

        memoria.addAttribute("listaCidades", cidadeRepository
                                                    .findAll()
                                                    .stream()
                                                    .map(Cidade::clonar)
                                                    .collect(Collectors.toList()));
        
        memoria.addAttribute("listaEstados", estadoRepository
									        		.findAll()
									                .stream()
									                .map(Estado::clonar)
									                .collect(Collectors.toList()));

        return "/crud";
    }
    
/* //////////CRUD para Cidades///////////////// */
    @PostMapping("/criarCidade")
    public String criarCidade(@Valid Cidade cidade, BindingResult validacao, Model memoria) {

        if (validacao.hasErrors()) {
            validacao
                .getFieldErrors()
                .forEach(error -> 
                        memoria.addAttribute(
                            error.getField(), 
                            error.getDefaultMessage())
                        );


        } else if (cidadeRepository.findByNome(cidade.getNome()).isPresent()) {
        	
        } else {
        	cidadeRepository.save(cidade.clonar());
            return "redirect:/";
            
        }
        memoria.addAttribute("nomeInformado", cidade.getNome());
        memoria.addAttribute("estadoInformado", cidade.getEstado());
        memoria.addAttribute("listaCidades", cidadeRepository.findAll());
        
        return ("/crud");

    }

    @GetMapping("/excluirCidade")
    public String excluirCidade(
            @RequestParam String nome, 
            @RequestParam String estado) {
        
        var cidadeEstadoEncontrada = cidadeRepository.findByNomeAndEstado(nome, estado);

        cidadeEstadoEncontrada.ifPresent(cidadeRepository::delete);

        return "redirect:/";
    }

    @GetMapping("/preparaAlterarCidade")
    public String preparaAlterarCidade(
        @RequestParam String nome, 
        @RequestParam String estado,
        Model memoria) {

            var cidadeAtual = cidadeRepository.findByNomeAndEstado(nome, estado);

            cidadeAtual.ifPresent(cidadeEncontrada -> {
                memoria.addAttribute("cidadeAtual", cidadeEncontrada);
                memoria.addAttribute("listaCidades", cidadeRepository.findAll());
            });

            return "/crud";
    }

    @PostMapping("/alterarCidade")
    public String alterarCidade(
        @RequestParam String nomeAtual, 
        @RequestParam String estadoAtual,
        Cidade cidade) {

            var cidadeAtual = cidadeRepository.findByNomeAndEstado(nomeAtual, estadoAtual);

            if (cidadeAtual.isPresent()) {
                
                var cidadeEncontrada = cidadeAtual.get();
                cidadeEncontrada.setNome(cidade.getNome());
                cidadeEncontrada.setEstado(cidade.getEstado());

                cidadeRepository.saveAndFlush(cidadeEncontrada);
            }

            return "redirect:/";
    }


    /*//////////CRUD para Estados/////////////////*/  
     
    @PostMapping("/criarEstado")
    public String criarEstado(@Valid Estado estado, BindingResult validacao, Model memoria) {

        if (validacao.hasErrors()) {
            validacao
                .getFieldErrors()
                .forEach(error -> 
                        memoria.addAttribute(
                            error.getField(), 
                            error.getDefaultMessage())
                        );


        } else if (estadoRepository.findByNome(estado.getNome()).isPresent()) {
        	
        } else {
        	estadoRepository.save(estado.clonar());
            return "redirect:/";
            
        }
        memoria.addAttribute("nomeInformado", estado.getNome());
        memoria.addAttribute("estadoInformado", estado.getSigla());
        memoria.addAttribute("listaCidades", cidadeRepository.findAll());
        
        return ("/crud");

    }

    @GetMapping("/excluirEstado")
    public String excluirEstado(
            @RequestParam String nome, 
            @RequestParam String sigla) {
        
        var estadoEncontrado = estadoRepository.findByNomeAndSigla(nome, sigla);

        estadoEncontrado.ifPresent(estadoRepository::delete);

        return "redirect:/";
    }

    @GetMapping("/preparaAlterarEstado")
    public String preparaAlterarEstado(
        @RequestParam String nome, 
        @RequestParam String sigla,
        Model memoria) {

            var estadoAtual = estadoRepository.findByNomeAndSigla(nome, sigla);

            estadoAtual.ifPresent(estadoEncontrado -> {
                memoria.addAttribute("estadoAtual", estadoEncontrado);
                memoria.addAttribute("listaEstados", estadoRepository.findAll());
            });

            return "/crud";
    }

    @PostMapping("/alterarEstado")
    public String alterarEstado(
        @RequestParam String nomeAtual, 
        @RequestParam String siglaAtual,
        Estado estado) {

            var estadoAtual = estadoRepository.findByNomeAndSigla(nomeAtual, siglaAtual);

            if (estadoAtual.isPresent()) {
                
                var estadoEncontrado = estadoAtual.get();
                estadoEncontrado.setNome(estado.getNome());
                estadoEncontrado.setSigla(estado.getSigla());

                estadoRepository.saveAndFlush(estadoEncontrado);
            }

            return "redirect:/";
    }
}


