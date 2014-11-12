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
        
        <!-- Bootstrap validator -->
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.min.css"/>
        <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/js/bootstrapValidator.min.js"></script>
    </head>
    <body>
        <div class="container"><jsp:include page="header.jsp" flush="true"/></div>
        <div id="surveyAlert" style="display: none;" style="width: 300px;">
            <div class="alert alert-success">
                <a href="#" class="close" data-dismiss="alert">&times;</a>
                <strong>Success!</strong> Your survey has been submitted!
            </div>
        </div>
        <div class="container" id="surveyContainer">
            <div id="surveyBox" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-msm-offset-2 col-xs-8 col-xs-offset-2">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Player Survey</h3>
                    </div> <!-- panel heading -->
                    <div class="panel-body">
                        <form role="form" id="surveyForm">
                            <div class="panel-body">
                                <div class="form-group">
                                    <label for="sex">Sex:</label>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="sex" value="m" /> Male
                                        </label>
                                    </div>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="sex" value="f" /> Female
                                        </label>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label for="height">Height:</label>
                                    <input id="height" name="height" type="text" placeholder="height in inches" class="form-control input-md">
                                </div>
                                
                                <div class="form-group">
                                    <label for="weight">Weight:</label>
                                    <input id="weight" name="weight" type="text" placeholder="weight in pounds" class="form-control input-md">
                                </div>
                                
                                <div class="form-group">
                                    <label for="compYears">Competitive years played in this sport:</label>
                                    <select id="compYears" name="compYears" class="form-control">
                                      <option value=""> Select...</option>
                                      <option value="1">0</option>
                                      <option value="2">1</option>
                                      <option value="3">2</option>
                                      <option value="4">3</option>
                                      <option value="5">More than 3</option>
                                    </select>
                                </div>
                                
                                <div class="form-group">
                                    <label for="intsPlayed">Number of times playing in this intramural:</label>
                                    <select id="intsPlayed" name="intsPlayed" class="form-control">
                                        <option value=""> Select...</option>
                                        <option value="1">0</option>
                                        <option value="2">1</option>
                                        <option value="3">2</option>
                                        <option value="4">3</option>
                                        <option value="5">More than 3</option>
                                    </select>
                                </div>
                                
                                <div class="form-group">
                                    <label for="compLvl">How competitive do you want to be:</label>
                                    <select id="compLvl" name="compLvl" class="form-control">
                                        <option value=""> Select...</option>
                                        <option value="1">1 - not competitive</option>
                                        <option value="2">2</option>
                                        <option value="3">3 - neutral</option>
                                        <option value="4">4</option>
                                        <option value="5">5 - very competitive</option>
                                    </select>
                                </div>
                      
                                <div class="form-group">
                                    <label for="isClubPlayer">University club experience in this sport:</label>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="isClubPlayer" id="isClubPlayer-0" value="false"> No
                                        </label>
                                    </div>
                                    
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="isClubPlayer" id="isClubPlayer-1" value="true"> Yes
                                        </label>
                                    </div>
                                </div>
                                
                                <div class="btn-group">
                                    <input type="submit" name="submitSurvey" class="btn btn-primary" value="Submit!"/>
                                    <button class="btn btn-default" type="button" onClick="cancelForm()">Cancel</button>
                                </div>
                            </div>
                        </form>
                    </div> <!-- panel body -->
                </div> <!-- panel -->
            </div> <!-- survey box -->
        </div> <!-- container -->
    </body>
</html>
