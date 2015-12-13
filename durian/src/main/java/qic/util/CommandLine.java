package qic.util;
   
/* 
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * CommandLine is a representation of the command line arguments passed to 
 * a Java class' main(String[]) method. It parses the arguments for flags 
 * (tokens prefixed with a dash ('-')), flag-value pairs, and an ordered 
 * list of argument values.
 *
 * @author Dan Jemiolo (danj)
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class CommandLine
{
	public CommandLine(String[] args) {
		parse(args);
	}
	
    //
    // all non-flag values
    //
    private String[] _arguments = null;
    
    //
    // the flags (-foo) that were found on the command line
    //
    
	private Map _flags = new HashMap();
    
    //
    // the flag values that are expected to be followed with a value 
    // that allows the application to process the flag.
    //
    private Set _flagsWithValues = new HashSet();
    
    /**
     * 
     * @return All of the values specified on the command line that were 
     *         not a flag or associated with a flag.
     *
     */
    public String[] getArguments()
    {
        return _arguments;
    }
    
    /**
     * 
     * @param flagName
     * 
     * @return The value that was specified after the flag, or null if 
     *         the flag was not specified.
     *
     * @see #hasFlag(String)
     * 
     */
    public String getFlagValue(String flagName)
    {
        return (String)_flags.get(flagName);
    }
    
    public int getNumberOfArguments()
    {
        return _arguments.length;
    }
    
    public int getNumberOfFlags()
    {
        return _flags.size();
    }
    
    /**
     * 
     * @param flagName
     * 
     * @return True if the flag was specified on the command line.
     *
     */
    public boolean hasFlag(String flagName)
    {
        return _flags.containsKey(flagName);
    }
    
    /**
     * 
     * Reads through each argument in the given array, picking out the 
     * flags (and optionally,their values) from the regular arguments. 
     * Users should use the saveFlagValue(String) method before this one 
     * in order to have their command line parsed correctly.
     *
     * @param args
     *        The command line arguments given to the application.
     *
     */
	public void parse(String[] args)
    {
        List regularArgs = new ArrayList();
        
        for (int n = 0; n < args.length; ++n)
        {
            if (args[n].charAt(0) == '-')
            {
                String name = args[n];
                String value = null;
                
                if (_flagsWithValues.contains(args[n]) && 
                    n < args.length - 1)
                    value = args[++n];
                
                _flags.put(name, value);
            }
            
            else
                regularArgs.add(args[n]);
        }
        
        int size = regularArgs.size();
        _arguments = (String[])regularArgs.toArray(new String[size]);
    }

    /**
     * 
     * Tells the command line parser to associate the given flag with the 
     * value that comes after it in the list of arguments (if the flag is 
     * found). This will allow the user to retrieve the flag-value pair 
     * later and prevent the flag value from being lumped in with the 
     * regular arguments.
     *
     * @param flagName
     *
     */
    public void saveFlagValue(String flagName)
    {
        _flagsWithValues.add(flagName);
    }

	public String getArgument(int i) {
		if (getNumberOfArguments() < (i + 1)) {
			return null;
		}
		return getArguments()[i];
	}
}

   