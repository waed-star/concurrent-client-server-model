
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Waed
 */
public class UserClientOne extends javax.swing.JFrame {

    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 9090;
    private static final String clientId = "U001";
    
    private static ArrayList<String> currentConnectedStations;
    
    private static ArrayList<String> incomingStations;
    
    private static ArrayList<String> ReceivedStations = new ArrayList<>(Arrays.asList("",""));
    
    private static String comboBoxSelectedItem;
    
    private Thread t1;
    private Thread t2 = new Thread();
    private Thread t3 = new Thread();
    private static Thread connectThread = new Thread();
    
    private String fileName = "WeatherStationDetails(" + clientId + ").txt";
    private int numberOfDownloads = 0;
    
    private static Socket socket;
    
    /**
     * Creates new form UserClientOne
     */
    public UserClientOne() throws InterruptedException, IOException{
        initComponents();
                            
        socket = new Socket(SERVER_IP,SERVER_PORT);

        ServerConnection serverConn = new ServerConnection(socket);
        
        //Goes out to the server
        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        new Thread(serverConn).start();
        
         out.println(clientId);
        
         //Main Thread
        Thread main = new Thread(){
            public void run(){
                while(true) {

                    try{
                        Thread.sleep(1000);
                        if (ReceivedStations.get(0).isEmpty()) {
                            ReceivedStations.remove(0);
                        }
                    } catch(InterruptedException e ) {
                        e.getMessage();
                    }
                    System.out.println(ServerConnection.getReceivedStations());

                    ReceivedStations = ServerConnection.getReceivedStations();

                }
                
            }
        };
        
        main.start();
        
        //This thread keeps on running and is not stopped during the program
        Thread t = new Thread(){
            public void run(){

               ArrayList<String> testing = ReceivedStations;
               ArrayList<String> weatherstationId = new ArrayList<String>();

               //Only one station is connected
               if (ReceivedStations.size() == 11) {
                   dropDownMenuForId.removeAllItems();
                   dropDownMenuForId.addItem(testing.get(0));
               } else {
                    //Weather station id is saved in every 11th field
                    for(int i = 0; i < ReceivedStations.size(); i = i + 11) {

                                dropDownMenuForId.removeAllItems();
                                dropDownMenuForId.addItem(testing.get(0));
                                dropDownMenuForId.addItem(testing.get(i));

                    }  
               }

               try {
                   Thread.sleep(1000);
               } catch (InterruptedException ex) {
                   ex.getMessage();
               }
            }
        };
        
        t.start();
        
        //The form automatically shows the first connected weather station
        t1 = new Thread(){
            public void run(){
                while(true) {
                    ArrayList<String> testing = ReceivedStations;
                    ArrayList<String> weatherstationId = new ArrayList<String>();

                //Only one station is connected
                if (ReceivedStations.size() == 11) {
                    dropDownMenuForId.removeAllItems();
                    dropDownMenuForId.addItem(testing.get(0));
                } else {
                     //Weather station id is saved in every 11th field
                     for(int i = 0; i < ReceivedStations.size(); i = i + 11) {

                                 dropDownMenuForId.removeAllItems();
                                 dropDownMenuForId.addItem(testing.get(0));
                                 dropDownMenuForId.addItem(testing.get(i));

                     }  
                }

                    latitudeField.setText(ReceivedStations.get(1));
                    longitudeField.setText(ReceivedStations.get(2));
                    tempertureField.setText(ReceivedStations.get(3) + "ºC");
                    pressureField.setText(ReceivedStations.get(4) + "kPa");
                    precipitationField.setText(ReceivedStations.get(5) + " inches");
                    rainfallField.setText(ReceivedStations.get(6) + " mm");
                    humidityField.setText(ReceivedStations.get(7) + "%");
                    windSpeedField.setText(ReceivedStations.get(8) + " mph");
                    windDirectionField.setText(ReceivedStations.get(9) + " degrees");
                    cloudHeightField.setText(ReceivedStations.get(10) + " m");
                    
                    try {
                        Thread.sleep(6000);
                    } catch (InterruptedException ex) {
                        ex.getMessage();
                    }
                }
                
            }
            
          };

        //It needs one second to get the data from the server to start displaying it
        t1.sleep(1000);
        t1.start();
    }
    
