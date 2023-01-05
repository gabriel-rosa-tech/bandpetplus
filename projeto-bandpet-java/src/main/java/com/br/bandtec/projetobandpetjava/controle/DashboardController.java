package com.br.bandtec.projetobandpetjava.controle;

import com.br.bandtec.projetobandpetjava.adapter.Entrada;
import com.br.bandtec.projetobandpetjava.adapter.MetodoPagamento;
import com.br.bandtec.projetobandpetjava.adapter.Receita;
import com.br.bandtec.projetobandpetjava.dominio.Custo;
import com.br.bandtec.projetobandpetjava.dominio.OrdemServico;
import com.br.bandtec.projetobandpetjava.repositorio.CustoRepository;
import com.br.bandtec.projetobandpetjava.repositorio.OrdemServicoRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    OrdemServicoRepository repository;

    @Autowired
    CustoRepository repositoryCusto;


    @GetMapping("/{id}")
    public ResponseEntity getOrdem(@PathVariable Integer id,
                                   @RequestParam String inicio,
                                   @RequestParam String fim) {

        List<OrdemServico> lista = repository.finByAllOrdem(id, inicio, fim);
        if (lista.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        Double valorTotal = 0.0;
        Double valorMin = 1000.0;
        Double valorMax = 0.0;
        Integer qtdeAnimais = lista.size();
        //Efetuando valor de receita bruta
        for(OrdemServico ordem : lista){
            valorTotal += ordem.getValorPago();
            if (ordem.getValorPago() < valorMin){
                valorMin = ordem.getValorPago();
            }
            if (ordem.getValorPago() > valorMax){
                valorMax = ordem.getValorPago();
            }
        }
        //Lib do Google para conversão para JSON Object
        Gson g = new Gson();
        Entrada dashEntrada = new Entrada(valorTotal,qtdeAnimais,valorMin,valorMax);

        return ResponseEntity.status(200).body(g.toJson(dashEntrada));
    }

    @GetMapping("/receita/{id}")
    public ResponseEntity getReceita(@PathVariable Integer id,
                                   @RequestParam String inicio,
                                   @RequestParam String fim) {

        List<OrdemServico> lista = repository.finByAllOrdem(id, inicio, fim);
        List<Custo> listaCusto = repositoryCusto.
                findByFkPetshopIdPetshopAndDataConsumoBetween(id, inicio, fim);

        if (lista.isEmpty() && listaCusto.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        Double valorTotal = 0.0;
        Double totalCusto = 0.0;

        //Efetuando valor de receita bruta
        for(OrdemServico ordem : lista){
            valorTotal += ordem.getValorPago();
        }
        for (Custo custo : listaCusto) {
            totalCusto += custo.getValor();
        }

        Receita dashReceita = new Receita(valorTotal-totalCusto, valorTotal/lista.size());
        //Lib do Google para conversão para JSON Object
        Gson g = new Gson();
        return ResponseEntity.status(200).body(g.toJson(dashReceita));
    }

    @GetMapping("/metodo/{id}")
    public ResponseEntity getValorPorPagamento(@PathVariable Integer id,
                                               @RequestParam String inicio,
                                               @RequestParam String fim) {

        MetodoPagamento debito = new MetodoPagamento("Débito", 0.0);
        MetodoPagamento credito = new MetodoPagamento("Crédito", 0.0);
        MetodoPagamento pix = new MetodoPagamento("Pix", 0.0);
        MetodoPagamento cheque = new MetodoPagamento("Cheque", 0.0);
        MetodoPagamento dinheiro = new MetodoPagamento("Dinheiro", 0.0);

        List<OrdemServico> lista = repository.finByAllOrdem(id, inicio, fim);
        if (lista.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        for (OrdemServico ordem : lista) {
            switch (ordem.getMetodoPagamento()) {
                case "Débito": debito.setValor(
                        debito.getValor() + ordem.getValorPago()
                ); break;
                case "Crédito": credito.setValor(
                        credito.getValor() + ordem.getValorPago()
                ); break;
                case "Pix": pix.setValor(
                        pix.getValor() + ordem.getValorPago()
                ); break;
                case "Cheque": cheque.setValor(
                        cheque.getValor() + ordem.getValorPago()
                ); break;
                case "Dinheiro": dinheiro.setValor(
                        dinheiro.getValor() + ordem.getValorPago()
                ); break;
                default: break;
            }
        }

        List<MetodoPagamento> listaPagamentos = new ArrayList<>();
        listaPagamentos.add(debito);
        listaPagamentos.add(credito);
        listaPagamentos.add(cheque);
        listaPagamentos.add(pix);
        listaPagamentos.add(dinheiro);

        Gson g = new Gson();

        return ResponseEntity.status(200).body(g.toJson(listaPagamentos));
    }

    @GetMapping("/custo/{id}")
    public ResponseEntity getCustos(@PathVariable Integer id,
                                    @RequestParam String inicio,
                                    @RequestParam String fim) {
        List<Custo> lista = repositoryCusto.
                findByFkPetshopIdPetshopAndDataConsumoBetween(id, inicio, fim);
        Double somaCustos = 0.0;

        if (lista.isEmpty()){
            return ResponseEntity.status(204).build();
        } else {
            for (Custo custo : lista) {
                somaCustos += custo.getValor();
            }
            return ResponseEntity.status(200).body(somaCustos);
        }
    }
}
