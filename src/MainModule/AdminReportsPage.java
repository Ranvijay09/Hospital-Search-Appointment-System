/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainModule;

import java.awt.*;
import java.awt.FontMetrics;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author HP
 */
public class AdminReportsPage extends javax.swing.JFrame {

    private TableRowSorter sorter;

    public void loadCountries() {
        String col[] = {"Country ID", "Name of Country"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(col);
        jTable1.setModel(model);
        jTable1.setRowHeight(50);
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        sorter = new TableRowSorter<>(model);
        jTable1.setRowSorter(sorter);
        int country_id;
        String country_name = "";

        try {
            MainModule.DB_Creds db = new MainModule.DB_Creds();
            Connection connection = db.ConnectToDB();
            Statement stmt;
            stmt = connection.createStatement();

            PreparedStatement pst = connection.prepareStatement("SELECT * FROM country");

            ResultSet rs = pst.executeQuery();

            int i = 0;

            while (rs.next()) {

                country_id = rs.getInt("country_id");
                country_name = rs.getString("country_name");

                model.addRow(new Object[]{country_id, country_name});

                i++;

            }

            if (i < 1) {

                JOptionPane.showMessageDialog(null, "No Country Found", "Error", JOptionPane.ERROR_MESSAGE);

            }

            if (i == 1) {

                System.out.println(i + " Country Found");

            } else {

                System.out.println(i + " Countries Found");

            }
            connection.close();
            rs.close();
            pst.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadStates() {
        String col[] = {"State ID", "Name of Country", "Name of State"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(col);
        jTable1.setModel(model);
        jTable1.setRowHeight(50);
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        sorter = new TableRowSorter<>(model);
        jTable1.setRowSorter(sorter);
        int state_id;
        String country_name = "";
        String state_name = "";

        try {
            MainModule.DB_Creds db = new MainModule.DB_Creds();
            Connection connection = db.ConnectToDB();
            Statement stmt;
            stmt = connection.createStatement();

            PreparedStatement pst = connection.prepareStatement("SELECT states.*,country.country_name FROM states join country on states.country_id=country.country_id");

            ResultSet rs = pst.executeQuery();

            int i = 0;

            while (rs.next()) {

                state_id = rs.getInt("state_id");
                country_name = rs.getString("country_name");
                state_name = rs.getString("state_name");

                model.addRow(new Object[]{state_id, country_name, state_name});

                i++;

            }

            if (i < 1) {

                JOptionPane.showMessageDialog(null, "No State Found", "Error", JOptionPane.ERROR_MESSAGE);

            }

            if (i == 1) {

                System.out.println(i + " State Found");

            } else {

                System.out.println(i + " States Found");

            }
            connection.close();
            rs.close();
            pst.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadDistricts() {
        String col[] = {"District ID", "Name of Country", "Name of State", "Name of District"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(col);
        jTable1.setModel(model);
        jTable1.setRowHeight(50);
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        sorter = new TableRowSorter<>(model);
        jTable1.setRowSorter(sorter);
        int dist_id;
        String country_name = "";
        String state_name = "";
        String dist_name = "";

        try {
            MainModule.DB_Creds db = new MainModule.DB_Creds();
            Connection connection = db.ConnectToDB();
            Statement stmt;
            stmt = connection.createStatement();

            PreparedStatement pst = connection.prepareStatement("SELECT district.*,country.country_name,states.state_name FROM district join country on district.country_id=country.country_id join states on district.state_id=states.state_id");

            ResultSet rs = pst.executeQuery();

            int i = 0;

            while (rs.next()) {

                dist_id = rs.getInt("dist_id");
                country_name = rs.getString("country_name");
                state_name = rs.getString("state_name");
                dist_name = rs.getString("dist_name");

                model.addRow(new Object[]{dist_id, country_name, state_name, dist_name});

                i++;

            }

            if (i < 1) {

                JOptionPane.showMessageDialog(null, "No District Found", "Error", JOptionPane.ERROR_MESSAGE);

            }

            if (i == 1) {

                System.out.println(i + " District Found");

            } else {

                System.out.println(i + " Districts Found");

            }
            connection.close();
            rs.close();
            pst.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadTalukas() {
        String col[] = {"Taluka ID", "Name of Country", "Name of State", "Name of District", "Name of Taluka"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(col);
        jTable1.setModel(model);
        jTable1.setRowHeight(50);
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

        sorter = new TableRowSorter<>(model);
        jTable1.setRowSorter(sorter);
        int tal_id;
        String country_name = "";
        String state_name = "";
        String dist_name = "";
        String tal_name = "";

        try {
            MainModule.DB_Creds db = new MainModule.DB_Creds();
            Connection connection = db.ConnectToDB();
            Statement stmt;
            stmt = connection.createStatement();

            PreparedStatement pst = connection.prepareStatement("SELECT taluka.*,district.dist_name,country.country_name,states.state_name FROM taluka join country on taluka.country_id=country.country_id join states on taluka.state_id=states.state_id join district on taluka.dist_id=district.dist_id");

            ResultSet rs = pst.executeQuery();

            int i = 0;

            while (rs.next()) {

                tal_id = rs.getInt("tal_id");
                country_name = rs.getString("country_name");
                state_name = rs.getString("state_name");
                dist_name = rs.getString("dist_name");
                tal_name = rs.getString("tal_name");
                model.addRow(new Object[]{tal_id, country_name, state_name, dist_name, tal_name});

                i++;

            }

            if (i < 1) {

                JOptionPane.showMessageDialog(null, "No Taluka Found", "Error", JOptionPane.ERROR_MESSAGE);

            }

            if (i == 1) {

                System.out.println(i + " Taluka Found");

            } else {

                System.out.println(i + " Talukas Found");

            }
            connection.close();
            rs.close();
            pst.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadSpecs() {
        String col[] = {"Specialization ID", "Name of Specialization"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(col);
        jTable1.setModel(model);
        jTable1.setRowHeight(50);
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        sorter = new TableRowSorter<>(model);
        jTable1.setRowSorter(sorter);
        int hosp_spec_id;
        String hosp_spec_name = "";

        try {
            MainModule.DB_Creds db = new MainModule.DB_Creds();
            Connection connection = db.ConnectToDB();
            Statement stmt;
            stmt = connection.createStatement();

            PreparedStatement pst = connection.prepareStatement("SELECT * FROM hosp_spec");

            ResultSet rs = pst.executeQuery();

            int i = 0;

            while (rs.next()) {

                hosp_spec_id = rs.getInt("hosp_spec_id");
                hosp_spec_name = rs.getString("hosp_spec_name");

                model.addRow(new Object[]{hosp_spec_id, hosp_spec_name});

                i++;

            }

            if (i < 1) {

                JOptionPane.showMessageDialog(null, "No Specialization Found", "Error", JOptionPane.ERROR_MESSAGE);

            }

            if (i == 1) {

                System.out.println(i + " Specialization Found");

            } else {

                System.out.println(i + " Specializations Found");

            }
            connection.close();
            rs.close();
            pst.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadHospitals() {
        String col[] = {"Hospital ID", "Name of Hospital", "Address of Hospital", "Hospital Speciality", "Hospital Contact No.", "Website of Hospital"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(col);
        jTable1.setModel(model);
        jTable1.setRowHeight(200);
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel colModel = jTable1.getColumnModel();
        colModel.getColumn(0).setPreferredWidth(50);
        colModel.getColumn(1).setPreferredWidth(300);
        colModel.getColumn(2).setPreferredWidth(300);
        colModel.getColumn(3).setPreferredWidth(500);
        colModel.getColumn(4).setPreferredWidth(300);
        colModel.getColumn(5).setPreferredWidth(150);
        sorter = new TableRowSorter<>(model);
        jTable1.setRowSorter(sorter);
        int hosp_id;
        String hosp_name = "";
        String hosp_address = "";
        String hosp_contact = "";
        String hosp_speciality = "";
        String hosp_web = "";

        try {
            MainModule.DB_Creds db = new MainModule.DB_Creds();
            Connection connection = db.ConnectToDB();
            Statement stmt;
            stmt = connection.createStatement();

            PreparedStatement pst = connection.prepareStatement("SELECT hosp_details.*,hosp_spec.hosp_spec_name,taluka.tal_name,district.dist_name,country.country_name,states.state_name FROM hosp_details join country on hosp_details.hosp_country=country.country_id join states on hosp_details.hosp_state=states.state_id join district on hosp_details.hosp_dist=district.dist_id join taluka on hosp_details.hosp_tal=taluka.tal_id join hosp_spec on hosp_details.hosp_speciality=hosp_spec.hosp_spec_id");

            ResultSet rs = pst.executeQuery();

            int i = 0;

            while (rs.next()) {

                hosp_id = rs.getInt("hosp_id");
                hosp_name = rs.getString("hosp_name");
                hosp_address = "<html>" + rs.getString("hosp_address") + ", " + rs.getString("tal_name") + ", " + rs.getString("dist_name") + ", " + rs.getString("state_name") + ", " + rs.getString("country_name") + "</html>";
                hosp_contact = rs.getString("hosp_contact");
                hosp_speciality = rs.getString("hosp_spec_name");
                hosp_web = rs.getString("hosp_web");
                model.addRow(new Object[]{hosp_id, hosp_name, hosp_address, hosp_speciality, hosp_contact, hosp_web});

                i++;

            }

            if (i < 1) {

                JOptionPane.showMessageDialog(null, "No Hospital Found", "Error", JOptionPane.ERROR_MESSAGE);

            }

            if (i == 1) {

                System.out.println(i + " Hospital Found");

            } else {

                System.out.println(i + " Hospitals Found");

            }
            connection.close();
            rs.close();
            pst.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadHRs() {

        String col[] = {"HR ID", "Name of Hospital", "HR Name", "Address of HR", "Email ID", "Contact No.", "Specialization"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(col);
        jTable1.setModel(model);
        jTable1.setRowHeight(200);
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel colModel = jTable1.getColumnModel();
        colModel.getColumn(0).setPreferredWidth(50);
        colModel.getColumn(1).setPreferredWidth(300);
        colModel.getColumn(2).setPreferredWidth(300);
        colModel.getColumn(3).setPreferredWidth(500);
        colModel.getColumn(4).setPreferredWidth(300);
        colModel.getColumn(5).setPreferredWidth(150);
        colModel.getColumn(6).setPreferredWidth(200);
        sorter = new TableRowSorter<>(model);
        jTable1.setRowSorter(sorter);
        int hr_id;
        String hosp_name = "";
        String hr_name = "";
        String hr_address = "";
        String hr_contact = "";
        String hr_email = "";
        String hr_spec = "";
        try {
            MainModule.DB_Creds db = new MainModule.DB_Creds();
            Connection connection = db.ConnectToDB();
            Statement stmt;
            stmt = connection.createStatement();

            PreparedStatement pst = connection.prepareStatement("SELECT hr_details.*,hosp_details.hosp_name,hosp_spec.hosp_spec_name,taluka.tal_name,district.dist_name,country.country_name,states.state_name FROM hr_details join hosp_details on hr_details.hr_hosp=hosp_details.hosp_id join country on hr_details.hr_country=country.country_id join states on hr_details.hr_state=states.state_id join district on hr_details.hr_dist=district.dist_id join taluka on hr_details.hr_tal=taluka.tal_id join hosp_spec on hr_details.hr_speciality=hosp_spec.hosp_spec_id");

            ResultSet rs = pst.executeQuery();

            int i = 0;

            while (rs.next()) {

                hr_id = rs.getInt("hr_id");
                hosp_name = rs.getString("hosp_name");
                hr_name = "<html>" + rs.getString("hr_lname") + " " + rs.getString("hr_fname") + " " + rs.getString("hr_mname") + "</html>";
                hr_address = "<html>" + rs.getString("hr_address") + ", " + rs.getString("tal_name") + ", " + rs.getString("dist_name") + ", " + rs.getString("state_name") + ", " + rs.getString("country_name") + "</html>";
                hr_email = rs.getString("hr_email");
                hr_contact = rs.getString("hr_contact");
                hr_spec = rs.getString("hosp_spec_name");

                model.addRow(new Object[]{hr_id, hosp_name, hr_name, hr_address, hr_email, hr_contact, hr_spec});

                i++;

            }

            if (i < 1) {

                JOptionPane.showMessageDialog(null, "No HR Found", "Error", JOptionPane.ERROR_MESSAGE);

            }

            if (i == 1) {

                System.out.println(i + " HR Found");

            } else {

                System.out.println(i + " HR's Found");

            }
            connection.close();
            rs.close();
            pst.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadTables() {
        jTable1.setModel(new DefaultTableModel());
        String choice = jComboBox7.getSelectedItem().toString();
        if (choice.equals("Countries")) {
            loadCountries();
        }
        if (choice.equals("States")) {
            loadStates();
        }
        if (choice.equals("Districts")) {
            loadDistricts();
        }
        if (choice.equals("Talukas")) {
            loadTalukas();
        }
        if (choice.equals("Specializations")) {
            loadSpecs();
        }
        if (choice.equals("Hospitals")) {
            loadHospitals();
        }
        if (choice.equals("HR's")) {
            loadHRs();
        }
    }

    public AdminReportsPage() {
        initComponents();
        loadTables();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kGradientPanel1 = new keeptoo.KGradientPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        jTextField8 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        kGradientPanel1.setkEndColor(new java.awt.Color(53, 32, 88));
        kGradientPanel1.setkGradientFocus(0);
        kGradientPanel1.setkStartColor(new java.awt.Color(53, 32, 88));
        kGradientPanel1.setName(""); // NOI18N

        jPanel2.setBackground(new java.awt.Color(0, 0, 51));
        jPanel2.setForeground(new java.awt.Color(202, 202, 253));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MainModule/Icons/caduceus_50px.png"))); // NOI18N
        jButton3.setContentAreaFilled(false);
        jButton3.setFocusable(false);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MainModule/Icons/home_50px.png"))); // NOI18N
        jButton7.setContentAreaFilled(false);
        jButton7.setFocusable(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Semilight", 3, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(202, 202, 253));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Admin Reports");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton7)
                .addGap(201, 201, 201)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 205, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel13.setFont(new java.awt.Font("Segoe UI Symbol", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Select Report");

        jComboBox7.setFont(new java.awt.Font("Segoe UI Symbol", 0, 18)); // NOI18N
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Countries", "States", "Districts", "Talukas", "Specializations", "Hospitals", "HR's" }));
        jComboBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox7ActionPerformed(evt);
            }
        });

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "null", "null", "null", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setOpaque(false);
        jTable1.setRowHeight(50);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(7).setResizable(false);
        }

        jButton9.setBackground(new java.awt.Color(77, 58, 111));
        jButton9.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MainModule/Icons/print_25px.png"))); // NOI18N
        jButton9.setText("Print");
        jButton9.setContentAreaFilled(false);
        jButton9.setFocusable(false);
        jButton9.setOpaque(true);
        jButton9.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jTextField8.setBackground(new java.awt.Color(53, 32, 88));
        jTextField8.setFont(new java.awt.Font("Segoe UI Symbol", 0, 18)); // NOI18N
        jTextField8.setForeground(new java.awt.Color(255, 255, 255));
        jTextField8.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        jTextField8.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField8.setInheritsPopupMenu(true);
        jTextField8.setOpaque(false);
        jTextField8.setSelectionColor(new java.awt.Color(255, 255, 255));
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(53, 32, 88));
        jButton10.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MainModule/Icons/search_filled_25px.png"))); // NOI18N
        jButton10.setContentAreaFilled(false);
        jButton10.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, kGradientPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(534, 534, 534)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton10))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1)
                .addGap(18, 18, 18)
                .addComponent(jButton9)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        MainModule.AdminMainPage m = new MainModule.AdminMainPage();
        m.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        try {
            boolean print = jTable1.print();
            if (!print) {
                JOptionPane.showMessageDialog(null, "Unable To Print !!..");
            }
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jComboBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox7ActionPerformed
        // TODO add your handling code here:
        loadTables();
    }//GEN-LAST:event_jComboBox7ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed

    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        String str = jTextField8.getText();
        if (str.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + str));
        }
    }//GEN-LAST:event_jButton10ActionPerformed

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
            java.util.logging.Logger.getLogger(AdminReportsPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminReportsPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminReportsPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminReportsPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminReportsPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField8;
    private keeptoo.KGradientPanel kGradientPanel1;
    // End of variables declaration//GEN-END:variables
}
