import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Color;

public class ModificarUsuario extends JFrame {

	
	Modelo modelo = new Modelo();
	ResultSet rs = null;
	private JPanel contentPane;
	 JTable Table;
	 private JLabel lblModificarUsuario;
	 private JLabel lblNombre;
	 private JLabel lblCodigoPostal;
	 private JLabel lblCiudad;
	 private JLabel lblPais;
	 private JLabel lblSexo;
	 private JTextField textField_Nombre;
	 private JTextField textField_CodPostal;
	 private JTextField textField_Ciudad;
	 private JTextField textField_Pais;
	 private JTextField textField_Sexo;
	 private JButton btnActualizar;
	 private JLabel lblNewLabel_Clave;
	 private JTextField txtDam;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarUsuario frame = new ModificarUsuario();
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
	public ModificarUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setForeground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		lblModificarUsuario = new JLabel("Modificar Usuario");
		lblModificarUsuario.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 14));
		
		lblNombre = new JLabel("NOMBRE");
		lblNombre.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		
		lblCodigoPostal = new JLabel("CODIGO POSTAL");
		lblCodigoPostal.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		
		lblCiudad = new JLabel("CIUDAD");
		lblCiudad.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		
		lblPais = new JLabel("PAIS");
		lblPais.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		
		lblSexo = new JLabel("SEXO");
		lblSexo.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		
		textField_Nombre = new JTextField();
		textField_Nombre.setColumns(10);
		
		textField_CodPostal = new JTextField();
		textField_CodPostal.setColumns(10);
		
		textField_Ciudad = new JTextField();
		textField_Ciudad.setColumns(10);
		
		textField_Pais = new JTextField();
		textField_Pais.setColumns(10);
		
		textField_Sexo = new JTextField();
		textField_Sexo.setColumns(10);
		
		btnActualizar = new JButton("ACTUALIZAR");
		btnActualizar.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Table.setValueAt(textField_Nombre.getText(),Table.getSelectedRow(),0);
				Table.setValueAt(txtDam.getText(),Table.getSelectedRow(),1);
				Table.setValueAt(textField_CodPostal.getText(),Table.getSelectedRow(),2);
				Table.setValueAt(textField_Ciudad.getText(),Table.getSelectedRow(),3);
				Table.setValueAt(textField_Pais.getText(),Table.getSelectedRow(),4);
				Table.setValueAt(textField_Sexo.getText(),Table.getSelectedRow(),5);
				
				String nombre=textField_Nombre.getText();
				String clave=txtDam.getText();
				int codigopostal=Integer.parseInt(textField_CodPostal.getText());
				String ciudad=textField_Ciudad.getText();
				String pais=textField_Pais.getText();
				String sexo=textField_Sexo.getText();
				modelo.modificar(nombre, clave, codigopostal, ciudad, pais, sexo);
				if(modelo!=null){
					JOptionPane.showMessageDialog(null, "Cliente modificado en la BBDD");
					textField_Nombre.setText("");
					txtDam.setText("");
					textField_Sexo.setText("");
					textField_Ciudad.setText("");
					textField_CodPostal.setText("");
					textField_Pais.setText("");
					modelo.seleccionarUsuarios();
					TablaDatos tabladatos=new TablaDatos();
					tabladatos.setLocationRelativeTo(null);
					setVisible(false);
					tabladatos.setVisible(true);

				}else{
					JOptionPane.showMessageDialog(null, " No se ha modificado Cliente en la BBDD...");

				}
				
			}
		});
		
		lblNewLabel_Clave = new JLabel("Clave");
		
		txtDam = new JTextField();
		txtDam.setBackground(new Color(153, 255, 153));
		txtDam.setColumns(10);
		

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(149)
							.addComponent(lblModificarUsuario))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(23)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNombre)
								.addComponent(lblCodigoPostal)
								.addComponent(lblCiudad)
								.addComponent(lblPais)
								.addComponent(lblSexo))
							.addGap(37)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(textField_Ciudad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_CodPostal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addComponent(btnActualizar, GroupLayout.PREFERRED_SIZE, 117, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(textField_Pais, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(12)
									.addComponent(lblNewLabel_Clave, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtDam, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
									.addGap(38))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(textField_Sexo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 142, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(textField_Nombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 142, Short.MAX_VALUE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(9)
					.addComponent(lblModificarUsuario)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_Nombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNombre))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCodigoPostal)
								.addComponent(textField_CodPostal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_Ciudad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCiudad)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(69)
							.addComponent(btnActualizar)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPais)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(textField_Pais, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_Clave)
							.addComponent(txtDam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSexo)
						.addComponent(textField_Sexo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(0, Short.MAX_VALUE))
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