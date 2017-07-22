<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="../build/css/custom.min.css" rel="stylesheet">
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
    
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


 <div class="">
 <div class="page-title">
            <div class="title_left">
                <h3>Show Category</h3>
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
                            <th>Categoria</th>


                            <th></th>
                        </tr>
                        </thead>

<c:forEach items="${categoria}" var="item">
<tbody>
                            <tr>
                            <c:forEach items="${item}" var="pippo">
                            <td class="col-md-3 col-sm-3 col-xs-12"> ${pippo}</td>                                                                          
                             <td class="col-md-1 col-sm-3 col-xs-6">
                                <a href="showCategoria.op?del=${1}&id=${item}"><button type="submit" id="upload2"  class="btn btn-success"value="Upload Image" name="submit"> Delete</button></a>
                                <a href="editCategoria.op?id=${item}"><button type="submit" id="upload"  class="btn btn-success"value="Upload Image" name="submit"> Edit</button></a>
                            </td>
                                      </c:forEach>
                            </tr>
                        </c:forEach>
                                  

                                
                             
                        





                        </tbody>
                    </table>
                </div>
            </div>
        </div>


    </div>

</div>