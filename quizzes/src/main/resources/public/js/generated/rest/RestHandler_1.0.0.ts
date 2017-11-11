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

/// <reference path="../model/Quiz_1.0.0.ts" /> ;
/// <reference path="RestListener_1.0.0.ts" /> ;

module javaquiz_api
{
    export class RestHandler
    {
        baseURI: string ;

        constructor(baseURI?: string)
        {
            if (baseURI == null || baseURI == "")
            {
                this.baseURI = "localhost:8080" ;
            }
            else
            {
                this.baseURI = baseURI ;
            }
        }

        getBaseURI(scheme: string)
        {
            return scheme + "://" + this.baseURI + "/" ;
        }

	/**
	 *
     * @param adminKey the admin key
	 */
	fullAnswers(listener: RestListener, scheme: string, adminKey: string)
	{
		// Local Reference of this class
		var self = this ;
		
		// Validate the input scheme
		var isValidInputScheme = this.fullAnswersValidateScheme(scheme) ;
		if (!isValidInputScheme)
		{
			var invalidInputScheme = 400 ;
			var exceptionError	   = "Javascript Client contains an invalid input scheme: '" + scheme + "'. Please use one of the defined in the specification." ;
			var exceptionMessage   = exceptionError ;
			var stackTraceError    ;
			var path   			   ;
			var timestamp		   ;
					
            var outcome = this.generateCommonException(invalidInputScheme, exceptionError, exceptionMessage, stackTraceError, path, timestamp) ;
			listener.fullAnswersCommonException(outcome) ;
		}
		else
		{
			// URL with scheme
			var urlWithScheme	 = this.getBaseURI(scheme) + "api/answers/" ;

			// Replace every path parameter with the input value
			var urlWithPaths     = this.fullAnswersPathAssignment(urlWithScheme) ;
			
			// Replace every query parameter with the input value
			var queryParameters  = this.fullAnswersQueryAssignment(adminKey) ;
			
			// Assign a final URL with all the parameters assigned to
			var fullURL			 = urlWithPaths + queryParameters ;
			
			// Set the header parameters
			var headerParameters = this.fullAnswersHeaderAssignment() ;
	
			// Verify if there is any body or one or many formData parameter
			var formDataInstance ;
			// Promise example
			var promiseFunction  = $.when($.ajax({type: "GET",
												  url: fullURL,
												  headers: headerParameters,
												  data: formDataInstance,
												  processData: false,
												  contentType: "application/json" })) ;
	
			// Create the observable
			var source 			 = Rx.Observable.fromPromise(promiseFunction) ;
	
			// Create the subscription
			var subscription 	 = source.subscribe(function (value)
											   	    {
														var outcome ;
														if (value != null && value != "undefined")
														{
	    									  		 		outcome = [] ;
	
		    									  		 	var length1 = value.length ;
		    									  		 	for (var i1=0 ; i1 < length1 ; i1++)
		    									  		 	{
																outcome[i1] = [] ;
										  		 				outcome[i1] = Quiz.parse(value[i1]) ;
															}
														}
														
	    									  		 	listener.fullAnswers(outcome) ;
											   	    },
											   	    function (error)
											   	    {
											   	    	var outcome ;
														var value = error.responseJSON ;
														outcome = self.generateCommonExceptionFromErrorInstance(error.status, value) ;
														listener.fullAnswersCommonException(outcome) ;
											   	    }) ;
		}
	}
	
	/**
	 * Validate the input scheme with the expected for this 
	 */
	fullAnswersValidateScheme(scheme)
	{
		return scheme == "http" ;
	}

	/**
	 * The following method assign every path parameter with the value
     */
	fullAnswersPathAssignment(urlWithScheme: string)
	{

		return urlWithScheme ;
	}
	
	/**
	 * The following method assign every query parameter with the value
     */	
	fullAnswersQueryAssignment(adminKey: string)
	{
		var outcome = "?" ;
		
		if (outcome != "?")
		{
			outcome = outcome + "&" ;
		}
		
		outcome = outcome + "adminKey={adminKey}" ;

		outcome = outcome.replace("{adminKey}", "" + adminKey) ;

		return outcome ;
	}
	
