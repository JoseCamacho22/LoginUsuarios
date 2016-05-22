import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;

public class TablaDatos extends JFrame {
	
	Modelo modelo = new Modelo();
	ResultSet rs = null;
	private JPanel contentPane;
	 JTable Table;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TablaDatos frame = new TablaDatos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TablaDatos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnNewButton_AÑADIR = new JButton("AÑADIR");
		btnNewButton_AÑADIR.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		btnNewButton_AÑADIR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AñadirUsuario añadirusuario = new AñadirUsuario();
				setVisible(false);
				añadirusuario.setVisible(true);
			}
		});
	
		
		JButton btnNewButton_MODIFICAR = new JButton("MODIFICAR");
		btnNewButton_MODIFICAR.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		btnNewButton_MODIFICAR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarUsuario modificarusuario = new ModificarUsuario();
				setVisible(false);
				modificarusuario.setVisible(true);
			}
		});
		
		JButton btnNewButton_ELIMINAR = new JButton("ELIMINAR");
		btnNewButton_ELIMINAR.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		btnNewButton_ELIMINAR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EliminarUsuario eliminarusuario = new EliminarUsuario();
				setVisible(false);
				eliminarusuario.setVisible(true);
			}
		});
		
		JButton btnNewButton = new JButton("SALIR");
		btnNewButton.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnNewButton.setForeground(new Color(255, 0, 0));
		btnNewButton.setIcon(new ImageIcon(TablaDatos.class.getResource("/com/sun/javafx/scene/control/skin/caspian/fxvk-backspace-button.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "GRACIAS POR UTILIZAR NUESTRA APLICACION");
				System.exit(0);
				
				
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton_AÑADIR)
					.addGap(18)
					.addComponent(btnNewButton_MODIFICAR, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_ELIMINAR, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_AÑADIR, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_MODIFICAR, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_ELIMINAR, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
					.addGap(42)
					.addComponent(btnNewButton))
		);
		contentPane.setLayout(gl_contentPane);
		
		//creamos la tabla y le añadimos la cabecera 
		DefaultTableModel dtm = new DefaultTableModel(new Object[][] {},
				new String[] { "Nombre", "Clave", "Cod Postal", "Ciudad", "Pais", "Sexo" });
		Table = new JTable();
		Table.setModel(dtm);
		scrollPane.setViewportView(Table);
		contentPane.setLayout(gl_contentPane);
		
		//añadimos los usuarios registrados en la base de datos
		rs = modelo.seleccionarUsuarios();
		try {
			while (rs.next()) {
				dtm.addRow(new Object[] { rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4),
						rs.getString(5), rs.getString(6) });
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
