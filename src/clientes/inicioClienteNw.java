package clientes;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import com.toedter.calendar.JDateChooser;

    
public class inicioClienteNw extends javax.swing.JFrame {

    // Constantes para índices de pestañas
    private static final int TAB_INICIO = 0;
    private static final int TAB_AGENDA_CONSULTA = 1;
    private static final int TAB_HISTORIAL = 2;
    private static final int TAB_AGENDAR = 3;

    // Componentes de la interfaz
    private JDateChooser dateFecha = new JDateChooser();

    public inicioClienteNw() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        configurarValidaciones();
        
    }


    private void configurarValidaciones() {
        // Configurar formatos iniciales
        dateFecha.setDateFormatString("yyyy-MM-dd");
    }

    private boolean validarFormulario() {
        String documento = txtDocumento.getText().trim();
        String nombreDueño = txtNombreDueno.getText().trim();
        String apellidoDueño = txtApellidoDueno.getText().trim();
        String nombreMascota = txtNombreMascota.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String tipoMascota = cbTipoAnimal.getSelectedItem() != null ? cbTipoAnimal.getSelectedItem().toString() : "";
        String veterinario = cbVeterinario.getSelectedItem() != null ? cbVeterinario.getSelectedItem().toString() : "";
        String motivo = cbMotivoConsulta.getSelectedItem() != null ? cbMotivoConsulta.getSelectedItem().toString() : "";
        Date fecha = dateFecha.getDate();

        if (documento.isEmpty() || !documento.matches("\\d{6,}")) {
            mostrarError("Número de documento inválido", "Ingrese un número de documento válido (mínimo 6 dígitos).");
            return false;
        }

        if (nombreDueño.isEmpty() || !nombreDueño.matches("^[A-Za-záéíóúÁÉÍÓÚñÑ]+(?: [A-Za-záéíóúÁÉÍÓÚñÑ]+)*$")) {
            mostrarError("Nombre inválido", "Ingrese un nombre válido (solo letras y un espacio entre nombres).");
            return false;
        }

        if (apellidoDueño.isEmpty() || !apellidoDueño.matches("^[A-Za-záéíóúÁÉÍÓÚñÑ]+(?: [A-Za-záéíóúÁÉÍÓÚñÑ]+)*$")) {
            mostrarError("Apellido inválido", "Ingrese un apellido válido (solo letras y un espacio entre apellidos).");
            return false;
        }

        if (nombreMascota.isEmpty() || !nombreMascota.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]{2,}")) {
            mostrarError("Nombre de mascota inválido", "Ingrese un nombre de mascota válido (solo letras).");
            return false;
        }

        if (telefono.isEmpty() || !telefono.matches("\\d{7,10}")) {
            mostrarError("Teléfono inválido", "Ingrese un número de teléfono válido (7 a 10 dígitos).");
            return false;
        }

        if (tipoMascota.isEmpty() || tipoMascota.equals("Seleccione")) {
            mostrarError("Tipo de mascota requerido", "Seleccione un tipo de mascota.");
            return false;
        }

        if (veterinario.isEmpty() || veterinario.equals("Seleccione")) {
            mostrarError("Veterinario requerido", "Seleccione un veterinario.");
            return false;
        }

        if (fecha == null || fecha.before(new Date()) && !esMismoDia(fecha, new Date())) {
            mostrarError("Fecha inválida", "Seleccione una fecha válida (hoy o en el futuro).");
            return false;
        }

        if (motivo.isEmpty() || motivo.equals("Seleccione")) {
            mostrarError("Motivo requerido", "Seleccione un motivo de consulta.");
            return false;
        }

        return true;
    }

    private boolean esMismoDia(Date date1, Date date2) {
        if (date1 == null || date2 == null) return false;
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
               cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
               cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }

    private void mostrarError(String titulo, String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
    }
        // Métodos de acción mejorados


    private void limpiarFormulario() {
        txtDocumento.setText("");
        txtNombreDueno.setText("");
        txtApellidoDueno.setText("");
        txtNombreMascota.setText("");
        txtTelefono.setText("");
        cbTipoAnimal.setSelectedIndex(0);
        cbVeterinario.setSelectedIndex(0);
        cbMotivoConsulta.setSelectedIndex(0);
        cbEdadMascota.setSelectedIndex(0);
        dateFecha.setDate(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtDocumento1 = new javax.swing.JTextField();
        consultar1 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtNombre1 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtApellido1 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtTelefono1 = new javax.swing.JTextField();
        txtTipo = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txtMotivo = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        fecha = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtVeterinario = new javax.swing.JTextField();
        imagen3 = new javax.swing.JLabel();
        imagen4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtDocumento2 = new javax.swing.JTextField();
        consultar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        imagen5 = new javax.swing.JLabel();
        imagen6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        txtDocumento = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtNombreDueno = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtApellidoDueno = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtNombreMascota = new javax.swing.JTextField();
        btnAgendar = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        cbTipoAnimal = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        cbEdadMascota = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        cbMotivoConsulta = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        jDayChooser1 = new com.toedter.calendar.JDayChooser();
        jLabel27 = new javax.swing.JLabel();
        cbVeterinario = new javax.swing.JComboBox<>();
        imagen1 = new javax.swing.JLabel();
        imagen2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/1333.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 100));

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        jLabel4.setText("INICIO");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 50, 50, 30));

        jLabel5.setFont(new java.awt.Font("Serif", 3, 14)); // NOI18N
        jLabel5.setText("AGENDA");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, 60, 30));

        jLabel6.setFont(new java.awt.Font("Serif", 3, 14)); // NOI18N
        jLabel6.setText("AGENDAR");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 50, 70, 30));

        jLabel7.setFont(new java.awt.Font("Serif", 3, 14)); // NOI18N
        jLabel7.setText("HISTORIAL");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 50, 90, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 890, -1));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/YY2.png"))); // NOI18N
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, 730, 330));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/32f7ea08-00a6-4865-9eeb-d777bc293d9c (1).png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, 960, 830));

        jTabbedPane1.addTab("inicio", jPanel2);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Tw Cen MT", 3, 50)); // NOI18N
        jLabel11.setText("Agenda de consulta previa");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel14.setText("Nro de documento:");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, -1, -1));

        txtDocumento1.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        txtDocumento1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDocumento1ActionPerformed(evt);
            }
        });
        jPanel3.add(txtDocumento1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 190, -1));

        consultar1.setBackground(new java.awt.Color(196, 154, 237));
        consultar1.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        consultar1.setText("Consultar");
        consultar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultar1ActionPerformed(evt);
            }
        });
        jPanel3.add(consultar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 150, 100, 30));

        jLabel12.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel12.setText("Nombre del dueño:");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, -1, -1));

        txtNombre1.setEditable(false);
        txtNombre1.setBackground(new java.awt.Color(255, 255, 255));
        txtNombre1.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        jPanel3.add(txtNombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, 190, -1));

        jLabel33.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel33.setText("Edad de la mascota:");
        jPanel3.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 240, -1, -1));

        txtEdad.setEditable(false);
        txtEdad.setBackground(new java.awt.Color(255, 255, 255));
        txtEdad.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        txtEdad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEdadActionPerformed(evt);
            }
        });
        jPanel3.add(txtEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 290, 200, 30));

        jLabel13.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel13.setText("Apellido del dueño:");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 350, -1, -1));

        txtApellido1.setEditable(false);
        txtApellido1.setBackground(new java.awt.Color(255, 255, 255));
        txtApellido1.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        txtApellido1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellido1ActionPerformed(evt);
            }
        });
        jPanel3.add(txtApellido1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 400, 190, -1));

        jLabel24.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel24.setText("Nro de telefono:");
        jPanel3.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 460, -1, -1));

        jLabel34.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel34.setText("Tipo de mascota:");
        jPanel3.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 550, -1, -1));

        txtTelefono1.setEditable(false);
        txtTelefono1.setBackground(new java.awt.Color(255, 255, 255));
        txtTelefono1.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        txtTelefono1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefono1ActionPerformed(evt);
            }
        });
        jPanel3.add(txtTelefono1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 510, 190, -1));

        txtTipo.setEditable(false);
        txtTipo.setBackground(new java.awt.Color(255, 255, 255));
        txtTipo.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        txtTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipoActionPerformed(evt);
            }
        });
        jPanel3.add(txtTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 590, 190, -1));

        jLabel32.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel32.setText("MOTIVO DE LA CONSULTA:");
        jPanel3.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 350, -1, -1));

        txtMotivo.setEditable(false);
        txtMotivo.setBackground(new java.awt.Color(255, 255, 255));
        txtMotivo.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        jPanel3.add(txtMotivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 390, 270, 40));

        jLabel31.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel31.setText("Fecha de la cita:");
        jPanel3.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 460, -1, -1));

        fecha.setEditable(false);
        fecha.setBackground(new java.awt.Color(255, 255, 255));
        fecha.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        fecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaActionPerformed(evt);
            }
        });
        jPanel3.add(fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 500, 190, -1));

        jLabel30.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel30.setText("Veterinario(a):");
        jPanel3.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 550, -1, -1));

        txtVeterinario.setEditable(false);
        txtVeterinario.setBackground(new java.awt.Color(255, 255, 255));
        txtVeterinario.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        txtVeterinario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVeterinarioActionPerformed(evt);
            }
        });
        jPanel3.add(txtVeterinario, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 590, 190, 30));

        imagen3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/GG.jpg"))); // NOI18N
        jPanel3.add(imagen3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-170, 0, 830, 850));

        imagen4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/GG2.jpg"))); // NOI18N
        jPanel3.add(imagen4, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 300, 850));

        jScrollPane2.setViewportView(jPanel3);

        jTabbedPane1.addTab("tab5", jScrollPane2);

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel39.setFont(new java.awt.Font("Tw Cen MT", 3, 50)); // NOI18N
        jLabel39.setText("Historiales medicos");
        jPanel5.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, -1, -1));

        jLabel35.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel35.setText("Nro de documento:");
        jPanel5.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, -1, -1));

        txtDocumento2.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        txtDocumento2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDocumento2ActionPerformed(evt);
            }
        });
        jPanel5.add(txtDocumento2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 190, -1));

        consultar.setBackground(new java.awt.Color(196, 154, 237));
        consultar.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        consultar.setText("Consultar");
        consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarActionPerformed(evt);
            }
        });
        jPanel5.add(consultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 100, 30));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "nombre", "raza", "edad", "Motivo", "Fecha", "Fecha"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 680, 260));

        imagen5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/GG.jpg"))); // NOI18N
        jPanel5.add(imagen5, new org.netbeans.lib.awtextra.AbsoluteConstraints(-160, 0, 810, 840));

        imagen6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/GG2.jpg"))); // NOI18N
        jPanel5.add(imagen6, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, 310, 840));

        jScrollPane3.setViewportView(jPanel5);

        jTabbedPane1.addTab("tab5", jScrollPane3);

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("Tw Cen MT", 3, 50)); // NOI18N
        jLabel16.setText("AGENDAR CONSULTAS");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, -1, -1));

        jLabel37.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel37.setText("Nro de documento:");
        jPanel4.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 180, 30));

        txtDocumento.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        txtDocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDocumentoActionPerformed(evt);
            }
        });
        jPanel4.add(txtDocumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 220, 30));

        jLabel17.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel17.setText("Nombre del dueño:");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, 180, -1));

        txtNombreDueno.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        jPanel4.add(txtNombreDueno, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, 220, 30));

        jLabel19.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel19.setText("Ingrese su apellido:");
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 350, -1, -1));

        txtApellidoDueno.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        jPanel4.add(txtApellidoDueno, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 400, 220, 30));

        jLabel23.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel23.setText("Nro de telefono:");
        jPanel4.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, 180, -1));

        txtTelefono.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });
        jPanel4.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 490, 220, 30));

        jLabel26.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel26.setText("Ingrese el nombre de su mascota:");
        jPanel4.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 590, 330, -1));

        txtNombreMascota.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        jPanel4.add(txtNombreMascota, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 640, 220, 30));

        btnAgendar.setBackground(new java.awt.Color(196, 154, 237));
        btnAgendar.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        btnAgendar.setText("Consultar");
        btnAgendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgendarActionPerformed(evt);
            }
        });
        jPanel4.add(btnAgendar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 700, 100, 30));

        jLabel28.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel28.setText("Seleccione que tipo de animal es:");
        jPanel4.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 140, -1, -1));

        cbTipoAnimal.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        cbTipoAnimal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Perro", "Gato", "Hamster", "Conejo", "Otro..." }));
        jPanel4.add(cbTipoAnimal, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 180, 250, 30));

        jLabel20.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel20.setText("Seleccione la edad de su mascota:");
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 230, -1, -1));

        cbEdadMascota.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        cbEdadMascota.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 año", "2 años", "3 años", "4 años", "5 años", "6 o más años" }));
        cbEdadMascota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEdadMascotaActionPerformed(evt);
            }
        });
        jPanel4.add(cbEdadMascota, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 280, 240, 30));

        jLabel22.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel22.setText("Motivo de la consulta");
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 350, -1, -1));

        cbMotivoConsulta.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        cbMotivoConsulta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Consulta - $40.000", "Vacunacion - $80.000", "Esterilizacion - $170.000", "Examenes de laboratorio - $20.000-$50.000", "Peluqueria y baño - $45.000" }));
        jPanel4.add(cbMotivoConsulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 390, -1, 30));

        jLabel25.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel25.setText("Seleccione la fecha para la consulta:");
        jPanel4.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 450, -1, -1));
        jPanel4.add(jDayChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 480, 220, 110));

        jLabel27.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel27.setText("Veterinario:");
        jPanel4.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 600, 180, -1));

        cbVeterinario.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        cbVeterinario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbVeterinarioActionPerformed(evt);
            }
        });
        jPanel4.add(cbVeterinario, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 640, 280, 30));

        imagen1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/GG.jpg"))); // NOI18N
        jPanel4.add(imagen1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-150, 0, 830, 840));

        imagen2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/GG2.jpg"))); // NOI18N
        jPanel4.add(imagen2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 0, 290, 840));

        jScrollPane4.setViewportView(jPanel4);

        jTabbedPane1.addTab("tab5", jScrollPane4);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 890, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDocumento1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDocumento1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDocumento1ActionPerformed

    private void consultar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_consultar1ActionPerformed

    private void txtEdadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEdadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdadActionPerformed

    private void txtApellido1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellido1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellido1ActionPerformed

    private void txtTelefono1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefono1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefono1ActionPerformed

    private void txtTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoActionPerformed

    private void fechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaActionPerformed

    private void txtVeterinarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVeterinarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVeterinarioActionPerformed

    private void txtDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDocumentoActionPerformed

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void btnAgendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgendarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgendarActionPerformed

    private void cbEdadMascotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEdadMascotaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbEdadMascotaActionPerformed

    private void cbVeterinarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbVeterinarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbVeterinarioActionPerformed

    private void txtDocumento2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDocumento2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDocumento2ActionPerformed

    private void consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarActionPerformed

    }//GEN-LAST:event_consultarActionPerformed

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
            java.util.logging.Logger.getLogger(inicioClienteNw.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(inicioClienteNw.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(inicioClienteNw.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inicioClienteNw.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new inicioClienteNw().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgendar;
    private javax.swing.JComboBox<String> cbEdadMascota;
    private javax.swing.JComboBox<String> cbMotivoConsulta;
    private javax.swing.JComboBox<String> cbTipoAnimal;
    private javax.swing.JComboBox<String> cbVeterinario;
    private javax.swing.JButton consultar;
    private javax.swing.JButton consultar1;
    private javax.swing.JTextField fecha;
    private javax.swing.JLabel imagen1;
    private javax.swing.JLabel imagen2;
    private javax.swing.JLabel imagen3;
    private javax.swing.JLabel imagen4;
    private javax.swing.JLabel imagen5;
    private javax.swing.JLabel imagen6;
    private com.toedter.calendar.JDayChooser jDayChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtApellido1;
    private javax.swing.JTextField txtApellidoDueno;
    private javax.swing.JTextField txtDocumento;
    private javax.swing.JTextField txtDocumento1;
    private javax.swing.JTextField txtDocumento2;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtMotivo;
    private javax.swing.JTextField txtNombre1;
    private javax.swing.JTextField txtNombreDueno;
    private javax.swing.JTextField txtNombreMascota;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTelefono1;
    private javax.swing.JTextField txtTipo;
    private javax.swing.JTextField txtVeterinario;
    // End of variables declaration//GEN-END:variables
}
