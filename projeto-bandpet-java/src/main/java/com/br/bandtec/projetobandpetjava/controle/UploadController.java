package com.br.bandtec.projetobandpetjava.controle;

import com.br.bandtec.projetobandpetjava.dominio.AnimalCliente;
import com.br.bandtec.projetobandpetjava.dominio.ClientesPetShop;
import com.br.bandtec.projetobandpetjava.dominio.PetShop;
import com.br.bandtec.projetobandpetjava.repositorio.AnimalClienteRepository;
import com.br.bandtec.projetobandpetjava.repositorio.ClientesPetShopRepository;
import com.br.bandtec.projetobandpetjava.repositorio.PetShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    ClientesPetShopRepository repositoryCliente;

    @Autowired
    AnimalClienteRepository repositoryAnimal;

    @Autowired
    PetShopRepository repositoryPetShop;

    @PostMapping("/{id}")
    public ResponseEntity leArquivo(@RequestParam MultipartFile arquivo,
                                    @PathVariable int id) throws IOException {
        System.out.println("Recebendo um arquivo do nome: " + arquivo.getOriginalFilename());
        System.out.println("Recebendo um arquivo do tipo: " + arquivo.getContentType());

        byte[] conteudo = arquivo.getBytes();

        Path path = Paths.get(arquivo.getOriginalFilename());
        Files.write(path, conteudo);

        BufferedReader entrada = null;
        String registro;
        String tipoRegistro;
        int contRegistro=0;
        Optional<PetShop> petShop = repositoryPetShop.findById(id);
        boolean status = false;

        ClientesPetShop cliente = new ClientesPetShop();
        cliente.setFkPetshop(petShop.get());
        try {
            entrada = new BufferedReader(new FileReader(arquivo.getOriginalFilename()));
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

        // Lê os registros do arquivo
        try {
            // Lê um registro
            registro = entrada.readLine();

            while (registro != null) {
                tipoRegistro = registro.substring(0, 2);

                //Lote
                if (tipoRegistro.equals("01")) {
                    System.out.println("Header");
                    System.out.println("Tipo de arquivo: " + registro.substring(2, 20));
                    System.out.println("Data de geração do arquivo: " + registro.substring(21,30));
                    System.out.println("Versão do layout: " + registro.substring(31,32));
                }
                else if(tipoRegistro.equals("02")){
                    cliente.setNomeCliente(registro.substring(05, 49).trim());
                    System.out.println(cliente.getNomeCliente());

                    cliente.setServicoUsado(Integer.parseInt(registro.substring(50, 51)));
                    System.out.println(cliente.getServicoUsado());

                    cliente.setTelefoneCelular(registro.substring(52, 64).trim());
                    System.out.println(cliente.getTelefoneCelular());

                    cliente.setTelefoneResidencial(registro.substring(65, 76).trim());
                    System.out.println(cliente.getTelefoneResidencial());

                    cliente.setEndereco(registro.substring(77, 121).trim());
                    System.out.println(cliente.getEndereco());

                    cliente.setNumero(Integer.parseInt(registro.substring(122, 125)));
                    System.out.println(cliente.getNumero());

                    cliente.setComplemento(registro.substring(125, 140).trim());
                    System.out.println(cliente.getComplemento());

                    repositoryCliente.save(cliente);
                    //Aumenta registro
                    contRegistro++;
                }
                else if(tipoRegistro.equals("03")){
                    AnimalCliente animal = new AnimalCliente();

                    //animal.setIdAnimal(Integer.parseInt(registro.substring(03, 04)));
                    animal.setNomeAnimal(registro.substring(07, 21).trim());
                    System.out.println(animal.getNomeAnimal());

                    animal.setRaca(registro.substring(22, 36).trim());
                    System.out.println(animal.getRaca());

                    animal.setPorte(registro.substring(36, 43).trim());
                    System.out.println(animal.getPorte());

                    animal.setTipo(registro.substring(43, 51).trim());
                    System.out.println(animal.getTipo());

                    animal.setDataNasc(LocalDate.parse(registro.substring(51, 61)));
                    System.out.println(animal.getDataNasc());

                    animal.setGenero(registro.substring(61, 70).trim());
                    System.out.println(animal.getGenero());
                    animal.setFkCliente(cliente);

                    repositoryAnimal.save(animal);

                    //Aumenta registro
                    contRegistro++;
                }
                else if (tipoRegistro.equals("04")){
                    System.out.println("\nTrailer");
                    int qtdRegistro = Integer.parseInt(registro.substring(3,6));
                    if (qtdRegistro == contRegistro) {
                        System.out.println("Quantidade de registros gravados compatível com quantidade lida");
                        status = true;
                    }
                    else {
                        System.out.println("Quantidade de registros gravados não confere com quantidade lida");
                    }
                }
                else {
                    System.out.println("Tipo de registro inválido");
                }
                registro = entrada.readLine();
            }
            entrada.close();
        }   catch (IOException ioException) {
                ioException.printStackTrace();
        // Fecha o arquivo}
        }
        Files.delete(path);
        if(status){
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(400).build();
        //return ResponseEntity.status(200).body("Funcionou, olha lá ->");//Alterar aqui
    }

//    @PostMapping
//    public ResponseEntity enviar(@RequestParam MultipartFile arquivo) throws IOException {
//        System.out.println("Recebendo um arquivo do nome: " + arquivo.getOriginalFilename());
//        System.out.println("Recebendo um arquivo do tipo: " + arquivo.getContentType());
//
//        byte[] conteudo = arquivo.getBytes();
//
//        Path path = Paths.get(arquivo.getOriginalFilename());
//        Files.write(path, conteudo);
//
//        return ResponseEntity.status(200).build();
//    }
}
