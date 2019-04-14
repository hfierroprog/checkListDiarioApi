package cl.hfierroprog.chelistDiarioApi.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the pool database table.
 * 
 */
@Entity
@Table(name="pool")
@NamedQuery(name="Pool.findAll", query="SELECT p FROM Pool p")
public class Pool implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false, length=55)
	private String titulo;

	public Pool() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}