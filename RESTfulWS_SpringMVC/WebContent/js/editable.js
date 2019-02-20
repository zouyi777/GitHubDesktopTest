/**
 * Created by Administrator on 2018-12-24.
 */
var nameLabels = document.getElementsByClassName("name-label");
for(var j=0;j<nameLabels.length;j++){
    nameLabels[j].addEventListener("click",function(){
        confirm("确定修改 ？");
    });
}
