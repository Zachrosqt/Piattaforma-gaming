<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
    
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container body">

    <!-- page content -->

    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>Add Group Permission</h3>
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
                    <div class="x_title">
                        <h2>Attention! <small></small></h2>

                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <br />
                        <form id="demo-form2" data-parsley-validate class="form-horizontal form-label-left" method="POST" action="addGroupPermission.op" >



                            <div class="form-group">
                                <label for="nome" class="control-label col-md-3 col-sm-3 col-xs-12">Select Group <span class="required">*</span></label>
                                <div class="col-md-3 col-sm-5 col-xs-12">

                                    <select name="nome">
                                        <optgroup >
<c:forEach items="${gruppo}" var="group">
                                            <option value="${group.nome}" > <c:out value="${group.nome}"/> </option>
                                           
</c:forEach>
</optgroup>
                                    </select>

                                </div>
                           </div>
                           
                           <div class="form-group">
                                <label for="script" class="control-label col-md-3 col-sm-3 col-xs-12">Select Service <span class="required">*</span></label>
                                <div class="col-md-3 col-sm-5 col-xs-12">

                                 <c:forEach items="${servizio}" var="service">
                                  
                                 	<c:if test = "${service.nome != 'AccessDenied' && service.nome != 'Login' && service.nome != 'Registrazione'}">
                                 		<input type="checkbox" name="permessi" value="${service.nome}"/> ${service.nome} (${service.indirizzo})<br>
                                 	</c:if>
                                 </c:forEach>   

                                </div>
                            </div>



                            <div class="ln_solid"></div>

                            <div class="form-group">
                                <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                                    <button type="submit" class="btn btn-primary">Cancel</button>
                                    <button type="submit" id="upload2"  class="btn btn-success"value="Upload Image" name="submit"> Upload</button>

                                </div>
                            </div>
                        </form>
                    </div>

                </div>

            </div>
        </div>
















    </div>

    <!-- /page content -->

    <!-- footer content -->

    <!-- /footer content -->

</div>