<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
    
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="">

<div class="page-title">
            <div class="title_left">
                <h3>Show Recensione </h3>
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
                            <th>Recensione</th>

                            <th></th>
                        </tr>
                        </thead>


                        <tbody>
                        
                                            
                            
<c:forEach items="${path}" var="index">

                        <tr>
                        
                        
                            <td class="col-md-3 col-sm-3 col-xs-12">${index[0]}   </td>


                            <td class="col-md-1 col-sm-1 col-xs-12">
                                <a href="showRecensione.op?del=${0}&username=${index[1].utente.username}&nome=${index[1].gioco.nome}"><button type="submit" id="upload2"  class="btn btn-success"value="Upload Image" name="submit"> Approve</button></a>
                                 <a href="showRecensione.op?del=${1}&username=${index[1].utente.username}&nome=${index[1].gioco.nome}"><button type="submit" id="upload2"  class="btn btn-success"value="Upload Image" name="submit"> Delete</button></a>
        
                                <a href="editRecensione.op?username=${index[1].utente.username}&nome=${index[1].gioco.nome}"><button type="submit" id="upload"  class="btn btn-success"value="Upload Image" name="submit"> Edit</button></a>
                            </td>
                            
                        </tr>
       </c:forEach>
                        
                        </tbody>
                    </table>
                </div>
            </div>
        </div>


    </div>

</div>