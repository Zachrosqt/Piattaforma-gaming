  
    <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="../build/css/custom.min.css" rel="stylesheet">   
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
    
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="">


        <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>Edit Trophy</h3>
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
                        <h2>Trofeo: ${trofeo[0].nome}</h2>

                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <br />


                        <form id="demo-form2" data-parsley-validate class="form-horizontal form-label-left" method="POST" enctype="multipart/form-data" action="editTrophy.op?id=${trofeo[0].nome}">


						<c:forEach items="${trofeo}" var="item">


                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="icona" >Insert Icons <span class="required">*</span>
                                </label>
                                <div class="col-md-4 col-sm-4 col-xs-3">
                                    <input type="file" id="uploadfile" name="file">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="obiettivo" >Insert Objective <span class="required">*</span>
                                </label>
                                <div class="col-md-3 col-sm-3 col-xs-3">
                                    <input type="text" id="objective" name="objective" required="required" class="form-control col-md-7 col-xs-12" value="${item.obiettivo}">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="gioco" >Select Game <span class="required">*</span>
                                </label>
                                <div class="col-md-3 col-sm-5 col-xs-12">

                                    <select name="gioco">
                                        <optgroup >
           								<c:forEach items="${gioco}" var="game">
                                            <option value="${game.nome}"> <c:out value="${game.nome}"/> </option>                                 
										</c:forEach>
                                      </optgroup>


                                    </select> 
                                  

                                </div>
                            </div>
 </c:forEach>






                            <div class="ln_solid"></div>
                            <div class="form-group">
                                <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                                    <button type="submit" class="btn btn-success" name="submit"> Edit</button>
                                </div>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>