$(document).ready(function(){
    console.log("Document Loaded");
    
    $("#nameButton").click(function(){
        var name = prompt("Please enter a name");
        console.log("Name = " + name);
        $("#data").append("<div>" + name + "</div>");
    });
    
    $("#greenB").click(function() {
     var dataDiv = $("#data");
     if(dataDiv.hasClass("red")){
            dataDiv.removeClass("red");
     }
     dataDiv.addClass("green");
    
    });
    
    $("#redB").click(function() {
     var dataDiv = $("#data");
     if(dataDiv.hasClass("green")){
            dataDiv.removeClass("green");
     }
     dataDiv.addClass("red");
    
    });
});