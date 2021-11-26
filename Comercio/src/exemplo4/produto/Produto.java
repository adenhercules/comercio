package exemplo4.produto;

import exemplo4.categoria.Categoria;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto implements Serializable{
    
    @Id
    @GeneratedValue
    @Column(name = "cod_produto")
    private Integer produto;
    
    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "preco")
    private float preco;

    @ManyToOne
    @JoinColumn(name="cod_Categoria")
    private Categoria fk_categoria;

    public Categoria getFk_categoria() {
        return fk_categoria;
    }

    public void setFk_categoria(Categoria fk_categoria) {
        this.fk_categoria = fk_categoria;
    }
    
    public Integer getProduto() {
        return produto;
    }

    public String getDescricao() {
        return descricao;
    }

    public float getPreco() {
        return preco;
    }

    public void setProduto(Integer produto) {
        this.produto = produto;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.produto);
        hash = 89 * hash + Objects.hashCode(this.descricao);
        hash = 89 * hash + Objects.hashCode(this.preco);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.descricao, other.descricao))
        {
            return false;
        }
        if (!Objects.equals(this.produto, other.produto))
        {
            return false;
        }
        if (!Objects.equals(this.preco, other.preco))
        {
            return false;
        }
        return true;
    } 
    
}