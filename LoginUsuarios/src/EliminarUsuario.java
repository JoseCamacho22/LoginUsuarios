import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;

public class EliminarUsuario extends JFrame {
	
	Modelo modelo = new Modelo();
	ResultSet rs = null;
	private JPanel contentPane;
	 JTable Table;
	 private JTextField textField_Nombre;
	 private JButton btnNewButton;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EliminarUsuario frame = new EliminarUsuario();
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
	public EliminarUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblIndiqueElNombre = new JLabel("Indique el nombre de usuario que desea eliminar");
		lblIndiqueElNombre.setFont(new Font("Arial Rounded MT Bold", Font.BOLD | Font.ITALIC, 15));
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		btnEliminar.setForeground(new Color(255, 0, 0));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dtm = (DefaultTableModel) Table.getModel();
				int a=Table.getSelectedRow();
				dtm.removeRow(a);
				String nombre=textField_Nombre.getText();
				modelo.Delete(nombre);
				modelo.seleccionarUsuarios();
				JOptionPane.showMessageDialog(null, "Cliente ha sido eliminado con exito");
				TablaDatos tabladatos=new TablaDatos();
				setVisible(false);
				tabladatos.setVisible(true);
			}
		});
		
		textField_Nombre = new JTextField();
		textField_Nombre.setColumns(10);
		
		btnNewButton = new JButton("VOLVER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					TablaDatos tabaladatos= new TablaDatos();
					setVisible(false);
					tabaladatos.setVisible(true);
				}
				
			
		});
	
		
	
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(31)
					.addComponent(lblIndiqueElNombre)
					.addContainerGap(55, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(151, Short.MAX_VALUE)
					.addComponent(textField_Nombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(155))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(168)
					.addComponent(btnEliminar)
					.addPreferredGap(ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(16)
					.addComponent(lblIndiqueElNombre)
					.addGap(33)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textField_Nombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
							.addComponent(btnEliminar)
							.addGap(17))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton)
							.addContainerGap())))
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