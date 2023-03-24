package emergencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Principal {
	private static final String FICHERO_ACCIDENTES = "../ficherosEmergencias/accidentes.txt";
	private static final String FICHERO_HISTORIAL = "../ficherosEmergencias/historialAccidentes.txt";

	public Principal() {
		dinamizar();
	}

	public void dinamizar() {
		while (true) {
			try {
				Thread.sleep(3000);
				comprobar();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void comprobar() {
		try (BufferedReader in = new BufferedReader(new FileReader(FICHERO_ACCIDENTES))) {
			String linea;
			while ((linea = in.readLine()) != null) {
				String[] s = linea.split("[$]");
				System.out.println(linea);

				JOptionPane.showMessageDialog(null, "Accidente" + s[0] + " " + s[1] + " " + s[2], "ATENCION!!",
						JOptionPane.WARNING_MESSAGE);
				actualizarFicheros(linea);
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "fichero de accidentes no encontrado", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void actualizarFicheros(String linea) {
		actualizarHistorial(linea);
		borrarAccidentes();
	}
	
	public void borrarAccidentes() {
		try (BufferedWriter bw = new BufferedWriter((new FileWriter(FICHERO_ACCIDENTES)))) {
			bw.write("");
		} catch (FileNotFoundException e) {
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void actualizarHistorial(String linea) {
		try (BufferedWriter br = new BufferedWriter((new FileWriter(FICHERO_HISTORIAL, true)))) {
			br.write(linea + "\n");
		/*} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "fichero de historial no encontrado", "ERROR",
					JOptionPane.ERROR_MESSAGE);*/
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Principal programa = new Principal();
	}

}
