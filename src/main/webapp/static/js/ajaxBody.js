//function getViaAjax() {
//
//var result = $("#result").val();
//    $.ajax(
//        {
//            type: "GET",
//            url: "${home}/admin/bank",
//            data: result,
//            dataType: "json",
//            success: function (data) {
//                console.log("success", data);
//                display(data);
//            },
//            error: function (e) {

//                display(e);
//            }
//        }
//    )
//    function display(data) {
//        var json = "<h3> Response </h3>" + JSON.stringify(data, null, 4);
//        $("#feedback").html(json);
//    }
//}
//
//function postViaAjax() {
//    var data = {}
//    data["bank"] = $('#bank').val();
//    $.ajax({
//        type: "POST",
//        contentType: "application/json",
//        url: "{$home}/admin/newBank",
//        data: JSON.stringify(data),
//        dataType: 'json',
//        success: function (data) {
//            console.log("success", data);
//            display(data);
//        },
//        error: function (e) {
//            console.log("error", e)
//        }
//    })
//}