	/**
	 * The following method assign every header parameter with the value
     */	
	fullAnswersHeaderAssignment()
	{
		var headerParameters = {} ;
		

		return headerParameters ;
	}


	/**
	 *
     * @param adminKey the admin key
	 */
	setAdminKey(listener: RestListener, scheme: string, adminKey: string)
	{
		// Local Reference of this class
		var self = this ;
		
		// Validate the input scheme
		var isValidInputScheme = this.setAdminKeyValidateScheme(scheme) ;
		if (!isValidInputScheme)
		{
			var invalidInputScheme = 400 ;
			var exceptionError	   = "Javascript Client contains an invalid input scheme: '" + scheme + "'. Please use one of the defined in the specification." ;
			var exceptionMessage   = exceptionError ;
			var stackTraceError    ;
			var path   			   ;
			var timestamp		   ;
					
            var outcome = this.generateCommonException(invalidInputScheme, exceptionError, exceptionMessage, stackTraceError, path, timestamp) ;
			listener.setAdminKeyCommonException(outcome) ;
		}
		else
		{
			// URL with scheme
			var urlWithScheme	 = this.getBaseURI(scheme) + "api/adminKey/" ;

			// Replace every path parameter with the input value
			var urlWithPaths     = this.setAdminKeyPathAssignment(urlWithScheme) ;
			
			// Replace every query parameter with the input value
			var queryParameters  = this.setAdminKeyQueryAssignment(adminKey) ;
			
			// Assign a final URL with all the parameters assigned to
			var fullURL			 = urlWithPaths + queryParameters ;
			
			// Set the header parameters
			var headerParameters = this.setAdminKeyHeaderAssignment() ;
	
			// Verify if there is any body or one or many formData parameter
			var formDataInstance ;
			// Promise example
			var promiseFunction  = $.when($.ajax({type: "POST",
												  url: fullURL,
												  headers: headerParameters,
												  data: formDataInstance,
												  processData: false,
												  contentType: "application/json" })) ;
	
			// Create the observable
			var source 			 = Rx.Observable.fromPromise(promiseFunction) ;
	
			// Create the subscription
			var subscription 	 = source.subscribe(function (value)
											   	    {
														listener.setAdminKey() ;
											   	    },
											   	    function (error)
											   	    {
											   	    	var outcome ;
														var value = error.responseJSON ;
														outcome = self.generateCommonExceptionFromErrorInstance(error.status, value) ;
														listener.setAdminKeyCommonException(outcome) ;
											   	    }) ;
		}
	}
	
	/**
	 * Validate the input scheme with the expected for this 
	 */
	setAdminKeyValidateScheme(scheme)
	{
		return scheme == "http" ;
	}

	/**
	 * The following method assign every path parameter with the value
     */
	setAdminKeyPathAssignment(urlWithScheme: string)
	{

		return urlWithScheme ;
	}
	
	/**
	 * The following method assign every query parameter with the value
     */	
	setAdminKeyQueryAssignment(adminKey: string)
	{
		var outcome = "?" ;
		
		if (outcome != "?")
		{
			outcome = outcome + "&" ;
		}
		
		outcome = outcome + "adminKey={adminKey}" ;

		outcome = outcome.replace("{adminKey}", "" + adminKey) ;

		return outcome ;
	}
	
	/**
	 * The following method assign every header parameter with the value
     */	
	setAdminKeyHeaderAssignment()
	{
		var headerParameters = {} ;
		

		return headerParameters ;
	}


