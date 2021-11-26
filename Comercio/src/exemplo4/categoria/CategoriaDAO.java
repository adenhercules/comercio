/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exemplo4.categoria;

import exemplo4.categoria.Categoria;
import exemplo4.util.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;


public class CategoriaDAO {
  private Session sessao;
  private Transaction transacao;
  
  public void salvar(Categoria categoria){
        try{
            this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            this.transacao = this.sessao.beginTransaction();
            this.sessao.save(categoria);
            this.transacao.commit();
        }catch (HibernateException e){
            System.out.println("Não foi possivel inserir o Categoria."+"Erro: " + e.getMessage());
        }finally{
            try{
                if(this.sessao.isOpen()){
                    this.sessao.close();
                }
            }catch (Throwable e){
                System.out.println("Erro ao fechar operacao de insercao de Categoria." + "Msg: "+e.getMessage());
            }
        }
    }
    
    public void atualizar(Categoria categoria){
        try{
            this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            this.transacao = this.sessao.beginTransaction();
            this.sessao.update(categoria);
            this.transacao.commit();
        }catch (HibernateException e){
            System.out.println("Não foi possivel alterar o Categoria."+"Erro: " + e.getMessage());
        }finally{
            try{
                if(this.sessao.isOpen()){
                    this.sessao.close();
                }
            }catch (Throwable e){
                System.out.println("Erro ao fechar operacao de atualizacao de Categoria." + "Msg: "+e.getMessage());
            }
        }
    }
   
    public void excluir(Categoria categoria){
        try{
            this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            this.transacao = this.sessao.beginTransaction();
            this.sessao.delete(categoria);
            this.transacao.commit();
        }catch (HibernateException e){
            System.out.println("Não foi possivel excluir o Categoria."+"Erro: " + e.getMessage());
        }finally{
            try{
                if(this.sessao.isOpen()){
                    this.sessao.close();
                }
            }catch (Throwable e){
                System.out.println("Erro ao fechar operacao de exclusao de Categoria." + "Msg: "+e.getMessage());
            }
        }
    }
    
    public Categoria buscaCategoria(String cat){
        Categoria categoria = null;
        try{
            this.sessao =  HibernateUtil.getSessionFactory().getCurrentSession();
            this.transacao = this.sessao.beginTransaction();
            Criteria filtro = this.sessao.createCriteria(Categoria.class);
            filtro.add(Restrictions.eq("descricao", cat));
            categoria = (Categoria) filtro.uniqueResult();
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
                System.out.println("Erro ao fechar operacao de bussca de Categoria." + "Msg: "+e.getMessage());
            }
        }
        return categoria;
    }
    
    public List<Categoria> listar(){
        List<Categoria> categorias = null;
        
        try{
            this.sessao =  HibernateUtil.getSessionFactory().getCurrentSession();
            this.transacao = this.sessao.beginTransaction();
            Criteria filtro = this.sessao.createCriteria(Categoria.class);
            //filtro.add(Restrictions.eq("categoria", codigo));
            categorias = filtro.list();
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
                System.out.println("Erro ao fechar operacao de listagem de Categorias." + "Msg: "+e.getMessage());
            }
        }
        return categorias;
    }
    
}
  

