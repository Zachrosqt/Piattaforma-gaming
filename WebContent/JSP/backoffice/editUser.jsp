  
    <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="../build/css/custom.min.css" rel="stylesheet">   
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
    
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="">


        <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>Edit User</h3>
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


                        <form id="demo-form2" data-parsley-validate class="form-horizontal form-label-left" method="POST" >


<c:forEach items="${utente}" var="item">


                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="username" >Insert Username <span class="required">*</span>
                                </label>
                                <div class="col-md-3 col-sm-3 col-xs-3">
                                    <input type="text" id="username" name="username" required="required" class="form-control col-md-7 col-xs-12" value="${item.username}">
                                </div>
                            </div>

                            <<div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="ban" >Insert Ban <span class="required">*</span>
                                </label>
                                <div class="col-md-2 col-sm-3 col-xs-3">


                                    <input type="radio" name="ban" value="1"> Yes
                                    <br>
                                    <input type="radio" name="ban" value="0"> No

                                </div>
                                <br>
                                <br>
                            </div>
                            
                            
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="surname" >Insert Surname <span class="required">*</span>
                                </label>
                                <div class="col-md-1 col-sm-3 col-xs-3">
                                    <input type="text" id="surname" name="surname" required="required" class="form-control col-md-7 col-xs-12" value="${item.cognome}">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email" >Insert Email <span class="required">*</span>
                                </label>
                                <div class="col-md-3 col-sm-3 col-xs-3">
                                    <input type="email" id="email" name="email" required="required" class="form-control col-md-7 col-xs-12" value="${item.email}">
                                </div>
                            </div>
                            
                           <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="eta" >Insert Age<span class="required">*</span>
                                </label>
                                <div class="col-md-1 col-sm-3 col-xs-3">
                                    <input type="number" id="eta" name="eta" required="required" class="form-control col-md-7 col-xs-12" value="${item.eta}">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="exp" >Insert Exp_tot <span class="required">*</span>
                                </label>
                                <div class="col-md-3 col-sm-3 col-xs-3">
                                    <input type="number" id="exp" name="exp" required="required" class="form-control col-md-7 col-xs-12" value="${item.exp_tot}">
                                </div>
                            </div> 
                            
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="nome" >Insert Name <span class="required">*</span>
                                </label>
                                <div class="col-md-1 col-sm-3 col-xs-3">
                                    <input type="text" id="nome" name="nome" required="required" class="form-control col-md-7 col-xs-12" value="${item.nome}">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="numeroaccessi" >Insert Number of Access <span class="required">*</span>
                                </label>
                                <div class="col-md-3 col-sm-3 col-xs-3">
                                    <input type="number" id="numeroaccessi" name="numeroaccessi" required="required" class="form-control col-md-7 col-xs-12" value="${item.numeroAccessi}">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="obiettivo" >Insert Password <span class="required">*</span>
                                </label>
                                <div class="col-md-1 col-sm-3 col-xs-3">
                                    <input type="password" id="password" name="password" required="required" class="form-control col-md-7 col-xs-12" value="${item.password}">
                                </div>
                            </div>



                            <div class="form-group">
                                <label for="script" class="control-label col-md-3 col-sm-3 col-xs-12">Select Group <span class="required">*</span></label>
                                <div class="col-md-3 col-sm-5 col-xs-12">
                                    <select name="gruppo">
                                        <optgroup >
                                        <c:forEach items="${utente1}" var="item1">
           
                                            <option value="${item1}"> <c:out value="${item1}"/> </option>                                 

      </c:forEach>

 </optgroup> 
                                    </select>  
                                                            
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