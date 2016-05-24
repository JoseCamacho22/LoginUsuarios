
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		Modelo modelo =new Modelo();
		Bienvenida bienvenida=new Bienvenida();
		bienvenida.setVisible(true);
		Login login = new Login();
		modelo.EscribirINI();
		modelo.LeerINI();
		modelo.conexionBBDD();


	}

}
