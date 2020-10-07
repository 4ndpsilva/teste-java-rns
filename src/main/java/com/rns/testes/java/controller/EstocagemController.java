package com.rns.testes.java.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.rns.testes.java.dto.EstocagemDTO;
import com.rns.testes.java.dto.ProdutoResponseDTO;
import com.rns.testes.java.dto.TransferenciaDTO;
import com.rns.testes.java.exception.FilialAndOrProdutoNotFoundException;
import com.rns.testes.java.exception.ResponseMessage;
import com.rns.testes.java.mapper.EstocagemMapper;
import com.rns.testes.java.model.Estocagem;
import com.rns.testes.java.service.impl.EstocagemService;

@CrossOrigin
@RestController
@RequestMapping("/estocagem")
public class EstocagemController extends AbstractController<Estocagem, EstocagemDTO, Long>{
	@Autowired
    EstocagemService service;
	
    @Autowired
	private ResponseMessage responseMessage;
	
	public EstocagemController() {
    	super(new EstocagemMapper());
    }

    @GetMapping("/filial/{filialId}/produto/{produtoId}")
    public ResponseEntity<Estocagem> findByFilialAndProduto(@PathVariable Long filialId, @PathVariable String produtoId) {
        return ResponseEntity.ok(service.findByFilialAndProduto(filialId, produtoId));
    }
    
    @GetMapping("/filial/{filialId}")
    public ResponseEntity<List<Estocagem>> findByFilial(@PathVariable Long filialId) {
        return ResponseEntity.ok(service.findByFilial(filialId));
    }
    
    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<List<Estocagem>> findByProduto(@PathVariable String produtoId) {
        return ResponseEntity.ok(service.findByProduto(produtoId));
    }
    
    @PutMapping("/transferencias")
    public ResponseEntity<Estocagem> transferir(@RequestBody @Valid TransferenciaDTO dto) {
        return ResponseEntity.ok(service.transferir(dto.getFilialOrigemId(), dto.getFilialDestinoId(), dto.getProdutoId()));
    }
    
    @ExceptionHandler(FilialAndOrProdutoNotFoundException.class)
	public ResponseEntity<Object> handleFilialAndOrProdutoNotFound(FilialAndOrProdutoNotFoundException ex, WebRequest request){
		return responseMessage.error(HttpStatus.NOT_FOUND, "filial-and-or-produto-not-found");
	}
    
    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoResponseDTO>> findAllProdutos() {
    	List<Estocagem> list = service.findAll();
    	List<ProdutoResponseDTO> produtos = new ArrayList<>();
    	
    	for(Estocagem e : list) {
    		ProdutoResponseDTO produto = new ProdutoResponseDTO();
    		produto.setId(e.getProduto().getId());
    		produto.setNome(e.getProduto().getNome());
    		produto.setFilial(e.getFilial().getRazaoSocial());
    		produto.setCnpj(e.getFilial().getCnpj());
    		produto.setTipoFilial(e.getFilial().getTipoFilial().getDescricao());
    		produto.setLocal(e.getFilial().getEndereco());
    		produto.setQuantidade(e.getQuantidade());
    		produtos.add(produto);
    	}
    	
    	return ResponseEntity.ok(produtos);
    }
}