    private static synchronized void setComboBoxItem(String item) {
    
        comboBoxSelectedItem = item;
        
    }
    
    private static synchronized String getComboBoxItem() {
            
        return comboBoxSelectedItem;
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabelUsername = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        dropDownMenuForId = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        latitudeField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        longitudeField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tempertureField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cloudHeightField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        pressureField = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        precipitationField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        rainfallField = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        humidityField = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        windSpeedField = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        windDirectionField = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(750, 600));
        setMinimumSize(new java.awt.Dimension(750, 600));
        setPreferredSize(new java.awt.Dimension(750, 600));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(248, 148, 6));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(236, 240, 241));
        jLabel11.setText("User Client: U001");

        jLabelUsername.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelUsername.setForeground(new java.awt.Color(236, 240, 241));
        jLabelUsername.setText("Hello Client!");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(236, 240, 241));
        jLabel12.setText("Weather Station ID:");

        dropDownMenuForId.setBackground(new java.awt.Color(108, 122, 137));
        dropDownMenuForId.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0000" }));

        jButton1.setText("View station");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(64, 500, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dropDownMenuForId, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelUsername)
                        .addGap(93, 93, 93))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(dropDownMenuForId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jLabelUsername))
                .addGap(11, 11, 11))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(-10, 0, 790, 90);

        jPanel2.setBackground(new java.awt.Color(44, 62, 80));

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(236, 240, 241));
        jLabel4.setText("GPS Positioning: ");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(236, 240, 241));
        jLabel5.setText("Latitude: ");

        latitudeField.setEditable(false);
        latitudeField.setBackground(new java.awt.Color(108, 122, 137));
        latitudeField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        latitudeField.setForeground(new java.awt.Color(228, 241, 254));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(236, 240, 241));
        jLabel6.setText("Longitude: ");

        longitudeField.setEditable(false);
        longitudeField.setBackground(new java.awt.Color(108, 122, 137));
        longitudeField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        longitudeField.setForeground(new java.awt.Color(228, 241, 254));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(236, 240, 241));
        jLabel9.setText("Data about the field: ");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(236, 240, 241));
        jLabel7.setText("Air Temperture: ");

        tempertureField.setBackground(new java.awt.Color(108, 122, 137));
        tempertureField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tempertureField.setForeground(new java.awt.Color(228, 241, 254));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(236, 240, 241));
        jLabel15.setText("Cloud Height: ");

        cloudHeightField.setEditable(false);
        cloudHeightField.setBackground(new java.awt.Color(108, 122, 137));
        cloudHeightField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cloudHeightField.setForeground(new java.awt.Color(228, 241, 254));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(236, 240, 241));
        jLabel8.setText("Barometric  Pressure:");

        pressureField.setEditable(false);
        pressureField.setBackground(new java.awt.Color(108, 122, 137));
        pressureField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        pressureField.setForeground(new java.awt.Color(228, 241, 254));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(236, 240, 241));
        jLabel16.setText("Precipitation:");

        precipitationField.setEditable(false);
        precipitationField.setBackground(new java.awt.Color(108, 122, 137));
        precipitationField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        precipitationField.setForeground(new java.awt.Color(228, 241, 254));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(236, 240, 241));
        jLabel13.setText("Rainfall:");

        rainfallField.setEditable(false);
        rainfallField.setBackground(new java.awt.Color(108, 122, 137));
        rainfallField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rainfallField.setForeground(new java.awt.Color(228, 241, 254));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(236, 240, 241));
        jLabel14.setText("Humidity: ");

        humidityField.setEditable(false);
        humidityField.setBackground(new java.awt.Color(108, 122, 137));
        humidityField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        humidityField.setForeground(new java.awt.Color(228, 241, 254));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(236, 240, 241));
        jLabel17.setText("Wind Speed: ");

        windSpeedField.setEditable(false);
        windSpeedField.setBackground(new java.awt.Color(108, 122, 137));
        windSpeedField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        windSpeedField.setForeground(new java.awt.Color(228, 241, 254));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(236, 240, 241));
        jLabel18.setText("Direction: ");

        windDirectionField.setEditable(false);
        windDirectionField.setBackground(new java.awt.Color(108, 122, 137));
        windDirectionField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        windDirectionField.setForeground(new java.awt.Color(228, 241, 254));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(latitudeField, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(longitudeField, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(56, 56, 56)
                        .addComponent(tempertureField, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel13)
                            .addComponent(jLabel17))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(windSpeedField)
                            .addComponent(rainfallField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pressureField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(52, 52, 52)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel16))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(windDirectionField, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(precipitationField)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(humidityField)
                            .addComponent(cloudHeightField))))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(latitudeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(longitudeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(humidityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cloudHeightField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(precipitationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(windDirectionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tempertureField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pressureField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rainfallField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(windSpeedField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jButton2.setText("Download current station details");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addGap(37, 37, 37))
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 90, 770, 480);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        t1.stop();

        if(dropDownMenuForId.getSelectedItem().toString().equals(ReceivedStations.get(11))) {

            threadToDisplaySecondItem();
            getThirdThread().stop();
            getSecondThread().start();

        } else if (dropDownMenuForId.getSelectedItem().toString().equals(ReceivedStations.get(0))) {

            threadToDisplayFirstItem();
            getSecondThread().stop();
            getThirdThread().start();

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:

        incrementNoOfDownloads();
        downloadStationDetails();
    }//GEN-LAST:event_jButton2MouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        
        
        try{
            
            if (socket != null) {
                PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                out.println(clientId + " Has disconnected");
                socket.close();
                System.out.println("Socket has been successfully closed");
                System.exit(0);
                
            } 
           
        } catch (IOException e) { 
            e.getStackTrace();
        }
    }//GEN-LAST:event_formWindowClosing

       
    private void threadToDisplayFirstItem() {
        t3 = new Thread(){
                public void run(){
                    while (true) {
                        //dropDownMenuForId.setSelectedItem(temp.get(11));
                        latitudeField.setText(ReceivedStations.get(1));
                        longitudeField.setText(ReceivedStations.get(2));
                        tempertureField.setText(ReceivedStations.get(3) + "ºC");
                        pressureField.setText(ReceivedStations.get(4) + "kPa");
                        precipitationField.setText(ReceivedStations.get(5) + " inches");
                        rainfallField.setText(ReceivedStations.get(6) + " mm");
                        humidityField.setText(ReceivedStations.get(7) + "%");
                        windSpeedField.setText(ReceivedStations.get(8) + " mph");
                        windDirectionField.setText(ReceivedStations.get(9) + " degrees");
                        cloudHeightField.setText(ReceivedStations.get(10) + " m");

                        try {
                            Thread.sleep(6000);
                            } catch (InterruptedException ex) {
                            ex.getMessage();
                        }

                    }
                }
            };
    }
    
    private Thread getThirdThread() {
        return t3;
    }
    
    private void threadToDisplaySecondItem() {
        t2 = new Thread(){
                public void run(){
                     while (true) {
                        //dropDownMenuForId.setSelectedItem(temp.get(11));
                        latitudeField.setText(ReceivedStations.get(12));
                        longitudeField.setText(ReceivedStations.get(13));
                        tempertureField.setText(ReceivedStations.get(14) + "ºC");
                        pressureField.setText(ReceivedStations.get(15) + "kPa");
                        precipitationField.setText(ReceivedStations.get(16) + " inches");
                        rainfallField.setText(ReceivedStations.get(17) + " mm");
                        humidityField.setText(ReceivedStations.get(18) + "%");
                        windSpeedField.setText(ReceivedStations.get(19) + " mph");
                        windDirectionField.setText(ReceivedStations.get(20) + " degrees");
                        cloudHeightField.setText(ReceivedStations.get(21) + " m");

                        try {
                            Thread.sleep(6000);
                            } catch (InterruptedException ex) {
                            ex.getMessage();
                        }

                    }
                }
            };
    }
    
    private Thread getSecondThread() {
        return t2;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException,  InterruptedException{
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
            java.util.logging.Logger.getLogger(UserClientOne.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserClientOne.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserClientOne.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserClientOne.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new UserClientOne().setVisible(true);
                } catch (InterruptedException ex) {
                    ex.getMessage();
                } catch (IOException ex) {
                   ex.getMessage();
                }
            }
        });         
    }
     
    private String getFileName() {
        return fileName;
    }
    
    private void setFileName(String _fileName) {
    
        fileName = _fileName;
        
    }
    
    private int getNoOfDownloads() {
        return numberOfDownloads;
    }
    
    private void incrementNoOfDownloads() {
    
        numberOfDownloads++;
        
    }
    
    
    private void downloadStationDetails() {
    
        try {
        
            File file = new File(getFileName());
            
            if (!file.exists()) {
                
                file.createNewFile();
                
                System.out.println("not Exists");
                
            } else if (file.exists()) {
                
                System.out.println("Exists");

                String newFileName = "WeatherStationDetails(" + clientId + ") (" + getNoOfDownloads() + ") .txt";
                
                setFileName(newFileName);
                
                file = new File(getFileName());
                
                file.createNewFile();

            }
            
            PrintWriter pw = new PrintWriter(file);
            
            if (dropDownMenuForId.getSelectedItem().toString().equals(ReceivedStations.get(0))) {
                
                pw.println("Weather station ID: " + ReceivedStations.get(0));
                pw.println("Latitude: " + ReceivedStations.get(1));
                pw.println("Longitude: " + ReceivedStations.get(2));
                pw.println("");
                pw.println("Current readings: ");
                pw.println("Temperture: " + ReceivedStations.get(3) + "ºC");
                pw.println("Pressure: " + ReceivedStations.get(4) + "kPa");
                pw.println("Precipition: " + ReceivedStations.get(5) + " inches");
                pw.println("Rainfall: " + ReceivedStations.get(6) + " mm");
                pw.println("Humidity: " + ReceivedStations.get(7) + "%");
                pw.println("Wind Speed: " + ReceivedStations.get(8) + " mph");
                pw.println("Wind Direction: " + ReceivedStations.get(9) + " degrees");
                pw.println("Cloud Height: " + ReceivedStations.get(10) + " m");
                pw.println("------- End of Weather station details ------");
                
                pw.close();
                
            } else if (dropDownMenuForId.getSelectedItem().toString().equals(ReceivedStations.get(11))) {
            
                pw.println("Weather station ID: " + ReceivedStations.get(11));
                pw.println("Latitude: " + ReceivedStations.get(12));
                pw.println("Longitude: " + ReceivedStations.get(13));
                pw.println("");
                pw.println("Current readings: ");
                pw.println("Temperture: " + ReceivedStations.get(14) + "ºC");
                pw.println("Pressure: " + ReceivedStations.get(15) + "kPa");
                pw.println("Precipition: " + ReceivedStations.get(16) + " inches");
                pw.println("Rainfall: " + ReceivedStations.get(17) + " mm");
                pw.println("Humidity: " + ReceivedStations.get(18) + "%");
                pw.println("Wind Speed: " + ReceivedStations.get(19) + " mph");
                pw.println("Wind Direction: " + ReceivedStations.get(20) + " degrees");
                pw.println("Cloud Height: " + ReceivedStations.get(21) + " m");
                pw.println("------- End of Weather station details ------");
                
                pw.close();
                
            }
            
            
           

        } catch (IOException e) {
        
            e.getMessage();
            
        } finally {
        
            JOptionPane.showMessageDialog(null,"Data has been successfully download!");
            
        }
        
    }
    
    
    public static synchronized void setCurrentConnectedStations(ArrayList<String> _currentConnectedStations) {
    
        currentConnectedStations = _currentConnectedStations;
        
    }
    
    public static synchronized ArrayList<String> getCurrentConnectedStations() {
    
        return currentConnectedStations;
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cloudHeightField;
    private javax.swing.JComboBox<String> dropDownMenuForId;
    private javax.swing.JTextField humidityField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelUsername;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField latitudeField;
    private javax.swing.JTextField longitudeField;
    private javax.swing.JTextField precipitationField;
    private javax.swing.JTextField pressureField;
    private javax.swing.JTextField rainfallField;
    private javax.swing.JTextField tempertureField;
    private javax.swing.JTextField windDirectionField;
    private javax.swing.JTextField windSpeedField;
    // End of variables declaration//GEN-END:variables
}
