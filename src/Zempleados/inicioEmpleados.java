package Zempleados;

import controller.HistorialEspecificoController;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.dao.ClienteDAO;
import model.dao.ClienteDAOImpl;
import model.dao.HistorialDAO;
import model.dao.HistorialDAOImpl;
import model.dao.HistorialEspecificoDAO;
import model.dao.HistorialEspecificoDAOImpl;
import model.dao.RegistrosDAO;
import model.dao.RegistrosDAOImpl;
import model.dao.VeterinarioDAO;
import model.dao.VeterinarioDAOImpl;
import model.entidades.Cliente;
import model.entidades.Historial;
import model.entidades.HistorialEspecifico;
import model.entidades.Registros;
import model.entidades.Usuario;
import model.entidades.Veterinario;
import view.HistorialView;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author aylee
 */
public final class inicioEmpleados extends javax.swing.JFrame {

    /**
     * Creates new form inicioAdmin
     */
    static private Usuario usuario;

    public inicioEmpleados(Usuario usuario) {
        initComponents();
        this.usuario = usuario;
        this.setLocationRelativeTo(null);
        cargarClientes1();
        cargarComboVeterinarios();
    }

    private void cargarClientes1() {
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0); // Limpiar tabla

        RegistrosDAO registrodao = new RegistrosDAOImpl();
        List<Registros> registros = registrodao.obtenerTodos();

