<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="../build/css/custom.min.css" rel="stylesheet">   
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
    
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="">
<div class="page-title">
            <div class="title_left">
                <h3>Show User</h3>
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
                            <th>Player Username</th>
                            <th>Player Name</th>
                            <th>Player Surname</th>
                            <th>Email</th>
                            <th> </th>


                        </tr>
                        </thead>


                        

                        
<c:forEach items="${utente}" var="item">
<tbody>
                            <tr>
                            <c:forEach items="${item}" var="pippo">
                            <td class="col-md-2 col-sm-3 col-xs-12"> ${pippo}</td>                                                                          
                                                         </c:forEach>
                                                        <td class="col-md-1 col-sm-3 col-xs-12">
                            
                                <a href="showUser.op?del=${1}&id=${item[0]}"><button type="submit" id="upload2"  class="btn btn-success"value="Upload Image" name="submit"> Delete</button></a>
                                <a href="editUser.op?del=${0}&id=${item[0]}"><button type="submit" id="upload"  class="btn btn-success"value="Upload Image" name="submit"> Edit</button></a>
                            </td>
                        
                         </c:forEach>
                        
                    
                    </table>
                </div>
            </div>
        </div>


    </div>

</div>