	/**
	 *
     * @param adminKey the admin key
     * @param candidate the candidate
	 */
	existsCandidate(listener: RestListener, scheme: string, adminKey: string, candidate: string)
	{
		// Local Reference of this class
		var self = this ;
		
		// Validate the input scheme
		var isValidInputScheme = this.existsCandidateValidateScheme(scheme) ;
		if (!isValidInputScheme)
		{
			var invalidInputScheme = 400 ;
			var exceptionError	   = "Javascript Client contains an invalid input scheme: '" + scheme + "'. Please use one of the defined in the specification." ;
			var exceptionMessage   = exceptionError ;
			var stackTraceError    ;
			var path   			   ;
			var timestamp		   ;
					
            var outcome = this.generateCommonException(invalidInputScheme, exceptionError, exceptionMessage, stackTraceError, path, timestamp) ;
			listener.existsCandidateCommonException(outcome) ;
		}
		else
		{
			// URL with scheme
			var urlWithScheme	 = this.getBaseURI(scheme) + "api/candidate/" ;

			// Replace every path parameter with the input value
			var urlWithPaths     = this.existsCandidatePathAssignment(urlWithScheme) ;
			
			// Replace every query parameter with the input value
			var queryParameters  = this.existsCandidateQueryAssignment(adminKey, candidate) ;
			
			// Assign a final URL with all the parameters assigned to
			var fullURL			 = urlWithPaths + queryParameters ;
			
			// Set the header parameters
			var headerParameters = this.existsCandidateHeaderAssignment() ;
	
			// Verify if there is any body or one or many formData parameter
			var formDataInstance ;
			// Promise example
			var promiseFunction  = $.when($.ajax({type: "GET",
												  url: fullURL,
												  headers: headerParameters,
												  data: formDataInstance,
												  processData: false,
												  contentType: "application/json" })) ;
	
			// Create the observable
			var source 			 = Rx.Observable.fromPromise(promiseFunction) ;
	
			// Create the subscription
			var subscription 	 = source.subscribe(function (value)
											   	    {
														var outcome ;
														if (value != null && value != "undefined")
														{
															outcome = ParserValues.booleanParser(value) ;
														}
														
	    									  		 	listener.existsCandidate(outcome) ;
											   	    },
											   	    function (error)
											   	    {
											   	    	var outcome ;
														var value = error.responseJSON ;
														outcome = self.generateCommonExceptionFromErrorInstance(error.status, value) ;
														listener.existsCandidateCommonException(outcome) ;
											   	    }) ;
		}
	}
	
	/**
	 * Validate the input scheme with the expected for this 
	 */
	existsCandidateValidateScheme(scheme)
	{
		return scheme == "http" ;
	}

	/**
	 * The following method assign every path parameter with the value
     */
	existsCandidatePathAssignment(urlWithScheme: string)
	{

		return urlWithScheme ;
	}
	
	/**
	 * The following method assign every query parameter with the value
     */	
	existsCandidateQueryAssignment(adminKey: string, candidate: string)
	{
		var outcome = "?" ;
		
		if (outcome != "?")
		{
			outcome = outcome + "&" ;
		}
		
		outcome = outcome + "adminKey={adminKey}" ;

		if (outcome != "?")
		{
			outcome = outcome + "&" ;
		}
		
		outcome = outcome + "candidate={candidate}" ;

		outcome = outcome.replace("{adminKey}", "" + adminKey) ;
		outcome = outcome.replace("{candidate}", "" + candidate) ;

		return outcome ;
	}
	
	/**
	 * The following method assign every header parameter with the value
     */	
	existsCandidateHeaderAssignment()
	{
		var headerParameters = {} ;
		

		return headerParameters ;
	}


