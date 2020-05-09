
/** 
* File Name: CMIS242PRJ2XaireB
* Author: Borja X13
* Date: 15 April 2020
* UMGC CMIS 242
* Project 2
*/

package cmis242prj2xaireb;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


/*
 * This program creates a UI along with all the functionality to add the automobile objects 
 * into a report to be displayed. It also checks that the inputs give by the user are valid
 * and prompts for new inputs if they are not. 
 */

public class CMIS242PRJ2XaireB {
    /*
    * This class provides the framework for the automobile superclass which will 
    * be inherited from.
    */
    static class Automobile {
        
	//instance variables
	private String makeModel;
	private int purchasePrice;
	
	//constructor to initialize the makeModel and purchasePrice
	public Automobile(String makeModel, int purchasePrice) {
		this.makeModel = makeModel;
		this.purchasePrice = purchasePrice;
	}//end public Automobile(String makeModel, double purchasePrice)
	
	//method to compute the salesTax
	public int salesTax() {
		return (purchasePrice * 5) / 100; 
	}//end public double salesTax()
	
	//toString method
	public String toString() {
		return "\nMake and Model: " + makeModel + "\n Sales Price: " + purchasePrice + "\n Sales Tax: " + salesTax() + "\n Cost of Automobile: " + (purchasePrice+=salesTax()) + "\n" ;
	}//end public String toString()
}//end public class Automobile
    
    /*
     * This class inherits from the Automobile superclass.
    */
    static class Electric extends Automobile {
	
	//instance variables
	private int automobileWeight;
	
	//initializing constructor
	public Electric(String makeModel, int purchasePrice, int automobileWeight) {
		super(makeModel, purchasePrice);
		this.automobileWeight = automobileWeight;
	}//end public Electric(String makeModel, double purchasePrice, int automobileWeight)
	
	//Overridden method to compute the sales tax due
	public int salesTax() {
		if (automobileWeight <= 3000) {
			return super.salesTax() - 200;
		}else {
			return super.salesTax() - 150;
		}//end if
	}//end public double salesTax()
	
	//overridden toString method
	public String toString() {
		return super.toString() + "Electric Vehicle \n Vehicle Weight: " + automobileWeight + " lbs\n\n";
	}//end public String toString()  
    }//end public class Electric extends Automobile

    /*
    * This class inherits from the Automobile superclass.
    */
    static class Hybrid extends Automobile {
        
	//instance variables
	private int mpg;
	
	//initializing constructor
	public Hybrid(String makeModel, int purchasePrice, int mpg) {
		super(makeModel, purchasePrice);
		this.mpg = mpg;
	}//end public Hybrid(String makeModel, double purchasePrice, int mpg)
	
	//overridden salesTax method
	public int salesTax() {
		if(mpg<=40) {
			return super.salesTax() - 100;
		}else {
			return super.salesTax() - (100 + ((mpg - 40) + 2 ));
		}//end if
	}//end public double salesTax()
	
	//overridden toString
	public String toString() {
		return super.toString() + "Hybrid Vehicle \n MPG: " + mpg + " MPG\n\n";
	}//end public String toString()
    }//end public class Hybrid extends Automobile

    
    static class AutomobileUI extends JFrame implements ActionListener {
     //variables for UIs display
  
   JLabel makeAndModelLabel,salesPriceLabel;
   JTextField makeAndModel, salesPrice;
  
   JPanel up,middle,down;
  
   JRadioButton hybrid,electric,other;
   ButtonGroup group;
  
   JLabel mpgLabel,weightLabel;
   JTextField mpg,weight;
  
   JButton computeSalesTax,clearFields,displayReport;
   JLabel output;
  
   //arraylist to store the Automobiles
   List<Automobile> autoMobiles;
  
   //create UI window layout
   AutomobileUI() {
       setTitle("Automobile Sales Tax Calculator");
       
       //initialize all components
       autoMobiles = new ArrayList<>();
       setLayout(null);
       setSize(600,450);
       up = new JPanel(new GridLayout(2,2,10,10));
       middle = new JPanel(new GridLayout(3,3,10,10));
       middle.setBorder(BorderFactory.createTitledBorder("Automobile Type"));
       down = new JPanel(new GridLayout(2,2,10,10));
      
       makeAndModelLabel = new JLabel("Make and Model");
       salesPriceLabel = new JLabel("Sales Price");
       makeAndModel = new JTextField(20);
       salesPrice = new JTextField(20);
       up.add(makeAndModelLabel);
       up.add(makeAndModel);
       up.add(salesPriceLabel);
       up.add(salesPrice);
      
       hybrid = new JRadioButton("Hybrid");
       electric = new JRadioButton("Electric");
       other = new JRadioButton("Other");
       ButtonGroup group = new ButtonGroup();
       group.add(hybrid);
       group.add(electric);
       group.add(other);
      
       mpgLabel = new JLabel("Miles per Gallon");
       weightLabel = new JLabel("Weight in Pounds");
       mpg = new JTextField(20);
       weight = new JTextField(20);
      
       middle.add(hybrid);
       middle.add(mpgLabel);
       middle.add(mpg);
      
       middle.add(electric);
       middle.add(weightLabel);
       middle.add(weight);
      
       middle.add(other);
      
       computeSalesTax = new JButton("Compute Sales tax");
       clearFields = new JButton("Clear Fields");
       displayReport = new JButton("Display Report");
       output = new JLabel("");
       output.setBorder(BorderFactory.createLineBorder(new Color(132,141,149),1));
      
       down.add(computeSalesTax);
       down.add(output);
       down.add(clearFields);
       down.add(displayReport);
      
       up.setBounds(80,30,400,50);
       middle.setBounds(10,100,550,120);
       down.setBounds(60,250,400,80);
       add(up);
       add(middle);
       add(down);
       
       //add actionlisteners
       computeSalesTax.addActionListener(this);
       clearFields.addActionListener(this);
       displayReport.addActionListener(this);
      
       other.addActionListener(this);
       hybrid.addActionListener(this);
       electric.addActionListener(this);
      
       output.setEnabled(false);
       other.doClick();
       output.setForeground(Color.BLUE);
       output.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
       
   	}// end UI window layout and actions
   
