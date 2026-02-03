package com.lojacarros.concessionaria.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.lojacarros.concessionaria.model.Carro;
import com.lojacarros.concessionaria.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/carros")
public class CarroController {

    @Autowired
    private CarroRepository carroRepository;

    private Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "drumr69qc",
            "api_key", "862236548868838",
            "api_secret", "NMf7dz8ANk65iyWGEUiJnULymsk"
    ));

    // Rota para listar carros (usada na função carregar() do JS)
    @GetMapping
    public List<Carro> listarTodos() {
        return carroRepository.findAll();
    }

    // Rota para salvar com foto (ajustada para o seu JavaScript)
    @PostMapping("/com-foto")
    public ResponseEntity<Carro> salvarComFoto(
            @RequestParam("marca") String marca,
            @RequestParam("modelo") String modelo,
            @RequestParam("ano") Integer ano,
            @RequestParam("preco") Double preco,
            @RequestParam("descricao") String descricao,
            @RequestParam("foto") MultipartFile foto) { // Nome 'foto' para bater com o JS
        
        try {
            Carro carro = new Carro();
            carro.setMarca(marca);
            carro.setModelo(modelo);
            carro.setAno(ano);
            carro.setPreco(preco);
            carro.setDescricao(descricao);

            if (foto != null && !foto.isEmpty()) {
                Map uploadResult = cloudinary.uploader().upload(foto.getBytes(), ObjectUtils.emptyMap());
                carro.setLinkImagem(uploadResult.get("url").toString());
            }

            Carro salvo = carroRepository.save(carro);
            return ResponseEntity.ok(salvo);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    // Rota para excluir (usada na função excluir() do JS)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        carroRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}