package qic.util;
import static java.awt.Color.blue;
import static java.awt.Cursor.HAND_CURSOR;
import static java.awt.Cursor.getPredefinedCursor;
import static java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment;
import static java.awt.event.KeyEvent.VK_ENTER;
import static javax.swing.Box.createRigidArea;
import static javax.swing.BoxLayout.PAGE_AXIS;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.SwingUtilities.getWindowAncestor;
import static javax.swing.SwingUtilities.invokeAndWait;
import static javax.swing.SwingUtilities.isEventDispatchThread;

import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.InvocationTargetException;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

/**
 * Simple, generic exception handler that could be installed either as an {@link UncaughtExceptionHandler} or used
 * standalone.
 *
 * @author nitanoc
 * @version 1.0, Jan 28, 2014
 * @see http://www.javaspecialists.eu/archive/Issue196.html
 */
public class SimpleExceptionHandler implements UncaughtExceptionHandler {
	
    @Override
    public void uncaughtException(final Thread thread, final Throwable throwable) {
        String formattedMessage = format(thread, throwable);
        if (formattedMessage == null || formattedMessage.trim().length() == 0) {
            formattedMessage = "An error has occurred but no further details are available.";
        }

        if (!getLocalGraphicsEnvironment().isHeadlessInstance()) { // if we are in a GUI / have graphics capabilities...
            if (isEventDispatchThread()) {  // --> when called from listeners...
                reportInGUI(thread, throwable);
            } else {                        // --> when called from outside the EDT...
                try {
                    invokeAndWait(new Runnable() {

                        @Override
                        public void run() {
                            reportInGUI(thread, throwable);
                        }
                    });
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                } catch (InvocationTargetException ite) {
                    ite.getCause().printStackTrace();
                }
            }
        }
    }

    /**
     * Mostly suited for printing a formatted message in consoles and/or logs. As opposed to the static method
     * {@link #simpleFormat(Thread, Throwable)}, this method can be overridden.
     */
    public String format(Thread thread, Throwable throwable) {
        return simpleFormat(thread, throwable);
    }

    /**
     * Should be called only within the Event Dispatch Thread.
     */
    public void reportInGUI(Thread thread, Throwable throwable) {
        JDialog dialog = new SimpleExceptionPane(thread, throwable).createDialog(null, "An error has occurred.");
        dialog.setResizable(true);
        dialog.pack();           // arrange BEFORE making it visible!
        dialog.setVisible(true); // block until an option is selected or the dialog is closed...
        dialog.dispose();        // clean up after closing the dialog
    }

    public static String simpleFormat(Thread thread, Throwable throwable) {
        StringBuilder buffer = new StringBuilder();

        if (thread != null) {
            buffer.append("An error has occurred in thread #").append(thread.getId()).append(": ")
                .append(thread.getName());
        }

        if (throwable != null) {
            StringWriter writer = new StringWriter();
            throwable.printStackTrace(new PrintWriter(writer)); // no need to close PrintWriter wrapping a StringWriter!

            String stackTrace = writer.toString();
            if (stackTrace != null && !"null".equalsIgnoreCase(stackTrace.trim()) && stackTrace.trim().length() != 0) {
                if (buffer.length() == 0) {
                    buffer.append("An error has occurred");
                }
                buffer.append("; please find details below:").append(System.lineSeparator()).append(System.lineSeparator())
                    .append(stackTrace);
            }
        }

        return buffer.toString();
    }

    public static class SimpleExceptionPane extends JOptionPane {

        private static final long serialVersionUID = 1020140128L;

        public SimpleExceptionPane(Thread thread, Throwable throwable) {
            JPanel messagePnl = new JPanel(); // the pane's message is actually a vertically laid panel...
            messagePnl.setLayout(new BoxLayout(messagePnl, PAGE_AXIS));
            messagePnl.add(createRigidArea(new Dimension(0, 10)));

            JLabel genericMessageLbl = new JLabel("An error has occurred:");
            genericMessageLbl.setAlignmentX(LEFT_ALIGNMENT);
            messagePnl.add(genericMessageLbl);

            String exceptionMessage = throwable != null ? throwable.getMessage() : null;
            if (exceptionMessage != null && exceptionMessage.trim().length() != 0) {
                messagePnl.add(createRigidArea(new Dimension(0, 10))); // some spacing before the exception message
                messagePnl.add(new JLabel(exceptionMessage));
            }

            messagePnl.add(createRigidArea(new Dimension(0, 10)));
            String details = simpleFormat(thread, throwable);
            if (details == null || details.length() == 0) {
                JLabel detailsLbl = new JLabel("No further details are available.");
                detailsLbl.setAlignmentX(LEFT_ALIGNMENT);
                messagePnl.add(detailsLbl);
            } else {
                JTextArea detailsTxA = new JTextArea(details, 10, 100);
                detailsTxA.setEditable(false);

                final JComponent detailsCmp =
                    new JScrollPane(detailsTxA, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
                detailsCmp.setAlignmentX(LEFT_ALIGNMENT);
                detailsCmp.setVisible(false);

                final String detailsBtnText = "\u00bb Details";
                final JLabel detailsBtn = new JLabel("<html>" + detailsBtnText);
                detailsBtn.setFocusable(true);
                detailsBtn.setForeground(blue);
                detailsBtn.setAlignmentX(LEFT_ALIGNMENT);
                detailsBtn.setCursor(getPredefinedCursor(HAND_CURSOR));
                detailsBtn.addFocusListener(new FocusListener() {

                    @Override
                    public void focusGained(FocusEvent e) {
                        detailsBtn.setText("<html><u>" + detailsBtnText);
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        detailsBtn.setText("<html>" + detailsBtnText);
                    }

                });
                detailsBtn.addKeyListener(new KeyAdapter() {

                    @Override
                    public void keyPressed(KeyEvent evt) {
                        if (evt.getKeyCode() == VK_ENTER) {
                            toggleDetails(detailsCmp);
                        }
                    }
                });
                detailsBtn.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseReleased(MouseEvent evt) {
                        toggleDetails(detailsCmp);
                    }
                });

                messagePnl.add(detailsBtn);
                messagePnl.add(createRigidArea(new Dimension(0, 10)));
                messagePnl.add(detailsCmp);
            }

            setMessage(messagePnl);
            setMessageType(ERROR_MESSAGE);
            setOptions(new String[]{"Close"});
        }

        protected void toggleDetails(JComponent detailsCmp) {
            detailsCmp.setVisible(!detailsCmp.isVisible());

            Window window = getWindowAncestor(this);
            if (detailsCmp.isVisible()) {
                window.setSize(650, 350);
            } else {
                window.pack();
            }
        }
    }

    /**
     * Simple test bed.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Throwable t) {
            t.printStackTrace();
        }

        new SimpleExceptionHandler().uncaughtException(Thread.currentThread(), new IllegalArgumentException(
            "This is not good! Who you gonna call??? Ghost Busters!"));

        Thread.setDefaultUncaughtExceptionHandler(new SimpleExceptionHandler());
        throw new IllegalArgumentException(new NullPointerException("Stop passing me nulls! ;)"));
    }
}