	/**
	 *
     * @param answerId The answer identifier
     * @param adminKey the admin key
	 */
	specificAnswer(listener: RestListener, scheme: string, answerId: number, adminKey: string)
	{
		// Local Reference of this class
		var self = this ;
		
		// Validate the input scheme
		var isValidInputScheme = this.specificAnswerValidateScheme(scheme) ;
		if (!isValidInputScheme)
		{
			var invalidInputScheme = 400 ;
			var exceptionError	   = "Javascript Client contains an invalid input scheme: '" + scheme + "'. Please use one of the defined in the specification." ;
			var exceptionMessage   = exceptionError ;
			var stackTraceError    ;
			var path   			   ;
			var timestamp		   ;
					
            var outcome = this.generateCommonException(invalidInputScheme, exceptionError, exceptionMessage, stackTraceError, path, timestamp) ;
			listener.specificAnswerCommonException(outcome) ;
		}
		else
		{
			// URL with scheme
			var urlWithScheme	 = this.getBaseURI(scheme) + "api/answers/{answerId}/" ;

			// Replace every path parameter with the input value
			var urlWithPaths     = this.specificAnswerPathAssignment(urlWithScheme, answerId) ;
			
			// Replace every query parameter with the input value
			var queryParameters  = this.specificAnswerQueryAssignment(adminKey) ;
			
			// Assign a final URL with all the parameters assigned to
			var fullURL			 = urlWithPaths + queryParameters ;
			
			// Set the header parameters
			var headerParameters = this.specificAnswerHeaderAssignment() ;
	
			// Verify if there is any body or one or many formData parameter
			var formDataInstance ;
			// Promise example
			var promiseFunction  = $.when($.ajax({type: "GET",
												  url: fullURL,
												  headers: headerParameters,
												  data: formDataInstance,
												  processData: false,
												  contentType: "application/json" })) ;
	
			// Create the observable
			var source 			 = Rx.Observable.fromPromise(promiseFunction) ;
	
			// Create the subscription
			var subscription 	 = source.subscribe(function (value)
											   	    {
														var outcome ;
														if (value != null && value != "undefined")
														{
	    									  		 		outcome = [] ;
	
		    									  		 	var length1 = value.length ;
		    									  		 	for (var i1=0 ; i1 < length1 ; i1++)
		    									  		 	{
																outcome[i1] = [] ;
										  		 				outcome[i1] = Quiz.parse(value[i1]) ;
															}
														}
														
	    									  		 	listener.specificAnswer(outcome) ;
											   	    },
											   	    function (error)
											   	    {
											   	    	var outcome ;
														var value = error.responseJSON ;
														outcome = self.generateCommonExceptionFromErrorInstance(error.status, value) ;
														listener.specificAnswerCommonException(outcome) ;
											   	    }) ;
		}
	}
	
	/**
	 * Validate the input scheme with the expected for this 
	 */
	specificAnswerValidateScheme(scheme)
	{
		return scheme == "http" ;
	}

	/**
	 * The following method assign every path parameter with the value
     */
	specificAnswerPathAssignment(urlWithScheme: string, answerId: number)
	{
		urlWithScheme = urlWithScheme.replace("{answerId}", "" + answerId) ;

		return urlWithScheme ;
	}
	
	/**
	 * The following method assign every query parameter with the value
     */	
	specificAnswerQueryAssignment(adminKey: string)
	{
		var outcome = "?" ;
		
		if (outcome != "?")
		{
			outcome = outcome + "&" ;
		}
		
		outcome = outcome + "adminKey={adminKey}" ;

		outcome = outcome.replace("{adminKey}", "" + adminKey) ;

		return outcome ;
	}
	
	/**
	 * The following method assign every header parameter with the value
     */	
	specificAnswerHeaderAssignment()
	{
		var headerParameters = {} ;
		

		return headerParameters ;
	}


