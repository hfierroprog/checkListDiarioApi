package cl.hfierroprog.chelistDiarioApi.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tarea database table.
 * 
 */
@Entity
@Table(name="tarea")
@NamedQuery(name="Tarea.findAll", query="SELECT t FROM Tarea t")
public class Tarea implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false)
	private Boolean completado;

	@Column(nullable=false, length=55)
	private String titulo;

	//bi-directional many-to-one association to Registro
	@ManyToOne
	@JoinColumn(name="registro_id", nullable=false)
	private Registro registro;

	public Tarea() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getCompletado() {
		return this.completado;
	}

	public void setCompletado(Boolean completado) {
		this.completado = completado;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Registro getRegistro() {
		return this.registro;
	}

	public void setRegistro(Registro registro) {
		this.registro = registro;
	}

}