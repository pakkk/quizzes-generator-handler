
/*********************************************************************************************************************************
 This class has been automatically generated using KLTT-APIRestGenerator project, don't do manual file modifications.
 Mon Oct 16 13:05:10 CEST 2017

 "Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements;
  and to You under the Apache License, Version 2.0. "
**********************************************************************************************************************************/
/// <reference path="../generated/rest/RestListener_1.0.0.ts" />
/// <reference path="../generated/rest/RestHandler_1.0.0.ts" />

	// Map of functions
	var mapOfFunctions     = new Object() ;
	
	// Utility variables to calculate if the "Select All" checkbox should be selected or not.
	var operationsSelected = 0 ;
	var operationsNumber   = 8 ;
	
	/**
	 * Update the number of operation selected
	 */
	function updateOperationsSelected(checkBoxId)
	{
		var element = <HTMLInputElement> document.getElementById(checkBoxId);
		var isChecked = element.checked ;
		
		var checkerUncheckerState = false ;
		if (isChecked)
		{
			operationsSelected ++ ;
			checkerUncheckerState = operationsSelected == operationsNumber ; 
		}
		else
		{
			operationsSelected -- ;
		}
		
		var checkerUnchecker = <HTMLInputElement> document.getElementById("checkerUnchecker");
		checkerUnchecker.checked = checkerUncheckerState ;
	}
	
	/**
	 * Initialization function
	 */
	function initialize()
	{
		// Initialize the check-unchecker all the checkboxes
		$('#checkerUnchecker').click(function(event)
		{
	        if(this.checked)
	        {
	            $('.myCheckBox').each(function() 
	            {
	                this.checked 	   = true ;
	                operationsSelected = operationsNumber ;               
	            });
	        }
	        else
	        {
	            $('.myCheckBox').each(function()
	            {
	                this.checked 	 = false ;
	                operationsNumber = 0 ;                  
	            });         
	        }
	    });

		// Initialize the javaquiz_api.RestHandler
		var restHandler = new javaquiz_api.RestHandler() ;

		// Generate a map with the key as 'operationId' and the value as the test function to be executed  
		mapOfFunctions["fullAnswers"] = function() { return fullAnswersTest(restHandler) } ;

		mapOfFunctions["setAdminKey"] = function() { return setAdminKeyTest(restHandler) } ;

		mapOfFunctions["existsCandidate"] = function() { return existsCandidateTest(restHandler) } ;

		mapOfFunctions["specificAnswer"] = function() { return specificAnswerTest(restHandler) } ;

		mapOfFunctions["generateQuiz"] = function() { return generateQuizTest(restHandler) } ;
		mapOfFunctions["rememberQuiz"] = function() { return rememberQuizTest(restHandler) } ;
		mapOfFunctions["storeQuiz"] = function() { return storeQuizTest(restHandler) } ;
		mapOfFunctions["checkQuiz"] = function() { return checkQuizTest(restHandler) } ;

	}

	function startTest()
	{
		$("#result").empty() ;

		$(".myCheckBox:checked").each(function(){
    		mapOfFunctions[this.id]() ;
		});
	}
	
	function fullAnswersTest(restHandler)
	{
		// Set a new listener for this call
		var listener = new javaquiz_api.RestListener() ;
	
		// Set one of the "schemes" defined
		var scheme = SchemesValues.http ;
		
		// Generate the random values
		var adminKey = RandomValues.generateRandomString() ;

		// Do the "restHandler" call
		restHandler.fullAnswers(listener, scheme, adminKey) ;
	}

	function setAdminKeyTest(restHandler)
	{
		// Set a new listener for this call
		var listener = new javaquiz_api.RestListener() ;
	
		// Set one of the "schemes" defined
		var scheme = SchemesValues.http ;
		
		// Generate the random values
		var adminKey = RandomValues.generateRandomString() ;

		// Do the "restHandler" call
		restHandler.setAdminKey(listener, scheme, adminKey) ;
	}

	function existsCandidateTest(restHandler)
	{
		// Set a new listener for this call
		var listener = new javaquiz_api.RestListener() ;
	
		// Set one of the "schemes" defined
		var scheme = SchemesValues.http ;
		
		// Generate the random values
		var adminKey = RandomValues.generateRandomString() ;
		var candidate = RandomValues.generateRandomString() ;

		// Do the "restHandler" call
		restHandler.existsCandidate(listener, scheme, adminKey, candidate) ;
	}

	function specificAnswerTest(restHandler)
	{
		// Set a new listener for this call
		var listener = new javaquiz_api.RestListener() ;
	
		// Set one of the "schemes" defined
		var scheme = SchemesValues.http ;
		
		// Generate the random values
		var answerId = RandomValues.generateRandomNumber() ;
		var adminKey = RandomValues.generateRandomString() ;

		// Do the "restHandler" call
		restHandler.specificAnswer(listener, scheme, answerId, adminKey) ;
	}

	function generateQuizTest(restHandler)
	{
		// Set a new listener for this call
		var listener = new javaquiz_api.RestListener() ;
	
		// Set one of the "schemes" defined
		var scheme = SchemesValues.http ;
		
		// Generate the random values
		var adminKey = RandomValues.generateRandomString() ;
		var candidate = RandomValues.generateRandomString() ;

		// Do the "restHandler" call
		restHandler.generateQuiz(listener, scheme, adminKey, candidate) ;
	}

	function rememberQuizTest(restHandler)
	{
		// Set a new listener for this call
		var listener = new javaquiz_api.RestListener() ;
	
		// Set one of the "schemes" defined
		var scheme = SchemesValues.http ;
		
		// Generate the random values
		var adminKey = RandomValues.generateRandomString() ;
		var candidate = RandomValues.generateRandomString() ;

		// Do the "restHandler" call
		restHandler.rememberQuiz(listener, scheme, adminKey, candidate) ;
	}

	function storeQuizTest(restHandler)
	{
		// Set a new listener for this call
		var listener = new javaquiz_api.RestListener() ;
	
		// Set one of the "schemes" defined
		var scheme = SchemesValues.http ;
		
		// Generate the random values

			var quiz = [] ;
			for (var i1=0 ; i1 < 5 ; i1++)
			{
				quiz[i1] = [] ;

				quiz[i1] = javaquiz_api.Quiz.generateRandomInstance() ;
			}
		var adminKey = RandomValues.generateRandomString() ;
		var candidate = RandomValues.generateRandomString() ;

		// Do the "restHandler" call
		restHandler.storeQuiz(listener, scheme, quiz, adminKey, candidate) ;
	}

	function checkQuizTest(restHandler)
	{
		// Set a new listener for this call
		var listener = new javaquiz_api.RestListener() ;
	
		// Set one of the "schemes" defined
		var scheme = SchemesValues.http ;
		
		// Generate the random values
		var adminKey = RandomValues.generateRandomString() ;
		var candidate = RandomValues.generateRandomString() ;

		// Do the "restHandler" call
		restHandler.checkQuiz(listener, scheme, adminKey, candidate) ;
	}


	function printOutcomeNoOutcome(operationId)
	{
		$("#result").append("<p>Operation " + operationId + " was executed properly.</p>") ;
	}
	
	function printOutcome(operationId, outcome)
	{
		$("#result").append("<p>Operation " + operationId + " was executed properly with the following outcome: " + outcome + "</p>") ;
	}

