package com.porty.swing.table.model;

import com.porty.swing.util.DynamicBeanUtils;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 * Bean property model is a table model which shows its rows off the list of beans.
 * Each column corresponds to the property of the bean which could be read. For editing, column
 * should be writable.
 *
 * @author iportyankin
 */
public class BeanPropertyTableModel<T> extends AbstractTableModel {
    /** class of the data in rows */
    private final Class beanClass;
    /** collection of table rows */
    private List<T> data = new ArrayList<T>();
    /** collections of column property descriptors and names */
    private List<PropertyDescriptor> columns = new ArrayList<PropertyDescriptor>();
    private List<String> columnNames = new ArrayList<String>();
    /** resource bundle used to get column names */
    private ResourceBundle resourceBundle;
    /** Prefix for resource bundle column names */
    private String resourcePrefix;
    /** set of properties of exclude */
    private Set<String> excludeProperties = new HashSet<String>();
    /** order of columns for this table */
    private List<String> orderedProperties = new ArrayList<String>();
    /** Order of properties to their table column indexes */
    private Map<String,Integer> propertiesIndexes = new HashMap<String,Integer>();

    public BeanPropertyTableModel(Class beanClass) {
        if (beanClass == null) {
            throw new IllegalArgumentException("Bean class required, cannot be null");
        }
        this.beanClass = beanClass;
        addExcludedProperty("class");
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
        fireTableDataChanged();
    }

    /**
     * Sets resource bundle to get the column names from.
     * @param resourceBundle Bundle with strings
     */
    public void setResourceBundle(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    /**
     *
     * @param resourcePrefix Resource prefix to search 'prefix.propertyName' as column name in the bundle
     */
    public void setResourcePrefix(String resourcePrefix) {
        this.resourcePrefix = resourcePrefix;
    }

    /**
     * Adds property not to show in the table
     * @param propertyName Property name to exclude
     */
    public void addExcludedProperty(String propertyName) {
        excludeProperties.add(propertyName);
    }

    /**
     * Sets order of properties in the table. Not all columns should be included,
     * columns that are not included added as after the ordered columns in undetermined order.
     *
     * @param orderedColumns List which contains order of properties to use when build columns
     */
    public void setOrderedProperties(List<String> orderedProperties) {
        this.orderedProperties = orderedProperties;
    }

    /**
     * Attempts to read ordered column names from plain text file, each column name on separate line.
     * File encoding is UTF-8.
     * All exceptions are converted to unchecked.
     * @param base Base class of resource
     * @param resourceName name of resource file
     */
    public void setOrderedPropertiesFromResource(Class base, String resourceName) {
        List<String> result = new ArrayList<String>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(base.getResourceAsStream(resourceName), "UTF8"));
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
        String nextLine = null;
        try {
            while ((nextLine = reader.readLine()) != null) {
                result.add(nextLine.trim());
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        // use result as ordered list
        setOrderedProperties(result);
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        if ( columns.isEmpty() ) {
            // lazily create the column set
            populateColumns();
        }
        return columns.size();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columns.get(columnIndex).getPropertyType();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PropertyDescriptor descriptor = columns.get(columnIndex);
        T bean = data.get(rowIndex);
        return DynamicBeanUtils.getPropertyValue(bean, descriptor);
    }

    public void populateColumns() {
        // on initialize, parse the class and add all the properties as columns
        BeanInfo info = null;
        try {
            info = Introspector.getBeanInfo(beanClass);
        } catch (IntrospectionException ex) {
            throw new RuntimeException("Unable to introspect dynamic table data class", ex);
        }
        PropertyDescriptor[] pds = info.getPropertyDescriptors();
        int columnIndex = 0;
        // first, add ordered columns
        for (String nextOrderedProp : orderedProperties) {
            for (PropertyDescriptor nextDescriptor : pds) {
                if (nextDescriptor.getName().equals(nextOrderedProp)) {
                    // we've got the property, add it now
                    if (addColumnFromProperty(nextDescriptor)) {
                        propertiesIndexes.put(nextOrderedProp, columnIndex++);
                    }
                }
            }
        }
        for (PropertyDescriptor nextDescriptor : pds) {
            // only add columns that are left unordered
            if (!propertiesIndexes.containsKey(nextDescriptor.getName())) {
                if (addColumnFromProperty(nextDescriptor)) {
                    propertiesIndexes.put(nextDescriptor.getName(), columnIndex++);
                }
            }
        }
    }

    /** Adds new column binding from a given property descriptor */
    protected boolean addColumnFromProperty(PropertyDescriptor descriptor) {
        // check the property can be read and it's not specific types
        if (!excludeProperties.contains(descriptor.getName())
                && descriptor.getReadMethod() != null) {
            columns.add(descriptor);
            columnNames.add(getColumnName(descriptor.getName()));
            return true;
        }
        return false;
    }

    /** Attempts to construct column name depending on bundles available */
    protected String getColumnName(String propertyName) {
        if ( resourceBundle != null && resourcePrefix != null ) {
            // use bundle string if all is set and it's available
            String bundleString = resourcePrefix + "." + propertyName;
            if ( resourceBundle.containsKey(bundleString) )
                return resourceBundle.getString(bundleString);
            else
                Logger.getLogger(getClass().getName()).log(
                        Level.WARNING, "Resource bundle does not contain property name: {0}", bundleString);
        }
        // use just a property name
        return convertPropertyToColumnName(propertyName);
    }

    /**
     * Primitively constructs display name out of Java bean property name
     * @param name Property name in camel case
     * @return Readable name
     */
    protected String convertPropertyToColumnName(String name) {
            // convert to primitive name
            String propertyNameCap = name.substring(0, 1).toUpperCase() + name.substring(1);
            String value = "";
            int lastIdx = 0;
            // i = 1 - skip first uppercase
            for (int i = 1; i < propertyNameCap.length(); i++) {
                if ( Character.isUpperCase(propertyNameCap.charAt(i)) ) {
                    // split with space
                    value = value + propertyNameCap.substring(lastIdx, i) + " ";
                    lastIdx = i;
                }
            }
            // add last portion
            value = value + propertyNameCap.substring(lastIdx, propertyNameCap.length());
            return value;
    }

}
