package dom.simple;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@Bookmarkable
public class AlumnoCalificacion {
	
	// {{ Alumno (property)
	private Alumno alumno;

	@Named("Alumno")	
	@Column(allowsNull = "true")
	@MemberOrder(sequence = "1")
	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(final Alumno alumno) {
		this.alumno = alumno;
	}
	// }}

	// {{ ListMateriaCalificacion (property)
	@Element(column = "id_alumnoCalificacion", dependent = "true")
	private SortedSet<MateriaCalificacion> listMateriaCalificacion = new TreeSet<MateriaCalificacion>();

	@Named("Materias")	
	@Column(allowsNull = "true")
	@MemberOrder(sequence = "2")
	public SortedSet<MateriaCalificacion> getListMateriaCalificacion() {
		return listMateriaCalificacion;
	}

	public void setListMateriaCalificacion(final SortedSet<MateriaCalificacion> listMateriaCalificacion) {
		this.listMateriaCalificacion = listMateriaCalificacion;
	}
	// }}

    // {{ Periodo (property)
	private Periodo periodo;

	@Named("Instancia")	
	@Column(allowsNull = "true")
	@MemberOrder(sequence = "3")
	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(final Periodo periodo) {
		this.periodo = periodo;
	}
	// }}
	
	
}
