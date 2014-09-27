package dom.simple;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({ 
	@javax.jdo.annotations.Query(name = "findMateriaCalificacionPorAlumno", 
			language = "JDOQL", 
			value = "SELECT FROM dom.simple.MateriaCalificacion"
					+" WHERE this.alumno.dni == :dni ")
				
})

@Bookmarkable
@MemberGroupLayout(columnSpans = {12,0,0,12})
public class MateriaCalificacion {
	
	// {{ Materia (property)
	@Element(column = "id_matCalificacion", dependent = "true")
	private SortedSet<Materia> materia = new TreeSet<Materia>();
	
	@Named("Materia")	
	@Column(allowsNull = "true")
	@MemberOrder(sequence = "1")
	public SortedSet<Materia> getMateria() {
		return materia;
	}

	public void setMateria(final SortedSet<Materia> materia) {
		this.materia = materia;
	}
	// }}
	
	// {{ Nota (property)
	private int nota;

	@Named("Nota")
	@MemberOrder(sequence = "2")
	@Column(allowsNull = "true")
	public int getNota() {
		return nota;
	}

	public void setNota(final int nota) {
		this.nota = nota;
	}
	// }}
	
	// {{ Alumno (property)
	private Alumno alumno;

	@Named("Alumno")
	@MemberOrder(sequence = "3")
	@Column(allowsNull = "true")
	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(final Alumno alumno) {
		this.alumno = alumno;
	}
	// }}
	
	// {{ Observacion (property)
	private String observacion;

	@Named("Observación")
	@MemberOrder(sequence = "4")
	@Column(allowsNull = "true")
	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(final String observacion) {
		this.observacion = observacion;
	}
	// }}


	public String title(){
		return alumno.title() + "- Materias: " + String.valueOf(materia.size()) + "- Nota: " + String.valueOf(nota);
	}


	@javax.inject.Inject
	DomainObjectContainer container;
	
	@javax.inject.Inject
	Curso curso;
}
