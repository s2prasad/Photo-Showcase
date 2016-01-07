package prasad.assign3;
/**
 * 
 * @author Swathi
 *
 */

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class MyArtPhotoShowcase extends JFrame 
{
	//Create panels
	private		JSplitPane	splitPaneH;
	private		JPanel		panel1,panel2,panel3,rightPanel;		 
	
	//create variables
	private String imgPath,imgName,imgKey;
	
	private Border thickBorder = new LineBorder(Color.BLACK, 8);
	private Border thinBorder = new LineBorder(Color.BLACK, 4);
	private CardLayout cl= new CardLayout();
	
	private Font font=new Font("Arial", Font.BOLD, 20);
	private JFileChooser fc = new JFileChooser();	
	private	Picture selectedImg;	
	private Map<String, Picture> map = new HashMap<String, Picture>();
	private Map<String, JButton> buttonMap = new ConcurrentHashMap<String, JButton>();
			
	private JButton imgButton, selectImage, save;	

	private JLabel picCaption,labelCaption,picDescription,labelDescription, labelCategory,picDate, labelPhotographer, labelLocation;
	private JTextField imgCaption,imgDesc,imgCategory,imgPhotographer,imgLocation, photographerField, locationField,categoryField,description,caption;
	private JComboBox comboBox;
	private JLabel jLab=new JLabel();
	
	/**
	 * Class constructor
	 */	
	public MyArtPhotoShowcase()
	{
		
		setTitle( "My Art Photo Showcase" );
		setBackground( Color.gray );
		
		// Create the panels
		createPanel1();
		createPanel2();
		
		rightPanel=new JPanel();		
		rightPanel.setLayout(cl);						//Make the right panel a CardLayout
		rightPanel.add(panel2,"1");						//Right panel contains panel2("1") and panel3("2")
		panel3=new JPanel();
		rightPanel.add(panel3,"2");
		
		panel3.setLayout(new FlowLayout());				//Set panel3 as FlowLayout
		
		cl.show(rightPanel, "1");						//Set panel panel2 ("1") to show as default in rightPanel
		
		//Styling for panels
		panel1.setBorder(BorderFactory.createLineBorder(Color.black));
		panel1.setBackground(Color.WHITE);
		panel2.setBorder(BorderFactory.createLineBorder(Color.black));
		panel2.setBackground(Color.WHITE);
		panel3.setBorder(BorderFactory.createLineBorder(Color.black));
		panel3.setBackground(Color.WHITE);
		
		// Create a splitter pane
		splitPaneH = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT );			//Frame is split into two panels i.e panel1 and rightPanel
		getContentPane().add(splitPaneH);
		splitPaneH.setLeftComponent( panel1 );
		splitPaneH.setRightComponent( rightPanel );		
		splitPaneH.setDividerLocation(450);
	}
	
	/**
	 *  Method to create panel1, that is the left most panel in UI
	 */
	public void createPanel1()
	{		
		//panel1 uses GridBagLayout
		//set positioning for elements in panel1
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 1, 0, 0, 0, 0, 0, 0, 0, 0,0 };
		gbl_panel_1.rowHeights = new int[] { 0,0,0,0, 0, 0 ,0,0 ,0,0};
		gbl_panel_1.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0,0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] {0, 0,0,0, 0,0,0,0,0,Double.MIN_VALUE };
		panel1 = new JPanel();
		panel1.setLayout(gbl_panel_1);
		
		final GridBagConstraints c=new GridBagConstraints();		
		c.anchor= GridBagConstraints.FIRST_LINE_END;		
		
		//add label and textfield for caption
		c.gridy=1;
		picCaption=new JLabel("Title: ");	
		panel1.add(picCaption,c);
		picCaption.setFont(font);
		caption=new JTextField(10);
		caption.setColumns( 10 );		
		panel1.add(caption,c);
		
		//add label and textfield for description
		c.gridy++;
		picDescription=new JLabel("Description: ");
		panel1.add(picDescription,c);
		picDescription.setFont(font);
		 description=new JTextField(10);
		description.setColumns( 10 );
		panel1.add(description,c);
		
		//add label and textfield for date
		c.gridy++;
		picDate=new JLabel("Date: ");
		picDate.setFont(font);
		panel1.add(picDate,c);		
		long now = System.currentTimeMillis();
		final JFormattedTextField date = new JFormattedTextField(new java.util.Date(now));
		date.setColumns( 10 );
		picDescription.setFont(font);
		panel1.add(date,c);
		
		//add label and textfield for category
		c.gridy++;
		JLabel categoryLabel=new JLabel("Category");
		categoryLabel.setFont(font);
		panel1.add(categoryLabel,c);		 
		categoryField = new JTextField(10);
		categoryField.setColumns( 10 );		
		panel1.add(categoryField,c);
		
		//add label and textfield for photographer
		c.gridy++;
		JLabel photographerLabel=new JLabel("Photographer");
		photographerLabel.setFont(font);
		panel1.add(photographerLabel,c);		 
		photographerField = new JTextField(10);
		photographerField.setColumns( 10 );	
		panel1.add(photographerField,c);
		
		//add label and textfield for location
		c.gridy++;
		JLabel locationLabel=new JLabel("Location");
		locationLabel.setFont(font);
		panel1.add(locationLabel,c);		 
		locationField = new JTextField(10);
		locationField.setColumns( 10 );		
		panel1.add(locationField,c);
				
		//add a button for File Chooser
		c.gridy++;
		selectImage= new JButton("Select image");
		panel1.add(selectImage,c);
		
		//add a button for to save image and description
		save= new JButton("Add image and Description to gallery");
		panel1.add(save,c);		
		c.gridx=0;
        c.gridy++;
        
        c.gridx++;
        jLab.setIcon(null);
        panel1.add(jLab,c);
		
		/**
		 * Action listener for selecting an image using file chooser
		 */
		selectImage.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				 
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);		//Selects files and directories
		        fc.setCurrentDirectory(new File("."));								//points to the current directory
				int returnVal = fc.showOpenDialog(selectImage);

	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	                File file = fc.getSelectedFile();								//get chosen file
	                ImageIcon icon= new ImageIcon(file.toString());	                	
	                icon.setImage(icon.getImage().getScaledInstance(200,150, Image.SCALE_DEFAULT));
	                jLab.setIcon(icon);
	                jLab.setBorder(thinBorder);
	                
	                caption.setColumns( 10 );
	                description.setColumns( 10 );
	                photographerField.setColumns( 10 );
	                date.setColumns(10);
	                locationField.setColumns( 10 );
	                panel1.validate();   
	                try {
	                	imgName=fc.getSelectedFile().getName();
		                imgPath=fc.getSelectedFile().getAbsolutePath();
	                  }
	                 catch (  final Exception ex) {
	                    JOptionPane.showMessageDialog(null,1,"Warning!",JOptionPane.WARNING_MESSAGE);
	                    file=null;
	                  }
	              
	            }
			}	
		});
		
		/**
		 * Action listener for saving image and description
		 */
		save.addActionListener(new ActionListener(){
			
			// check if title || description || category fields are empty
			@Override
			public void actionPerformed(ActionEvent e) {
				if( caption.getText().isEmpty() || description.getText().isEmpty() || categoryField.getText().isEmpty()){
				    JOptionPane.showMessageDialog(null, "Enter Values In Fields", "Enter Values In Fields", JOptionPane.ERROR_MESSAGE);
				    return;//return from the method to allow the user to edit the JTextField
				}
				if(imgName != null && imgPath != null){
				//display the entered category in the combobox for filtering 
				String comboSelection= categoryField.getText();				
				displayComboBox(comboSelection);
				
				//Store all the field values into a new Picture object
				Picture pic= new Picture();
				pic.setPath(imgPath);
				pic.setCaption(caption.getText());
				pic.setDescription(description.getText());
				pic.setPhotographer(photographerField.getText());
				pic.setLocation(locationField.getText());
				pic.setPicName(imgName);
				java.util.Date theDate = (java.util.Date)date.getValue();
				pic.setDate(theDate);
				pic.setCategory(comboSelection);;
				
				//Store the newly created picture object(value) into a hash map with its image name as its key.
				map.put(imgName, pic);	
				
				//display the image in the gallery area
				displayGalleryImg(pic);
				
				//Clear all the input fields
				caption.setText("");
				caption.setColumns( 10 );
				description.setText("");
				description.setColumns( 10 );
				categoryField.setText("");
				photographerField.setText("");
				photographerField.setColumns( 10 );
				locationField.setText("");
				locationField.setColumns( 10 );
				jLab.setIcon(null);
				fc.setSelectedFile(new File(""));					//clear file chooser
				imgPath = null;
				imgName = null;
				}
				else {					//if save button is clicked without selecting image
					 JOptionPane.showMessageDialog(null, "Select image!", "Select image!", JOptionPane.ERROR_MESSAGE);
				}
			}			
		});		
	}
	
	/**
	 * 
	 * @param comboSelection : the category value
	 *  method displays the category in the combobox.
	 */
	public void displayComboBox(String comboSelection){
		Boolean existingCategory=false;		
		for(String key : map.keySet()){
			   Picture value = map.get(key);
			   
			   //check if the category already exists
			   if ((value.getCategory()).equals(comboSelection) )
			   {
				   existingCategory=true;
				   break;
			   }
		}		
		
		//add the category to combobox if that category does not already exist
		if (existingCategory==false){
			comboBox.addItem(comboSelection);
		}
		existingCategory=false;
	
		/**
		 * Action listener for selecting combobox values
		 */
		comboBox.addActionListener(new ActionListener(){
		  	public void actionPerformed(ActionEvent e){
		  	     String selectedSortCategory = (String)comboBox.getSelectedItem();      //get selected combobox option
		  	    // Remove all the buttons which contains images , which is placed on panel2
		  	   for(String buttonKey : buttonMap.keySet()){
		  		 JButton jbutton= buttonMap.get(buttonKey);
				   panel2.remove(jbutton); 				 // remove button
				   buttonMap.remove(buttonKey);			 //remove button from hashmap of buttons				   
		  	   }
		  	   
		  	 //Select all the images which has category value same as the category selcted for filtering in combobox
		  	 for(String key : map.keySet()){
			  		Picture value = map.get(key);
			  		if(selectedSortCategory.equals("Sort by Category"))
					   {
			  			displayGalleryImg(value);     	//Displays all the images without regard to image category				    
					   }
			  		else if(selectedSortCategory.equals(value.getCategory()))
					   {
			  			displayGalleryImg(value);     	//Displays only the images which has category same as combobox		    
				   }
		  	 }   
			   panel2.repaint();
			   panel2.validate();
		  	}
		  }); 
	}
	
	/**
	 * 
	 * @param value image whose category is same as selected category
	 */
	public void displayGalleryImg(Picture value){
		//Display the image on panel2 using ImageIcon and JButton
		ImageIcon icon=new ImageIcon(value.getPath());
		icon.setImage(icon.getImage().getScaledInstance(200,200, Image.SCALE_DEFAULT));
		imgButton=new JButton(icon);
		imgButton.setBorder(thickBorder);
		imgButton.setText(value.getPicName());
        panel2.add(imgButton);		// Add image to panel2
        panel2.validate();
      //Store all the buttons created in a hashmap. So that it will be helpful when filtering by other categories
        buttonMap.put(value.getPicName(), imgButton);
        
        //Action listener to the images. So that on click of it , a new panel which has edit elements appears
		imgButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(rightPanel, "2");					//Show panel3 of the cardLayout	
				imgKey=e.getActionCommand();				//Get image name of clicked image
				selectedImg=map.get(imgKey);				//Get the clicked image
				editPanel();				
			}
		});	
		return;
	}

	/**
	 * Builds the panel3 which contains an image and other editable information
	 */
	public void editPanel(){
		
		//Intialize Gridbaglayout properties.
		//set panel3 to use GridBagLayout
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_3.rowHeights = new int[] {0,0,0,0,0,0, 0, 0, 0 ,0,0 ,0};
		gbl_panel_3.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_3.rowWeights = new double[] {0,0,0, 0,0,0,0, 0,0,0,0,Double.MIN_VALUE };
		
		panel3.setLayout(gbl_panel_3);
		
		final GridBagConstraints constraints=new GridBagConstraints();		
		constraints.anchor= GridBagConstraints.CENTER;
		
		//Add selected image to panel3
		constraints.gridy=0;
		constraints.gridx=1;
		constraints.fill= GridBagConstraints.HORIZONTAL;
		ImageIcon icon=new ImageIcon(selectedImg.getPath());
		icon.setImage(icon.getImage().getScaledInstance(400,400, Image.SCALE_DEFAULT));
		JLabel imgLabel=new JLabel(icon);
		panel3.add(imgLabel,constraints);
		constraints.gridy++;
		constraints.gridx=0;
		
		//Add editable caption and description text fields containing their already stored values
		labelCaption=new JLabel("Caption");
		labelCaption.setFont(font);
		imgCaption=new JTextField(selectedImg.getCaption());	////Add stored caption to textfield
		imgCaption.setColumns( 10 );
		panel3.add(labelCaption,constraints);
		constraints.gridx++;
		panel3.add(imgCaption,constraints);
		constraints.gridy++;
		constraints.gridx=0;
		labelDescription=new JLabel("Description");
		labelDescription.setFont(font);
		
		imgDesc=new JTextField(selectedImg.getDescription());	//Add stored description to textfield
		imgDesc.setColumns( 10 );
		panel3.add(labelDescription,constraints);
		constraints.gridx++;
		panel3.add(imgDesc,constraints);
		constraints.gridy++;
		
		constraints.gridx=0;
		labelCategory=new JLabel("Category");
		labelCategory.setFont(font);
		imgCategory=new JTextField(selectedImg.getCategory());		//Add selected image to panel3
		imgCategory.setColumns( 10 );
		panel3.add(labelCategory,constraints);
		constraints.gridx++;
		panel3.add(imgCategory,constraints);
		constraints.gridy++;

		constraints.gridx=0;
		labelPhotographer=new JLabel("Photographer");
		labelPhotographer.setFont(font);
		imgPhotographer=new JTextField(selectedImg.getPhotographer());		//Add selected image to panel3
		imgPhotographer.setColumns( 10 );
		panel3.add(labelPhotographer,constraints);
		constraints.gridx++;
		panel3.add(imgPhotographer,constraints);
		constraints.gridy++;
		
		constraints.gridx=0;
		labelLocation=new JLabel("Location");
		labelLocation.setFont(font);
		imgLocation=new JTextField(selectedImg.getLocation());		//Add selected image to panel3
		imgLocation.setColumns( 10 );
		panel3.add(labelLocation,constraints);
		constraints.gridx++;
		panel3.add(imgLocation,constraints);
		constraints.gridy++;
		
		JLabel labelDate=new JLabel("Date");					//Add stored date to panel3
		labelDate.setFont(font);
		constraints.gridy++;
		JButton saveEdited= new JButton("Save Description");	//Create a JBtuoon to store the dited information
		panel3.add(saveEdited,constraints);
		
		constraints.gridy++;
		JButton deleteImg= new JButton("Delete Image");			//Delete button to delete the selected image and its tore information
		panel3.add(deleteImg,constraints);
		
		/**
		 * Add action listener to save button. It saves the edited caption, description, date to the respective values of the object
		 */
		saveEdited.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(rightPanel, "1");						//Displays back panel2
				selectedImg.setCaption(imgCaption.getText());				
				selectedImg.setDescription(imgDesc.getText());
				
				//in case user changes the category of the image
				String removeCategory=selectedImg.getCategory();
				displayComboBox(imgCategory.getText());					//Update in combobox that is filter button
				selectedImg.setCategory(imgCategory.getText());			//Set the image to new category
				deleteCategory(removeCategory);							//Delete old category if there are no other picture of same category
				
				selectedImg.setPhotographer(imgPhotographer.getText());
				selectedImg.setLocation(imgLocation.getText());
				panel3.removeAll(); 							//Clears the panel3
				// refresh the panel.
				panel3.updateUI();
				
			}
		});
		
		/**
		 * Deletes the selected image and its stored properties
		 */
		deleteImg.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(rightPanel, "1");					//Displays back panel2
				Picture removeImg=map.get(imgKey);
				String removeCategory=removeImg.getCategory();
				map.remove(imgKey);							//Removes it from Hashmap
				JButton jButton = buttonMap.get(imgKey);	//Selects the image's button from button Hashmap				
				panel2.remove(jButton);						//Removes the image's button from button Hashmap
				panel3.removeAll(); 						//Clears the panel3
				// refresh the panel.
				panel3.updateUI();
				deleteCategory(removeCategory);
				
				
			}
		});
	}
	
	
	public void deleteCategory(String removeCategory){
		Boolean categoryExists=false;
		 for(String key : map.keySet()){			//Checks if other objects of same category exists
	        	Picture value = map.get(key);
	        	if(value.getCategory().equals(removeCategory))
	        	{
	        		categoryExists=true;				//if exists then dont remove category type from combobox 
	        		break;}
	        }
		 if(categoryExists==false)
		 {
			 comboBox.removeItem(removeCategory);			//if does not exist then remove that category from combobox
		 }
	}
	/**
	 * Creates panel2 which is on the right side of the page
	 */
	public void createPanel2()
	{
		//Set panel2's layout as FlowLayout
		FlowLayout flowLayout=new FlowLayout();
		panel2 = new JPanel();
		panel2.setLayout( flowLayout );
		
		//panel2 contains a navPanel which contains button for sort by date and combobox for filter by category			
		JPanel navPanel = new JPanel();
		navPanel.setLayout(flowLayout);
		//JButton sortDate= new JButton( "Sort by Date" );
		//sortDate.setFont(new Font("Arial", Font.BOLD, 20));
		
		comboBox = new JComboBox();						//create a combo box which stores categories to filter
		comboBox.addItem("Sort by Category");			//set first item of combobox
		comboBox.setForeground(Color.BLACK);
		comboBox.setMinimumSize(new Dimension(100,20));
		comboBox.setFont(new Font("Arial", Font.BOLD, 20));		
		comboBox.setEditable(false);
		comboBox.setBounds(20, 20, 200, 100);
		
		//navPanel.add(sortDate);							//add date and comboBox to navPanel
		navPanel.add(comboBox);
		panel2.add(navPanel);	
		navPanel.setBackground(Color.BLACK);
		navPanel.setPreferredSize(new Dimension(650, 50));
		panel2.validate();		
		
	}
		
		
	public static void main( String args[] )
	{
		// Create an instance of the newLayout application
		MyArtPhotoShowcase mainFrame	= new MyArtPhotoShowcase();
		mainFrame.setSize(1100, 900);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(true);
		mainFrame.setDefaultCloseOperation(mainFrame.DISPOSE_ON_CLOSE);		
		mainFrame.setVisible( true );
	}

	
}