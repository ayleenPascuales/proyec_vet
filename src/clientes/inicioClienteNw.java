package clientes;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.toedter.calendar.JDateChooser;
import controller.CitasController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.dao.CitasDAO;
import model.dao.CitasDAOImpl;
import model.dao.HistorialEspecificoDAO;
import model.dao.HistorialEspecificoDAOImpl;
import model.dao.VeterinarioDAO;
import model.dao.VeterinarioDAOImpl;
import model.entidades.Citas;
import model.entidades.Historial;
import model.entidades.HistorialEspecifico;
import model.entidades.Usuario;
import model.entidades.Veterinario;
import view.HistorialView;

public class inicioClienteNw extends javax.swing.JFrame {

    // Componentes de la interfaz
    private static Usuario usuario;
    private CitasDAO citasDAO = new CitasDAOImpl();

    public inicioClienteNw(Usuario usuario) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.usuario = usuario;
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        //configurarValidaciones();
        cargarComboVeterinarios();

    }

    private void cargarHistoriales() {
        String documentoIngresado = txtdocumentoHistorial.getText().trim();
        if (documentoIngresado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingresa un número de documento.", "Campo vacío", JOptionPane.WARNING_MESSAGE);
            return;
        }

        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0); // Limpiar tabla

        HistorialEspecificoDAO historialdao = new HistorialEspecificoDAOImpl();
        java.util.List<HistorialEspecifico> historiales = historialdao.buscarPorDocumento(documentoIngresado);

        if (historiales.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron historiales para el documento ingresado.", "Documento no encontrado", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        for (HistorialEspecifico h : historiales) {
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

    private java.util.List<Veterinario> listaVeterinarios = new ArrayList<>();

    private void cargarComboVeterinarios() {
        VeterinarioDAO veterinarioDAO = new VeterinarioDAOImpl();
        listaVeterinarios = veterinarioDAO.obtenerTodosVeterinarios();

        cbVet.removeAllItems(); // Limpiar combo
        cbVet.addItem("Seleccione...");

        for (Veterinario vet : listaVeterinarios) {
            cbVet.addItem(vet.getNombres() + " " + vet.getApellidos());
        }
    }

    private boolean validarFormulario() {
        String documento = txtDocumento1.getText().trim();
        String nombreDueño = txtNombreDueno.getText().trim();
        String apellidoDueño = txtApellidoDueno.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String nombreMascota = txtNombreMascota.getText().trim();
        String tipoMascota = cbEdad.getSelectedItem() != null ? cbEdad.getSelectedItem().toString() : "";
        String motivo = cbMotivoConsulta.getSelectedItem() != null ? cbMotivoConsulta.getSelectedItem().toString() : "";
        Date fecha = JdateFecha.getDate();
        String hora = txtHora.getText().trim();
        String veterinario = cbVet.getSelectedItem() != null ? cbVet.getSelectedItem().toString() : "";

        if (documento.isEmpty() || !documento.matches("\\d{8,10}")) {
            mostrarError("Número de documento inválido", "Ingrese un número de documento válido.");
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

        if (fecha == null) {
            mostrarError("Fecha inválida", "Seleccione una fecha.");
            return false;
        }
        if (hora.isEmpty() || !validarHoraConSegundos(hora)) {
            return false;
        }

        if (motivo.isEmpty() || motivo.equals("Seleccione")) {
            mostrarError("Motivo requerido", "Seleccione un motivo de consulta.");
            return false;
        }

        return true;
    }

    private boolean validarHoraConSegundos(String horaTexto) {
        List<Citas> citasExistentes = citasDAO.obtenerTodas();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        try {
            LocalTime hora = LocalTime.parse(horaTexto, formatter);
            LocalTime horaInicio = LocalTime.of(8, 0);
            LocalTime horaFin = LocalTime.of(18, 0);

            if (hora.isBefore(horaInicio) || hora.isAfter(horaFin)) {
                JOptionPane.showMessageDialog(null, "La hora debe estar entre 08:00:00 y 18:00:00.");
                return false;
            }
            int minuto = hora.getMinute();
            int segundo = hora.getSecond();

            if (!((minuto == 0 || minuto == 15 || minuto == 30 || minuto == 45) && segundo == 0)) {
                JOptionPane.showMessageDialog(null, "La hora debe ser en intervalos de 15 minutos (ejemplo: 10:00:00, 10:15:00, 10:30:00, 10:45:00).");
                return false;
            }

            Date fechaSeleccionada = JdateFecha.getDate();
            if (fechaSeleccionada == null) {
                JOptionPane.showMessageDialog(null, "Seleccione una fecha válida.");
                return false;
            }

            LocalDate fechaCitaSeleccionada = fechaSeleccionada.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            // Validar si ya existe una cita con la misma fecha y hora
            for (Citas cita : citasExistentes) {
                LocalDate fechaCitaExistente = cita.getFechaConsulta();
                if (fechaCitaExistente.equals(fechaCitaSeleccionada)) {
                    if (cita.gethoraConsulta().equals(hora)) {
                        JOptionPane.showMessageDialog(null, "Ya existe una cita programada a esa hora.");
                        return false;
                    }
                }

            }

            return true;

        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Hora inválida. Usa el formato HH:mm:ss (ej: 14:30:45).");
            return false;
        }
    }

    private void mostrarError(String titulo, String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
    }

    private void limpiarFormulario() {
        txtDocumento1.setText("");
        txtNombreDueno.setText("");
        txtApellidoDueno.setText("");
        txtNombreMascota.setText("");
        txtTelefono.setText("");
        txtHora.setText("");
        cbEdad.setSelectedIndex(0);
        cbVet.setSelectedIndex(0);
        cbMotivoConsulta.setSelectedIndex(0);
        JdateFecha.setDate(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupHistorial = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtNombre1 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txtMotivo1 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtTipo1 = new javax.swing.JTextField();
        txtHora1 = new javax.swing.JTextField();
        txtEdad = new javax.swing.JTextField();
        txtMascota1 = new javax.swing.JTextField();
        txtveterinario = new javax.swing.JTextField();
        txtdocumento1 = new javax.swing.JTextField();
        txtapellido1 = new javax.swing.JTextField();
        txttelefono1 = new javax.swing.JTextField();
        txtfecha = new javax.swing.JTextField();
        consultarCita = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        imagen3 = new javax.swing.JLabel();
        imagen4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnHistorial = new javax.swing.JButton();
        txtdocumentoHistorial = new javax.swing.JTextField();
        imagen5 = new javax.swing.JLabel();
        imagen6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtNombreDueno = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtApellidoDueno = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtNombreMascota = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        cbEdad = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        cbMotivoConsulta = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        JdateFecha = new com.toedter.calendar.JDateChooser();
        txtHora = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtDocumento1 = new javax.swing.JTextField();
        cbTipoanimal = new javax.swing.JComboBox<>();
        cbVet = new javax.swing.JComboBox<>();
        btnAgendar = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        imagen1 = new javax.swing.JLabel();
        imagen2 = new javax.swing.JLabel();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_imgs/aylen 100 x 100.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 100, 100));

        jLabel4.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        jLabel4.setText("ANIMALSLOVE");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 120, 20));

        jLabel5.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        jLabel5.setText("INICIO");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, 50, 20));

        jLabel6.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        jLabel6.setText("AGENDAR");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 40, 80, 20));

        jLabel7.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        jLabel7.setText("AGENDA");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, 70, 20));

        jLabel8.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        jLabel8.setText("HISTORIAL");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 40, 80, 20));

        jLabel9.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        jLabel9.setText("VETERINARIA");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 100, 20));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 60, 80, 10));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 60, 70, 10));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 60, 80, 10));

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 60, 50, 10));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 70));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_imgs/perritos.png"))); // NOI18N
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 730, 330));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_imgs/huellitasVerdes.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 480));

        jTabbedPane1.addTab("inicio", jPanel2);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Tw Cen MT", 3, 50)); // NOI18N
        jLabel11.setText("Agenda de consulta previa");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel14.setText("Nro de documento:");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel12.setText("Nombre del dueño:");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, -1, -1));

        txtNombre1.setEditable(false);
        txtNombre1.setBackground(new java.awt.Color(255, 255, 255));
        txtNombre1.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        jPanel3.add(txtNombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, 190, -1));

        jLabel33.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel33.setText("Edad de la mascota:");
        jPanel3.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 620, -1, 20));

        jLabel13.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel13.setText("Apellido del dueño:");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 340, -1, -1));

        jLabel24.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel24.setText("Nro de telefono:");
        jPanel3.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 430, -1, -1));

        jLabel34.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel34.setText("Nombre de la mascota:");
        jPanel3.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 130, -1, -1));

        jLabel32.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel32.setText("Motivo de la consulta");
        jPanel3.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 250, -1, -1));

        txtMotivo1.setEditable(false);
        txtMotivo1.setBackground(new java.awt.Color(255, 255, 255));
        txtMotivo1.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        jPanel3.add(txtMotivo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 290, 270, -1));

        jLabel31.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel31.setText("Fecha de la cita:");
        jPanel3.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 340, -1, -1));

        jLabel30.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel30.setText("Veterinario(a):");
        jPanel3.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 530, -1, -1));

        txtTipo1.setEditable(false);
        txtTipo1.setBackground(new java.awt.Color(255, 255, 255));
        txtTipo1.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        txtTipo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipo1ActionPerformed(evt);
            }
        });
        jPanel3.add(txtTipo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 570, 190, -1));

        txtHora1.setEditable(false);
        txtHora1.setBackground(new java.awt.Color(255, 255, 255));
        txtHora1.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        txtHora1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHora1ActionPerformed(evt);
            }
        });
        jPanel3.add(txtHora1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 480, 200, -1));

        txtEdad.setEditable(false);
        txtEdad.setBackground(new java.awt.Color(255, 255, 255));
        txtEdad.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        jPanel3.add(txtEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 660, 190, 30));

        txtMascota1.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        jPanel3.add(txtMascota1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 170, 190, 30));

        txtveterinario.setEditable(false);
        txtveterinario.setBackground(new java.awt.Color(255, 255, 255));
        txtveterinario.setFont(new java.awt.Font("Tw Cen MT", 2, 20)); // NOI18N
        jPanel3.add(txtveterinario, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 570, 190, 30));

        txtdocumento1.setFont(new java.awt.Font("Tw Cen MT", 2, 20)); // NOI18N
        txtdocumento1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdocumento1ActionPerformed(evt);
            }
        });
        jPanel3.add(txtdocumento1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 170, 30));

        txtapellido1.setEditable(false);
        txtapellido1.setBackground(new java.awt.Color(255, 255, 255));
        txtapellido1.setFont(new java.awt.Font("Tw Cen MT", 2, 20)); // NOI18N
        jPanel3.add(txtapellido1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 380, 190, 30));

        txttelefono1.setEditable(false);
        txttelefono1.setBackground(new java.awt.Color(255, 255, 255));
        txttelefono1.setFont(new java.awt.Font("Tw Cen MT", 2, 20)); // NOI18N
        jPanel3.add(txttelefono1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 470, 190, 30));

        txtfecha.setEditable(false);
        txtfecha.setBackground(new java.awt.Color(255, 255, 255));
        txtfecha.setFont(new java.awt.Font("Tw Cen MT", 2, 20)); // NOI18N
        jPanel3.add(txtfecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 380, 190, 30));

        consultarCita.setBackground(new java.awt.Color(196, 154, 237));
        consultarCita.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        consultarCita.setText("Consultar");
        consultarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarCitaActionPerformed(evt);
            }
        });
        jPanel3.add(consultarCita, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 150, 100, 30));

        jLabel38.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel38.setText("Hora de la cita:");
        jPanel3.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 440, -1, -1));

        jLabel36.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel36.setText("Tipo de mascota:");
        jPanel3.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 530, -1, -1));

        imagen3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/GG.jpg"))); // NOI18N
        jPanel3.add(imagen3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-150, 0, 830, 850));

        imagen4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/GG2.jpg"))); // NOI18N
        jPanel3.add(imagen4, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 0, 300, 850));

        jScrollPane2.setViewportView(jPanel3);

        jTabbedPane1.addTab("agenda", jScrollPane2);

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel39.setFont(new java.awt.Font("Tw Cen MT", 3, 50)); // NOI18N
        jLabel39.setText("Historiales medicos");
        jPanel5.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, -1, -1));

        jLabel35.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel35.setText("Nro de documento:");
        jPanel5.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 760, 190));

        btnHistorial.setBackground(new java.awt.Color(196, 154, 237));
        btnHistorial.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        btnHistorial.setText("Consultar");
        btnHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistorialActionPerformed(evt);
            }
        });
        jPanel5.add(btnHistorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 150, 100, 30));

        txtdocumentoHistorial.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        txtdocumentoHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdocumentoHistorialActionPerformed(evt);
            }
        });
        jPanel5.add(txtdocumentoHistorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 220, 30));

        imagen5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/GG.jpg"))); // NOI18N
        jPanel5.add(imagen5, new org.netbeans.lib.awtextra.AbsoluteConstraints(-160, 0, 810, 840));

        imagen6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/GG2.jpg"))); // NOI18N
        jPanel5.add(imagen6, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 0, 310, 840));

        jScrollPane3.setViewportView(jPanel5);

        jTabbedPane1.addTab("historial", jScrollPane3);

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("Tw Cen MT", 3, 50)); // NOI18N
        jLabel16.setText("AGENDAR CONSULTAS");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, -1, -1));

        jLabel37.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel37.setText("Nro de documento:");
        jPanel4.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 180, 30));

        jLabel17.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel17.setText("Nombre del dueño:");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 180, -1));

        txtNombreDueno.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        jPanel4.add(txtNombreDueno, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, 220, 30));

        jLabel19.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel19.setText("Ingrese su apellido:");
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 340, -1, -1));

        txtApellidoDueno.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        jPanel4.add(txtApellidoDueno, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 380, 220, 30));

        jLabel23.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel23.setText("Nro de telefono:");
        jPanel4.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 430, 180, -1));

        jLabel26.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel26.setText("Ingrese el nombre de su mascota:");
        jPanel4.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 530, 330, -1));

        txtNombreMascota.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        jPanel4.add(txtNombreMascota, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 570, 220, 30));

        jLabel28.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel28.setText("Seleccione que tipo de animal es:");
        jPanel4.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 630, -1, -1));

        cbEdad.setBackground(new java.awt.Color(255, 255, 255));
        cbEdad.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        cbEdad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "1-12 Meses", "1 Año", "2 Años", "3 Años ", "4 Años", "5 Años", "6 o mas " }));
        jPanel4.add(cbEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 180, 250, 30));

        jLabel20.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel20.setText("Seleccione la edad de su mascota:");
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 140, -1, 20));

        jLabel22.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel22.setText("Motivo de la consulta");
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 240, -1, -1));

        cbMotivoConsulta.setBackground(new java.awt.Color(255, 255, 255));
        cbMotivoConsulta.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        cbMotivoConsulta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione....", "Consulta - $40.000", "Vacunacion - $80.000", "Esterilizacion - $170.000", "Examenes de laboratorio - $20.000-$50.000", "Peluqueria y baño - $45.000" }));
        jPanel4.add(cbMotivoConsulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 280, -1, 30));

        jLabel25.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel25.setText("Seleccione la fecha para la consulta:");
        jPanel4.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 340, -1, -1));

        jLabel27.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel27.setText("Veterinario:");
        jPanel4.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 530, 180, -1));
        jPanel4.add(JdateFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 380, 280, 30));

        txtHora.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        txtHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoraActionPerformed(evt);
            }
        });
        jPanel4.add(txtHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 470, 220, 30));

        txtTelefono.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        jPanel4.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 470, 220, 30));

        txtDocumento1.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        jPanel4.add(txtDocumento1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 220, 30));

        cbTipoanimal.setBackground(new java.awt.Color(255, 255, 255));
        cbTipoanimal.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        cbTipoanimal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "Perro", "Gato", "Hamster", "Conejo", "Otro..." }));
        cbTipoanimal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoanimalActionPerformed(evt);
            }
        });
        jPanel4.add(cbTipoanimal, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 670, 250, 30));

        cbVet.setBackground(new java.awt.Color(255, 255, 255));
        cbVet.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        jPanel4.add(cbVet, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 570, 250, 30));

        btnAgendar.setBackground(new java.awt.Color(196, 154, 237));
        btnAgendar.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        btnAgendar.setText("Agendar");
        btnAgendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgendarActionPerformed(evt);
            }
        });
        jPanel4.add(btnAgendar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 670, 100, 30));

        jLabel21.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jLabel21.setText("Ingrese la hora para la consulta:");
        jPanel4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 430, -1, -1));

        imagen1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/GG.jpg"))); // NOI18N
        jPanel4.add(imagen1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-150, 0, 830, 840));

        imagen2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/GG2.jpg"))); // NOI18N
        jPanel4.add(imagen2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 0, 290, 840));

        jScrollPane4.setViewportView(jPanel4);

        jTabbedPane1.addTab("agendar", jScrollPane4);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 890, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTipo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipo1ActionPerformed

    private void txtHora1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHora1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHora1ActionPerformed

    private void txtHoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoraActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_jLabel8MouseClicked

    private void consultarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarCitaActionPerformed
        String documento = txtdocumento1.getText().trim();
        String nombreMascota = txtMascota1.getText().trim();

        CitasDAO citasDAO = new CitasDAOImpl();
        Citas cita = citasDAO.buscarPorDocumentoYNombreMascota(documento, nombreMascota);

        if (cita != null) {
            txtNombre1.setText(cita.getNombreDueno());
            txtapellido1.setText(cita.getApellidoDueno());
            txttelefono1.setText(cita.getTelefono());
            txtTipo1.setText(cita.getTipoAnimal());
            txtEdad.setText(String.valueOf(cita.getEdadMascota()));
            txtMotivo1.setText(cita.getMotivoConsulta());
            txtfecha.setText(cita.getFechaConsulta().toString());
            txtHora1.setText(cita.gethoraConsulta().toString());
            txtveterinario.setText(cita.getVeterinario());
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró una cita con esos datos.");
        }
    }//GEN-LAST:event_consultarCitaActionPerformed

    private void btnHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorialActionPerformed
        cargarHistoriales();
    }//GEN-LAST:event_btnHistorialActionPerformed

    private void txtdocumentoHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdocumentoHistorialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdocumentoHistorialActionPerformed

    private void cbTipoanimalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoanimalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTipoanimalActionPerformed

    private void btnAgendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgendarActionPerformed
        if (!validarFormulario()) {
            return;
        }

        // Convertir fecha del JDateChooser a LocalDate
        LocalDate fechaConsulta = JdateFecha.getDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        // Obtener fecha de hoy
        LocalDate fechaActual = LocalDate.now();

        // Validar que la fecha no sea anterior a hoy
        if (fechaConsulta.isBefore(fechaActual)) {
            JOptionPane.showMessageDialog(null, "La fecha no puede ser anterior a hoy. Selecciona una fecha válida.");
            return;
        }

        String horaString = txtHora.getText().trim(); // ej. "14:30"
        LocalTime horaConsulta = LocalTime.parse(horaString);

        String edadTexto = (String) cbEdad.getSelectedItem();
        String edadNumerica = edadTexto.replaceAll("[^0-9]", "").trim();

        int edad = 0;
        if (!edadNumerica.isEmpty()) {
            edad = Integer.parseInt(edadNumerica);
        } else {
            JOptionPane.showMessageDialog(null, "Edad inválida. Por favor selecciona una edad válida.");
            return;
        }

