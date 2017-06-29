<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<section style="background-color: #201B3A;">
	<div style="margin: 10%; margin-top: 5%; margin-bottom: 5%;">
	<!-- Post Info -->
        <article>
         <c:set var="first" value="${gioco[0]}"/>

          <!-- Description -->
          <h2 class="mt-0">Descrizione</h2>
          <div class="description">
            <p>
            	${first.descrizione}
            </p>
          </div>
          <!-- /Description -->

        </article>
        <!-- /Post Info -->


        <!-- Information -->
        <div class="requirements-block">
          <h2>Requisiti di Sistema</h2>
          ${first.specifiche}
        </div>
        </div>
        <!-- /Information -->
     </section>