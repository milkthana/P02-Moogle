import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class GUI extends JFrame {
	 public static void main(String[] args) {
		 	//Scanner comment = new Scanner(System.in);
		 	//String re;
		 	//ArrayList<String> arr = new ArrayList<String>();
		 	//for(int i=0;i<6;i++)
		    // {  
		 		//re = comment.nextLine();
		 		//arr.add(re);
		    // }
		    JFrame jf = new JFrame("Moogle");
		    jf.setSize(250, 250);
		    jf.setLocation(300,200);
		    final JTextArea textArea = new JTextArea(10, 40);
		    jf.getContentPane().add(BorderLayout.CENTER, textArea);
		    final JButton button = new JButton("Add Comment");
		    jf.getContentPane().add(BorderLayout.SOUTH, button);
		    button.addActionListener(new ActionListener() {

		       // @SuppressWarnings("unused")
				@Override
		        public void actionPerformed(ActionEvent e) {
		        	//if(arr!=null) {
		        	textArea.append("\nYour comment was added\n");
		            textArea.append("\nThank you for your comment\n");
		        	//}
		        	//else if(arr==null) {
		        	//textArea.append("\nYour comment was not appeared\n");
		        	//}
		        	
		        }
		    });

		    jf.setVisible(true);

		  }

}
