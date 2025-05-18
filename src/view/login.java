/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.AuthController;
import model.entidades.Usuario;
import javax.swing.JOptionPane;
import model.entidades.Rol;
import view.registro;

/**
 *
 * @author aylee
 */
public class login extends javax.swing.JFrame {

    /**
     * Creates new form login
     */
    private AuthController authController;
    
    public login() {
        initComponents();
        String[] rolesBonitos = {
        Rol.ADMINISTRADOR.toString(),
        Rol.CLIENTE.toString(),
        Rol.VETERINARIO.toString()
        };
        comboRol.setModel(new javax.swing.DefaultComboBoxModel<>(rolesBonitos));
        authController = new AuthController();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cuenta = new javax.swing.JLabel();
        registro = new javax.swing.JButton();
        ingreso = new javax.swing.JButton();
        comboRol = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_imgs/aylen 200 x 200.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 210, 200));

        jLabel8.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 2, 60)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(47, 22, 57));
        jLabel8.setText("BIENVENIDO");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 300, 60));

        jLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed", 3, 54)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(47, 22, 57));
        jLabel2.setText("VETERINARIA");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, 270, 30));

        jLabel3.setFont(new java.awt.Font("Tw Cen MT Condensed", 3, 54)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(47, 22, 57));
        jLabel3.setText("ANIMALSLOVE");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 360, 280, 40));

        cuenta.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        cuenta.setForeground(new java.awt.Color(0, 0, 0));
        cuenta.setText("No tiene cuenta?");
        jPanel1.add(cuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 530, 170, 40));

        registro.setBackground(new java.awt.Color(255, 255, 255));
        registro.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        registro.setForeground(new java.awt.Color(0, 0, 0));
        registro.setText("Crear cuenta");
        registro.setActionCommand("");
        registro.setBorderPainted(false);
        registro.setContentAreaFilled(false);
        registro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registroActionPerformed(evt);
            }
        });
        jPanel1.add(registro, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 540, 160, 20));

        ingreso.setBackground(new java.awt.Color(196, 154, 237));
        ingreso.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        ingreso.setForeground(new java.awt.Color(47, 22, 57));
        ingreso.setText("Ingresar");
        ingreso.setActionCommand("");
        ingreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresoActionPerformed(evt);
            }
        });
        jPanel1.add(ingreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 460, 250, 40));

        comboRol.setBackground(new java.awt.Color(255, 255, 255));
        comboRol.setFont(new java.awt.Font("Tw Cen MT", 3, 22)); // NOI18N
        comboRol.setForeground(new java.awt.Color(0, 0, 0));
        comboRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cliente", "Veterinario", "Administrador" }));
        comboRol.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        comboRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboRolActionPerformed(evt);
            }
        });
        jPanel1.add(comboRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 390, 240, -1));

        jLabel10.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Seleccione su rol*");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 360, 170, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_imgs/loginMorado.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 510, 670));

        jLabel11.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Ingrese su contraseña*");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 250, 210, 40));

        txtUsuario.setFont(new java.awt.Font("Tw Cen MT", 3, 22)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(0, 0, 0));
        txtUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 200, 240, 30));

        jLabel7.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Ingrese su usuario*");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 160, 170, 30));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_imgs/veterinario (1).png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 60, 80, 80));

        jLabel4.setFont(new java.awt.Font("Tw Cen MT Condensed", 3, 46)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Inicie sesion");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 70, 210, 60));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 560, 130, 20));

        txtPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        jPanel1.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 300, 240, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 904, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registroActionPerformed
        registro registroView = new registro();
        registroView.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_registroActionPerformed

    private void ingresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresoActionPerformed
       String username = txtUsuario.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Usuario y contraseña son requeridos");
        return;
        }
        Rol rolSeleccionado = (Rol) comboRol.getSelectedItem();
        Usuario usuario = authController.login(username, password, rolSeleccionado);

        if (usuario != null) {
        JOptionPane.showMessageDialog(this, "Bienvenido " + usuario.getUser());
    
        // Redirección según el rol del usuario
        switch(usuario.getRole()) {
        case ADMINISTRADOR:
            Admin adminView = new Admin(usuario);
            adminView.setVisible(true);
            break;
        /*case VETERINARIO: // 
            inicioEmpleados vetView = new inicioEmpleados(usuario);
            vetView.setVisible(true);
            break;
        case CLIENTE:
            inicioCliente clienteView = new inicioCliente(usuario);
            clienteView.setVisible(true);
            break;
        default:
            JOptionPane.showMessageDialog(this, "Rol no reconocido", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
    }
    this.dispose();
} else {
    JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", 
        "Error", JOptionPane.ERROR_MESSAGE);
}*/
        }
        }
    }//GEN-LAST:event_ingresoActionPerformed
        
    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void comboRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboRolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboRolActionPerformed

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
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboRol;
    private javax.swing.JLabel cuenta;
    private javax.swing.JButton ingreso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton registro;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
