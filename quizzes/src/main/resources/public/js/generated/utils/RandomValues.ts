/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
class RandomValues
{
    /**
	 * Public constructor
	 */
	constructor() { }
	
	/**
	 * @return a random boolean
	 */
	static generateRandomBoolean()
	{
		return Math.random() >= 0.5 ;
	}
	
	/**
	 * @return a random number
	 */
	static generateRandomNumber()
	{
		return Math.floor((Math.random() * 10) + 1) ;
	}
	
	/**
	 * @return a random string
	 */	
	static generateRandomString()
	{
	    var randomString = "" ;
	    var possible 	 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789" ;
	
	    for(var i=0; i < 5; i++)
	   	{
	        randomString += possible.charAt(Math.floor(Math.random() * possible.length)) ;
		}
		
	    return randomString ;
	}
	
	/**
	 * @return a random file
	 */	
	static generateRandomFile()
	{
        var input: any = document.getElementById('myFile') ;
        return input.files[0];
	}
};
