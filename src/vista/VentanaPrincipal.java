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
	String carr;
	
	JMenuBar menuBar;
	JMenu alumnos;
	JMenuItem altas,bajas,cambios,consultas;
	JToolBar barraH;
	JPanel panel1;
	
	JInternalFrame bd;
	
	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	
	
	public Ventana() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(550,400);
		setLocationRelativeTo(null);
		setTitle("Base de datos");
		setVisible(true);
		
		JOptionPane.showMessageDialog(rootPane,"Alumnos => Altas");
		
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
				bd.setVisible(true);
							
							
				}
			});
			
		menuBar.add(alumnos);
	
	
		setJMenuBar(menuBar);
		
		JDesktopPane dp = new JDesktopPane();
					
		
		bd = new JInternalFrame();
		bd.getContentPane().setLayout(null);
		bd.setDefaultCloseOperation(HIDE_ON_CLOSE);
		bd.setSize(534, 338);
		bd.setTitle("Altas alumnos");
		//bd.setVisible(true);
		
		
		
		panel1 = new JPanel();
		panel1.setBackground(new Color(237,234,227));
		panel1.setLayout(gbl);
		Border bordejpanel = new TitledBorder(new EtchedBorder());
		panel1.setBorder(bordejpanel);
		panel1.setBounds(1, 1, 523, 304);
		
		
		JLabel lblNumControl = new JLabel("Numero de control: ");
		componentePanel(lblNumControl, 0, 0, 1, 1);
		JTextField txtNumControl = new JTextField(15);
		componentePanel(txtNumControl, 1, 0, 1, 1);
		
		JLabel lblNombres = new JLabel("Nombres: ");
		componentePanel(lblNombres, 0, 1, 1, 1);
		JTextField txtNombres = new JTextField(20);
		componentePanel(txtNombres, 1, 1, 1, 1);
		
		JLabel lblApPaterno = new JLabel("Apellido paterno: ");
		componentePanel(lblApPaterno, 0, 2, 1, 1);
		JTextField txtApPaterno = new JTextField(15);
		componentePanel(txtApPaterno, 1, 2, 1, 1);
		
		JLabel lblApMaterno = new JLabel("Apellido materno: ");
		componentePanel(lblApMaterno, 0, 3, 1, 1);
		JTextField txtApMaterno = new JTextField(15);
		componentePanel(txtApMaterno, 1, 3, 1, 1);
		
		JLabel lblEdad = new JLabel("Edad: ");
		componentePanel(lblEdad, 0, 4, 1, 1);
		JTextField txtEdad = new JTextField(10);
		componentePanel(txtEdad, 1, 4, 1, 1);
		
		JLabel lblSemestre = new JLabel("Semestre: ");
		componentePanel(lblSemestre, 0, 5, 1, 1);
		String sem[] = {"Elige semestre...","1","2","3","4","5","6","7","8","9","10"};
		JComboBox <String> cmbSemestre = new JComboBox<String>(sem);
		componentePanel(cmbSemestre, 1, 5, 1, 1);
		
		JLabel lblCarrera = new JLabel("Carrera: ");
		componentePanel(lblCarrera, 0, 6, 1, 1);
		String carrera[] = {"Elige carrera...","ISC","IM","IIA","LA","CP"};
		JComboBox <String> cmbCarrera = new JComboBox<String>(carrera);
		componentePanel(cmbCarrera, 1, 6, 1, 1);
		
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
		componentePanel(btnAgregar, 2, 1, 1, 1);
		
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
		componentePanel(btnBorrar, 2, 3, 1, 1);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		componentePanel(btnCancelar, 2, 5, 1, 1);
		
		JTable tabla = new JTable(7, 6);
		componentePanel(tabla, 0, 7, 3, 1);
		
		
		bd.add(panel1);
		
		
		dp.add(bd);
		dp.setBounds(0, 0, 550, 400);
		add(dp);
		
		}
	
	public void componentePanel(JComponent c, int gx, int gy, int gw, int gh) {
		
		gbc.gridx = gx;
		gbc.gridy = gy;
		gbc.gridheight = gh;
		gbc.gridwidth = gw;
		gbc.anchor = GridBagConstraints.WEST;
		gbl.setConstraints(c, gbc);
		panel1.add(c);
		
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
		

	}

}