package dom.simple;

import java.awt.Container;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.ObjectType;
import org.joda.time.LocalDate;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({ 
	@javax.jdo.annotations.Query(name = "findMateriaCalificacionPorAlumno", 
			language = "JDOQL", 
			value = "SELECT FROM dom.simple.Periodo"
					+" WHERE this.nombre == :nombre ")
				
})

@ObjectType("Instancia de calificación")
@Bookmarkable
@Bounded
public class Periodo implements Comparable<Periodo>{
	
	//Cargar Alumnos y Materias
	/*@MemberOrder(sequence = "1", name = "Agregar Alumnos")
	@Named("Alumnos por Curso")
	public Periodo cargarAlumnoMateria(@Named("Curso") Curso inCurso, 
									   @Named("Alumno") Alumno inAlumno, 
									   @Named("Materia") Materia inMateria){
		
		agregarAlumno(inAlumno);		
		//agregarMaterias(inMateria);		
				
		return this;
	}*/
	
	//Choices cargarAlumnoMateria 
	////////////////////////////////////////////
	/*public List<Curso> choices0CargarAlumnoMateria(){
		return container.allInstances(Curso.class);
	}
	
	public List<Alumno> choices1CargarAlumnoMateria(){
		return container.allInstances(Alumno.class);
	}
	
	public List<Materia> choices2CargarAlumnoMateria(){
		return container.allInstances(Materia.class);
	}*/
	
	/////////////////////////////////////////////
	
	// {{ Nombre (property)
	private String nombre;

	@Named("Instancia")	
	@Column(allowsNull = "true")
	@MemberOrder(sequence = "1")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}
	// }}


	// {{ ListAlumnoCalificacion (property)
	@Element(column = "id_periodo", dependent = "true")
	private	List<AlumnoCalificacion> listAlumnoCalificacion = new ArrayList<AlumnoCalificacion>();

	
	@Named("Calificaciones por alumno")	
	@Column(allowsNull = "true")
	@MemberOrder(sequence = "2")
	public List<AlumnoCalificacion> getListAlumnoCalificacion() {
		return listAlumnoCalificacion;
	}

	public void setListAlumnoCalificacion(final List<AlumnoCalificacion> listAlumnoCalificacion) {
		this.listAlumnoCalificacion = listAlumnoCalificacion;
	}
	// }}
	
	// {{ FechaInicio (property)
	private LocalDate fechaInicio;


	@Named("Inicio")	
	@Column(allowsNull = "true")
	@MemberOrder(sequence = "3")
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(final LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	// }}

	// {{ FechaFinal (property)
	private LocalDate fechaFinal;	

	@Named("Cierre")	
	@Column(allowsNull = "true")
	@MemberOrder(sequence = "4")	
	public LocalDate getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(final LocalDate fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	// }}

	
	public String title(){
		return nombre.toString();
	}
	
	@Override
	public int compareTo(Periodo arg0) {
		// TODO Auto-generated method stub
		return 0;
	}	
	
	
	@javax.inject.Inject 
	DomainObjectContainer container;
	
	@javax.inject.Inject 
    dom.simple.AlumnoCalificacion alumnoCalificacion; 
	
	@javax.inject.Inject 
    dom.simple.CursoRepositorio cursoRepositorio;
	
	@javax.inject.Inject 
    dom.simple.MateriaCalificacion materiaCalificacion;

	 
}
