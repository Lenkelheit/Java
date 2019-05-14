package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Properties;
 
public class JDBCFrame extends JFrame implements ActionListener
{
    // FIELDS
    private JLabel connectionStringLabel = new JLabel("Connection string:");
	private JTextField connectionString = new JTextField("jdbc:postgresql://127.0.0.1:5432/Restaurant_base");
	private JTree tree = new JTree();
	private JTextArea query = new JTextArea();
	
	private JButton connectButton = new JButton("Connect");
	private JButton executeButton = new JButton("Execute query to the database");

    private JMenuBar menuBar = new JMenuBar();
    private JMenu databaseMenu = new JMenu("Database");
    private JMenuItem connectMenu = new JMenuItem("Connect");
    private JSeparator separator = new JSeparator();
    private JMenuItem exitMenu = new JMenuItem("Exit");

    private JMenu helpMenu = new JMenu("Help");
    private JMenuItem aboutMenu = new JMenuItem("About");

    //Structure tree
    DefaultTreeModel treeModel = (DefaultTreeModel)tree.getModel();
    DefaultMutableTreeNode root = (DefaultMutableTreeNode)treeModel.getRoot();
    
    //Results table
    DefaultTableModel model = new DefaultTableModel(); 
    JTable table = new JTable(model);
    
    private JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
    private JPanel topPanel = new JPanel(new BorderLayout(10, 10));
    private JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
    private JPanel centerLeftPanel = new JPanel(new BorderLayout(10, 10));
    private JPanel centerRightPanel = new JPanel(new GridLayout(2, 1, 10, 10));
    
    private JPanel queryPanel = new JPanel(new BorderLayout(10,10));
    private JPanel resultsPanel = new JPanel(new BorderLayout(10,10));

    private Connection connection;
    private java.sql.Statement statement;
    private DatabaseMetaData metaData;

    // CONSTRUCTOR
    public JDBCFrame()
    {
        super("Working with database");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(800, 500));
        setResizable(false);

        setLayout(new BorderLayout(10, 10));

        connectMenu.addActionListener(this);
        exitMenu.addActionListener(this);

        databaseMenu.add(connectMenu);
        databaseMenu.add(separator);
        databaseMenu.add(exitMenu);

        aboutMenu.addActionListener(this);
        helpMenu.add(aboutMenu);

        menuBar.add(databaseMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
        
        connectButton.addActionListener(this);
        executeButton.addActionListener(this);
                
        topPanel.add(connectionStringLabel, BorderLayout.WEST);
        topPanel.add(connectionString, BorderLayout.CENTER);
        topPanel.add(connectButton, BorderLayout.EAST);
        
        centerLeftPanel.setPreferredSize(new Dimension(160, 600));
        centerLeftPanel.add(new JLabel("Structure:"), BorderLayout.NORTH);
        
        root.setUserObject("No Database");
        root.removeAllChildren();
        treeModel.reload(root);
        
        JScrollPane scrollingTree = new JScrollPane();
        scrollingTree.setViewportView(tree);
        centerLeftPanel.add(scrollingTree, BorderLayout.CENTER);
        
        JPanel queryLinePanel = new JPanel(new BorderLayout(10,10));
        queryLinePanel.add(new JLabel("Query:"), BorderLayout.WEST);
        queryLinePanel.add(executeButton, BorderLayout.EAST);
        
        queryPanel.add(queryLinePanel, BorderLayout.NORTH);
        queryPanel.add(query, BorderLayout.CENTER);
        
        JPanel scrollingTable = new JPanel(new BorderLayout());
        scrollingTable.add(new JScrollPane(table));
        scrollingTable.add(table.getTableHeader(), BorderLayout.NORTH);
        scrollingTable.add(table, BorderLayout.CENTER);
        resultsPanel.add(new JLabel("Results:"), BorderLayout.NORTH);
        resultsPanel.add(new JScrollPane(scrollingTable), BorderLayout.CENTER);

        centerRightPanel.add(queryPanel);
        centerRightPanel.add(resultsPanel);

        centerPanel.add(centerLeftPanel, BorderLayout.WEST);
        centerPanel.add(centerRightPanel, BorderLayout.CENTER);
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(mainPanel);
    }

    // METHODS
    public void actionPerformed(ActionEvent e)
    {
        var event = e.getSource();
    	if(event == connectButton || event == connectMenu)
    	{
    		System.setProperty("jdbc.drivers", "org.postgresql.Driver");
    		String url = connectionString.getText();
    		Properties props = new Properties();
    		props.setProperty("user","postgres");
    		props.setProperty("password","1234");
    		try
            {
                connection = DriverManager.getConnection(url, props);
                metaData = connection.getMetaData();
				root.setUserObject(connection.getCatalog());
				root.removeAllChildren();
				ResultSet tables = metaData.getTables(null, null, null, new String[] { "TABLE" });
				while (tables.next())
				{
					DefaultMutableTreeNode dbTable = new DefaultMutableTreeNode(tables.getString(3));
					ResultSet columns = metaData.getColumns(null, null, tables.getString(3), null);
					while(columns.next())
					{
						String name = columns.getString("COLUMN_NAME");
						dbTable.add(new DefaultMutableTreeNode(name));
					}
					root.add(dbTable);
				}
	            tables.close();
			} catch (SQLException e1)
            {
				e1.printStackTrace();
			}
            treeModel.reload(root);
    	}
    	else if(event == executeButton)
    	{
    		try
            {
    			removeAllRows();

                statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(query.getText());
				ResultSetMetaData rsmd = rs.getMetaData();
				int columns = rsmd.getColumnCount();
				for(int i=0; i<columns; ++i)
				{
					model.addColumn(rsmd.getColumnName(i+1));
				}

				while(rs.next())
				{
					Object[] values = new Object[columns];
					for(int i=0; i<columns; ++i)
					{
						values[i] = rs.getString(i+1);
					}
					model.addRow(values);
				}
			} catch (SQLException e1)
            {
				e1.printStackTrace();
			}
    	}
    	else if(event == aboutMenu)
        {
        	AboutFrame frame = new AboutFrame("Developed by Nazariy Tymtsiv.");
            frame.setVisible(true);
        }
        else if(event == exitMenu)
        {
        	System.exit(0);
        }
    } 

    public void removeAllRows()
    {
    	int rowCount = model.getRowCount();
    	for (int i = rowCount - 1; i >= 0; i--) 
    	{
    	    model.removeRow(i);
    	}
    	model.setColumnCount(0);
    }
}