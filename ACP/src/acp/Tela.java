/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acp;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author erickkimura
 */
public class Tela extends javax.swing.JFrame {

    /**
     * Creates new form Tela
     */
    Erros tratamento;
    private boolean base_inserido = false;
    private boolean erro = false;
    private boolean junto = false;
    ArrayList telaBC;

    public Tela() {
        initComponents();

        tratamento = new Erros();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();

        jMenuItem2.setText("jMenuItem2");

        jMenu2.setText("File");
        jMenuBar2.add(jMenu2);

        jMenu3.setText("Edit");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("File");
        jMenuBar3.add(jMenu4);

        jMenu5.setText("Edit");
        jMenuBar3.add(jMenu5);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("ACP - Desenvolvido por Erick , Giovanni e Lucas\nPara ajudar digite /help");
        jScrollPane1.setViewportView(jTextArea1);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Carregar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem3.setText("Sair");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu6.setText("Ajuda");

        jMenuItem4.setText("Tabela de Erros");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem4);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE)
                    .addComponent(jTextField1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        JFileChooser file = new JFileChooser();

        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        file.setApproveButtonText("Open");
        file.setApproveButtonToolTipText("Open");
        file.setApproveButtonMnemonic('O');
        ArrayList<String> temp = new ArrayList<String>();
        ArrayList<String> temp_consulta = new ArrayList<String>();

        File arquivo = null;
        int i = file.showOpenDialog(null);
        if (i == 1) {
            System.out.println("");

        } else {
            arquivo = file.getSelectedFile();
            System.out.println(arquivo.getPath());
            try {
                FileReader reader = new FileReader(arquivo.getPath());
                BufferedReader input = new BufferedReader(reader);
                String linha;
                boolean conhecimento = true;
                while ((linha = input.readLine()) != null) {
                    String ll = linha.replace(" ", "");
                    if (!ll.equals("")) {
                        if (ll.equals("###")) {

                            conhecimento = false;

                        } else if (conhecimento) {
                            temp.add(linha);
                        } else {
                            temp_consulta.add(linha);
                        }

                    }
                    System.out.println(linha);
                }

                input.close();
                tratamento.setBC(temp);
                tratamento.setConsulta(temp_consulta);
                base_inserido = true;

                if (tratamento.ErrosBC.size() > 0) {
                    erro = true;
                    for (String each : tratamento.ErrosBC) {

                        jTextArea1.setText(jTextArea1.getText() + "\n" + each);

                    }
                    jTextArea1.setText(jTextArea1.getText() + "\n");

                } else {

                    jTextArea1.setText(jTextArea1.getText() + "\nBase de conhecimento carregada com sucesso");

                    if (tratamento.ErrosConsulta.size() > 0) {
                        //JOptionPane.showMessageDialog(null, "Aqui");
                        for (String x : tratamento.ErrosConsulta) {
                            jTextArea1.setText(jTextArea1.getText() + "\n" + x);
                        }
                    } else {
                        tratamento.unificar(2);
                        //JOptionPane.showMessageDialog(null, "Aqui1");
                        for (String cons : tratamento.consulta) {
                            jTextArea1.setText(jTextArea1.getText() + "\n-? " + cons);
                            for (String x : tratamento.resposta) {
                                jTextArea1.setText(jTextArea1.getText() + "\n" + x);
                            }
                        }
                    }

                    erro = false;
                }

            } catch (IOException ioe) {
                //System.out.println(ioe);
            }
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
        //acao de quando ter um evento no text field
        //coloca os dados no jTextArea1

        String msg = "";
        String parte[];
        ArrayList<String> temp1 = new ArrayList<String>();
        ArrayList<String> temp2 = new ArrayList<String>();
        String temp3 = "";

        msg = jTextField1.getText();
        jTextArea1.setText(jTextArea1.getText() + "\n?- " + msg);
        if (msg.matches("\\/.*")) {
            if (msg.equals("/help")) {
                jTextArea1.setText(jTextArea1.getText() + "\n" + "Digite /BC para vizualizar a base de conhecimento.");
                jTextArea1.setText(jTextArea1.getText() + "\n" + "Digite /Erros para vizualizar a lista de Erros.");
            } else if (msg.equals("/BC")) {
                if (base_inserido) {
                    BCTela tela = new BCTela();
                    telaBC = tratamento.BC;
                    tela.setVisible(true);
                    tela.carregar(telaBC);

                } else {
                    jTextArea1.setText(jTextArea1.getText() + "\n" + "Base de Conhecimento não inserida.");
                }
            } else if (msg.equals("/Erros")) {
                ErrosTela tela = new ErrosTela();

                tela.setVisible(true);
            } else {
                jTextArea1.setText(jTextArea1.getText() + "\n" + "Opção não encontrada, digite /help para ajuda.");
            }
        } else {
            if (msg.matches(".+\\.\\s*\\&\\s*.+\\.")) {

                parte = msg.split("&");
                temp1.add(parte[0]);
                temp3 = parte[1];

                tratamento.setConsulta(temp1);
                tratamento.setTemporario(temp3);

                if (!(tratamento.ErroTemp.size() > 0 && tratamento.ErrosConsulta.size() > 0)) {
                    tratamento.unificar(1);

                    temp2 = tratamento.getResposta();

                    for (String each : temp2) {

                        jTextArea1.setText(jTextArea1.getText() + "\n" + each);

                    }
                    tratamento.resposta.clear();

                } else {

                    temp2 = tratamento.getErrosConsulta();

                    for (String each : temp2) {

                        jTextArea1.setText(jTextArea1.getText() + "\n" + each);

                    }

                }

            } else {

                if (base_inserido) {

                    temp1.add(msg);
                    tratamento.setConsulta(temp1);

                    if (!(tratamento.ErrosConsulta.size() > 0 && tratamento.ErrosConsulta.size() > 0)) {
                        tratamento.unificar(2);

                        temp2 = tratamento.getResposta();

                        for (String each : temp2) {

                            jTextArea1.setText(jTextArea1.getText() + "\n" + each);

                        }
                        tratamento.resposta.clear();

                    } else {
                        temp2 = tratamento.getErrosConsulta();

                        for (String each : temp2) {

                            jTextArea1.setText(jTextArea1.getText() + "\n" + each);

                        }
                    }

                } else {
                    jTextArea1.setText(jTextArea1.getText() + "\n" + "Base de Conhecimento nao inserida.");
                }

            }
        }

        //jTextArea1.setForeground(Color.black);
        jTextField1.setText(null);
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jMenuItem4ActionPerformed

    boolean junto(String var) {
        return false;
    }

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
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tela().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
