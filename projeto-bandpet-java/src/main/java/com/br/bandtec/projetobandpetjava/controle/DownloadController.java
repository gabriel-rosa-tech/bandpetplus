package com.br.bandtec.projetobandpetjava.controle;

import com.br.bandtec.projetobandpetjava.dominio.Fornecedor;
import com.br.bandtec.projetobandpetjava.dominio.ListaObj;
import com.br.bandtec.projetobandpetjava.repositorio.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/download")
public class DownloadController {

    @Autowired
    FornecedorRepository repository;

    public void gravarDados(String nomeArquivo,String registro){
        BufferedWriter saida = null;
        try {
            saida = new BufferedWriter(new FileWriter(nomeArquivo, true));
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
        try {
            saida.append(registro + "\n");
            saida.close();

        } catch (IOException e) {
            System.err.printf("Erro ao gravar arquivo: %s.\n", e.getMessage());
        }
    }

    @GetMapping(value = "/{id}/fornecedor-txt", produces = "text/plain")
    @ResponseBody
    public ResponseEntity gravarFornecedoresTxt(@PathVariable int id){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=forcedores.txt");

        List<Fornecedor> fornecedores = repository.findByFkPetshopIdPetshop(id);

        String nomeArquivo = "fornecedores.txt";
        String header = "";
        String corpo = "";
        String trailer = "";
        int contRegDados = 0;

        Date dataDeHoje = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        header += "01FORNECEDORES";
        header += formatter.format(dataDeHoje);
        header += "01\n"; // barra invertida \n

        gravarDados(nomeArquivo, header);
        for (int i =0; i < fornecedores.size(); i++){
            Fornecedor fornecedor = fornecedores.get(i);

            corpo += "02";
            corpo += String.format("%02d",fornecedor.getIdFornecedor());
            corpo += String.format("%35s",fornecedor.getEmpresa());
            corpo += String.format("%12s",fornecedor.getTelefone());
            corpo += String.format("%40s",fornecedor.getEndereco());
            corpo += String.format("%40s",fornecedor.getEmail());
            corpo += String.format("%18s",fornecedor.getCnpj());
            corpo += String.format("%35s\n",fornecedor.getDescricao()); // barra invertida \n

            gravarDados(nomeArquivo, corpo);
            contRegDados++;
        }

        trailer += "03";
        trailer += String.format("%04d\n",contRegDados); // barra invertida \n
        gravarDados(nomeArquivo, trailer);

        return ResponseEntity.status(200).headers(headers).body(header + corpo + trailer);
    }

//    @GetMapping(value = "/fornecedor-csv", produces = "text/csv")
//    @ResponseBody
//    public ResponseEntity gravarFornecedoresCsv() {
//        List<Fornecedor> fornecedores = repository.findAll();
//        for(int i=0; i < fornecedores.size(); i++){
//            fornecedorList.adiciona(fornecedores.get(i));
//        }
//        FileWriter arq = null;
//        Formatter saida = null;
//        boolean deuRuim = false;
//        String nomeArquivo = "forncedores.csv";
//        String registro = "";
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=fornecedores.csv");
//        // defining the custom Content-Type
//        headers.set(HttpHeaders.CONTENT_TYPE, "text/csv");
//
//        try {
//            arq = new FileWriter(nomeArquivo, false);
//            saida = new Formatter(arq);
//        } catch (IOException erro) {
//            System.err.println("Erro ao abrir arquivo");
//            System.exit(1);
//        }
//
//        try {
//            for (int i = 0; i < fornecedorList.getTamanho(); i++) {
//                Fornecedor fornecedor = fornecedorList.getElemento(i);
//                registro += String.format("%d;%d;%s;%s;%s;%s;%s;%s;%d;%s%n", fornecedor.getIdFornecedor(),
//                        fornecedor.getFkPetshop(), fornecedor.getEmpresa(), fornecedor.getCnpj(),
//                        fornecedor.getDescricao(), fornecedor.getTelefone(), fornecedor.getEmail(),
//                        fornecedor.getEndereco(), fornecedor.getNumero(), fornecedor.getComplemento());
//            }
//        } catch (FormatterClosedException erro) {
//            System.err.println("Erro ao gravar no arquivo");
//            deuRuim = true;
//        } finally { // bloco finally é executado independente de dar erro ou não
//            // usado para fechar os objetos saida e close.
//            saida.close();
//            try {
//                arq.close();
//            } catch (IOException erro) {
//                System.err.println("Erro ao fechar arquivo.");
//                deuRuim = true;
//            }
//            if (deuRuim) {
//                System.exit(1);
//            }
//        }
//        return status(200).headers(headers).body(registro);
//    }
}
