function toogleDatatableSummary(element) {
    $span = $(element).find(".ui-button-icon-left"); 
    if($span.hasClass("ui-icon-plus")){
        $span.removeClass("ui-icon-plus");
        $span.addClass("ui-icon-minusthick");
    }else{
        $span.removeClass("ui-icon-minusthick");
        $span.addClass("ui-icon-plus");
    } 
    $(element).closest("tr").next("tr").toggle("fast");
}
