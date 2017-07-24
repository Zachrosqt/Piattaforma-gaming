<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="../build/css/custom.min.css" rel="stylesheet">   
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
    
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="">
<div class="page-title">
            <div class="title_left">
                <h3>Show GroupPermission</h3>
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
                
                <form id="demo-form2" data-parsley-validate class="form-horizontal form-label-left" method="POST" action="showGroupPermission.op" >



                            <div class="form-group">
                                <label for="nome" class="control-label col-md-3 col-sm-3 col-xs-12">Select Group <span class="required">*</span></label>
                                <div class="col-md-3 col-sm-5 col-xs-12">

                                    <select name="nome" onchange="this.form.submit()">
                                        <optgroup >
                                         <option value="" > --- </option>
										<c:forEach items="${gruppo}" var="group">
                                            <option value="${group.nome}" > <c:out value="${group.nome}"/> </option>
                                           
										</c:forEach>
										</optgroup>
                                    </select>

                                </div>
                           </div>
                           
                  </form>

				<c:if test = "${test == true}"> 
                   <table id="datatable" class="table table-striped table-bordered">
                        <thead>
                        <tr>
                            <th> Gruppo</th>
                            <th> Permesso</th>
                            <th> </th>
                           


                       </tr>
                        </thead>


                        

                       
						<c:forEach items="${perm}" var="permesso">

                          <tr>
                            
                            <td class="col-md-2 col-sm-3 col-xs-12">${groupToControl.nome}</td>
                            <td class="col-md-2 col-sm-3 col-xs-12">${permesso.nome} (${permesso.indirizzo})</td>                                                                          
                                                                                                      

                                                         
                                                         
                            <td class="col-md-1 col-sm-3 col-xs-12">
                             
                                <a href="showGroupPermission.op?del=1&g=${groupToControl.nome}&p=${permesso.nome}"><button type="submit" id="upload2"  class="btn btn-success"value="Upload Image" name="submit"> Delete</button></a>
                            </td>
                      
                         </tr>
                        </c:forEach>
                       
                    
                    </table>
                  </c:if>
                </div>
            </div>
        </div>


    </div>

</div>