	/**
	 *
     * @param adminKey the admin key
     * @param candidate the candidate
	 */
	generateQuiz(listener: RestListener, scheme: string, adminKey: string, candidate: string)
	{
		// Local Reference of this class
		var self = this ;
		
		// Validate the input scheme
		var isValidInputScheme = this.generateQuizValidateScheme(scheme) ;
		if (!isValidInputScheme)
		{
			var invalidInputScheme = 400 ;
			var exceptionError	   = "Javascript Client contains an invalid input scheme: '" + scheme + "'. Please use one of the defined in the specification." ;
			var exceptionMessage   = exceptionError ;
			var stackTraceError    ;
			var path   			   ;
			var timestamp		   ;
					
            var outcome = this.generateCommonException(invalidInputScheme, exceptionError, exceptionMessage, stackTraceError, path, timestamp) ;
			listener.generateQuizCommonException(outcome) ;
		}
		else
		{
			// URL with scheme
			var urlWithScheme	 = this.getBaseURI(scheme) + "api/quiz/" ;

			// Replace every path parameter with the input value
			var urlWithPaths     = this.generateQuizPathAssignment(urlWithScheme) ;
			
			// Replace every query parameter with the input value
			var queryParameters  = this.generateQuizQueryAssignment(adminKey, candidate) ;
			
			// Assign a final URL with all the parameters assigned to
			var fullURL			 = urlWithPaths + queryParameters ;
			
			// Set the header parameters
			var headerParameters = this.generateQuizHeaderAssignment() ;
	
			// Verify if there is any body or one or many formData parameter
			var formDataInstance ;
			// Promise example
			var promiseFunction  = $.when($.ajax({type: "GET",
												  url: fullURL,
												  headers: headerParameters,
												  data: formDataInstance,
												  processData: false,
												  contentType: "application/json" })) ;
	
			// Create the observable
			var source 			 = Rx.Observable.fromPromise(promiseFunction) ;
	
			// Create the subscription
			var subscription 	 = source.subscribe(function (value)
											   	    {
														var outcome ;
														if (value != null && value != "undefined")
														{
	    									  		 		outcome = [] ;
	
		    									  		 	var length1 = value.length ;
		    									  		 	for (var i1=0 ; i1 < length1 ; i1++)
		    									  		 	{
																outcome[i1] = [] ;
										  		 				outcome[i1] = Quiz.parse(value[i1]) ;
															}
														}
														
	    									  		 	listener.generateQuiz(outcome) ;
											   	    },
											   	    function (error)
											   	    {
											   	    	var outcome ;
														var value = error.responseJSON ;
														outcome = self.generateCommonExceptionFromErrorInstance(error.status, value) ;
														listener.generateQuizCommonException(outcome) ;
											   	    }) ;
		}
	}
	
	/**
	 * Validate the input scheme with the expected for this 
	 */
	generateQuizValidateScheme(scheme)
	{
		return scheme == "http" ;
	}

	/**
	 * The following method assign every path parameter with the value
     */
	generateQuizPathAssignment(urlWithScheme: string)
	{

		return urlWithScheme ;
	}
	
	/**
	 * The following method assign every query parameter with the value
     */	
	generateQuizQueryAssignment(adminKey: string, candidate: string)
	{
		var outcome = "?" ;
		
		if (outcome != "?")
		{
			outcome = outcome + "&" ;
		}
		
		outcome = outcome + "adminKey={adminKey}" ;

		if (outcome != "?")
		{
			outcome = outcome + "&" ;
		}
		
		outcome = outcome + "candidate={candidate}" ;

		outcome = outcome.replace("{adminKey}", "" + adminKey) ;
		outcome = outcome.replace("{candidate}", "" + candidate) ;

		return outcome ;
	}
	
	/**
	 * The following method assign every header parameter with the value
     */	
	generateQuizHeaderAssignment()
	{
		var headerParameters = {} ;
		

		return headerParameters ;
	}


