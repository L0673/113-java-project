import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.*;
import java.io.*;
public class FlowerStore extends JFrame  {

   final private Font mainFont = new Font("Segoe UI Emoji", Font.BOLD,18); //vairble for font
   static JTextField tfFirstName, tfId; //text field
   JLabel lbwelcome;
   static Order order;

   public void initializeinput() {
   	//form panel
      JLabel lbFirstName = new JLabel("✿name ");
      lbFirstName.setFont(mainFont);
   
      tfFirstName=new JTextField();
      tfFirstName.setFont(mainFont);
   
      JLabel lbId = new JLabel("✿ID"); 
      lbId.setFont(mainFont);
   
      tfId=new JTextField();
      tfId.setFont(mainFont);	
   
      JPanel formPenel = new JPanel();
      formPenel.setLayout(new GridLayout(4,1,5,5));
      formPenel.setOpaque(false);
      formPenel.add(lbFirstName);
      formPenel.add(tfFirstName);
      formPenel.add(lbId);
      formPenel.add(tfId);
   
   	//welcome label
      lbwelcome = new JLabel();
      lbwelcome.setFont(mainFont);
      lbwelcome.setHorizontalAlignment(SwingConstants.CENTER); 
   
   	//bottom
      JButton btnNext = new JButton("Next");
      btnNext.setFont(mainFont);
      btnNext.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String name = tfFirstName.getText();
               lbwelcome.setText("❀✿ welcome "+ name + " ✿❀");
            }
         });
   
      
      order = new Order();// make new order after adding new customer info
   
      JButton btnClear = new JButton("Clear");
      btnClear.setFont(mainFont);
      btnClear.addActionListener(
         new ActionListener() {
         
            @Override
            public void actionPerformed(ActionEvent e) {
               tfFirstName.setText("");
               tfId.setText("");
               lbwelcome.setText("");
            }	
         });
   
      JPanel buttonsPanel=new JPanel();
      buttonsPanel.setLayout(new GridLayout(1,2,5,5));
      buttonsPanel.setOpaque(false);
      buttonsPanel.add(btnClear);
      buttonsPanel.add(btnNext);
   	
      JPanel mainPanel = new JPanel();
      mainPanel.setLayout(new BorderLayout());
      mainPanel.setBackground(new Color(255,214,214));
      mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
      mainPanel.add(formPenel, BorderLayout.NORTH);
      mainPanel.add(lbwelcome, BorderLayout.CENTER);
      mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
   
      add(mainPanel);  //add mainPanel to JFrame

	// set icon	 
      ImageIcon imageIcon = new ImageIcon("113-java-project\\113-project\\src\\FlowerImage.png");
      setIconImage(imageIcon.getImage());
   
      setTitle(" ‧₊˚❀༉‧₊˚.FLOWER STORE‧₊˚❀༉‧₊˚.");
      setSize(280,300);
      setMinimumSize(new Dimension(30,100));
      setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); 
      setVisible(true);
   }

   public static void main(String[] args) throws choiceNumberException {
   
      FlowerStore InputFrame = new FlowerStore();
      InputFrame.initializeinput();
      InputFrame.getDefaultCloseOperation();
   
      Scanner input = new Scanner(System.in);
      int costumerchoise;
      String chooseAnotherOption;
   
      do {
         System.out.println("\nEnter your menu option:");
         System.out.println("1-Add Item");
         System.out.println("2-Remove Item");
         System.out.println("3-is There a ChoclateBox?");
         System.out.println("4-Calculate Total price ");
         System.out.println("5-Disply receipt");
         System.out.println("6-to make a readble copy of your receipt");
         System.out.println("7-Exite");
         System.out.println("your choice is:");
         costumerchoise = input.nextInt();
      
         switch (costumerchoise) {
            case 1:
            // Add item
               System.out.println("Do you want to add Flowers 'F' or ChocolateBox 'C': ");
               char typeOrder = input.next().charAt(0);
            
               if (Character.toUpperCase(typeOrder) == 'F') {
               
                  int typeOfFlower;
                  int numOfFlowers = 0;
                  Items it1;
               
                  boolean repeat=true;
                  while(repeat){
                     try{
                        System.out.println("Enter the type each number represents a flower type #1 Rose, 2# Lily, 3# Blossom: ");
                        typeOfFlower = input.nextInt();
                        checkInputNumber(typeOfFlower);
                        it1 = new Flower(typeOfFlower, numOfFlowers);
                     
                        if (order.addItem(it1))
                           System.out.println("Added flower successfully\n");
                        else
                           System.out.println("Sorry, we can't add the flower\n");
                        repeat=false;	
                     }catch(choiceNumberException e){
                        System.err.println("try again");
                     }	
                  }
               } // end if
               
               else if (Character.toUpperCase(typeOrder) == 'C') {
               
                  System.out.println("Enter size (L)or(M)or(S):");
                  char sizeOfChocolate = input.next().charAt(0);
                  Items it2 = new ChocolateBox(sizeOfChocolate);
                  if (order.addItem(it2))
                     System.out.println("Added ChocolateBox successfully");
                  else
                     System.out.println(" Sorry, we can't add the Chocolate Box");
               } // end if
               break;
         
            case 2:
            // remove item
               System.out.println(
                  "Enter the item number that you want to remove: 1#Rose, 2#Lily, 3#Blossom or 4#ChocolateBox ");
               int removeItem = input.nextInt();
               if (order.removeItem(removeItem))
                  System.out.println("Removed succssfuly.\n");
               else
                  System.out.println("Sorry, we can't remove it.\n");
               break;
         
            case 3:
            // search item
               System.out.println("Checking for a ChocolateBox.....");
               String searchItem ="ChocolateBox";
               if (order.searchChocolateBox(searchItem))
                  System.out.println("Yes, there is a Chocolate Box.\n");
               else
                  System.out.println("No, there is no Chocolate Box.\n");
               break;
         
            case 4:
            // calculate Total price
               double total = 0;
               total = order.calculateTotalprice();
               System.out.println("The total price is: " + total + " SR");
               break;
         
            case 5:
             // Display receipt
               FlowerStore OutputFrame = new FlowerStore();// Create an instance of FlowerStore
               OutputFrame.initializeOutput(order, tfFirstName.getText(), tfId.getText());
               try {
				order.writeFile(); // mothod to write the items and save it in orderinfo.ser
			} catch (IOException e) {
				e.printStackTrace();
			} 
               break;
         
            case 6:
                  order.readFile();   // method to read and display items in text file         
               break;

			case 7:
			System.out.println("see you soon, bey");
			System.exit(0);  
         
            default:
               System.out.println("invaild input\n");
         
         } // end switch
      
         System.out.println("Do you want to select another option?");
         System.out.println("Enter yes or 7 to exite: "); // what if it is another input  
         chooseAnotherOption = input.next();
      
      } // end do
      while (costumerchoise != 7 && chooseAnotherOption.equalsIgnoreCase("yes"));
	  System.out.println("see you soon, bey");
	  System.exit(0);  
      input.close();
   } // end main


   private static void checkInputNumber(int typeOfFlower) {
      if ( typeOfFlower == 1 || typeOfFlower == 2 || typeOfFlower == 3 )
         return;
      throw new choiceNumberException("you can enter that, choice 1 or 2 or 3: ");
   }


   public void initializeOutput(Order order, String name, String id) {
   	// Labels
      JLabel thxlabel = new JLabel();
      thxlabel.setText("❀ Thanks for shopping ❀");
      thxlabel.setFont(mainFont);
      thxlabel.setHorizontalAlignment(SwingConstants.CENTER);
   
      JLabel nameLabel = new JLabel("Name: " + (name != null ? name : ""));
      nameLabel.setFont(mainFont);
      
      JLabel idLabel = new JLabel(", ID: " + (id != null ? id : ""));
      idLabel.setFont(mainFont);
   
      JLabel SULabel = new JLabel();
      SULabel.setText("See you soon <3");
      SULabel.setFont(mainFont);
      SULabel.setHorizontalAlignment(SwingConstants.CENTER);
      
      JLabel Conformlabel = new JLabel();
      Conformlabel.setText("✓ we've successfully saved your receipt to a file, you can chose option 6 to be able to reaad it ");
      Conformlabel.setFont(mainFont);
      Conformlabel.setHorizontalAlignment(SwingConstants.CENTER);
      
   	// Panels 
      JPanel nameIdPanel = new JPanel();
      nameIdPanel.setLayout(new FlowLayout());
      nameIdPanel.add(nameLabel);
      nameIdPanel.add(idLabel);
   
      JPanel mainPanel = new JPanel();
      mainPanel.setLayout(new BorderLayout());
      mainPanel.setBackground(new Color(255, 214, 214));
      mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
      mainPanel.add(thxlabel, BorderLayout.NORTH);
      mainPanel.add(nameIdPanel, BorderLayout.CENTER);
   	
      JPanel EndOfPagePanel = new JPanel();
      EndOfPagePanel.setLayout(new BorderLayout());
      EndOfPagePanel.setBackground(new Color(255, 214, 214));
      EndOfPagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
      EndOfPagePanel.add(Conformlabel, BorderLayout.NORTH);
      EndOfPagePanel.add(SULabel, BorderLayout.SOUTH);

   	// Frame
      JFrame OutputFrame = new JFrame();
      ImageIcon imageIcon = new ImageIcon("113-java-project\\113-project\\src\\FlowerImage.png");
      OutputFrame.setIconImage(imageIcon.getImage());
      OutputFrame.setTitle(" ‧₊˚❀༉‧₊˚.FLOWER STORE‧₊˚❀༉‧₊˚.");
      OutputFrame.setSize(400, 200);
      OutputFrame.setMinimumSize(new Dimension(400, 200));
      OutputFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      OutputFrame.setVisible(true);
      OutputFrame.add(mainPanel, BorderLayout.NORTH);// add mainPanel to JFrame
      OutputFrame.add(EndOfPagePanel, BorderLayout.CENTER);//add EndOfPagePanel to JFrame
      OutputFrame.pack();
   }	
} // end class