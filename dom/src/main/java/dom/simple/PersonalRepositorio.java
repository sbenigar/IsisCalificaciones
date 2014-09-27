package dom.simple;

import java.util.ArrayList;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionSemantics;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.Mask;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.ActionSemantics.Of;
import org.apache.isis.applib.annotation.RegEx;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.value.DateTime;
import org.joda.time.LocalDate;

import dom.simple.Funcion.E_funciones;
import dom.simple.Localidad.E_localidades;
import dom.simple.Persona.E_nacionalidad;
import dom.simple.Persona.E_sexo;

@DomainService(menuOrder = "18", repositoryFor = Personal.class)
@Named("Personal Educativo")
public class PersonalRepositorio {

	public String getId() {
        return "personal";
    }

    public String iconName() {
        return "SimpleObject";
    }
    
        
    //Crear personal    
    @MemberOrder(sequence = "2")
    @Named ("Crear Personal")
    public Personal createPersonal(final @RegEx(validation = "[A-Za-z]+") @Named("Nombre") String nombre,
            final @RegEx(validation = "[A-Za-z]+") @Named("Apellido") String apellido,
            final @Named("Sexo") E_sexo sexo,
            final @RegEx(validation = "/d{6,10}") @Named("DNI") int dni, 
            final @Named("Fecha Nacimiento") LocalDate nacimiento,
            final @Named("Nacionalidad") E_nacionalidad nacionalidad,
            final @Named("Domicilio. Localidad") E_localidades localidad,
            final @Named("Domicilio. Calle") String calle,
            final @RegEx(validation = "/d{5}") @Named("Domicilio. Numero") int numero,
            final @RegEx(validation = "/d+")@org.apache.isis.applib.annotation.Optional @Named("Domicilio. Piso") String piso,
            final @org.apache.isis.applib.annotation.Optional @Named("Domicilio. Departamento") String departamento,
            @RegEx(validation = "/d+") @SuppressWarnings("deprecation") final @Mask("(NNNN)NNN-NNNNNNN") @org.apache.isis.applib.annotation.Optional @Named("TelÃ©fono") String telefono){
    	
    	final Personal newPersonal = container.newTransientInstance(Personal.class);
    	final Direccion newDireccion = new Direccion();
        final Localidad newLocalidad = new Localidad();
    	
        
        newLocalidad.setNombre(localidad);
        
        newDireccion.setCalle(calle.toUpperCase());
        newDireccion.setDepartamento(departamento);
        newDireccion.setLocalidad(newLocalidad);
        newDireccion.setNumero(numero);
        newDireccion.setPiso(piso);
        
        newPersonal.setApellido(apellido.toUpperCase());
        newPersonal.setDireccion(newDireccion);
        newPersonal.setDni(dni);
        newPersonal.setFechaNacimiento(nacimiento);
        newPersonal.setNacionalidad(nacionalidad);
        newPersonal.setNombre(nombre.toUpperCase());
        newPersonal.setSexo(sexo);
        newPersonal.setTelefono(telefono);
        
        container.persistIfNotAlready(newPersonal);        
    	return newPersonal;
    }
    
    //Validar createPersonal
    public String validateCreatePersonal(String nombre, String apellido, E_sexo sexo, int dni, LocalDate nacimiento, E_nacionalidad nacionalidad,
   		 E_localidades localidad, String calle, int numero, String piso, String departamento, String telefono)
	{	
    	List<Personal> dniPersonal = container.allMatches((new QueryDefault<Personal>(Personal.class, "findByDni", "dni", dni)));
    	if(!dniPersonal.isEmpty()){					
			return "El número de dni ya existe";			
		}
    	if (nacimiento.isAfter(LocalDate.now())){		
			return "La fecha de nacimiento debe ser menor al día actual";
		}
		return null;
		
	}
    
    // Listados  ////////////////////
        
    //Todo
    @Bookmarkable
    @ActionSemantics(Of.SAFE)
    @MemberOrder(sequence = "1")
    @Named ("Listar Personal")
    public List<Personal> listAll(){    	
    	return container.allInstances(Personal.class);
    }
    
    
    // Preceptores    
    @MemberOrder(sequence = "1.1")
    @ActionSemantics(Of.SAFE)
    @Named ("Preceptores")
    public List<Personal> listPreceptor(){
    	return container.allMatches(
    			new QueryDefault<Personal>(Personal.class, "findByFuncion", "nombre", E_funciones.PRECEPTOR.toString()));
    }
    
    // Profesores    
    @MemberOrder(sequence = "1.2")
    @Named ("Profesores")
    public List<Personal> listProfesor(){
    	return container.allMatches(
    			new QueryDefault<Personal>(Personal.class, "findByFuncion", "nombre", E_funciones.PROFESOR.toString()));
    }
    
    // Directores    
    @MemberOrder(sequence = "1.3")
    @Named ("Directores")
    public List<Personal> listDirector(){
    	return container.allMatches(
    			new QueryDefault<Personal>(Personal.class, "findByFuncion", "nombre", E_funciones.DIRECTOR.toString()));
    }
    
    // Secretarios    
    @MemberOrder(sequence = "1.4")
    @Named ("Secretarios")
    public List<Personal> listSecretario(){
    	return container.allMatches(
    			new QueryDefault<Personal>(Personal.class, "findByFuncion", "nombre", E_funciones.SECRETARIO.toString()));
    }    
    
    //  Fin Listados  ////////////////////
    
    
    // Eliminar Personal    
    @Hidden(where = Where.OBJECT_FORMS)    
    @ActionSemantics(Of.NON_IDEMPOTENT)
    @MemberOrder(sequence = "3")
    @Named("Eliminar Personal")    
    public String removePersonal(@Named("Personal") Personal delPersonal) {
    		String remPersonal = delPersonal.title();			
			container.removeIfNotAlready(delPersonal);			
			return  remPersonal + " fue eliminado";
			
	}
        
    
    @javax.inject.Inject 
    DomainObjectContainer container;
}
