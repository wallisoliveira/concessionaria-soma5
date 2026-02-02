package com.lojacarros.concessionaria.controller;

import com.lojacarros.concessionaria.model.Carro;
import com.lojacarros.concessionaria.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/carros")
@CrossOrigin("*") 
public class CarroController {

    @Autowired
    private CarroRepository repository;

    private static String CAMINHO_IMAGENS = "src/main/resources/static/uploads/";

    @GetMapping
    public List<Carro> listar() {
        return repository.findAll();
    }

    @PostMapping("/com-foto")
    public Carro salvarComFoto(
            @RequestParam("marca") String marca,
            @RequestParam("modelo") String modelo,
            @RequestParam("ano") Integer ano,
            @RequestParam("preco") Double preco,
            @RequestParam("descricao") String descricao,
            @RequestParam("foto") MultipartFile foto) throws IOException {

        Carro carro = new Carro();
        carro.setMarca(marca);
        carro.setModelo(modelo);
        carro.setAno(ano);
        carro.setPreco(preco);
        carro.setDescricao(descricao);

        if (foto != null && !foto.isEmpty()) {
            Path pasta = Paths.get(CAMINHO_IMAGENS);
            if (!Files.exists(pasta)) Files.createDirectories(pasta);

            String nomeArquivo = System.currentTimeMillis() + "_" + foto.getOriginalFilename();
            Path caminhoCompleto = pasta.resolve(nomeArquivo);
            Files.write(caminhoCompleto, foto.getBytes());
            
            carro.setLinkImagem("/uploads/" + nomeArquivo);
        }

        return repository.save(carro);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        repository.deleteById(id);
    }
}