        for (Registros registrados : registros) {
            modelo.addRow(new Object[]{
                registrados.getNumeroDocumento(),
                registrados.getNombre(),
                registrados.getApellido(),
                registrados.getTelefono(),
                registrados.getEmail(),
                registrados.getNombreMascota(),
                registrados.getTipoMascota()
            });
        }
    }

    private void cargarHistoriales() {
        String documentoIngresado = txtDocumento1.getText().trim();
        if (documentoIngresado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingresa un número de documento.", "Campo vacío", JOptionPane.WARNING_MESSAGE);
            return;
        }

        DefaultTableModel modelo = (DefaultTableModel) jTable2.getModel();
        modelo.setRowCount(0); // Limpiar tabla

        HistorialEspecificoDAO historialdao = new HistorialEspecificoDAOImpl();
        List<HistorialEspecifico> historiales = historialdao.buscarPorDocumento(documentoIngresado);

        if (historiales.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron historiales para el documento ingresado.", "Documento no encontrado", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        for (Historial h : historiales) {
            modelo.addRow(new Object[]{
                h.getNombreDueño(),
                h.getNombreMascota(),
                h.getTipo(),
                h.getEdad(),
                h.getMotivoConsulta(),
                h.getFechaConsulta(),
                h.getVeterinario()
            });
        }
    }
    private List<Veterinario> listaVeterinarios = new ArrayList<>();

    private void cargarComboVeterinarios() {
        VeterinarioDAO veterinarioDAO = new VeterinarioDAOImpl();
        listaVeterinarios = veterinarioDAO.obtenerTodosVeterinarios();

        comboVet.removeAllItems(); // Limpiar combo
        comboVet.addItem("Seleccione...");

        for (Veterinario vet : listaVeterinarios) {
            comboVet.addItem(vet.getNombres() + " " + vet.getApellidos());
        }
    }

    private boolean validarCampos() {
        String soloLetrasRegex = "^[A-Za-záéíóúÁÉÍÓÚñÑ ]+$";
        String textoLibreRegex = "^[A-Za-z0-9áéíóúÁÉÍÓÚñÑ ,.()¡!¿?\"'-]{3,}$";

        // Validaciones TXT
        String documento = txtDocumento.getText().trim();
        if (documento.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo Documento es obligatorio.");
            txtDocumento.requestFocus();
            return false;
        }
        if (!documento.matches("\\d{8,10}")) {
            JOptionPane.showMessageDialog(null, "El documento debe contener solo números (8-10 dígitos).");
            txtDocumento.requestFocus();
            return false;
        }

        String nombre = txtNombre.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo Nombre del dueño es obligatorio.");
            txtNombre.requestFocus();
            return false;
        }
        if (!nombre.matches(soloLetrasRegex)) {
            JOptionPane.showMessageDialog(null, "El nombre del dueño debe contener solo letras.");
            txtNombre.requestFocus();
            return false;
        }

        String apellido = txtApellido.getText().trim();
        if (apellido.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo Apellido del dueño es obligatorio.");
            txtApellido.requestFocus();
            return false;
        }
        if (!apellido.matches(soloLetrasRegex)) {
            JOptionPane.showMessageDialog(null, "El apellido del dueño debe contener solo letras.");
            txtApellido.requestFocus();
            return false;
        }

        String telefono = txtTelefono.getText().trim();
        if (telefono.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo Teléfono es obligatorio.");
            txtTelefono.requestFocus();
            return false;
        }
        if (!telefono.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(null, "El teléfono debe contener solo números ( 10 dígitos).");
            txtTelefono.requestFocus();
            return false;
        }

        String mascota = txtMascota.getText().trim();
        if (mascota.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo Nombre de la mascota es obligatorio.");
            txtMascota.requestFocus();
            return false;
        }
        if (!mascota.matches(soloLetrasRegex)) {
            JOptionPane.showMessageDialog(null, "El nombre de la mascota debe contener solo letras.");
            txtMascota.requestFocus();
            return false;
        }

        String motivo = txtMotivo.getText().trim();
        if (motivo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo Motivo de consulta es obligatorio.");
            txtMotivo.requestFocus();
            return false;
        }
        if (!motivo.matches(textoLibreRegex)) {
            JOptionPane.showMessageDialog(null, "El motivo debe tener al menos 3 caracteres y no contener símbolos extraños.");
            txtMotivo.requestFocus();
            return false;
        }

        String diagnostico = txtDiagnostico.getText().trim();
        if (diagnostico.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo Diagnóstico es obligatorio.");
            txtDiagnostico.requestFocus();
            return false;
        }
        if (!diagnostico.matches(textoLibreRegex)) {
            JOptionPane.showMessageDialog(null, "El diagnóstico debe tener al menos 3 caracteres y no contener símbolos extraños.");
            txtDiagnostico.requestFocus();
            return false;
        }

        // Validaciones Combobox
        if (comboVet.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona un veterinario.");
            comboVet.requestFocus();
            return false;
        }

        if (comboGenero.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona el género de la mascota.");
            comboGenero.requestFocus();
            return false;
        }

        if (comboTipo1.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona el tipo de mascota.");
            comboTipo1.requestFocus();
            return false;
        }

        if (comboEdad1.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona la edad de la mascota.");
            comboEdad1.requestFocus();
            return false;
        }

        // Validación fecha
        if (fechaJDATE.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona una fecha de consulta.");
            fechaJDATE.requestFocus();
            return false;
        }

        return true;
    }

    private void limpiarCampos() {
        txtDocumento.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtMascota.setText("");
        txtMotivo.setText("");
        txtDiagnostico.setText("");

        comboVet.setSelectedIndex(0);
        comboGenero.setSelectedIndex(0);
        comboTipo1.setSelectedIndex(0);
        comboEdad1.setSelectedIndex(0);

        fechaJDATE.setDate(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ppMenuTabla = new javax.swing.JPopupMenu();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel26 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel31 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        actualizarClientes = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel10 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtDocumento = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtMotivo = new javax.swing.JTextArea();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDiagnostico = new javax.swing.JTextArea();
        guardar = new javax.swing.JToggleButton();
        comboGenero = new javax.swing.JComboBox<>();
        comboVet = new javax.swing.JComboBox<>();
        txtMascota = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        comboEdad1 = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        comboTipo1 = new javax.swing.JComboBox<>();
        fechaJDATE = new com.toedter.calendar.JDateChooser();
        jPanel9 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtDocumento1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(221, 6, -1, -1));

        jPanel4.setBackground(new java.awt.Color(196, 154, 237));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel1.setText("Veterinaria");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 110, 20));

        jLabel3.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel3.setText("Animalslove");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, -1, 20));

        jLabel4.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel4.setText("Clinica");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, -1, 20));

        jButton3.setBackground(new java.awt.Color(196, 154, 237));
        jButton3.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jButton3.setText("Historial medico");
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
        jPanel4.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 190, -1));

        jButton5.setBackground(new java.awt.Color(196, 154, 237));
        jButton5.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jButton5.setText("Registro");
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });
        jPanel4.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, -1, -1));

        jButton1.setBackground(new java.awt.Color(196, 154, 237));
        jButton1.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jButton1.setText("Pacientes");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 140, -1));

        jLabel2.setBackground(new java.awt.Color(196, 154, 237));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/1333.png"))); // NOI18N
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 110, 110));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 560));

        jPanel8.setBackground(new java.awt.Color(196, 154, 237));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Tw Cen MT", 3, 36)); // NOI18N
        jLabel9.setText("Empleado");
        jPanel8.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 200, -1));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel8.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 20, 500));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jPanel8.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 140, 10));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/veterinarian.png"))); // NOI18N
        jPanel8.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, -1, -1));

        getContentPane().add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, -30, 1160, 130));

        jPanel30.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, -1, -1));

        jTabbedPane2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));
        jPanel31.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Documento", "Nombre", "Apellido", "Telefono", "Correo", "Mascota", "Tipo de mascota"
            }
        ));
        jScrollPane5.setViewportView(jTable1);

        jPanel31.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 1050, 270));

        jLabel10.setFont(new java.awt.Font("Tw Cen MT", 3, 36)); // NOI18N
        jLabel10.setText("CLIENTES");
        jPanel31.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, 140, -1));

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jPanel31.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, 140, 10));

        actualizarClientes.setBackground(new java.awt.Color(196, 154, 237));
        actualizarClientes.setFont(new java.awt.Font("Tw Cen MT", 3, 20)); // NOI18N
        actualizarClientes.setForeground(new java.awt.Color(47, 22, 57));
        actualizarClientes.setText("Actualizar");
        actualizarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarClientesActionPerformed(evt);
            }
        });
        jPanel31.add(actualizarClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 120, 30));

        jTabbedPane2.addTab("Datos", jPanel31);

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Tw Cen MT", 3, 50)); // NOI18N
        jLabel14.setText("Historial medico");
        jPanel10.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel15.setText("Nombre del dueño:");
        jPanel10.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        txtNombre.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        jPanel10.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 220, -1));

        jLabel11.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel11.setText("Apellido del dueño:");
        jPanel10.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, -1, -1));

        txtApellido.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        jPanel10.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 297, 220, 30));

        jLabel16.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel16.setText("Nro de identificacion:");
        jPanel10.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 210, -1));

        txtDocumento.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        jPanel10.add(txtDocumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 220, 30));

        jLabel18.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel18.setText("Nombre de la mascota:");
        jPanel10.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 170, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel17.setText("Genero de la mascota:");
        jPanel10.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 250, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel19.setText("Veterinario");
        jPanel10.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, -1, -1));

        jLabel20.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel20.setText("Nro de telefono:");
        jPanel10.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, -1, -1));

        txtTelefono.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        jPanel10.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 220, 30));

        jLabel21.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel21.setText("Fecha de la consulta:");
        jPanel10.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, -1, -1));

        jLabel22.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel22.setText("Motivo de la consulta:");
        jPanel10.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 70, 220, -1));

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));

        txtMotivo.setColumns(20);
        txtMotivo.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        txtMotivo.setRows(5);
        jScrollPane4.setViewportView(txtMotivo);

        jPanel10.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 110, 460, 140));

        jLabel23.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel23.setText("Diagnostico y tratamiento:");
        jPanel10.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 260, -1, -1));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        txtDiagnostico.setColumns(20);
        txtDiagnostico.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        txtDiagnostico.setRows(5);
        jScrollPane1.setViewportView(txtDiagnostico);

        jPanel10.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 290, 460, 190));

        guardar.setBackground(new java.awt.Color(196, 154, 237));
        guardar.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        guardar.setForeground(new java.awt.Color(47, 22, 57));
        guardar.setText("Guardar");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        jPanel10.add(guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 520, -1, -1));

        comboGenero.setBackground(new java.awt.Color(255, 255, 255));
        comboGenero.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        comboGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "Hembra ", "Macho" }));
        jPanel10.add(comboGenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 300, 240, 30));

        comboVet.setBackground(new java.awt.Color(255, 255, 255));
        comboVet.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        comboVet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboVetActionPerformed(evt);
            }
        });
        jPanel10.add(comboVet, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, 220, 30));

        txtMascota.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        jPanel10.add(txtMascota, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, 240, 30));

        jLabel25.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel25.setText("Edad de la mascota:");
        jPanel10.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 430, -1, -1));

        comboEdad1.setBackground(new java.awt.Color(255, 255, 255));
        comboEdad1.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        comboEdad1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "1-12 Meses", "1 año", "2 años", "3 años", "4 años", "5 años", "6 o más " }));
        comboEdad1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboEdad1ActionPerformed(evt);
            }
        });
        jPanel10.add(comboEdad1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 470, 240, 30));

        jLabel27.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel27.setText("Tipo de mascota:");
        jPanel10.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 340, -1, -1));

        comboTipo1.setBackground(new java.awt.Color(255, 255, 255));
        comboTipo1.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        comboTipo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "Perro", "Gato", "Hamster", "Conejo", "Otro..." }));
        jPanel10.add(comboTipo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 380, 240, 30));
        jPanel10.add(fechaJDATE, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 119, 230, 30));

        jScrollPane3.setViewportView(jPanel10);

        jTabbedPane2.addTab("historial", jScrollPane3);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel9.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1265, 6, -1, -1));

        jTable2.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Mascota", "Tipo", "Edad", "Motivo", "Fecha", "Veterinario"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jPanel9.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 890, 260));

        jLabel6.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        jLabel6.setText("NRO DOCUMENTO");
        jPanel9.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, -1, 30));

        txtDocumento1.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        jPanel9.add(txtDocumento1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 200, 30));

        jButton2.setBackground(new java.awt.Color(196, 154, 237));
        jButton2.setFont(new java.awt.Font("Tw Cen MT", 3, 20)); // NOI18N
        jButton2.setForeground(new java.awt.Color(47, 22, 57));
        jButton2.setText("BUSCAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 50, 110, 30));

        jTabbedPane2.addTab("Consulta historial", jPanel9);

        getContentPane().add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, 1110, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        if (!validarCampos()) {
            return; 
        }

        // Convertir fecha JDateChooser a LocalDate
        LocalDate fechaConsulta = fechaJDATE.getDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        // Obtener la fecha actual del sistema
        LocalDate fechaActual = LocalDate.now();

        // Validar que sea exactamente hoy
        if (!fechaConsulta.equals(fechaActual)) {
            JOptionPane.showMessageDialog(null, "Solo se permite seleccionar la fecha de hoy.");
            return; 
        }

        String edadTexto = (String) comboEdad1.getSelectedItem();
        String edadNumerica = edadTexto.replaceAll("[^0-9]", "").trim();

        int edad = 0;
        if (!edadNumerica.isEmpty()) {
            edad = Integer.parseInt(edadNumerica);
        } else {
            JOptionPane.showMessageDialog(null, "Edad inválida. Por favor selecciona una edad válida.");
            return;
        }

        HistorialEspecifico historial = new HistorialEspecifico(
                txtDocumento.getText().trim(),
                txtNombre.getText().trim(),
                txtApellido.getText().trim(),
                txtTelefono.getText().trim(),
                comboVet.getSelectedItem().toString(),
                fechaConsulta,
                txtMascota.getText().trim(),
                comboGenero.getSelectedItem().toString(),
                comboTipo1.getSelectedItem().toString(),
                edad,
                txtDiagnostico.getText().trim(),
                txtMotivo.getText().trim()
        );

        try {
            HistorialEspecificoController controller = new HistorialEspecificoController();
            controller.guardarHistorial(historial);

            JOptionPane.showMessageDialog(null, "Historial guardado exitosamente");
            limpiarCampos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar historial: " + e.getMessage());
        }
    }//GEN-LAST:event_guardarActionPerformed

    private void comboVetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboVetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboVetActionPerformed

    private void comboEdad1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboEdad1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboEdad1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void actualizarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarClientesActionPerformed
        cargarClientes1();
    }//GEN-LAST:event_actualizarClientesActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        jTabbedPane2.setSelectedIndex(0);
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        jTabbedPane2.setSelectedIndex(1);
    }//GEN-LAST:event_jButton5MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        jTabbedPane2.setSelectedIndex(2);
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        cargarHistoriales();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        if (evt.getClickCount() == 1 && jTable2.getSelectedRow() != -1) {
            int filaSeleccionada = jTable2.getSelectedRow();

            String numeroDocumento = txtDocumento1.getText(); // Ajusta si tu JTextField tiene otro nombre
            String fechaStr = jTable2.getValueAt(filaSeleccionada, 5).toString(); // Cambia el 5 si la fecha está en otra columna
            LocalDate fechaConsulta = LocalDate.parse(fechaStr);

            HistorialEspecificoDAOImpl dao = new HistorialEspecificoDAOImpl();
            List<HistorialEspecifico> historiales = dao.buscarPorDocumentoYFecha(numeroDocumento, fechaConsulta);

            if (!historiales.isEmpty()) {
                HistorialEspecifico historial = historiales.get(0);
                HistorialView detalle = new HistorialView(historial);
                detalle.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró historial para esa fecha.");
            }
        }
    }//GEN-LAST:event_jTable2MouseClicked

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
            java.util.logging.Logger.getLogger(inicioEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(inicioEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(inicioEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inicioEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new inicioEmpleados(usuario).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizarClientes;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> comboEdad1;
    private javax.swing.JComboBox<String> comboGenero;
    private javax.swing.JComboBox<String> comboTipo1;
    private javax.swing.JComboBox<String> comboVet;
    private com.toedter.calendar.JDateChooser fechaJDATE;
    private javax.swing.JToggleButton guardar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JPopupMenu ppMenuTabla;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextArea txtDiagnostico;
    private javax.swing.JTextField txtDocumento;
    private javax.swing.JTextField txtDocumento1;
    private javax.swing.JTextField txtMascota;
    private javax.swing.JTextArea txtMotivo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
