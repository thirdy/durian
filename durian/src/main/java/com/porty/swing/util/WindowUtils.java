package com.porty.swing.util;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;

/**
 * Windows related utilities.
 *
 * @author iportyankin
 */
public class WindowUtils {

    /**
     * <p/>
     * Returns the <code>Point</code> at which a window should be placed to
     * center that window on the given desktop.
     * </p>
     * <p/>
     * Some thought was taken as to whether to implement a method such as this,
     * or to simply make a method that, given a window, will center it.  It was
     * decided that it is better to not alter an object within a method.
     * </p>
     *
     * @param window  The window (JInternalFrame) to calculate the center point
     *                for.  This object can not be null.
     *
     * @return the <code>Point</code> at which the window should be placed to
     *         center that window on the given desktop
     */
    public static Point getPointForCentering(Window window) {
        try {
            //assert window != null;
            Point mousePoint = MouseInfo.getPointerInfo().getLocation();
            GraphicsDevice[] devices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
            for (GraphicsDevice device : devices) {
                Rectangle bounds = device.getDefaultConfiguration().getBounds();
                //check to see if the mouse cursor is within these bounds
                if (mousePoint.x >= bounds.x && mousePoint.y >= bounds.y
                        && mousePoint.x <= (bounds.x + bounds.width)
                        && mousePoint.y <= (bounds.y + bounds.height)) {
                    //this is it
                    int screenWidth = bounds.width;
                    int screenHeight = bounds.height;
                    int width = window.getWidth();
                    int height = window.getHeight();
                    return new Point(((screenWidth - width) / 2) + bounds.x,
                            ((screenHeight - height) / 2) + bounds.y);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("unable to access window bounds", e);
        }
        return new Point(0, 0);
    }

    public static void centerWindow(Window frame) {
        frame.setLocation(getPointForCentering(frame));
    }

	public static Rectangle getScreenRect() {
		GraphicsDevice[] devices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        for (GraphicsDevice device : devices) {
    		Rectangle bounds = device.getDefaultConfiguration().getBounds();
    		return bounds;	
        }
        return new Rectangle();
	}
}
