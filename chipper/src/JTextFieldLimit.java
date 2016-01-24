/******************************
 * 
 * @author Patrick Atkins
 * CS 350 Project 3
 * March 9 2015
 * CWID: 11495287
 * JTextFieldLimit.java
 * Modifies the document used by JTextField to limit the number of characters a text field can contain
 *
 *****************************/

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class JTextFieldLimit extends PlainDocument {
  private int limit;
  JTextFieldLimit(int limit) {
    super();
    this.limit = limit;
    }

  JTextFieldLimit(int limit, boolean upper) {
    super();
    this.limit = limit;
    }

  public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
    if (str == null)
      return;

    if ((getLength() + str.length()) <= limit) {
      super.insertString(offset, str, attr);
    }
  }
}