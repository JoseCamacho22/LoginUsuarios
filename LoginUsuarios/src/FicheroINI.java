import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FicheroINI {

	public void leer() {
		// TODO Auto-generated method stub
		Properties propiedades = new Properties();
		InputStream entrada = null;
		try {
			File miFichero = new File("configuracion.ini");
			if (miFichero.exists()) {

				entrada = new FileInputStream(miFichero);

				// cargamos el archivo de propiedades

				propiedades.load(entrada);

				// obtenemos las propiedades y las imprimimos

				System.out.println(propiedades.getProperty("url"));

				System.out.println(propiedades.getProperty("usuario"));

				System.out.println(propiedades.getProperty("password"));

			} else

				System.err.println("Fichero no encontrado");

		} catch (IOException ex) {

			ex.printStackTrace();

		} finally {

			if (entrada != null) {

				try {

					entrada.close();

				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}
	}

		
	}


