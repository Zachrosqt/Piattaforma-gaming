<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
    
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="">
<div class="page-title">
            <div class="title_left">
                <h3>Show Images</h3>
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
                            <th>Image</th>
                            <th>Gioco</th>

                            <th></th>
                        </tr>
                        </thead>


                        <tbody>
                        
                                            
<c:forEach items="${path}" var="index">
                            

                        <tr>
                        
                        
                            <td class="col-md-3 col-sm-3 col-xs-3">  <img style="width: 250px; height: auto;" src="assets/images/games/${index.gioco.nome}/${index.path}"> </td>
                            <td class="col-md-3 col-sm-3 col-xs-6"> ${index.gioco.nome}  </td>


                            <td class="col-md-1 col-sm-1 col-xs-12">
                                <a href="showImage.op?del=${1}&id=${index.id}"><button type="submit" id="upload2"  class="btn btn-success"value="Upload Image" name="submit"> Delete</button></a>
                                <a href="editImage.op?id=${index.id}"><button type="submit" id="upload"  class="btn btn-success"value="Upload Image" name="submit"> Edit</button></a>
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