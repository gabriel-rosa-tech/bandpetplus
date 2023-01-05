package com.br.bandtec.projetobandpetjava.controle;

import com.br.bandtec.projetobandpetjava.dominio.Anexo;
import com.br.bandtec.projetobandpetjava.repositorio.AnexoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@RestController
@RequestMapping("/arquivos")
public class ArquivosController {

    @Autowired
    private AnexoRepository repository;

    /*
    Upload usando MultipartFile. Características:
    1 - Recebe arquivo vindo de uma tag <input type="file"> numa <form> em um HTML
    2 - Conseguimos obter o tipo de arquivo (content type)
    3 - Conseguimos recuperar o nome original do arquivo

    Usamos essa técnica quando queremos obter não só o conteúdo, mas alguns detalhes do arquivo

    No exemplo abaixo, a tag ficaria:
    <input type="file" name="arquivo">
         */

    @PostMapping
    public ResponseEntity enviar(@RequestParam MultipartFile arquivo) throws IOException {
        // recuperando o nome original do arquivo
        System.out.println("Recebendo um arquivo de nome: " + arquivo.getOriginalFilename());

        // recuperando o tipo do arquivo
        System.out.println("Recebendo um arquivo do tipo: " + arquivo.getContentType());

        // recuperando o conteúdo do arquivo
        byte[] conteudo = arquivo.getBytes();

        // aqui estamos gravando o arquivo na pasta de trabalho da API
        Path path = Paths.get(arquivo.getOriginalFilename());
        Files.write(path, conteudo);

        return ResponseEntity.status(201).build();
    }

    @PostMapping("/anexo")
    public ResponseEntity enviarAnexo(@RequestParam MultipartFile arquivo) throws IOException {

        Anexo novoAnexo = new Anexo();
        novoAnexo.setNomeArquivo(arquivo.getOriginalFilename());
        novoAnexo.setConteudoArquivo(arquivo.getBytes());
        novoAnexo.setTipoArquivo(arquivo.getContentType());

        repository.save(novoAnexo);

        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity download(@PathVariable int id) {
        Optional<Anexo> anexoOptional = repository.findById(id);
        if (anexoOptional.isPresent()) {
            Anexo anexo = anexoOptional.get();

            return ResponseEntity.status(200)
                    .header("content-type", anexo.getTipoArquivo())
                    .header("content-disposition", "filename=" + anexo.getNomeArquivo())
                    .body(anexo.getConteudoArquivo());
            /*
            Nos inserirmos 2 headers (cabeçalhos) de resposta usando campos do Anexo
            Content-Type -> Para indicar o tipo do arquivo para download (ou exibição, em caso de imagens, por exemplo)
            Content-Disposition -> Para indicar o nome do arquivo que será sugerido ao navegador
             */
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}