package vista;
import modelo.Alumno;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controlador.AlumnoDAO;

class Ventana extends JFrame{
	
	JMenuBar menuBar;
	JMenu alumnos;
	JMenuItem altas,bajas,cambios,consultas;
	JToolBar barraH;
	JPanel panel1,panel2;
	
	JInternalFrame a,b,c,c1;
	
	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	
	
	public Ventana() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(550,400);
		setLocationRelativeTo(null);
		setTitle("Base de datos");
		setVisible(true);
		
		//JOptionPane.showMessageDialog(rootPane,"Alumnos => Altas");
		
		menuBar = new JMenuBar();
		alumnos = new JMenu("Alumnos");
		altas = new JMenuItem("Altas");
		alumnos.add(altas);
		bajas = new JMenuItem("Bajas");
		alumnos.add(bajas);	
		cambios = new JMenuItem("Cambios");
		alumnos.add(cambios);	
		consultas = new JMenuItem("Consultas");
		alumnos.add(consultas);	
		
				
		altas.addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				a.setVisible(true);
							
							
				}
			});
		
		bajas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				b.setVisible(true);
							
							
				}
			});
			
		menuBar.add(alumnos);
	
	
		setJMenuBar(menuBar);
		
		JDesktopPane dp = new JDesktopPane();
			
		
		//---------------------------------Altas--------------------------------------------
		
		a = new JInternalFrame();
		a.getContentPane().setLayout(null);
		a.setDefaultCloseOperation(HIDE_ON_CLOSE);
		a.setSize(534, 338);
		a.setTitle("Altas alumnos");
		//bd.setVisible(true);
		
		
		
		panel1 = new JPanel();
		panel1.setBackground(new Color(237,234,227));
		panel1.setLayout(gbl);
		Border bordejpanel = new TitledBorder(new EtchedBorder());
		panel1.setBorder(bordejpanel);
		panel1.setBounds(1, 1, 523, 304);
		
		
		JLabel lblNumControl = new JLabel("Numero de control: ");
		componentePanel(lblNumControl, 0, 0, 1, 1, panel1);
		JTextField txtNumControl = new JTextField(15);
		componentePanel(txtNumControl, 1, 0, 1, 1, panel1);
		
		JLabel lblNombres = new JLabel("Nombres: ");
		componentePanel(lblNombres, 0, 1, 1, 1, panel1);
		JTextField txtNombres = new JTextField(20);
		componentePanel(txtNombres, 1, 1, 1, 1, panel1);
		
		JLabel lblApPaterno = new JLabel("Apellido paterno: ");
		componentePanel(lblApPaterno, 0, 2, 1, 1, panel1);
		JTextField txtApPaterno = new JTextField(15);
		componentePanel(txtApPaterno, 1, 2, 1, 1, panel1);
		
		JLabel lblApMaterno = new JLabel("Apellido materno: ");
		componentePanel(lblApMaterno, 0, 3, 1, 1, panel1);
		JTextField txtApMaterno = new JTextField(15);
		componentePanel(txtApMaterno, 1, 3, 1, 1, panel1);
		
		JLabel lblEdad = new JLabel("Edad: ");
		componentePanel(lblEdad, 0, 4, 1, 1, panel1);
		JTextField txtEdad = new JTextField(10);
		componentePanel(txtEdad, 1, 4, 1, 1, panel1);
		
		JLabel lblSemestre = new JLabel("Semestre: ");
		componentePanel(lblSemestre, 0, 5, 1, 1, panel1);
		String sem[] = {"Elige semestre...","1","2","3","4","5","6","7","8","9","10"};
		JComboBox <String> cmbSemestre = new JComboBox<String>(sem);
		componentePanel(cmbSemestre, 1, 5, 1, 1, panel1);
		
		JLabel lblCarrera = new JLabel("Carrera: ");
		componentePanel(lblCarrera, 0, 6, 1, 1, panel1);
		String carrera[] = {"Elige carrera...","ISC","IM","IIA","LA","CP"};
		JComboBox <String> cmbCarrera = new JComboBox<String>(carrera);
		componentePanel(cmbCarrera, 1, 6, 1, 1, panel1);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Alumno a = new Alumno(txtNumControl.getText(), txtNombres.getText(), txtApPaterno.getText(), txtApMaterno.getText(), 
						Byte.parseByte(txtEdad.getText()), Byte.parseByte((String) cmbSemestre.getSelectedItem()), (String) cmbCarrera.getSelectedItem());
				
				AlumnoDAO aDAO = new AlumnoDAO();
				
				//System.out.println(aDAO.insertarRragistro(a)?"Se agregó correctamente":"Error");
				
				if(aDAO.insertarRragistro(a)) {
					JOptionPane.showMessageDialog(rootPane,"Se agregó correctamente a la base de datos");
				}else {
					JOptionPane.showMessageDialog(rootPane,"Hubo un error al intentar agregar a la base de datos");
				}
			}
		});
		componentePanel(btnAgregar, 2, 1, 1, 1, panel1);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtNumControl.setText("");
				txtNombres.setText("");
				txtApPaterno.setText("");
				txtApMaterno.setText("");
				txtEdad.setText("");
				cmbSemestre.setSelectedItem("Elige semestre...");
				cmbCarrera.setSelectedItem("Elige carrera...");
			}
		});
		componentePanel(btnBorrar, 2, 3, 1, 1, panel1);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		componentePanel(btnCancelar, 2, 5, 1, 1, panel1);
		
		JTable tabla = new JTable(7, 6);
		componentePanel(tabla, 0, 7, 3, 1, panel1);
		
		a.add(panel1);
		
		//-----------------------------------Bajas----------------------------------------------
		b = new JInternalFrame();
		b.getContentPane().setLayout(null);
		b.setDefaultCloseOperation(HIDE_ON_CLOSE);
		b.setSize(534, 338);
		b.setTitle("Bajas alumnos");
	
		panel2 = new JPanel();
		panel2.setBackground(new Color(237,234,227));
		panel2.setLayout(gbl);
		Border bordejpanel2 = new TitledBorder(new EtchedBorder());
		panel2.setBorder(bordejpanel);
		panel2.setBounds(1, 1, 523, 304);
		
		JLabel lblNumControl2 = new JLabel("Numero de control: ");
		componentePanel(lblNumControl2, 0, 0, 1, 1, panel2);
		JTextField txtNumControl2 = new JTextField(10);
		componentePanel(txtNumControl2, 1, 0, 1, 1, panel2);
		
		JLabel lblNombres2 = new JLabel("Nombres: ");
		componentePanel(lblNombres2, 0, 1, 1, 1, panel2);
		JTextField txtNombres2 = new JTextField(16);
		componentePanel(txtNombres2, 1, 1, 2, 1, panel2);
		
		JLabel lblApPaterno2 = new JLabel("Apellido paterno: ");
		componentePanel(lblApPaterno2, 0, 2, 1, 1, panel2);
		JTextField txtApPaterno2 = new JTextField(16);
		componentePanel(txtApPaterno2, 1, 2, 2, 1, panel2);
		
		JLabel lblApMaterno2 = new JLabel("Apellido materno: ");
		componentePanel(lblApMaterno2, 0, 3, 1, 1, panel2);
		JTextField txtApMaterno2 = new JTextField(16);
		componentePanel(txtApMaterno2, 1, 3, 2, 1, panel2);
		
		JLabel lblEdad2 = new JLabel("Edad: ");
		componentePanel(lblEdad2, 0, 4, 1, 1, panel2);
		JTextField txtEdad2 = new JTextField(10);
		componentePanel(txtEdad2, 1, 4, 2, 1, panel2);
		
		JLabel lblSemestre2 = new JLabel("Semestre: ");
		componentePanel(lblSemestre2, 0, 5, 1, 1, panel2);
		String sem2[] = {"Elige semestre...","1","2","3","4","5","6","7","8","9","10"};
		JComboBox <String> cmbSemestre2 = new JComboBox<String>(sem2);
		componentePanel(cmbSemestre2, 1, 5, 1, 1, panel2);
		
		JLabel lblCarrera2 = new JLabel("Carrera: ");
		componentePanel(lblCarrera2, 0, 6, 1, 1, panel2);
		String carrera2[] = {"Elige carrera...","ISC","IM","IIA","LA","CP"};
		JComboBox <String> cmbCarrera2 = new JComboBox<String>(carrera2);
		componentePanel(cmbCarrera2, 1, 6, 1, 1, panel2);
		
		JButton btnBuscar = new JButton("Buscar");
		componentePanel(btnBuscar, 2, 0, 1, 1, panel2);
		
		JButton btnBorrar2 = new JButton("Borrar");
		btnBorrar2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtNumControl.setText("");
				txtNombres.setText("");
				txtApPaterno.setText("");
				txtApMaterno.setText("");
				txtEdad.setText("");
				cmbSemestre.setSelectedItem("Elige semestre...");
				cmbCarrera.setSelectedItem("Elige carrera...");
			}
		});
		componentePanel(btnBorrar2, 3, 0, 1, 1, panel2);
		
		JButton btnAgregar2 = new JButton("Eliminar");
		componentePanel(btnAgregar2, 3, 2, 1, 1, panel2);
		
		JButton btnCancelar2 = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		componentePanel(btnCancelar2, 3, 5, 1, 1, panel2);
		
		//JTable tabla2 = new JTable(6, 6);
		//componentePanel(tabla, 0, 7, 4, 1, panel2);

		b.add(panel2);
		//---------------------------------------------------------------------------------
		
		dp.add(a);
		dp.add(b);
		dp.setBounds(0, 0, 550, 400);
		add(dp);
		
		}
	
	public void componentePanel(JComponent c, int gx, int gy, int gw, int gh, JPanel panel) {
		
		gbc.gridx = gx;
		gbc.gridy = gy;
		gbc.gridheight = gh;
		gbc.gridwidth = gw;
		gbc.anchor = GridBagConstraints.WEST;
		gbl.setConstraints(c, gbc);
		panel.add(c);
		
	}
		
	
}

public class VentanaPrincipal {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Ventana();
			}
		});	
		
		/*
		String nc = "S19070";
		AlumnoDAO aDAO = new AlumnoDAO();
		System.out.println(aDAO.eliminarRegistro(nc)?"Exito":"Error");
		
		Alumno a = new Alumno("S1907000", "Ruby", "Pinedo", "d", (byte)19, (byte)4, "IM");
		System.out.println(aDAO.modificarRegistro(a)?"Exito":"Errorxd");
		*/

	}

}