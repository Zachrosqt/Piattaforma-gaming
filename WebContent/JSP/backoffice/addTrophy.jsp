<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
    
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container body" >

<div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>Add Trophy</h3>
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
                        <h2>Attention!<small></small></h2>

                        <div class="clearfix"></div>
                    </div>
                    <form id="demo-form2" data-parsley-validate class="form-horizontal form-label-left" method="POST" action="addTrophy.op" enctype="multipart/form-data">

                           <div class="form-group">
                                <label for="nome" class="control-label col-md-3 col-sm-3 col-xs-12">Insert Name <span class="required">*</span></label>
                                <div class="col-md-2 col-sm-2 col-xs-12">
                                    <input id="nome" required="required" name="nome" class="form-control col-md-7 col-xs-12" type="text" >
                                </div>
                            </div>


                              <div class="form-group">
                                <label for="fileToUpload" class="control-label col-md-3 col-sm-3 col-xs-12">Insert Icons <span class="required">*</span></label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                                                <input type="file" name="uploadFile" />
                                
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="obiettivo" class="control-label col-md-3 col-sm-3 col-xs-12">Insert Objective <span class="required">*</span></label>
                                <div class="col-md-1 col-sm-2 col-xs-12">
                                    <input id="obiettivo" required="required" name="obiettivo" class="form-control col-md-7 col-xs-12" type="number" >
                                </div>
                            </div>




                               <div class="form-group">
                                <label for="script" class="control-label col-md-3 col-sm-3 col-xs-12">Select Game <span class="required">*</span></label>
                                <div class="col-md-3 col-sm-5 col-xs-12">

                                    <select name="gioco">
                                        <optgroup >
           <c:forEach begin="0" end="${fn:length(gioco1) - 1}" var="yy">
                                            <option value="${gioco1[yy]}"> <c:out value="${gioco1[yy]}"/> </option>                                 
</c:forEach>
                                      </optgroup>


                                    </select> 
                                  

                                </div>
                            </div> 
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
        </div>
