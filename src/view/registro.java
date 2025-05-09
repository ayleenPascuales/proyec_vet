/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.*;
import javax.swing.*;
import controller.AuthController;
import controller.ClienteController;
import controller.MascotaController;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import javax.swing.JOptionPane;
import model.entidades.Mascota;
import model.entidades.Cliente;
import model.entidades.Rol;


/**
 *
 * @author aylee
 */
public class registro extends javax.swing.JFrame {

    private AuthController authController;
    private ClienteController clienteController;
    private MascotaController mascotaController;
   
    

    
    public registro() {
        initComponents();
        calendarFechaNacimiento = new com.toedter.calendar.JCalendar();
        //calendarFechaNacimiento.setDateFormatString("dd/MM/yyyy");
        authController = new AuthController();
        clienteController = new ClienteController();
        mascotaController = new MascotaController();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Rmascota = new javax.swing.JButton();
        registrar = new javax.swing.JButton();
        Rdueno = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        a8 = new javax.swing.JLabel();
        txtNombreMascota = new javax.swing.JTextField();
        txtEdadMascota = new javax.swing.JTextField();
        a9 = new javax.swing.JLabel();
        a10 = new javax.swing.JLabel();
        txtRazaMascota = new javax.swing.JTextField();
        a11 = new javax.swing.JLabel();
        comboTipoMascota = new javax.swing.JComboBox<>();
        a12 = new javax.swing.JLabel();
        comboGeneroMascota = new javax.swing.JComboBox<>();
        a13 = new javax.swing.JLabel();
        comboEsterilizado = new javax.swing.JComboBox<>();
        a14 = new javax.swing.JLabel();
        comboPesoMascota = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        a = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        a1 = new javax.swing.JLabel();
        txtDocumento = new javax.swing.JTextField();
        a2 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        a3 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        a4 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        a5 = new javax.swing.JLabel();
        a6 = new javax.swing.JLabel();
        a7 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        calendarFechaNacimiento = new com.toedter.calendar.JCalendar();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Rmascota.setBackground(new java.awt.Color(196, 154, 237));
        Rmascota.setFont(new java.awt.Font("Tw Cen MT Condensed", 3, 24)); // NOI18N
        Rmascota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_imgs/circulo (1).png"))); // NOI18N
        Rmascota.setText("Datos de tu mascota");
        Rmascota.setContentAreaFilled(false);
        Rmascota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RmascotaMouseClicked(evt);
            }
        });
        jPanel1.add(Rmascota, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 240, 30));

        registrar.setBackground(new java.awt.Color(196, 154, 237));
        registrar.setFont(new java.awt.Font("Tw Cen MT Condensed", 3, 24)); // NOI18N
        registrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_imgs/circulo (1).png"))); // NOI18N
        registrar.setText("Registrar");
        registrar.setContentAreaFilled(false);
        registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarActionPerformed(evt);
            }
        });
        jPanel1.add(registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 180, 30));

        Rdueno.setBackground(new java.awt.Color(196, 154, 237));
        Rdueno.setFont(new java.awt.Font("Tw Cen MT Condensed", 3, 24)); // NOI18N
        Rdueno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_imgs/circulo (1).png"))); // NOI18N
        Rdueno.setText("Datos personales");
        Rdueno.setContentAreaFilled(false);
        Rdueno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RduenoMouseClicked(evt);
            }
        });
        jPanel1.add(Rdueno, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 210, 30));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_imgs/imagen (2).png"))); // NOI18N
        jLabel5.setText("jLabel5");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 300, 140));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        a8.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        a8.setForeground(new java.awt.Color(0, 0, 0));
        a8.setText("Nombre de tu mascota*");
        jPanel4.add(a8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 170, 25));

        txtNombreMascota.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        txtNombreMascota.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel4.add(txtNombreMascota, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 160, 30));

        txtEdadMascota.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        txtEdadMascota.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel4.add(txtEdadMascota, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 160, 30));

        a9.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        a9.setForeground(new java.awt.Color(0, 0, 0));
        a9.setText("Edad de tu mascota*");
        jPanel4.add(a9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 160, 25));

        a10.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        a10.setForeground(new java.awt.Color(0, 0, 0));
        a10.setText("Tipo de mascota*");
        jPanel4.add(a10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 130, 20));

        txtRazaMascota.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        txtRazaMascota.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel4.add(txtRazaMascota, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 470, 160, 30));

        a11.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        a11.setForeground(new java.awt.Color(0, 0, 0));
        a11.setText("Raza de tu mascota*");
        jPanel4.add(a11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 440, 160, 25));

        comboTipoMascota.setBackground(new java.awt.Color(255, 255, 255));
        comboTipoMascota.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        comboTipoMascota.setForeground(new java.awt.Color(0, 0, 0));
        comboTipoMascota.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gato", "Perro", "Hamster", "Otro", " " }));
        comboTipoMascota.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel4.add(comboTipoMascota, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 160, 30));

        a12.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        a12.setForeground(new java.awt.Color(0, 0, 0));
        a12.setText("Genero de tu mascota*");
        jPanel4.add(a12, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, 170, 20));

        comboGeneroMascota.setBackground(new java.awt.Color(255, 255, 255));
        comboGeneroMascota.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        comboGeneroMascota.setForeground(new java.awt.Color(0, 0, 0));
        comboGeneroMascota.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hembra ", "Macho", " ", " " }));
        comboGeneroMascota.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel4.add(comboGeneroMascota, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 200, 160, 30));

        a13.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        a13.setForeground(new java.awt.Color(0, 0, 0));
        a13.setText("Esta esterilizado?");
        jPanel4.add(a13, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 260, 130, 20));

        comboEsterilizado.setBackground(new java.awt.Color(255, 255, 255));
        comboEsterilizado.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        comboEsterilizado.setForeground(new java.awt.Color(0, 0, 0));
        comboEsterilizado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Si", "No" }));
        comboEsterilizado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel4.add(comboEsterilizado, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 290, 160, 30));

        a14.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        a14.setForeground(new java.awt.Color(0, 0, 0));
        a14.setText("Peso de la mascota");
        jPanel4.add(a14, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 350, 150, 20));

        comboPesoMascota.setBackground(new java.awt.Color(255, 255, 255));
        comboPesoMascota.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        comboPesoMascota.setForeground(new java.awt.Color(0, 0, 0));
        comboPesoMascota.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0 - 5 kg", "6 - 10 kg", "11 -15 kg", "16 - 20 kg", "21 - 25 kg", "+ 26 kg" }));
        comboPesoMascota.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel4.add(comboPesoMascota, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 380, 160, 30));

        jLabel3.setFont(new java.awt.Font("Tw Cen MT Condensed", 3, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(47, 22, 57));
        jLabel3.setText("Datos de tu mascota");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 280, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_imgs/wao.png"))); // NOI18N
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 70, -1));

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, -1, 50));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel4.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 270, 10));

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, -1, -1));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 570, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -30, -1, -1));

        jTabbedPane1.addTab("tab2", jPanel4);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        a.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        a.setForeground(new java.awt.Color(0, 0, 0));
        a.setText("Nombre*");
        jPanel3.add(a, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 92, 25));

        txtNombre.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        txtNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel3.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 160, 30));

        txtApellido.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        txtApellido.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel3.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 160, 30));

        a1.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        a1.setForeground(new java.awt.Color(0, 0, 0));
        a1.setText("Apellido*");
        jPanel3.add(a1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 92, 25));

        txtDocumento.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        txtDocumento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel3.add(txtDocumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 540, 170, 30));

        a2.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        a2.setForeground(new java.awt.Color(0, 0, 0));
        a2.setText("Fecha de nacimiento*");
        jPanel3.add(a2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, 170, 25));

        txtTelefono.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        txtTelefono.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel3.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 200, 160, 30));

        a3.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        a3.setForeground(new java.awt.Color(0, 0, 0));
        a3.setText("Nro de telefono*");
        jPanel3.add(a3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, 120, 25));

        txtEmail.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        txtEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel3.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 280, 160, 30));

        a4.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        a4.setForeground(new java.awt.Color(0, 0, 0));
        a4.setText("Email*");
        jPanel3.add(a4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 250, 92, 25));

        txtUser.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        txtUser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel3.add(txtUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 370, 160, 30));

        a5.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        a5.setForeground(new java.awt.Color(0, 0, 0));
        a5.setText("Usuario*");
        jPanel3.add(a5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 340, 92, 25));

        a6.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        a6.setForeground(new java.awt.Color(0, 0, 0));
        a6.setText("Nro de documento*");
        jPanel3.add(a6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 510, 150, 25));

        a7.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        a7.setForeground(new java.awt.Color(0, 0, 0));
        a7.setText("Contraseña*");
        jPanel3.add(a7, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 430, 92, 25));

        txtPassword.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        txtPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel3.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 460, 160, 30));

        jLabel4.setFont(new java.awt.Font("Tw Cen MT Condensed", 3, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(47, 22, 57));
        jLabel4.setText("Datos Personales");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 230, 40));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_imgs/form.png"))); // NOI18N
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, 70, -1));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 230, 10));

        calendarFechaNacimiento.setBackground(new java.awt.Color(255, 255, 255));
        calendarFechaNacimiento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel3.add(calendarFechaNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 200, 120));

        jTabbedPane1.addTab("tab1", jPanel3);

        jPanel2.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 570, 640));

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 570, 640));

        jLabel9.setFont(new java.awt.Font("Tw Cen MT Condensed", 3, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(47, 22, 57));
        jLabel9.setText("Vet Animalslove");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 220, 40));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_imgs/aylen 100 x 100.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 110, 100));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_imgs/nube.jpg"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 640));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarActionPerformed
 try {
            // Obtener datos del formulario
            String numeroDocumento = txtDocumento.getText().trim();
            String nombres = txtNombre.getText().trim();
            String apellidos = txtApellido.getText().trim();
            //LocalDate fechaNacimiento = parseFecha(calendarFechaNacimiento.get;
            
            Date fechaNacimientoDate = calendarFechaNacimiento.getDate();
            if (fechaNacimientoDate == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha de nacimiento");
            return;
            }
            LocalDate fechaNacimiento = fechaNacimientoDate.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
            String email = txtEmail.getText().trim();
            String telefono = txtTelefono.getText().trim();
            String username = txtUser.getText().trim();
            String password = new String(txtPassword.getText().trim());
            
             // Registrar cliente
            if (!authController.registrarUsuario(username, password, Rol.CLIENTE)) {
                JOptionPane.showMessageDialog(this, "El nombre de usuario ya existe");
                return;
            } 
            // Crear y registrar cliente
            Cliente cliente = new Cliente(username, numeroDocumento, nombres, apellidos,fechaNacimiento,email, telefono);
            cliente.setUser(username);
            cliente.setNumeroDocumento(numeroDocumento);
            cliente.setNombres(nombres);
            cliente.setApellidos(apellidos);
            cliente.setFechaNacimiento(fechaNacimiento);
            cliente.setEmail(email);
            cliente.setTelefono(telefono);
            
            
            if (!clienteController.registrarCliente(username, numeroDocumento, nombres, apellidos,fechaNacimiento,email, telefono)){
                JOptionPane.showMessageDialog(this, "Error al registrar cliente", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
             Mascota mascota = obtenerDatosMascota(numeroDocumento);
                if (mascota != null) {
                    if (!mascotaController.registrarMascota(mascota)) {
                        JOptionPane.showMessageDialog(this, "Cliente registrado pero hubo error con la mascota", 
                            "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                }
                JOptionPane.showMessageDialog(this, "Registro exitoso! Ahora puede iniciar sesión");
            new login().setVisible(true);
            this.dispose();
            
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use dd/MM/yyyy", 
                "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private boolean validarCamposCliente(String documento, String nombres, String apellidos, 
                                       LocalDate fechaNacimiento, String email, 
                                       String telefono, String username, 
                                       String password, String confirmPassword) {
        // Validar campos vacíos
        if (documento.isEmpty() || nombres.isEmpty() || apellidos.isEmpty() || 
            email.isEmpty() || telefono.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son requeridos");
            return true;
        }
        // Validar formato de email
        if (!email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(this, "Ingrese un email válido");
            return true;
        }
        
        // Validar coincidencia de contraseñas
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden");
            return true;
        }
        
        // Validar longitud mínima de contraseña
        if (password.length() < 6) {
            JOptionPane.showMessageDialog(this, "La contraseña debe tener al menos 6 caracteres");
            return true;
        }
        return false;
    }
            
        private Mascota obtenerDatosMascota(String idCliente) {
        String nombreMascota = txtNombreMascota.getText().trim();
        String edad = txtEdadMascota.getText().trim();
        String especie = (String) comboTipoMascota.getSelectedItem();
        String raza = txtRazaMascota.getText().trim();
        String sexo = (String) comboGeneroMascota.getSelectedItem();
        boolean esterilizado = (boolean) comboEsterilizado.getSelectedItem();
        String peso = (String) comboPesoMascota.getSelectedItem();
        
        if (nombreMascota.isEmpty() || edad.isEmpty() || raza.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos de mascota son requeridos");
            return null;
        }
         return null;
    }//GEN-LAST:event_registrarActionPerformed

    private void RduenoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RduenoMouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_RduenoMouseClicked

    private void RmascotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RmascotaMouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_RmascotaMouseClicked
    
    
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
            java.util.logging.Logger.getLogger(registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new registro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Rdueno;
    private javax.swing.JButton Rmascota;
    private javax.swing.JLabel a;
    private javax.swing.JLabel a1;
    private javax.swing.JLabel a10;
    private javax.swing.JLabel a11;
    private javax.swing.JLabel a12;
    private javax.swing.JLabel a13;
    private javax.swing.JLabel a14;
    private javax.swing.JLabel a2;
    private javax.swing.JLabel a3;
    private javax.swing.JLabel a4;
    private javax.swing.JLabel a5;
    private javax.swing.JLabel a6;
    private javax.swing.JLabel a7;
    private javax.swing.JLabel a8;
    private javax.swing.JLabel a9;
    private com.toedter.calendar.JCalendar calendarFechaNacimiento;
    private javax.swing.JComboBox<String> comboEsterilizado;
    private javax.swing.JComboBox<String> comboGeneroMascota;
    private javax.swing.JComboBox<String> comboPesoMascota;
    private javax.swing.JComboBox<String> comboTipoMascota;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton registrar;
    javax.swing.JTextField txtApellido;
    javax.swing.JTextField txtDocumento;
    javax.swing.JTextField txtEdadMascota;
    javax.swing.JTextField txtEmail;
    javax.swing.JTextField txtNombre;
    javax.swing.JTextField txtNombreMascota;
    javax.swing.JTextField txtPassword;
    javax.swing.JTextField txtRazaMascota;
    javax.swing.JTextField txtTelefono;
    javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
