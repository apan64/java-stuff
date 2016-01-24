/******************************
 * 
 * @author Patrick Atkins
 * CS 350 Project 3
 * March 9 2015
 * CWID: 11495287
 * IntegerField.java
 * Modifies JTextField to create a text field that only accepts integers
 *
 *****************************/

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class IntegerField extends JTextField {

    public IntegerField(String n) {
        super(n);
        }

    public IntegerField( int cols ) {
        super( cols );
        }

    protected Document createDefaultModel() {
        return new UpperCaseDocument();
        }

    static class UpperCaseDocument extends PlainDocument {
    	
        public void insertString( int offs, String str, AttributeSet a )
                throws BadLocationException {

            if ( str == null ) {
            	return;
                }

            char[] chars = str.toCharArray();
            boolean ok = true;

            for ( int i = 0; i < chars.length; i++ ) {

                try {
                    Integer.parseInt( String.valueOf( chars[i] ) );
                    } catch ( NumberFormatException exc ) {
                    	ok = false;
                    	break;
                    	}
            }
            if ( ok )
                super.insertString( offs, new String( chars ), a );
        }
        }
    }