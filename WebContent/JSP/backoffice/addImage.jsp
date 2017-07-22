<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
    
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>Add Image</h3>
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


                        <form id="demo-form2" data-parsley-validate class="form-horizontal form-label-left" method="POST" action="addImage.op" enctype="multipart/form-data">

                              <div class="form-group">
                                <label for="gioco" class="control-label col-md-3 col-sm-3 col-xs-12">Select Game <span class="required">*</span></label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
<select name="gioco" type="file">
                                        <optgroup >
<c:forEach begin="0" end="${fn:length(gioco) - 1}" var="index">
                                            <option value="${gioco[index]}" > <c:out value="${gioco[index]}"/> </option>
                                           
</c:forEach>
</optgroup>
                                    </select>
                                </div>
                            </div>
                            
                            
                            <div class="form-group">
                                <label for="fileToUpload" class="control-label col-md-3 col-sm-3 col-xs-12">Insert Photo <span class="required">*</span></label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                                                <input type="file" name="file" />
                                
                                </div>
                            </div>

                            <div class="ln_solid"></div>
                            <div class="form-group">
                                <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                                    <button type="submit" class="btn btn-primary">Cancel</button>
                                    <button type="submit" class="btn btn-success"value="Upload" name="submit"> Upload</button>
                                </div>
                            </div>

                        </form>
                        
                     
                  
                    </div>
                </div>
            </div>
        </div>
    </div>
