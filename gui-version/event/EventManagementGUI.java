package event;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class EventManagementGUI extends JFrame {
    private EventManager eventManager;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    // Components
    private JTable eventTable;
    private DefaultTableModel tableModel;
    private JTextField eventNameField, startDateField, endDateField, searchNameField, searchDateField;
    private JTextArea outputArea;
    private JTabbedPane tabbedPane;
    
    public EventManagementGUI() {
        // Initialize EventManager and load events
        eventManager = new EventManager();
        eventManager.load();
        
        // Frame setup
        setTitle("Event Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);//CENTER OF THE FRAME
        setResizable(true);
        
        // Create UI
        createUI();
        
        setVisible(true);
    }
    
    private void createUI() {
        // Create tabbed pane
        tabbedPane = new JTabbedPane();
        
        // Add tabs
        tabbedPane.addTab("Dashboard", createDashboardIcon(), createDashboardPanel());
        tabbedPane.addTab("Add Event", createAddEventIcon(), createAddEventPanel());
        tabbedPane.addTab("Search Events", createSearchIcon(), createSearchPanel());
        tabbedPane.addTab("Manage Events", createManageIcon(), createManagePanel());
        
        add(tabbedPane);
    }
    
    // ============ ICON CREATION METHODS ============
    private Icon createDashboardIcon() {
        return new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(70, 130, 180));
                
                // Draw grid for dashboard
                int rectSize = 4;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        g2d.fillRect(x + i * 5, y + j * 5, rectSize, rectSize);
                    }
                }
            }
            
            @Override
            public int getIconWidth() {
                return 16;
            }
            
            @Override
            public int getIconHeight() {
                return 16;
            }
        };
    }
    
    private Icon createAddEventIcon() {
        return new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(34, 139, 34));
                
                // Draw plus sign
                int size = 14;
                int centerX = x + 8;
                int centerY = y + 8;
                
                // Vertical line
                g2d.setStroke(new BasicStroke(2));
                g2d.drawLine(centerX, centerY - size/2, centerX, centerY + size/2);
                // Horizontal line
                g2d.drawLine(centerX - size/2, centerY, centerX + size/2, centerY);
            }
            
            @Override
            public int getIconWidth() {
                return 16;
            }
            
            @Override
            public int getIconHeight() {
                return 16;
            }
        };
    }
    
    private Icon createSearchIcon() {
        return new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(255, 140, 0));
                
                // Draw magnifying glass
                g2d.setStroke(new BasicStroke(1.5f));
                g2d.drawOval(x + 2, y + 2, 8, 8);
                g2d.drawLine(x + 10, y + 10, x + 14, y + 14);
            }
            
            @Override
            public int getIconWidth() {
                return 16;
            }
            
            @Override
            public int getIconHeight() {
                return 16;
            }
        };
    }
    
    private Icon createManageIcon() {
        return new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(220, 20, 60));
                
                // Draw sliders/settings
                g2d.drawLine(x + 2, y + 4, x + 10, y + 4);
                g2d.fillOval(x + 10, y + 2, 4, 4);
                
                g2d.drawLine(x + 2, y + 8, x + 8, y + 8);
                g2d.fillOval(x + 8, y + 6, 4, 4);
                
                g2d.drawLine(x + 2, y + 12, x + 12, y + 12);
                g2d.fillOval(x + 12, y + 10, 4, 4);
            }
            
            @Override
            public int getIconWidth() {
                return 16;
            }
            
            @Override
            public int getIconHeight() {
                return 16;
            }
        };
    }
    
    private Icon createLoadIcon() {
        return new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(0, 102, 204));
                
                // Draw folder/load icon
                g2d.fillRect(x + 1, y + 4, 14, 10);
                g2d.setColor(new Color(255, 255, 255));
                g2d.drawLine(x + 8, y + 7, x + 8, y + 11);
                g2d.drawLine(x + 6, y + 9, x + 10, y + 9);
            }
            
            @Override
            public int getIconWidth() {
                return 16;
            }
            
            @Override
            public int getIconHeight() {
                return 16;
            }
        };
    }
    
    private Icon createRefreshIcon() {
        return new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(70, 130, 180));
                g2d.setStroke(new BasicStroke(2));
                
                // Draw circular arrow
                g2d.drawArc(x + 2, y + 2, 12, 12, 0, 270);
                g2d.drawLine(x + 13, y + 3, x + 14, y + 1);
                g2d.drawLine(x + 13, y + 3, x + 15, y + 4);
            }
            
            @Override
            public int getIconWidth() {
                return 16;
            }
            
            @Override
            public int getIconHeight() {
                return 16;
            }
        };
    }
    
    private Icon createSaveIcon() {
        return new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(34, 139, 34));
                
                // Draw floppy disk
                g2d.fillRect(x + 2, y + 1, 12, 14);
                g2d.setColor(new Color(200, 200, 200));
                g2d.fillRect(x + 2, y + 1, 12, 4);
                g2d.setColor(new Color(34, 139, 34));
                g2d.fillRect(x + 4, y + 3, 8, 2);
                g2d.fillRect(x + 3, y + 7, 10, 7);
            }
            
            @Override
            public int getIconWidth() {
                return 16;
            }
            
            @Override
            public int getIconHeight() {
                return 16;
            }
        };
    }
    
    private Icon createDeleteIcon() {
        return new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(220, 20, 60));
                
                // Draw trash bin
                g2d.drawLine(x + 4, y + 2, x + 12, y + 2);
                g2d.fillRect(x + 3, y + 3, 10, 12);
                g2d.setColor(new Color(255, 255, 255));
                g2d.fillRect(x + 5, y + 5, 6, 8);
            }
            
            @Override
            public int getIconWidth() {
                return 16;
            }
            
            @Override
            public int getIconHeight() {
                return 16;
            }
        };
    }
    
    private Icon createClearIcon() {
        return new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(105, 105, 105));
                g2d.setStroke(new BasicStroke(2));
                
                // Draw X
                g2d.drawLine(x + 3, y + 3, x + 13, y + 13);
                g2d.drawLine(x + 13, y + 3, x + 3, y + 13);
            }
            
            @Override
            public int getIconWidth() {
                return 16;
            }
            
            @Override
            public int getIconHeight() {
                return 16;
            }
        };
    }
    
    // ============ DASHBOARD PANEL ============
    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Title
        JLabel titleLabel = new JLabel("Event Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);
        
        // Table to display events
        String[] columnNames = {"Event Name", "Start Date", "End Date"};
        tableModel = new DefaultTableModel(columnNames, 0);
        eventTable = new JTable(tableModel);
        eventTable.setFont(new Font("Arial", Font.PLAIN, 12));
        eventTable.setRowHeight(25);
        eventTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(eventTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JButton loadBtn = new JButton("Load", createLoadIcon());
        loadBtn.setFont(new Font("Arial", Font.PLAIN, 12));
        loadBtn.addActionListener(e -> loadEvents());
        buttonPanel.add(loadBtn);
        
        JButton refreshBtn = new JButton("Refresh", createRefreshIcon());
        refreshBtn.setFont(new Font("Arial", Font.PLAIN, 12));
        refreshBtn.addActionListener(e -> refreshTable());
        buttonPanel.add(refreshBtn);
        
        JButton saveBtn = new JButton("Save All", createSaveIcon());
        saveBtn.setFont(new Font("Arial", Font.PLAIN, 12));
        saveBtn.addActionListener(e -> saveEvents());
        buttonPanel.add(saveBtn);
        
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Load initial data
        refreshTable();
        
        return panel;
    }
    
    // ============ ADD EVENT PANEL ============
    private JPanel createAddEventPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Title
        JLabel titleLabel = new JLabel("Add New Event");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(20));
        
        // Event Name
        panel.add(new JLabel("Event Name:"));
        eventNameField = new JTextField();
        eventNameField.setMaximumSize(new Dimension(400, 35));
        eventNameField.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(eventNameField);
        panel.add(Box.createVerticalStrut(15));
        
        // Start Date
        panel.add(new JLabel("Start Date (dd/MM/yyyy HH:mm):"));
        startDateField = new JTextField();
        startDateField.setMaximumSize(new Dimension(400, 35));
        startDateField.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(startDateField);
        panel.add(Box.createVerticalStrut(15));
        
        // End Date
        panel.add(new JLabel("End Date (dd/MM/yyyy HH:mm):"));
        endDateField = new JTextField();
        endDateField.setMaximumSize(new Dimension(400, 35));
        endDateField.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(endDateField);
        panel.add(Box.createVerticalStrut(25));
        
        // Output area
        JLabel outputLabel = new JLabel("Status:");
        panel.add(outputLabel);
        outputArea = new JTextArea(5, 40);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Arial", Font.PLAIN, 11));
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        panel.add(scrollPane);
        panel.add(Box.createVerticalStrut(15));
        
        // Button
        JButton addBtn = new JButton("Add Event", createAddEventIcon());
        addBtn.setFont(new Font("Arial", Font.BOLD, 13));
        addBtn.setPreferredSize(new Dimension(150, 40));
        addBtn.addActionListener(e -> addEvent());
        panel.add(addBtn);
        
        panel.add(Box.createVerticalGlue());
        
        return panel;
    }
    
    // ============ SEARCH PANEL ============
    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Title
        JLabel titleLabel = new JLabel("Search Events");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        JPanel titlePanel = new JPanel();
        titlePanel.add(titleLabel);
        panel.add(titlePanel, BorderLayout.NORTH);
        
        // Search criteria panel
        JPanel searchCriteriaPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        searchCriteriaPanel.setBorder(BorderFactory.createTitledBorder("Search Criteria"));
        
        searchCriteriaPanel.add(new JLabel("Search by Name:"));
        searchNameField = new JTextField();
        searchNameField.setFont(new Font("Arial", Font.PLAIN, 12));
        searchCriteriaPanel.add(searchNameField);
        
        searchCriteriaPanel.add(new JLabel("Search by Date (dd/MM/yyyy HH:mm):"));
        searchDateField = new JTextField();
        searchDateField.setFont(new Font("Arial", Font.PLAIN, 12));
        searchCriteriaPanel.add(searchDateField);
        
        panel.add(searchCriteriaPanel, BorderLayout.NORTH);
        
        // Results table
        String[] columnNames = {"Event Name", "Start Date", "End Date"};
        DefaultTableModel searchTableModel = new DefaultTableModel(columnNames, 0);
        JTable searchTable = new JTable(searchTableModel);
        searchTable.setFont(new Font("Arial", Font.PLAIN, 12));
        searchTable.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(searchTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JButton searchBtn = new JButton("Search", createSearchIcon());
        searchBtn.setFont(new Font("Arial", Font.PLAIN, 12));
        searchBtn.addActionListener(e -> {
            String name = searchNameField.getText().trim();
            String dateStr = searchDateField.getText().trim();
            LocalDateTime date = null;
            
            if (!dateStr.isEmpty()) {
                try {
                    date = LocalDateTime.parse(dateStr, formatter);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(this, 
                        "Invalid date format. Use dd/MM/yyyy HH:mm", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
            searchTableModel.setRowCount(0);
            List<Event> results = eventManager.retrieve(date, name);
            for (Event event : results) {
                searchTableModel.addRow(new Object[]{
                    event.getEventName(),
                    event.getStartDateTime().format(formatter),
                    event.getEndDateTime().format(formatter)
                });
            }
            
            if (results.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "No events found.", 
                    "Search Result", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        buttonPanel.add(searchBtn);
        
        JButton clearBtn = new JButton("Clear", createClearIcon());
        clearBtn.setFont(new Font("Arial", Font.PLAIN, 12));
        clearBtn.addActionListener(e -> {
            searchNameField.setText("");
            searchDateField.setText("");
            searchTableModel.setRowCount(0);
        });
        buttonPanel.add(clearBtn);
        
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    // ============ MANAGE PANEL ============
    private JPanel createManagePanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Title
        JLabel titleLabel = new JLabel("Manage Events");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        JPanel titlePanel = new JPanel();
        titlePanel.add(titleLabel);
        panel.add(titlePanel, BorderLayout.NORTH);
        
        // Events list table
        String[] columnNames = {"Event Name", "Start Date", "End Date"};
        DefaultTableModel manageTableModel = new DefaultTableModel(columnNames, 0);
        JTable manageTable = new JTable(manageTableModel);
        manageTable.setFont(new Font("Arial", Font.PLAIN, 12));
        manageTable.setRowHeight(25);
        manageTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(manageTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JButton refreshBtn = new JButton("Refresh", createRefreshIcon());
        refreshBtn.setFont(new Font("Arial", Font.PLAIN, 12));
        refreshBtn.addActionListener(e -> {
            manageTableModel.setRowCount(0);
            List<Event> allEvents = eventManager.getAllEvents();
            for (Event event : allEvents) {
                manageTableModel.addRow(new Object[]{
                    event.getEventName(),
                    event.getStartDateTime().format(formatter),
                    event.getEndDateTime().format(formatter)
                });
            }
        });
        buttonPanel.add(refreshBtn);
        
        JButton deleteBtn = new JButton("Delete Selected", createDeleteIcon());
        deleteBtn.setFont(new Font("Arial", Font.PLAIN, 12));
        deleteBtn.addActionListener(e -> {
            int selectedRow = manageTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, 
                    "Please select an event to delete.", 
                    "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                String eventName = (String) manageTableModel.getValueAt(selectedRow, 0);
                int confirm = JOptionPane.showConfirmDialog(this, 
                    "Are you sure you want to delete '" + eventName + "'?", 
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (eventManager.remove(eventName)) {
                        eventManager.save();
                        JOptionPane.showMessageDialog(this, 
                            "Event deleted successfully.", 
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                        manageTableModel.removeRow(selectedRow);
                        refreshTable();
                    } else {
                        JOptionPane.showMessageDialog(this, 
                            "Could not find event to delete.", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        buttonPanel.add(deleteBtn);
        
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Load initial data
        refreshBtn.doClick();
        
        return panel;
    }
    
    // ============ HELPER METHODS ============
    private void addEvent() {
        String name = eventNameField.getText().trim();
        String startStr = startDateField.getText().trim();
        String endStr = endDateField.getText().trim();
        
        // Validation
        if (name.isEmpty() || startStr.isEmpty() || endStr.isEmpty()) {
            outputArea.setText("Error: All fields are required.");
            return;
        }
        
        try {
            LocalDateTime startDateTime = LocalDateTime.parse(startStr, formatter);
            LocalDateTime endDateTime = LocalDateTime.parse(endStr, formatter);
            
            Event event = new Event(name, startDateTime, endDateTime);
            eventManager.add(event);
            eventManager.save();
            
            outputArea.setText("Event added successfully!\n\n" + event.toString());
            
            // Clear fields
            eventNameField.setText("");
            startDateField.setText("");
            endDateField.setText("");
            
            // Refresh dashboard
            refreshTable();
            
        } catch (InvalidEventNameException e) {
            outputArea.setText("Error: " + e.getMessage());
        } catch (InvalidEventDateException e) {
            outputArea.setText("Error: " + e.getMessage());
        } catch (DateTimeParseException e) {
            outputArea.setText("Error: Invalid date format. Use dd/MM/yyyy HH:mm");
        }
    }
    
    
    private void refreshTable() {
        tableModel.setRowCount(0);
        List<Event> allEvents = eventManager.getAllEvents();
        for (Event event : allEvents) {
            tableModel.addRow(new Object[]{
                event.getEventName(),
                event.getStartDateTime().format(formatter),
                event.getEndDateTime().format(formatter)
            });
        }
    }
    
    private void saveEvents() {
        eventManager.save();
        JOptionPane.showMessageDialog(this, 
            "All events saved successfully!", 
            "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void loadEvents() {
        eventManager.load();
        refreshTable();
        JOptionPane.showMessageDialog(this, 
            "Events loaded successfully from file!", 
            "Load Complete", JOptionPane.INFORMATION_MESSAGE);
    }
    
   
}