	/**
	 *
     * @param adminKey the admin key
     * @param candidate the candidate
	 */
	rememberQuiz(listener: RestListener, scheme: string, adminKey: string, candidate: string)
	{
		// Local Reference of this class
		var self = this ;
		
		// Validate the input scheme
		var isValidInputScheme = this.rememberQuizValidateScheme(scheme) ;
		if (!isValidInputScheme)
		{
			var invalidInputScheme = 400 ;
			var exceptionError	   = "Javascript Client contains an invalid input scheme: '" + scheme + "'. Please use one of the defined in the specification." ;
			var exceptionMessage   = exceptionError ;
			var stackTraceError    ;
			var path   			   ;
			var timestamp		   ;
					
            var outcome = this.generateCommonException(invalidInputScheme, exceptionError, exceptionMessage, stackTraceError, path, timestamp) ;
			listener.rememberQuizCommonException(outcome) ;
		}
		else
		{
			// URL with scheme
			var urlWithScheme	 = this.getBaseURI(scheme) + "api/quiz/" ;

			// Replace every path parameter with the input value
			var urlWithPaths     = this.rememberQuizPathAssignment(urlWithScheme) ;
			
			// Replace every query parameter with the input value
			var queryParameters  = this.rememberQuizQueryAssignment(adminKey, candidate) ;
			
			// Assign a final URL with all the parameters assigned to
			var fullURL			 = urlWithPaths + queryParameters ;
			
			// Set the header parameters
			var headerParameters = this.rememberQuizHeaderAssignment() ;
	
			// Verify if there is any body or one or many formData parameter
			var formDataInstance ;
			// Promise example
			var promiseFunction  = $.when($.ajax({type: "PATCH",
												  url: fullURL,
												  headers: headerParameters,
												  data: formDataInstance,
												  processData: false,
												  contentType: "application/json" })) ;
	
			// Create the observable
			var source 			 = Rx.Observable.fromPromise(promiseFunction) ;
	
			// Create the subscription
			var subscription 	 = source.subscribe(function (value)
											   	    {
														var outcome ;
														if (value != null && value != "undefined")
														{
	    									  		 		outcome = [] ;
	
		    									  		 	var length1 = value.length ;
		    									  		 	for (var i1=0 ; i1 < length1 ; i1++)
		    									  		 	{
																outcome[i1] = [] ;
										  		 				outcome[i1] = Quiz.parse(value[i1]) ;
															}
														}
														
	    									  		 	listener.rememberQuiz(outcome) ;
											   	    },
											   	    function (error)
											   	    {
											   	    	var outcome ;
														var value = error.responseJSON ;
														outcome = self.generateCommonExceptionFromErrorInstance(error.status, value) ;
														listener.rememberQuizCommonException(outcome) ;
											   	    }) ;
		}
	}
	
	/**
	 * Validate the input scheme with the expected for this 
	 */
	rememberQuizValidateScheme(scheme)
	{
		return scheme == "http" ;
	}

	/**
	 * The following method assign every path parameter with the value
     */
	rememberQuizPathAssignment(urlWithScheme: string)
	{

		return urlWithScheme ;
	}
	
	/**
	 * The following method assign every query parameter with the value
     */	
	rememberQuizQueryAssignment(adminKey: string, candidate: string)
	{
		var outcome = "?" ;
		
		if (outcome != "?")
		{
			outcome = outcome + "&" ;
		}
		
		outcome = outcome + "adminKey={adminKey}" ;

		if (outcome != "?")
		{
			outcome = outcome + "&" ;
		}
		
		outcome = outcome + "candidate={candidate}" ;

		outcome = outcome.replace("{adminKey}", "" + adminKey) ;
		outcome = outcome.replace("{candidate}", "" + candidate) ;

		return outcome ;
	}
	
	/**
	 * The following method assign every header parameter with the value
     */	
	rememberQuizHeaderAssignment()
	{
		var headerParameters = {} ;
		

		return headerParameters ;
	}


