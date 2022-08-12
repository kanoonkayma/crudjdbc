/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.entidade;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author kayma.silva
 */

@Setter 
@Getter //pegar valor da memória
@NoArgsConstructor //gerar construtor sem parametro
@AllArgsConstructor //gerar construtor com 1 parametro ou mais

public class Usuario implements Serializable{
    //MVC
    private Integer id;
    private String nome;
    private String login;
    private String senha;
    private Date ultimoAcesso;
    
    //alt+insert -> pra gerar os getter and setter (quando não usar o lombok)
    
    /*
    //sem parametro -> construir objeto -> a classe vai usar o new pro construtor
    public Usuario(){
    }
    
    
    */
    
 
    
}
