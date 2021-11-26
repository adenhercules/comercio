/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exemplo4.cadastro;

import exemplo4.categoria.Categoria;
import exemplo4.produto.Produto;
import exemplo4.categoria.CategoriaDAO;
import exemplo4.produto.ProdutoDAO;
import exemplo4.util.HibernateUtil;


public class Cadastro {
    
    public void cadastraProdutos(String prod, float preco, String cat){
        Produto produto = new Produto();
        ProdutoDAO prodDAO = new ProdutoDAO();
        CategoriaDAO catDAO = new CategoriaDAO();
        
        produto.setDescricao(prod);
        produto.setPreco(preco);
        produto.setFk_categoria(catDAO.buscaCategoria(cat));
        prodDAO.salvar(produto);
    }
    
    public void cadastraCategorias(String cat){
        Categoria categoria = new Categoria();
        CategoriaDAO catDAO = new CategoriaDAO();
        
        categoria.setDescricao(cat);
        catDAO.salvar(categoria);
    }
    
    public static void main (String[] args){
        HibernateUtil.getSessionFactory().openSession();
        
        Cadastro cadastro = new Cadastro();
        
        
        cadastro.cadastraCategorias("testou");
        cadastro.cadastraProdutos("Teste", 20, "testou");
        System.out.println("Cadastros gerados com sucesso");
    }
}

