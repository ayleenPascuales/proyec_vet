package view;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import controller.CitasController;
import controller.VetController;
import java.awt.Color;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.dao.ClienteDAO;
import model.dao.ClienteDAOImpl;
import model.dao.VeterinarioDAO;
import model.dao.VeterinarioDAOImpl;
import model.entidades.Cliente;
import model.entidades.Rol;
import model.entidades.Usuario;
import model.entidades.Veterinario;

/**
 *
 * @author aylee
 */
public class Admin extends javax.swing.JFrame {

    private static Usuario usuario;

    public Admin(Usuario usuario) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.usuario = usuario;
        cargarEmpleados();
        cargarClientes();
    }

    private void cargarEmpleados() {
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0);

        VeterinarioDAO vetDao = new VeterinarioDAOImpl();
        List<Veterinario> empleados = vetDao.obtenerTodosVeterinarios();

        for (Veterinario emp : empleados) {
            modelo.addRow(new Object[]{
                emp.getNumeroDocumento(),
                emp.getNombres(),
                emp.getApellidos(),
                emp.getEmail(),
                emp.getFechaNacimiento(),
                emp.getTelefono(),
                emp.getCargo(),
                emp.getExperiencia(),
                emp.getUser(),
                "********" // No mostrar contraseña real
            });
        }
    }

    private void cargarClientes() {
        DefaultTableModel modelo = (DefaultTableModel) jTable2.getModel();
        modelo.setRowCount(0); // Limpiar tabla

        ClienteDAO clienteDao = new ClienteDAOImpl();
        List<Cliente> clientes = clienteDao.obtenerTodosClientes();

        for (Cliente cli : clientes) {
            modelo.addRow(new Object[]{
                cli.getNumeroDocumento(),
                cli.getNombres(),
                cli.getApellidos(),
                cli.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                cli.getEmail(),
                cli.getTelefono()
            });
        }
    }
   
    public boolean esSoloLetras(String texto) {
        return texto.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+");
    }

    public boolean esSoloNumeros(String texto) {
        return texto.matches("\\d+");
    }

    public boolean esEmailValido(String email) {
        return email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    public boolean esTelefonoValido(String telefono) {
        return telefono.matches("\\d{10}");
    }
    
    public boolean validarEdad(LocalDate fechaNacimiento) {
    LocalDate fechaActual = LocalDate.now();
    if (fechaNacimiento.isAfter(fechaActual)) {
        JOptionPane.showMessageDialog(null, "La fecha de nacimiento no puede ser futura.");
        return false;
    }
    int edad = Period.between(fechaNacimiento, fechaActual).getYears();
    
    if (edad < 18) {
        JOptionPane.showMessageDialog(null, "Debe ser mayor de edad. Edad calculada: " + edad);
        return false;
    }
    
    return true;
    }

    public boolean validarCamposVeterinario() {
        String documento = txtDocumento.getText().trim();
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String email = txtEmail.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String usuario = txtUsuario.getText().trim();
        String contraseña = txtContraseña.getText().trim();
        String experiencia = txtExperiencia.getText().trim();
        Object cargo = comboCargo.getSelectedItem();
        Date fechaNacimiento = JDateAdmin.getDate();

        // Verificar campos vacíos
        if (documento.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || email.isEmpty()
                || telefono.isEmpty() || usuario.isEmpty() || contraseña.isEmpty()
                || experiencia.isEmpty() || cargo == null || fechaNacimiento == null) {
            JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos.");
            return false;
        }

        if (!esSoloNumeros(documento)) {
            JOptionPane.showMessageDialog(null, "El documento debe contener solo números.");
            return false;
        }
        VeterinarioDAO veterinarioDAO = new VeterinarioDAOImpl();
        if (veterinarioDAO.existeVeterinarioConDocumento(documento)){
            JOptionPane.showMessageDialog(null, "El número de documento ya está registrado. Por favor ingrese otro.");
            txtDocumento.requestFocus(); 
            return false;
        }
        
        if (!esSoloLetras(nombre)) {
            JOptionPane.showMessageDialog(null, "El apellido debe contener solo letras.");
            return false;
        }

        if (!esSoloLetras(apellido)) {
            JOptionPane.showMessageDialog(null, "El apellido debe contener solo letras.");
            return false;
        }

        if (!esEmailValido(email)) {
            JOptionPane.showMessageDialog(null, "Correo electrónico inválido.");
            return false;
        }
        
        if (!esTelefonoValido(telefono)) {
            JOptionPane.showMessageDialog(null, "Teléfono inválido. Debe tener 10 dígitos.");
            return false;
        }

        if (usuario.length() < 4 || contraseña.length() < 4) {
            JOptionPane.showMessageDialog(null, "Usuario y contraseña deben tener al menos 4 caracteres.");
            return false;
        }

        return true;
    }

    public void limpiarFormulario() {
        txtDocumento.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtEmail.setText("");
        txtTelefono.setText("");
        txtUsuario.setText("");
        txtContraseña.setText("");
        txtExperiencia.setText("");
        comboCargo.setSelectedIndex(0);
        JDateAdmin.setDate(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupEmpleados = new javax.swing.JPopupMenu();
        popupClientes = new javax.swing.JPopupMenu();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        txtDocumento = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        comboCargo = new javax.swing.JComboBox<>();
        txtUsuario = new javax.swing.JTextField();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jSeparator14 = new javax.swing.JSeparator();
        txtContraseña = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        btnContratar = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        txtExperiencia = new javax.swing.JTextField();
        jSeparator15 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jSeparator16 = new javax.swing.JSeparator();
        JDateAdmin = new com.toedter.calendar.JDateChooser();
        jPanel7 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 0, -1, -1));

        jPanel6.setBackground(new java.awt.Color(196, 154, 237));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(47, 22, 57));
        jLabel1.setText("ADMINISTRADOR");
        jPanel6.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 269, 46));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel6.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 260, 10));

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 950, 90));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jTable1.setForeground(new java.awt.Color(0, 0, 0));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Documento", "Nombre", "Apellido", "Email", "Fecha de nacimiento", "Telefono", "Cargo", "Experiencia", "Usuario", "Contraseña"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 1100, 300));

        jLabel2.setFont(new java.awt.Font("Tw Cen MT", 3, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(47, 22, 57));
        jLabel2.setText("EMPLEADOS");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, 180, 46));

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 60, 180, 10));

        jButton5.setBackground(new java.awt.Color(196, 154, 237));
        jButton5.setFont(new java.awt.Font("Tw Cen MT", 3, 20)); // NOI18N
        jButton5.setText("Actualizar");
        jButton5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton5.setBorderPainted(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 110, 30));

        jScrollPane3.setViewportView(jPanel1);

        jTabbedPane1.addTab("tab5", jScrollPane3);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jTable2.setForeground(new java.awt.Color(0, 0, 0));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Documento", "Nombre", "Apellido", "Fecha de nacimiento", "Email", "Telefono"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 890, 310));

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, 140, 10));

        jLabel3.setFont(new java.awt.Font("Tw Cen MT", 3, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(47, 22, 57));
        jLabel3.setText("CLIENTES");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 130, 46));

        jButton6.setBackground(new java.awt.Color(196, 154, 237));
        jButton6.setFont(new java.awt.Font("Tw Cen MT", 3, 20)); // NOI18N
        jButton6.setText("Actualizar");
        jButton6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton6.setBorderPainted(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 110, -1));

        jTabbedPane1.addTab("tab2", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, 280, 10));

        jLabel5.setFont(new java.awt.Font("Tw Cen MT", 3, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(47, 22, 57));
        jLabel5.setText("Contratar Emplados");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 290, 46));

        jSeparator6.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 160, 10));

        txtDocumento.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        jPanel3.add(txtDocumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 200, -1));

        txtNombre.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        jPanel3.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 200, -1));

        jSeparator7.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 80, 10));

        jLabel8.setFont(new java.awt.Font("Tw Cen MT", 3, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(47, 22, 57));
        jLabel8.setText("Nombre:");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, -1, 20));

        jSeparator8.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, 80, 10));

        jLabel9.setFont(new java.awt.Font("Tw Cen MT", 3, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(47, 22, 57));
        jLabel9.setText("Apellido:");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, -1, 20));

        txtApellido.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        jPanel3.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, 200, -1));

        txtEmail.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        jPanel3.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, 200, -1));

        jSeparator9.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator9.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, 160, 10));

        jLabel10.setFont(new java.awt.Font("Tw Cen MT", 3, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(47, 22, 57));
        jLabel10.setText("Correo electronico:");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 320, -1, 20));

        txtTelefono.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        jPanel3.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 130, 200, -1));

        jSeparator11.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator11.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, 140, 10));

        jLabel12.setFont(new java.awt.Font("Tw Cen MT", 3, 20)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(47, 22, 57));
        jLabel12.setText("Nro de telefono:");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, -1, 20));

        jSeparator12.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator12.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 190, 60, 10));

        jLabel13.setFont(new java.awt.Font("Tw Cen MT", 3, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(47, 22, 57));
        jLabel13.setText("Cargo:");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, -1, 20));

        comboCargo.setBackground(new java.awt.Color(255, 255, 255));
        comboCargo.setFont(new java.awt.Font("Tw Cen MT", 2, 20)); // NOI18N
        comboCargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Veterinario", "Vacunacion", "Cirujano", "Bacteriologo", "Peluquero", " " }));
        jPanel3.add(comboCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 200, 200, 30));

        txtUsuario.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        jPanel3.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 130, 200, -1));

        jSeparator13.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator13.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 120, 80, 10));

        jLabel14.setFont(new java.awt.Font("Tw Cen MT", 3, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(47, 22, 57));
        jLabel14.setText("Usuario:");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, -1, 20));

        jSeparator14.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator14.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 200, 110, 10));

        txtContraseña.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        jPanel3.add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 210, 200, -1));

        jLabel15.setFont(new java.awt.Font("Tw Cen MT", 3, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(47, 22, 57));
        jLabel15.setText("Contraseña:");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 180, -1, 20));

        btnContratar.setBackground(new java.awt.Color(196, 154, 237));
        btnContratar.setFont(new java.awt.Font("Tw Cen MT", 3, 20)); // NOI18N
        btnContratar.setText("Contratar");
        btnContratar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        btnContratar.setBorderPainted(false);
        btnContratar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnContratarMouseClicked(evt);
            }
        });
        btnContratar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContratarActionPerformed(evt);
            }
        });
        jPanel3.add(btnContratar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 410, 110, 30));

        jLabel16.setFont(new java.awt.Font("Tw Cen MT", 3, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(47, 22, 57));
        jLabel16.setText("Nro de documento:");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, -1, 20));

        txtExperiencia.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        jPanel3.add(txtExperiencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 270, 200, -1));

        jSeparator15.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator15.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 260, 170, 10));

        jLabel18.setFont(new java.awt.Font("Tw Cen MT", 3, 20)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(47, 22, 57));
        jLabel18.setText("Años de experiencia:");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 240, -1, 20));

        jLabel19.setFont(new java.awt.Font("Tw Cen MT", 3, 20)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(47, 22, 57));
        jLabel19.setText("Fecha de nacimiento:");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 320, -1, 20));

        jSeparator16.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator16.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 340, 170, 10));
        jPanel3.add(JDateAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 350, 200, 30));

        jTabbedPane1.addTab("tab3", jPanel3);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jTabbedPane1.addTab("tab4", jPanel7);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 940, 490));

        jPanel5.setBackground(new java.awt.Color(196, 154, 237));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(196, 154, 237));
        jButton1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 2, 30)); // NOI18N
        jButton1.setForeground(new java.awt.Color(47, 22, 57));
        jButton1.setText("Empleados");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jPanel5.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 190, 60));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_imgs/aylen 100 x 100.png"))); // NOI18N
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, 100));

        jButton2.setBackground(new java.awt.Color(196, 154, 237));
        jButton2.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 2, 30)); // NOI18N
        jButton2.setForeground(new java.awt.Color(47, 22, 57));
        jButton2.setText("Contratar");
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 190, 60));

        jButton3.setBackground(new java.awt.Color(196, 154, 237));
        jButton3.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 2, 30)); // NOI18N
        jButton3.setForeground(new java.awt.Color(47, 22, 57));
        jButton3.setText("Graficos");
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 190, 60));

        jButton4.setBackground(new java.awt.Color(196, 154, 237));
        jButton4.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 2, 30)); // NOI18N
        jButton4.setForeground(new java.awt.Color(47, 22, 57));
        jButton4.setText("Clientes");
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton4MouseMoved(evt);
            }
        });
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton4MouseExited(evt);
            }
        });
        jPanel5.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 190, 60));

        jLabel7.setFont(new java.awt.Font("Tw Cen MT", 3, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(47, 22, 57));
        jLabel7.setText("ANIMALSLOVE");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, 20));

        jLabel17.setFont(new java.awt.Font("Tw Cen MT", 3, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(47, 22, 57));
        jLabel17.setText("VETERINARIA");
        jPanel5.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, 20));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 580));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseMoved
        jButton4.setBackground(new Color(166, 124, 207));
    }//GEN-LAST:event_jButton4MouseMoved

    private void jButton4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseExited
        jButton4.setBackground(new Color(196, 154, 237));
    }//GEN-LAST:event_jButton4MouseExited

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        cargarEmpleados();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnContratarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContratarActionPerformed


    }//GEN-LAST:event_btnContratarActionPerformed

    private void btnContratarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContratarMouseClicked
        if (!validarCamposVeterinario()) {
            return;
        }
        
        // Convertir fecha del JDateChooser a LocalDate
        LocalDate fecha = JDateAdmin.getDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        if (!validarEdad(fecha)) {
            return;
        }

        // Crear el objeto Veterinario
        Veterinario vet = new Veterinario(
                txtDocumento.getText().trim(),
                txtNombre.getText().trim(),
                txtApellido.getText().trim(),
                fecha,
                txtEmail.getText().trim(),
                txtTelefono.getText().trim(),
                comboCargo.getSelectedItem().toString(),
                txtExperiencia.getText().trim(),
                txtUsuario.getText().trim(),
                txtContraseña.getText().trim()
        );

        try {
            VetController controller = new VetController();
            controller.registrarVeterinario(vet);

            JOptionPane.showMessageDialog(null, "Veterinario guardado exitosamente");
            limpiarFormulario();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el veterinario: " + e.getMessage());
        }
    }//GEN-LAST:event_btnContratarMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       cargarClientes();
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin(usuario).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser JDateAdmin;
    private javax.swing.JButton btnContratar;
    private javax.swing.JComboBox<String> comboCargo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JPopupMenu popupClientes;
    private javax.swing.JPopupMenu popupEmpleados;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtContraseña;
    private javax.swing.JTextField txtDocumento;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtExperiencia;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
