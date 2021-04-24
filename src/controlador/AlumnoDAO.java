package controlador;
import modelo.Alumno;
import conexionBD.ConexionBD;

//DAO - Data Access Object

public class AlumnoDAO {

	ConexionBD conexion;
	
	public AlumnoDAO() {
		conexion = new ConexionBD();
	}
	
	
	//Metodos para ALTAS, BJAS, CAMBIOS, CONSULTAS
	public boolean insertarRragistro(Alumno a) {
		boolean resultado = false;
		
		String sql="INSERT INTO Alumnos VALUES('"+a.getNumControl()+"', '"+a.getNombre()+"', '"+a.getPrimerAp()+"', '"+a.getSegundoAp()+"', '"+a.getEdad()+"', '"+a.getSemestre()+"', '"+a.getCarrera()+"');";
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	
}