	/**
	 *
     * @param quiz The quiz
     * @param adminKey the admin key
     * @param candidate the candidate
	 */
	storeQuiz(listener: RestListener, scheme: string, quiz: Quiz[], adminKey: string, candidate: string)
	{
		// Local Reference of this class
		var self = this ;
		
		// Validate the input scheme
		var isValidInputScheme = this.storeQuizValidateScheme(scheme) ;
		if (!isValidInputScheme)
		{
			var invalidInputScheme = 400 ;
			var exceptionError	   = "Javascript Client contains an invalid input scheme: '" + scheme + "'. Please use one of the defined in the specification." ;
			var exceptionMessage   = exceptionError ;
			var stackTraceError    ;
			var path   			   ;
			var timestamp		   ;
					
            var outcome = this.generateCommonException(invalidInputScheme, exceptionError, exceptionMessage, stackTraceError, path, timestamp) ;
			listener.storeQuizCommonException(outcome) ;
		}
		else
		{
			// URL with scheme
			var urlWithScheme	 = this.getBaseURI(scheme) + "api/quiz/" ;

			// Replace every path parameter with the input value
			var urlWithPaths     = this.storeQuizPathAssignment(urlWithScheme) ;
			
			// Replace every query parameter with the input value
			var queryParameters  = this.storeQuizQueryAssignment(adminKey, candidate) ;
			
			// Assign a final URL with all the parameters assigned to
			var fullURL			 = urlWithPaths + queryParameters ;
			
			// Set the header parameters
			var headerParameters = this.storeQuizHeaderAssignment() ;
	
			// Verify if there is any body or one or many formData parameter
			var formDataInstance ;
 			formDataInstance = JSON.stringify(quiz) ;
			// Promise example
			var promiseFunction  = $.when($.ajax({type: "POST",
												  url: fullURL,
												  headers: headerParameters,
												  data: formDataInstance,
												  processData: false,
												  contentType: "application/json" })) ;
	
			// Create the observable
			var source 			 = Rx.Observable.fromPromise(promiseFunction) ;
	
			// Create the subscription
			var subscription 	 = source.subscribe(function (value)
											   	    {
														listener.storeQuiz() ;
											   	    },
											   	    function (error)
											   	    {
											   	    	var outcome ;
														var value = error.responseJSON ;
														outcome = self.generateCommonExceptionFromErrorInstance(error.status, value) ;
														listener.storeQuizCommonException(outcome) ;
											   	    }) ;
		}
	}
	
	/**
	 * Validate the input scheme with the expected for this 
	 */
	storeQuizValidateScheme(scheme)
	{
		return scheme == "http" ;
	}

	/**
	 * The following method assign every path parameter with the value
     */
	storeQuizPathAssignment(urlWithScheme: string)
	{

		return urlWithScheme ;
	}
	
	/**
	 * The following method assign every query parameter with the value
     */	
	storeQuizQueryAssignment(adminKey: string, candidate: string)
	{
		var outcome = "?" ;
		
		if (outcome != "?")
		{
			outcome = outcome + "&" ;
		}
		
		outcome = outcome + "adminKey={adminKey}" ;

		if (outcome != "?")
		{
			outcome = outcome + "&" ;
		}
		
		outcome = outcome + "candidate={candidate}" ;

		outcome = outcome.replace("{adminKey}", "" + adminKey) ;
		outcome = outcome.replace("{candidate}", "" + candidate) ;

		return outcome ;
	}
	
	/**
	 * The following method assign every header parameter with the value
     */	
	storeQuizHeaderAssignment()
	{
		var headerParameters = {} ;
		

		return headerParameters ;
	}


