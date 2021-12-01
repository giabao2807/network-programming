package tcpchatapp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author giabao
 */
public class ChatClient extends javax.swing.JFrame {
    
    private static final long serialVersionUID = 1L;
    private Socket socket;
    private DataOutputStream socketOutputStream;
    private DataInputStream socketInputStream;
    private boolean bConnected = false;
    private Accept accept;
    
    @SuppressWarnings("unused")
    private Thread acceptThread;
    
    boolean isConnected = false;
    static String username = "";
    static boolean readyToSend = false;
    private boolean connected;
    
    private void sendMessage() {
        String tempStr;
        tempStr = jTextField.getText();
        
        try {
            socketOutputStream.writeUTF(">> " + username + ": " + tempStr);
            socketOutputStream.flush();
        } catch (IOException e1) {
            System.out.println("Can't output!!");
        }
        
        jTextField.setText("");
    }
    
    void connectionFailed() {
        jButtonLogin.setEnabled(true);
        jButtonLogOut.setEnabled(false);
        jButtonSend.setEnabled(false);
        connected = false;
    }

    /**
     * Creates new form ChatClient
     */
    public ChatClient() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jButtonSend = new javax.swing.JButton();
        jLabel = new javax.swing.JLabel();
        jTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea = new javax.swing.JTextArea();
        jButtonLogin = new javax.swing.JButton();
        jButtonLogOut = new javax.swing.JButton();
        jTextFieldServer = new javax.swing.JTextField();
        jLabelServer = new javax.swing.JLabel();
        jLabelPort = new javax.swing.JLabel();
        jTextFieldPort = new javax.swing.JTextField();
        jTextFieldUsername = new javax.swing.JTextField();
        jLabelUsername = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonSend.setText("Send");
        jButtonSend.setEnabled(false);
        jButtonSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSendActionPerformed(evt);
            }
        });

        jLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel.setText("Bài 3: Chat TCP");

        jTextField.setEditable(false);

        jTextArea.setEditable(false);
        jTextArea.setColumns(20);
        jTextArea.setRows(5);
        jScrollPane1.setViewportView(jTextArea);

        jButtonLogin.setText("Login");
        jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginActionPerformed(evt);
            }
        });

        jButtonLogOut.setText("Log out");
        jButtonLogOut.setEnabled(false);
        jButtonLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLogOutActionPerformed(evt);
            }
        });

        jTextFieldServer.setEditable(false);
        jTextFieldServer.setText("localhost");

        jLabelServer.setText("Server address:");

        jLabelPort.setText("Port number:");

        jTextFieldPort.setEditable(true);
        jTextFieldPort.setText("2807");

        jLabelUsername.setText("Username:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSend)
                        .addGap(12, 12, 12))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelServer, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(jLabelUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextFieldServer, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelPort, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jTextFieldUsername)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonLogin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonLogOut)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSend)
                    .addComponent(jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelServer)
                    .addComponent(jLabelPort)
                    .addComponent(jTextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonLogOut)
                    .addComponent(jButtonLogin)
                    .addComponent(jTextFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelUsername))
                .addContainerGap())
        );

        pack();
    }

    private void jButtonSendActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("sending ...");
        sendMessage();
        return;
    }
    
    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        username = jTextFieldUsername.getText().trim();
        
        if (username.length() == 0) return;
        
        String server = jTextFieldServer.getText().trim();
        if (server.length() == 0) return;
        
        String portNumber = jTextFieldPort.getText().trim();
        if (portNumber.length() == 0) return;
        
        int port = 0;
        
        port = Integer.parseInt(portNumber);
        
        if (!connected) {
            try {
                socket = new Socket(server, port);
                socketOutputStream = new DataOutputStream(socket.getOutputStream());
                socketInputStream = new DataInputStream(socket.getInputStream());
                isConnected = true;
                bConnected = true;
                jTextField.setEditable(true);
                jButtonSend.setEnabled(true);
                jButtonLogOut.setEnabled(true);
                jButtonLogin.setEnabled(false);
                jTextFieldUsername.setEditable(false);
                accept = new Accept(socket);
                Thread acceptThread = new Thread(accept);
                acceptThread.start();
            } catch (UnknownHostException e1) {
                System.out.println("Miss server!");
            } catch (IOException e2) {
                System.out.println("Can't output");
            }
        }
        
    }

    private void jButtonLogOutActionPerformed(java.awt.event.ActionEvent evt) {
        if (isConnected) {
            bConnected = false;
            try {
                socketInputStream.close();
                socketOutputStream.close();
                jTextField.setEditable(false);
                jButtonSend.setEnabled(false);
                jButtonLogOut.setEnabled(false);
                jButtonLogin.setEnabled(true);
                jTextFieldUsername.setEditable(true);
                socket.close();
            } catch (IOException ex) {
                System.out.println("Doesn't connected!");
            }
            
            connectionFailed();
            return;
        }
    }
    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChatClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatClient().setVisible(true);
            }
        });
    }
    
    class Accept implements Runnable {
        Socket s;
        
        Accept(Socket s){
            this.s = s;
        }
        
        @Override
        public void run() {
            while(bConnected) {
                try {
                    String str = socketInputStream.readUTF();
                    jTextArea.append(str + "\n");
                } catch (SocketException e3){
                    System.out.println("Disconnected!!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLogOut;
    private javax.swing.JButton jButtonLogin;
    private javax.swing.JButton jButtonSend;
    private javax.swing.JLabel jLabel;
    private javax.swing.JLabel jLabelPort;
    private javax.swing.JLabel jLabelServer;
    private javax.swing.JLabel jLabelUsername;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea;
    private javax.swing.JTextField jTextField;
    private javax.swing.JTextField jTextFieldPort;
    private javax.swing.JTextField jTextFieldServer;
    private javax.swing.JTextField jTextFieldUsername;
    // End of variables declaration//GEN-END:variables
}