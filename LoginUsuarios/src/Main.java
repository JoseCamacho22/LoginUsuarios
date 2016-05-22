
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FicheroINI fichero=new FicheroINI();
		fichero.leer();
		Modelo modelo =new Modelo();
		Bienvenida bienvenida=new Bienvenida();
		//modelo.conexionBBDD();
		bienvenida.setVisible(true);
		Login login = new Login();

	}

}
