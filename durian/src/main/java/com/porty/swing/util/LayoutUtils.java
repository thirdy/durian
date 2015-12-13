package com.porty.swing.util;

import java.awt.Dimension;
import java.util.Arrays;
import java.util.Comparator;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

/**
 * Various utilities for making manual layout easier, especially BoxLayout-based blocks based ones.
 *
 * @author iportyankin
 */
public class LayoutUtils {

    // dummy components to get the layout sizes
    private static JLabel dummyComponent = new JLabel("foo");
    private static JTextField dummyTextfield = new JTextField(10);
    private static JPanel dummyContainer = new JPanel();

    /**
     *
     * @return Recommended gap between components and container edges.
     */
    public static int getGenericContainerGap() {
        return LayoutStyle.getInstance().
                getContainerGap(dummyComponent, SwingConstants.NORTH, dummyContainer);
    }

    /**
     *
     * @return Recommended gap between related components in current look and feel.
     */
    public static int getGenericRelatedGap() {
        return LayoutStyle.getInstance().
                getPreferredGap(dummyComponent, dummyTextfield, LayoutStyle.ComponentPlacement.RELATED, SwingConstants.EAST, dummyContainer);
    }

        /**
     *
     * @return Recommended gap between unrelated components in current look and feel.
     */
    public static int getGenericUnrelatedGap() {
        return LayoutStyle.getInstance().
                getPreferredGap(dummyComponent, dummyTextfield, LayoutStyle.ComponentPlacement.UNRELATED, SwingConstants.NORTH, dummyContainer);
    }

    /**
     * Aligns component in the BoxLayout container.
     * @param alignment
     * @param comps
     */
    public static void setSameAlignmentX(float alignment, JComponent... comps) {
        for (JComponent comp: comps) {
            comp.setAlignmentX(alignment);
        }
    }

    /**
     * Makes components use same width as the width of the widest component in the group
     * @param comps List of components to align sizes
     */
    public static void makeSamePreferredWidth(JComponent... comps) {
        if ( comps.length == 0)
            return;
        Arrays.sort(comps, new Comparator<JComponent>() {
            @Override
            public int compare(JComponent o1, JComponent o2) {
                return o1.getPreferredSize().width - o2.getPreferredSize().width;
            }
        });
        int biggest = comps[comps.length - 1].getPreferredSize().width;
        for ( JComponent comp: comps) {
            comp.setPreferredSize(new Dimension(biggest, comp.getPreferredSize().height));
            comp.setMaximumSize(new Dimension(biggest, comp.getMaximumSize().height));
            comp.setMinimumSize(new Dimension(biggest, comp.getMinimumSize().height));
        }
    }
}
