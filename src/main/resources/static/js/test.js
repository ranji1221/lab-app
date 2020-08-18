function test(){
    alert("aaa");
}
function find(){

    $.ajax({
        url:"/lab/allnews",
        success:function (e) {
            console.log(e)
        }
        }
    )
}