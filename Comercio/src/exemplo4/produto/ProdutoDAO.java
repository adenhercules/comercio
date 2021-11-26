/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exemplo4.produto;

import exemplo4.util.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;


public class ProdutoDAO {
    private Session sessao;
    private Transaction transacao;
    
    public void salvar(Produto produto){
        try{
            this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            this.transacao = this.sessao.beginTransaction();
            this.sessao.save(produto);
            this.transacao.commit();
        }catch (HibernateException e){
            System.out.println("Não foi possivel inserir o produto."+"Erro: " + e.getMessage());
        }finally{
            try{
                if(this.sessao.isOpen()){
                    this.sessao.close();
                }
            }catch (Throwable e){
                System.out.println("Erro ao fechar operacao de insercao de produto." + "Msg: "+e.getMessage());
            }
        }
    }
    
    public void atualizar(Produto produto){
        try{
            this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            this.transacao = this.sessao.beginTransaction();
            this.sessao.update(produto);
            this.transacao.commit();
        }catch (HibernateException e){
            System.out.println("Não foi possivel alterar o produto."+"Erro: " + e.getMessage());
        }finally{
            try{
                if(this.sessao.isOpen()){
                    this.sessao.close();
                }
            }catch (Throwable e){
                System.out.println("Erro ao fechar operacao de atualizacao de produto." + "Msg: "+e.getMessage());
            }
        }
    }
   
    public void excluir(Produto produto){
        try{
            this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            this.transacao = this.sessao.beginTransaction();
            this.sessao.delete(produto);
            this.transacao.commit();
        }catch (HibernateException e){
            System.out.println("Não foi possivel excluir o produto."+"Erro: " + e.getMessage());
        }finally{
            try{
                if(this.sessao.isOpen()){
                    this.sessao.close();
                }
            }catch (Throwable e){
                System.out.println("Erro ao fechar operacao de exclusao de produto." + "Msg: "+e.getMessage());
            }
        }
    }
    
    public Produto buscaProduto(Integer codigo){
        Produto produto = null;
        try{
            this.sessao =  HibernateUtil.getSessionFactory().getCurrentSession();
            this.transacao = this.sessao.beginTransaction();
            Criteria filtro = this.sessao.createCriteria(Produto.class);
            filtro.add(Restrictions.eq("produto", codigo));
            produto = (Produto) filtro.uniqueResult();
            this.transacao.commit();
        }catch (Throwable e){
            if (this.transacao.isActive()){
                this.transacao.rollback();
            }
        }finally{
            try{
                if(this.sessao.isOpen()){
                    this.sessao.close();
                }
            }catch (Throwable e){
                System.out.println("Erro ao fechar operacao de bussca de produto." + "Msg: "+e.getMessage());
            }
        }
        return produto;
    }
    
    public List<Produto> listar(){
        List<Produto> produtos = null;
        
        try{
            this.sessao =  HibernateUtil.getSessionFactory().getCurrentSession();
            this.transacao = this.sessao.beginTransaction();
            Criteria filtro = this.sessao.createCriteria(Produto.class);
            //filtro.add(Restrictions.eq("categoria", codigo));
            produtos = filtro.list();
            this.transacao.commit();
        }catch (Throwable e){
            if (this.transacao.isActive()){
                this.transacao.rollback();
            }
        }finally{
            try{
                if(this.sessao.isOpen()){
                    this.sessao.close();
                }
            }catch (Throwable e){
                System.out.println("Erro ao fechar operacao de listagem de produtos." + "Msg: "+e.getMessage());
            }
        }
        return produtos;
    }
    
}
