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
class CommonException
{
	error    : string ;
	message  : string ;
	exception: string ;
	path     : string ;
	timestamp: number ;

    /**
	 * Public constructor
	 * @param status with the status code
	 */
	constructor(public status: number) { }
	
	/**
	 * @param status the status to set
	 */
	setStatus(status)
	{
		this.status = status ;
	}
	
	/**
	 * @return the status
	 */
	getStatus()
	{
		return this.status ;
	}
	
	/**
	 * @param error the error to set
	 */
	setError(error)
	{
		this.error = error ;
	}
	
	/**
	 * @return the error
	 */
	getError()
	{
		return this.error ;
	}
	
	/**
	 * @param message the message to set
	 */
	setMessage(message)
	{
		this.message = message ;
	}
	
	/**
	 * @return the message
	 */
	getMessage()
	{
		return this.message ;
	}
	
	/**
	 * @param exception the exception to set
	 */
	setException(exception)
	{
		this.exception = exception ;
	}
	
	/**
	 * @return the exception
	 */
	getException()
	{
		return this.exception ;
	}
	
	/**
	 * @param path the path to set
	 */
	setPath(path)
	{
		this.path = path ;
	}
	
	/**
	 * @return the path
	 */
	getPath()
	{
		return this.path ;
	}
	
	/**
	 * @param timestamp the timestamp to set
	 */
	setTimestamp(timestamp)
	{
		this.timestamp = timestamp ;
	}
	
	/**
	 * @return the timestamp
	 */
	getTimestamp()
	{
		return this.timestamp ;
	}
	
	/**
	 * @return the object as string
	 */
	toString()
	{
		return JSON.stringify(this, null, 4) ;
	}
};
