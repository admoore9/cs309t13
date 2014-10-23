<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <!-- CSS -->
        <link rel="stylesheet" href="../../resources/js/bootstrap/css/bootstrap.min.css">
        
        <!-- jQuery library -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        
        <!-- Bootstrap JavaScript plug-ins -->
        <script src="../../resources/js/bootstrap/js/bootstrap.min.js"></script>
        <script src="../../resources/js/survey.js"></script>
    </head>
    <body>
        <form class="form-horizontal" id="surveyForm" onsubmit="return validateForm()" action="/survey">
            <fieldset>
              <!-- Form Name -->
              <legend>Player Evaluation for </legend>
              
              <!-- Select Basic -->
              <div class="form-group">
                  <label class="col-md-4 control-label" for="sex">Sex:</label>
                  <div class="col-md-2">
                      <label class="radio-inline" for="sex-0">
                      <input type="radio" name="sex" id="sex-0" value="M" required value="1">
                          Male
                      </label> 
                      <label class="radio-inline" for="sex-1">
                          <input type="radio" name="sex" id="sex-1" value="F">
                          Female
                      </label>
                  </div>
              </div>
              
              <!-- Text input-->
              <div class="form-group">
                  <label class="col-md-4 control-label" for="height">Height:</label>  
                  <div class="col-md-4">
                      <input id="height" name="height" type="text" placeholder="height in inches" class="form-control input-md" required>
                  </div>
              </div>
              
              <!-- Text input-->
              <div class="form-group">
                  <label class="col-md-4 control-label" for="weight">Weight:</label>  
                  <div class="col-md-4">
                      <input id="weight" name="weight" type="text" placeholder="weight in pounds" class="form-control input-md" required>
                  </div>
              </div>
              
              <!-- Select Basic -->
              <div class="form-group">
                  <label class="col-md-4 control-label" for="competitive_years_played">Competitive (organized years played in this sport:</label>
                  <div class="col-md-2">
                      <select id="competative_years_played" name="competitive_years_played" class="form-control" required>
                        <option value=""> Select...</option>
                        <option value="0">0</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">5+</option>
                      </select>
                  </div>
              </div>
              
              <div class="form-group">
                    <label class="col-md-4 control-label" for="submitSurvey">Submit your Survey:</label>
                    <div class="col-md-2">
                        <input type="submit" name="submitSurvey" value="Submit!" />
                    </div>
              </div>
              
            </fieldset>
        </form>
    </body>
</html>
