package qic.ui.extra;

import javax.swing.JTextArea;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

public class JTextAreaAppender extends AppenderSkeleton {
	
	JTextArea textArea = new JTextArea();

    public JTextAreaAppender(JTextArea textArea) {
    	this.textArea = textArea;
	}

	@Override
    protected void append(LoggingEvent event) {
    	String text = textArea.getText();
    	text += event.getRenderedMessage() + System.lineSeparator();
    	textArea.setText(text);
    }

    public void close() {
    }

    public boolean requiresLayout() {
        return false;
    }

}