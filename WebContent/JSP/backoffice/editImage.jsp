<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
    
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>Edit Image</h3>
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
                        <h2> Attention!<small></small></h2>

                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <br />


                        <form id="demo-form2" data-parsley-validate class="form-horizontal form-label-left" method="POST" action="editImage.op">

                              <div class="form-group">
                                <label for="gioco" class="control-label col-md-3 col-sm-3 col-xs-12">Select Game <span class="required">*</span></label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
<select name="gioco" type="file">
                                        <optgroup >
<c:forEach items="${gioco}" var="game">
                                            <option value="${game.nome}" > <c:out value="${game.nome}"/> </option>
                                           
</c:forEach>
</optgroup>
                                    </select>
                                </div>
                            </div>
                            
							
							
							<div class="form-group">
														<label for="fileToUpload" class="control-label col-md-3 col-sm-3 col-xs-12">Mantain Photo <span class="required"></span></label>
							
							 <div class="col-md-6 col-sm-6 col-xs-12">
							<img align="left"src="assets/images/games/${foto[0].gioco.nome}/${foto[0].path}" style="width: 80% ; height: auto"/>
							</div>
                            </div>
                            
                            <input type="hidden" name="id" value="${idImg}">



                            <div class="ln_solid"></div>
                            <div class="form-group">
                                <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                                    <button type="submit" class="btn btn-primary">Cancel</button>
                                    <button type="submit" class="btn btn-success"value="Upload" name="submit"> Edit</button>
                                </div>
                            </div>

                        </form>
                        
                     
                  
                    </div>
                </div>
            </div>
        </div>
    </div>