  //method to test if the price given is a valid price
   public Integer isValidPrice(String price) {
       try {
           Integer priceValue = Integer.parseInt(price.trim());
           if(priceValue <= 0) {
               priceValue = -1;
           }
           return priceValue;
       }catch(Exception e) {
           return -1;
       }
   }// end check price method
 
   //this method tests if the number provided is a proper integer for MPG and weight purposes
   public Integer isValidInteger(String num) {
       try {
           Integer intValue = Integer.parseInt(num.trim());
           if(intValue <= 0) {
               intValue = -1;
           }
           return intValue;
       }catch(Exception e) {
           return -1;
       }
   }// end check int method
   
  //this method add each entered Automobile into the list for display up to 5
   public void addToList(Automobile mobile) {
      
       if(autoMobiles.size() < 5) {
           autoMobiles.add(mobile);
       }else {
           autoMobiles.remove(0);
           autoMobiles.add(mobile);
       }
   }//end method
   
 //method to save the "other" automobiles into the report
   
   public void saveOtherReport() {
       Integer price = isValidPrice(salesPrice.getText());
       if(price != -1) {
           Automobile mobile = new Automobile(makeAndModel.getText(),price);
           output.setText(String.format("%d",mobile.salesTax()));
           addToList(mobile);
       }else {
           JOptionPane.showMessageDialog(this, "Invalid Sales Price. Please enter an integer Sales Price","ERROR",JOptionPane.ERROR_MESSAGE);
       }
   }//end method
  
 //method to save hybrid automobiles into the report
   
   public void saveHybridReport() {
       Integer price = isValidPrice(salesPrice.getText());
       if(price != -1) {
           Integer mpgValue = isValidInteger(mpg.getText());
           if(mpgValue != -1) {              
               Hybrid mobile = new Hybrid(makeAndModel.getText(),price,mpgValue);
               output.setText(String.format("%d",mobile.salesTax()));
               addToList(mobile);
           }else {
               JOptionPane.showMessageDialog(this, "Invalid MPG. Please enter an integer MPG","ERROR",JOptionPane.ERROR_MESSAGE);
           }
       }else {
           JOptionPane.showMessageDialog(this, "Invalid Sales Price. Please enter an integer Sales Price","ERROR",JOptionPane.ERROR_MESSAGE);
       }
   }//end method
   
 //method to save the "other" automobiles into the report
   
   public void saveElectricReport() {
       Integer price = isValidPrice(salesPrice.getText());
       if(price != -1) {
           Integer weightValue = isValidInteger(weight.getText());
           if(weightValue != -1) {              
               Electric mobile = new Electric(makeAndModel.getText(),price,weightValue);
               output.setText(String.format("%d",mobile.salesTax()));
               addToList(mobile);
           }else {
               JOptionPane.showMessageDialog(this, "Invalid Weight. Please enter an integer weight","ERROR",JOptionPane.ERROR_MESSAGE);
           }
       }else {
           JOptionPane.showMessageDialog(this, "Invalid Sales Price. Please enter an integer Sales Price","ERROR",JOptionPane.ERROR_MESSAGE);
       }
   }//end method
  

        public void actionPerformed(ActionEvent ae) {
            
            if(ae.getSource() == computeSalesTax) {

                if(other.isSelected()) {
                    saveOtherReport();
                }else if(hybrid.isSelected()) {
                    saveHybridReport();
                }else{
                    saveElectricReport();
                }

            }else if(ae.getSource() == clearFields) {
                resetForm();
            }else if(ae.getSource() == displayReport) {
                displayReports();
            }else if(ae.getSource() == other) {
                mpg.setEnabled(false);
                weight.setEnabled(false);
                output.setText("");
                mpg.setText("");
                weight.setText("");
            }
            else if(ae.getSource() == hybrid) {
                mpg.setEnabled(true);
                weight.setEnabled(false);
                output.setText("");
                weight.setText("");
            }
            else if(ae.getSource() == electric) {
                mpg.setEnabled(false);
                weight.setEnabled(true);
                mpg.setText("");
                output.setText("");
            }
        }//end public void actionPerformed(ActionEvent ae)
        
        public void resetForm() {
            makeAndModel.setText("");
            salesPrice.setText("");
            mpg.setText("");
            weight.setText("");
            other.setSelected(true);
            output.setText("");

            other.doClick();
        }// end public void resetForm()
        
        public void displayReports() {      
            String result = "";
            for(Automobile mobile:autoMobiles) {
                result += mobile+"";
            }//end for loop
            JOptionPane.showMessageDialog(this, result, "Query Report", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(result);
        }//end public void displayReports()
    }//endpublic class AutomobileUI extends JFrame implements ActionListener   
       public static void main(String[] args) {
       AutomobileUI mainFrame = new AutomobileUI();
       mainFrame.setVisible(true);
       mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }//end public static void main(String[] args)     
}//end public class CMIS242PRJ2XaireB
