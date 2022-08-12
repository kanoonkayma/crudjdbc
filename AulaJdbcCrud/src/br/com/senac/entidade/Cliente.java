/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.entidade;

/**
 *
 * @author kayma.silva
 */
public class Cliente {
    
    private int id;
    private String nome;
    private String cpf;
    private String rg;
    private Double salario;

    public Cliente(String nome, String cpf, String rg, Double salario) {
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.salario = salario;
    }

    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
 
    
    
}
