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
        <div id="surveyAlert" style="display: none;" style="width: 300px;">
            <div class="alert alert-success">
                <a href="#" class="close" data-dismiss="alert">&times;</a>
                <strong>Success!</strong> Your survey has been submitted!
            </div>
        </div>
    
        <form class="form-horizontal" id="surveyForm">
            <fieldset>
              <legend>Player Evaluation for </legend>
              
              <div class="form-group">
                  <label class="col-md-4 control-label" for="sex">Sex:</label>
                  <div class="col-md-2">
                      <label class="radio-inline" for="sex-0">
                      <input type="radio" name="sex" id="sex-0" value="M">Male
                      </label> 
                      <label class="radio-inline" for="sex-1">
                          <input type="radio" name="sex" id="sex-1" value="F">Female
                      </label>
                  </div>
              </div>
              
              <div class="form-group">
                  <label class="col-md-4 control-label" for="height">Height:</label>  
                  <div class="col-md-2">
                      <input id="height" name="height" type="text" placeholder="height in inches" class="form-control input-md">
                  </div>
              </div>
              
              <div class="form-group">
                  <label class="col-md-4 control-label" for="weight">Weight:</label>  
                  <div class="col-md-2">
                      <input id="weight" name="weight" type="text" placeholder="weight in pounds" class="form-control input-md">
                  </div>
              </div>
              
              <div class="form-group">
                  <label class="col-md-4 control-label" for="compYears">Competitive years played in this sport:</label>
                  <div class="col-md-2">
                      <select id="compYears" name="compYears" class="form-control">
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
                  <label class="col-md-4 control-label" for="intsPlayed">Number of times playing in this intramural:</label>
                  <div class="col-md-2">
                      <select id="intsPlayed" name="intsPlayed" class="form-control">
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
                  <label class="col-md-4 control-label" for="compLvl">How competitive do you want to be:</label>
                  <div class="col-md-2">
                      <select id="compLvl" name="compLvl" class="form-control">
                          <option value=""> Select...</option>
                          <option value="5">5 - very competitive</option>
                          <option value="4">4</option>
                          <option value="3">3 - neutral</option>
                          <option value="2">2</option>
                          <option value="1">1 - not competitive</option>
                      </select>
                  </div>
              </div>

              <div class="form-group">
                  <label class="col-md-4 control-label" for="isClubMember">University club experience in this sport:</label>
                  <div class="col-md-2">
                      <label class="radio-inline" for="isClubMember-0">
                          <input type="radio" name="isClubMember" id="isClubMember-0" value="false">No
                      </label> 
                      <label class="radio-inline" for="isClubMember-1">
                          <input type="radio" name="isClubMember" id="isClubMember-1" value="true">Yes
                      </label>
                  </div>
              </div>
              
              <div class="form-group">
                    <label class="col-md-4 control-label" for="submitSurvey">Submit your Survey:</label>
                    <div class="col-md-2">
                        <input type="submit" name="submitSurvey" class="btn btn-primary" value="Submit!"/>
                        <button class="btn btn-default" type="button" onClick="cancelForm()">Cancel</button>
                    </div>
              </div>
              
            </fieldset>
        </form>
    </body>
</html>
