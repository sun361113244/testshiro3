var DATATABLEHEIGHT = 270;
var RES_UNAUTHORIZED = 403;
var RES_SUCCESS = 1;

function getContextPath()
{
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}
function getCurrentMonth()
{
    var date=new Date;
    var month=date.getMonth()+1;
    return month;
}