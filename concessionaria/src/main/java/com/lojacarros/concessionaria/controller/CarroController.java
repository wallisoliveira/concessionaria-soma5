package com.lojacarros.concessionaria.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.lojacarros.concessionaria.model.Carro;
import com.lojacarros.concessionaria.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/admin")
public class CarroController {

    @Autowired
    private CarroRepository carroRepository;

    // Configuração Cloudinary com suas credenciais
    private Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "drumr69qc",
            "api_key", "862236548868838",
            "api_secret", "NMf7dz8ANk65iyWGEUiJnULymsk"
    ));

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Carro carro, 
                         @RequestParam("file") MultipartFile file, 
                         RedirectAttributes attributes) {
        try {
            if (file != null && !file.isEmpty()) {
                // Upload para Cloudinary
                Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
                String url = uploadResult.get("url").toString();
                carro.setImagemUrl(url);
            }

            carroRepository.save(carro);
            attributes.addFlashAttribute("mensagem", "Veículo salvo com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("erro", "Erro ao salvar veículo: " + e.getMessage());
        }
        return "redirect:/admin.html";
    }
}