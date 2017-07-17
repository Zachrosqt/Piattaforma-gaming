  
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
                        <h2> Attention!<small></small></h2>

                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <br />


                        <form id="demo-form2" data-parsley-validate class="form-horizontal form-label-left" method="POST" enctype="multipart/form-data">


<c:forEach items="${trofeo}" var="item">


                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="nome" >Insert Trophy's name <span class="required">*</span>
                                </label>
                                <div class="col-md-3 col-sm-3 col-xs-3">
                                    <input type="text" id="trophy" name="trophy" required="required" class="form-control col-md-7 col-xs-12" value="${item.nome}">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="icona" >Insert Icons <span class="required">*</span>
                                </label>
                                <div class="col-md-4 col-sm-4 col-xs-3">
                                    <input type="file" id="uploadfile" name="uploadfile" required="required" value="${item.icona}">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="obiettivo" >Insert Objective <span class="required">*</span>
                                </label>
                                <div class="col-md-1 col-sm-3 col-xs-3">
                                    <input type="number" id="objective" name="objective" required="required" class="form-control col-md-7 col-xs-12" value="${item.obiettivo}">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="gioco" >Insert Game <span class="required">*</span>
                                </label>
                                <div class="col-md-3 col-sm-3 col-xs-3">
                                    <input type="text" id="game" name="game" required="required" class="form-control col-md-7 col-xs-12" value="${item.gioco.nome}">
                                </div>
                            </div>
 </c:forEach>






                            <div class="ln_solid"></div>
                            <div class="form-group">
                                <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                                    <button type="submit" class="btn btn-primary">Cancel</button>
                                    <a href="editTrophy.op"><button type="submit" id="id" class="btn btn-success" name="id" value="${item}"> Upload</button> </a>
                                      
                                </div>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>