	/**
	 *
     * @param adminKey the admin key
     * @param candidate the candidate
	 */
	checkQuiz(listener: RestListener, scheme: string, adminKey: string, candidate: string)
	{
		// Local Reference of this class
		var self = this ;
		
		// Validate the input scheme
		var isValidInputScheme = this.checkQuizValidateScheme(scheme) ;
		if (!isValidInputScheme)
		{
			var invalidInputScheme = 400 ;
			var exceptionError	   = "Javascript Client contains an invalid input scheme: '" + scheme + "'. Please use one of the defined in the specification." ;
			var exceptionMessage   = exceptionError ;
			var stackTraceError    ;
			var path   			   ;
			var timestamp		   ;
					
            var outcome = this.generateCommonException(invalidInputScheme, exceptionError, exceptionMessage, stackTraceError, path, timestamp) ;
			listener.checkQuizCommonException(outcome) ;
		}
		else
		{
			// URL with scheme
			var urlWithScheme	 = this.getBaseURI(scheme) + "api/quiz/" ;

			// Replace every path parameter with the input value
			var urlWithPaths     = this.checkQuizPathAssignment(urlWithScheme) ;
			
			// Replace every query parameter with the input value
			var queryParameters  = this.checkQuizQueryAssignment(adminKey, candidate) ;
			
			// Assign a final URL with all the parameters assigned to
			var fullURL			 = urlWithPaths + queryParameters ;
			
			// Set the header parameters
			var headerParameters = this.checkQuizHeaderAssignment() ;
	
			// Verify if there is any body or one or many formData parameter
			var formDataInstance ;
			// Promise example
			var promiseFunction  = $.when($.ajax({type: "PUT",
												  url: fullURL,
												  headers: headerParameters,
												  data: formDataInstance,
												  processData: false,
												  contentType: "application/json" })) ;
	
			// Create the observable
			var source 			 = Rx.Observable.fromPromise(promiseFunction) ;
	
			// Create the subscription
			var subscription 	 = source.subscribe(function (value)
											   	    {
														var outcome ;
														if (value != null && value != "undefined")
														{
	    									  		 		outcome = [] ;
	
		    									  		 	var length1 = value.length ;
		    									  		 	for (var i1=0 ; i1 < length1 ; i1++)
		    									  		 	{
																outcome[i1] = [] ;
										  		 				outcome[i1] = Quiz.parse(value[i1]) ;
															}
														}
														
	    									  		 	listener.checkQuiz(outcome) ;
											   	    },
											   	    function (error)
											   	    {
											   	    	var outcome ;
														var value = error.responseJSON ;
														outcome = self.generateCommonExceptionFromErrorInstance(error.status, value) ;
														listener.checkQuizCommonException(outcome) ;
											   	    }) ;
		}
	}
	
	/**
	 * Validate the input scheme with the expected for this 
	 */
	checkQuizValidateScheme(scheme)
	{
		return scheme == "http" ;
	}

	/**
	 * The following method assign every path parameter with the value
     */
	checkQuizPathAssignment(urlWithScheme: string)
	{

		return urlWithScheme ;
	}
	
	/**
	 * The following method assign every query parameter with the value
     */	
	checkQuizQueryAssignment(adminKey: string, candidate: string)
	{
		var outcome = "?" ;
		
		if (outcome != "?")
		{
			outcome = outcome + "&" ;
		}
		
		outcome = outcome + "adminKey={adminKey}" ;

		if (outcome != "?")
		{
			outcome = outcome + "&" ;
		}
		
		outcome = outcome + "candidate={candidate}" ;

		outcome = outcome.replace("{adminKey}", "" + adminKey) ;
		outcome = outcome.replace("{candidate}", "" + candidate) ;

		return outcome ;
	}
	
	/**
	 * The following method assign every header parameter with the value
     */	
	checkQuizHeaderAssignment()
	{
		var headerParameters = {} ;
		

		return headerParameters ;
	}


		/**
		 * @param statusCode with the status code
		 * @param error with the instance of the error
		 * @return a new instance of common exception
		 */
		generateCommonExceptionFromErrorInstance(statusCode, error)
		{
			var errorString ;
			var message     ;
			var exception   ;
			var path 	    ;
			var timestamp   ;
			
			if (error != undefined)
			{
				errorString = error.error ;
				message     = error.message ;
				exception   = error.exception ;
				path 	    = error.path ;
				timestamp   = error.timestamp ;
			}
		
			return this.generateCommonException(statusCode, errorString, message, exception, path, timestamp) ;
		}
	
		/**
		 * @param statusCode  with the status code
		 * @param errorString with the error message
		 * @param message 	  with the exception message
		 * @param exception   with the stack trace error
		 * @param path        with the path error
		 * @param timestamp   with the timestamp
		 * @return a new instance of common exception
		 */
	    generateCommonException(statusCode: number, errorString: string, message: string, exception: string, path: string, timestamp: number)
	    {
	    	var commonException = new CommonException(statusCode) ;
	
			commonException.setError(errorString) ;
			commonException.setMessage(message) ;
			commonException.setException(exception) ;
			commonException.setPath(path) ;
			commonException.setTimestamp(timestamp) ;
						
	        return commonException ;
	    }
    }
}