// Crear objeto Citas con los datos del formulario
        Citas cita = new Citas(
                txtDocumento1.getText().trim(),
                txtNombreDueno.getText().trim(),
                txtApellidoDueno.getText().trim(),
                txtTelefono.getText().trim(),
                txtNombreMascota.getText().trim(),
                cbTipoanimal.getSelectedItem().toString(),
                edad,
                cbMotivoConsulta.getSelectedItem().toString(),
                fechaConsulta,
                horaConsulta,
                cbVet.getSelectedItem().toString()
        );

        try {
            // Instanciar el controller y guardar la cita
            CitasController controller = new CitasController();
            controller.guardarCita(cita);

            JOptionPane.showMessageDialog(null, "Cita guardada exitosamente");
            limpiarFormulario();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar la cita: " + e.getMessage());
        }

    }//GEN-LAST:event_btnAgendarActionPerformed

    private void txtdocumento1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdocumento1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdocumento1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 1 && jTable1.getSelectedRow() != -1) {
            int filaSeleccionada = jTable1.getSelectedRow();

            String numeroDocumento = txtdocumentoHistorial.getText(); // Ajusta si tu JTextField tiene otro nombre
            String fechaStr = jTable1.getValueAt(filaSeleccionada, 5).toString(); // Cambia el 5 si la fecha está en otra columna
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
    }//GEN-LAST:event_jTable1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(inicioClienteNw.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(inicioClienteNw.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(inicioClienteNw.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inicioClienteNw.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new inicioClienteNw(usuario).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser JdateFecha;
    private javax.swing.JButton btnAgendar;
    private javax.swing.JButton btnHistorial;
    private javax.swing.JComboBox<String> cbEdad;
    private javax.swing.JComboBox<String> cbMotivoConsulta;
    private javax.swing.JComboBox<String> cbTipoanimal;
    private javax.swing.JComboBox<String> cbVet;
    private javax.swing.JButton consultarCita;
    private javax.swing.JLabel imagen1;
    private javax.swing.JLabel imagen2;
    private javax.swing.JLabel imagen3;
    private javax.swing.JLabel imagen4;
    private javax.swing.JLabel imagen5;
    private javax.swing.JLabel imagen6;
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
    private javax.swing.JLabel jLabel21;
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
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPopupMenu popupHistorial;
    private javax.swing.JTextField txtApellidoDueno;
    private javax.swing.JTextField txtDocumento1;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtHora;
    private javax.swing.JTextField txtHora1;
    private javax.swing.JTextField txtMascota1;
    private javax.swing.JTextField txtMotivo1;
    private javax.swing.JTextField txtNombre1;
    private javax.swing.JTextField txtNombreDueno;
    private javax.swing.JTextField txtNombreMascota;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTipo1;
    private javax.swing.JTextField txtapellido1;
    private javax.swing.JTextField txtdocumento1;
    private javax.swing.JTextField txtdocumentoHistorial;
    private javax.swing.JTextField txtfecha;
    private javax.swing.JTextField txttelefono1;
    private javax.swing.JTextField txtveterinario;
    // End of variables declaration//GEN-END:variables
}
