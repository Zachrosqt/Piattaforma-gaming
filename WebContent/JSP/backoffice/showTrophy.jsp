<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="../build/css/custom.min.css" rel="stylesheet">   
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
    
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="">
<div class="page-title">
            <div class="title_left">
                <h3>Show Trophy</h3>
            </div>

            <div class="title_right">
                <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">

                </div>
            </div>
        </div>

    <div class="clearfix"></div>

    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_content">

                    <table id="datatable" class="table table-striped table-bordered">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Objective</th>
                            <th>Game</th>
                            <th> </th>
                            


                        </tr>
                        </thead>


                        

                        
<c:forEach items="${trofeo}" var="item">


                            <tr>
                            <c:forEach items="${item}" begin="0" end="${fn:length(item) - 3 }" var="go">
                            <td class="col-md-2 col-sm-3 col-xs-12"> ${item[0]}</td>
                            <td class="col-md-2 col-sm-3 col-xs-12"> ${item[1]}</td>    
                            <td class="col-md-2 col-sm-3 col-xs-12"> ${item[2].nome}</td>    
                            
                                                                                                                         
                                                                                                                     
                                                                                                                                          
                                                         
                                                         
                                                                   
                                                                                      
                                                        <td class="col-md-2 col-sm-3 col-xs-12">
                            
                               <a href="showTrophy.op?del=${1}&id=${item[0]}"><button type="submit" id="upload2"  class="btn btn-success"value="Upload Image" name="submit"> Delete</button></a>
                                <a href="editTrophy.op?del=${0}&id=${item[0]}"><button type="submit" id="upload"  class="btn btn-success"value="Upload Image" name="submit"> Edit</button></a>
                            </td>
                                                                                     </c:forEach>
                            <tr>
                             </c:forEach>
                                               
                
                    
                    </table>
                </div>
            </div>
        </div>


    </div>

</div>
