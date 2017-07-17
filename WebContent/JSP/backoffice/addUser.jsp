<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container body" >
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
    
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <div class="page-title">
            <div class="title_left">
                <h3>Add User</h3>
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
                    <div class="x_content">
                        <br />
                        <form id="demo-form2" data-parsley-validate class="form-horizontal form-label-left" method="POST" action="addUser.op">


                            <div class="form-group">
                                <label for="username" class="control-label col-md-3 col-sm-3 col-xs-12">Insert Username <span class="required">*</span></label>
                                <div class="col-md-3 col-sm-2 col-xs-12">
                                    <input id="username" required="required" name="username" class="form-control col-md-7 col-xs-12" type="text" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="ban" >Insert Ban <span class="required">*</span>
                                </label>
                                <div class="col-md-2 col-sm-3 col-xs-3">


                                    <input type="radio" name="ban" value=1/> Yes
                                    <br>
                                    <input type="radio" name="ban" value=0/> No

                                </div>
                                <br>
                                <br>
                            </div>
                            <div class="form-group">
                                <label for="cognome" class="control-label col-md-3 col-sm-3 col-xs-12">Insert Surname <span class="required">*</span></label>
                                <div class="col-md-3 col-sm-2 col-xs-12">
                                    <input id="cognome" required="required" name="cognome" class="form-control col-md-7 col-xs-12" type="text" >
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="email" class="control-label col-md-3 col-sm-3 col-xs-12">Insert Email <span class="required">*</span></label>
                                <div class="col-md-4 col-sm-2 col-xs-12">
                                    <input id="email" required="required" name="email" class="form-control col-md-7 col-xs-12" type="email" >
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="eta" class="control-label col-md-3 col-sm-3 col-xs-12">Insert Age <span class="required">*</span></label>
                                <div class="col-md-1 col-sm-2 col-xs-12">
                                    <input id="eta" required="required" name="eta" class="form-control col-md-7 col-xs-12" type="number" >
                                </div>
                            </div>

                            



                            <div class="form-group">
                                <label for="nome" class="control-label col-md-3 col-sm-3 col-xs-12">Insert Name <span class="required">*</span></label>
                                <div class="col-md-3 col-sm-2 col-xs-12">
                                    <input id="nome" required="required" name="nome" class="form-control col-md-7 col-xs-12" type="text" >
                                </div>
                            </div>


                            
                            
                            <div class="form-group">
                                <label for="password" class="control-label col-md-3 col-sm-3 col-xs-12">Insert Password <span class="required">*</span></label>
                                <div class="col-md-3 col-sm-2 col-xs-12">
                                    <input id="password" required="required" name="password" class="form-control col-md-7 col-xs-12" type="password" >
                                </div>
                            </div>
                        

            <div class="form-group">
                                <label for="script" class="control-label col-md-3 col-sm-3 col-xs-12">Select Group <span class="required">*</span></label>
                                <div class="col-md-3 col-sm-5 col-xs-12">

                                    <select name="gruppo">
                                        <optgroup >
           <c:forEach begin="0" end="${fn:length(gruppo) - 1}" var="yy">
                                            <option value="${gruppo[yy]}"> <c:out value="${gruppo[yy]}"/> </option>                                 
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